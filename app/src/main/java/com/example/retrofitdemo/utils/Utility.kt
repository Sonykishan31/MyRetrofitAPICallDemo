package com.vendor.sterlingvendorapp.utils


import android.app.Activity
import android.app.DatePickerDialog
import android.content.ContentValues.TAG
import android.content.Context
import android.content.DialogInterface
import android.graphics.Color
import android.net.ConnectivityManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.retrofitdemo.BuildConfig
import com.example.retrofitdemo.R
import com.example.retrofitdemo.utils.widgets.MyProgressDialog
import com.google.gson.Gson

import java.io.File
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit
import java.util.regex.Pattern


object Utility {


    fun log(msg: String) {
        if (BuildConfig.DEBUG) {
            Log.d(TAG, msg)
        }
    }

    fun getStringFromObject(`object`: Any): String {

        val gson = Gson()
        return gson.toJson(`object`)
    }

    internal var pdlg: MyProgressDialog? = null

    fun isNetworkConnected(context: Context): Boolean {
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork = cm.activeNetworkInfo
        return activeNetwork != null && activeNetwork.isConnectedOrConnecting
    }

    fun isUpper(s: String): Boolean {
        for (c in s.toCharArray()) {
            if (!Character.isUpperCase(c)) return false
        }
        return true
    }

    fun showLoading(c: Context) {
        if (pdlg == null) {
            pdlg = MyProgressDialog(c)
            pdlg!!.setCancelable(false)

            try {
                pdlg!!.show()
            } catch (e: Exception) {
                e.printStackTrace()
            }

        }
    }

//    fun reduceFileSize(activity: Activity, picturePath: String): File? {
//
//        var compressedImageFile: File? = null
//
//        var actualFile = File(picturePath)
//
//        val size = actualFile.length() / (1024 * 1024)
//
//        if (size > 1) {
//
//            compressedImageFile = Compressor(activity)
//                .setMaxWidth(480)
//                .setMaxHeight(640)
//                .setQuality(75).compressToFile(File(picturePath))
////            log("size in Kb : ${compressedImageFile.length() / 1000}")
//        } else {
//            return actualFile
//        }
//
//        return compressedImageFile
//
//    }

    fun closeLoading() {
        if (pdlg != null && pdlg!!.isShowing) {
            pdlg!!.dismiss()
            pdlg = null
        }
    }

    fun showErrorToast(ct: Context, message: String) {
//        showToast(ct, message, true)
    }


    fun showSuccessToast(ct: Context, message: String) {
//        showToast(ct, message, false)
    }

    fun parseDate(toDateFormat: String, dateString: String): String {
        return parseDate(null, toDateFormat, dateString)
    }

    fun parseDateReverse(toDateFormat: String, dateString: String): String {
        return parseDateReverse(null, toDateFormat, dateString)
    }

    fun parseDateWithFormat(dateFormat: String, toDateFormat: String, dateString: String): String {
        return parseFinalDate(dateFormat, toDateFormat, dateString)
    }

    fun getTimeInMinutes(estimatedTime: String): String {
        val pickUpTime = Utility
            .parseDateWithFormat(
                "yyyy-MM-dd HH:mm:ss", "hh:mm a",
                estimatedTime
            )
        return pickUpTime
    }

    fun getTimeTotalInMinute(estimatedTime: Long): String {
        val seconds: Long = estimatedTime * 1000

//        val day = TimeUnit.SECONDS.toDays(seconds) as Long
//        val hours = TimeUnit.SECONDS.toHours(seconds) - day * 24
//        val minute = TimeUnit.SECONDS.toMinutes(seconds) - TimeUnit.SECONDS.toHours(seconds) * 60

        val minute = TimeUnit.MILLISECONDS.toMinutes(seconds)
        var dayStr = ""

//        if (day > 0) {
//            dayStr = "$day day"
//        } else {
//            dayStr = ""
//        }
//
//        var hourStr = ""
//
//        if (hours > 0) {
//            hourStr = "$hours hour"
//        } else {
//            hourStr = ""
//        }

        var minStr = ""

        if (minute > 0) {
            minStr = "$minute min"
        } else {
            minStr = ""
        }

        val time = "$minStr"
        return time
    }

    fun parseDate(dateFormat: String?, toDateFormat: String, dateString: String): String {
        val formatter: SimpleDateFormat
        try {
            if (dateFormat == null) {
                formatter = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)
            } else {
                formatter = SimpleDateFormat(dateFormat, Locale.ENGLISH)
            }
            val date = formatter.parse(dateString)
            return SimpleDateFormat(toDateFormat).format(date)
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return ""
    }

    fun parseDateReverse(dateFormat: String?, toDateFormat: String, dateString: String): String {
        val formatter: SimpleDateFormat
        try {
            if (dateFormat == null) {
                formatter = SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH)
            } else {
                formatter = SimpleDateFormat(dateFormat, Locale.ENGLISH)
            }
            val date = formatter.parse(dateString)
            return SimpleDateFormat(toDateFormat).format(date)
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return ""
    }

    fun parseFinalDate(dateFormat: String?, toDateFormat: String, dateString: String): String {
        val formatter: SimpleDateFormat
        try {
            if (dateFormat == null) {
                formatter = SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH)
            } else {
                formatter = SimpleDateFormat(dateFormat, Locale.ENGLISH)
            }
            val date = formatter.parse(dateString)
            return SimpleDateFormat(toDateFormat).format(date)
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return ""
    }

    fun getFormatedDateTime(strWriteFormat: String, dateStr: String): String {

        var formattedDate = dateStr

        val readFormat = SimpleDateFormat("HH:mm:ss", Locale.getDefault())
        val writeFormat = SimpleDateFormat(strWriteFormat, Locale.getDefault())

        var date: Date? = null

        try {
            date = readFormat.parse(dateStr)
        } catch (e: ParseException) {
            e.printStackTrace()
        }

        if (date != null) {
            formattedDate = writeFormat.format(date)
        }

        return formattedDate
    }

//    private fun showToast(ct: Context, message: String, isError: Boolean) {
//        try {
//            val inflater = LayoutInflater.from(ct)
//            val layout = inflater.inflate(
//                R.layout.layout_toast_message,
//                null
//            )
//            val textV = layout.findViewById(R.id.lbl_toast) as TextView
//            if (isError) {
//                textV.setBackgroundResource(R.drawable.toast_error_background)
//                textV.setTextColor(Color.WHITE)
//            }
//            textV.text = message
//            val toast = Toast(ct)
//            toast.duration = Toast.LENGTH_LONG
//            toast.view = layout
//            toast.show()
//        } catch (e: Exception) {
//            e.printStackTrace()
//        }
//
//    }

    private val EMAIL =
        "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$"


    fun isAValidEmail(input: CharSequence?): Boolean {
        if (input == null)
            return false
        val pattern = Pattern.compile(EMAIL)
        val matcher = pattern.matcher(input)
        return matcher.matches()
    }

    /**
     * Convert String into Object using @[Gson]
     *
     * @param mListStr to convert into string
     * @return object from string with gson
     */


    fun <T> getObjectFromString(stringJSON: String, classType: Class<T>): T {
        return Gson().fromJson(stringJSON, classType)
        /*  Object obj = null;
        Type type = new TypeToken<Object>() {
        }.getType();
        obj = new Gson().fromJson(mListStr, type);
        return obj;*/
    }

    fun hideSoftInput(activity: Activity) {
        var view = activity.currentFocus
        if (view == null) view = View(activity)
        val imm = activity.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }

//    fun clearSingleton() {
//
//        val singleton = Singleton.getInstance()
//        com.vendor.sterlingvendorapp.api.log("Before clear : ${singleton.getTestListDetails().size}")
//        val test = singleton.getTestListDetails()
//        val packages = singleton.getPackageDetails()
//        test.clear()
//        packages.clear()
//        singleton.prescritionFile = null
//        singleton.imagesList.clear()
//        singleton.setTestListDetails(test)
//        singleton.setPackageList(packages)
//        com.vendor.sterlingvendorapp.api.log("after clear : ${singleton.getTestListDetails().size}")
//    }

    fun openDatePicker(context: Context, editText: EditText) {
        var cal = Calendar.getInstance()
        val currentYear = 0
        val currentMonth = 0
        val currentDay = 0
        val dateSetListener =
            DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
                cal.set(Calendar.YEAR, year)
                cal.set(Calendar.MONTH, monthOfYear)
                cal.set(Calendar.DAY_OF_MONTH, dayOfMonth)

                val myFormat = "dd-MMMM-yyyy"
                val sdf = SimpleDateFormat(myFormat, Locale.ENGLISH)
                var textDate = sdf.format(cal.time)
                editText.setText(textDate)

            }


        var datePicker = DatePickerDialog(
            context, dateSetListener,
            cal.get(Calendar.YEAR),
            cal.get(Calendar.MONTH),

            cal.get(Calendar.DAY_OF_MONTH)
        )
        datePicker.datePicker.minDate = System.currentTimeMillis() - 1000
        datePicker.show()

    }

//    fun showErrorMessage(activity: Activity, layout: View) {
//
//        if (!isNetworkConnected(activity)) {
//            MyUtils.showSnackbar(
//                activity,
//                activity.getString(R.string.error_common_network),
//                layout
//            )
//        } else {
//            MyUtils.showSnackbar(
//                activity,
//                activity.getString(R.string.error_crash_error_message),
//                layout
//            )
//        }
//
//    }

    fun perfomStartActivityAnimation(activity: Activity) {
        activity.overridePendingTransition(
            R.anim.slide_in_right,
            R.anim.slide_out_left
        )
    }


    fun getConfirmationDialog(
        context: Context,
        title: String,
        msg: String,
        btnPositiveTitle: String,
        btnNegativeTitle: String,
        onPositiveBtnClick: DialogInterface.OnClickListener,
        onNegativeBtnClick: DialogInterface.OnClickListener
    ): AlertDialog.Builder {

        return getStyledConfirmationDialog(
            context,
            0,
            title,
            msg,
            btnPositiveTitle,
            btnNegativeTitle,
            onPositiveBtnClick,
            onNegativeBtnClick
        )
    }

    fun getStyledConfirmationDialog(
        context: Context,
        styleId: Int,
        title: String,
        msg: String,
        btnPositiveTitle: String,
        btnNegativeTitle: String,
        onPositiveBtnClick: DialogInterface.OnClickListener?,
        onNegativeBtnClick: DialogInterface.OnClickListener?
    ): AlertDialog.Builder {

        val builder: AlertDialog.Builder
        if (styleId == 0) {
            builder = AlertDialog.Builder(context)
        } else {
            builder = AlertDialog.Builder(context, styleId)
        }
        builder.setCancelable(false)
        builder.setTitle(title)
        if (!(msg.isEmpty())) {
            builder.setMessage(msg)
        }
        if (!btnNegativeTitle.equals("", ignoreCase = true)) {
            builder.setNegativeButton(btnNegativeTitle) { dialog, which ->
                if (onNegativeBtnClick != null) {
                    onNegativeBtnClick.onClick(dialog, 0)
                } else {
                    dialog.dismiss()
                }
            }
        }
        if (!btnPositiveTitle.equals("", ignoreCase = true)) {
            builder.setPositiveButton(btnPositiveTitle) { dialog, which ->
                if (onPositiveBtnClick != null) {
                    onPositiveBtnClick.onClick(dialog, 0)
                } else {
                    dialog.dismiss()
                }
            }
        }
        return builder
    }

}