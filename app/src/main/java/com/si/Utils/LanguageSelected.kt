package com.si.Utils

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import android.content.res.Resources
import android.util.DisplayMetrics
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import com.si.R
import com.si.Views.LoginActivity
import java.util.*


private var currentLanguage: String? = null

fun setLocale(context: Context, localeName: String) {
    if (localeName != currentLanguage) {
        currentLanguage = localeName
        saveLanguage(localeName)
        val locale = Locale(localeName)
        val res: Resources = context.resources
        val dm: DisplayMetrics = res.displayMetrics
        val conf: Configuration = res.configuration
        conf.locale = locale
        res.updateConfiguration(conf, dm)
        context.startActivity(Intent(context, LoginActivity::class.java))
    }
}

fun selectLanguage(context: Context) {
    val languages = arrayOf("pt", "es", "en")
    currentLanguage = getSavedLanguage() ?: "pt"

    val spinner: Spinner = (context as Activity).findViewById(R.id.spinner)

    val list: MutableList<String> = ArrayList()
    list.add(currentLanguage!!)
    for (i in languages.indices) {
        if (languages[i] != currentLanguage) {
            list.add(languages[i])
        }
    }
    val adapter = ArrayAdapter(context, android.R.layout.simple_spinner_item, list)
    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
    spinner.adapter = adapter
    spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
        override fun onItemSelected(adapterView: AdapterView<*>, view: View?, position: Int, l: Long) {
            val selectedLanguage = spinner.adapter.getItem(position) as String
            setLocale(context, selectedLanguage)
        }

        override fun onNothingSelected(adapterView: AdapterView<*>?) {}
    }
}

private fun getSavedLanguage(): String? {
    return currentLanguage
}

private fun saveLanguage(language: String) {
    currentLanguage = language
}

