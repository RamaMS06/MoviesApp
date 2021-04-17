package id.rama.moviesapp.activity

import android.annotation.SuppressLint
import android.app.ActivityOptions
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.material.snackbar.Snackbar
import id.rama.moviesapp.R
import id.rama.moviesapp.adapter.AdapterListGenre
import id.rama.moviesapp.model.modelgenrelist.ModelListGenreMovies
import id.rama.moviesapp.model.modelmoviedetail.ModelGenresMovieDetail
import id.rama.moviesapp.repository.RepositoryMovies
import id.rama.moviesapp.utils.Utilities
import id.rama.moviesapp.viewmodel.ViewModelListGenre
import id.rama.moviesapp.viewmodelfactory.ViewModelFactoryListGenre
import kotlinx.android.synthetic.main.activity_list_genre_movies.*
import kotlinx.android.synthetic.main.activity_movie_list.*

class ListGenreMovies : AppCompatActivity(), AdapterListGenre.OnGenreCLickListener {
    private lateinit var viewModelGenre: ViewModelListGenre
    private val adapterGenre by lazy { AdapterListGenre(this, this) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_genre_movies)

        setupRecyclerView()
        getDataListGenre()
        alertMovie()

    }

    private fun setupRecyclerView() {
        rv_list_genre_movies.apply {
            adapter = adapterGenre
            layoutManager =
                GridLayoutManager(this@ListGenreMovies, 2, GridLayoutManager.VERTICAL, false)
        }
    }

    private fun alertMovie() {
        edt_search_list_genre_movies.setOnClickListener {
            Snackbar.make(layout_container_list_genre, "This feature not work, wait soon :)",Snackbar.LENGTH_LONG
            ).show()
        }
    }

    private fun getDataListGenre() {
        val repository = RepositoryMovies()
        val viewModelFactoryGenre = ViewModelFactoryListGenre(repository)
        viewModelGenre =
            ViewModelProvider(this, viewModelFactoryGenre).get(ViewModelListGenre::class.java)
        viewModelGenre.getListGenre(Utilities.TOKEN_MOVIES)
        viewModelGenre.responseGenre.observe(this, Observer { response ->
            if (response.isSuccessful) {
                response.body()?.let { adapterGenre.setData(it.genres) }
            }
        })

        edt_search_list_genre_movies.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(
                s: CharSequence?,
                start: Int,
                count: Int,
                after: Int
            ) {
            }

            override fun onTextChanged(
                s: CharSequence?,
                start: Int,
                before: Int,
                count: Int
            ) {
                adapterGenre.filter.filter(s)
            }

        })
    }


    @SuppressLint("ResourceAsColor")
    override fun onItemClickListener(item: ModelListGenreMovies, position: Int) {
        viewModelGenre.responseGenre.observe(this, Observer { response ->
            val genreList = response.body()?.genres?.get(position)
            val intent = Intent(this, MovieList::class.java)
            val options: ActivityOptions = ActivityOptions.makeCustomAnimation(
                this,
                R.anim.slide_in_right,
                R.anim.slide_out_left
            )
            intent.putExtra(Utilities.LIST_GENRE_ID, genreList?.id)
            intent.putExtra(Utilities.LIST_GENRE_NAMA, genreList?.name)
            startActivity(intent, options.toBundle())
        })
    }


}