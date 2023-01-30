package com.trips.pk.utils

import android.app.DatePickerDialog
import android.app.Dialog
import android.app.usage.UsageEvents
import android.content.ContentResolver
import android.content.Context
import android.net.Uri
import android.os.Build
import android.os.Parcel
import android.os.Parcelable
import android.provider.OpenableColumns
import android.view.View
import android.view.ViewGroup
import android.view.ViewTreeObserver
import android.webkit.MimeTypeMap
import android.widget.Button
import android.widget.FrameLayout
import android.widget.TextView
import androidx.annotation.ColorRes
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import androidx.core.util.Pair
import androidx.fragment.app.FragmentManager
import com.github.razir.progressbutton.hideProgress
import com.github.razir.progressbutton.showProgress
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.datepicker.CalendarConstraints
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener
import com.kizitonwose.calendar.core.atStartOfMonth
import com.kizitonwose.calendar.core.nextMonth
import com.kizitonwose.calendar.core.previousMonth
import com.kizitonwose.calendar.core.yearMonth
import com.trips.pk.R
import com.trips.pk.ui.common.sharedDob
import com.trips.pk.ui.common.sharedExpireDate
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.text.SimpleDateFormat
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.Month
import java.time.YearMonth
import java.time.format.DateTimeFormatter
import java.time.format.TextStyle
import java.time.temporal.ChronoUnit
import java.util.*

 val mCalendar: Calendar = Calendar.getInstance()
val date = DatePickerDialog.OnDateSetListener { view, year, month, day ->
        mCalendar[Calendar.YEAR] = year
        mCalendar[Calendar.MONTH] = month
        mCalendar[Calendar.DAY_OF_MONTH] = day
        updateDobLabel()
    }

fun updateDobLabel(){
    val myFormat = "dd MMM, yyyy"
    val serverFormat="yyyy-MM-dd"
    val dateFormat = SimpleDateFormat(myFormat, Locale.US)
    val dateFormatForServer = SimpleDateFormat(serverFormat, Locale.US)
    // dob=dateFormatForServer.format(myCalendar.time)
    val date = dateFormat.format(mCalendar.time)
    if (sharedDob!=null) sharedDob!!.setText(date)
    if (sharedExpireDate!=null)sharedExpireDate!!.setText(date)
}

fun Button.makeProgressOnButton(loadingTextRes: Int) {
    showProgress {
        buttonTextRes = loadingTextRes
        progressColor = currentTextColor
    }
}
fun Button.hideProgressOnButton(text:String) {
    hideProgress(text)
}

fun splitTimeAndDate(date : String) :String {
    var d=""
    try {
        d= date.substring(0, date.indexOf('T'))
    }catch (e:Exception){
        e.printStackTrace()
    }
    return d
}

object Helpers {

    fun makeBottomSheetRounded(view: View, dialog: Dialog){
        view.viewTreeObserver.addOnGlobalLayoutListener(object :
            ViewTreeObserver.OnGlobalLayoutListener {
            override fun onGlobalLayout() {
                view.viewTreeObserver.removeOnGlobalLayoutListener(this)
                val dg = dialog as BottomSheetDialog?
                val bottomSheet =
                    dg!!.findViewById<View>(com.google.android.material.R.id.design_bottom_sheet) as FrameLayout?

                //Change background Image for all android versions below Api < 21
                bottomSheet!!.setBackgroundResource(R.drawable.bg_rounded_cardview)
            }
        })
    }
    fun setMargins(view: View, left: Int, top: Int, right: Int, bottom: Int) {
        if (view.layoutParams is ViewGroup.MarginLayoutParams) {
            val p = view.layoutParams as ViewGroup.MarginLayoutParams
            p.setMargins(left, top, right, bottom)
            view.requestLayout()
        }
    }
}

object FileHelper {
    fun getTextFromResources(context: Context, resId: Int) =
        context.resources.openRawResource(resId).use { inputStream ->
            inputStream.bufferedReader().use {
                it.readText()
            }
        }

    private fun ContentResolver.getFileName(fileUri: Uri, context: Context): String {

        var name = ""
        val returnCursor = this.query(fileUri, null, null, null, null)
        if (returnCursor != null) {
            val nameIndex = returnCursor.getColumnIndex(OpenableColumns.DISPLAY_NAME)
            returnCursor.moveToFirst()
            name = returnCursor.getString(nameIndex) ?: getFileName() + "." + getMineType(fileUri, context)
            returnCursor.close()
        }

        return name
    }

    fun createSelectedFileCopy(uri: Uri, context: Context): File? {
        val parcelFileDescriptor = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            context.contentResolver.openFileDescriptor(uri, "r", null)
        } else {
            null
        }
        parcelFileDescriptor?.let {
            val inputStream = FileInputStream(parcelFileDescriptor.fileDescriptor)
            val file = File(context.cacheDir, context.contentResolver.getFileName(uri, context))
            val outputStream = FileOutputStream(file)
            inputStream.copyTo(outputStream)
            inputStream.close()
            outputStream.close()
            return file
        }
        return null
    }

    private fun getMineType(uri: Uri?, context: Context?) : String? {
        return try {
            val cR = context?.contentResolver
            val mime = MimeTypeMap.getSingleton()
            mime.getExtensionFromMimeType(uri?.let { cR?.getType(it) })
        }catch (e: Exception){
            ""
        }
    }

    private fun getFileName() : String{
        val tsLong = System.currentTimeMillis()/1000
        return tsLong.toString()
    }

}

object CustomDateRangeHelper{
    @RequiresApi(Build.VERSION_CODES.O)
    data class DateSelection(val startDate: LocalDate? = null, val endDate: LocalDate? = null) {
        val daysBetween by lazy(UsageEvents.Event.NONE) {
            if (startDate == null || endDate == null) null else {
                ChronoUnit.DAYS.between(startDate, endDate)
            }
        }
    }
    internal fun TextView.setTextColorRes(@ColorRes color: Int) =
        setTextColor(ContextCompat.getColor(context,color))

    @RequiresApi(Build.VERSION_CODES.O)
    private val rangeFormatter = DateTimeFormatter.ofPattern("d MMMM yyyy")
    @RequiresApi(Build.VERSION_CODES.O)
    fun dateRangeDisplayText(startDate: LocalDate, endDate: LocalDate): String {
        return "Selected: ${rangeFormatter.format(startDate)} - ${rangeFormatter.format(endDate)}"
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun getSelection(
        clickedDate: LocalDate,
        dateSelection: DateSelection,
    ): DateSelection {
        val (selectionStartDate, selectionEndDate) = dateSelection
        return if (selectionStartDate != null) {
            if (clickedDate < selectionStartDate || selectionEndDate != null) {
                DateSelection(startDate = clickedDate, endDate = null)
            } else if (clickedDate != selectionStartDate) {
                DateSelection(startDate = selectionStartDate, endDate = clickedDate)
            } else {
                DateSelection(startDate = clickedDate, endDate = null)
            }
        } else {
            DateSelection(startDate = clickedDate, endDate = null)
        }
    }

    fun View.makeInVisible() {
        visibility = View.INVISIBLE
    }

    fun View.makeVisible(){
        visibility=View.VISIBLE
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun isInDateBetweenSelection(
        inDate: LocalDate,
        startDate: LocalDate,
        endDate: LocalDate,
    ): Boolean {
        if (startDate.yearMonth == endDate.yearMonth) return false
        if (inDate.yearMonth == startDate.yearMonth) return true
        val firstDateInThisMonth = inDate.yearMonth.nextMonth.atStartOfMonth()
        return firstDateInThisMonth in startDate..endDate && startDate != firstDateInThisMonth
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun isOutDateBetweenSelection(
        outDate: LocalDate,
        startDate: LocalDate,
        endDate: LocalDate,
    ): Boolean {
        if (startDate.yearMonth == endDate.yearMonth) return false
        if (outDate.yearMonth == endDate.yearMonth) return true
        val lastDateInThisMonth = outDate.yearMonth.previousMonth.atEndOfMonth()
        return lastDateInThisMonth in startDate..endDate && endDate != lastDateInThisMonth
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun YearMonth.displayText(short: Boolean = false): String {
        return "${this.month.displayText(short = short)} ${this.year}"
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun Month.displayText(short: Boolean = true): String {
        val style = if (short) TextStyle.SHORT else TextStyle.FULL
        return getDisplayName(style, Locale.ENGLISH)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun DayOfWeek.displayText(uppercase: Boolean = false): String {
        return getDisplayName(TextStyle.SHORT, Locale.ENGLISH).let { value ->
            if (uppercase) value.uppercase(Locale.ENGLISH) else value
        }
    }

}
 fun changeStringDateFormat(date:kotlin.String):kotlin.String{
    var formattedDate=""
    try {
        val fromUser = SimpleDateFormat("yyyy-MM-dd")
        val myFormat = SimpleDateFormat("dd/MM/yyyy")
        formattedDate =  myFormat.format(fromUser.parse(date))
    }catch (e:Exception){
        e.printStackTrace()
    }
    return formattedDate
}

object DatePicker {
    const val DATE_ENABLED_ALL = 0
    const val DATE_ENABLED_PAST_ONLY = 1
    const val DATE_ENABLED_FUTURE_ONLY = 2

    fun showDateRangePicker(
        fm: FragmentManager, enabledDates: Int, selection: Pair<Long, Long>? = null,
        onDateSelectedListener: MaterialPickerOnPositiveButtonClickListener<Pair<Long, Long>>? = null
    ) {
        val builder = MaterialDatePicker.Builder.dateRangePicker()
        builder.setSelection(selection)
        if (enabledDates != DATE_ENABLED_ALL) {
            builder.setCalendarConstraints(limitRange(enabledDates).build())
        }
        val picker = builder.build()
        picker.addOnPositiveButtonClickListener(onDateSelectedListener)
        picker.show(fm, picker.toString())
    }

    fun showDatePicker(
        fm: FragmentManager, enabledDates: Int, selection: Long? = null,
        onDateSelectedListener: MaterialPickerOnPositiveButtonClickListener<Long>? = null
    ) {
        val builder = MaterialDatePicker.Builder.datePicker().setTheme(R.style.MaterialCalendarTheme)
        builder.setSelection(selection)
        if (enabledDates != DATE_ENABLED_ALL) {
            builder.setCalendarConstraints(limitRange(enabledDates).build())
        }
        val picker = builder.build()
        picker.addOnPositiveButtonClickListener(onDateSelectedListener)
        picker.show(fm, picker.toString())
    }

    private fun limitRange(enabledDates: Int): CalendarConstraints.Builder {

        val constraintsBuilderRange = CalendarConstraints.Builder()

        val calendarStart: Calendar = Calendar.getInstance()
        val calendarEnd: Calendar = Calendar.getInstance()

        val year = calendarStart.get(Calendar.YEAR)
        val month = calendarStart.get(Calendar.MONTH) + 1
        val day = calendarStart.get(Calendar.DATE)

        when (enabledDates) {
            DATE_ENABLED_PAST_ONLY -> {
                calendarStart.set(
                    year - 10,
                    month - 1,
                    day - 1
                )
                calendarEnd.set(year, month - 1, day)
            }
            DATE_ENABLED_FUTURE_ONLY -> {
                calendarStart.set(year, month - 1, day - 1)
                calendarEnd.set(year + 10, month - 1, day)
            }
        }

        val minDate = calendarStart.timeInMillis
        val maxDate = calendarEnd.timeInMillis

        constraintsBuilderRange.setStart(minDate)
        constraintsBuilderRange.setEnd(maxDate)

        constraintsBuilderRange.setValidator(RangeValidator(minDate, maxDate))

        return constraintsBuilderRange
    }
}


private class RangeValidator(private val minDate: Long, private val maxDate: Long) :
    CalendarConstraints.DateValidator {

    constructor(parcel: Parcel) : this(
        parcel.readLong(),
        parcel.readLong()
    )

    override fun writeToParcel(p0: Parcel, flags: Int) {

    }

    override fun describeContents(): Int {
        return 0
    }

    override fun isValid(date: Long): Boolean {
        return !(minDate > date || maxDate < date)

    }

    companion object CREATOR : Parcelable.Creator<RangeValidator> {
        override fun createFromParcel(parcel: Parcel): RangeValidator {
            return RangeValidator(parcel)
        }

        override fun newArray(size: Int): Array<RangeValidator?> {
            return arrayOfNulls(size)
        }
    }

}