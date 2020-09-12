package tw.com.james.kkstream.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import tw.com.james.kkstream.data.source.StreamRepository
import tw.com.james.kkstream.ranking.RankingViewModel
import tw.com.james.kkstream.release.ReleaseViewModel

@Suppress("UNCHECKED_CAST")
class ViewModelFactory(val repo: StreamRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
        with(modelClass) {
            when {
                isAssignableFrom(RankingViewModel::class.java) ->
                    RankingViewModel(repo)
                isAssignableFrom(ReleaseViewModel::class.java) ->
                    ReleaseViewModel(repo)
                else -> throw IllegalArgumentException("Unknown ViewModel Class ${modelClass.name}")
            }
        } as T

}