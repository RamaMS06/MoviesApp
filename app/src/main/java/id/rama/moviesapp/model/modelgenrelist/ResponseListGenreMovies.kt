package id.rama.moviesapp.model.modelgenrelist

import com.google.gson.annotations.SerializedName

data class ResponseListGenreMovies(
    @SerializedName("genres")
    val genres: List<ModelListGenreMovies>
)