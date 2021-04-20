package hu.organicsoft.coroulin.ui.view_model.artist_details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import hu.organicsoft.coroulin.domain.model.Artist
import javax.inject.Inject

@HiltViewModel
class ArtistDetailsViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle
    ): ViewModel() {

    private val _artist = MutableLiveData<Artist>()
    val artist: LiveData<Artist> = _artist

    init {
        _artist.value = savedStateHandle.get("artist")
    }
}