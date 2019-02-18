package dave.receptv2

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView


class WordListAdapter internal constructor(context: Context) : RecyclerView.Adapter<WordListAdapter.WordViewHolder>()  {
    lateinit var adapter : WordListAdapter

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var words = emptyList<Word>() // Cached copy of words

    inner class WordViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val wordItemView: TextView = itemView.findViewById(R.id.listItem)



init {
    itemView.setOnClickListener{
        Log.i("Pia8", "Klick på rad ${adapterPosition}")

        var detailIntent = Intent(itemView.context, detailViewRecept::class.java)
        detailIntent.putExtra("titel", "${wordItemView.text}")
        //detailIntent.putExtra("recept", adapter.words.get(adapterPosition).recept)
        detailIntent.putExtra("recept", words.get(adapterPosition).recept)
        detailIntent.putExtra("info", words.get(adapterPosition).info)
        detailIntent.putExtra("picture", words.get(adapterPosition).picture)
        detailIntent.putExtra("ingredienser", words.get(adapterPosition).ingredienser)
        detailIntent.putExtra("ID", words.get(adapterPosition).ID)





        itemView.context.startActivity(detailIntent)

    }
}

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WordViewHolder {
        val itemView = inflater.inflate(R.layout.list_item, parent, false)
        return WordViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: WordViewHolder, position: Int) {
        val current = words[position]
        holder.wordItemView.text = current.title






    }

    internal fun setWords(words: List<Word>) {
        this.words = words
        notifyDataSetChanged()


    }





    override fun getItemCount() = words.size
}




