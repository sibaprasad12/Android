package spm.architecture.designpattern.mvi.state

import spm.architecture.designpattern.mvi.model.User

/**
 * Created by Sibaprasad Mohanty on 14/6/21.
 * Spm Limited
 * sp.dobest@gmail.com
 */

sealed class MainState {
    object Idle : MainState()     
    object Loading : MainState()     
    data class Users(val user: List<User>) : MainState()
    data class Error(val error: String?) : MainState()  
}