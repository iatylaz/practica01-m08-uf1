package com.example.newrecycler

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.layout_item_view.view.*

class CarAdapter(private val mContext: Context, var items : ArrayList<Marcas>, var clickListner: OnMarcaItemClickListner) : RecyclerView.Adapter<CarViewHolder>(){
    override fun getItemCount(): Int {
        return items.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarViewHolder {
        lateinit var carViewHolder : CarViewHolder
        carViewHolder = CarViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.layout_item_view,parent,false ))
        return carViewHolder
    }

    override fun onBindViewHolder(holder: CarViewHolder, position: Int) {

        holder.marcaName?.text =  items.get(position).empresa_organitzadora
        holder.marcaDescription?.text = items.get(position).descripcio

        Glide.with(mContext).load(items[position].logo).placeholder(R.drawable.ic_baseline_downloading_24).error(R.drawable.ic_baseline_error_outline_24).into(holder.logo)
        //Glide.with(mContext).load(items[position].logo).into(holder.logo)
        holder.initialize(items.get(position),clickListner)

    }
}

class CarViewHolder(itemView:View) : RecyclerView.ViewHolder(itemView){
    var marcaName = itemView.carname
    var marcaDescription = itemView.cardescription
    var logo = itemView.carlogo



    fun initialize(item: Marcas, action:OnMarcaItemClickListner){
        marcaName.text = item.empresa_organitzadora
        marcaDescription.text = item.descripcio


        itemView.setOnClickListener{
            action.onItemClick(item,adapterPosition)
        }

    }

}

interface OnMarcaItemClickListner{
    fun onItemClick(item: Marcas, position: Int)
}