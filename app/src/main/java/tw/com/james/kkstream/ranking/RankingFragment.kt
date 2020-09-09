package tw.com.james.kkstream.ranking

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController

import tw.com.james.kkstream.R
import tw.com.james.kkstream.data.PlaylistDomain
import tw.com.james.kkstream.databinding.FragmentRankingBinding
import tw.com.james.kkstream.ext.getVMFactory
import tw.com.james.kkstream.home.HomeFragmentDirections

class RankingFragment : Fragment() {

    private val viewModel: RankingViewModel by viewModels{ getVMFactory() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentRankingBinding = DataBindingUtil
            .inflate(inflater,R.layout.fragment_ranking, container, false)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        binding.recyclerChart.adapter = RankingAdapter(RankingAdapter.OnClickListener{chart->
            val domain = PlaylistDomain.CHART.apply {
                setPlayId(chart.id)
            }
            viewModel.watchTracks(domain)
        })

        viewModel.tracksDomain.observe(viewLifecycleOwner, Observer{
            it?.let{domain->
                findNavController()
                    .navigate(HomeFragmentDirections.actionGlobalAlbumFragment(domain))
            }
        })

        return binding.root
    }
}
