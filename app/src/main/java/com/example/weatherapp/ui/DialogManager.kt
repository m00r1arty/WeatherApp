package com.example.weatherapp.ui

import android.app.AlertDialog
import android.content.Context
import android.widget.EditText
import com.example.weatherapp.R

object DialogManager {

    /**
     * Отображает диалог для включения служб местоположения.
     *
     * @param context Контекст, в котором следует отобразить диалог.
     * @param listener Слушатель для обработки действий пользователя в диалоге.
     */
    fun locationSettingsDialog(context: Context, onClick: (String?) -> Unit) {
        AlertDialog.Builder(context)
            .setTitle(context.getString(R.string.enable_location))
            .setMessage(context.getString(R.string.location_disabled))
            .setPositiveButton(context.getString(R.string.ok)) { _, _ ->
                onClick(null)
            }
            .setNegativeButton(context.getString(R.string.cancel), null)
            .show()
    }

    /**
     * Отображает диалог для поиска города по имени.
     *
     * @param context Контекст, в котором следует отобразить диалог.
     * @param listener Слушатель для обработки действий пользователя в диалоге.
     */
    fun searchByNameDialog(context: Context, onClick: (String?) -> Unit) {
        val editName = EditText(context)
        AlertDialog.Builder(context)
            .setView(editName)
            .setTitle(context.getString(R.string.location))
            .setPositiveButton(context.getString(R.string.ok)) { _, _ ->
                onClick(editName.text.toString())
            }
            .setNegativeButton(context.getString(R.string.cancel), null)
            .show()
    }
}