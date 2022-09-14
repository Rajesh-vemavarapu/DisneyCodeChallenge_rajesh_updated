package com.sample.data.models

data class GuestListItem(
    val id: Long? = null,
    val name: String,
    var isChecked: Boolean,
    var haveReservation: Boolean? = null,
    var needReservation: Boolean? = null
)
