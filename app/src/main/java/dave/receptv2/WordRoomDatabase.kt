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

@Database(entities = [Word::class], version = 8)
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
            var word = Word(
                "Test",
                "Hej",
                "Meat",
                "Ingredienser",
                "https://foodwatch.com.au/images/stories/p-fats/iStock-POPCORN839992974.jpg",
                "30 minuter",
                21)
            wordDao.insert(word)

            word = Word(
                "World!",
                "Hej",
                "Meat",
                "7",
                "https://s1.dmcdn.net/ZVqns/x240-oku.jpg",
                "30 minuter",
                22)
            wordDao.insert(word)

            word = Word(
                "1!",
                "2",
                "Meat",
                "6",
                "https://m.ellaslist.com.au/system/articles/featured_images/000/000/826/original/food-hero.jpg?1489722536",
                "30 minuter",
                23)
            wordDao.insert(word)

       //Förrätt
            word = Word(
                "vege!",
                "2",
                "Forrat",
                "Lök\nBacon\nSaft",
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcT5sld6AcJjX6VvfNorWI99UgQyV_JlQy8jE8lOWn1XsFbhuciN1A",
                "30 minuter",
                11)
            wordDao.insert(word)

            word = Word(
                "FUCK FÖRRÄTT",
                "FEMTIO",
                "Forrat",
                "5",
                "https://s1-ssl.dmcdn.net/s_e5s/x240-qbM.jpg",
                "30 minuter",
                12)
            wordDao.insert(word)

            //Fisk
            word = Word(
                "Fisk1",
                "FISHBLA TEST",
                "Fish",
                "4",
                "https://cdn.dnaindia.com/sites/default/files/styles/third/public/2019/01/14/778006-uber-eats.jpg?itok=h_6XYLpj",
                "30 minuter",
                31)
            wordDao.insert(word)

            word = Word(
                "Fisk2",
                "FEMTIO",
                "Fish",
                "3",
                "https://t4.ftcdn.net/jpg/02/39/06/95/240_F_239069592_gdCQhV9iiv7aTm8tbPLz8m361nHc27vZ.jpg",
                "30 minuter",
                32)
            wordDao.insert(word)

            //Vegetariskt
            word = Word(
                "Vegetariskt",
                "test vege bla\nbla",
                "Vegetariskt",
                "Test",
                "https://t3.ftcdn.net/jpg/02/34/31/86/240_F_234318664_VZYlo0dECXPKuGoJqEDhIitb7y2oAOBW.jpg",
                "30 minuter",
                41)
            wordDao.insert(word)

            //Veganskt
            word = Word(
                "Veganskt",
                "test vege bla\nbla",
                "Veganskt",
                "2",
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQNt_rRuBjbob2ILhp_kbsregKhqn3JKsYPF8EVW9r6oVb11XTE",
                "30 minuter",
                51)
            wordDao.insert(word)

            //Dessert
           word = Word(
               "Dessert",
               "test vege bla\nbla",
               "Dessert",
               "1",
               "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQdzfjkjqTHnRQ2AsB1Hd3MN476Z8dKT-bsPh5KvlM5pN35WLaE",
               "30 minuter",
               61)
            wordDao.insert(word)


        }
    }

}
