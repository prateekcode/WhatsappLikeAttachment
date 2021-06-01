package com.prateekcode.whatsapplikeattachment.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.prateekcode.whatsapplikeattachment.R
import kotlinx.android.synthetic.main.item_menu.view.*

/**
 * Created by Prateek Rai on 31,May,2021
 * Github Profile https://github.com/prateekcode/
 */
class GridMenuAdapter : RecyclerView.Adapter<GridMenuAdapter.MenuViewHolder>() {

    var listener: GridMenuListener? = null

    var menus = arrayListOf(
        Menu("Documents", R.drawable.ic_document),
        Menu("Camera", R.drawable.ic_camera),
        Menu("Gallery", R.drawable.ic_gallery),
        Menu("Location", R.drawable.ic_location),
        Menu("Contact", R.drawable.ic_contact),
        Menu("Audio", R.drawable.ic_volume),
    )

    val data = ArrayList<Menu>().apply {
        addAll(menus)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MenuViewHolder {

        return MenuViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_menu,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MenuViewHolder, position: Int) {
        holder.bind(data[position], listener)
    }

    override fun getItemCount(): Int = data.size

    interface GridMenuListener {
        fun dismissPopup()
        fun onClick(position: Int, itemList: List<Menu>)
    }

    inner class MenuViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(
            menu: Menu,
            listener: GridMenuListener?
        ) {
            with(itemView) {
                tvTitle.text = menu.name
                ivIcon.setImageDrawable(ContextCompat.getDrawable(context, menu.drawable))
//                itemView.setOnClickListener {
//                    Toast.makeText(it.context, "Menu ${menu.name} clicked", Toast.LENGTH_SHORT)
//                        .show()
//                    listener?.dismissPopup()
//                }
            }
            itemView.setOnClickListener {
                listener?.onClick(adapterPosition, data)
                listener?.dismissPopup()
            }
        }

    }
    data class Menu(val name: String, @DrawableRes val drawable: Int)
}