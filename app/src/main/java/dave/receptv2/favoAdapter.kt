package dave.receptv2

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import kotlinx.android.synthetic.main.list_item.view.*
import kotlin.math.sign
import android.widget.Toast
import android.app.ListActivity
import android.provider.UserDictionary
import android.support.v7.widget.helper.ItemTouchHelper

class favoAdapter internal constructor(context: Context) : RecyclerView.Adapter<favoAdapter.WordViewHolder>()  {

    private var words = emptyList<Word>() // Cached copy of words
    var arrayRecept = mutableListOf<Word>()

    override fun onCreateViewHolder(parent: ViewGroup, rownumber: Int): favoAdapter.WordViewHolder {
        var theholder = WordViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false))
        theholder.theadapter = this
        Log.i("Pia8", "Recept: ${arrayRecept}")
        return theholder
    }

    override fun getItemCount(): Int {
        return words.size
    }

    override fun onBindViewHolder(holder: favoAdapter.WordViewHolder, rownumber: Int) {

        val current = words[rownumber]
        holder.wordItemView.text = current.title

    }

    class WordViewHolder (view: View) : RecyclerView.ViewHolder(view) {
        lateinit var theadapter : favoAdapter
     val wordItemView: TextView = itemView.findViewById(R.id.listItem)
        init {
            view.setOnClickListener {
                 Log.i("pia8app", "KLICKAT PÅ RADEN " + adapterPosition.toString())

                var detailIntent = Intent(itemView.context, detailViewRecept::class.java)
                detailIntent.putExtra("titel", theadapter.words.get(adapterPosition).title)
                detailIntent.putExtra("recept", theadapter.words.get(adapterPosition).recept)
                detailIntent.putExtra("info", theadapter.words.get(adapterPosition).info)
                detailIntent.putExtra("picture", theadapter.words.get(adapterPosition).picture)
                detailIntent.putExtra("ingredienser", theadapter.words.get(adapterPosition).ingredienser)
                detailIntent.putExtra("ID", theadapter.words.get(adapterPosition).ID)

                view.context.startActivity(detailIntent)
            }
        }
    }
    internal fun setWords(words: List<Word>) {
        this.words = words
        notifyDataSetChanged()
    }
}
