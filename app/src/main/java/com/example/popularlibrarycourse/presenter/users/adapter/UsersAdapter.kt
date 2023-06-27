package com.example.popularlibrarycourse.presenter.users.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.popularlibrarycourse.extensions.setStartDrawableCircleImageFromUri
import com.example.popularlibrarycourse.presenter.IUserListPresenter
import com.example.popularlibrarycourse.ui.IUserItemView
import com.example.popularlibrarycourse.databinding.UserBinding


class UsersAdapter(val presenter: IUserListPresenter) :
    RecyclerView.Adapter<UsersAdapter.ItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ItemViewHolder(
            UserBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        ).apply {
            itemView.setOnClickListener { presenter.itemClickListener?.invoke(this) }
        }

    override fun getItemCount() = presenter.getCount()

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) =
        presenter.bindView(holder.apply { pos = position })

    inner class ItemViewHolder(
        private val vb: UserBinding
    ) : RecyclerView.ViewHolder(vb.root),
        IUserItemView {
        override var pos = -1

        override fun setUser(login: String, avatar: String) = with(vb) {
            tvLogin.text = login
            tvLogin.setStartDrawableCircleImageFromUri(avatar)
        }
    }
}