package krzbb.com.coloringdrawable

import android.content.Context
import android.content.res.Resources
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.graphics.drawable.LayerDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.transition.Fade
import android.transition.TransitionManager
import android.view.View
import android.view.animation.AccelerateInterpolator
import android.view.animation.DecelerateInterpolator
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity()
{
    private val rnd = Random()

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        colorize_btn.setOnClickListener {
            val color = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256))
            setViewStrokeColor(this, color)
        }
    }

    private fun setViewStrokeColor(context: Context,
                                   color: Int)
    {
        val layerDrawable = ContextCompat.getDrawable(context, R.drawable.border_drawable) as LayerDrawable
        val gradientDrawable = layerDrawable.findDrawableByLayerId(R.id.border_layer) as GradientDrawable

        var borderWidth = resources.getDimension(R.dimen.border_size)
        gradientDrawable.setStroke(borderWidth.toInt(), color)

        animateScale(view_to_colorize)

        view_to_colorize.background = gradientDrawable
    }

    private fun animateScale(view: View)
    {
        view.animate()
                .scaleX(1.1f)
                .scaleY(1.1f)
                .setDuration(100)
                .setInterpolator(AccelerateInterpolator())
                .withEndAction {
                    view.animate()
                            .scaleX(1.0f)
                            .scaleY(1.0f)
                            .setDuration(90)
                            .setInterpolator(AccelerateInterpolator())
                }
                .start()
    }
}
