package dave.receptv2

import android.arch.lifecycle.LiveData
import android.support.annotation.WorkerThread

class WordRepository(private val wordDao: WordDao) {

    // Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the data has changed.
    val allWords: LiveData<List<Word>> = wordDao.getAlphabetizedWords()
    val meatCategoryTest: LiveData<List<Word>> = wordDao.findByCategory("Meat")
    val forratCategoryTest: LiveData<List<Word>> = wordDao.findByCategory("Forrat")
    val fishCategoryTest: LiveData<List<Word>> = wordDao.findByCategory("Fish")
    val vegeCategoryTest: LiveData<List<Word>> = wordDao.findByCategory("Vegetariskt")
    val vegansktCategoryTest: LiveData<List<Word>> = wordDao.findByCategory("Veganskt")
    val dessertCategoryTest: LiveData<List<Word>> = wordDao.findByCategory("Dessert")
    //test
    //Förrät
    val numberCategory11: LiveData<List<Word>> = wordDao.findByID(11)
    val numberCategory12: LiveData<List<Word>> = wordDao.findByID(12)

    //Kött
    val numberCategory21: LiveData<List<Word>> = wordDao.findByID(21)
    val numberCategory22: LiveData<List<Word>> = wordDao.findByID(22)
    val numberCategory23: LiveData<List<Word>> = wordDao.findByID(23)

    //Fisk
    val numberCategory31: LiveData<List<Word>> = wordDao.findByID(31)
    val numberCategory32: LiveData<List<Word>> = wordDao.findByID(32)

    //Vegetariskt
    val numberCategory41: LiveData<List<Word>> = wordDao.findByID(41)

    //Veganskt
    val numberCategory51: LiveData<List<Word>> = wordDao.findByID(51)

    //Dessert
    val numberCategory61: LiveData<List<Word>> = wordDao.findByID(61)

    //Random
   // val randomID: LiveData<List<Word>> = wordDao.findbyRandom()
    val randomID: LiveData<List<Word>> = wordDao.findbyRandom()
    val randomID2: LiveData<List<Word>> = wordDao.findbyRandom()
    val randomID3: LiveData<List<Word>> = wordDao.findbyRandom()
    val randomID4: LiveData<List<Word>> = wordDao.findbyRandom()
    val randomID5: LiveData<List<Word>> = wordDao.findbyRandom()

    //Favoriter
    var favoriteID: LiveData<List<Word>> = wordDao.findByFavorit(true)




    // You must call this on a non-UI thread or your app will crash. So we're making this a
    // suspend function so the caller methods know this.
    // Like this, Room ensures that you're not doing any long running operations on the main
    // thread, blocking the UI.
    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(word: Word) {
        wordDao.insert(word)

    }





}
