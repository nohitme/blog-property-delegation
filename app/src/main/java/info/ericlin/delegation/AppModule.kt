package info.ericlin.delegation

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule {

    @Provides
    fun provideContext(application: Application): Context = application

    @Provides
    @Singleton
    fun providePreferencesManager(context: Context): PreferencesManager = PreferencesManager(context)

}