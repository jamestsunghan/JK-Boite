package tw.com.james.kkstream.data.source

import tw.com.james.kkstream.data.ChartResult
import tw.com.james.kkstream.data.Result
import tw.com.james.kkstream.data.TokenResult

class DefaultStreamRepository(private val remote: StreamDataSource): StreamRepository{

    override suspend fun getToken(): Result<TokenResult> {
        return remote.getToken()
    }

    override suspend fun getChartPlaylists(token: String): Result<ChartResult> {
        return remote.getChartPlaylists(token)
    }
}