package com.chegsmania.usertest

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.chegsmania.usertest.model.Model
import com.squareup.picasso.Picasso

class PostsAdapter (private val context: Context, private val postList: List<Model.PostObject>): RecyclerView.Adapter<PostsAdapter.PostsViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostsViewHolder {
        return PostsViewHolder(LayoutInflater.from(context).inflate(R.layout.images_list_item, parent, false))
    }

    override fun getItemCount(): Int {
        return postList.size
    }

    override fun onBindViewHolder(holder: PostsViewHolder, position: Int) {
        val imageUrl: String = postList[position].media_url
        Picasso.get()
            .load(imageUrl)
            .into(holder.postImageView)
    }

    class PostsViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val postImageView: ImageView = view.findViewById(R.id.post_images)
    }
}