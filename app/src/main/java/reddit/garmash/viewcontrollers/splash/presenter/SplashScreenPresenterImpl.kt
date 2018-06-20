package reddit.garmash.viewcontrollers.splash.presenter

import android.content.Intent

import reddit.garmash.viewcontrollers.main.MainActivity
import reddit.garmash.viewcontrollers.splash.view.SplashScreenView

class SplashScreenPresenterImpl(private val screenView: SplashScreenView) : SplashScreenPresenter {

    private var handler: android.os.Handler? = null
    private val runnable = Runnable { screenView.context.startActivity(Intent(screenView.context, MainActivity::class.java)) }
    private val START_DELAY = 3000

    override fun startMainScreen() {
        handler = android.os.Handler()
        handler?.postDelayed(runnable, START_DELAY.toLong())

    }

    override fun onResume() {
        startMainScreen()
    }

    override fun onPause() {
    }

    override fun onDestroy() {
        handler?.removeCallbacks(runnable)
    }
}
