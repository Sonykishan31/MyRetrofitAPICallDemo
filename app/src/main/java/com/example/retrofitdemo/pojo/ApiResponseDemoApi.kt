package com.example.retrofitdemo.pojo

data class ApiResponseDemoApi(
    val `data`: Data?,
    val support: Support?
)

data class Data(
    val color: String?,
    val id: Int?,
    val name: String?,
    val pantone_value: String?,
    val year: Int?
)

data class Support(
    val text: String?,
    val url: String?
)