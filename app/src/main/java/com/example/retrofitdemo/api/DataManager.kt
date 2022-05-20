package com.vendor.sterlingvendorapp.api

import com.vendor.sterlingvendorapp.utils.prefrence.PreferenceKeys
import com.vendor.sterlingvendorapp.utils.prefrence.SharedPreferenceManager

/**
 * Class contains all the necessary methods to interact with any data set.
 * there are mainly 3 data sources @[ApiManager] , @[SharedPreferenceManager] or @[SQLManager]
 * This class is responsible for deciding data source for required data
 * All the data operations must go through this class.
 */
object DataManager {

    /**
     * Calls @[SharedPreferenceManager] to get access token from @[android.content.SharedPreferences]
     * @return current access token
     */
    /**
     * Calls @[SharedPreferenceManager] to save access token to @[android.content.SharedPreferences]
     * @param accessToken access token to save
     */
//    var accessToken: String
//        get() = SharedPreferenceManager.getString(PreferenceKeys.ACCESS_TOKEN)
//        set(accessToken) {
//            SharedPreferenceManager.setString(PreferenceKeys.ACCESS_TOKEN, accessToken)
//        }

    /**
     * Calls @[ApiManager] to initiate Login request
     *
     * @param map        map containing email,password as parameters
     * @param onResponse @[OnResponse] listener to call on response received
     */
//    fun logInUser(map: HashMap<String, String>, onResponse: OnResponse<LoginResponse>) {
//        ApiManager.executeRequest(onResponse, ApiManager.apiRepository.logInUser(map))
//    }
//
//    fun registerUser(map: HashMap<String, String>, onResponse: OnResponse<LoginResponse>) {
//        ApiManager.executeRequest(onResponse, ApiManager.apiRepository.registerUser(map))
//    }
//
    fun getAccessToken(): String? {
        return SharedPreferenceManager.getString(PreferenceKeys.ACCESS_TOKEN)
    }

    fun setAccessToken(accessToken: String) {
        SharedPreferenceManager.setString(PreferenceKeys.ACCESS_TOKEN, accessToken)
    }

    fun getIntroScreen(): String? {
        return SharedPreferenceManager.getString(PreferenceKeys.IntroScreen)
    }

    fun setIntroScreen(accessToken: String) {
        SharedPreferenceManager.setString(PreferenceKeys.IntroScreen, accessToken)
    }
//
//    fun resetPassword(map: HashMap<String, String>, onResponse: OnResponse<CommonResponse>) {
//        ApiManager.executeRequest(onResponse, ApiManager.apiRepository.resetPassword(map))
//    }
//
//    fun changePassword(map: HashMap<String, String>, onResponse: OnResponse<CommonResponse>) {
//        ApiManager.executeRequest(onResponse, ApiManager.apiRepository.changePassword(map))
//    }
//
//    fun editTimeSheetDays(map: HashMap<String, String>, onResponse: OnResponse<DaysInfoData>) {
//        ApiManager.executeRequest(onResponse, ApiManager.apiRepository.editTimeSheetDays(map))
//    }
//
//    fun addTimeSheet(map: HashMap<String, String>, onResponse: OnResponse<AddTimeSheetModel>) {
//        ApiManager.executeRequest(onResponse, ApiManager.apiRepository.addTimeSheet(map))
//    }
//
//    fun getParameter(map: HashMap<String, String>, onResponse: OnResponse<ParameterModel>) {
//        ApiManager.executeRequest(onResponse, ApiManager.apiRepository.getParameter(map))
//    }
//
//    fun signOff(fileMap: HashMap<String, File>, map: HashMap<String, String>, onResponse: OnResponse<CommonResponse>) {
//        ApiManager.executeRequest(
//            onResponse,
//            ApiManager.apiRepository.signOff(ApiManager.prepareFilePart(fileMap), map)
//        )
//    }
//
//    fun signOffLater(map: HashMap<String, String>, onResponse: OnResponse<CommonResponse>) {
//        ApiManager.executeRequest(
//            onResponse,
//            ApiManager.apiRepository.signOffLater(map)
//        )
//    }
//
//    fun logout(onResponse: OnResponse<CommonResponse>) {
//        ApiManager.executeRequest(onResponse, ApiManager.apiRepository.logout())
//    }
//
//    fun timeSheetList(onResponse: OnResponse<TimeSheetListModel>) {
//        ApiManager.executeRequest(onResponse, ApiManager.apiRepository.timeSheetList())
//    }
//
//    fun setUserProfile(profile: LoginResponse.DataBean.UserBean, accessToken: String) {
//        SharedPreferenceManager.setString(
//            PreferenceKeys.USER_PROFILE,
//            Utility.getStringFromObject(profile)
//        )
//        if (profile != null)
//            setAccessToken(accessToken)
//
//    }
//
//    fun getUserData(): LoginResponse.DataBean.UserBean {
//        return Gson().fromJson(
//            SharedPreferenceManager.getString(PreferenceKeys.USER_PROFILE),
//            LoginResponse.DataBean.UserBean::class.java
//        )
//    }

}