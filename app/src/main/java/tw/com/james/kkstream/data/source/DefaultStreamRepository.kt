package tw.com.james.kkstream.data.source

import tw.com.james.kkstream.data.*

class DefaultStreamRepository(private val remote: StreamDataSource): StreamRepository{

    override suspend fun getToken(): Result<TokenResult> {
        return remote.getToken()
    }

    override suspend fun getChartPlaylists(token: String): Result<ChartResult> {
        return remote.getChartPlaylists(token)
    }

    override suspend fun getFeaturedPlaylists(token: String): Result<ChartResult> {
        return remote.getFeaturedPlaylists(token)
    }

    override suspend fun getIndieMusic(token: String): Result<AlbumResult> {
        return remote.getIndieMusic(token)
    }

    override suspend fun getTracks(
        token: String,
        domain: PlaylistDomain
    ): Result<PlaylistTracksResult> {
        return remote.getTracks(token, domain)
    }
}