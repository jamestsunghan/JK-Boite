package tw.com.james.kkstream.release.paging

import android.view.ViewGroup
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter

class ReleaseLoadStateAdapter(
    private val retry: () -> Unit
): LoadStateAdapter<ReleaseLoadStateViewHolder>() {

    override fun onBindViewHolder(holder: ReleaseLoadStateViewHolder, loadState: LoadState) {
        holder.bind(loadState)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        loadState: LoadState
    ): ReleaseLoadStateViewHolder {
        return ReleaseLoadStateViewHolder.create(parent, retry)
    }
}