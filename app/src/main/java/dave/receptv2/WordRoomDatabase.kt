package dave.receptv2

import android.arch.persistence.db.SupportSQLiteDatabase
import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import kotlinx.android.synthetic.main.fragment_recept.*
import kotlinx.coroutines.experimental.CoroutineScope
import kotlinx.coroutines.experimental.Dispatchers
import kotlinx.coroutines.experimental.IO
import kotlinx.coroutines.experimental.launch

@Database(entities = [Word::class], version = 6)
abstract class WordRoomDatabase : RoomDatabase() {

    abstract fun wordDao(): WordDao

    companion object {
        @Volatile
        private var INSTANCE: WordRoomDatabase? = null

        fun getDatabase(
            context: Context,
            scope: CoroutineScope
        ): WordRoomDatabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    WordRoomDatabase::class.java,
                    "word_database"
                )
                    // Wipes and rebuilds instead of migrating if no Migration object.
                    // Migration is not part of this codelab.
                    .fallbackToDestructiveMigration()
                    .addCallback(WordDatabaseCallback(scope))
                    .build()
                INSTANCE = instance
                // return instance
                instance
            }
        }

        private class WordDatabaseCallback(
            private val scope: CoroutineScope
        ) : RoomDatabase.Callback() {
            /**
             * Override the onOpen method to populate the database.
             * For this sample, we clear the database every time it is created or opened.
             */
            override fun onOpen(db: SupportSQLiteDatabase) {
                super.onOpen(db)
                // If you want to keep the data through app restarts,
                // comment out the following line.
                INSTANCE?.let { database ->
                    scope.launch(Dispatchers.IO) {
                        populateDatabase(database.wordDao())
                    }
                }
            }
        }

        /**
         * Populate the database in a new coroutine.
         * If you want to start with more words, just add them.
         */
        fun populateDatabase(wordDao: WordDao) {
            // Start the app with a clean database every time.
            // Not needed if you only populate on creation.


            wordDao.deleteAll()
//Kött
            var word = Word("Test", "Hej", "Meat", "", "https://foodwatch.com.au/images/stories/p-fats/iStock-POPCORN839992974.jpg","")

            wordDao.insert(word)
            word = Word("World!", "Hej", "Meat", "","https://foodwatch.com.au/images/stories/p-fats/iStock-POPCORN839992974.jpg","")
            wordDao.insert(word)
            word = Word("1!", "2", "Meat","","https://foodwatch.com.au/images/stories/p-fats/iStock-POPCORN839992974.jpg","")
            wordDao.insert(word)

       //Förrätt
            word = Word("vege!", "2", "Forrat","","https://foodwatch.com.au/images/stories/p-fats/iStock-POPCORN839992974.jpg","")
            wordDao.insert(word)

            word = Word("FUCK FÖRRÄTT", "FEMTIO", "Forrat","","https://foodwatch.com.au/images/stories/p-fats/iStock-POPCORN839992974.jpg","")
            wordDao.insert(word)

            //Fisk
            word = Word("Fisk1", "FISHBLA TEST", "Fish","","https://foodwatch.com.au/images/stories/p-fats/iStock-POPCORN839992974.jpg","")
            wordDao.insert(word)

            word = Word("Fisk2", "FEMTIO", "Fish","","https://foodwatch.com.au/images/stories/p-fats/iStock-POPCORN839992974.jpg","")
            wordDao.insert(word)

            //Vegetariskt
            word = Word("Vegetariskt", "test vege bla\nbla", "Vegetariskt","","https://foodwatch.com.au/images/stories/p-fats/iStock-POPCORN839992974.jpg","")
            wordDao.insert(word)

            //Veganskt
            word = Word("Veganskt", "test vege bla\nbla", "Veganskt","","https://foodwatch.com.au/images/stories/p-fats/iStock-POPCORN839992974.jpg","")
            wordDao.insert(word)

            //Dessert
            word = Word("Dessert", "test vege bla\nbla", "Dessert","","https://foodwatch.com.au/images/stories/p-fats/iStock-POPCORN839992974.jpg","")
            wordDao.insert(word)
        }
    }

}
