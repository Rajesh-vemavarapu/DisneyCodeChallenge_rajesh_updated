package com.sample.database.di

import android.content.Context
import com.sample.database.db.DisneyDatabase
import com.sample.database.db.guests.GuestsDao
import com.sample.database.repository.RoomDBRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
object DatabaseModule {
    @Provides
    fun provideGuestsDao(@ApplicationContext context: Context): GuestsDao {
        return DisneyDatabase.getInstance(context).guestsDao
    }

    @Provides
    fun provideRoomDBRepository(
        guestsDao: GuestsDao
    ) = RoomDBRepository(
        guestsDao = guestsDao,
    )
}