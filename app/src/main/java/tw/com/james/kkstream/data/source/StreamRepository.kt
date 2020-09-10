package tw.com.james.kkstream.data.source

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import tw.com.james.kkstream.data.*
import tw.com.james.kkstream.release.Release

interface StreamRepository {

    suspend fun getToken(): Result<TokenResult>

    suspend fun getChartPlaylists(token: String): Result<ChartResult>

    suspend fun getFeaturedPlaylists(token: String): Result<ChartResult>

    suspend fun getIndieMusic(token: String): Result<AlbumResult>

    suspend fun getTracks(token: String, domain: PlaylistDomain): Result<PlaylistTracksResult>

    suspend fun getPagingChartPlaylists(token: String): Flow<PagingData<Release>>

}