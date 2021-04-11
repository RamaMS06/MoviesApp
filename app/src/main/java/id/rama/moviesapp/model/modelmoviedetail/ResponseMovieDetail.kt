package id.rama.moviesapp.model.modelmoviedetail

data class ResponseMovieDetail(
    val adult : Boolean,
    val backdrop_path : String,
    val genres : List<ModelGenresMovieDetail>? = null,
    val id: Int,
    val overview : String,
    val popularity : Float,
    val poster_path : String,
    val production_companies : List<ModelCompaniesMovieDetail>? = null,
    val spoken_languages : List<ModelLanguageMovieDetail>? =  null,
    val release_date : String,
    val tagline : String,
    val title : String,
    val vote_average : Float
)

