package ifrs.ibiruba.httpbooks

import android.content.Context
import android.net.ConnectivityManager
import com.google.gson.Gson
import okhttp3.OkHttpClient
import okhttp3.Request
import java.util.concurrent.TimeUnit

object BookHttp {
    val BOOK_JSON_URL = "https://raw.githubusercontent.com/nglauber/dominando_android3/master/livros_novatec.json"

    fun hasConnection(ctx: Context): Boolean{
        val cm = ctx.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val info = cm.activeNetworkInfo
        return info != null && info.isConnected
    }

    fun loadBooksGson(): List<Book>?{
        val client = OkHttpClient.Builder()
            .readTimeout(5, TimeUnit.SECONDS)
            .connectTimeout(10,TimeUnit.SECONDS)
            .build()
        val request = Request.Builder()
            .url(BOOK_JSON_URL)
            .build()
        try {
            val response = client.newCall(request).execute()
            val json = response.body()?.string()
            val gson = Gson()
            val publisher = gson.fromJson<Publisher>(json, Publisher::class.java)
            return  publisher.categories
                .flatMap { category ->
                    category.books.forEach { book ->
                      book.category = category.name
                    }
                    category.books
                }
        }catch (e: Exception){
            e.printStackTrace()
        }
        return null
    }
}