package tw.com.james.kkstream.release.paging

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.recyclerview.widget.RecyclerView
import tw.com.james.kkstream.databinding.ItemReleaseLoadStateBinding

class ReleaseLoadStateViewHolder(
    private val binding: ItemReleaseLoadStateBinding,
    retry: () -> Unit
) : RecyclerView.ViewHolder(binding.root){


    init{
        binding.retryButton.setOnClickListener { retry.invoke() }
    }

    fun bind(state: LoadState){
        if(state is LoadState.Error){
            binding.errorMessage.text = state.error.localizedMessage
        }
        binding.progressRelease.isVisible = state is LoadState.Loading
        binding.retryButton.isVisible = state is LoadState.Error
        binding.errorMessage.isVisible = state is LoadState.Error
    }

    companion object {
        fun create(parent: ViewGroup, retry: () -> Unit): ReleaseLoadStateViewHolder{

            return ReleaseLoadStateViewHolder(
                ItemReleaseLoadStateBinding
                    .inflate(LayoutInflater.from(parent.context),parent,false),retry
            )
        }
    }
}