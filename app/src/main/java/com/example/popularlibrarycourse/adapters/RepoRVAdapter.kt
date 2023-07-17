package com.example.popularlibrarycourse.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.popularlibrarycourse.databinding.ItemRepoBinding
import com.example.popularlibrarycourse.presenter.IRepoListPresenter
import com.example.popularlibrarycourse.presenter.RepoItemView


class RepoRVAdapter(
    private val presenter: IRepoListPresenter
) : RecyclerView.Adapter<RepoRVAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(
            ItemRepoBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        ).apply {
            itemView.setOnClickListener { presenter.itemClickListener?.invoke(this) }
        }

    override fun getItemCount() = presenter.getCount()

    override fun onBindViewHolder(holder: RepoRVAdapter.ViewHolder, position: Int) =
        presenter.bindView(holder.apply { pos = position })

    inner class ViewHolder(private val vb: ItemRepoBinding) : RecyclerView.ViewHolder(vb.root),
        RepoItemView {
        @SuppressLint("SetTextI18n")
        override fun setTitle(text: String) {
            vb.title.text = "Repo title: $text"
        }

        override var pos = -1
    }
}