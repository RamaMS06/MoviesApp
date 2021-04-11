package id.rama.moviesapp.viewmodelfactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import id.rama.moviesapp.repository.RepositoryMovies
import id.rama.moviesapp.viewmodel.ViewModelTrailerMovie

@Suppress("UNCHECKED_CAST")
class ViewModelFactoryTrailerMovie(private val repo : RepositoryMovies): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ViewModelTrailerMovie(repo) as T
    }

}