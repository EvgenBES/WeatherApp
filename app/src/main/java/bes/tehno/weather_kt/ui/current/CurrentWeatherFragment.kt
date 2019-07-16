package bes.tehno.weather_kt.ui.current

import android.os.Bundle
import android.text.Html
import android.util.Log
import android.widget.TextView
import androidx.core.text.HtmlCompat
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.viewpager.widget.ViewPager
import bes.tehno.weather_kt.R
import bes.tehno.weather_kt.databinding.CurrentWeatherFragmentBinding
import bes.tehno.weather_kt.domain.entity.WeatherDay
import bes.tehno.weather_kt.ui.base.BaseMvvmFragment
import kotlinx.android.synthetic.main.current_weather_fragment.*

class CurrentWeatherFragment: BaseMvvmFragment<CurrentWeatherModel, CurrentWeatherFragmentBinding>() {

    override fun provideViewModel(): CurrentWeatherModel = CurrentWeatherModel()
    override fun provideLayoutId(): Int = R.layout.current_weather_fragment
    private var sumDots: Int = 0
    private var lastPosition = 0

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val slidePagerAdapter = SlidePagerAdapter(context)
        binding.mViewPager.adapter = slidePagerAdapter
        addPageChangeListener(binding.mViewPager)

        initObserver(viewModel.getWeather(), slidePagerAdapter)
    }

    private fun initObserver(
        weather: MutableLiveData<List<WeatherDay>>,
        slidePagerAdapter: SlidePagerAdapter
    ) {
        weather.observe(this, Observer {
            if (it.isNotEmpty()) {
                sumDots = it.size
                val list = it as List<WeatherDay>
                slidePagerAdapter.setItem(list)
                addDotsIndicator(sumDots, lastPosition)
            }
        })
    }

    private fun addDotsIndicator(size: Int, position: Int) {
        val mDots = arrayOfNulls<TextView>(size)
        mLostLayout.removeAllViews()

        for (i in mDots.indices) {
            mDots[i] = TextView(context?.applicationContext)
            mDots[i]?.text = Html.fromHtml("&#8226;")
            mDots[i]?.textSize = 36f
            mDots[i]?.setTextColor(resources.getColor(R.color.bottom_menu))

            mLostLayout.addView(mDots[i])
        }

        if (mDots.size > 0) {
            mDots[position]?.setTextColor(resources.getColor(R.color.colorPrimary))
            mDots[position]?.textSize = 48f
        }
    }

    private fun addPageChangeListener(mViewPager: ViewPager) {
        mViewPager.addOnPageChangeListener(object: ViewPager.OnPageChangeListener{
            override fun onPageScrollStateChanged(state: Int) { }

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) { }

            override fun onPageSelected(position: Int) {
                addDotsIndicator(sumDots, position)
                lastPosition = position
            }

        })
    }
}