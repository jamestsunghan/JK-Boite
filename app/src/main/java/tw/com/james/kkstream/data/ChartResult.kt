package tw.com.james.kkstream.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ChartResult (
    val data: List<Chart>,
    val paging: Paging,
    val summary: Summary,
    val error: String? = null
): Parcelable
