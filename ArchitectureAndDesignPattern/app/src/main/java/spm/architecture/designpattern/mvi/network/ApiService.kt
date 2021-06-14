package spm.architecture.designpattern.mvi.network

import retrofit2.http.GET
import spm.architecture.designpattern.mvi.model.User

/**
 * Created by Sibaprasad Mohanty on 14/6/21.
 * Spm Limited
 * sp.dobest@gmail.com
 */

interface ApiService {
                        @GET("users")    
                         suspend fun getUsers(): List<User>
                     }