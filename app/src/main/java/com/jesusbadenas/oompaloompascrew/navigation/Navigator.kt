package com.jesusbadenas.oompaloompascrew.navigation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import com.jesusbadenas.oompaloompascrew.list.ListActivity

class Navigator {

    fun navigateToList(activity: AppCompatActivity) {
        Intent(activity, ListActivity::class.java).let { intent ->
            activity.startActivity(intent)
        }
    }
}
