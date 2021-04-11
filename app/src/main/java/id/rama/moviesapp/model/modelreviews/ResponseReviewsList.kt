package id.rama.moviesapp.model.modelreviews

data class ResponseReviewsList(
    val id: Int,
    val page: Int,
    val results: ArrayList<ModelReviewsList>,
    val total_pages: Int,
    val total_results: Int
)