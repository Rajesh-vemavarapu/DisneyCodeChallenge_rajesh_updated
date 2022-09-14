package com.sample.database.db.guests

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface GuestsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(guests: List<Guests>)

    @Query("SELECT * FROM Guests ORDER BY name ASC")
    fun getAll(): MutableList<Guests>
}
