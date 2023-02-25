package com.example.employeedirectory.model

import com.squareup.moshi.Json

data class Employee (
    @field:Json(name = "uuid") val uuId: String,
    @field:Json(name = "full_name") val name: String,
    @field:Json(name = "phone_number") val phoneNumber: String,
    @field:Json(name = "email_address") val emailAddress: String,
    @field:Json(name = "biography") val biography: String,
    @field:Json(name = "photo_url_small") val photoUrlSmall: String,
    @field:Json(name = "photo_url_large") val photoUrlLarge: String,
    @field:Json(name = "team") val teamName: String,
    @field:Json(name = "employee_type") val employeeType: String
)