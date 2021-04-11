package id.rama.moviesapp.viewmodelfactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import id.rama.moviesapp.repository.RepositoryMovies
import id.rama.moviesapp.viewmodel.ViewModelListGenre

@Suppress("UNCHECKED_CAST")
class ViewModelFactoryListGenre(private val repositoryMovies: RepositoryMovies) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ViewModelListGenre(repositoryMovies) as T
    }
}