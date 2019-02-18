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
    lateinit var adapter : favoAdapter

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

        wordViewModel = ViewModelProviders.of(this).get(WordViewModel::class.java)
        favRec.layoutManager = LinearLayoutManager(this.context)
        adapter = favoAdapter(food){rownumberClicked ->

        }
        favRec.adapter = adapter
      //  food.add(Word("1","1","1", "1",0,"1",11))


        wordViewModel.allWords.observe(this, Observer { words ->

        //wordViewModel.getCategory("Vege").observe(this, Observer { words ->
            // Update the cached copy of the words in the adapter.
            words?.let { adapter.setWords(it) }

        })

        Log.i("Pia8", "${wordViewModel.getCategory("Vege")}")

    }



}
