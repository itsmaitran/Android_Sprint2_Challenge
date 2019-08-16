package com.lambdaschool.shoppinglist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.shopping_item_list.view.*

class ShoppingListAdapter (val shoppingList: MutableList<Shopping>): RecyclerView.Adapter<ShoppingListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.shopping_item_list, parent, false) as View)
    }

    override fun getItemCount(): Int {
        return shoppingList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = shoppingList[position]
        holder.bindModel(item)

        holder.shoppingItemParent.setOnClickListener {
            item.isAdded = !item.isAdded

            notifyItemChanged(position)
        }
    }

    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val itemImageView = view.iv_product_image
        val itemNameView = view.tv_product_name
        val shoppingItemParent = view.ll_shopping_list

        fun bindModel(item: Shopping) {
            itemImageView.setImageResource(item.imageId)
            itemNameView.text = item.product
            if (item.isAdded)
                shoppingItemParent.setBackgroundColor(ContextCompat.getColor(itemView.context,R.color.colorAdded))
            else
                shoppingItemParent.setBackgroundColor(ContextCompat.getColor(itemView.context, R.color.colorList))
        }
    }
}