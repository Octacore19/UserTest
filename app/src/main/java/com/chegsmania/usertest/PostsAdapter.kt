package com.chegsmania.usertest

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.chegsmania.usertest.model.Model
import com.squareup.picasso.Picasso

class PostsAdapter (private val postList: List<Model.PostObject>): RecyclerView.Adapter<PostsAdapter.PostsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostsViewHolder {
        return PostsViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.images_list_item, parent, false))
    }

    override fun getItemCount(): Int {
        return postList.size
    }

    override fun onBindViewHolder(holder: PostsViewHolder, position: Int) {
        holder.bind(postList[position])
    }

    class PostsViewHolder(view: View) : RecyclerView.ViewHolder(view){
        private var imageView: ImageView = view.findViewById(R.id.post_photo)

        fun bind(post: Model.PostObject){
            val imageUrl = post.media_url
            Picasso.get().load(imageUrl).into(imageView)
        }
    }
}