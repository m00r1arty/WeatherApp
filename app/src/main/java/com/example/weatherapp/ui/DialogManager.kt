package com.example.weatherapp.ui

import android.app.AlertDialog
import android.content.Context
import android.widget.EditText

object DialogManager {

    /**
     * Отображает диалог для включения служб местоположения.
     *
     * @param context Контекст, в котором следует отобразить диалог.
     * @param listener Слушатель для обработки действий пользователя в диалоге.
     */
    fun locationSettingsDialog(context: Context, onClick: (String?) -> Unit) {
        AlertDialog.Builder(context)
            .setTitle("Enable location?")
            .setMessage("Location disabled. Do you want to enable location?")
            .setPositiveButton("Ок") { _, _ ->
                // Уведомляет слушателя при нажатии пользователем "Ок" для включения местоположения.
                onClick(null)
            }
            .setNegativeButton("Cancel", null)
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
            .setTitle("City name:")
            .setPositiveButton("Ok") { _, _ ->
                // Уведомляет слушателя с введенным именем города при нажатии пользователем "Ок".
                onClick(editName.text.toString())
            }
            .setNegativeButton("Cancel", null)
            .show()
    }
}