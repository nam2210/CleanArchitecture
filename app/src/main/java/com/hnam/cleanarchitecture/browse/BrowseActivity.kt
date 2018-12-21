package com.hnam.cleanarchitecture.browse

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import com.hnam.cleanarchitecture.R
import kotlinx.android.synthetic.main.activity_browse.*

/**
 * Created by nampham on 12/21/18.
 */
class BrowseActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_browse)
        setupBrowseRecycler()
    }

    private fun setupBrowseRecycler(){
        recycler_projects.layoutManager = LinearLayoutManager(this)
    }
}