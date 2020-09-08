package tw.com.james.kkstream.data.source

import tw.com.james.kkstream.data.ChartResult
import tw.com.james.kkstream.data.ReleaseResult
import tw.com.james.kkstream.data.Result
import tw.com.james.kkstream.data.TokenResult

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

    override suspend fun getIndieMusic(token: String): Result<ReleaseResult> {
        return remote.getIndieMusic(token)
    }
}