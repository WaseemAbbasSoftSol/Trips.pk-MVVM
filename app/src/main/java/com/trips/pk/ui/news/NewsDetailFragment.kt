package com.trips.pk.ui.news

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.google.android.material.appbar.AppBarLayout
import com.trips.pk.databinding.FragmentNewsDetailBinding
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class NewsDetailFragment: Fragment() {
    private lateinit var binding:FragmentNewsDetailBinding
    private val mViewModel:NewsDetailViewModel by viewModel{
        parametersOf(heading)
    }
    private var heading = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments.let {
            val args = NewsDetailFragmentArgs.fromBundle(it!!)
            heading=args.heading
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=FragmentNewsDetailBinding.inflate(inflater,container,false)
        binding.lifecycleOwner=this

        binding.appbar.addOnOffsetChangedListener(object : AppBarLayout.OnOffsetChangedListener {
            var isShow = false
            var scrollRange = -1
            override fun onOffsetChanged(appBarLayout: AppBarLayout, verticalOffset: Int) {
                if (scrollRange == -1) {
                    scrollRange = appBarLayout.totalScrollRange

                }
                if (scrollRange + verticalOffset == 0) {//collapsed

                    isShow = true
                   // binding.tvToolbar.visibility=View.VISIBLE
                    binding.tvToolbar.visibility=View.GONE
                } else if (isShow) {
                     binding.tvToolbar.visibility=View.GONE
                }

            }
        })

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel=mViewModel
        mViewModel.news.observe(viewLifecycleOwner){
            if (it!=null){
                Glide.with(requireContext()).load(it.bannerLink).into(binding.imDetail)
            }
        }
    }
}