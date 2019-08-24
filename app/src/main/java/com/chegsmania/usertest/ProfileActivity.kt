package com.chegsmania.usertest

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.GridLayoutManager
import com.chegsmania.usertest.model.Model
import com.chegsmania.usertest.utils.GridItemDecoration
import com.chegsmania.usertest.database.remote.UsApiService
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_profile.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProfileActivity : AppCompatActivity() {
    var posts = ArrayList<Model.PostObject>()
    lateinit var toolbarTitleTextView: TextView

    private val api by lazy{
        UsApiService.create()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
        val mToolbar: Toolbar = findViewById(R.id.toolbar)
        toolbarTitleTextView = mToolbar.findViewById(R.id.toolbar_title)
        setSupportActionBar(mToolbar)

        postsRecycler.adapter = PostsAdapter(posts)
        postsRecycler.layoutManager = GridLayoutManager(this, 3)
        postsRecycler.addItemDecoration(GridItemDecoration(8, 3))
        getUser()
    }

    private fun getUser(){
        val call: Call<Model.User> = api.getUserProfile()
        call.enqueue(object : Callback<Model.User>{
            override fun onFailure(call: Call<Model.User>, t: Throwable) {}

            override fun onResponse(call: Call<Model.User>, response: Response<Model.User>) {
                val user: Model.User = response.body()!!
                displayUserProfile(user)
                posts.addAll(user.posts)
                postsRecycler.adapter!!.notifyDataSetChanged()
            }
        })
    }

    private fun displayUserProfile(user: Model.User){
        val details = user.details
        user_fullname_textView.text = details.fullname
        following_count_textView.text = details.following_count.toString()
        followers_count_textView.text = details.follower_count.toString()
        likes_count_textView.text = details.post_like_count.toString()
        val username: String = getString(R.string.at_symbol) + details.username
        toolbarTitleTextView.text = username

        val imageUrl: String = details.avatar
        Picasso.get()
            .load(imageUrl)
            .placeholder(R.drawable.ic_person_black_24dp)
            .resize(96, 96)
            .centerCrop()
            .into(profile_photo)
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
