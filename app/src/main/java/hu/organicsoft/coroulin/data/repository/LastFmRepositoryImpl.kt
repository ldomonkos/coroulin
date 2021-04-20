package hu.organicsoft.coroulin.data.repository

import android.content.Context
import hu.organicsoft.coroulin.R
import hu.organicsoft.coroulin.data.endpoint.LastFmEndPoint
import hu.organicsoft.coroulin.data.mapper.LastFmMapper
import hu.organicsoft.coroulin.domain.model.Artist
import hu.organicsoft.coroulin.domain.repository.LastFmRepository

open class LastFmRepositoryImpl(protected val context: Context,
                                protected val lastFmEndPoint: LastFmEndPoint,
                                protected val lastFmMapper: LastFmMapper): LastFmRepository {
    override suspend fun searchArtists(searchPhrase: String): List<Artist> {
        return lastFmMapper.map(lastFmEndPoint.search(searchPhrase, context.getString(R.string.api_key)))
    }
}