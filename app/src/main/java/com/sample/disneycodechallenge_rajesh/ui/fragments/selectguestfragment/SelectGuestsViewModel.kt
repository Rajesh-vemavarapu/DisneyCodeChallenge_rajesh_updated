package com.sample.disneycodechallenge_rajesh.ui.fragments.selectguestfragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.sample.data.models.GuestListItem
import com.sample.database.db.guests.Guests
import com.sample.database.repository.RoomDBRepository
import com.sample.disneycodechallenge_rajesh.ui.fragments.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SelectGuestsViewModel @Inject constructor(
    databaseRepository: RoomDBRepository
) : BaseViewModel(databaseRepository) {

    private val _guests = MutableLiveData<List<GuestListItem>>()
    val guests: LiveData<List<GuestListItem>> = _guests

    init {
        initGuests(
            listOf(
                Guests(name = "Dale Gibson", haveReservation = false),
                Guests(name = "Jeremy Gibson", haveReservation = false),
                Guests(name = "Linda Gibson", needReservation = false),
                Guests(name = "Margaret Gibson", needReservation = false),
            )
        )
    }

    fun getAllGuests() = viewModelScope.launch {
        _guests.value =
            databaseRepository.getAllGuests().map { it.toGuestListItem() }.toMutableList()
    }

    private fun initGuests(guests: List<Guests>) = viewModelScope.launch {
        if (databaseRepository.getAllGuests().isEmpty()) {
            databaseRepository.insertAllGuests(guests)
            getAllGuests()
        }
    }
}
