package com.devandfun.mytravel.domain.entities

import java.util.Date

data class Profile(
    val id: String,
    val name: String,
    var dateOfLastVisit: Date?
)