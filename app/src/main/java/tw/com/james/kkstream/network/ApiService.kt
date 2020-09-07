package tw.com.james.kkstream.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.*
import tw.com.james.kkstream.R
import tw.com.james.kkstream.StreamApp
import tw.com.james.kkstream.data.ChartResult
import tw.com.james.kkstream.data.ReleaseResult
import tw.com.james.kkstream.data.TokenResult

private const val BASE_URL = "https://api.kkbox.com/v1.1/"
private const val HOST_URL = "https://account.kkbox.com/"


private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val client = OkHttpClient.Builder()
    .addInterceptor(HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    })
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .client(client)
    .build()

private val retrofitHost = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(HOST_URL)
    .client(client)
    .build()

interface KKBOXOpenApiService{

    @GET("new-release-categories/{category}")
    suspend fun getIndieMusic(
        @Header("Authorization") token: String,
        @Path("category") category: String = "Ksfvcl5NAnG7pjDuca",
        @Query("territory")terr: String = "TW"
    ): ReleaseResult

    @GET("charts")
    suspend fun getChartPlaylists(
        @Header("Authorization") token: String,
        @Query("territory")terr: String = "TW",
        @Query("limit") limit: Int = 10
    ): ChartResult
}

interface KKBOXAccountApiService{

    @FormUrlEncoded
    @POST("oauth2/token")
    suspend fun getToken(
        @Field("grant_type")type: String = "client_credentials",
        @Field("client_id")id: String = StreamApp.instance.getString(R.string.kkbox_client_id),
        @Field("client_secret")secret: String = StreamApp.instance.getString(R.string.kkbox_client_secret)
    ): TokenResult
}

object KKBOXOpenApi{
    val retrofitService: KKBOXOpenApiService by lazy{retrofit.create(KKBOXOpenApiService::class.java)}
    val accountService: KKBOXAccountApiService by lazy{ retrofitHost.create(KKBOXAccountApiService::class.java)}
}