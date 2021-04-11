package id.rama.moviesapp.model.modelreviews

data class ModelReviewsList(
    val author : String,
    val author_details : AuthorDetails,
    val content : String,
    val results : String,
    val created_at : String
)

data class AuthorDetails(
    val avatar_path : String,
    val rating : Float
)