package tw.com.james.kkstream.data.source

import tw.com.james.kkstream.data.*

interface StreamDataSource {

    suspend fun getToken(): Result<TokenResult>

    suspend fun getChartPlaylists(token: String): Result<ChartResult>

    suspend fun getFeaturedPlaylists(token: String): Result<ChartResult>

    suspend fun getNewestAlbumMixed(token: String): Result<AlbumResult>

    suspend fun getTracks(token: String, domain: PlaylistDomain): Result<PlaylistTracksResult>

}