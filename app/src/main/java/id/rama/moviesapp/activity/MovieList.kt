package id.rama.moviesapp.activity

import android.app.ActivityOptions
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import id.rama.moviesapp.R
import id.rama.moviesapp.adapter.AdapterMovieList
import id.rama.moviesapp.detailactivity.DetailMovieList
import id.rama.moviesapp.model.modelmovielist.ModelMovieList
import id.rama.moviesapp.repository.RepositoryMovies
import id.rama.moviesapp.utils.Utilities
import id.rama.moviesapp.viewmodel.ViewModelMovieList
import id.rama.moviesapp.viewmodelfactory.ViewModelFactoryMovieList
import kotlinx.android.synthetic.main.activity_list_genre_movies.*
import kotlinx.android.synthetic.main.activity_movie_list.*

class MovieList : AppCompatActivity(), AdapterMovieList.OnMovieClickListener {
    private lateinit var viewModelMovieList : ViewModelMovieList
    private val adapterMovie by lazy { AdapterMovieList(this,this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_list)

        setupRecyclerView()
        getDataMovie()
        backActivity()
        alertMovie()
    }

    private fun alertMovie() {
        edt_search_movie_list.setOnClickListener {
            Snackbar.make(layout_container_movie_list, "This feature not work, wait soon :)",Snackbar.LENGTH_LONG
            ).show()
        }
    }

    private fun backActivity() {
        btn_back_movie_list.setOnClickListener {
            onBackPressed()
        }
    }

    private fun setupRecyclerView() {
        rv_movie_list.apply {
            adapter = adapterMovie
            layoutManager = LinearLayoutManager(this@MovieList,LinearLayoutManager.VERTICAL,false)
        }
    }

    private fun getDataMovie() {

        val idGenre = intent.getIntExtra(Utilities.LIST_GENRE_ID,0)
        val namaGenre = intent.getStringExtra(Utilities.LIST_GENRE_NAMA)
        pb_movie_list.visibility = View.VISIBLE
        txt_nama_genre_movie_list.text = namaGenre
        val repository = RepositoryMovies()
        val viewModelFactoryMovie = ViewModelFactoryMovieList(repository)
        viewModelMovieList = ViewModelProvider(this,viewModelFactoryMovie).get(ViewModelMovieList::class.java)
        viewModelMovieList.getMovieList(idGenre,Utilities.TOKEN_MOVIES)
        viewModelMovieList.responseMovie.observe(this, Observer { response ->
            if (response.isSuccessful){
                pb_movie_list.visibility = View.GONE
                rv_movie_list.visibility = View.VISIBLE
                response.body()?.let { adapterMovie.setData(it.items) }
            }else if (response.body()?.items?.get(0)?.poster_path?.isEmpty()!!){
                Log.d("Movie List", "getDataMovie: "+"Data Not Found")
                Toast.makeText(this, "Data not found!", Toast.LENGTH_SHORT).show()
            }
        })
    }

    override fun onItemClickListener(item: ModelMovieList, position: Int) {
        viewModelMovieList.responseMovie.observe(this, Observer { response ->
            val movieList = response.body()?.items?.get(position)
            val intent = Intent(this,DetailMovieList::class.java)
            val options : ActivityOptions = ActivityOptions.makeCustomAnimation(this,R.anim.slide_in_right,R.anim.slide_out_left)
            intent.putExtra(Utilities.LIST_MOVIE_ID, movieList?.id)
            startActivity(intent,options.toBundle())
        })
    }

    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(R.anim.slide_in_left,
            R.anim.slide_out_right);
    }
}