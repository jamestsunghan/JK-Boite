package tw.com.james.kkstream.data.source

import tw.com.james.kkstream.data.ChartResult
import tw.com.james.kkstream.data.Result
import tw.com.james.kkstream.data.TokenResult

interface StreamDataSource {

    suspend fun getToken(): Result<TokenResult>

    suspend fun getChartPlaylists(token: String): Result<ChartResult>
}