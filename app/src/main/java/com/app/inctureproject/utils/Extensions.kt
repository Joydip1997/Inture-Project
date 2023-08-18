package com.app.inctureproject.utils

import android.app.Activity
import android.content.Context
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

fun <T> Flow<T>.collectIn(
    lifecycleOwner: LifecycleOwner,
    onEachValue: (T) -> Unit
) {
    lifecycleOwner.lifecycleScope.launch {
        collect { value ->
            onEachValue(value)
        }
    }
}

fun Context.toast(message : String?){
    Toast.makeText(this,message ?: "Something Went Wrong",Toast.LENGTH_SHORT).show()
}

fun Activity.hideKeyboard() {
    val inputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    val currentFocusedView = currentFocus
    currentFocusedView?.let {
        inputMethodManager.hideSoftInputFromWindow(it.windowToken, InputMethodManager.HIDE_NOT_ALWAYS)
    }
}


