package spm.androidworld.all.roomDatabase.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_hilt_layout.view.*
import spm.androidworld.all.R
import spm.androidworld.all.roomDatabase.entity.User


/**
 * Created by Sibaprasad Mohanty on 27/06/21.
 * Spm Limited
 * sp.dobest@gmail.com
 */

class UserAdapter(val listUsers: ArrayList<User>) :
    RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

    class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(user: User) {
            itemView.textViewUserName.text = user.fName
            itemView.textViewUserEmail.text = user.lName
            /* Glide.with(itemView.imageViewAvatar.context)
                 .load(user.avatar)
                 .into(itemView.imageViewAvatar)*/
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder =
        UserViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_book, parent,
                false
            )
        )

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) =
        holder.bind(listUsers[position])

    override fun getItemCount(): Int = listUsers.size

}