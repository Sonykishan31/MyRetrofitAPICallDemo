package com.example.retrofitdemo.api

import com.example.retrofitdemo.pojo.ApiResponseDemoApi
import com.example.retrofitdemo.pojo.MovieListModel
import com.example.retrofitdemo.utils.AppConstants
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query
import retrofit2.http.QueryMap

interface ApiRepo {


    @POST("${AppConstants.API_VERSION}/registerStep1")
    fun checkEmailValidation(@QueryMap params: HashMap<String, String>): Call<ApiResponseDemoApi>

    @GET("/")
    fun moverListOmDb(
        @Query("t") type: String,
        @Query("apikey") apiKey: String,
        @Query("s") search: String
    ): Call<MovieListModel>


//
//    @POST("${AppConstants.API_VERSION}/login")
//    fun login(@QueryMap params: HashMap<String, String>): Call<LoginModel>
//
//    @POST("${AppConstants.API_VERSION}/forgot/password")
//    fun forgotPassword(@QueryMap params: HashMap<String, String>): Call<ForgotModel>
//
//    @POST("${AppConstants.API_VERSION}/changepassword")
//    fun changePassword(@QueryMap params: HashMap<String, String>): Call<ForgotModel>
//
//    @Multipart
//    @POST("${AppConstants.API_VERSION}/register")
//    fun register(
//        @QueryMap params: HashMap<String, String>,
//        @Part file: List<MultipartBody.Part>
//    ): Call<LoginModel>
//
//    @Multipart
//    @POST("${AppConstants.API_VERSION}/profile/update")
//    fun updateProfile(
//        @QueryMap params: HashMap<String, String>,
//        @Part file: List<MultipartBody.Part>
//    ): Call<LoginModel>
//
//    @POST("${AppConstants.API_VERSION}/profile/update")
//    fun updateProfile(
//        @QueryMap params: HashMap<String, String>
//    ): Call<LoginModel>
//
//    @POST("${AppConstants.API_VERSION}/home")
//    fun getHomeBanner(): Call<HomeBannerModel>
//
//    @POST("${AppConstants.API_VERSION}/testList")
//    fun getTestList(@QueryMap params: HashMap<String, String>): Call<TestPackageSearchCommonPojo>
//
//    @POST("${AppConstants.API_VERSION}/update_location")
//    fun updateLocation(@QueryMap params: HashMap<String, String>): Call<LoginModel>
//
//    @POST("${AppConstants.API_VERSION}/packageList")
//    fun getPackageList(@QueryMap params: HashMap<String, String>): Call<TestPackageSearchCommonPojo>
//
//    @POST("${AppConstants.API_VERSION}/logout")
//    fun logout(): Call<CommonPojo>
//
//    @POST("${AppConstants.API_VERSION}/searchTestPackage")
//    fun searchTestPackage(@QueryMap params: HashMap<String, String>): Call<TestPackageSearchCommonPojo>
//
//    @POST("${AppConstants.API_VERSION}/estimatorList")
//    fun getDiscountList(): Call<DiscountListModel>
//
//    @Multipart
//    @POST("${AppConstants.API_VERSION}/bookings")
//    fun bookingAPI(
//        @QueryMap params: HashMap<String, String>,
//        @Part file: List<MultipartBody.Part>
//    ): Call<CommonPojo>
//
//    @POST("${AppConstants.API_VERSION}/bookingsHistory")
//    fun getTransactionHistory(@QueryMap params: HashMap<String, String>): Call<TransactionHistory>
//
//    @POST("${AppConstants.API_VERSION}/searchPhoneNumber")
//        fun searchPhoneNumber(@QueryMap params: HashMap<String, String>): Call<ListofPhoneNumbers>
//
//    @POST("${AppConstants.API_VERSION}/notification")
//    fun getNotificationList(@QueryMap params: HashMap<String, String>): Call<NotificationListPojo>
//
//    @POST("${AppConstants.API_VERSION}/user/is-approved")
//    fun isUserApprove(@QueryMap params: HashMap<String, String>): Call<IsApproved>
//
//    @POST("${AppConstants.API_VERSION}/CostCenterList")
//    fun getLocationData(): Call<LocationListPojo>
//
//    @POST("${AppConstants.API_VERSION}/getSalesExecutive")
//    fun getSalesExecutive(@QueryMap params: HashMap<String, String>): Call<AreaASMSalesExecutivePojo>

}