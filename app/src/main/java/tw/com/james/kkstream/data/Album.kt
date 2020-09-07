package tw.com.james.kkstream.data

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Album (
    val id: String,
    val name: String,
    val url: String,
    val explicitness: Boolean,
    @Json(name = "available_territories") val terrList : List<String>,
    @Json(name = "release_date") val releaseDate : String,
    val images: List<ImageVariant>,
    val artist: Artist
): Parcelable
