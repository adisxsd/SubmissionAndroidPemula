import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MarvelMovies(
    val name: String,
    val description: String,
    val photo: Int,
    val longDescription: String
) : Parcelable