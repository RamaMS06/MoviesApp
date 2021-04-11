package id.rama.moviesapp.model.modelmovielist

data class ResponseMovieList(
    val created_by: String,
    val description : String,
    val favorite_count : Int,
    val id : String,
    val items : List<ModelMovieList>)