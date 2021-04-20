package hu.organicsoft.coroulin.ui.view_model.artists

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import hu.organicsoft.coroulin.domain.model.Artist
import hu.organicsoft.coroulin.domain.repository.LastFmRepository
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class ArtistsViewModel @Inject constructor(
    private val lastFmRepository: LastFmRepository
    ): ViewModel() {

    private val _artists = MutableLiveData<List<Artist>>()
    val artists: LiveData<List<Artist>> = _artists

    fun searchArtists(searchPhrase: String?) {
        val checkedSearchPhrase = searchPhrase ?: return
        val errorHandler = CoroutineExceptionHandler { _, throwable ->
            Timber.e(throwable)
        }

        viewModelScope.launch(errorHandler) {
            val searchArtistsResult = lastFmRepository.searchArtists(checkedSearchPhrase)
            if (searchArtistsResult.isNotEmpty()) {
                _artists.value = searchArtistsResult
            }
        }
    }
}
