package com.henry.corrossel.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat
import androidx.viewpager.widget.PagerAdapter
import com.henry.corrossel.databinding.TipContenteBinding
import com.henry.corrossel.model.Tip

class CorrosselAdapter(private val context: Context, val tips: Array<Tip>) : PagerAdapter(){

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val binding = TipContenteBinding.inflate(LayoutInflater.from(context), container, false)

        tips[position].let{
            binding.apply {
                tipTitle.text = it.title
                tipSubtitle.text = it.subtitle
                tipLogo.setImageResource(it.logo)
               root.background = ContextCompat.getDrawable(context, it.image)
            }
        }



        container.addView(binding.root)

        return binding.root
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object`
    }


    override fun getCount(): Int = tips.size

}