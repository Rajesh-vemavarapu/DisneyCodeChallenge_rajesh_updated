package com.sample.disneycodechallenge_rajesh.ui.fragments

import androidx.lifecycle.ViewModel
import com.sample.database.repository.RoomDBRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
open class BaseViewModel @Inject constructor(
    val databaseRepository: RoomDBRepository
) : ViewModel()