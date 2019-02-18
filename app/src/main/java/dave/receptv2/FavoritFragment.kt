package dave.receptv2

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.activity_genre_recept_.*
import kotlinx.android.synthetic.main.fragment_favorit.*


class FavoritFragment : Fragment() {

    private lateinit var wordViewModel: WordViewModel
    lateinit var adapter1 : favoAdapter

    var food = mutableListOf<Word>()

    var recept: ArrayList<String> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)








    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favorit, container, false)



    }


    override fun onStart() {
        super.onStart()






        val adapter = favoAdapter(context!!)
        favRec.adapter = adapter
        favRec.layoutManager = LinearLayoutManager(context)

        wordViewModel = ViewModelProviders.of(this).get(WordViewModel::class.java)


      //  val recyclerView = findViewById<RecyclerView>(R.id.receptListRec)
      //  val adapter = WordListAdapter(this)
      //  recyclerView.adapter = adapter
      //  recyclerView.layoutManager = LinearLayoutManager(this)
      //  wordViewModel = ViewModelProviders.of(this).get(WordViewModel::class.java)




        if (favTextView.text == "Förrätt")
        {
            wordViewModel.getFavorit(true).observe(this, Observer { words ->
                // Update the cached copy of the words in the adapter.
                words?.let { adapter.setWords(it) }

            })


        }



    }



}
