package tw.com.james.kkstream.release

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import tw.com.james.kkstream.data.LoadStatus
import tw.com.james.kkstream.data.source.StreamRepository

class ReleaseViewModel(private val repo: StreamRepository) : ViewModel() {

    private val _error = MutableLiveData<String>()
    val error: LiveData<String>
        get() = _error

    private val _status = MutableLiveData<LoadStatus>()
    val status: LiveData<LoadStatus>
        get() = _status



}
