package spm.architecture.designpattern.mvi.model


/**
 * Created by Sibaprasad Mohanty on 14/6/21.
 * Spm Limited
 * sp.dobest@gmail.com
 */

import com.squareup.moshi.Json

data class User(
    @Json(name = "id") val id: Int = 0,
    @Json(name = "name") val name: String = "",
    @Json(name = "email") val email: String = "",
    @Json(name = "avatar") val avatar: String = ""
)