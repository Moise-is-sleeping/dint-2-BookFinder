package ui.ViewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import data.Models.Book
import data.Util.BookApiService
import data.Util.BookRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import ui.state.BookState


class BookViewModel: ViewModel() {

    private var bookApiService = BookApiService()
    private var bookRepository = BookRepository(bookApiService)


    private var  _bookList = MutableStateFlow<List<BookState?>>(emptyList())
    var bookList: StateFlow<List<BookState?>> = _bookList.asStateFlow()



    fun getBooks(ids: MutableList<String>){
        viewModelScope.launch(Dispatchers.IO) {
            val tempList = mutableListOf<BookState?>()
            for (id in ids){
                val book = bookRepository.getBookState(id)
                tempList.add(book)
            }
            _bookList.value = tempList

        }
    }

    fun searchBooks(search:String) {
        viewModelScope.launch(Dispatchers.IO) {
            val result = bookRepository.searchBooksState(search)
            val tempList = mutableListOf<String>()
            Log.d("results",result!!.docs.toString())
            for (doc in result.docs){
                val id = doc.key.substring(7)
                tempList.add(id)
            }
            getBooks(tempList)
        }
    }

    fun clear(){
        _bookList.value = emptyList()
    }




}




