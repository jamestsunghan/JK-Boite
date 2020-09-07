package tw.com.james.kkstream.data.source

import tw.com.james.kkstream.R
import tw.com.james.kkstream.StreamApp
import tw.com.james.kkstream.Util
import tw.com.james.kkstream.data.ChartResult
import tw.com.james.kkstream.data.Result
import tw.com.james.kkstream.data.TokenResult
import tw.com.james.kkstream.network.KKBOXOpenApi
import java.lang.Exception

object StreamRemoteDataSource: StreamDataSource {

    override suspend fun getToken(): Result<TokenResult> {
//        if(!Util.isInternetConnected()){
//            return Result.Fail(StreamApp.instance.getString(R.string.no_internet))
//        }

        return try{
            val result = KKBOXOpenApi.accountService.getToken()
            result.error?.let{
                return Result.Fail(it)
            }

            Result.Success(result)
        }catch(e: Exception){
            Result.Error(e)
        }
    }

    override suspend fun getChartPlaylists(token: String): Result<ChartResult> {
//        if(!Util.isInternetConnected()){
//            return Result.Fail(StreamApp.instance.getString(R.string.no_internet))
//        }

        return try{
            val result = KKBOXOpenApi.retrofitService.getChartPlaylists(token)
            result.error?.let{
                return Result.Fail(it)
            }

            Result.Success(result)
        }catch(e: Exception){
            Result.Error(e)
        }
    }
}