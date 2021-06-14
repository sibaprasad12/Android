package spm.architecture.designpattern.mvi.repository

import spm.architecture.designpattern.mvi.network.ApiHelper

/**
 * Created by Sibaprasad Mohanty on 14/6/21.
 * Spm Limited
 * sp.dobest@gmail.com
 */

class MainRepository(private val apiHelper: ApiHelper)
 {      
     suspend fun getUsers() = apiHelper.getUsers()  
  }