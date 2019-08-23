package com.chegsmania.usertest.model

object Model {

    data class User(val details: DetailsObject, val posts: ArrayList<PostObject>)

    data class DetailsObject(val id: String,
                             val fullname: String,
                             val username: String,
                             val avatar: String,
                             val follower_count: Int,
                             val following_count: Int,
                             val post_like_count: Int)

    data class PostObject(val id: String,
                           val media_url: String)
}