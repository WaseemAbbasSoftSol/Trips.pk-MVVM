package com.trips.pk.ui.common

import android.graphics.Paint
import android.graphics.drawable.Drawable
import android.widget.*
import androidx.cardview.widget.CardView
import androidx.databinding.BindingAdapter
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mikelau.views.shimmer.ShimmerRecyclerViewX
import com.trips.pk.R
import com.trips.pk.utils.Helpers


@BindingAdapter("navigation")
fun navigateUp(view: CardView, isEnabled: Boolean) {
    view.setOnClickListener {
        it.findNavController().navigateUp()
    }
}

@BindingAdapter("drawLineOverTextView")
fun drawLine(view: TextView, isFlag:Boolean) {
   if (isFlag)view.paintFlags= view.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
}
//@BindingAdapter("imageTint")
//fun setImage(view: ImageView, colorResource: Int) {
//    view.setColorFilter(
//        ContextCompat.getColor
//            (view.context, colorResource), android.graphics.PorterDuff.Mode.SRC_IN
//    )
//}
//
//@BindingAdapter("imageTint")
//fun setImage(view: ImageView, color: String) {
//    view.setColorFilter(
//        Color.parseColor(color), android.graphics.PorterDuff.Mode.SRC_IN
//    )
//}
//
//@BindingAdapter("imageRes")
//fun setImageRes(view: ImageView, imageRes: Int) {
//    view.setImageResource(imageRes)
//}
//
//@BindingAdapter(value = ["imageUrl", "default"], requireAll = false)
//fun loadImage(view: ImageView, imageUrl: String?, default: Drawable?) {
//    if (default == null) {
//        Glide.with(view.context).load(imageUrl).into(view)
//    } else {
//        Glide.with(view.context).load(imageUrl).placeholder(default).into(view)
//    }
//}



@BindingAdapter(value = ["imageUrl", "default"], requireAll = false)
fun loadImage(view: ImageView, imageUrl: String?, default: Drawable?) {
    if (default == null) {
        if (imageUrl!!.isNotEmpty()) Glide.with(view.context).load(imageUrl).into(view)
        else Glide.with(view.context).load(R.drawable.image_placeholder).into(view)
    } else {
       if (imageUrl!!.isNotEmpty()) Glide.with(view.context).load(imageUrl).placeholder(default).into(view)
        else Glide.with(view.context).load(R.drawable.image_placeholder).placeholder(default).into(view)
    }
}

//@BindingAdapter(value = ["imageUrl", "default", "borderColor"], requireAll = false)
//fun loadImage(
//    view: CircleImageView,
//    imageUrl: String?,
//    default: Drawable?,
//    borderColor: Int = Color.WHITE
//) {
//    if (default == null) {
//        Glide.with(view.context).load(imageUrl).into(view)
//    } else {
//        Glide.with(view.context).load(imageUrl).placeholder(default).into(view)
//    }
//    view.borderColor = borderColor
//}
//
//@BindingAdapter("drawableLeft")
//fun setDrawableLeft(view: TextView, drawableLeft: Int) {
//    val img = view.context.resources.getDrawable(drawableLeft)
//    view.setCompoundDrawablesWithIntrinsicBounds(img, null, null, null)
//}
//
//@BindingAdapter("html")
//fun setHtml(view: TextView, html: String) {
//    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
//        view.text = Html.fromHtml(html, Html.FROM_HTML_MODE_COMPACT)
//    } else {
//        view.text = Html.fromHtml(html)
//    }
//}
//
//@BindingAdapter("error")
//fun setError(view: EditText, error: String?) {
//    if (!error.isNullOrEmpty())
//        view.error = error
//}
//
//@BindingAdapter(value = ["nextEditText", "previousEditText"], requireAll = false)
//fun gotoNextEditText(
//    view: EditText,
//    nextView: EditText? = null,
//    previousEditText: EditText? = null
//) {
//    view.addTextChangedListener(object : TextWatcher {
//        override fun afterTextChanged(s: Editable?) {
//
//        }
//
//        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
//
//        }
//
//        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
//            if (s.isNullOrEmpty()) {
//                if (previousEditText == null)
//                    return
//                view.clearFocus()
//                previousEditText.requestFocus()
//                return
//            }
//            if (s.length >= 2) {
//                view.setText(s.toString().substring(s.length - 1, s.length))
//                view.setSelection(view.text.length)
//            }
//            if (nextView == null) return
//            view.clearFocus()
//            nextView.requestFocus()
//        }
//
//    })
//}

@BindingAdapter(
    value = ["itemsList", "itemLayout", "itemClickListener", "hasFixSize", "onItemViewClick","hasMargin"],
    requireAll = false
)
fun <T> setItems(
    view: RecyclerView, itemsList: List<T>, layout: Int,
    itemClickListener: OnListItemClickListener<T>?, hasFixSize: Boolean = false,
    onItemViewClick: OnItemViewClickListener<T>?, hasMargin:Boolean
) {
    val mAdapter = GenericRecyclerViewAdapter(itemsList, layout, hasMargin)
    view.adapter = mAdapter
    mAdapter.setItemClickListener(itemClickListener)
    mAdapter.onItemViewClick = onItemViewClick
    view.setHasFixedSize(hasFixSize)
}
@BindingAdapter(
    value = ["itemsList", "itemLayout", "itemClickListener", "hasFixSize", "onItemViewClick","hasMargin"],
    requireAll = false
)
fun <T> setItems(
    view: ShimmerRecyclerViewX, itemsList: List<T>, layout: Int,
    itemClickListener: OnListItemClickListener<T>?, hasFixSize: Boolean = false,
    onItemViewClick: OnItemViewClickListener<T>?, hasMargin: Boolean
) {
    if (itemsList.isNotEmpty()){
        val mAdapter = GenericRecyclerViewAdapter(itemsList, layout,hasMargin)
        view.adapter = mAdapter
        mAdapter.setItemClickListener(itemClickListener)
        mAdapter.onItemViewClick = onItemViewClick
        view.setHasFixedSize(hasFixSize)
       if (hasMargin) Helpers.setMargins(view,0,0,0,0)
    }
   else {
       view.showShimmerAdapter()
        if (hasMargin)Helpers.setMargins(view,30,0,0,0)
    }
}
@BindingAdapter(value = ["itemsList", "isSpinner"], requireAll = false)
fun <T> setSpinnerItems(view: AutoCompleteTextView, items: List<T>, isSpinner: Boolean = false) {
    val adapter = ArrayAdapter<T>(
        view.context,
        android.R.layout.simple_spinner_dropdown_item, items
    )
    view.setAdapter(adapter)
    if (isSpinner) {
        view.setOnClickListener { view.showDropDown() } //show drop down like spinner
    } else if (view is MultiAutoCompleteTextView) {
        view.setTokenizer(MultiAutoCompleteTextView.CommaTokenizer())
    }
}
//
//@BindingAdapter(value = ["spItemsList", "isSmall"], requireAll = false)
//fun <T> setSpinnerItems(view: Spinner, spItemsList: List<T>, isSmall: Boolean = false) {
//    val headerLayout: Int
//    val spinnerItem: Int
//    if (isSmall) {
//        headerLayout = R.layout.item_spinner_header_small
//        spinnerItem = R.layout.item_spinner_small
//    } else {
//        headerLayout = R.layout.item_spinner_header
//        spinnerItem = R.layout.item_spinner
//    }
//    val adapter = ArrayAdapter(
//        view.context,
//        headerLayout, R.id.text1, spItemsList
//    )
//    adapter.setDropDownViewResource(spinnerItem)
//    view.adapter = adapter
//}
//
//@BindingAdapter(value = ["spItemsList", "isSmall"], requireAll = false)
//fun <T> setSpinnerItems(view: MaterialSpinner, spItemsList: List<T>, isSmall: Boolean = false) {
//    val headerLayout: Int
//    val spinnerItem: Int
//    if (isSmall) {
//        headerLayout = R.layout.item_spinner_header_small
//        spinnerItem = R.layout.item_spinner_small
//    } else {
//        headerLayout = R.layout.item_spinner_header
//        spinnerItem = R.layout.item_spinner
//    }
//    val adapter = ArrayAdapter(
//        view.context,
//        headerLayout, R.id.text1, spItemsList
//    )
//    adapter.setDropDownViewResource(spinnerItem)
//    view.adapter = adapter
//}
//
//@BindingAdapter("currentItem")
//fun setCurrentItem(view: Spinner, currentItem: Int) {
//    if (currentItem == -1) return
//    view.setSelection(currentItem)
//}
//
//@BindingAdapter("selectedPos")
//fun setSelectedItem(view: Spinner, selectedPos: Int) {
//    view.setSelection(selectedPos)
//}
//

//
//@BindingAdapter("isSelected")
//fun setSelect(view: View, isSelected: Boolean) {
//    if (view.isSelected != isSelected)
//        view.isSelected = isSelected
//}
//
//@InverseBindingAdapter(attribute = "isSelected")
//fun getSelect(view: View): Boolean {
//    return view.isSelected
//}
//
//@SuppressLint("ClickableViewAccessibility")
//@BindingAdapter("app:isSelectedAttrChanged")
//fun setListeners(
//    view: View,
//    attrChange: InverseBindingListener
//) {
//    view.setOnTouchListener { view, motionEvent ->
//        when(motionEvent.action) {
//            MotionEvent.ACTION_UP -> {
//                view.callOnClick()
//                attrChange.onChange()
//            }
//        }
//        return@setOnTouchListener true
//    }
//}
//
//@BindingAdapter(value = ["isLoading", "newText"], requireAll = false)
//fun setLoading(view: Button, isLoading: Boolean, newText: String?) {
//    view.attachTextChangeAnimator()
//    if (isLoading) {
//        view.isEnabled = false
//        view.showProgress {
//            buttonText = "Please Wait..."
//            progressColor = view.currentTextColor
//        }
//    } else {
//        view.isEnabled = true
//        view.hideProgress(newText)
//    }
//}
//
//@BindingAdapter("isRefreshing")
//fun setRefreshing(view: SwipeRefreshLayout, isRefreshing: Boolean) {
//    view.isRefreshing = isRefreshing
//}
//
//@BindingAdapter("url")
//fun loadUrl(view: WebView, url: String? = null){
//    if (url.isNullOrEmpty()) return
//    view.loadUrl(url)
//    view.settings.javaScriptEnabled = true
//    view.webViewClient = WebViewClient()
//}
//
//@BindingAdapter(
//    value = ["listDataHeader", "listChildData", "headerLayout", "childLayout", "onChildClickListener", "onChildItemViewClick"],
//    requireAll = false
//)
//fun <T, U> setListItems(
//    view: ExpandableListView,
//    listDataHeader: List<T>,
//    listChildData: HashMap<T, List<U>>,
//    headerLayout: Int,
//    childLayout: Int,
//    onChildClickListener: ExpandableListView.OnChildClickListener? = null,
//    onChildItemViewClick: OnItemViewClickListener<U>? = null
//) {
//    val adapter =
//        GenericExpandableListAdapter(headerLayout, childLayout, listDataHeader, listChildData)
//    view.setAdapter(adapter)
//    adapter.onChildItemViewClick = onChildItemViewClick
//    view.setOnChildClickListener(onChildClickListener)
//}
