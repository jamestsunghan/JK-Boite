package tw.com.james.kkstream.data.source

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import tw.com.james.kkstream.data.*
import tw.com.james.kkstream.network.KKBOXOpenApi
import tw.com.james.kkstream.release.Release
import tw.com.james.kkstream.release.paging.ReleasedPagingSource

class DefaultStreamRepository(
    private val remote: StreamDataSource,
    private val KKApi: KKBOXOpenApi
) : StreamRepository {

    override suspend fun getToken(): Result<TokenResult> {
        return remote.getToken()
    }

    override suspend fun getChartPlaylists(token: String): Result<ChartResult> {
        return remote.getChartPlaylists(token)
    }

    override suspend fun getFeaturedPlaylists(token: String): Result<ChartResult> {
        return remote.getFeaturedPlaylists(token)
    }

    override suspend fun getNewestAlbumMixed(token: String): Result<AlbumResult> {
        return remote.getNewestAlbumMixed(token)
    }

    override suspend fun getTracks(
        token: String,
        domain: PlaylistDomain
    ): Result<PlaylistTracksResult> {
        return remote.getTracks(token, domain)
    }

    override fun getPagingChartPlaylists(token: String): Flow<PagingData<Release>> {
        return Pager(
            config = PagingConfig(pageSize = 10)
        ) {
            ReleasedPagingSource(KKApi, token)
        }.flow
    }
}