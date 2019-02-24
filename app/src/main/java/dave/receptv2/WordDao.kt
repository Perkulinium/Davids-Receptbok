package dave.receptv2

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.*


@Dao
interface WordDao {

    // LiveData is a data holder class that can be observed within a given lifecycle.
    // Always holds/caches latest version of data. Notifies its active observers when the
    // data has changed. Since we are getting all the contents of the database,
    // we are notified whenever any of the database contents have changed.
    @Query("SELECT * from word_table ORDER BY title ASC")
    fun getAlphabetizedWords(): LiveData<List<Word>>


    // We do not need a conflict strategy, because the title is our primary key, and you cannot
    // add two items with the same primary key to the database. If the table has more than one
    // column, you can use @Insert(onConflict = OnConflictStrategy.REPLACE) to update a row.
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(word: Word)


    @Query("DELETE FROM word_table")
    fun deleteAll()

    @Query("SELECT * FROM word_table where category LIKE  :category")
    fun findByCategory(category: String) : LiveData<List<Word>>

    @Query("SELECT * FROM word_table where ID LIKE :ID")
    fun findByID(ID: Int) : LiveData<List<Word>>


    //@Query ("SELECT * FROM word_table ORDER BY RANDOM() LIMIT 1")
   @Query ("SELECT * FROM word_table WHERE ID IN (SELECT ID FROM word_table ORDER BY RANDOM() LIMIT 1)")

//    fun findbyRandom() : LiveData<List<Word>>
    fun findbyRandom() : LiveData<List<Word>>


    @Query ("SELECT * FROM WORD_TABLE where favoriter LIKE :favoriter")
    fun findByFavorit(favoriter: Boolean) : LiveData<List<Word>>



    @Update
    fun updateWord(word: Word)


}
//test