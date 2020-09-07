package tw.com.james.kkstream.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Paging (
    val offset: Int,
    val limit: Int,
    val previous: Int?,
    val next: Int?
): Parcelable
