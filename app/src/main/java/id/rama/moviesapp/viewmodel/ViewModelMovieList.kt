package id.rama.moviesapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import id.rama.moviesapp.model.modelmovielist.ResponseMovieList
import id.rama.moviesapp.repository.RepositoryMovies
import kotlinx.coroutines.launch
import retrofit2.Response

class ViewModelMovieList(var repositoryMovies: RepositoryMovies) : ViewModel() {
    val responseMovie : MutableLiveData<Response<ResponseMovieList>> = MutableLiveData()

    fun getMovieList(listId : Int, apiKey : String){
        viewModelScope.launch {
            val response = repositoryMovies.getMovieList(listId,apiKey)
            responseMovie.value = response
        }
    }
}