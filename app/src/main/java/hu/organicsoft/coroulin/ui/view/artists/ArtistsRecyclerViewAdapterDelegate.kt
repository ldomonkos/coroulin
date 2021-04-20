package hu.organicsoft.coroulin.ui.view.artists

import hu.organicsoft.coroulin.domain.model.Artist

interface ArtistsRecyclerViewAdapterDelegate {
    fun onArtistSelected(artist: Artist)
}