package id.rama.moviesapp.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import id.rama.moviesapp.R
import id.rama.moviesapp.model.modelreviews.ModelReviewsList
import id.rama.moviesapp.utils.Utilities
import kotlinx.android.synthetic.main.item_list_review.view.*
import java.text.SimpleDateFormat
import java.time.Instant

@SuppressLint("SimpleDateFormat")
class AdapterReviewsList(var context: Context, var reviewsClickListener: OnReviewsClickListener) : RecyclerView.Adapter<AdapterReviewsList.ReviewsViewHolder>(){
    private var listReviews = ArrayList<ModelReviewsList>()

    inner class ReviewsViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        val container = itemView.cv_container_review_list
        @SuppressLint("NewApi")
        fun bindData(data : ModelReviewsList){
            with(itemView){
                txt_author_list_preview.text = data.author
                txt_rating_list_preview.text = data.author_details.rating.toString()
                txt_created_at_list_preview.text = Instant.parse(data.created_at).toString()
                txt_content_list_preview.text = data.content
                Glide.with(context)
                    .load(Utilities.PATH_IMAGE_MOVIE+data.author_details.avatar_path)
                    .into(img_avatar_path_review_list)
            }

        }

        fun init(item : ModelReviewsList, action : OnReviewsClickListener){
            with(itemView){
                setOnClickListener {
                    action.onItemClickListener(item,absoluteAdapterPosition)
                }
            }
        }
    }

    interface OnReviewsClickListener {
        fun onItemClickListener(item: ModelReviewsList, position: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReviewsViewHolder {
        return ReviewsViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_list_review,parent,false))
    }

    override fun getItemCount(): Int = listReviews.size

    override fun onBindViewHolder(holder: ReviewsViewHolder, position: Int) {
        holder.container.animation = AnimationUtils.loadAnimation(context,R.anim.item_animation_fall_down)
        holder.bindData(listReviews[position])
        holder.init(listReviews[position],reviewsClickListener)
    }

    fun setData(newList : ArrayList<ModelReviewsList>){
        listReviews.addAll(newList)
        notifyDataSetChanged()
    }

    fun clear(){
        listReviews.clear()
        notifyDataSetChanged()
    }
}