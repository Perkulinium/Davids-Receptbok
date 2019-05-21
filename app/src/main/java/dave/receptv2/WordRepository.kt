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
    val numberCategory13: LiveData<List<Word>> = wordDao.findByID(13)
    val numberCategory14: LiveData<List<Word>> = wordDao.findByID(14)
    val numberCategory15: LiveData<List<Word>> = wordDao.findByID(15)

    //Kött
    val numberCategory21: LiveData<List<Word>> = wordDao.findByID(21)
    val numberCategory22: LiveData<List<Word>> = wordDao.findByID(22)
    val numberCategory23: LiveData<List<Word>> = wordDao.findByID(23)
    val numberCategory24: LiveData<List<Word>> = wordDao.findByID(24)
    val numberCategory25: LiveData<List<Word>> = wordDao.findByID(24)

    //Vegetariskt
    val numberCategory31: LiveData<List<Word>> = wordDao.findByID(31)
    val numberCategory32: LiveData<List<Word>> = wordDao.findByID(32)
    val numberCategory33: LiveData<List<Word>> = wordDao.findByID(33)
    val numberCategory34: LiveData<List<Word>> = wordDao.findByID(34)
    val numberCategory35: LiveData<List<Word>> = wordDao.findByID(35)

    //Fisk
    val numberCategory41: LiveData<List<Word>> = wordDao.findByID(41)
    val numberCategory42: LiveData<List<Word>> = wordDao.findByID(42)
    val numberCategory43: LiveData<List<Word>> = wordDao.findByID(43)
    val numberCategory44: LiveData<List<Word>> = wordDao.findByID(44)
    val numberCategory45: LiveData<List<Word>> = wordDao.findByID(45)

    //Veganskt
    val numberCategory51: LiveData<List<Word>> = wordDao.findByID(51)
    val numberCategory52: LiveData<List<Word>> = wordDao.findByID(52)
    val numberCategory53: LiveData<List<Word>> = wordDao.findByID(53)
    val numberCategory54: LiveData<List<Word>> = wordDao.findByID(54)
    val numberCategory55: LiveData<List<Word>> = wordDao.findByID(55)

    //Dessert
    val numberCategory61: LiveData<List<Word>> = wordDao.findByID(61)
    val numberCategory62: LiveData<List<Word>> = wordDao.findByID(62)
    val numberCategory63: LiveData<List<Word>> = wordDao.findByID(63)
    val numberCategory64: LiveData<List<Word>> = wordDao.findByID(64)
    val numberCategory65: LiveData<List<Word>> = wordDao.findByID(65)

    //Random
    val randomID: LiveData<List<Word>> = wordDao.findbyRandom()
    val randomID2: LiveData<List<Word>> = wordDao.findbyRandom()
    val randomID3: LiveData<List<Word>> = wordDao.findbyRandom()
    val randomID4: LiveData<List<Word>> = wordDao.findbyRandom()
    val randomID5: LiveData<List<Word>> = wordDao.findbyRandom()

    //Favoriter
    var favoriteID: LiveData<List<Word>> = wordDao.findByFavorit(true)

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(word: Word) {
        wordDao.insert(word)

    }
    @WorkerThread
    suspend fun update(word: Word) {
        wordDao.updateWord(word)
    }
}
