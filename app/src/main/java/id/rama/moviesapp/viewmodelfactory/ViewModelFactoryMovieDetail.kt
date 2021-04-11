package id.rama.moviesapp.viewmodelfactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import id.rama.moviesapp.repository.RepositoryMovies
import id.rama.moviesapp.viewmodel.ViewModelMovieDetail

@Suppress("UNCHECKED_CAST")
class ViewModelFactoryMovieDetail(private val repo : RepositoryMovies) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ViewModelMovieDetail(repo) as T
    }

}