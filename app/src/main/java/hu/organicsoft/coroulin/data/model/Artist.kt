package hu.organicsoft.coroulin.data.model

import com.google.gson.annotations.SerializedName

data class Artist(
    val name: String,
    val listeners: String,

    @SerializedName("image")
    val images: List<Image>
)
