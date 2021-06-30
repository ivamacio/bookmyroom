package com.bookmyroom.ui.home

import com.bookmyroom.data.models.Room
import com.bookmyroom.data.repositories.room.RoomAPIRepository
import com.bookmyroom.data.repositories.room.RoomLocalRepository
import com.bookmyroom.data.responses.room.RoomBookingResponse
import com.bookmyroom.data.responses.room.RoomsResponse
import com.bookmyroom.mock.RoomLocalRepositoryMockData
import com.bookmyroom.ui.viewdata.RoomViewData
import com.core.network.ErrorCode
import com.core.network.ResponseResult
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.MockitoAnnotations

@ExperimentalCoroutinesApi
@RunWith(JUnit4::class)
class ExampleUnitTest {

    private lateinit var roomAPIRepository: RoomAPIRepository
    private lateinit var roomLocalRepository: RoomLocalRepository
    private lateinit var viewModel: HomeViewModel
    private val testDispatcher = TestCoroutineDispatcher()

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        MockitoAnnotations.initMocks(this)

        roomAPIRepository = mock()
        roomLocalRepository = mock()
    }

    @After
    fun tearDown() {
        // Resets state of the [Dispatchers.Main] to the original main dispatcher.
        // For example, in Android Main thread dispatcher will be set as [Dispatchers.Main].
        Dispatchers.resetMain()

        // Clean up the TestCoroutineDispatcher to make sure no other work is running.
        testDispatcher.cleanupTestCoroutines()
    }

    @Test
    fun testLoadingState() = runBlocking {
        viewModel = HomeViewModel(roomAPIRepository, roomLocalRepository)

        val result = viewModel.state.value

        Assert.assertTrue(result.isLoading)
        Assert.assertFalse(result.isEmpty)
        Assert.assertEquals(0, result.showRooms.size)

        Assert.assertFalse(viewModel.isRoomBooked.value)
        Assert.assertTrue(viewModel.errorMessage.value.isNullOrEmpty())
    }


    @Test
    fun testEmptyListState() = runBlocking {
        whenever(roomAPIRepository.getRooms()).thenReturn(getFetchEmptyRoomList())
        viewModel = HomeViewModel(roomAPIRepository, roomLocalRepository)

        val result = viewModel.state.value

        Assert.assertFalse(result.isLoading)
        Assert.assertTrue(result.isEmpty)
        Assert.assertEquals(0, result.showRooms.size)

        Assert.assertFalse(viewModel.isRoomBooked.value)
        Assert.assertTrue(viewModel.errorMessage.value.isNullOrEmpty())
    }

    @Test
    fun testRoomsListState() = runBlocking {
        whenever(roomLocalRepository.getAlphabetizedRooms()).thenReturn(getFetchAlphabetizedRooms())
        viewModel = HomeViewModel(roomAPIRepository, roomLocalRepository)

        val result = viewModel.state.value

        Assert.assertFalse(result.isLoading)
        Assert.assertFalse(result.isEmpty)
        Assert.assertEquals(1, result.showRooms.size)

        Assert.assertFalse(viewModel.isRoomBooked.value)
        Assert.assertTrue(viewModel.errorMessage.value.isNullOrEmpty())
    }

    @Test
    fun testErrorState() = runBlocking {
        whenever(roomAPIRepository.getRooms()).thenReturn(getErrorState())
        viewModel = HomeViewModel(roomAPIRepository, roomLocalRepository)
        val result = viewModel.errorMessage

        Assert.assertFalse(viewModel.state.value.isEmpty)
        Assert.assertTrue(viewModel.state.value.isLoading)
        Assert.assertEquals(0, viewModel.state.value.showRooms.size)

        Assert.assertFalse(viewModel.isRoomBooked.value)
        Assert.assertFalse(result.value.isNullOrEmpty())
    }

    @Test
    fun testErrorQuickBooking() = runBlocking {
        whenever(roomAPIRepository.bookRoom(RoomLocalRepositoryMockData.room)).thenReturn(getErrorState())
        viewModel = HomeViewModel(roomAPIRepository, roomLocalRepository)
        val result = viewModel.errorMessage

        val roomViewData = RoomViewData(
            RoomLocalRepositoryMockData.NAME,
            RoomLocalRepositoryMockData.SPOTS,
            RoomLocalRepositoryMockData.THUMBNAIL)
        viewModel.requestBookRoom(roomViewData)

        Assert.assertFalse(viewModel.isRoomBooked.value)
        Assert.assertFalse(result.value.isNullOrEmpty())
    }

    @Test
    fun testSuccessfulQuickBooking() = runBlocking {
        whenever(roomAPIRepository.bookRoom(RoomLocalRepositoryMockData.room)).thenReturn(getSuccessfulQuickBookingState())
        viewModel = HomeViewModel(roomAPIRepository, roomLocalRepository)
        val result = viewModel.errorMessage

        val roomViewData = RoomViewData(
            RoomLocalRepositoryMockData.NAME,
            RoomLocalRepositoryMockData.SPOTS,
            RoomLocalRepositoryMockData.THUMBNAIL)
        viewModel.requestBookRoom(roomViewData)

        Assert.assertTrue(viewModel.isRoomBooked.value)
        Assert.assertTrue(result.value.isNullOrEmpty())
    }

    private fun getFetchAlphabetizedRooms(): Flow<List<Room>> {
        val room = RoomLocalRepositoryMockData.room
        val data = arrayListOf<Room>()
        data.add(room)

        return flowOf(data)
    }

    private fun getErrorState(): Flow<ResponseResult.Error> {
        val errorCode = ErrorCode.SERVER_UNEXPECTED_ERROR
        val response = ResponseResult.Error(errorCode)
        return flowOf(response)
    }

    private fun getSuccessfulQuickBookingState(): Flow<ResponseResult.Success<RoomBookingResponse>> {
        val successfulBookingResponse = RoomLocalRepositoryMockData.successfulBookingResponse
        val response = ResponseResult.Success(successfulBookingResponse)
        return flowOf(response)
    }


    private fun getFetchEmptyRoomList(): Flow<ResponseResult.Success<RoomsResponse>> {
        val data = arrayListOf<Room>()

        val roomsResponse = RoomsResponse(data)
        val response = ResponseResult.Success(roomsResponse)
        return flowOf(response)
    }
}