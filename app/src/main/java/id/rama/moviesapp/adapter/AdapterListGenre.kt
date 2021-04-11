package id.rama.moviesapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import id.rama.moviesapp.R
import id.rama.moviesapp.model.modelgenrelist.ModelListGenreMovies
import kotlinx.android.synthetic.main.item_list_genre_movies.view.*
import java.util.*
import kotlin.collections.ArrayList

class AdapterListGenre(var context: Context, var clickListenerGenre: OnGenreCLickListener) :
    RecyclerView.Adapter<AdapterListGenre.GenreViewHolder>(), Filterable {
    var listGenre = ArrayList<ModelListGenreMovies>()
    var filterGenreList  = ArrayList<ModelListGenreMovies>()


    inner class GenreViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val container = itemView.cv_container_genre_movies

        fun bindData(data: ModelListGenreMovies) {
            with(itemView){
                txt_title_list_genre.text = data.name
            }
        }

        fun init(item: ModelListGenreMovies, action: OnGenreCLickListener) {
            with(itemView){
                setOnClickListener {
                    action.onItemClickListener(item,absoluteAdapterPosition)
                }
            }
        }
    }

    interface OnGenreCLickListener {
        fun onItemClickListener(item: ModelListGenreMovies, position: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenreViewHolder {
        return GenreViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_list_genre_movies, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return listGenre.size
    }

    override fun onBindViewHolder(holder: GenreViewHolder, position: Int) {
        holder.container.animation = AnimationUtils.loadAnimation(context,R.anim.item_animation_fall_down)
        holder.bindData(listGenre[position])
        holder.init(listGenre[position], clickListenerGenre)
    }

    fun setData(newList: List<ModelListGenreMovies>) {
        listGenre.addAll(newList)
        notifyDataSetChanged()
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {

                val searchString = constraint.toString()
                if (searchString.isEmpty()) {
                    filterGenreList = listGenre
                } else {
                    val tempFilterList: ArrayList<ModelListGenreMovies> = ArrayList()
                    for (genreList: ModelListGenreMovies in listGenre) {
                        if (genreList.name.toLowerCase().contains(searchString)) {
                            tempFilterList.add(genreList)
                        }
                    }
                    filterGenreList = tempFilterList
                }
                val filterResult = FilterResults()
                filterResult.values = filterGenreList
                return filterResult
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                filterGenreList = results?.values as ArrayList<ModelListGenreMovies>
                notifyDataSetChanged()
            }

        }
    }
}