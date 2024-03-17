package com.adit.kandang.ui.main

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.adit.kandang.R
import com.adit.kandang.databinding.LayoutKandangItemBinding
import com.adit.kandang.model.data.remote.ResponseKandang
import com.adit.kandang.ui.detail.DetailActivity

class KandangAdapter : ListAdapter<ResponseKandang, KandangAdapter.ViewHolder>(myDiffUtils()) {

    class ViewHolder(private val binding: LayoutKandangItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: ResponseKandang) {
            with(binding) {
                dataBind = data

                if (data.status_aktif)
                    imageKandang.setImageResource(R.drawable.image_kandang)
                else
                    imageKandang.setImageResource(R.drawable.kandang_rehat_default)

                if (data.status_aktif)
                    itemView.setOnClickListener {
                        val intent = Intent(itemView.context, DetailActivity::class.java)
                        intent.putExtra(DetailActivity.ARGS_DETAILS_, data)
                        itemView.context.startActivity(intent)
                    }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutKandangItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    private class myDiffUtils : DiffUtil.ItemCallback<ResponseKandang>() {
        override fun areItemsTheSame(oldItem: ResponseKandang, newItem: ResponseKandang): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(
            oldItem: ResponseKandang,
            newItem: ResponseKandang,
        ): Boolean {
            return oldItem == newItem
        }

    }

}