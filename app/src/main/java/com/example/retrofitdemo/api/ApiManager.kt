package com.example.retrofitdemo.api

import android.webkit.MimeTypeMap
import androidx.annotation.NonNull

import com.example.retrofitdemo.utils.AppConstants
import com.example.retrofitdemo.utils.Utility
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.OkHttpClient
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.internal.tls.OkHostnameVerifier
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit



class ApiManager {

    companion object {

        val instance: ApiRepo
            get() {
                val retrofit = retrofitInstance
                return retrofit.create(ApiRepo::class.java)
            }

        val retrofitInstance: Retrofit
            get() {
                val httpClient = OkHttpClient.Builder()
                httpClient.connectTimeout(2, TimeUnit.MINUTES)
//                if (BuildConfig.DEBUG) {
                val logging = HttpLoggingInterceptor()
                logging.level = HttpLoggingInterceptor.Level.BODY
                httpClient.addInterceptor(logging)
//                }

                val accessToken =
                    DataManager.getAccessToken()

                if (!accessToken.equals(""))
                    httpClient.addInterceptor(addAccessToken())

                return Retrofit.Builder()
                    .baseUrl(AppConstants.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(trustAllSslClient(httpClient.build()))
                    .build()
            }


        @NonNull
        private fun addAccessToken(): Interceptor {

            return Interceptor { chain ->
                val original = chain.request()
                val accessToken = DataManager.getAccessToken()
                val request = original.newBuilder()
                    .header("Accept", "application/json")
                    .addHeader("Authorization", "Bearer ${DataManager.getAccessToken()}")
                    .method(original.method, original.body)
                    .build()
                Utility.log("Authorization \nBearer $accessToken")
                chain.proceed(request)
            }
        }


        fun prepareFilePart(fileMap: HashMap<String, File>): List<MultipartBody.Part> {
            val finalFileMap = ArrayList<MultipartBody.Part>()

            for (paramName in fileMap.keys) {
                val mimeType = getMimeType(fileMap[paramName]!!.path)
                log("" + fileMap[paramName]!!.totalSpace + " is exist = " + mimeType)
//                val requestFile = RequestBody.create(
//                    MediaType.parse(mimeType ?: "image/*"),
//                    fileMap[paramName]!!)
                val requestFile = fileMap[paramName]!!
                    .asRequestBody((mimeType ?: "image/*").toMediaTypeOrNull())
                finalFileMap.add(
                    MultipartBody.Part.createFormData(
                        paramName,
                        fileMap[paramName]!!.name,
                        requestFile
                    )
                )

                finalFileMap.add(
                    MultipartBody.Part.createFormData(
                        paramName,
                        fileMap[paramName]!!.name,
                        requestFile
                    )
                )

            }

            if (finalFileMap.size == 0) {
                val file = "".toRequestBody(MultipartBody.FORM)
                finalFileMap.add(MultipartBody.Part.createFormData("dummy", "", file))
            }
            // MultipartBody.Part is used to send also the actual file name
            return finalFileMap
        }

        fun getMimeType(url: String): String? {
            var type: String? = null
            val extension = MimeTypeMap.getFileExtensionFromUrl(url)
            if (extension != null) {
                type = MimeTypeMap.getSingleton().getMimeTypeFromExtension(extension)
            }
            return type
        }

        fun trustAllSslClient(client: OkHttpClient): OkHttpClient {
            val builder = client.newBuilder()
            builder.readTimeout(60, TimeUnit.SECONDS)
            builder.connectTimeout(60, TimeUnit.SECONDS)
            //  builder.sslSocketFactory(trustAllSslSocketFactory, (X509TrustManager) trustAllCerts[0]);
//            builder.hostnameVerifier { hostname, session -> true }
            builder.hostnameVerifier(OkHostnameVerifier)
            return builder.build()
        }
    }
}