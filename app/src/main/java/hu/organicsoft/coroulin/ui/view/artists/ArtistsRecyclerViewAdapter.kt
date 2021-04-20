package hu.organicsoft.coroulin.ui.view.artists

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import hu.organicsoft.coroulin.R
import hu.organicsoft.coroulin.domain.model.Artist
import java.lang.ref.WeakReference

open class ArtistsRecyclerViewAdapter(protected val context: Context): RecyclerView.Adapter<ArtistsRecyclerViewAdapter.ViewHolder>() {

    //region Properties
    protected val layoutInflater: LayoutInflater = LayoutInflater.from(context)

    var artists: List<Artist>? = null
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    protected var delegateWeakReference: WeakReference<ArtistsRecyclerViewAdapterDelegate>? = null
    //endregion

    //region Public Interface
    fun setDelegate(delegate: ArtistsRecyclerViewAdapterDelegate) {
        delegateWeakReference = WeakReference(delegate)
    }
    //endregion

    //region RecyclerView.Adapter
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(layoutInflater.inflate(R.layout.recycler_view_artist, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.artist = artists?.get(position)
        holder.artistNameTextView.text = holder.artist?.name
        holder.numberOfListenersTextView.text = holder.artist?.numberOfListeners?.toString()
        holder.artistImageView.setImageDrawable(null)
        holder.artist?.imageUrl?.let { imageUrl ->
            holder.artistImageView.load(imageUrl)
        }
    }

    override fun getItemCount() = artists?.size ?: 0
    //endregion

    //region ViewHolder
    inner class ViewHolder(view: View): RecyclerView.ViewHolder(view), View.OnClickListener {
        val artistImageView: ImageView = view.findViewById(R.id.artistImageView)
        val artistNameTextView: TextView = view.findViewById(R.id.artistNameTextView)
        val numberOfListenersTextView: TextView = view.findViewById(R.id.numberOfListenersTextView)
        var artist: Artist? = null

        init {
            view.setOnClickListener(this)
        }

        override fun onClick(view: View?) {
            val artist = artist ?: return
            delegateWeakReference?.get()?.let { delegate ->
                delegate.onArtistSelected(artist)
            }
        }
    }
    //endregion
}