package id.rama.moviesapp.api

import id.rama.moviesapp.model.modelgenrelist.ResponseListGenreMovies
import id.rama.moviesapp.model.modelmoviedetail.ResponseMovieDetail
import id.rama.moviesapp.model.modelmovielist.ResponseMovieList
import id.rama.moviesapp.model.modelreviews.ResponseReviewsList
import id.rama.moviesapp.model.modeltrailermovie.ResponseTrailerMovie
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiServices {

    //Get List of Genre
    @GET("list")
    suspend fun getListGenre(
        @Query("api_key") apiKey: String
    ): Response<ResponseListGenreMovies>

    //Get Movie List and Detail
    @GET("list/{list_id}")
    suspend fun getMovieList(
        @Path("list_id") list_id: Int,
        @Query("api_key") apiKey: String
    ): Response<ResponseMovieList>

    //Get Movie Detail
    @GET("movie/{movie_id}")
    suspend fun getMovieDetail(
        @Path("movie_id") movie_id : Int,
        @Query("api_key") apiKey : String
    ) : Response<ResponseMovieDetail>

    //Get Review List
    @GET("movie/{movie_id}/reviews")
    suspend fun getReviewList(
        @Path("movie_id") movie_id : Int,
        @Query("api_key") apiKey : String,
        @Query("page") page : Int
    ) : Response<ResponseReviewsList>

    //Get Trailer Movie
    @GET("movie/{movie_id}/videos")
    suspend fun getTrailerMovie(
        @Path("movie_id") movie_id : Int,
        @Query("api_key") apiKet : String
    ) : Response<ResponseTrailerMovie>
}