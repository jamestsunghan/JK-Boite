package tw.com.james.kkstream.release

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

import tw.com.james.kkstream.R
import tw.com.james.kkstream.Util.token
import tw.com.james.kkstream.data.LoadStatus
import tw.com.james.kkstream.data.PlaylistDomain
import tw.com.james.kkstream.databinding.FragmentReleaseBinding
import tw.com.james.kkstream.ext.getVMFactory
import tw.com.james.kkstream.home.HomeFragmentDirections
import tw.com.james.kkstream.release.paging.ReleasePagingAdapter

class ReleaseFragment : Fragment() {

    private val viewModel: ReleaseViewModel by viewModels { getVMFactory() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding: FragmentReleaseBinding = DataBindingUtil
            .inflate(inflater, R.layout.fragment_release, container, false)

        binding.lifecycleOwner = this

        binding.viewModel = viewModel

        val releaseAdapter = ReleasePagingAdapter(ReleasePagingAdapter.OnClickListener { featured ->
            viewModel.watchTracks(PlaylistDomain.FEATURED.apply {
                id = featured.id
                cover = featured.images.last().url
            })
        }, viewModel)

        releaseAdapter.addLoadStateListener { loadState ->
            binding.progressRelease.isVisible =
                loadState.refresh is LoadState.Loading || viewModel.status.value == LoadStatus.LOADING
            binding.errorMessage.isVisible =
                loadState.refresh is LoadState.Error || viewModel.error.value != null

        }

        viewModel.error.observe(viewLifecycleOwner, Observer {
            it?.let { error ->
                binding.errorMessage.isVisible = true
                Log.d("JJ", "error $error")
            }
        })

        binding.recyclerRelease.adapter = releaseAdapter

        token.observe(viewLifecycleOwner, Observer {
            it?.let {
                lifecycleScope.launch {
                    viewModel.releaseFlow.collectLatest { pagingData ->
                        Log.d("JJ", "paging data $pagingData")

                        releaseAdapter.submitData(pagingData)
                    }
                }
            }
        })

        viewModel.tracksDomain.observe(viewLifecycleOwner, Observer {
            it?.let { domain ->
                findNavController()
                    .navigate(HomeFragmentDirections.actionGlobalAlbumFragment(domain))
                viewModel.navigationComplete()
            }
        })

        return binding.root
    }
}
