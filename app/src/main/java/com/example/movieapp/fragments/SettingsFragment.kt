package com.example.movieapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.movieapp.R
import com.example.movieapp.activities.MainActivity

class SettingsFragment : Fragment(R.layout.fragment_settings) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        configActionBar()
    }

    private fun configActionBar() {
        (activity as MainActivity?)?.findViewById<TextView>(R.id.action_bar_title)?.text = "Settings Fragment"
    }
}