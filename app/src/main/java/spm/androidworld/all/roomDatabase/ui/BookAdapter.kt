package spm.androidworld.all.roomDatabase.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_hilt_layout.view.*
import spm.androidworld.all.R
import spm.androidworld.all.roomDatabase.entity.Book


/**
 * Created by Sibaprasad Mohanty on 27/06/21.
 * Spm Limited
 * sp.dobest@gmail.com
 */

class BookAdapter(val listBooks: ArrayList<Book>) :
    RecyclerView.Adapter<BookAdapter.BookViewHolder>() {

    class BookViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(book: Book) {
            itemView.textViewUserName.text = book.name
            itemView.textViewUserEmail.text = book.author
            /* Glide.with(itemView.imageViewAvatar.context)
                 .load(user.avatar)
                 .into(itemView.imageViewAvatar)*/
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder =
        BookViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_book, parent,
                false
            )
        )

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) =
        holder.bind(listBooks[position])

    override fun getItemCount(): Int = listBooks.size

}