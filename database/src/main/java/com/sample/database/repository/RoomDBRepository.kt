package com.sample.database.repository

import com.sample.database.db.guests.Guests
import com.sample.database.db.guests.GuestsDao
import javax.inject.Inject

class RoomDBRepository @Inject constructor(
    private val guestsDao: GuestsDao
) {
    fun getAllGuests() = guestsDao.getAll()
    suspend fun insertAllGuests(guests: List<Guests>) = guestsDao.insertAll(guests)
}
