package hu.organicsoft.coroulin.ui.view.artist_details

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import coil.load
import dagger.hilt.android.AndroidEntryPoint
import hu.organicsoft.coroulin.R
import hu.organicsoft.coroulin.ui.view_model.artist_details.ArtistDetailsViewModel
import kotlinx.android.synthetic.main.activity_artist_details.*

@AndroidEntryPoint
class ArtistDetailsActivity : AppCompatActivity() {

    //region Properties
    private val viewModel: ArtistDetailsViewModel by viewModels()
    //endregion

    //region Activity
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_artist_details)

        viewModel.artist.observe(this) { artist ->
            artistNameTextView.text = artist?.name
            artist?.imageUrl?.let { imageUrl ->
                artistImageView.load(imageUrl)
            }
        }
    }
    //endregion
}
