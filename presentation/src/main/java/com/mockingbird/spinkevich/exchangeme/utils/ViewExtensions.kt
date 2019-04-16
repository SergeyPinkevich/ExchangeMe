package com.mockingbird.spinkevich.exchangeme.utils

import android.support.v7.widget.SearchView
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.EditText

fun View.visibleIf(condition: () -> Boolean) {
    if (condition.invoke()) {
        makeVisible()
    } else {
        makeGone()
    }
}

fun SearchView.onQueryTextChange(onQueryTextChange: (String) -> Unit) {
    this.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
        override fun onQueryTextChange(query: String): Boolean {
            onQueryTextChange.invoke(query)
            return true
        }

        override fun onQueryTextSubmit(query: String): Boolean {
            return false
        }
    })
}

fun EditText.afterTextChanged(afterTextChanged: (String) -> Unit) {

    this.addTextChangedListener(object : TextWatcher {
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

        override fun afterTextChanged(editable: Editable?) {
            afterTextChanged.invoke(editable.toString())
        }
    })
}

fun EditText.beforeTextChanged(beforeTextChanged: (CharSequence) -> Unit) {

    this.addTextChangedListener(object : TextWatcher {
        override fun beforeTextChanged(p0: CharSequence, p1: Int, p2: Int, p3: Int) {
            beforeTextChanged.invoke(p0)
        }

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

        override fun afterTextChanged(editable: Editable?) {}
    })
}

fun View.makeVisible() {
    visibility = View.VISIBLE
}

fun View.makeGone() {
    visibility = View.GONE
}