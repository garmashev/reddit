package reddit.garmash.core

import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity

open abstract class BaseActivity : AppCompatActivity(), BaseView {


    override val context: Context
        get() = this

}
