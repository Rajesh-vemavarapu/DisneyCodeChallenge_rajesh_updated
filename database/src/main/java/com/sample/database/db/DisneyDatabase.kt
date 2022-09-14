package com.sample.database.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.sample.database.db.guests.Guests
import com.sample.database.db.guests.GuestsDao

@Database(
    entities = [
        Guests::class
    ],
    version = 2,
    exportSchema = false
)
abstract class DisneyDatabase : RoomDatabase() {

    abstract val guestsDao: GuestsDao

    companion object {

        @Volatile
        private var instance: DisneyDatabase? = null

        fun getInstance(context: Context): DisneyDatabase {
            synchronized(this) {
                var mInstance = instance

                if (mInstance == null) {
                    mInstance = Room.databaseBuilder(
                        context.applicationContext,
                        DisneyDatabase::class.java,
                        "disney_database"
                    ).fallbackToDestructiveMigration()
                        .allowMainThreadQueries()
                        .build()
                    instance = mInstance
                }
                return mInstance
            }
        }
    }
}
