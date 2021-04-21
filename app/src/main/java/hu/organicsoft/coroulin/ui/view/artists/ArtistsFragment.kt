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
import hu.organicsoft.coroulin.databinding.FragmentArtistsBinding
import hu.organicsoft.coroulin.domain.model.Artist
import hu.organicsoft.coroulin.ui.Constants
import hu.organicsoft.coroulin.ui.view.artist_details.ArtistDetailsActivity
import hu.organicsoft.coroulin.ui.view_model.artists.ArtistsViewModel

@AndroidEntryPoint
class ArtistsFragment : Fragment(), ArtistsRecyclerViewAdapterDelegate {

    //region Properties
    private var _binding: FragmentArtistsBinding? = null
    private val binding get() = _binding!!

    private val viewModel: ArtistsViewModel by viewModels()

    private lateinit var artistsRecyclerViewAdapter: ArtistsRecyclerViewAdapter
    //endregion

    //region Fragment
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentArtistsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val context = context ?: return

        artistsRecyclerViewAdapter = ArtistsRecyclerViewAdapter(context)
        artistsRecyclerViewAdapter.setDelegate(this)
        binding.artistsRecyclerView.adapter = artistsRecyclerViewAdapter
        binding.artistsRecyclerView.layoutManager = GridLayoutManager(context, 2)

        binding.searchView.setOnQueryTextListener(object : OnQueryTextListener {
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

        binding.searchView.setOnCloseListener {
            showEmptyView()
            true
        }

        viewModel.artists.observe(viewLifecycleOwner) { artists ->
            binding.emptyGroup.visibility = View.GONE
            binding.artistsRecyclerView.visibility = View.VISIBLE
            artistsRecyclerViewAdapter.artists = artists
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    //endregion

    //region Protected Interface
    protected fun showEmptyView() {
        binding.artistsRecyclerView.visibility = View.GONE
        binding.emptyGroup.visibility = View.VISIBLE
    }
    //endregion

    //region ArtistsRecyclerViewAdapterDelegate
    override fun onArtistSelected(artist: Artist) {
        val context = context ?: return

        val intent = Intent(context, ArtistDetailsActivity::class.java)
        intent.putExtra(Constants.EXTRA_ARTIST, artist)
        startActivity(intent)
    }
    //endregion
}
