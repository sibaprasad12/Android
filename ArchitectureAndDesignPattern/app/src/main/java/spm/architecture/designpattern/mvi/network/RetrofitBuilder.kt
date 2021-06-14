package spm.architecture.designpattern.mvi.network

import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

/**
 * Created by Sibaprasad Mohanty on 14/6/21.
 * Spm Limited
 * sp.dobest@gmail.com
 */

object RetrofitBuilder 
{      
   private const val BASE_URL = "https://test.mockapi.io/"      
   private fun getRetrofit() = Retrofit.Builder()
                               .baseUrl(BASE_URL)   
               .addConverterFactory(MoshiConverterFactory.create())
               .build()       
val apiService: ApiService =
                       getRetrofit().create(ApiService::class.java)  
}