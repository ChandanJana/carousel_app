package com.carouselapplication

import android.content.res.Resources
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
import com.carouselapplication.databinding.ActivityMainBinding
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    var dataList: MutableList<SliderData> = mutableListOf()
    private lateinit var listAdapter: ListAdapter

    private var reDataList = mutableListOf<ListData>()

    private val filteredList = mutableListOf<ListData>()

    private var callback: OnPageChangeCallback = object : OnPageChangeCallback() {
        override fun onPageSelected(position: Int) {
            super.onPageSelected(position)
            updateIndicators(position)
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Create dummy data
        dataList.add(
            SliderData(
                "Image1", R.drawable.image1, mutableListOf(
                    ListData("Item 1", R.drawable.image1),
                    ListData("Item 2", R.drawable.image1),
                    ListData("Item 3", R.drawable.image1),
                    ListData("Item 4", R.drawable.image1),
                    ListData("Item 5", R.drawable.image1),
                    ListData("Item 6", R.drawable.image1),
                    ListData("Item 7", R.drawable.image1),
                    ListData("Item 8", R.drawable.image1),
                    ListData("Item 9", R.drawable.image1),
                    ListData("Item 10", R.drawable.image1),
                    ListData("Item 11", R.drawable.image1),
                    ListData("Item 12", R.drawable.image1),
                    ListData("Item 13", R.drawable.image1),
                    ListData("Item 14", R.drawable.image1),
                    ListData("Item 15", R.drawable.image1),
                    ListData("Item 16", R.drawable.image1),
                    ListData("Item 17", R.drawable.image1),
                    ListData("Item 18", R.drawable.image1),
                    ListData("Item 19", R.drawable.image1),
                    ListData("Item 20", R.drawable.image1),
                    ListData("Item 21", R.drawable.image1),
                    ListData("Item 22", R.drawable.image1),
                    ListData("Item 23", R.drawable.image1),
                    ListData("Item 24", R.drawable.image1),
                    ListData("Item 25", R.drawable.image1),
                )
            )
        )
        dataList.add(
            SliderData(
                "Image2", R.drawable.image2, mutableListOf(
                    ListData("Item 26", R.drawable.image2),
                    ListData("Item 27", R.drawable.image2),
                    ListData("Item 28", R.drawable.image2),
                    ListData("Item 29", R.drawable.image2),
                    ListData("Item 30", R.drawable.image2),
                    ListData("Item 31", R.drawable.image2),
                    ListData("Item 32", R.drawable.image2),
                    ListData("Item 33", R.drawable.image2),
                    ListData("Item 34", R.drawable.image2),
                    ListData("Item 35", R.drawable.image2),
                    ListData("Item 36", R.drawable.image2),
                    ListData("Item 37", R.drawable.image2),
                    ListData("Item 38", R.drawable.image2),
                    ListData("Item 39", R.drawable.image2),
                    ListData("Item 40", R.drawable.image2),
                    ListData("Item 41", R.drawable.image2),
                    ListData("Item 42", R.drawable.image2),
                    ListData("Item 43", R.drawable.image2),
                    ListData("Item 44", R.drawable.image2),
                    ListData("Item 45", R.drawable.image2),
                    ListData("Item 46", R.drawable.image2),
                    ListData("Item 47", R.drawable.image2),
                    ListData("Item 48", R.drawable.image2),
                    ListData("Item 49", R.drawable.image2),
                    ListData("Item 50", R.drawable.image2),
                )
            )
        )
        dataList.add(
            SliderData(
                "Image3", R.drawable.image3, mutableListOf(
                    ListData("Item 75", R.drawable.image3),
                    ListData("Item 74", R.drawable.image3),
                    ListData("Item 73", R.drawable.image3),
                    ListData("Item 72", R.drawable.image3),
                    ListData("Item 71", R.drawable.image3),
                    ListData("Item 70", R.drawable.image3),
                    ListData("Item 69", R.drawable.image3),
                    ListData("Item 68", R.drawable.image3),
                    ListData("Item 67", R.drawable.image3),
                    ListData("Item 66", R.drawable.image3),
                    ListData("Item 65", R.drawable.image3),
                    ListData("Item 64", R.drawable.image3),
                    ListData("Item 63", R.drawable.image3),
                    ListData("Item 62", R.drawable.image3),
                    ListData("Item 61", R.drawable.image3),
                    ListData("Item 60", R.drawable.image3),
                    ListData("Item 59", R.drawable.image3),
                    ListData("Item 58", R.drawable.image3),
                    ListData("Item 57", R.drawable.image3),
                    ListData("Item 56", R.drawable.image3),
                    ListData("Item 55", R.drawable.image3),
                    ListData("Item 54", R.drawable.image3),
                    ListData("Item 53", R.drawable.image3),
                    ListData("Item 52", R.drawable.image3),
                    ListData("Item 51", R.drawable.image3),
                )
            )
        )

        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        listAdapter = ListAdapter(reDataList)
        binding.recyclerView.adapter = listAdapter

        val compositePageTransformer = CompositePageTransformer()
        compositePageTransformer.addTransformer(MarginPageTransformer((10 * Resources.getSystem().displayMetrics.density).toInt()))
        binding.viewPager.setPageTransformer(compositePageTransformer)
        binding.viewPager.apply {
            clipChildren = false  // No clipping the left and right items
            clipToPadding = false  // Show the viewpager in full width without clipping the padding
            offscreenPageLimit = 2  // Render the left and right items
            (getChildAt(0) as RecyclerView).overScrollMode =
                RecyclerView.OVER_SCROLL_NEVER // Remove the scroll effect
        }


        // Set Viewpager adapter
        binding.viewPager.adapter = CarouselAdapter(dataList)
        // Set initial page
        binding.viewPager.post {
            binding.viewPager.setCurrentItem(1, true)
        }
        TabLayoutMediator(binding.intoTabLayout, binding.viewPager,
            TabLayoutMediator.TabConfigurationStrategy { tab, position ->
                Log.d("TAGG", " TabLayoutMediator onCreate: $position")
            }).attach()

        binding.viewPager.registerOnPageChangeCallback(callback)

        binding.searchEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable?) {
                filterList(s.toString())
            }
        })
    }

    private fun updateIndicators(position: Int) {
        listAdapter.setData(dataList.get(position).slideList)
    }

    private fun filterList(query: String) {
        filteredList.clear()
        reDataList.forEach {
            if (it.title.contains(query, ignoreCase = true)) {
                filteredList.add(it)
            }
        }
        listAdapter.setData(filteredList)
    }

    override fun onDestroy() {
        super.onDestroy()
        binding.viewPager.unregisterOnPageChangeCallback(callback)
    }
}