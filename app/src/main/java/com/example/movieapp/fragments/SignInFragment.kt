package com.example.movieapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.movieapp.R
import com.example.movieapp.activities.MainActivity


class SignInFragment : Fragment(R.layout.fragment_sign_in), View.OnClickListener {

    private lateinit var navController: NavController

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUi(view)
    }

    private fun setupUi(view: View) {
        navController = Navigation.findNavController(view)
        view.findViewById<Button>(R.id.signin).setOnClickListener(this)
        (activity as MainActivity?)?.supportActionBar?.hide()

    }

    override fun onClick(view: View?) {
        when(view?.id) {
            R.id.signin -> navController.navigate(R.id.action_signInFragment_to_homeFragment)
        }
    }
}