package hu.organicsoft.coroulin.data.model

import com.google.gson.annotations.SerializedName

data class ArtistMatches(

    @SerializedName("artist")
    val artists: List<Artist>
)
