package info.ericlin.delegation

import android.app.Activity
import android.content.res.Resources
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.TypedValue
import android.widget.TextView
import javax.inject.Inject
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

class DelegateActivity : AppCompatActivity() {

    @Inject lateinit var preferencesManager: PreferencesManager

    private lateinit var textView: TextView
    private var textSize: Float = 0f

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_delegate)

        (application as DelegateApplication).appComponent.inject(this)

        textSize = resources.getDimension(R.dimen.font_size)

        textView = findViewById(R.id.main_text)
        textView.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize)
        textView.text = getString(R.string.app_launch_count, preferencesManager.launchCount)
    }
}

//class Abitrary {
//
//    private val resource1 by CreateOnceResource()
//    private val resource2 by CreateOnceResource()
//
//    fun method() {
//        resource1.configuration
//        resource2.configuration
//    }
//}
//
//class CreateOnceResource : ReadOnlyProperty<Any, Resources> {
//
//    private var resource: Resources? = null
//
//    override operator fun getValue(thisRef: Any, property: KProperty<*>): Resources {
//        var r = resource
//        if (r == null) {
//            r = Resources()
//            resource = r
//        }
//        return r
//    }
//}