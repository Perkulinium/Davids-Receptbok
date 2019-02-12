package dave.receptv2

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_home.*
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
    var words = emptyList<Word>() // Cached copy of words


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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {


        super.onViewCreated(view, savedInstanceState)

        val adapter = WordListAdapter(this.context!!)

        wordViewModel = ViewModelProviders.of(this).get(WordViewModel::class.java)



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
        food2.setOnClickListener{
            //  wordViewModel.getID(11)
            val intent1 = Intent(context, detailViewRecept::class.java)
            wordViewModel.getID(ID = 41).observe(this, Observer { words ->
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



            food3.setOnClickListener {
               // words.random().ID


                val intent1 = Intent(context, detailViewRecept::class.java)

               wordViewModel.getRandom(id).observe(this, Observer { words ->
                   words?.let { adapter.setWords(it)}

                  // words?.random()?.ID


                    this.words = words!!


                intent1.putExtra("ID", words?.random()?.ID)

                intent1.putExtra("titel", words?.single()?.title)
                intent1.putExtra("recept", words?.single()?.recept)
                intent1.putExtra("info", words?.single()?.info)
                intent1.putExtra("ingredienser", words?.single()?.ingredienser)
                intent1.putExtra("picture", words?.single()?.picture)



                   //    intent1.putExtra("titel", words?.random().title)
               //    intent1.putExtra("recept", words?.random().recept)
               //    intent1.putExtra("info", words?.random().info)
               //    intent1.putExtra("ingredienser", words?.random().ingredienser)
               //    intent1.putExtra("picture", words?.random().picture)


                   startActivity(intent1)
                })

              //  Log.i("Pia8", "$randomTest")

            }

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



}
