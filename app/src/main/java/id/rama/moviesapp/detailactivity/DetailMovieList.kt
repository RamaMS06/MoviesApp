package id.rama.moviesapp.detailactivity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.bumptech.glide.Glide
import id.rama.moviesapp.R
import id.rama.moviesapp.adapter.AdapterReviewsList
import id.rama.moviesapp.model.modelreviews.ModelReviewsList
import id.rama.moviesapp.repository.RepositoryMovies
import id.rama.moviesapp.utils.Utilities
import id.rama.moviesapp.viewmodel.ViewModelMovieDetail
import id.rama.moviesapp.viewmodel.ViewModelReviewsList
import id.rama.moviesapp.viewmodelfactory.ViewModelFactoryMovieDetail
import id.rama.moviesapp.viewmodelfactory.ViewModelFactoryReviewsList
import kotlinx.android.synthetic.main.activity_detail_movie_list.*
import kotlinx.android.synthetic.main.layout_include_detail_movie.*
import kotlinx.coroutines.joinAll

class DetailMovieList : AppCompatActivity(), AdapterReviewsList.OnReviewsClickListener {
    private lateinit var viewModelMovieDetail: ViewModelMovieDetail
    private lateinit var viewModelReviewsList: ViewModelReviewsList
    private lateinit var linearLayoutManager : LinearLayoutManager
    private val adapterReviews by lazy { AdapterReviewsList(this, this) }
    private var page =1
    private var totalPage = 0
    private var isLoading = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_movie_list)
        linearLayoutManager= LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        setupRecyclerView()
        getDataMovieDetail()
        getDataReviewsList()
    }

    private fun setupRecyclerView() {

        rv_reviews_detail_movie.apply {
            setHasFixedSize(true)
            adapter = adapterReviews
            layoutManager = linearLayoutManager
            addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    val visibleItemCount = linearLayoutManager.childCount
                    val pastVisibleItem = linearLayoutManager.findFirstVisibleItemPosition()
                    val total = adapterReviews.itemCount
                    if (!isLoading && page < totalPage) {
                        if (visibleItemCount + pastVisibleItem >= total) {
                            page++
                            getDataReviewsList()
                        }
                    }
                    super.onScrolled(recyclerView, dx, dy)
                }
            })
        }
    }

    private fun getDataMovieDetail() {
        val idMovie = intent.getIntExtra(Utilities.LIST_MOVIE_ID, 0)
        val repo = RepositoryMovies()
        val viewModelFactoryMovie = ViewModelFactoryMovieDetail(repo)
        viewModelMovieDetail =
            ViewModelProvider(this, viewModelFactoryMovie).get(ViewModelMovieDetail::class.java)
        viewModelMovieDetail.getMovieDetail(idMovie, Utilities.TOKEN_MOVIES)
        viewModelMovieDetail.responseMovies.observe(this, Observer { response ->
            if (response.isSuccessful) {
                pb_detail_movie_list.visibility = View.GONE
                layout_container_detail_movie.visibility = View.VISIBLE
                btn_watch_trailer_detail_movie.visibility = View.VISIBLE
                val movieDetail = response.body()
                Glide.with(this)
                    .load(Utilities.PATH_IMAGE_MOVIE + movieDetail?.poster_path)
                    .into(img_poster_movie_detail)
                txt_title_movie_detail.text = movieDetail?.title
                txt_tagline_movie_detail.text = movieDetail?.tagline
                txt_vote_average_movie_detail.text = movieDetail?.vote_average.toString()
                txt_language_movie_detail.text = movieDetail?.spoken_languages?.get(0)?.english_name
                txt_overview_movie_detail.text = movieDetail?.overview
                Glide.with(this)
                    .load(Utilities.PATH_IMAGE_MOVIE + movieDetail?.production_companies?.get(0)?.logo_path)
                    .into(img_companies_movie_detail)

                btn_watch_trailer_detail_movie.setOnClickListener {
                    val intent = Intent(this, DetailTrailerMovie::class.java)
                    intent.putExtra(Utilities.LIST_MOVIE_ID, idMovie)
                    intent.putExtra(Utilities.LIST_TITLE_MOVIE, movieDetail?.title)
                    intent.putExtra(Utilities.LIST_TAGLINE_MOVIE, movieDetail?.tagline)
                    intent.putExtra(Utilities.LIST_VOTE_MOVIE, movieDetail?.vote_average)
                    intent.putExtra(
                        Utilities.LIST_LANGUAGE_MOVIE,
                        movieDetail?.spoken_languages?.get(0)?.english_name
                    )
                    startActivity(intent)
                }

            } else if (response.code() == 404) {
                Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show()
            }
        })


    }

    private fun getDataReviewsList() {
        isLoading = true
        val idMovie = intent.getIntExtra(Utilities.LIST_MOVIE_ID, 0)
        val repo = RepositoryMovies()
        val viewModelFactoryReviews = ViewModelFactoryReviewsList(repo)
        viewModelReviewsList =
            ViewModelProvider(this, viewModelFactoryReviews).get(ViewModelReviewsList::class.java)
        viewModelReviewsList.getReviewsList(idMovie, Utilities.TOKEN_MOVIES, page++)
        viewModelReviewsList.responseReviews.observe(this, Observer { response ->
            if (response.isSuccessful) {
                totalPage = response.body()?.total_pages!!
                val trailerList = response.body()
                trailerList.let { adapterReviews.setData(it!!.results) }
            }
            isLoading = false
        })

    }

    override fun onItemClickListener(item: ModelReviewsList, position: Int) {

    }


}