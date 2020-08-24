package io.github.quwac.how_to_use_recyclerview_2020

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import io.github.quwac.how_to_use_recyclerview_2020.ui.main.MainFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, MainFragment.newInstance())
                .commitNow()
        }
    }
}