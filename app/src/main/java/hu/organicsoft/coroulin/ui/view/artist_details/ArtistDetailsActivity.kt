package hu.organicsoft.coroulin.ui.view.artist_details

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import coil.load
import dagger.hilt.android.AndroidEntryPoint
import hu.organicsoft.coroulin.databinding.ActivityArtistDetailsBinding
import hu.organicsoft.coroulin.ui.view_model.artist_details.ArtistDetailsViewModel

@AndroidEntryPoint
class ArtistDetailsActivity : AppCompatActivity() {

    //region Properties
    private lateinit var binding: ActivityArtistDetailsBinding

    private val viewModel: ArtistDetailsViewModel by viewModels()
    //endregion

    //region Activity
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityArtistDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.artist.observe(this) { artist ->
            binding.artistNameTextView.text = artist?.name
            artist?.imageUrl?.let { imageUrl ->
                binding.artistImageView.load(imageUrl)
            }
        }
    }
    //endregion
}
