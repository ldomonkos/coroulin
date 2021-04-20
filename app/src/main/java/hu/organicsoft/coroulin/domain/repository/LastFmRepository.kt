package hu.organicsoft.coroulin.domain.repository

import hu.organicsoft.coroulin.domain.model.Artist

interface LastFmRepository {
    suspend fun searchArtists(searchPhrase: String): List<Artist>
}