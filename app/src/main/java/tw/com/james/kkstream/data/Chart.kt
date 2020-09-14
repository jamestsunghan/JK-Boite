package tw.com.james.kkstream.data

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Chart(
    val id: String,
    val title: String,
    val description: String,
    val url: String,
    val images: List<ImageVariant>,
    @Json(name = "updated_at") val updatedAt: String,
    val owner: Owner
): Parcelable