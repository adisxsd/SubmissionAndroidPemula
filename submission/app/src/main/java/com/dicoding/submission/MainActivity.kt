package com.dicoding.submission

import ListMovieAdapter
import MarvelMovies
import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private lateinit var rvMarvel: RecyclerView
    private val list = ArrayList<MarvelMovies>()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val aboutPage: ImageView = findViewById(R.id.about_page)

        aboutPage.setOnClickListener {
            val intent = Intent(this, AboutActivity::class.java)
            startActivity(intent)
        }

        rvMarvel = findViewById(R.id.rv_marvel)
        rvMarvel.setHasFixedSize(true)

        list.addAll(getListMovies())
        showRecyclerList()



    }
    private fun getListMovies(): ArrayList<MarvelMovies> {
        val dataName = resources.getStringArray(R.array.data_name)
        val dataDescription = resources.getStringArray(R.array.data_description)
        val dataLongDescription = resources.getStringArray(R.array.data_long_description) // Data deskripsi panjang
        val dataPhoto = resources.obtainTypedArray(R.array.data_photo)
        val listMovies = ArrayList<MarvelMovies>()

        for (i in dataName.indices) {
            val movie = MarvelMovies(dataName[i], dataDescription[i], dataPhoto.getResourceId(i, -1), dataLongDescription[i])
            listMovies.add(movie)
        }

        dataPhoto.recycle()
        return listMovies
    }
    private fun showRecyclerList() {
        rvMarvel.layoutManager = LinearLayoutManager(this)
        val listHeroAdapter = ListMovieAdapter(list, object : ListMovieAdapter.OnItemClickCallback {
            override fun onItemClicked(data: MarvelMovies) {
                val intent = Intent(this@MainActivity, DetailActivity::class.java)
                intent.putExtra(DetailActivity.EXTRA_NAME, data.name)
                intent.putExtra(DetailActivity.EXTRA_DESCRIPTION, data.description)
                intent.putExtra(DetailActivity.EXTRA_PHOTO, data.photo)
                intent.putExtra(DetailActivity.EXTRA_LONG_DESCRIPTION, data.longDescription) // Kirim longDescription
                startActivity(intent)
            }
        })
        rvMarvel.adapter = listHeroAdapter
    }


}