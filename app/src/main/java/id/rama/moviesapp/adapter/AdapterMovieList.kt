package id.rama.moviesapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import id.rama.moviesapp.R
import id.rama.moviesapp.model.modelmovielist.ModelMovieList
import id.rama.moviesapp.utils.Utilities
import kotlinx.android.synthetic.main.item_movie_list.view.*

class AdapterMovieList(var context: Context,var movieClickListener : OnMovieClickListener) : RecyclerView.Adapter<AdapterMovieList.MovieViewHolder>(){
    var listMovie = emptyList<ModelMovieList>()

    inner class MovieViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        val container = itemView.cv_container_movie_list

        fun bindData(data : ModelMovieList){
            with(itemView){
                txt_title_movie_list.text = data.title
                txt_overview_movie_list.text = data.overview
                txt_vote_average_movie_list.text = data.vote_average.toString()
                txt_original_language_movie_list.text = data.original_language
                txt_release_date_movie_list.text = data.release_date

                Glide.with(context)
                    .load(Utilities.PATH_IMAGE_MOVIE+data.poster_path)
                    .into(img_poster_movie_list)
            }
        }

        fun init(item : ModelMovieList, action : OnMovieClickListener){
            with(itemView){
                setOnClickListener {
                    action.onItemClickListener(item,absoluteAdapterPosition)
                }
            }
        }

    }

    interface OnMovieClickListener {
        fun  onItemClickListener(item : ModelMovieList, position : Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        return MovieViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_movie_list,parent,false))
    }

    override fun getItemCount(): Int {
        return listMovie.size
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.container.animation = AnimationUtils.loadAnimation(context,R.anim.item_animation_fall_down)
        holder.bindData(listMovie[position])
        holder.init(listMovie[position],movieClickListener)
    }

    fun setData(newList : List<ModelMovieList>){
        listMovie = newList
        notifyDataSetChanged()
    }





}