package spm.androidworld.all.hilt.data.api

import spm.androidworld.all.hilt.data.model.User
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET("users")
    suspend fun getUsers(): Response<List<User>>
}