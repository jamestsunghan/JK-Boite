package tw.com.james.kkstream.ranking

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import tw.com.james.kkstream.Util.token
import tw.com.james.kkstream.data.Chart
import tw.com.james.kkstream.data.LoadStatus
import tw.com.james.kkstream.data.PlaylistDomain
import tw.com.james.kkstream.data.source.StreamRepository

class RankingViewModel(private val repo: StreamRepository) : ViewModel() {

    private val _error = MutableLiveData<String>()
    val error: LiveData<String>
        get() = _error

    private val _status = MutableLiveData<LoadStatus>()
    val status: LiveData<LoadStatus>
        get() = _status

    private val _chartList = MutableLiveData<List<Chart>>()
    val chartList: LiveData<List<Chart>>
        get() = _chartList

    private val _tracksDomain = MutableLiveData<PlaylistDomain>()
    val tracksDomain: LiveData<PlaylistDomain>
        get() = _tracksDomain

    init{
        if(token.value == null){
            getToken()
        }else {
            getChart(token.value as String)
        }
    }

    internal fun getChart(token: String){
        viewModelScope.launch {

            _status.value = LoadStatus.LOADING

            val result = repo.getChartPlaylists(token).handleResultWith(_error, _status)
            result?.let{
                _chartList.value = result.data
            }
            Log.d("JJ","${result?.data}")

        }
    }

    internal fun getToken(){
        viewModelScope.launch {

            val result = repo.getToken().handleResultWith(_error, _status)

            result?.let{
                token.value = result.type + " " + result.token
                getChart(token.value as String)
            }
        }
    }

    fun watchTracks(domain: PlaylistDomain){
        _tracksDomain.value = domain
    }
}
