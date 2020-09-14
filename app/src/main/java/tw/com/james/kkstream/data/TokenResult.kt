package tw.com.james.kkstream.data

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Parcelize
data class TokenResult (
    @Json(name = "access_token") val token: String = "",
    @Json(name = "token_type") val type: String = "",
    @Json(name = "expires_in") val expiresIn: Long = 0L,
    val error: String? = null
): Parcelable