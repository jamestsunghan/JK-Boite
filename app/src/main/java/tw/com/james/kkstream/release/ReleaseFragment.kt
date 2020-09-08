package tw.com.james.kkstream.release

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer

import tw.com.james.kkstream.R
import tw.com.james.kkstream.databinding.FragmentReleaseBinding
import tw.com.james.kkstream.ext.getVMFactory

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

        binding.recyclerRelease.adapter = ReleaseAdapter()

        viewModel.releaseList.observe(viewLifecycleOwner, Observer{
            it?.let{list->
                Log.d("JJ","release list ${list.size}")
            }
        })

        return binding.root
    }
}
