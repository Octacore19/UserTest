package com.chegsmania.usertest.model

object User {
    data class DetailsObject(val id: String,
                             val fullname: String,
                             val username: String,
                             val followerCount: Int,
                             val followingCount: Int,
                             val postLikeCount: Int)
    
    data class PostsObject(val id: String,
                           val imageId: String)
}