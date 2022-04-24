package com.example.movieapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.TextView
import com.example.movieapp.R
import com.example.movieapp.activities.MainActivity


class ConditionFragment : Fragment(R.layout.fragment_condition) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        configActionBar()
    }

    private fun configActionBar() {
        (activity as MainActivity?)?.findViewById<TextView>(R.id.action_bar_title)?.text = "Condition Fragment"
    }
}