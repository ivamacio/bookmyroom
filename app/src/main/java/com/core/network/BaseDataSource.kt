package com.core.network

import com.google.gson.JsonParseException
import retrofit2.Response
import java.io.IOException

abstract class BaseDataSource(private val connectionManager: ConnectionManager) {

    protected suspend fun <T> getResult(call: suspend () -> Response<T>): ResponseResult<T> {
        try {
            if (!connectionManager.isNetworkAvailable()) {
                return ResponseResult.Error(ErrorCode.NO_INTERNET)
            }
            val response = call()
            if (response.isSuccessful) {
                val body = response.body()
                if (body != null) return ResponseResult.Success(body)
            }
            return ResponseResult.Error(ErrorCode.SERVER_UNEXPECTED_ERROR)
        } catch (e: IllegalArgumentException) {
            e.printStackTrace()
            return ResponseResult.Error(ErrorCode.APP_UNEXPECTED_ERROR)
        } catch (e: IOException) {
            e.printStackTrace()
            return ResponseResult.Error(ErrorCode.NO_INTERNET)
        } catch (e: JsonParseException) {
            e.printStackTrace()
            return ResponseResult.Error(ErrorCode.JSON_PARSE_EXCEPTION)
        } catch (e: Exception) {
            // out of safety catch anything for unexpected error
            e.printStackTrace()
            return ResponseResult.Error(ErrorCode.SERVER_UNEXPECTED_ERROR)
        }
    }
}