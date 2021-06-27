package spm.androidworld.all.hilt.data.api

import spm.androidworld.all.hilt.data.model.User
import retrofit2.Response

interface ApiHelper {
    suspend fun getUsers(): Response<List<User>>
}