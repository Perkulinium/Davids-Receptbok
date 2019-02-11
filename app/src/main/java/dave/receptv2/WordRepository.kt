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
    val numberCategory11: LiveData<List<Word>> = wordDao.findByID(11)




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
