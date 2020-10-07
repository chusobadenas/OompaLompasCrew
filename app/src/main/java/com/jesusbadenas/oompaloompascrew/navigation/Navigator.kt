package com.jesusbadenas.oompaloompascrew.navigation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.jesusbadenas.oompaloompascrew.list.ListActivity
import com.jesusbadenas.oompaloompascrew.list.ListFragmentDirections

class Navigator {

    fun navigateToList(activity: AppCompatActivity) {
        Intent(activity, ListActivity::class.java).let { intent ->
            activity.startActivity(intent)
        }
    }

    fun navigateToDetail(fragment: Fragment, id: Int) {
        val directions = ListFragmentDirections.navigateToDetailFragment(id)
        fragment.findNavController().navigate(directions)
    }
}
