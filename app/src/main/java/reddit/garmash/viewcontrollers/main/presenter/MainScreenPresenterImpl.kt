package reddit.garmash.viewcontrollers.splash.presenter

import android.content.Intent

import reddit.garmash.viewcontrollers.main.MainActivity
import reddit.garmash.viewcontrollers.splash.view.MainScreenView
import reddit.garmash.viewcontrollers.splash.view.SplashScreenView

class MainScreenPresenterImpl(private val screenView: MainScreenView) : MainScreenPresenter {


    override fun onResume() {
        screenView.initAdapter()
    }

    override fun onPause() {
    }

    override fun onDestroy() {

    }
}
