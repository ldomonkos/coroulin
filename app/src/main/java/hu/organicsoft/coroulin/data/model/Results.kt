package hu.organicsoft.coroulin.data.model

import com.google.gson.annotations.SerializedName

data class Results(

    @SerializedName("artistmatches")
    val artistMatches: ArtistMatches
)
