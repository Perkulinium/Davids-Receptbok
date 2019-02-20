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



    //var words : List<Word>


    fun letsUpdateStuff(thenewpeople : MutableList<Word>)
    {
        words = arrayRecept
        notifyDataSetChanged()

    }

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
                 Log.i("pia8app", "KLICKAT PÅ RADEN " + adapterPosition.toString())
         //       Log.i("pia8app", "RADERA ID  ${theadapter.words.get(adapterPosition).fbKey}")
              //  theadapter.clickReceptListener(adapterPosition)





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

    fun update(view : View)
    {

    }

    internal fun setWords(words: List<Word>) {
        this.words = words
        notifyDataSetChanged()


    }

    /*
 var simpleItemTouchCallback: ItemTouchHelper.SimpleCallback = object : ItemTouchHelper.SimpleCallback(
     0,
     ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT or ItemTouchHelper.DOWN or ItemTouchHelper.UP
 ) {

     override fun onMove(
         recyclerView: RecyclerView,
         viewHolder: RecyclerView.ViewHolder,
         target: RecyclerView.ViewHolder
     ): Boolean {

         return false
     }


     override fun onSwiped(viewHolder: RecyclerView.ViewHolder, swipeDir: Int) {



         //Remove swiped item from list and notify the RecyclerView
         val position = viewHolder.adapterPosition
         words.removeAt(position)
         notifyDataSetChanged()

     }
 }
*/
}
