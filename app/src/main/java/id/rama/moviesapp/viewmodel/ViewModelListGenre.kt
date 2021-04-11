package id.rama.moviesapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import id.rama.moviesapp.model.modelgenrelist.ResponseListGenreMovies
import id.rama.moviesapp.repository.RepositoryMovies
import kotlinx.coroutines.launch
import retrofit2.Response

class ViewModelListGenre(var repositoryMovies: RepositoryMovies) : ViewModel() {
    val responseGenre : MutableLiveData<Response<ResponseListGenreMovies>> = MutableLiveData()

    fun getListGenre(apiKey : String){
        viewModelScope.launch {
            val response = repositoryMovies.getListGenre(apiKey)
            responseGenre.value = response
        }
    }
}