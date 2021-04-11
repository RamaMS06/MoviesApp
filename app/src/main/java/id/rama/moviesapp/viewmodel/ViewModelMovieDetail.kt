package id.rama.moviesapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import id.rama.moviesapp.model.modelmoviedetail.ResponseMovieDetail
import id.rama.moviesapp.repository.RepositoryMovies
import kotlinx.coroutines.launch
import retrofit2.Response

class ViewModelMovieDetail(var repo : RepositoryMovies) : ViewModel() {
    val responseMovies : MutableLiveData<Response<ResponseMovieDetail>> = MutableLiveData()

    fun getMovieDetail(movieId : Int, apiKey : String){
        viewModelScope.launch {
            val response = repo.getMovieDetail(movieId, apiKey)
            responseMovies.value = response
        }
    }
}