package dave.receptv2

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.list_item.*


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
           wordViewModel.getID(11)


            val intent1 = Intent(context, detailViewRecept::class.java)


            wordViewModel.getID(ID = 11).observe(this, Observer { words ->
                words?.let {adapter.setWords(it)}

                this.words = words!!

                   // intent1.putExtra("recept", words.single().title)
                    intent1.putExtra("titel", words.single().title)
                intent1.putExtra("recept", words.single().recept)

                // intent1.putExtra("titel", words.get(0).title)
                   // intent1.putExtra("recept", words.get(1).recept)

                //intent1.putExtra("recept", words!![3].recept)

                        startActivity(intent1)

            })



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
