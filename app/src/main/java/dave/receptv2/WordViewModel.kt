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

        if (ID == 12)
        {
            val number12 = repository.numberCategory12
            return number12
        }
        if (ID == 13)
        {
            val number13 = repository.numberCategory13
            return number13
        }
        if (ID == 14)
        {
            val number14 = repository.numberCategory14
            return number14
        }
        if (ID == 15)
        {
            val number15 = repository.numberCategory15
            return number15
        }
        if (ID == 21)
    {
        val number21 = repository.numberCategory21
        return number21
    }
        if (ID == 22)
        {
            val number22 = repository.numberCategory22
            return number22
        }
        if (ID == 23)
        {
            val number23 = repository.numberCategory23
            return number23
        }
        if (ID == 24)
        {
            val number24 = repository.numberCategory24
            return number24
        }
        if (ID == 25)
        {
            val number25 = repository.numberCategory25
            return number25
        }
        if (ID == 31)
        {
            val number31 = repository.numberCategory31
            return number31
        }
        if (ID == 32)
        {
            val number32 = repository.numberCategory32
            return number32 //test
        }
        if (ID == 33)
        {
            val number33 = repository.numberCategory33
            return number33 //test
        }
        if (ID == 34)
        {
            val number34 = repository.numberCategory34
            return number34 //test
        }
        if (ID == 35)
        {
            val number35 = repository.numberCategory35
            return number35 //test
        }
        if (ID == 41)
        {
            val number41 = repository.numberCategory41
            return number41
        }
        if (ID == 42)
        {
            val number42 = repository.numberCategory42
            return number42
        }
        if (ID == 43)
        {
            val number43 = repository.numberCategory43
            return number43
        }
        if (ID == 44)
        {
            val number44 = repository.numberCategory44
            return number44
        }
        if (ID == 45)
        {
            val number45 = repository.numberCategory45
            return number45
        }
        if (ID == 51)
        {
            val number51 = repository.numberCategory51
            return number51
        }
        if (ID == 52)
        {
            val number52 = repository.numberCategory52
            return number52
        }
        if (ID == 53)
        {
            val number53 = repository.numberCategory53
            return number53
        }
        if (ID == 54)
        {
            val number54 = repository.numberCategory54
            return number54
        }
        if (ID == 55)
        {
            val number55 = repository.numberCategory55
            return number55
        }
        if (ID == 61)
        {
            val number61 = repository.numberCategory61
            return number61
        }
        if (ID == 62)
        {
            val number62 = repository.numberCategory62
            return number62
        }
        if (ID == 63)
        {
            val number63 = repository.numberCategory63
            return number63
        }
        if (ID == 64)
        {
            val number64 = repository.numberCategory64
            return number64
        }
        if (ID == 65)
        {
            val number65 = repository.numberCategory65
            return number65
        }

        return allWords
    }

    fun getFavorit(favoriter : Boolean) : LiveData<List<Word>>
    {
        if (favoriter == true)
        {
            var favoriteID = repository.favoriteID
            return favoriteID
        }
        if (favoriter == false)
        {
            var changeFavorit = repository.favoriteID
            return changeFavorit
        }
        return allWords
    }

    fun getRandom() : LiveData<List<Word>>
    {
            val randomTest = repository.randomID
            return randomTest
    }
    fun getRandom2() : LiveData<List<Word>>
    {
        val randomTest = repository.randomID2
        return randomTest
    }
    fun getRandom3() : LiveData<List<Word>>
    {
        val randomTest = repository.randomID3
        return randomTest
    }
    fun getRandom4() : LiveData<List<Word>>
    {
        val randomTest = repository.randomID4
        return randomTest
    }
    fun getRandom5() : LiveData<List<Word>>
    {
        val randomTest = repository.randomID5
        return randomTest
    }

    fun update(word: Word) = scope.launch(Dispatchers.IO) {
        repository.update(word)
    }


}
