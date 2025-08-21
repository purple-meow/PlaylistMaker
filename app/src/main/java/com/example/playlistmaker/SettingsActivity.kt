package com.example.playlistmaker

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.textview.MaterialTextView

class SettingsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        val navBack = findViewById<MaterialToolbar>(R.id.tool_bar)

        navBack.setNavigationOnClickListener {
            finish()
        }

        val shareLine = findViewById<MaterialTextView>(R.id.shareLine)
        shareLine.setOnClickListener {
            val shareIntent = Intent(Intent.ACTION_SEND).apply {
                type = "text/plain"
                putExtra(Intent.EXTRA_TEXT, getString(R.string.practicumLink))

            }
            startActivity(shareIntent)
        }

        val supportLine = findViewById<MaterialTextView>(R.id.supportLine)
        supportLine.setOnClickListener {
            val sendIntent = Intent(Intent.ACTION_SENDTO).apply {
                data = Uri.parse("mailto:")
                putExtra(Intent.EXTRA_EMAIL, arrayOf(getString(R.string.supportEmail)))
                putExtra(Intent.EXTRA_SUBJECT, getString(R.string.supportEmailSubj))
                putExtra(Intent.EXTRA_TEXT, getString(R.string.supportEmailText))
            }
            startActivity(sendIntent)
        }

        val agreementLine = findViewById<MaterialTextView>(R.id.agreementLine)
        agreementLine.setOnClickListener {
            val browseIntent = Intent(Intent.ACTION_VIEW)
            browseIntent.data = Uri.parse(getString(R.string.agreementLink))
            startActivity(browseIntent)
        }
    }
}