package spm.architecture.designpattern.mvi.intents

/**
 * Created by Sibaprasad Mohanty on 14/6/21.
 * Spm Limited
 * sp.dobest@gmail.com
 */

sealed class MainIntent {
    object FetchUser : MainIntent()
}