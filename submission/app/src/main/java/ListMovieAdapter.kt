import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.dicoding.submission.R

class ListMovieAdapter(private val listMovie: ArrayList<MarvelMovies>, private val onItemClickCallback: OnItemClickCallback) : RecyclerView.Adapter<ListMovieAdapter.ListViewHolder>() {

    interface OnItemClickCallback {
        fun onItemClicked(data: MarvelMovies)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_row_movie, parent, false)
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val movie = listMovie[position]
        holder.imgPhoto.setImageResource(movie.photo)
        holder.tvName.text = movie.name
        holder.tvDescription.text = movie.description

        holder.itemView.setOnClickListener {
            onItemClickCallback.onItemClicked(movie)
        }
    }

    override fun getItemCount(): Int = listMovie.size

    class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imgPhoto: ImageView = itemView.findViewById(R.id.img_item_photo)
        val tvName: TextView = itemView.findViewById(R.id.tv_item_name)
        val tvDescription: TextView = itemView.findViewById(R.id.tv_item_description)
    }
}

