package com.maestre.preferenciasapp

//settings fragment para las preferencias muy parecido al de clase

import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
import androidx.preference.PreferenceFragmentCompat
import com.maestre.gridpedia.R

class SettingsFragment: PreferenceFragmentCompat(), SharedPreferences.OnSharedPreferenceChangeListener {

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.preferences, rootKey)
    }

    override fun onResume() {
        super.onResume()
        preferenceManager.sharedPreferences?.registerOnSharedPreferenceChangeListener(this)
    }


    override fun onPause() {
        super.onPause()
        preferenceManager.sharedPreferences?.unregisterOnSharedPreferenceChangeListener (this)
    }

    override fun onSharedPreferenceChanged(sharedPreferences: SharedPreferences?, key: String?) {
        when (key) {
            "pref_checkbox" -> {
                val isNotificationEnabled = sharedPreferences?.getBoolean(key, true) ?: true

                if(isNotificationEnabled){
                    Toast.makeText(this.context,"Notificaciones activadas", Toast.LENGTH_LONG).show()
                    Log.e("mateo","notificaciones activadas")

                }else{
                    Toast.makeText(this.context,"Notificaciones desactivadas", Toast.LENGTH_LONG).show()
                    Log.e("mateo","notificaciones desactivadas")

                }

            }

            "pref_texto" -> {
                val userName = sharedPreferences?.getString(key, "Usuario") ?: "Usuario"

                //ponemos un log o un toast
                Toast.makeText(this.context, " $userName ", Toast.LENGTH_LONG).show()
                Log.e("mateo","usuario: $userName ")
            }

            "pref_checkbox_theme" -> {
                val isThemeEnabled = sharedPreferences?.getBoolean(key, true) ?: true

                if (isThemeEnabled) {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                } else {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                }
                requireActivity().recreate()
            }
        }
    }
}

