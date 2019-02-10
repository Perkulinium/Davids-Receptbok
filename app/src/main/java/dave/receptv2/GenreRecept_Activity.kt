package dave.receptv2

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_genre_recept_.*


class GenreRecept_Activity : AppCompatActivity() {

    companion object {
        const val newWordActivityRequestCode = 1
    }
    private lateinit var wordViewModel: WordViewModel
    lateinit var adapter : WordListAdapter


    var recept: ArrayList<String> = ArrayList()
  //  var receptFor: ArrayList<String> = ArrayList()




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_genre_recept_)

        var intent = intent.getStringExtra("textNamn")

        foodCategoryTextView.setText(intent)

        val recyclerView = findViewById<RecyclerView>(R.id.receptListRec)
        val adapter = WordListAdapter(this)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)







if (foodCategoryTextView.text == "Kött")
{
    wordViewModel = ViewModelProviders.of(this).get(WordViewModel::class.java)
    wordViewModel.allWords.observe(this, Observer { words ->
        // Update the cached copy of the words in the adapter.
        words?.let { adapter.setWords(it) }
    })


}


    



    }


}
