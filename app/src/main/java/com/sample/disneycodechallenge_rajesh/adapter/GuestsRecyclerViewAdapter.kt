package com.sample.disneycodechallenge_rajesh.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sample.data.models.GuestListItem
import com.sample.disneycodechallenge_rajesh.databinding.LayoutSelectGuestRecyclerViewItemBinding
import com.sample.disneycodechallenge_rajesh.listener.OnRecyclerViewItemClick

class GuestsRecyclerViewAdapter(private val onRecyclerViewItemClick: OnRecyclerViewItemClick) :
    RecyclerView.Adapter<GuestsRecyclerViewAdapter.SelectGuestViewHolder>() {

    private var guestListItems = mutableListOf<GuestListItem>()

    inner class SelectGuestViewHolder(val binding: LayoutSelectGuestRecyclerViewItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SelectGuestViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding =
            LayoutSelectGuestRecyclerViewItemBinding.inflate(inflater, parent, false)
        return SelectGuestViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SelectGuestViewHolder, position: Int) {
        with(holder.binding) {
            val guestListItem = guestListItems[position]
            chkGuest.text = guestListItem.name
            chkGuest.isChecked = guestListItem.isChecked

            chkGuest.setOnCheckedChangeListener { _, b ->
                guestListItem.isChecked = b
                guestListItem.haveReservation?.let {
                    guestListItem.haveReservation = b
                }
                guestListItem.needReservation?.let {
                    guestListItem.needReservation = b
                }
                onRecyclerViewItemClick.onItemClick(position)
            }
        }
    }

    override fun getItemCount() = guestListItems.size

    fun setData(guestListItems: List<GuestListItem>) {
        this.guestListItems.clear()
        this.guestListItems.addAll(guestListItems)
        notifyDataSetChanged()
    }

    fun getUpdatedData() = this.guestListItems
}