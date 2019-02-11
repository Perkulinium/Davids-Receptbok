package dave.receptv2

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import kotlinx.coroutines.experimental.*
import kotlinx.coroutines.experimental.android.Main
import kotlin.coroutines.experimental.CoroutineContext

class WordViewModel(application: Application) : AndroidViewModel(application) {

    private var parentJob = Job()
    // By default all the coroutines launched in this scope should be using the Main dispatcher
    private val coroutineContext: CoroutineContext
        get() = parentJob + Dispatchers.Main
    private val scope = CoroutineScope(coroutineContext)

    private val repository: WordRepository
    // Using LiveData and caching what getAlphabetizedWords returns has several benefits:
    // - We can put an observer on the data (instead of polling for changes) and only update the
    //   the UI when the data actually changes.
    // - Repository is completely separated from the UI through the ViewModel.
    val allWords: LiveData<List<Word>>

   // val meatList: LiveData<List<Word>>

   // val forratList : LiveData<List<Word>>


    init {

        val wordsDao = WordRoomDatabase.getDatabase(application, scope).wordDao()
        repository = WordRepository(wordsDao)
        allWords = repository.allWords




    }

    /**
     * Launching a new coroutine to insert the data in a non-blocking way
     */
    fun insert(word: Word) = scope.launch(Dispatchers.IO) {
        repository.insert(word)
    }

    override fun onCleared() {
        super.onCleared()
        parentJob.cancel()
    }

    fun getCategory(category: String) : LiveData<List<Word>>
    {

        if (category == "Meat")
        {

            val meatList = repository.meatCategoryTest
            return meatList
        }

        if (category == "Forrat")
        {
            val forratList = repository.forratCategoryTest
            return forratList

        }
        if (category == "Fish")
        {
            val fishList = repository.fishCategoryTest
            return fishList
        }
        if (category == "Vegetariskt")
        {
            val vegeList = repository.vegeCategoryTest
            return vegeList
        }
        if (category == "Veganskt")
        {
            val veganList = repository.vegansktCategoryTest
            return veganList
        }
        if (category == "Dessert")
        {
            val dessertList = repository.dessertCategoryTest
            return dessertList
        }

        return allWords
    }


    fun getID(ID : Int) : LiveData<List<Word>>
    {
        if (ID == 11)
        {
            val number11 = repository.numberCategory11
            return number11
        }

        return allWords
    }

}
