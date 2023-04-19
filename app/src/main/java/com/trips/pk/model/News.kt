package com.trips.pk.model

import android.os.Build
import android.text.Html
import com.google.gson.annotations.SerializedName
import com.trips.pk.ui.common.ADMIN_BASE_URL

data class News(
    @SerializedName("heading")
    val heading: String,
    @SerializedName("thumbnail")
    val thumbnail: String,

    //For News Detail
    @SerializedName("banner")
    val banner: String,
    @SerializedName("description")
    val description: String
) {

    val thumbnailLink: String
        get() = "$ADMIN_BASE_URL$thumbnail"

    val bannerLink:String
    get() = "$ADMIN_BASE_URL$banner"

    val descriptionText:String
    get() = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
       Html.fromHtml(description, Html.FROM_HTML_MODE_COMPACT).toString()
    } else {
        Html.fromHtml(description).toString()
    }
}
