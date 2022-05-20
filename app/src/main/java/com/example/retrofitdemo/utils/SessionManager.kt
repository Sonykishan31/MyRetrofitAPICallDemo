package com.vendor.sterlingvendorapp.utils

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import com.google.gson.Gson

class SessionManager {
    companion object {
        val PREF_NAME: String = "LoginSession"
        val KEY_IS_LOGGEDIN: String = "isLoggedIn"
        val KEY_USER_OBJ: String = "AuthenticateUser"
        val KEY_USER_SETTINGS: String = "DefaultSettings"
        val KEY_USER_EMAIL: String = "UserEmail"
        val KEY_USER_PASSWORD: String = "Location"

        val KEY_IsVerify: String = "isVerify"
        val Key_Email: String = "isEmailLogin"
        val KEY_SelectedLanguage: String = ""
        val KEY_SelectedLanguageId: String = ""

        val Key_PushCounter: String = "pushCounter"//fireBase
        val TAG = SessionManager::class.java.simpleName
    }

    var pref: SharedPreferences
    var editor: SharedPreferences.Editor
    var _context: Context
    var PRIVATE_MODE = 0

    constructor(context: Context) {
        this._context = context
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE)
        editor = pref.edit()
    }

    fun create_login_session(
        AuthenticateUser: String,
        email: String,
        password: String,
        isVerify: Boolean,
        isEmail: Boolean,
        isUpdate: Boolean = false
    ) {
        if (false)
            clear_login_session()
        editor.putBoolean(KEY_IS_LOGGEDIN, true)

        editor.putString(Key_Email, email)
        editor.putString(KEY_USER_PASSWORD, password)
        editor.putBoolean(KEY_IsVerify, isVerify)
        editor.putBoolean(Key_Email, isEmail)
        editor.putString(KEY_USER_OBJ, AuthenticateUser)
        editor.commit()
        Log.d(TAG, "User login session modified!")


    }


    fun getPreferences(Key: String): String {
        return pref.getString(Key, "").toString()
    }

    fun isLoggedIn(): Boolean {

        /*if ((!getIsVerify()!!))
             clear_login_session()*/
        return pref.getBoolean(KEY_IS_LOGGEDIN, false)
    }

    fun isEmailLogin(): Boolean {

        return pref.getBoolean(Key_Email, false)
    }

//    fun get_Authenticate_User(): Response {
//
//        val gson = Gson()
//        val json = pref.getString(KEY_USER_OBJ, "")
//        return gson.fromJson(json, Response::class.java)
//    }

    var NotificationRead: Boolean
        get() = pref.getBoolean("NotificationRead", true)
        set(NotificationRead) {
            editor.putBoolean("NotificationRead", NotificationRead)
            editor.commit()
        }


    /*fun get_UserSettings(): UsersettingPojo.Datum {

        val gson = Gson()
        val json = pref.getString(KEY_USER_SETTINGS, "")
        return gson.fromJson<Any>(json, UsersettingPojo.Datum::class.java)
    }*/

    fun set_UserSettings(defaultSettings: String) {

        editor.putString(KEY_USER_SETTINGS, defaultSettings)
        editor.commit()
        Log.d(TAG, "User settings modified!")
    }

    fun get_Email(): String {
        return pref.getString(KEY_USER_EMAIL, "").toString()
    }

    fun get_Password(): String {
        return pref.getString(KEY_USER_PASSWORD, "").toString()
    }

    fun getIsVerify(): Boolean? {
        return pref.getBoolean(KEY_IsVerify, false)
    }

    fun setIsVerify(isVerfiy: Boolean?) {
        editor.putBoolean(KEY_IsVerify, isVerfiy!!)
        // commit changes
        editor.commit()
        Log.d(TAG, "User login session modified!")
    }

    fun getsetSelectedLanguage(): String? {
        return pref.getString(KEY_SelectedLanguageId, "")
    }

    fun setSelectedLanguage(languageID: String?) {
        editor.putString(KEY_SelectedLanguageId, languageID!!)
        // commit changes
        editor.commit()
        Log.d(TAG, "User login session modified!")
    }


    fun clear_login_session() {
        editor.putBoolean(KEY_IS_LOGGEDIN, false)
        editor.putString(KEY_USER_OBJ, "")

        editor.commit()
    }

}