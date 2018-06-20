package reddit.garmash.viewcontrollers.splash

import android.os.Bundle
import android.view.Window
import android.view.WindowManager

import reddit.garmash.core.BaseActivity
import reddit.garmash.viewcontrollers.main.R
import reddit.garmash.viewcontrollers.splash.presenter.SplashScreenPresenterImpl
import reddit.garmash.viewcontrollers.splash.view.SplashScreenView

class SplashScreen : BaseActivity(), SplashScreenView {

    var splashPresenter :SplashScreenPresenterImpl ? = null;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.splash_screen)
        splashPresenter = SplashScreenPresenterImpl(this);
    }

    override fun onResume() {
        super.onResume()
        splashPresenter?.onResume()
    }

    override fun onDestroy() {
        super.onDestroy()
        splashPresenter?.onDestroy()

    }

}
