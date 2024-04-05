package com.carouselapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView

/**
 * Created by Chandan Jana on 04-04-2024.
 * Company name: Mindteck
 * Email: chandan.jana@mindteck.com
 */
class CarouselAdapter(private val carouselDataList: List<SliderData>) :
    RecyclerView.Adapter<CarouselAdapter.CarouselItemViewHolder>() {

    class CarouselItemViewHolder(view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarouselItemViewHolder {
        val viewHolder = LayoutInflater.from(parent.context).inflate(R.layout.carousel_item, parent, false)
        return CarouselItemViewHolder(viewHolder)
    }

    override fun onBindViewHolder(holder: CarouselItemViewHolder, position: Int) {
        val imageView = holder.itemView.findViewById<ImageView>(R.id.carouselItemImageView)
        imageView.setImageResource(carouselDataList[position].slideImage)
    }

    override fun getItemCount(): Int {
        return carouselDataList.size
    }

}