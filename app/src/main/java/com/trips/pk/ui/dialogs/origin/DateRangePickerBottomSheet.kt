package com.trips.pk.ui.dialogs.origin

import android.graphics.Color
import android.graphics.drawable.Drawable
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.graphics.BlendModeColorFilterCompat
import androidx.core.graphics.BlendModeCompat
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.snackbar.Snackbar
import com.kizitonwose.calendar.core.CalendarDay
import com.kizitonwose.calendar.core.CalendarMonth
import com.kizitonwose.calendar.core.DayPosition
import com.kizitonwose.calendar.core.daysOfWeek
import com.kizitonwose.calendar.view.MonthDayBinder
import com.kizitonwose.calendar.view.MonthHeaderFooterBinder
import com.kizitonwose.calendar.view.ViewContainer
import com.trips.pk.R
import com.trips.pk.databinding.DateRangePickerBottomsheetBinding
import com.trips.pk.databinding.Example4CalendarDayBinding
import com.trips.pk.databinding.Example4CalendarHeaderBinding
import com.trips.pk.utils.CustomDateRangeHelper
import com.trips.pk.utils.CustomDateRangeHelper.dateRangeDisplayText
import com.trips.pk.utils.CustomDateRangeHelper.displayText
import com.trips.pk.utils.CustomDateRangeHelper.getSelection
import com.trips.pk.utils.CustomDateRangeHelper.isInDateBetweenSelection
import com.trips.pk.utils.CustomDateRangeHelper.isOutDateBetweenSelection
import com.trips.pk.utils.CustomDateRangeHelper.makeInVisible
import com.trips.pk.utils.CustomDateRangeHelper.makeVisible
import com.trips.pk.utils.CustomDateRangeHelper.setTextColorRes
import com.trips.pk.utils.Helpers
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.YearMonth
import java.time.format.DateTimeFormatter
import java.util.*
import java.util.concurrent.TimeUnit

class DateRangePickerBottomSheet:BottomSheetDialogFragment() {
    private lateinit var binding: DateRangePickerBottomsheetBinding

    @RequiresApi(Build.VERSION_CODES.O)
    private val today = LocalDate.now()

    @RequiresApi(Build.VERSION_CODES.O)
    private var mCurrentMonth = YearMonth.now()

    @RequiresApi(Build.VERSION_CODES.O)
    private var selection = CustomDateRangeHelper.DateSelection()

    @RequiresApi(Build.VERSION_CODES.O)
    private val headerDateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")

    private var listener: OnDateRangeSelected?=null

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DateRangePickerBottomsheetBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        Helpers.makeBottomSheetRounded(binding.root, dialog!!)

        Log.d("todayss", today.toString())
    /*    binding.exFourHeaderText.text=getMonthNameManually(mCurrentMonth.toString())
        binding.arrowRight.setOnClickListener {
            mCurrentMonth = mCurrentMonth.nextMonth
            binding.exFourCalendar.smoothScrollToMonth(mCurrentMonth)
            binding.exFourHeaderText.text=getMonthNameManually(mCurrentMonth.toString())
        }
        binding.arrowLeft.setOnClickListener {

            mCurrentMonth = mCurrentMonth.previousMonth
            binding.exFourCalendar.smoothScrollToMonth(mCurrentMonth)
            binding.exFourHeaderText.text=getMonthNameManually(mCurrentMonth.toString())
        }*/

        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //  addStatusBarColorUpdate(R.color.white)
        setHasOptionsMenu(true)
        //  binding = Example4FragmentBinding.bind(view)
        // Set the First day of week depending on Locale
        val daysOfWeek = daysOfWeek()
        getCurrentTim()
//        binding.legendLayout.root.children.forEachIndexed { index, child ->
//            (child as TextView).apply {
//                text = daysOfWeek[index].displayText()
//                setTextSize(TypedValue.COMPLEX_UNIT_SP, 15f)
//                setTextColorRes(R.color.example_4_grey)
//            }
//        }

        binding.legendLayout.first.legendText1.text = daysOfWeek[0].displayText()
        binding.legendLayout.sec.legendText1.text = daysOfWeek[1].displayText()
        binding.legendLayout.third.legendText1.text = daysOfWeek[2].displayText()
        binding.legendLayout.fourth.legendText1.text = daysOfWeek[3].displayText()
        binding.legendLayout.fifth.legendText1.text = daysOfWeek[4].displayText()
        binding.legendLayout.six.legendText1.text = daysOfWeek[5].displayText()
        binding.legendLayout.sev.legendText1.text = daysOfWeek[6].displayText()

        configureBinders()
        val currentMonth = YearMonth.now()
        binding.exFourCalendar.setup(
            currentMonth,
            currentMonth.plusMonths(12),
            daysOfWeek.first(),
        )
        binding.exFourCalendar.scrollToMonth(currentMonth)

        binding.exFourSaveButton.setOnClickListener click@{
            val (startDate, endDate) = selection
            if (startDate != null && endDate != null) {
                val text = dateRangeDisplayText(startDate, endDate)
                Snackbar.make(requireView(), text, Snackbar.LENGTH_LONG).show()
            }
            dismiss()
            //  parentFragmentManager.popBackStack()
        }

        bindSummaryViews()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun bindSummaryViews() {
        binding.exFourStartDateText.apply {
            if (selection.startDate != null) {
                setText(headerDateFormatter.format(selection.startDate))
                setTextColorRes(R.color.black)
                listener!!.selectedRange(start = headerDateFormatter.format(selection.startDate))
            } else {
               setText(getString(R.string.start_date))
                setTextColor(Color.GRAY)
            }
        }

        binding.exFourEndDateText.apply {
            if (selection.endDate != null) {
                setText(headerDateFormatter.format(selection.endDate))
                setTextColorRes(R.color.black)
                listener!!.selectedRange(end = headerDateFormatter.format(selection.endDate))
            } else {
               setText(getString(R.string.end_date))
                setTextColor(Color.GRAY)
            }
        }

        binding.exFourSaveButton.isEnabled = selection.daysBetween != null
    }

    override fun onStart() {
        super.onStart()
        val closeIndicator = ContextCompat.getDrawable(
            requireContext(),
            com.google.android.material.R.drawable.ic_m3_chip_close
        )?.apply {
            colorFilter = BlendModeColorFilterCompat.createBlendModeColorFilterCompat(
                ContextCompat.getColor(requireContext(), R.color.grey_002),
                BlendModeCompat.SRC_ATOP,
            )
        }
        (activity as AppCompatActivity).supportActionBar?.setHomeAsUpIndicator(closeIndicator)
    }

    private fun configureBinders() {
        val clipLevelHalf = 5000
        val ctx = requireContext()
//        val rangeStartBackground =
//            ctx.getDrawableCompat(R.drawable.example_4_continuous_selected_bg_start).also {
//                it.level = clipLevelHalf // Used by ClipDrawable
//            }

        val rangeStartBackground =
            ContextCompat.getDrawable(requireContext(), R.drawable.bg_start_range_date).also {
                it!!.level = clipLevelHalf
            }

        val rangeEndBackground =
            ContextCompat.getDrawable(requireContext(), R.drawable.bg_end_range_date).also {
                it!!.level = clipLevelHalf // Used by ClipDrawable
            }
        val rangeMiddleBackground =
            ContextCompat.getDrawable(requireContext(), R.drawable.dummy)

        val singleBackground = ContextCompat.getDrawable(
            requireContext(),
            R.drawable.bg_single_range_date
        )

        val todayBackground = ContextCompat.getDrawable(
            requireContext(),
            R.drawable.bg_today_date
        )

        @RequiresApi(Build.VERSION_CODES.O)
        class DayViewContainer(view: View) : ViewContainer(view) {
            lateinit var day: CalendarDay // Will be set when this container is bound.
            val binding = Example4CalendarDayBinding.bind(view)

            init {
                view.setOnClickListener {
                    if (day.position == DayPosition.MonthDate &&
                        (day.date == today || day.date.isAfter(today))
                    ) {
                        selection = getSelection(
                            clickedDate = day.date,
                            dateSelection = selection,
                        )
                        this@DateRangePickerBottomSheet.binding.exFourCalendar.notifyCalendarChanged()
                        bindSummaryViews()
                    }
                }
            }
        }

        binding.exFourCalendar.dayBinder = object : MonthDayBinder<DayViewContainer> {
            @RequiresApi(Build.VERSION_CODES.O)
            override fun create(view: View) = DayViewContainer(view)

            @RequiresApi(Build.VERSION_CODES.O)
            override fun bind(container: DayViewContainer, data: CalendarDay) {
                container.day = data
                val textView = container.binding.exFourDayText
                val roundBgView = container.binding.exFourRoundBackgroundView
                val continuousBgView = container.binding.exFourContinuousBackgroundView

                textView.text = null
                roundBgView.makeInVisible()
                continuousBgView.makeInVisible()

                val (startDate, endDate) = selection

                when (data.position) {
                    DayPosition.MonthDate -> {
                        textView.text = data.date.dayOfMonth.toString()
                        if (data.date.isBefore(today)) {
                            textView.setTextColorRes(R.color.black)
                        } else {
                            when {
                                startDate == data.date && endDate == null -> {
                                    textView.setTextColorRes(R.color.black)
                                    if (singleBackground != null) {
                                        roundBgView.applyBackground(singleBackground)
                                    }
                                }
                                data.date == startDate -> {
                                    textView.setTextColorRes(R.color.black)
                                    if (rangeStartBackground != null) {
                                        continuousBgView.applyBackground(rangeStartBackground)
                                    }
                                    if (singleBackground != null) {
                                        roundBgView.applyBackground(singleBackground)
                                    }
                                }
                                startDate != null && endDate != null && (data.date > startDate && data.date < endDate) -> {
                                    textView.setTextColorRes(R.color.black)
                                    if (rangeMiddleBackground != null) {
                                        continuousBgView.applyBackground(rangeMiddleBackground)
                                    }
                                }
                                data.date == endDate -> {
                                    textView.setTextColorRes(R.color.black)
                                    if (rangeEndBackground != null) {
                                        continuousBgView.applyBackground(rangeEndBackground)
                                    }
                                    if (singleBackground != null) {
                                        roundBgView.applyBackground(singleBackground)
                                    }
                                }
                                data.date == today -> {
                                    textView.setTextColorRes(R.color.white)
                                    if (todayBackground != null) {
                                        roundBgView.applyBackground(todayBackground)
                                    }
                                }
                                else -> textView.setTextColorRes(R.color.black)
                            }
                        }
                    }
                    // Make the coloured selection background continuous on the
                    // invisible in and out dates across various months.
                    DayPosition.InDate ->
                        if (startDate != null && endDate != null &&
                            isInDateBetweenSelection(data.date, startDate, endDate)
                        ) {
                            if (rangeMiddleBackground != null) {
                                continuousBgView.applyBackground(rangeMiddleBackground)
                            }
                        }
                    DayPosition.OutDate ->
                        if (startDate != null && endDate != null &&
                            isOutDateBetweenSelection(data.date, startDate, endDate)
                        ) {
                            if (rangeMiddleBackground != null) {
                                continuousBgView.applyBackground(rangeMiddleBackground)
                            }
                        }
                }
            }

            private fun View.applyBackground(drawable: Drawable) {
                makeVisible()
                background = drawable
            }
        }

        class MonthViewContainer(view: View) : ViewContainer(view) {
            val textView = Example4CalendarHeaderBinding.bind(view).exFourHeaderText
        }
        binding.exFourCalendar.monthHeaderBinder =
            object : MonthHeaderFooterBinder<MonthViewContainer> {
                override fun create(view: View) = MonthViewContainer(view)

                @RequiresApi(Build.VERSION_CODES.O)
                override fun bind(container: MonthViewContainer, data: CalendarMonth) {
                    container.textView.text = data.yearMonth.displayText()
                }
            }
    }

    interface OnDateRangeSelected{
        fun selectedRange(start:String="", end:String="")
    }
    fun setSelectedRange(listener: OnDateRangeSelected){
        this.listener=listener
    }

    private fun getMonthNameManually(date:String):String{
        var monthName=""
        var d =""
        try {
            d=date.substring(0,date.indexOf('-'))
            val year = date.substring(date.indexOf('-') + 1)
            when(year){
                "01"->monthName="January"
                "02"->monthName="February"
                "03"->monthName="March"
                "04"->monthName="April"
                "05"->monthName="May"
                "06"->monthName="June"
                "07"->monthName="July"
                "08"->monthName="August"
                "09"->monthName="September"
                "10"->monthName="October"
                "11"->monthName="November"
                "12"->monthName="December"
                else ->""
            }
        }catch (e:Exception){
            e.printStackTrace()
        }
        return "$monthName-$d"
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun getCurrentTim(){

        val timestamp=SimpleDateFormat("HH:mm:ss")

        val time = Calendar.getInstance()
        time.add(Calendar.MINUTE, 3)

        val d  = timestamp.format(time.time)
        Toast.makeText(requireContext(),d.toString(), Toast.LENGTH_LONG).show()

    }
}