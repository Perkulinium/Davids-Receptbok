package dave.receptv2

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.helper.ItemTouchHelper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_genre_recept_.*
import kotlinx.android.synthetic.main.fragment_favorit.*


class FavoritFragment : Fragment() {
    private lateinit var wordViewModel: WordViewModel
    lateinit var adapter1 : favoAdapter
    var recept: ArrayList<String> = ArrayList()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_favorit, container, false)
    }

    override fun onStart() {
        super.onStart()
        val adapter = favoAdapter(context!!)
        favRec.adapter = adapter
        favRec.layoutManager = LinearLayoutManager(context)

        wordViewModel = ViewModelProviders.of(this).get(WordViewModel::class.java)

        if (favTextView.text == "Favoriter")
        {
            wordViewModel.getFavorit(true).observe(this, Observer { words ->
                // Update the cached copy of the words in the adapter.
                words?.let { adapter.setWords(it) }
            })
        }
    }
}
