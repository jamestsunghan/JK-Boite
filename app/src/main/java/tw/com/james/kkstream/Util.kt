package tw.com.james.kkstream

import android.annotation.SuppressLint
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import androidx.lifecycle.MutableLiveData

object Util {

    val token = MutableLiveData<String>()

    fun getString(id: Int): String{
        return StreamApp.instance.getString(id)
    }

    @SuppressLint("MissingPermission")
    fun isInternetConnected(): Boolean {
        val cm = StreamApp.instance
            .getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val nw = cm.activeNetwork ?: return false
            val actNw = cm.getNetworkCapabilities(nw) ?: return false
            return when {
                actNw.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                actNw.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                else -> false
            }
        } else {
            val nwInfo = cm.activeNetworkInfo ?: return true
            return nwInfo.isConnectedOrConnecting
        }

    }
}