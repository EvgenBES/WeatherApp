package bes.tehno.weather_kt.ui.settings

import android.os.Bundle
import androidx.preference.PreferenceFragmentCompat
import bes.tehno.weather_kt.R

class SettingsFragment : PreferenceFragmentCompat() {
    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        addPreferencesFromResource(R.xml.preferences)
    }
}