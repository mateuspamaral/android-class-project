package br.com.rogalabs.postsapi.view.main

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import br.com.rogalabs.postsapi.R
import br.com.rogalabs.postsapi.view.fragments.post.PostFragment


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
          val  postFragmenet = PostFragment()
            supportFragmentManager
                    .beginTransaction()
                    .add(R.id.container, postFragmenet, "postFragment")
                    .commit()
        }
    }
}
