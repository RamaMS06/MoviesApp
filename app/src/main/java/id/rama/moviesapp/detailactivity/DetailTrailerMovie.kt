package id.rama.moviesapp.detailactivity

import android.accounts.AbstractAccountAuthenticator
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import id.rama.moviesapp.R
import id.rama.moviesapp.repository.RepositoryMovies
import id.rama.moviesapp.utils.Utilities
import id.rama.moviesapp.viewmodel.ViewModelTrailerMovie
import id.rama.moviesapp.viewmodelfactory.ViewModelFactoryTrailerMovie
import kotlinx.android.synthetic.main.activity_detail_trailer_movie.*

class DetailTrailerMovie : AppCompatActivity() {
    private lateinit var viewModelTrailer : ViewModelTrailerMovie
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_trailer_movie)

        getDataTrailer()
        backActivity()
    }

    private fun backActivity() {
        btn_back_movie_list.setOnClickListener {
            onBackPressed()
        }
    }

    private fun getDataTrailer() {
        val idMovie = intent.getIntExtra(Utilities.LIST_MOVIE_ID,0)
        val title = intent.getStringExtra(Utilities.LIST_TITLE_MOVIE)
        val tagline = intent.getStringExtra(Utilities.LIST_TAGLINE_MOVIE)
        val vote = intent.getFloatExtra(Utilities.LIST_VOTE_MOVIE,0F)
        val language = intent.getStringExtra(Utilities.LIST_LANGUAGE_MOVIE)
        val repo = RepositoryMovies()
        val viewModelFactoryTrailer = ViewModelFactoryTrailerMovie(repo)
        viewModelTrailer = ViewModelProvider(this,viewModelFactoryTrailer).get(ViewModelTrailerMovie::class.java)
        viewModelTrailer.getTrailerMovie(idMovie,Utilities.TOKEN_MOVIES)
        viewModelTrailer.responseTrailer.observe(this, Observer { response ->
            if (response.isSuccessful){
                pb_trailer_movie.visibility = View.GONE
                container_trailer_movie.visibility = View.VISIBLE
                val idTrailer= response.body()?.results?.get(0)?.key.toString()
                txt_title_movie_trailer.text = title
                txt_tagline_movie_trailer.text = tagline
                txt_vote_average_movie_trailer.text = vote.toString()
                txt_language_movie_trailer.text = language

                lifecycle.addObserver(yt_trailer_detail_movies)
                yt_trailer_detail_movies.addYouTubePlayerListener(object  :
                    AbstractYouTubePlayerListener(){
                    override fun onReady(youTubePlayer: YouTubePlayer) {
                        super.onReady(youTubePlayer)
                        youTubePlayer.loadVideo(idTrailer, 0F)
                    }
                })
            }
        })
    }
}