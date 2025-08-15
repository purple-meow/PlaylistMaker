package com.example.playlistmaker

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.appbar.MaterialToolbar

class SearchActivity : AppCompatActivity() {

    private var inputText: String = DEF_TEXT
    private lateinit var searchInput: EditText



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        val navBack = findViewById<MaterialToolbar>(R.id.tool_bar)

        navBack.setNavigationOnClickListener {
            finish()
        }
        searchInput = findViewById<EditText>(R.id.searchInput)
        val searchInputClear = findViewById<ImageView>(R.id.searchInputClear)

        searchInputClear.setOnClickListener {
            searchInput.setText("")
            val inputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
            inputMethodManager?.hideSoftInputFromWindow(searchInput.windowToken, 0)
        }

        val searchInputTextWatcher = object : TextWatcher {

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                searchInputClear.visibility = inputClearVisibility(s)
                inputText = searchInput.text.toString()
            }

            override fun afterTextChanged(p0: Editable?) {}

        }

        searchInput.addTextChangedListener(searchInputTextWatcher)

    }

    private fun inputClearVisibility(s: CharSequence?): Int {
        return if (s.isNullOrEmpty()) {
            View.GONE
        } else {
            View.VISIBLE
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(INPUT_TEXT, inputText)
        Log.d(TAG, "В onSaveInstanceState сохранен текст: $inputText")
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        inputText = savedInstanceState.getString(INPUT_TEXT, DEF_TEXT)
        searchInput.setText(inputText)
        Log.d(TAG, "В onRestoreInstanceState восстановлен текст: $inputText")
    }

    companion object {
        private const val INPUT_TEXT = "INPUT_TEXT"
        private const val DEF_TEXT = ""
        private const val TAG = "SEARCH_TEST"
    }
}