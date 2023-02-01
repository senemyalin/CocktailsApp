package com.senemyalin.cocktailsapp.ui.home

import android.view.View
import android.view.ViewGroup
import com.senemyalin.cocktailsapp.ui.base.BaseRecyclerViewAdapter

class DrinkRecyclerViewAdapter(private val click: (position: Int) -> Unit) : BaseRecyclerViewAdapter<HomeUiData, DrinkViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DrinkViewHolder {
//        val binding =
//            AdapterDrinkItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)

//        val binding = parent.inflateAdapterItem(AdapterDrinkItemBinding::inflate)
        return DrinkViewHolder.createFrom(parent)
    }

    override fun onBindViewHolder(holder: DrinkViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)

        holder.itemView.rootView.setOnClickListener(View.OnClickListener {
            click.invoke(position)
        })
    }
}