package com.chegsmania.usertest

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.chegsmania.usertest.model.Model
import com.chegsmania.usertest.utils.UsApiService
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProfileActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: PostsAdapter
    private lateinit var followingCount: TextView
    private lateinit var followerCount: TextView
    private lateinit var likesCount: TextView
    private lateinit var fullname: TextView
    private lateinit var image: ImageView
    var posts = ArrayList<Model.PostObject>()

    private val api by lazy{
        UsApiService.create()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        followingCount = findViewById(R.id.following_count_textView)
        followerCount = findViewById(R.id.followers_count_textView)
        likesCount = findViewById(R.id.likes_count_textView)
        fullname = findViewById(R.id.user_fullname_textView)
        image = findViewById(R.id.profile_photo)

        recyclerView = findViewById(R.id.postsRecycler)
        recyclerView.layoutManager = GridLayoutManager(this, 3)
        adapter = PostsAdapter(this, posts)
        getUser()
    }

    private fun getUser(){
        val call: Call<Model.User> = api.getUserProfile()
        call.enqueue(object : Callback<Model.User>{
            override fun onFailure(call: Call<Model.User>, t: Throwable) {

            }

            override fun onResponse(call: Call<Model.User>, response: Response<Model.User>) {
                val user: Model.User = response.body()!!
                displayUserProfile(user)
                adapter.notifyDataSetChanged()
                posts = user.posts
            }

        })
    }

    private fun displayUserProfile(user: Model.User){
        val details = user.details
        fullname.text = details.fullname
        followingCount.text = details.following_count.toString()
        followerCount.text = details.follower_count.toString()
        likesCount.text = details.post_like_count.toString()
        val imageUrl: String = details.avatar
        Picasso.get()
            .load(imageUrl)
            .into(image)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.profile_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId){
            R.id.settings_menu -> run {
                intent = Intent(this, SettingsActivity::class.java)
                startActivity(intent)
                true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
