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


class favoAdapter internal constructor(var words : List<Word>, val clickReceptListener: (Int) -> (Unit)) : RecyclerView.Adapter<favoAdapter.WordViewHolder>()  {

    private var words1 = emptyList<Word>() // Cached copy of words

    //var words : List<Word>


    fun letsUpdateStuff(thenewpeople : List<Word>)
    {
        words = thenewpeople
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, rownumber: Int): favoAdapter.WordViewHolder {
        var theholder = WordViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false))
        theholder.theadapter = this
        return theholder
    }

    override fun getItemCount(): Int {
        return words.size
    }

    override fun onBindViewHolder(holder: favoAdapter.WordViewHolder, rownumber: Int) {
      //  holder.title.text = words.get(rownumber).title

        val current = words[rownumber]
        holder.wordItemView.text = current.title

        // holder.personname.text = people.get(rownumber)

    }

    class WordViewHolder (view: View) : RecyclerView.ViewHolder(view) {
        lateinit var theadapter : favoAdapter

     //   val title = view.listItem
     val wordItemView: TextView = itemView.findViewById(R.id.listItem)


        init {
            view.setOnClickListener {
                // Log.i("pia8app", "KLICKAT PÅ RADEN " + adapterPosition.toString())
         //       Log.i("pia8app", "RADERA ID  ${theadapter.words.get(adapterPosition).fbKey}")
                theadapter.clickReceptListener(adapterPosition)


             /*
                var detailIntent = Intent(itemView.context, detailViewRecept::class.java)
                detailIntent.putExtra("titel", "${wordItemView.text}")
                //detailIntent.putExtra("recept", adapter.words.get(adapterPosition).recept)

                detailIntent.putExtra("recept", words.get(adapterPosition).recept)
                detailIntent.putExtra("info", words.get(adapterPosition).info)
                detailIntent.putExtra("picture", words.get(adapterPosition).picture)
                detailIntent.putExtra("ingredienser", words.get(adapterPosition).ingredienser)
*/
                var detailIntent = Intent(itemView.context, detailViewRecept::class.java)
                detailIntent.putExtra("titel", theadapter.words.get(adapterPosition).title)
                detailIntent.putExtra("recept", theadapter.words.get(adapterPosition).recept)
                detailIntent.putExtra("info", theadapter.words.get(adapterPosition).info)
                detailIntent.putExtra("picture", theadapter.words.get(adapterPosition).picture)
                detailIntent.putExtra("ingredienser", theadapter.words.get(adapterPosition).ingredienser)





                view.context.startActivity(detailIntent)

            }
        }
    }

    fun update(view : View)
    {

    }

    internal fun setWords(words: List<Word>) {
        this.words = words
        notifyDataSetChanged()


    }


}
