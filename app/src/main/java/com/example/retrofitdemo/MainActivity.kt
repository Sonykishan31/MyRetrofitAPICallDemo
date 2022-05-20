package com.example.retrofitdemo

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.retrofitdemo.adapter.MovieListAdapter
import com.example.retrofitdemo.pojo.Search
import com.vendor.sterlingvendorapp.api.ApiManager
import com.vendor.sterlingvendorapp.api.callApi
import com.vendor.sterlingvendorapp.utils.Utility

class MainActivity : AppCompatActivity() {

    val activity = this
    val movieList = ArrayList<Search>()
    lateinit var movieListAdapter: MovieListAdapter
    lateinit var recyclerView: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView)
        movieListAdapter = MovieListAdapter(movieList, activity)
        recyclerView.layoutManager = LinearLayoutManager(activity)
        recyclerView.adapter = movieListAdapter
        recyclerView.addItemDecoration(
            DividerItemDecoration(
                activity,
                DividerItemDecoration.VERTICAL
            )
        )

        callMovieListAPI()
    }

    private fun callMovieListAPI() {

        Utility.showLoading(activity)

        ApiManager.instance.moverListOmDb("movie", "671de40e", "inception").callApi(
            onSuccess = {
                Utility.closeLoading()

                if (it.Response.equals("True")) {
                    movieList.clear()
                    it.Search?.let { it1 -> movieList.addAll(it1) }
                    movieListAdapter.notifyDataSetChanged()
                } else {
                    Toast.makeText(activity, "Something went wrong", Toast.LENGTH_LONG).show()
                }

            },
            onFailure = {
                Utility.closeLoading()
                it?.printStackTrace()
            }
        )
    }
}