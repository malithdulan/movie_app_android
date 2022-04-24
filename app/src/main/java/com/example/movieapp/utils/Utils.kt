package com.example.movieapp.utils

import android.content.Context
import androidx.appcompat.app.AlertDialog

object Utils{

    init {
        println("Singleton class invoked.")
    }

    fun showAlertDialog(context: Context,title: String, message: String, icon: Int, positiveAction: () -> Unit, negativeAction: () -> Unit) {
        val builder = AlertDialog.Builder(context)
        //set title for alert dialog
        builder.setTitle(title)
        //set message for alert dialog
        builder.setMessage(message)
        builder.setIcon(icon)
        //performing positive action
        builder.setPositiveButton("Yes"){ _, _ -> positiveAction() }
        //performing negative action
        builder.setNegativeButton("No"){ _, _ -> negativeAction() }
        // Create the AlertDialog
        val alertDialog: AlertDialog = builder.create()
        // Set other dialog properties
        alertDialog.setCancelable(false)
        alertDialog.show()
    }
}