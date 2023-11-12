package com.example.utility.constant

import java.lang.annotation.RetentionPolicy

enum class UrlConfig( val value:String){
    BASE_URL("https://api.themoviedb.org/3/"),
    IMAGE_URL("https://image.tmdb.org/t/p/original")
}
enum class BundleKeys(val key:String){
    MOVIE_ID_KEY(":movieIdKey")
}