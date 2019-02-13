package dave.receptv2

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_home.*
import java.time.LocalDateTime
import java.util.*


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"



/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [HomeFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 */


private var param1: String? = null
private var param2: String? = null
private var listener: HomeFragment.OnFragmentInteractionListener? = null


var recept: ArrayList<String> = ArrayList()


class HomeFragment : Fragment() {



    lateinit var wordViewModel: WordViewModel
    lateinit var adapter : WordListAdapter
    var words1 = emptyList<Word>() // Cached copy of words
    var words2 = emptyList<Word>() // Cached copy of words
    var words3 = emptyList<Word>() // Cached copy of words
    var words4 = emptyList<Word>() // Cached copy of words
    var words5 = emptyList<Word>() // Cached copy of words







    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)



        }

    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment


        return inflater.inflate(R.layout.fragment_home, container, false)


    }

    // TODO: Rename method, update argument and hook method into UI event
    fun onButtonPressed(uri: Uri) {
        listener?.onFragmentInteraction(uri)
    }


    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)

        val adapter = WordListAdapter(this.context!!)

        wordViewModel = ViewModelProviders.of(this).get(WordViewModel::class.java)


        var RandomRecept1 =  wordViewModel.getRandom().observe(this, Observer { words ->
            words?.let { adapter.setWords(it)}
            this.words1 = words!!
            Picasso.get().load(words?.single()?.picture).resize(350, 350).into(food1)
        })
        var RandomRecept2 =  wordViewModel.getRandom1().observe(this, Observer { words ->
            words?.let { adapter.setWords(it)}
            this.words2 = words!!
            Picasso.get().load(words?.single()?.picture).resize(350, 350).into(food2)
        })
        var RandomRecept3 =  wordViewModel.getRandom2().observe(this, Observer { words ->
            words?.let { adapter.setWords(it)}
            this.words3 = words!!
            Picasso.get().load(words?.single()?.picture).resize(350, 350).into(food3)
        })
        var RandomRecept4 =  wordViewModel.getRandom3().observe(this, Observer { words ->
            words?.let { adapter.setWords(it)}
            this.words4 = words!!
            Picasso.get().load(words?.single()?.picture).resize(350, 350).into(food4)
        })
        var RandomRecept5 =  wordViewModel.getRandom4().observe(this, Observer { words ->
            words?.let { adapter.setWords(it)}
            this.words5 = words!!
            Picasso.get().load(words?.single()?.picture).resize(350, 350).into(food6)
        })


        food1.setOnClickListener{
            val intent1 = Intent(context, detailViewRecept::class.java)
            intent1.putExtra("ID", words1?.single()?.ID)
            intent1.putExtra("titel", words1?.single()?.title)
            intent1.putExtra("recept", words1?.single()?.recept)
            intent1.putExtra("info", words1?.single()?.info)
            intent1.putExtra("ingredienser", words1?.single()?.ingredienser)
            intent1.putExtra("picture", words1?.single()?.picture)
            startActivity(intent1)

        }
        food2.setOnClickListener{
            val intent1 = Intent(context, detailViewRecept::class.java)

            intent1.putExtra("ID", words2?.single()?.ID)
            intent1.putExtra("titel", words2.single().title)
            intent1.putExtra("recept", words2.single().recept)
            intent1.putExtra("info", words2.single().info)
            intent1.putExtra("ingredienser", words2.single().ingredienser)
            intent1.putExtra("picture", words2.single().picture)
            startActivity(intent1)
        }
        food3.setOnClickListener {
            val intent1 = Intent(context, detailViewRecept::class.java)
            intent1.putExtra("ID", words3?.single()?.ID)
            intent1.putExtra("titel", words3?.single()?.title)
            intent1.putExtra("recept", words3?.single()?.recept)
            intent1.putExtra("info", words3?.single()?.info)
            intent1.putExtra("ingredienser", words3?.single()?.ingredienser)
            intent1.putExtra("picture", words3?.single()?.picture)
            startActivity(intent1)
        }
        food4.setOnClickListener {
            val intent1 = Intent(context, detailViewRecept::class.java)
            intent1.putExtra("ID", words4?.single()?.ID)
            intent1.putExtra("titel", words4?.single()?.title)
            intent1.putExtra("recept", words4?.single()?.recept)
            intent1.putExtra("info", words4?.single()?.info)
            intent1.putExtra("ingredienser", words4?.single()?.ingredienser)
            intent1.putExtra("picture", words4?.single()?.picture)
            startActivity(intent1)
        }
        food6.setOnClickListener {
            val intent1 = Intent(context, detailViewRecept::class.java)
            intent1.putExtra("ID", words5?.single()?.ID)
            intent1.putExtra("titel", words5?.single()?.title)
            intent1.putExtra("recept", words5?.single()?.recept)
            intent1.putExtra("info", words5?.single()?.info)
            intent1.putExtra("ingredienser", words5?.single()?.ingredienser)
            intent1.putExtra("picture", words5?.single()?.picture)
            startActivity(intent1)
        }

        week()

    }

    override fun onStart() {
        super.onStart()







    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     *
     *
     * See the Android Training lesson [Communicating with Other Fragments]
     * (http://developer.android.com/training/basics/fragments/communicating.html)
     * for more information.
     */
    interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        fun onFragmentInteraction(uri: Uri)
    }

    internal fun setWords(words: List<Word>) {
        var words = words
        words.get(11).title


    }

@RequiresApi(Build.VERSION_CODES.O)
fun week()
{
    //val current = LocalDateTime.now()

        val calender = Calendar.getInstance()
        val day =    calender.get(Calendar.DAY_OF_WEEK)


        if (day == 1)
        {

            ourFood1.setOnClickListener {
                Log.i("Pia8", "Söndag")
            }

        }
    if (day == 2)
    {

        ourFood1.setOnClickListener {
            Log.i("Pia8", "Måndag")
        }
    }
    if (day == 3)
    {
        ourFood1.setOnClickListener {
            Log.i("Pia8", "Tisdag")
        }

    }
    if (day == 4) {
        ourFood1.setOnClickListener {
            Log.i("Pia8", "Onsdag")
        }


    }
    if (day == 5)
    {
        ourFood1.setOnClickListener {
            Log.i("Pia8", "Torsdag")
        }    }
    if (day == 6)
    {
        ourFood1.setOnClickListener {
            Log.i("Pia8", "Fredag")
        }    }
    if (day == 7)
    {
        ourFood1.setOnClickListener {
            Log.i("Pia8", "Lördag")
        }    }





}

}

/*

 food1.setOnClickListener{
        //  wordViewModel.getID(11)
        val intent1 = Intent(context, detailViewRecept::class.java)
        wordViewModel.getID(ID = 11).observe(this, Observer { words ->
            words?.let {adapter.setWords(it)}

            this.words = words!!

            intent1.putExtra("titel", words.single().title)
            intent1.putExtra("recept", words.single().recept)
            intent1.putExtra("info", words.single().info)
            intent1.putExtra("ingredienser", words.single().ingredienser)
            intent1.putExtra("picture", words.single().picture)
            startActivity(intent1)
        })
    }
*/