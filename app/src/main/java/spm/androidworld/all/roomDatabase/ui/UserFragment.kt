package spm.androidworld.all.roomDatabase.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_book.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import spm.androidworld.all.R
import spm.androidworld.all.roomDatabase.db.DatabaseClient
import spm.androidworld.all.roomDatabase.entity.Book

/**
 * Created by Sibaprasad Mohanty on 27/06/21.
 * Spm Limited
 * sp.dobest@gmail.com
 */


class UserFragment : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_user, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        buttonSaveBook.setOnClickListener {

        }

    }

    fun saveBook(book: Book) {
        CoroutineScope(IO).launch {
            DatabaseClient(requireActivity()).getInstance(requireActivity())?.getAppDatabase()
                ?.bookDao()?.insertAll(book)
        }
    }

}