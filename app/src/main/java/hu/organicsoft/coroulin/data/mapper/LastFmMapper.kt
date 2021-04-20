package hu.organicsoft.coroulin.data.mapper

import hu.organicsoft.coroulin.data.model.SearchResponse
import hu.organicsoft.coroulin.domain.model.Artist

interface LastFmMapper {

    fun map(searchResponse: SearchResponse): List<Artist>
}