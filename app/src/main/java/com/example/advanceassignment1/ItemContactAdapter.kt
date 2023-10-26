package com.example.advanceassignment1

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.advanceassignment1.databinding.ItemContactBinding

class ItemContactAdapter(var contacts: List<Contact>) :
    RecyclerView.Adapter<ItemContactAdapter.ViewHolder>() {

    inner class ViewHolder(private val binding: ItemContactBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(contact: Contact){
            binding.contact = contact
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemContactAdapter.ViewHolder {
        val binding = ItemContactBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ItemContactAdapter.ViewHolder, position: Int) {
        val contact = contacts[position]
        holder.bind(contact)
    }

    override fun getItemCount(): Int {
        return contacts.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateList(newList: List<Contact>) {
        contacts = newList
        notifyDataSetChanged()
    }
}