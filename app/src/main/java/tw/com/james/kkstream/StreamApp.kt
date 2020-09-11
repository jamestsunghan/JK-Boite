package tw.com.james.kkstream

import android.app.Application
import tw.com.james.kkstream.data.source.DefaultStreamRepository
import tw.com.james.kkstream.data.source.StreamRemoteDataSource
import kotlin.properties.Delegates

class StreamApp: Application() {

    lateinit var repo: DefaultStreamRepository

    companion object{
        var instance: StreamApp by Delegates.notNull()
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        repo = DefaultStreamRepository(StreamRemoteDataSource)
    }


}
