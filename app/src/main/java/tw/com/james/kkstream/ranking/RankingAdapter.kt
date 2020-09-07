package tw.com.james.kkstream.ranking

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import tw.com.james.kkstream.data.Chart
import tw.com.james.kkstream.databinding.ItemRankingBinding

class RankingAdapter: ListAdapter<Chart, RankingAdapter.RankingViewHolder>(DiffCallback) {
    class RankingViewHolder(private val binding: ItemRankingBinding)
        : RecyclerView.ViewHolder(binding.root){
        fun bind(){

        }
    }
    companion object DiffCallback: DiffUtil.ItemCallback<Chart>(){
        override fun areItemsTheSame(oldItem: Chart, newItem: Chart): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Chart, newItem: Chart): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RankingViewHolder {
        return RankingViewHolder(
            ItemRankingBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: RankingViewHolder, position: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}