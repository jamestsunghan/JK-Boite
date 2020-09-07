package tw.com.james.kkstream

import android.annotation.SuppressLint
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo

object Util {

    var token: String? = null

    @SuppressLint("MissingPermission")
    fun isInternetConnected(): Boolean {
        val cm = StreamApp.instance
            .getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork: NetworkInfo? = cm.activeNetworkInfo
        return activeNetwork?.isConnectedOrConnecting == true
    }
}