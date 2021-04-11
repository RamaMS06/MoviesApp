package id.rama.moviesapp.repository

import id.rama.moviesapp.api.RetrofitInstance
import id.rama.moviesapp.model.modelgenrelist.ResponseListGenreMovies
import id.rama.moviesapp.model.modelmoviedetail.ResponseMovieDetail
import id.rama.moviesapp.model.modelmovielist.ResponseMovieList
import id.rama.moviesapp.model.modelreviews.ResponseReviewsList
import id.rama.moviesapp.model.modeltrailermovie.ResponseTrailerMovie
import retrofit2.Response

class RepositoryMovies {

    suspend fun getListGenre(
        apiKey: String
    ): Response<ResponseListGenreMovies>? {
        return RetrofitInstance.apiListGenre.getListGenre(apiKey)
    }

    suspend fun getMovieList(
        listId: Int,
        apiKey: String
    ): Response<ResponseMovieList>? {
        return RetrofitInstance.apiMovieList.getMovieList(listId, apiKey)
    }

    suspend fun getMovieDetail(
        movieId: Int,
        apiKey: String
    ): Response<ResponseMovieDetail>? {
        return RetrofitInstance.apiMovieDetail.getMovieDetail(movieId, apiKey)
    }

    suspend fun getReviewsList(
        movieId: Int,
        apiKey: String,
        page: Int
    ): Response<ResponseReviewsList>? {
        return RetrofitInstance.apiReviewsList.getReviewList(movieId, apiKey, page)
    }

    suspend fun getTrailerMovie(
        movieId : Int,
        apiKey: String
    ) : Response<ResponseTrailerMovie>? {
        return RetrofitInstance.apiTrailerMovie.getTrailerMovie(movieId, apiKey)
    }
}