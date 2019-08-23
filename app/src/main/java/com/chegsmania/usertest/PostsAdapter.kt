package com.chegsmania.usertest

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.images_list_item.view.*

class PostsAdapter (val context: Context): RecyclerView.Adapter<PostsAdapter.PostsViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostsViewHolder {
        return PostsViewHolder(LayoutInflater.from(context).inflate(R.layout.images_list_item, parent, false))
    }

    override fun getItemCount(): Int {
        return 0
    }

    override fun onBindViewHolder(holder: PostsViewHolder, position: Int) {

    }

    class PostsViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val postImage = view.post_images
    }
}