package hu.organicsoft.coroulin.data.endpoint

import hu.organicsoft.coroulin.data.model.SearchResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface LastFmEndPoint {

    @GET("?method=artist.search&format=json")
    suspend fun search(@Query("artist") artist: String, @Query("api_key") apiKey: String): SearchResponse
}