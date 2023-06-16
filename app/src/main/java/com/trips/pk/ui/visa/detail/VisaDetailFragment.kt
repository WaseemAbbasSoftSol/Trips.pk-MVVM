package com.trips.pk.ui.visa.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.google.android.material.appbar.AppBarLayout
import com.trips.pk.R
import com.trips.pk.databinding.FragmentVisaDetailBinding
import com.trips.pk.model.visa.Visa
import com.trips.pk.ui.common.DummyClickListener
import com.trips.pk.ui.common.OnItemViewClickListener
import com.trips.pk.ui.common.tempVisaPlaceName
import org.koin.androidx.viewmodel.ext.android.viewModel

class VisaDetailFragment: Fragment() {
    private lateinit var binding:FragmentVisaDetailBinding
    private val mViewModel : VisaDetailViewModel by viewModel()
    private var countryId = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments.let {
            val args=VisaDetailFragmentArgs.fromBundle(it!!)
            if (args.countryId!=0){
                countryId = args.countryId
                mViewModel.getListOfVisaByCountryId(countryId)
            }
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentVisaDetailBinding.inflate(inflater,container,false)
        binding.lifecycleOwner=this

//        val adapter= VisaAdapter(requireContext(),this,2,9)
//        val layoutManager=LinearLayoutManager(requireContext())
//        binding.rvVisa.layoutManager=layoutManager
//        binding.rvVisa.adapter=adapter

        binding.appbar.addOnOffsetChangedListener(object : AppBarLayout.OnOffsetChangedListener {
            var isShow = false
            var scrollRange = -1
            override fun onOffsetChanged(appBarLayout: AppBarLayout, verticalOffset: Int) {
                if (scrollRange == -1) {
                    scrollRange = appBarLayout.totalScrollRange

                }
                if (scrollRange + verticalOffset == 0) {//collapsed
                    //   setMargins(binding.cl,0,0,0,0)
                    //  binding.cl.setBackgroundColor(ContextCompat.getColor(requireContext(),R.color.primary_color))
                    isShow = true
                    binding.tvToolbar.text = "$tempVisaPlaceName Visa from Pakistan"
                    binding.tvToolbar.visibility=View.VISIBLE
                    binding.tvTour.visibility=View.GONE
                } else if (isShow) {
                    //  setMargins(binding.cl,0,-50,0,0)
                    //  binding.cl.setBackgroundColor(ContextCompat.getColor(requireContext(),R.color.transparent))
                    binding.tvToolbar.visibility=View.GONE
                    binding.tvTour.visibility=View.VISIBLE

                }

            }
        })

        binding.clickListener = OnApplyVisaClick()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel=mViewModel
        mViewModel.visa.observe(viewLifecycleOwner){
            if (it.isNotEmpty()){
                var img = ""
                if (it.get(0).countries.flag.isNotEmpty() && it.get(0).countries.flag !="N/A") img=it.get(0).countries.flag
                if (img.isNotEmpty()) Glide.with(requireContext()).load(it[0].countries.flag).into(binding.guideDetailImage)
            }
        }
    }

    inner class OnApplyVisaClick : OnItemViewClickListener<Visa>{
        override fun onClick(view: View, item: Visa) {
            findNavController().navigate(R.id.action_visa_detail_to_visa_book_fragment)
        }
    }
}