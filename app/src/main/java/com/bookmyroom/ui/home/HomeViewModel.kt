package com.bookmyroom.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bookmyroom.data.models.Room
import com.bookmyroom.data.repositories.room.RoomAPIRepository
import com.bookmyroom.data.repositories.room.RoomLocalRepository
import com.bookmyroom.ui.viewdata.RoomViewData
import com.bookmyroom.ui.viewdata.mappers.RoomMapper
import com.bookmyroom.ui.viewdata.mappers.RoomViewDataMapper
import com.core.network.ErrorCode
import com.core.network.ResponseResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@ExperimentalCoroutinesApi
@HiltViewModel
class HomeViewModel @Inject constructor(private val roomAPIRepository: RoomAPIRepository,
                                        private val roomLocalRepository: RoomLocalRepository) : ViewModel() {

    private val _state = MutableStateFlow(HomeViewState())
    val state: StateFlow<HomeViewState>
        get() = _state

    private val _isRoomBooked = MutableStateFlow(false)
    val isRoomBooked: StateFlow<Boolean>
        get() = _isRoomBooked

    private val _searchName = MutableStateFlow("")
    val searchName: StateFlow<String>
        get() = _searchName

    private val _errorMessage: MutableStateFlow<String?> = MutableStateFlow("")
    val errorMessage: StateFlow<String?>
        get() = _errorMessage

    init {
        readRooms()
        fetchRooms()
    }

    fun readRooms(searchByName: String = "") {
        _searchName.value = searchByName
        viewModelScope.launch {
            roomLocalRepository.getAlphabetizedRooms(searchByName)?.collectLatest { result ->
                result?.let { result ->
                    val roomViewData = arrayListOf<RoomViewData>()
                    result.forEach {
                        it?.let { room ->
                            roomViewData.add(RoomViewDataMapper().map(room))
                        }
                    }
                    showRooms(roomViewData)
                }
            }
        }
    }

    private fun fetchRooms() {
        viewModelScope.launch {
            roomAPIRepository.getRooms().collectLatest { result ->
                when (result) {
                    is ResponseResult.Error -> {
                        showError(result.errorCode)
                    }
                    is ResponseResult.Success -> {
                        if (result.data.rooms.isNullOrEmpty()) {
                            showEmptyState()
                        } else {
                            insertRoomsLocally(result.data.rooms)
                        }
                    }
                }
            }
        }
    }

    fun requestBookRoom(searchByName: String, roomViewData: RoomViewData) {
        _searchName.value = searchByName
        viewModelScope.launch {
            val room = RoomMapper().map(roomViewData)
            roomAPIRepository.bookRoom(room).collect { result ->
                when (result) {
                    is ResponseResult.Error -> {
                        showError(result.errorCode)
                    }
                    is ResponseResult.Success -> {
                        showSuccessfullyBooked()
                        updateRoomLocally(room)
                    }
                }
            }
        }
    }

    private suspend fun insertRoomsLocally(rooms: List<Room>) {
        rooms.forEach {
            roomLocalRepository.insertRoom(it)
        }
    }

    private suspend fun updateRoomLocally(room: Room) {
        val updatedRoom = Room(name = room.name,
                                spots = (room.spots - 1),
                                thumbnail = room.thumbnail)
        roomLocalRepository.updateRoom(updatedRoom)
    }

    private fun showEmptyState() {
        _state.value = HomeViewState(isLoading = false, isEmpty = true)
    }

    private fun showRooms(rooms: List<RoomViewData>) {
        _state.value = HomeViewState(isLoading = false, isEmpty = false, showRooms = rooms)
    }

    private fun showError(errorCode: ErrorCode) {
        val error = errorCode.name
        val timestamp = System.currentTimeMillis()/1000
        _errorMessage.value = "$error $timestamp"
    }

    private fun showSuccessfullyBooked() {
        _isRoomBooked.value = true
    }

    fun hideDialog() {
        _isRoomBooked.value = false
    }

    data class HomeViewState(
        val showRooms: List<RoomViewData> = emptyList(),
        val isEmpty: Boolean = false,
        val isLoading: Boolean = true,
    )
}