package com.example.weatherapp.ui

import android.app.AlertDialog
import android.content.Context
import android.widget.EditText

object DialogManager {
    fun locationSettingsDialog(context: Context, listener: Listener) {
        AlertDialog.Builder(context)
            .setTitle("Enable location?")
            .setMessage("Location disabled. Do you want to enable location?")
            .setPositiveButton("Ok") { _, _ ->
                listener.onClick(null)
            }
            .setNegativeButton("Cancel", null)
            .show()
    }

    fun searchByNameDialog(context: Context, listener: Listener) {
        val editName = EditText(context)
        AlertDialog.Builder(context)
            .setView(editName)
            .setTitle("City name:")
            .setPositiveButton("Ok") { _, _ ->
                listener.onClick(editName.text.toString())
            }
            .setNegativeButton("Cancel", null)
            .show()
    }

    interface Listener {
        fun onClick(name: String?)
    }
}