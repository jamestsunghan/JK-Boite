package tw.com.james.kkstream.data

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Track (
    val id: String,
    val name: String,
    val duration: Int,
    val isrc: String,
    val url: String,
    @Json(name = "track_number")val trackNumber: Int,
    val explicitness: Boolean,
    @Json(name = "available_territories")val availableTerr: List<String>,
    val album: Album
): Parcelable
