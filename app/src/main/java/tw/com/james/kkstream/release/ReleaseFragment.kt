package tw.com.james.kkstream.release

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.map
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

import tw.com.james.kkstream.R
import tw.com.james.kkstream.Util.token
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

//        binding.recyclerRelease.adapter = ReleaseAdapter(ReleaseAdapter.OnClickListener{featured->
//            viewModel.watchTracks(PlaylistDomain.FEATURED.apply {
//                id = featured.id
//                cover = featured.images.last().url
//            })
//        }, viewModel)

        val releaseAdapter = ReleasePagingAdapter(ReleasePagingAdapter.OnClickListener{ featured->
            viewModel.watchTracks(PlaylistDomain.FEATURED.apply {
                id = featured.id
                cover = featured.images.last().url
            })
        }, viewModel)


        binding.recyclerRelease.adapter = releaseAdapter



        lifecycleScope.launch {
            viewModel.releaseFlow.collectLatest{pagingData->
                Log.d("JJ", "paging data $pagingData")

                releaseAdapter.submitData(pagingData)
            }
        }

        viewModel.releaseList.observe(viewLifecycleOwner, Observer{
            it?.let{list->
                Log.d("JJ","release list ${list.size}")
            }
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
