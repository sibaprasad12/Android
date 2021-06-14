package spm.architecture.designpattern.mvi.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.mvi_item_view.view.*
import spm.architecture.designpattern.R
import spm.architecture.designpattern.mvi.model.User

/**
 * Created by Sibaprasad Mohanty on 14/6/21.
 * Spm Limited
 * sp.dobest@gmail.com
 */

class MainAdapter(private val users: ArrayList<User>) :
    RecyclerView.Adapter<MainAdapter.DataViewHolder>() {
    class DataViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(user: User) {
            itemView.textViewUserName.text = user.name
            itemView.textViewUserEmail.text = user.email
            Glide.with(itemView.imageViewAvatar.context)
                .load(user.avatar)
                .into(itemView.imageViewAvatar)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = DataViewHolder(
        LayoutInflater.from(parent.context).inflate(
            R.layout.mvi_item_view, parent, false
        )
    )

    override fun getItemCount(): Int = users.size
    override fun onBindViewHolder(
        holder: DataViewHolder,
        position: Int
    ) =
        holder.bind(users[position])

    fun addData(list: List<User>) {
        users.addAll(list)
    }
}