package dave.receptv2

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.*


@Dao
interface WordDao {

    @Query("SELECT * from word_table ORDER BY title ASC")
    fun getAlphabetizedWords(): LiveData<List<Word>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(word: Word)


    @Query("DELETE FROM word_table")
    fun deleteAll()

    @Query("SELECT * FROM word_table where category LIKE  :category")
    fun findByCategory(category: String) : LiveData<List<Word>>

    @Query("SELECT * FROM word_table where ID LIKE :ID")
    fun findByID(ID: Int) : LiveData<List<Word>>

   @Query ("SELECT * FROM word_table WHERE ID IN (SELECT ID FROM word_table ORDER BY RANDOM() LIMIT 1)")

    fun findbyRandom() : LiveData<List<Word>>

    @Query ("SELECT * FROM WORD_TABLE where favoriter LIKE :favoriter")
    fun findByFavorit(favoriter: Boolean) : LiveData<List<Word>>

    @Update
    fun updateWord(word: Word)
}
