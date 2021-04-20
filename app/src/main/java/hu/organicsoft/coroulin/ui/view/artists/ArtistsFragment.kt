package hu.organicsoft.coroulin.ui.view.artists

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView.OnQueryTextListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import hu.organicsoft.coroulin.R
import hu.organicsoft.coroulin.domain.model.Artist
import hu.organicsoft.coroulin.ui.view.artist_details.ArtistDetailsActivity
import hu.organicsoft.coroulin.ui.view_model.artists.ArtistsViewModel
import kotlinx.android.synthetic.main.fragment_artists.*

@AndroidEntryPoint
class ArtistsFragment : Fragment(), ArtistsRecyclerViewAdapterDelegate {

    //region Properties
    private val viewModel: ArtistsViewModel by viewModels()

    private lateinit var artistsRecyclerViewAdapter: ArtistsRecyclerViewAdapter
    //endregion

    //region Fragment
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_artists, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val context = context ?: return

        artistsRecyclerViewAdapter = ArtistsRecyclerViewAdapter(context)
        artistsRecyclerViewAdapter.setDelegate(this)
        artistsRecyclerView.adapter = artistsRecyclerViewAdapter
        artistsRecyclerView.layoutManager = GridLayoutManager(context, 2)

        searchView.setOnQueryTextListener(object : OnQueryTextListener {
            override fun onQueryTextSubmit(text: String?): Boolean {
                viewModel.searchArtists(text)
                return true
            }

            override fun onQueryTextChange(searchPhrase: String?): Boolean {
                if (searchPhrase == null || searchPhrase.isEmpty()) {
                    showEmptyView()
                    return true
                }
                return false
            }
        })

        searchView.setOnCloseListener {
            showEmptyView()
            true
        }

        viewModel.artists.observe(viewLifecycleOwner) { artists ->
            emptyGroup.visibility = View.GONE
            artistsRecyclerView.visibility = View.VISIBLE
            artistsRecyclerViewAdapter.artists = artists
        }
    }
    //endregion

    //region Protected Interface
    protected fun showEmptyView() {
        artistsRecyclerView.visibility = View.GONE
        emptyGroup.visibility = View.VISIBLE
    }
    //endregion

    //region ArtistsRecyclerViewAdapterDelegate
    override fun onArtistSelected(artist: Artist) {
        val context = context ?: return

        val intent = Intent(context, ArtistDetailsActivity::class.java)
        intent.putExtra("artist", artist)
        startActivity(intent)
    }
    //endregion
}
