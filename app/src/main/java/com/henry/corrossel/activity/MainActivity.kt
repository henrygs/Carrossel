package com.henry.corrossel.activity

import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.WindowCompat
import androidx.core.view.size
import androidx.viewpager.widget.ViewPager
import com.henry.corrossel.R
import com.henry.corrossel.adapter.CorrosselAdapter
import com.henry.corrossel.databinding.ActivityMainBinding
import com.henry.corrossel.model.Tip
import kotlin.math.abs

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
        setContentView(binding.root)

        initAdapter()
        addDots(tips.size)


    }

        private val tips = arrayOf(
            Tip("Don’t let the noise of others’ opinions drown out your own inner voice.",
                "You can’t connect the dots looking forward; you can only connect them looking" +
                        " backwards. So you have to trust that the dots will somehow connect in your future.",
                R.drawable.house,
                R.drawable.backgroundblue),
            Tip("Stay hungry. Stay foolish.",
                "Be a yardstick of quality. Some people aren’t used to an environment where excellence is expected.",
                R.drawable.woman,
                R.drawable.backgroundpink),
            Tip("We’re here to put a dent in the universe. Otherwise why else even be here?",
                "You can't just ask customers what they want and then try to give that to them." +
                        " By the time you get it built, they'll want something new.",
                R.drawable.sofa,
                R.drawable.backgroundorange),

            )

    private fun initAdapter() {
        with(binding.viewPager) {
            adapter = CorrosselAdapter(context, tips)

            setPageTransformer(true){ page, position ->
                page.alpha = 1 - abs(position)
                page.translationX = - position * page.width
            }

            addOnPageChangeListener(object : ViewPager.OnPageChangeListener{
                override fun onPageScrolled(
                    position: Int,
                    positionOffset: Float,
                    positionOffsetPixels: Int
                ) {
                }

                override fun onPageSelected(position: Int) {
                    addDots(tips.size, position)
                }

                override fun onPageScrollStateChanged(state: Int) {
                }
            })
        }
        binding.apply {

            next.setOnClickListener {
                if(viewPager.currentItem == viewPager.size){
                    Toast.makeText(baseContext, "Abrir tela de home", Toast.LENGTH_SHORT).show()
                }
                viewPager.setCurrentItem(viewPager.currentItem + 1, true)
            }
            back.setOnClickListener {
                viewPager.setCurrentItem(viewPager.currentItem - 1, true)
            }
        }
    }

    private fun addDots(size: Int, position: Int = 0){
        binding.dots.removeAllViews()
        Array(size){
            val textView = TextView(baseContext).apply {
                text = getText(R.string.dotted)
                textSize = 35f
                setTextColor(
                    if(position == it) ContextCompat.getColor(baseContext, android.R.color.white)
                    else ContextCompat.getColor(baseContext, android.R.color.darker_gray)
                )
            }
            binding.dots.addView(textView)
        }
    }
}