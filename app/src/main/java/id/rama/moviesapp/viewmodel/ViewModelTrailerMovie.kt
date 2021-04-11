package id.rama.moviesapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import id.rama.moviesapp.model.modeltrailermovie.ResponseTrailerMovie
import id.rama.moviesapp.repository.RepositoryMovies
import kotlinx.coroutines.launch
import retrofit2.Response

class ViewModelTrailerMovie(var repo : RepositoryMovies): ViewModel() {
    val responseTrailer : MutableLiveData<Response<ResponseTrailerMovie>> = MutableLiveData()

    fun getTrailerMovie(movieId : Int, apiKey : String){
        viewModelScope.launch {
            val response = repo.getTrailerMovie(movieId, apiKey)
            responseTrailer.value = response
        }
    }
}