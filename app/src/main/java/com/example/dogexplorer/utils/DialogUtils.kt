package com.example.dogexplorer.utils

import android.content.Context
import androidx.appcompat.app.AlertDialog
import com.example.dogexplorer.R


fun Context.showErrorDialog(errorMessage: String) {
    AlertDialog.Builder(this)
        .setTitle(R.string.dialog_title_error)
        .setMessage(errorMessage)
        .setPositiveButton(R.string.btn_ok) { dialog, _ ->
            dialog.dismiss()
        }.show()
}