package bes.tehno.weather_kt.ui.current

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import bes.tehno.weather_kt.R
import bes.tehno.weather_kt.domain.entity.WeatherForHour
import kotlinx.android.synthetic.main.item_weather.view.*

class WeatherRecyclerAdapter(private var itemList: MutableList<WeatherForHour> = mutableListOf()) :
    RecyclerView.Adapter<WeatherRecyclerAdapter.BaseViewHolder>() {

    fun setItem(items: List<WeatherForHour>) {
        this.itemList = items as MutableList<WeatherForHour>
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        return BaseViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_weather, parent, false))
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int)  = holder.let {
        it.onBind(position)
    }

    inner class BaseViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        fun onBind(position: Int) {
            val (time, icon, temp) = itemList[position]
           inflateData(time, icon, temp)
        }


        @SuppressLint("SetTextI18n")
        private fun inflateData(date: String, icon: String, temp: Int) {
            val resourceId =
                itemView.context?.resources?.getIdentifier("ic_$icon", "drawable", itemView.context.packageName)
            if (resourceId != null) itemView.imageWeather.setImageResource(resourceId)
            itemView.tvTime.text = date
            itemView.tvTemp.text = "$temp\u2103"
        }
    }

}