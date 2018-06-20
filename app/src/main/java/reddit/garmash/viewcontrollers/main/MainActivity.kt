package reddit.garmash.viewcontrollers.main

import android.os.Bundle
import reddit.garmash.core.BaseActivity
import reddit.garmash.viewcontrollers.splash.view.MainScreenView
import android.arch.paging.PagedList
import android.os.Handler
import reddit.garmash.viewcontrollers.main.adapter.MainPostAdapter
import reddit.garmash.viewcontrollers.main.adapter.PostDiffCallback
import reddit.garmash.viewcontrollers.main.storage.PostsDataSource
import java.util.concurrent.Executors
import android.os.Looper
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ProgressBar
import reddit.garmash.viewcontrollers.splash.presenter.MainScreenPresenterImpl
import java.util.concurrent.Executor


class MainActivity : BaseActivity(), MainScreenView {
    override fun hideLoading() {
        (findViewById<View>(R.id.pb_main) as ProgressBar).visibility  = View.GONE
        rvTop?.visibility = View.VISIBLE
    }

    private var rvTop: RecyclerView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        rvTop = findViewById<View>(R.id.top_items) as RecyclerView
        MainScreenPresenterImpl(this).onResume()
    }

    override fun initAdapter() {
        // DataSource
        val dataSource = PostsDataSource(this)


        // PagedList
        val config = PagedList.Config.Builder()
                .setEnablePlaceholders(false)
                .setPageSize(30)
                .setPrefetchDistance(10)
                .build()



        val pagedList = PagedList.Builder(dataSource, config)
                .setNotifyExecutor(MainThreadExecutor())
                .setFetchExecutor(Executors.newSingleThreadExecutor())
                .build()


        // Adapter
        val adapter = MainPostAdapter(PostDiffCallback())
        adapter.submitList(pagedList)


        // RecyclerView
        rvTop?.layoutManager = LinearLayoutManager(this)
        rvTop?.adapter = adapter

    }


    internal inner class MainThreadExecutor : Executor {
        private val mHandler = Handler(Looper.getMainLooper())

        override fun execute(command: Runnable) {
            mHandler.post(command)
        }
    }
}
