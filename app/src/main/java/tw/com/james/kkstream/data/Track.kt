package tw.com.james.kkstream.data

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Track (
    val id: String = "",
    val name: String = "",
    val duration: Int = 0,
    val isrc: String = "",
    val url: String = "",
    @Json(name = "track_number")val trackNumber: Int = 0,
    val explicitness: Boolean = false,
    @Json(name = "available_territories")val availableTerr: List<String> = listOf(),
    var album: Album? = null
): Parcelable
