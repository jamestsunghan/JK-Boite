package tw.com.james.kkstream.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class Owner (
    val id: String,
    val url: String,
    val name: String,
    val description: String,
    val images: List<ImageVariant>
): Parcelable