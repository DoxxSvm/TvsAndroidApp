package com.doxx.tvscredit.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.doxx.tvscredit.R
import kotlinx.android.synthetic.main.item.view.*

class MobileNumberAdapter(private val onClick: (String,Int) -> Unit, private var list:ArrayList<String>):RecyclerView.Adapter<MobileNumberAdapter.ViewHolder>() {

    var selectedPosition = -1
    class ViewHolder(itemView:View):RecyclerView.ViewHolder(itemView){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item,parent,false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val mobileNumber = list.get(position)

        holder.itemView.apply {
            mobile_number_rb.text = mobileNumber
            mobile_number_rb.isChecked=position==selectedPosition
            mobile_number_rb.setOnCheckedChangeListener { compoundButton, b ->
                if(b){
                    selectedPosition=holder.adapterPosition
                    onClick(holder.itemView.mobile_number_rb.text.toString(),position)
                }
            }
        }
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    override fun getItemCount(): Int {
        return list.size
    }
}