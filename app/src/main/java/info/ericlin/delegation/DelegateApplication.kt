package info.ericlin.delegation

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import javax.inject.Inject
import javax.inject.Singleton

class DelegateApplication : Application() {

    lateinit var appComponent: AppComponent

    @Inject internal lateinit var preferencesManager: PreferencesManager

    override fun onCreate() {
        super.onCreate()

        appComponent = DaggerAppComponent
                .builder()
                .application(this)
                .build()

        appComponent.inject(this)

        preferencesManager.launchCount += 1
    }

}

@Component(modules = [AppModule::class])
@Singleton
interface AppComponent {

    fun inject(application: DelegateApplication)

    fun inject(activity: DelegateActivity)

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent

    }
}