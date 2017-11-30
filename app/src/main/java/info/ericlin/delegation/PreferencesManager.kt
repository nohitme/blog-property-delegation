package info.ericlin.delegation

import android.content.Context
import javax.inject.Inject

/**
 * Manage all preferences
 */
class PreferencesManager @Inject constructor(context: Context) {

    private val preferences = context.getSharedPreferences("myApp", Context.MODE_PRIVATE)

    var launchCount: Int
        get() = preferences.getInt(PREF_KEY, 0)
        set(value) {
            preferences.edit().putInt(PREF_KEY, value).apply()
        }

    companion object {
        private const val PREF_KEY = "count"
    }
}