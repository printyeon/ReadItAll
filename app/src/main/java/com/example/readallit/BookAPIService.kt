package com.example.readallit

import com.google.gson.annotations.SerializedName
import org.json.JSONArray
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query

interface BookAPIService {
    /*
    // https://book.interpark.com/api/search.api?key=45B4DD127DD3DD5D8A37DCB8810027A5266CC3A45E174E40D8E0A6117008717E&query=%EC%82%BC%EA%B5%AD%EC%A7%80&output=json
    @GET("/api/search.api")
    fun getBookR(
        @Query("key") key: String,  //위에 아래 벨류값 같아야함
        @Query("query") query: String,  //위에 아래 벨류값 같아야함
        @Query("output") output: String = "json"  //위에 아래 벨류값 같아야함
    ): Call<BookSearchResponse>

*/

    //책 검색
    @GET("/api/search.api")
    fun getBook(
        @Query("key") key: String,  //위에 아래 벨류값 같아야함
        @Query("query") query: String,  //위에 아래 벨류값 같아야함
        @Query("output") output: String = "json"  //위에 아래 벨류값 같아야함
    ): Call<BookSearchResponse>

    //bestseller 받아오기
    // https://book.interpark.com/api/bestSeller.api?key=45B4DD127DD3DD5D8A37DCB8810027A5266CC3A45E174E40D8E0A6117008717E&categoryId=100&output=json
    @GET("/api/bestSeller.api")
    fun getBestSellerBooks(
        @Query("key") key: String,
        @Query("categoryId") categoryId: String,
        @Query("output") output: String = "json"
    ): Call<BestBookSearchResponse>
}

data class BookSearchResponse(
    val title: String,
    val item: List<Book>
)

data class BestBookSearchResponse(
    val title: String,
    val item: List<Book>

)

data class Book(
    val id: Int,
    val title: String,
    val description: String,
    val coverSmallUrl : String,
    val coverLargeUrl : String
)



