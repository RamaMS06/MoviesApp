package id.rama.moviesapp.viewmodelfactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import id.rama.moviesapp.repository.RepositoryMovies
import id.rama.moviesapp.viewmodel.ViewModelReviewsList

@Suppress("UNCHECKED_CAST")
class ViewModelFactoryReviewsList(var repo : RepositoryMovies) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ViewModelReviewsList(repo) as T
    }
}