package tw.com.james.kkstream.data

data class ReleaseResult (
    val id: String,
    val title: String,
    val album: List<Album>
)