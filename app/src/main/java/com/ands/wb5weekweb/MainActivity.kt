package com.ands.wb5weekweb

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ands.wb5weekweb.di.App
import com.ands.wb5weekweb.di.App.Companion.INSTANCE
import com.ands.wb5weekweb.fragments.MenuFragment
import com.ands.wb5weekweb.utils.Screens
import com.ands.wb5weekweb.utils.Screens.menuScreen
import com.github.terrakok.cicerone.androidx.AppNavigator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val navigator = AppNavigator(this, R.id.fragmentContainerView)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            App.INSTANCE.router.newRootScreen(menuScreen())
        }

    }
    override fun onResumeFragments() {
        super.onResumeFragments()
        App.INSTANCE.navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        super.onPause()
        App.INSTANCE.navigatorHolder.removeNavigator()
    }


}