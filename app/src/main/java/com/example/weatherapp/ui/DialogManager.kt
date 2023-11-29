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
    fun locationSettingsDialog(context: Context, listener: Listener) {
        AlertDialog.Builder(context)
            .setTitle("Enable location?")
            .setMessage("Location disabled. Do you want to enable location?")
            .setPositiveButton("Ок") { _, _ ->
                // Уведомляет слушателя при нажатии пользователем "Ок" для включения местоположения.
                listener.onClick(null)
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
    fun searchByNameDialog(context: Context, listener: Listener) {
        val editName = EditText(context)
        AlertDialog.Builder(context)
            .setView(editName)
            .setTitle("City name:")
            .setPositiveButton("Ok") { _, _ ->
                // Уведомляет слушателя с введенным именем города при нажатии пользователем "Ок".
                listener.onClick(editName.text.toString())
            }
            .setNegativeButton("Cancel", null)
            .show()
    }

    /**
     * Интерфейс слушателя для обработки действий пользователя в диалогах.
     */
    interface Listener {
        /**
         * Вызывается при нажатии положительного действия (например, кнопки "Ок") в диалоге.
         *
         * @param name Имя города, введенное пользователем. Может быть null в определенных случаях.
         */
        fun onClick(name: String?)
    }
}