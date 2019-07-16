package bes.tehno.weather_kt.ui.current

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager.widget.PagerAdapter
import bes.tehno.weather_kt.domain.entity.WeatherDay
import kotlinx.android.synthetic.main.item_pager.view.*


class SlidePagerAdapter(private var context: Context?,
                        private var itemList: MutableList<WeatherDay> = mutableListOf())
    : PagerAdapter() {

    private lateinit var layoutInflater: LayoutInflater

    fun setItem(items: List<WeatherDay>) {
        this.itemList = items as MutableList<WeatherDay>
        notifyDataSetChanged()
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object` as ConstraintLayout
    }

    override fun getCount(): Int {
        return itemList.size
    }

    override fun getItemPosition(`object`: Any): Int {
        return POSITION_NONE
    }

    @SuppressLint("SetTextI18n")
    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        layoutInflater = context?.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view: View = layoutInflater.inflate(bes.tehno.weather_kt.R.layout.item_pager, container, false)

        view.recycler.layoutManager = LinearLayoutManager(
            context?.applicationContext,
        LinearLayoutManager.HORIZONTAL,
        false)
        view.recycler.setHasFixedSize(true)
        view.recycler.adapter = WeatherRecyclerAdapter(itemList[position].weatherDayList)

        view.cityName.text = itemList[position].city
        view.tvDate.text = itemList[position].date
        view.tvTemp.text  = "${itemList[position].temp}\u2103"
        view.tvTypeWeath.text =
            "${itemList[position].typeWeather.substring(0,1).toUpperCase()}${itemList[position].typeWeather.substring(1)}"
        view.tvWindSpeed.text = "${itemList[position].windSpeed} м/сек."
        view.tvHumidity.text = "${itemList[position].humidity}%"

        val resourceId = context?.resources?.getIdentifier("ic_${itemList[position].typeWeatherIcon}", "drawable", context?.packageName)
        if (resourceId != null) view.imageViewWeather.setImageResource(resourceId)


        container.addView(view)
        return view
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as ConstraintLayout)
    }
}