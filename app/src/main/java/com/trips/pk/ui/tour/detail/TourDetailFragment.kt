package com.trips.pk.ui.tour.detail

import android.app.Activity
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.provider.SyncStateContract.Constants
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.google.android.material.appbar.AppBarLayout
import com.trips.pk.databinding.FragmentTourDetailBinding
import com.trips.pk.model.tour.TourPackagePrices
import com.trips.pk.ui.common.ADMIN_BASE_URL
import com.trips.pk.ui.common.mTourPackagePrices
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class TourDetailFragment:Fragment() {
    private lateinit var binding:FragmentTourDetailBinding
    private val mViewModel : TourDetailViewModel by viewModel{
        parametersOf(tourId)
    }
    private var tourId=0
    private var placeName=""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments.let {
            val args=TourDetailFragmentArgs.fromBundle(it!!)
            tourId=args.tourId
            placeName=args.place
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentTourDetailBinding.inflate(inflater,container,false)
     //   requireActivity().makeStatusBarTransparent()
        binding.lifecycleOwner=this
        binding.tvToolbar.text = "$placeName Tour Package"
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
                    binding.tvToolbar.visibility=View.VISIBLE
                } else if (isShow) {
                  //  setMargins(binding.cl,0,-50,0,0)
                  //  binding.cl.setBackgroundColor(ContextCompat.getColor(requireContext(),R.color.transparent))
                    binding.tvToolbar.visibility=View.GONE
                }

            }
        })

        binding.btnBook.setOnClickListener {
        //    findNavController().navigate(R.id.action_tour_detail_to_book_list)

            findNavController().navigate(TourDetailFragmentDirections.actionTourDetailToBookList())
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = mViewModel
        mViewModel.tour.observe(viewLifecycleOwner){
            if (it != null){
                binding.tvFromCity.text = "${it.noOfDays} days trip"
                mTourPackagePrices.clear()
                mTourPackagePrices.addAll(it.priceDetails)
                binding.wvTourDetail.loadDataWithBaseURL(
                    null,
                    it.essentialDetail,
                    "text/html; charset=utf-8",
                    "UTF-8",
                    null
                )
                binding.tvDiscount.text= mViewModel.getTotalPrices(it.priceDetails)
             binding.tvTotalPrice.text=   mViewModel.getDiscountedPrice(it.priceDetails)

                var img = it.tourGalleries?.get(0)!!.imageURL
                val link ="$ADMIN_BASE_URL$img"
                if (img.isNotEmpty()) Glide.with(requireContext()).load(link).into(binding.guideDetailImage)
            }
        }
    }
    fun Activity.makeStatusBarTransparent() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.apply {
                clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
                addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
                decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                statusBarColor = Color.TRANSPARENT
                setSoftInputMode(
                    WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE
                            or WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN
                )
            }
        }
    }

    private fun setMargins(view: View, left: Int, top: Int, right: Int, bottom: Int) {
        if (view.layoutParams is ViewGroup.MarginLayoutParams) {
            val p = view.layoutParams as ViewGroup.MarginLayoutParams
            p.setMargins(left, top, right, bottom)
            view.requestLayout()
        }
    }
}