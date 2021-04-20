package hu.organicsoft.coroulin.data.mapper

import hu.organicsoft.coroulin.data.model.SearchResponse
import hu.organicsoft.coroulin.domain.model.Artist

class LastFmMapperImpl: LastFmMapper {

    override fun map(searchResponse: SearchResponse): List<Artist> {
        val artists = ArrayList<Artist>()
        for (artist in searchResponse.results.artistMatches.artists) {
            var imageUrl: String? = null
            if (artist.images.isNotEmpty()) {
                imageUrl = artist.images.last().url
            }
            artists.add(Artist(artist.name, artist.listeners.toInt(), imageUrl))
        }
        return artists
    }
}