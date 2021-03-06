package com.example.retrofitdemo.api

import android.app.Activity
import android.content.Context
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.Toast
import com.example.retrofitdemo.AppClass
import com.example.retrofitdemo.AppClass.Companion.appInstance
import com.example.retrofitdemo.R


import com.example.retrofitdemo.utils.Utility.isNetworkConnected
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.regex.Pattern


fun <T> Call<T>.callApi(onSuccess: (body: T) -> Unit, onFailure: (t: Throwable?) -> Unit) {
    if (isNetworkConnected(appInstance!!)) {
        this.enqueue(object : Callback<T> {
            override fun onResponse(call: Call<T>?, response: Response<T>) {
                val body: T? = response.body()
                if (body != null) {
                    onSuccess(body)
                } else {
                    onFailure(
                        Throwable(
                            AppClass.getAppContext().getString(R.string.error_crash_error_message)
                        )
                    )
                }
            }

            override fun onFailure(call: Call<T>?, t: Throwable?) {
                onFailure(t)
            }

        })
    } else {
        onFailure(Throwable(AppClass.getAppContext().getString(R.string.error_common_network)))

    }
}

fun <T> Call<T>.callApiLogin(
    headerCode: (headerCode: Int) -> Unit,
    onSuccess: (body: T) -> Unit,
    onFailure: (t: Throwable?) -> Unit
) {

    if (isNetworkConnected(appInstance!!)) {
        this.enqueue(object : Callback<T> {
            override fun onResponse(call: Call<T>?, response: Response<T>) {
                val headerList = response.code()
                headerCode(headerList)

                val body: T? = response.body()
                if (body != null) {
                    onSuccess(body)
                } else {
                    var msg = AppClass.appInstance!!.getString(R.string.error_crash_error_message)
                    try {
                        val jObjError = JSONObject(response.errorBody()!!.string())
                        msg = jObjError.getJSONObject("error").getString("message")
                    } catch (e: Exception) {
                        log("" + e.printStackTrace())
                    }
                    onFailure(Throwable(msg))

                }
            }

            override fun onFailure(call: Call<T>?, t: Throwable?) {
                onFailure(t)
                Log.e("something", "" + t?.message.toString())
            }
        })
    } else {
        onFailure(Throwable(AppClass.appInstance!!.getString(R.string.internetmsg1)))
    }
}

fun View.onClick(onClick: () -> Unit) {
    setOnClickListener { onClick() }
}

fun String.showSuccessMsg() {

    Toast.makeText(AppClass.appInstance, this, Toast.LENGTH_LONG).show()
}

fun hideSoftInput(activity: Activity) {
    var view = activity.currentFocus
    if (view == null) view = View(activity)
    val imm = activity.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(view.windowToken, 0)
}

fun String.showFailedMsg() {
    Toast.makeText(AppClass.appInstance, this, Toast.LENGTH_LONG).show()
}

fun Int.isZero(): Boolean {
    if (this == 0) {
        return true
    }
    return false
}

private val EMAIL =
    "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$"

fun Context.toast(message: CharSequence) {
    Toast.makeText(this, message, Toast.LENGTH_LONG).show()
}

fun log(message: String) {
    Log.e("Content", " == " + message)
}

fun EditText.isAValidEmail(input: CharSequence?): Boolean {
    if (input == null)
        return false
    val pattern = Pattern.compile(EMAIL)
    val matcher = pattern.matcher(input)
    return matcher.matches()
}