package info.ericlin.delegation

import android.content.Context

import android.content.SharedPreferences
import javax.inject.Inject
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

class IntPreference(
        private val preferences: SharedPreferences,
        private val name: String,
        private val defaultValue: Int = 0
) : ReadWriteProperty<Any, Int> {

    override fun getValue(thisRef: Any, property: KProperty<*>): Int {
        return preferences.getInt(name, defaultValue)
    }

    override fun setValue(thisRef: Any, property: KProperty<*>, value: Int) {
        preferences.edit().putInt(name, value).apply()
    }
}

fun SharedPreferences.int(name: String) = IntPreference(this, name, 0)

/**
 * Manage all preferences
 */
class PreferencesManager @Inject constructor(context: Context) {
    var launchCount by context.getSharedPreferences("myApp", Context.MODE_PRIVATE).int("count")
}
