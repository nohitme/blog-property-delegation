package info.ericlin.delegation

import android.app.Activity
import android.app.Fragment
import android.os.Bundle
import android.support.annotation.DimenRes
import android.support.annotation.IdRes
import android.support.v7.app.AppCompatActivity
import android.util.TypedValue
import android.view.View
import android.widget.TextView
import javax.inject.Inject

class DelegateActivity : AppCompatActivity() {

    @Inject lateinit var preferencesManager: PreferencesManager

    private val textView by bindView<TextView>(R.id.main_text)
    private val textSize by bindDimension(R.dimen.font_size)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_delegate)

        (application as DelegateApplication).appComponent.inject(this)

        textView.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize)
        textView.text = getString(R.string.app_launch_count, preferencesManager.launchCount)
    }
}

fun Activity.bindDimension(@DimenRes id: Int) = lazy { resources.getDimension(id) }

fun <T : View> Activity.bindView(@IdRes id: Int) = lazy(LazyThreadSafetyMode.NONE) { findViewById<T>(id) }
fun <T : View> Fragment.bindView(@IdRes id: Int) = lazy(LazyThreadSafetyMode.NONE) { view!!.findViewById<T>(id) }

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