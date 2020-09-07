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

    init{
        if(token == null){
            getToken()
        }else {
            getChart(token as String)
        }
    }

    fun getChart(token: String){
        viewModelScope.launch {
            val result = repo.getChartPlaylists(token).handleResultWith(_error, _status)
            result?.let{
                _chartList.value = result.data
            }
            Log.d("JJ","${result?.data}")

        }
    }

    fun getToken(){
        viewModelScope.launch {

            val result = repo.getToken().handleResultWith(_error, _status)

            result?.let{
                token = result.type + " " + result.token
                getChart(token as String)
            }
        }
    }
}
