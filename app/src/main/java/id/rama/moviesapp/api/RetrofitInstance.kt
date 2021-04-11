package id.rama.moviesapp.api

import id.rama.moviesapp.utils.Utilities
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    private val logging = HttpLoggingInterceptor()
    private val client = OkHttpClient.Builder()


    private val retrofitListGenre by lazy {
        logging.setLevel(HttpLoggingInterceptor.Level.BODY)
        client.addInterceptor(logging)
        Retrofit.Builder()
            .baseUrl(Utilities.URL_LIST_GENRE)
            .client(client.build())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private val retrofitMovieList by lazy {
        logging.setLevel(HttpLoggingInterceptor.Level.BODY)
        client.addInterceptor(logging)
        Retrofit.Builder()
            .baseUrl(Utilities.URL_MOVIE_LIST)
            .client(client.build())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private val retrofitMovieDetail by lazy {
        logging.setLevel(HttpLoggingInterceptor.Level.BODY)
        client.addInterceptor(logging)
        Retrofit.Builder()
            .baseUrl(Utilities.URL_MOVIE_DETAIL)
            .client(client.build())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private val retrofitReviewList by lazy{
        logging.setLevel(HttpLoggingInterceptor.Level.BODY)
        client.addInterceptor(logging)
        Retrofit.Builder()
            .baseUrl(Utilities.URL_REVIEW_LIST)
            .client(client.build())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private val retrofitTrailerList by lazy {
        logging.setLevel(HttpLoggingInterceptor.Level.BODY)
        client.addInterceptor(logging)
        Retrofit.Builder()
            .baseUrl(Utilities.URL_TRAILER_MOVIE)
            .client(client.build())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val apiListGenre: ApiServices by lazy {
        retrofitListGenre.create(ApiServices::class.java)
    }

    val apiMovieList: ApiServices by lazy {
        retrofitMovieList.create(ApiServices::class.java)
    }

    val apiMovieDetail: ApiServices by lazy {
        retrofitMovieDetail.create(ApiServices::class.java)
    }

    val apiReviewsList : ApiServices by lazy {
        retrofitReviewList.create(ApiServices::class.java)
    }

    val apiTrailerMovie : ApiServices by lazy {
        retrofitTrailerList.create(ApiServices::class.java)
    }
}