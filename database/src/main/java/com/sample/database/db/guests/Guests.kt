package com.sample.database.db.guests

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.sample.data.models.GuestListItem

@Entity(tableName = "Guests")
data class Guests(
    @PrimaryKey(autoGenerate = true)
    val id: Long? = null,
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "haveReservation")
    val haveReservation: Boolean? = null,
    @ColumnInfo(name = "needReservation")
    val needReservation: Boolean? = null
) {
    fun toGuestListItem() = GuestListItem(
        id = this.id,
        name = this.name,
        isChecked = (this.haveReservation ?: this.needReservation) ?: false,
        haveReservation = this.haveReservation,
        needReservation = this.needReservation
    )
}