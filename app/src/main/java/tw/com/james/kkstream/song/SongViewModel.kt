package tw.com.james.kkstream.song

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import tw.com.james.kkstream.data.LoadStatus
import tw.com.james.kkstream.data.source.StreamRepository

class SongViewModel(private val repo: StreamRepository, private val songUrl: String) : ViewModel() {

    private val _error = MutableLiveData<String>()
    val error: LiveData<String>
        get() = _error

    private val _status = MutableLiveData<LoadStatus>()
    val status: LiveData<LoadStatus>
        get() = _status


}
