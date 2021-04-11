package id.rama.moviesapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import id.rama.moviesapp.model.modelreviews.ResponseReviewsList
import id.rama.moviesapp.repository.RepositoryMovies
import kotlinx.coroutines.launch
import retrofit2.Response

class ViewModelReviewsList(var repo : RepositoryMovies) : ViewModel() {
    val responseReviews : MutableLiveData<Response<ResponseReviewsList>> = MutableLiveData()

    fun getReviewsList(movieId : Int, apiKey : String, page : Int){
        viewModelScope.launch {
            val response = repo.getReviewsList(movieId, apiKey, page)
            responseReviews.value = response
        }
    }
}