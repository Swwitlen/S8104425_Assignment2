package com.example.s8104425_assignment2.ui.dashboard

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.s8104425_assignment2.R
import com.example.s8104425_assignment2.model.SportEntity

//adapte connects sportEntity to RecycleView items
class EntityAdapter(
    private val entities: List<SportEntity>, //list of sports to display
    private val onItemClick: (SportEntity) -> Unit //callback for item click
) : RecyclerView.Adapter<EntityAdapter.EntityViewHolder>() {

    //ViewHolder holds the UI references for each item
    inner class EntityViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvSportName: TextView = itemView.findViewById(R.id.tvSportName)
        val tvPlayerCount: TextView = itemView.findViewById(R.id.tvPlayerCount)
        val tvFieldType: TextView = itemView.findViewById(R.id.tvFieldType)
    }

    //inflate layout for each item
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EntityViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_entity, parent, false)
        return EntityViewHolder(view)
    }

    //bind data to UI
    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: EntityViewHolder, position: Int) {
        val entity = entities[position]
        holder.tvSportName.text = entity.sportName
        holder.tvPlayerCount.text = "Players: ${entity.playerCount}"
        holder.tvFieldType.text = "Field: ${entity.fieldType}"

        //handle item clicked
        holder.itemView.setOnClickListener { onItemClick(entity) }
    }

    override fun getItemCount(): Int = entities.size
}