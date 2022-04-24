package com.example.movieapp.fragments

import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toolbar
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.movieapp.R
import com.example.movieapp.activities.MainActivity
import org.w3c.dom.Text


class HomeFragment : Fragment(R.layout.fragment_home) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        configActionBar()
    }

    private fun configActionBar() {
        (activity as MainActivity?)?.supportActionBar?.show()
        (activity as MainActivity?)?.findViewById<TextView>(R.id.action_bar_title)?.text = "Home Fragment"
    }
}