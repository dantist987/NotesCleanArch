package com.example.notescleanarch.core

import android.app.AlertDialog
import android.content.Context
import android.widget.Toast
import androidx.fragment.app.Fragment

fun Fragment.showToast(msg: String) {
    Toast.makeText(requireContext(), msg, Toast.LENGTH_SHORT).show()
}

fun Context.alertDialog(
    title: String,
    negativeButtonTitle: String,
    positiveButtonTitle: String,
    onPositiveClick: () -> Unit
) {
    val d = AlertDialog.Builder(this)
    d.setTitle(title)
    d.setNegativeButton(negativeButtonTitle) { dialog, p1 ->
        dialog.cancel()
    }
    d.setPositiveButton(positiveButtonTitle) { dialog, p1 ->
        onPositiveClick()
        dialog.cancel()
    }
    d.create().show()
}