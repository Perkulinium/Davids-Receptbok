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
import java.util.*
import kotlin.collections.ArrayList


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
  //  lateinit var adapter : WordListAdapter
    var words1 = emptyList<Word>() // Cached copy of words

    var words2 = emptyList<Word>()
    var words3 = emptyList<Word>()
    var words4 = emptyList<Word>()
    var words5 = emptyList<Word>()
    var todayRecept = emptyList<Word>()
    var randomWords1 = emptyList<Word>()
    var randomWords2 = emptyList<Word>()
    var randomWords3 = emptyList<Word>()
    var randomWords4 = emptyList<Word>()
    var randomWords5 = emptyList<Word>()








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



        wordViewModel.getRandom().observe(this, Observer { randomwords1 ->
            randomwords1?.let { adapter.setWords(it)
                this.randomWords1 = randomwords1
                if (randomwords1.size >0)
                {
                    Log.i("Pia8", "ID: ${randomwords1.single().ID}")
                    Picasso.get().load(randomwords1.single().picture).fit().into(food1)
                }
            }
        })
        food1.setOnClickListener{
            val intent1 = Intent(context, detailViewRecept::class.java)
            intent1.putExtra("ID", randomWords1?.single()?.ID)
            intent1.putExtra("titel", randomWords1?.single()?.title)
            intent1.putExtra("recept", randomWords1?.single()?.recept)
            intent1.putExtra("info", randomWords1?.single()?.info)
            intent1.putExtra("ingredienser", randomWords1?.single()?.ingredienser)
            intent1.putExtra("picture", randomWords1.single()?.picture)
            startActivity(intent1)
        }
        wordViewModel.getRandom2().observe(this, Observer { randomwords2 ->
            randomwords2?.let { adapter.setWords(it)
                this.randomWords2 = randomwords2
                if (randomwords2.size >0)
                {
                    Log.i("Pia8", "ID: ${randomwords2.single().ID}")
                    Picasso.get().load(randomwords2.single().picture).fit().into(food2)
                }
            }
        })
        food2.setOnClickListener{
            val intent2 = Intent(context, detailViewRecept::class.java)
            intent2.putExtra("ID", randomWords2?.single()?.ID)
            intent2.putExtra("titel", randomWords2?.single()?.title)
            intent2.putExtra("recept", randomWords2?.single()?.recept)
            intent2.putExtra("info", randomWords2?.single()?.info)
            intent2.putExtra("ingredienser", randomWords2?.single()?.ingredienser)
            intent2.putExtra("picture", randomWords2.single()?.picture)
            startActivity(intent2)
        }
        wordViewModel.getRandom3().observe(this, Observer { randomwords3 ->
            randomwords3?.let { adapter.setWords(it)
                this.randomWords3 = randomwords3
                if (randomwords3.size >0)
                {
                    Log.i("Pia8", "ID: ${randomwords3.single().ID}")
                    Picasso.get().load(randomwords3.single().picture).fit().into(food3)
                }
            }
        })
        food3.setOnClickListener{
            val intent2 = Intent(context, detailViewRecept::class.java)
            intent2.putExtra("ID", randomWords3?.single()?.ID)
            intent2.putExtra("titel", randomWords3?.single()?.title)
            intent2.putExtra("recept", randomWords3?.single()?.recept)
            intent2.putExtra("info", randomWords3?.single()?.info)
            intent2.putExtra("ingredienser", randomWords3?.single()?.ingredienser)
            intent2.putExtra("picture", randomWords3.single()?.picture)
            startActivity(intent2)
        }
        wordViewModel.getRandom4().observe(this, Observer { randomwords4 ->
            randomwords4?.let { adapter.setWords(it)
                this.randomWords4 = randomwords4
                if (randomwords4.size >0)
                {
                    Log.i("Pia8", "ID: ${randomwords4.single().ID}")
                    Picasso.get().load(randomwords4.single().picture).fit().into(food4)
                }
            }
        })
        food4.setOnClickListener{
            val intent2 = Intent(context, detailViewRecept::class.java)
            intent2.putExtra("ID", randomWords4?.single()?.ID)
            intent2.putExtra("titel", randomWords4?.single()?.title)
            intent2.putExtra("recept", randomWords4?.single()?.recept)
            intent2.putExtra("info", randomWords4?.single()?.info)
            intent2.putExtra("ingredienser", randomWords4?.single()?.ingredienser)
            intent2.putExtra("picture", randomWords4.single()?.picture)
            startActivity(intent2)
        }
        wordViewModel.getRandom5().observe(this, Observer { randomwords5 ->
            randomwords5?.let { adapter.setWords(it)
                this.randomWords5 = randomwords5
                if (randomwords5.size >0)
                {
                    Log.i("Pia8", "ID: ${randomwords5.single().ID}")
                    Picasso.get().load(randomwords5.single().picture).fit().into(food6)
                }
            }
        })
        food6.setOnClickListener {
            val intent2 = Intent(context, detailViewRecept::class.java)
            intent2.putExtra("ID", randomWords5?.single()?.ID)
            intent2.putExtra("titel", randomWords5?.single()?.title)
            intent2.putExtra("recept", randomWords5?.single()?.recept)
            intent2.putExtra("info", randomWords5?.single()?.info)
            intent2.putExtra("ingredienser", randomWords5?.single()?.ingredienser)
            intent2.putExtra("picture", randomWords5.single()?.picture)
            startActivity(intent2)
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
    val adapter = WordListAdapter(this.context!!)
        val calender = Calendar.getInstance()
        val day =    calender.get(Calendar.DAY_OF_WEEK)


        if (day == 1)
        {
            wordViewModel.getID(11).observe(this, Observer { words ->
                words1.let { adapter.setWords(it) }

                this.words1 = words!!
                if (words.size >0)
                {
                    Log.i("Pia8", "ID: ${words.single().ID}")
                    Picasso.get().load(words.single().picture).fit().into(ourFood1)

                }
            })
            ourFood1.setOnClickListener {
                Log.i("Pia89", "Söndag")
              //  if (words.size > 0)
                val intent1 = Intent(context, detailViewRecept::class.java)
                intent1.putExtra("ID", words1?.single()?.ID)
                intent1.putExtra("titel", words1?.single()?.title)
                intent1.putExtra("recept", words1?.single()?.recept)
                intent1.putExtra("info", words1?.single()?.info)
                intent1.putExtra("ingredienser", words1?.single()?.ingredienser)
                intent1.putExtra("picture", words1.single()?.picture)
                startActivity(intent1)
            }
            wordViewModel.getID(21).observe(this, Observer { words ->
                words?.let { adapter.setWords(it)
                    this.words2 = words
                    if (words.size >0)
                    {
                        Log.i("Pia8", "ID: ${words.single().ID}")
                        Picasso.get().load(words.single().picture).fit().into(ourFood2)
                    }
                }
            })

            ourFood2.setOnClickListener{
                val intent1 = Intent(context, detailViewRecept::class.java)
                intent1.putExtra("ID", words2?.single()?.ID)
                intent1.putExtra("titel", words2?.single()?.title)
                intent1.putExtra("recept", words2?.single()?.recept)
                intent1.putExtra("info", words2?.single()?.info)
                intent1.putExtra("ingredienser", words2?.single()?.ingredienser)
                intent1.putExtra("picture", words2.single()?.picture)
                startActivity(intent1)
            }
            wordViewModel.getID(31).observe(this, Observer { words ->
                words?.let { adapter.setWords(it)
                    this.words3 = words
                    if (words.size >0)
                    {
                        Log.i("Pia8", "ID: ${words.single().ID}")
                        Picasso.get().load(words.single().picture).fit().into(ourFood3)
                    }
                }
            })

            ourFood3.setOnClickListener{
                val intent1 = Intent(context, detailViewRecept::class.java)
                intent1.putExtra("ID", words3?.single()?.ID)
                intent1.putExtra("titel", words3?.single()?.title)
                intent1.putExtra("recept", words3?.single()?.recept)
                intent1.putExtra("info", words3?.single()?.info)
                intent1.putExtra("ingredienser", words3?.single()?.ingredienser)
                intent1.putExtra("picture", words3.single()?.picture)
                startActivity(intent1)
            }
            wordViewModel.getID(41).observe(this, Observer { words ->
                words?.let { adapter.setWords(it)
                    this.words4 = words
                    if (words.size >0)
                    {
                        Log.i("Pia8", "ID: ${words.single().ID}")
                        Picasso.get().load(words.single().picture).fit().into(ourFood4)
                    }
                }
            })

            ourFood4.setOnClickListener{
                val intent1 = Intent(context, detailViewRecept::class.java)
                intent1.putExtra("ID", words4?.single()?.ID)
                intent1.putExtra("titel", words4?.single()?.title)
                intent1.putExtra("recept", words4?.single()?.recept)
                intent1.putExtra("info", words4?.single()?.info)
                intent1.putExtra("ingredienser", words4?.single()?.ingredienser)
                intent1.putExtra("picture", words4.single()?.picture)
                startActivity(intent1)
            }
            wordViewModel.getID(51).observe(this, Observer { words ->
                words?.let { adapter.setWords(it)
                    this.words5 = words
                    if (words.size >0)
                    {
                        Log.i("Pia8", "ID: ${words.single().ID}")
                        Picasso.get().load(words.single().picture).fit().into(ourFood5)
                    }
                }
            })

            ourFood5.setOnClickListener{
                val intent1 = Intent(context, detailViewRecept::class.java)
                intent1.putExtra("ID", words5?.single()?.ID)
                intent1.putExtra("titel", words5?.single()?.title)
                intent1.putExtra("recept", words5?.single()?.recept)
                intent1.putExtra("info", words5?.single()?.info)
                intent1.putExtra("ingredienser", words5?.single()?.ingredienser)
                intent1.putExtra("picture", words5.single()?.picture)
                startActivity(intent1)
            }
            wordViewModel.getID(61).observe(this, Observer { words ->
                words?.let { adapter.setWords(it)
                    this.todayRecept = words
                    if (words.size >0)
                    {
                        Log.i("Pia8", "ID: ${words.single().ID}")
                        Picasso.get().load(words.single().picture).resize(400, 400).into(todaysRecept)
                    }
                }
            })

            todaysRecept.setOnClickListener{
                val intent1 = Intent(context, detailViewRecept::class.java)
                intent1.putExtra("ID", todayRecept?.single()?.ID)
                intent1.putExtra("titel", todayRecept?.single()?.title)
                intent1.putExtra("recept", todayRecept?.single()?.recept)
                intent1.putExtra("info", todayRecept?.single()?.info)
                intent1.putExtra("ingredienser", todayRecept?.single()?.ingredienser)
                intent1.putExtra("picture", todayRecept.single()?.picture)
                startActivity(intent1)
            }


        }
    if (day == 2)
    {
        wordViewModel.getID(12).observe(this, Observer { words ->
            words1.let { adapter.setWords(it) }

            this.words1 = words!!
            if (words.size >0)
            {
                Log.i("Pia8", "ID: ${words.single().ID}")
                Picasso.get().load(words.single().picture).fit().into(ourFood1)

            }
        })
        ourFood1.setOnClickListener {
            Log.i("Pia8", "Måndag")
            //  if (words.size > 0)
            val intent1 = Intent(context, detailViewRecept::class.java)
            intent1.putExtra("ID", words1?.single()?.ID)
            intent1.putExtra("titel", words1?.single()?.title)
            intent1.putExtra("recept", words1?.single()?.recept)
            intent1.putExtra("info", words1?.single()?.info)
            intent1.putExtra("ingredienser", words1?.single()?.ingredienser)
            intent1.putExtra("picture", words1.single()?.picture)
            startActivity(intent1)
        }
        wordViewModel.getID(22).observe(this, Observer { words ->
            words?.let { adapter.setWords(it)
                this.words2 = words
                if (words.size >0)
                {
                    Log.i("Pia8", "ID: ${words.single().ID}")
                    Picasso.get().load(words.single().picture).fit().into(ourFood2)
                }
            }
        })

        ourFood2.setOnClickListener{
            Log.i("Pia8", "Måndag")

            val intent1 = Intent(context, detailViewRecept::class.java)
            intent1.putExtra("ID", words2?.single()?.ID)
            intent1.putExtra("titel", words2?.single()?.title)
            intent1.putExtra("recept", words2?.single()?.recept)
            intent1.putExtra("info", words2?.single()?.info)
            intent1.putExtra("ingredienser", words2?.single()?.ingredienser)
            intent1.putExtra("picture", words2.single()?.picture)
            startActivity(intent1)
        }
        wordViewModel.getID(32).observe(this, Observer { words ->
            words?.let { adapter.setWords(it)
                this.words3 = words
                if (words.size >0)
                {
                    Log.i("Pia8", "ID: ${words.single().ID}")
                    Picasso.get().load(words.single().picture).fit().into(ourFood3)
                }
            }
        })

        ourFood3.setOnClickListener{
            val intent1 = Intent(context, detailViewRecept::class.java)
            intent1.putExtra("ID", words3?.single()?.ID)
            intent1.putExtra("titel", words3?.single()?.title)
            intent1.putExtra("recept", words3?.single()?.recept)
            intent1.putExtra("info", words3?.single()?.info)
            intent1.putExtra("ingredienser", words3?.single()?.ingredienser)
            intent1.putExtra("picture", words3.single()?.picture)
            startActivity(intent1)
        }
        wordViewModel.getID(42).observe(this, Observer { words ->
            words?.let { adapter.setWords(it)
                this.words4 = words
                if (words.size >0)
                {
                    Log.i("Pia8", "ID: ${words.single().ID}")
                    Picasso.get().load(words.single().picture).fit().into(ourFood4)
                }
            }
        })

        ourFood4.setOnClickListener{
            val intent1 = Intent(context, detailViewRecept::class.java)
            intent1.putExtra("ID", words4?.single()?.ID)
            intent1.putExtra("titel", words4?.single()?.title)
            intent1.putExtra("recept", words4?.single()?.recept)
            intent1.putExtra("info", words4?.single()?.info)
            intent1.putExtra("ingredienser", words4?.single()?.ingredienser)
            intent1.putExtra("picture", words4.single()?.picture)
            startActivity(intent1)
        }
        wordViewModel.getID(52).observe(this, Observer { words ->
            words?.let { adapter.setWords(it)
                this.words5 = words
                if (words.size >0)
                {
                    Log.i("Pia8", "ID: ${words.single().ID}")
                    Picasso.get().load(words.single().picture).fit().into(ourFood5)
                }
            }
        })

        ourFood5.setOnClickListener{
            val intent1 = Intent(context, detailViewRecept::class.java)
            intent1.putExtra("ID", words5?.single()?.ID)
            intent1.putExtra("titel", words5?.single()?.title)
            intent1.putExtra("recept", words5?.single()?.recept)
            intent1.putExtra("info", words5?.single()?.info)
            intent1.putExtra("ingredienser", words5?.single()?.ingredienser)
            intent1.putExtra("picture", words5.single()?.picture)
            startActivity(intent1)
        }
        wordViewModel.getID(62).observe(this, Observer { words ->
            words?.let { adapter.setWords(it)
                this.todayRecept = words
                if (words.size >0)
                {
                    Log.i("Pia8", "ID: ${words.single().ID}")
                    Picasso.get().load(words.single().picture).resize(300, 300).into(todaysRecept)
                }
            }
        })

        todaysRecept.setOnClickListener{
            val intent1 = Intent(context, detailViewRecept::class.java)
            intent1.putExtra("ID", todayRecept?.single()?.ID)
            intent1.putExtra("titel", todayRecept?.single()?.title)
            intent1.putExtra("recept", todayRecept?.single()?.recept)
            intent1.putExtra("info", todayRecept?.single()?.info)
            intent1.putExtra("ingredienser", todayRecept?.single()?.ingredienser)
            intent1.putExtra("picture", todayRecept.single()?.picture)
            startActivity(intent1)
        }


    }
    if (day == 3)
    {
        wordViewModel.getID(13).observe(this, Observer { words ->
            words1.let { adapter.setWords(it) }

            this.words1 = words!!
            if (words.size >0)
            {
                Log.i("Pia8", "ID: ${words.single().ID}")
                Picasso.get().load(words.single().picture).fit().into(ourFood1)

            }
        })
        ourFood1.setOnClickListener {
            Log.i("Pia89", "Tisdag")
            //  if (words.size > 0)
            val intent1 = Intent(context, detailViewRecept::class.java)
            intent1.putExtra("ID", words1?.single()?.ID)
            intent1.putExtra("titel", words1?.single()?.title)
            intent1.putExtra("recept", words1?.single()?.recept)
            intent1.putExtra("info", words1?.single()?.info)
            intent1.putExtra("ingredienser", words1?.single()?.ingredienser)
            intent1.putExtra("picture", words1.single()?.picture)
            startActivity(intent1)
        }
        wordViewModel.getID(23).observe(this, Observer { words ->
            words?.let { adapter.setWords(it)
                this.words2 = words
                if (words.size >0)
                {
                    Log.i("Pia8", "ID: ${words.single().ID}")
                    Picasso.get().load(words.single().picture).fit().into(ourFood2)
                }
            }
        })

        ourFood2.setOnClickListener{
            val intent1 = Intent(context, detailViewRecept::class.java)
            intent1.putExtra("ID", words2?.single()?.ID)
            intent1.putExtra("titel", words2?.single()?.title)
            intent1.putExtra("recept", words2?.single()?.recept)
            intent1.putExtra("info", words2?.single()?.info)
            intent1.putExtra("ingredienser", words2?.single()?.ingredienser)
            intent1.putExtra("picture", words2.single()?.picture)
            startActivity(intent1)
        }
        wordViewModel.getID(33).observe(this, Observer { words ->
            words?.let { adapter.setWords(it)
                this.words3 = words
                if (words.size >0)
                {
                    Log.i("Pia8", "ID: ${words.single().ID}")
                    Picasso.get().load(words.single().picture).fit().into(ourFood3)
                }
            }
        })

        ourFood3.setOnClickListener{
            val intent1 = Intent(context, detailViewRecept::class.java)
            intent1.putExtra("ID", words3?.single()?.ID)
            intent1.putExtra("titel", words3?.single()?.title)
            intent1.putExtra("recept", words3?.single()?.recept)
            intent1.putExtra("info", words3?.single()?.info)
            intent1.putExtra("ingredienser", words3?.single()?.ingredienser)
            intent1.putExtra("picture", words3.single()?.picture)
            startActivity(intent1)
        }
        wordViewModel.getID(43).observe(this, Observer { words ->
            words?.let { adapter.setWords(it)
                this.words4 = words
                if (words.size >0)
                {
                    Log.i("Pia8", "ID: ${words.single().ID}")
                    Picasso.get().load(words.single().picture).fit().into(ourFood4)
                }
            }
        })

        ourFood4.setOnClickListener{
            val intent1 = Intent(context, detailViewRecept::class.java)
            intent1.putExtra("ID", words4?.single()?.ID)
            intent1.putExtra("titel", words4?.single()?.title)
            intent1.putExtra("recept", words4?.single()?.recept)
            intent1.putExtra("info", words4?.single()?.info)
            intent1.putExtra("ingredienser", words4?.single()?.ingredienser)
            intent1.putExtra("picture", words4.single()?.picture)
            startActivity(intent1)
        }
        wordViewModel.getID(53).observe(this, Observer { words ->
            words?.let { adapter.setWords(it)
                this.words5 = words
                if (words.size >0)
                {
                    Log.i("Pia8", "ID: ${words.single().ID}")
                    Picasso.get().load(words.single().picture).fit().into(ourFood5)
                }
            }
        })

        ourFood5.setOnClickListener{
            val intent1 = Intent(context, detailViewRecept::class.java)
            intent1.putExtra("ID", words5?.single()?.ID)
            intent1.putExtra("titel", words5?.single()?.title)
            intent1.putExtra("recept", words5?.single()?.recept)
            intent1.putExtra("info", words5?.single()?.info)
            intent1.putExtra("ingredienser", words5?.single()?.ingredienser)
            intent1.putExtra("picture", words5.single()?.picture)
            startActivity(intent1)
        }
        wordViewModel.getID(63).observe(this, Observer { words ->
            words?.let { adapter.setWords(it)
                this.todayRecept = words
                if (words.size >0)
                {
                    Log.i("Pia8", "ID: ${words.single().ID}")
                    Picasso.get().load(words.single().picture).resize(400, 400).into(todaysRecept)
                }
            }
        })

        todaysRecept.setOnClickListener{
            val intent1 = Intent(context, detailViewRecept::class.java)
            intent1.putExtra("ID", todayRecept?.single()?.ID)
            intent1.putExtra("titel", todayRecept?.single()?.title)
            intent1.putExtra("recept", todayRecept?.single()?.recept)
            intent1.putExtra("info", todayRecept?.single()?.info)
            intent1.putExtra("ingredienser", todayRecept?.single()?.ingredienser)
            intent1.putExtra("picture", todayRecept.single()?.picture)
            startActivity(intent1)
        }

    }
    if (day == 4) {

        wordViewModel.getID(14).observe(this, Observer { words ->
            words1.let { adapter.setWords(it) }

            this.words1 = words!!
            if (words.size >0)
            {
                Log.i("Pia8", "ID: ${words.single().ID}")
                Picasso.get().load(words.single().picture).fit().into(ourFood1)

            }
        })
        ourFood1.setOnClickListener {
            Log.i("Pia89", "Onsdag")
            //  if (words.size > 0)
            val intent1 = Intent(context, detailViewRecept::class.java)
            intent1.putExtra("ID", words1?.single()?.ID)
            intent1.putExtra("titel", words1?.single()?.title)
            intent1.putExtra("recept", words1?.single()?.recept)
            intent1.putExtra("info", words1?.single()?.info)
            intent1.putExtra("ingredienser", words1?.single()?.ingredienser)
            intent1.putExtra("picture", words1.single()?.picture)
            startActivity(intent1)
        }
        wordViewModel.getID(24).observe(this, Observer { words ->
            words?.let { adapter.setWords(it)
                this.words2 = words
                if (words.size >0)
                {
                    Log.i("Pia8", "ID: ${words.single().ID}")
                    Picasso.get().load(words.single().picture).fit().into(ourFood2)
                }
            }
        })

        ourFood2.setOnClickListener{
            val intent1 = Intent(context, detailViewRecept::class.java)
            intent1.putExtra("ID", words2?.single()?.ID)
            intent1.putExtra("titel", words2?.single()?.title)
            intent1.putExtra("recept", words2?.single()?.recept)
            intent1.putExtra("info", words2?.single()?.info)
            intent1.putExtra("ingredienser", words2?.single()?.ingredienser)
            intent1.putExtra("picture", words2.single()?.picture)
            startActivity(intent1)
        }
        wordViewModel.getID(34).observe(this, Observer { words ->
            words?.let { adapter.setWords(it)
                this.words3 = words
                if (words.size >0)
                {
                    Log.i("Pia8", "ID: ${words.single().ID}")
                    Picasso.get().load(words.single().picture).fit().into(ourFood3)
                }
            }
        })

        ourFood3.setOnClickListener{
            val intent1 = Intent(context, detailViewRecept::class.java)
            intent1.putExtra("ID", words3?.single()?.ID)
            intent1.putExtra("titel", words3?.single()?.title)
            intent1.putExtra("recept", words3?.single()?.recept)
            intent1.putExtra("info", words3?.single()?.info)
            intent1.putExtra("ingredienser", words3?.single()?.ingredienser)
            intent1.putExtra("picture", words3.single()?.picture)
            startActivity(intent1)
        }
        wordViewModel.getID(44).observe(this, Observer { words ->
            words?.let { adapter.setWords(it)
                this.words4 = words
                if (words.size >0)
                {
                    Log.i("Pia8", "ID: ${words.single().ID}")
                    Picasso.get().load(words.single().picture).fit().into(ourFood4)
                }
            }
        })

        ourFood4.setOnClickListener{
            val intent1 = Intent(context, detailViewRecept::class.java)
            intent1.putExtra("ID", words4?.single()?.ID)
            intent1.putExtra("titel", words4?.single()?.title)
            intent1.putExtra("recept", words4?.single()?.recept)
            intent1.putExtra("info", words4?.single()?.info)
            intent1.putExtra("ingredienser", words4?.single()?.ingredienser)
            intent1.putExtra("picture", words4.single()?.picture)
            startActivity(intent1)
        }
        wordViewModel.getID(54).observe(this, Observer { words ->
            words?.let { adapter.setWords(it)
                this.words5 = words
                if (words.size >0)
                {
                    Log.i("Pia8", "ID: ${words.single().ID}")
                    Picasso.get().load(words.single().picture).fit().into(ourFood5)
                }
            }
        })

        ourFood5.setOnClickListener{
            val intent1 = Intent(context, detailViewRecept::class.java)
            intent1.putExtra("ID", words5?.single()?.ID)
            intent1.putExtra("titel", words5?.single()?.title)
            intent1.putExtra("recept", words5?.single()?.recept)
            intent1.putExtra("info", words5?.single()?.info)
            intent1.putExtra("ingredienser", words5?.single()?.ingredienser)
            intent1.putExtra("picture", words5.single()?.picture)
            startActivity(intent1)
        }
        wordViewModel.getID(64).observe(this, Observer { words ->
            words?.let { adapter.setWords(it)
                this.todayRecept = words
                if (words.size >0)
                {
                    Log.i("Pia8", "ID: ${words.single().ID}")
                    Picasso.get().load(words.single().picture).resize(400, 400).into(todaysRecept)
                }
            }
        })

        todaysRecept.setOnClickListener{
            val intent1 = Intent(context, detailViewRecept::class.java)
            intent1.putExtra("ID", todayRecept?.single()?.ID)
            intent1.putExtra("titel", todayRecept?.single()?.title)
            intent1.putExtra("recept", todayRecept?.single()?.recept)
            intent1.putExtra("info", todayRecept?.single()?.info)
            intent1.putExtra("ingredienser", todayRecept?.single()?.ingredienser)
            intent1.putExtra("picture", todayRecept.single()?.picture)
            startActivity(intent1)
        }

    }
    if (day == 5)
    {    wordViewModel.getID(15).observe(this, Observer { words ->
        words1.let { adapter.setWords(it) }

        this.words1 = words!!
        if (words.size >0)
        {
            Log.i("Pia8", "ID: ${words.single().ID}")
            Picasso.get().load(words.single().picture).fit().into(ourFood1)

        }
    })
        ourFood1.setOnClickListener {
            Log.i("Pia89", "Torsdag")
            //  if (words.size > 0)
            val intent1 = Intent(context, detailViewRecept::class.java)
            intent1.putExtra("ID", words1?.single()?.ID)
            intent1.putExtra("titel", words1?.single()?.title)
            intent1.putExtra("recept", words1?.single()?.recept)
            intent1.putExtra("info", words1?.single()?.info)
            intent1.putExtra("ingredienser", words1?.single()?.ingredienser)
            intent1.putExtra("picture", words1.single()?.picture)
            startActivity(intent1)
        }
        wordViewModel.getID(25).observe(this, Observer { words ->
            words?.let { adapter.setWords(it)
                this.words2 = words
                if (words.size >0)
                {
                    Log.i("Pia8", "ID: ${words.single().ID}")
                    Picasso.get().load(words.single().picture).fit().into(ourFood2)
                }
            }
        })

        ourFood2.setOnClickListener{
            val intent1 = Intent(context, detailViewRecept::class.java)
            intent1.putExtra("ID", words2?.single()?.ID)
            intent1.putExtra("titel", words2?.single()?.title)
            intent1.putExtra("recept", words2?.single()?.recept)
            intent1.putExtra("info", words2?.single()?.info)
            intent1.putExtra("ingredienser", words2?.single()?.ingredienser)
            intent1.putExtra("picture", words2.single()?.picture)
            startActivity(intent1)
        }
        wordViewModel.getID(35).observe(this, Observer { words ->
            words?.let { adapter.setWords(it)
                this.words3 = words
                if (words.size >0)
                {
                    Log.i("Pia8", "ID: ${words.single().ID}")
                    Picasso.get().load(words.single().picture).fit().into(ourFood3)
                }
            }
        })

        ourFood3.setOnClickListener{
            val intent1 = Intent(context, detailViewRecept::class.java)
            intent1.putExtra("ID", words3?.single()?.ID)
            intent1.putExtra("titel", words3?.single()?.title)
            intent1.putExtra("recept", words3?.single()?.recept)
            intent1.putExtra("info", words3?.single()?.info)
            intent1.putExtra("ingredienser", words3?.single()?.ingredienser)
            intent1.putExtra("picture", words3.single()?.picture)
            startActivity(intent1)
        }
        wordViewModel.getID(45).observe(this, Observer { words ->
            words?.let { adapter.setWords(it)
                this.words4 = words
                if (words.size >0)
                {
                    Log.i("Pia8", "ID: ${words.single().ID}")
                    Picasso.get().load(words.single().picture).fit().into(ourFood4)
                }
            }
        })

        ourFood4.setOnClickListener{
            val intent1 = Intent(context, detailViewRecept::class.java)
            intent1.putExtra("ID", words4?.single()?.ID)
            intent1.putExtra("titel", words4?.single()?.title)
            intent1.putExtra("recept", words4?.single()?.recept)
            intent1.putExtra("info", words4?.single()?.info)
            intent1.putExtra("ingredienser", words4?.single()?.ingredienser)
            intent1.putExtra("picture", words4.single()?.picture)
            startActivity(intent1)
        }
        wordViewModel.getID(55).observe(this, Observer { words ->
            words?.let { adapter.setWords(it)
                this.words5 = words
                if (words.size >0)
                {
                    Log.i("Pia8", "ID: ${words.single().ID}")
                    Picasso.get().load(words.single().picture).fit().into(ourFood5)
                }
            }
        })

        ourFood5.setOnClickListener{
            val intent1 = Intent(context, detailViewRecept::class.java)
            intent1.putExtra("ID", words5?.single()?.ID)
            intent1.putExtra("titel", words5?.single()?.title)
            intent1.putExtra("recept", words5?.single()?.recept)
            intent1.putExtra("info", words5?.single()?.info)
            intent1.putExtra("ingredienser", words5?.single()?.ingredienser)
            intent1.putExtra("picture", words5.single()?.picture)
            startActivity(intent1)
        }
        wordViewModel.getID(65).observe(this, Observer { words ->
            words?.let { adapter.setWords(it)
                this.todayRecept = words
                if (words.size >0)
                {
                    Log.i("Pia8", "ID: ${words.single().ID}")
                    Picasso.get().load(words.single().picture).resize(400, 400).into(todaysRecept)
                }
            }
        })

        todaysRecept.setOnClickListener{
            val intent1 = Intent(context, detailViewRecept::class.java)
            intent1.putExtra("ID", todayRecept?.single()?.ID)
            intent1.putExtra("titel", todayRecept?.single()?.title)
            intent1.putExtra("recept", todayRecept?.single()?.recept)
            intent1.putExtra("info", todayRecept?.single()?.info)
            intent1.putExtra("ingredienser", todayRecept?.single()?.ingredienser)
            intent1.putExtra("picture", todayRecept.single()?.picture)
            startActivity(intent1)
        }


    }
    if (day == 6)
    {
        wordViewModel.getID(43).observe(this, Observer { words ->
            words1.let { adapter.setWords(it) }

            this.words1 = words!!
            if (words.size >0)
            {
                Log.i("Pia8", "ID: ${words.single().ID}")
                Picasso.get().load(words.single().picture).fit().into(ourFood1)

            }
        })
        ourFood1.setOnClickListener {
            Log.i("Pia89", "Fredag")
            //  if (words.size > 0)
            val intent1 = Intent(context, detailViewRecept::class.java)
            intent1.putExtra("ID", words1?.single()?.ID)
            intent1.putExtra("titel", words1?.single()?.title)
            intent1.putExtra("recept", words1?.single()?.recept)
            intent1.putExtra("info", words1?.single()?.info)
            intent1.putExtra("ingredienser", words1?.single()?.ingredienser)
            intent1.putExtra("picture", words1.single()?.picture)
            startActivity(intent1)
        }
        wordViewModel.getID(44).observe(this, Observer { words ->
            words?.let { adapter.setWords(it)
                this.words2 = words
                if (words.size >0)
                {
                    Log.i("Pia8", "ID: ${words.single().ID}")
                    Picasso.get().load(words.single().picture).fit().into(ourFood2)
                }
            }
        })

        ourFood2.setOnClickListener{
            val intent1 = Intent(context, detailViewRecept::class.java)
            intent1.putExtra("ID", words2?.single()?.ID)
            intent1.putExtra("titel", words2?.single()?.title)
            intent1.putExtra("recept", words2?.single()?.recept)
            intent1.putExtra("info", words2?.single()?.info)
            intent1.putExtra("ingredienser", words2?.single()?.ingredienser)
            intent1.putExtra("picture", words2.single()?.picture)
            startActivity(intent1)
        }
        wordViewModel.getID(11).observe(this, Observer { words ->
            words?.let { adapter.setWords(it)
                this.words3 = words
                if (words.size >0)
                {
                    Log.i("Pia8", "ID: ${words.single().ID}")
                    Picasso.get().load(words.single().picture).fit().into(ourFood3)
                }
            }
        })

        ourFood3.setOnClickListener{
            val intent1 = Intent(context, detailViewRecept::class.java)
            intent1.putExtra("ID", words3?.single()?.ID)
            intent1.putExtra("titel", words3?.single()?.title)
            intent1.putExtra("recept", words3?.single()?.recept)
            intent1.putExtra("info", words3?.single()?.info)
            intent1.putExtra("ingredienser", words3?.single()?.ingredienser)
            intent1.putExtra("picture", words3.single()?.picture)
            startActivity(intent1)
        }
        wordViewModel.getID(22).observe(this, Observer { words ->
            words?.let { adapter.setWords(it)
                this.words4 = words
                if (words.size >0)
                {
                    Log.i("Pia8", "ID: ${words.single().ID}")
                    Picasso.get().load(words.single().picture).fit().into(ourFood4)
                }
            }
        })

        ourFood4.setOnClickListener{
            val intent1 = Intent(context, detailViewRecept::class.java)
            intent1.putExtra("ID", words4?.single()?.ID)
            intent1.putExtra("titel", words4?.single()?.title)
            intent1.putExtra("recept", words4?.single()?.recept)
            intent1.putExtra("info", words4?.single()?.info)
            intent1.putExtra("ingredienser", words4?.single()?.ingredienser)
            intent1.putExtra("picture", words4.single()?.picture)
            startActivity(intent1)
        }
        wordViewModel.getID(63).observe(this, Observer { words ->
            words?.let { adapter.setWords(it)
                this.words5 = words
                if (words.size >0)
                {
                    Log.i("Pia8", "ID: ${words.single().ID}")
                    Picasso.get().load(words.single().picture).fit().into(ourFood5)
                }
            }
        })

        ourFood5.setOnClickListener{
            val intent1 = Intent(context, detailViewRecept::class.java)
            intent1.putExtra("ID", words5?.single()?.ID)
            intent1.putExtra("titel", words5?.single()?.title)
            intent1.putExtra("recept", words5?.single()?.recept)
            intent1.putExtra("info", words5?.single()?.info)
            intent1.putExtra("ingredienser", words5?.single()?.ingredienser)
            intent1.putExtra("picture", words5.single()?.picture)
            startActivity(intent1)
        }
        wordViewModel.getID(41).observe(this, Observer { words ->
            words?.let { adapter.setWords(it)
                this.todayRecept = words
                if (words.size >0)
                {
                    Log.i("Pia8", "ID: ${words.single().ID}")
                    Picasso.get().load(words.single().picture).resize(400, 400).into(todaysRecept)
                }
            }
        })

        todaysRecept.setOnClickListener{
            val intent1 = Intent(context, detailViewRecept::class.java)
            intent1.putExtra("ID", todayRecept?.single()?.ID)
            intent1.putExtra("titel", todayRecept?.single()?.title)
            intent1.putExtra("recept", todayRecept?.single()?.recept)
            intent1.putExtra("info", todayRecept?.single()?.info)
            intent1.putExtra("ingredienser", todayRecept?.single()?.ingredienser)
            intent1.putExtra("picture", todayRecept.single()?.picture)
            startActivity(intent1)
        }


    }
    if (day == 7) {
        wordViewModel.getID(13).observe(this, Observer { words ->
            words1.let { adapter.setWords(it) }

            this.words1 = words!!
            if (words.size >0)
            {
                Log.i("Pia8", "ID: ${words.single().ID}")
                Picasso.get().load(words.single().picture).fit().into(ourFood1)

            }
        })
        ourFood1.setOnClickListener {
            Log.i("Pia89", "Lördag")
            //  if (words.size > 0)
            val intent1 = Intent(context, detailViewRecept::class.java)
            intent1.putExtra("ID", words1?.single()?.ID)
            intent1.putExtra("titel", words1?.single()?.title)
            intent1.putExtra("recept", words1?.single()?.recept)
            intent1.putExtra("info", words1?.single()?.info)
            intent1.putExtra("ingredienser", words1?.single()?.ingredienser)
            intent1.putExtra("picture", words1.single()?.picture)
            startActivity(intent1)
        }
        wordViewModel.getID(14).observe(this, Observer { words ->
            words?.let { adapter.setWords(it)
                this.words2 = words
                if (words.size >0)
                {
                    Log.i("Pia8", "ID: ${words.single().ID}")
                    Picasso.get().load(words.single().picture).fit().into(ourFood2)
                }
            }
        })

        ourFood2.setOnClickListener{
            val intent1 = Intent(context, detailViewRecept::class.java)
            intent1.putExtra("ID", words2?.single()?.ID)
            intent1.putExtra("titel", words2?.single()?.title)
            intent1.putExtra("recept", words2?.single()?.recept)
            intent1.putExtra("info", words2?.single()?.info)
            intent1.putExtra("ingredienser", words2?.single()?.ingredienser)
            intent1.putExtra("picture", words2.single()?.picture)
            startActivity(intent1)
        }
        wordViewModel.getID(15).observe(this, Observer { words ->
            words?.let { adapter.setWords(it)
                this.words3 = words
                if (words.size >0)
                {
                    Log.i("Pia8", "ID: ${words.single().ID}")
                    Picasso.get().load(words.single().picture).fit().into(ourFood3)
                }
            }
        })

        ourFood3.setOnClickListener{
            val intent1 = Intent(context, detailViewRecept::class.java)
            intent1.putExtra("ID", words3?.single()?.ID)
            intent1.putExtra("titel", words3?.single()?.title)
            intent1.putExtra("recept", words3?.single()?.recept)
            intent1.putExtra("info", words3?.single()?.info)
            intent1.putExtra("ingredienser", words3?.single()?.ingredienser)
            intent1.putExtra("picture", words3.single()?.picture)
            startActivity(intent1)
        }
        wordViewModel.getID(31).observe(this, Observer { words ->
            words?.let { adapter.setWords(it)
                this.words4 = words
                if (words.size >0)
                {
                    Log.i("Pia8", "ID: ${words.single().ID}")
                    Picasso.get().load(words.single().picture).fit().into(ourFood4)
                }
            }
        })

        ourFood4.setOnClickListener{
            val intent1 = Intent(context, detailViewRecept::class.java)
            intent1.putExtra("ID", words4?.single()?.ID)
            intent1.putExtra("titel", words4?.single()?.title)
            intent1.putExtra("recept", words4?.single()?.recept)
            intent1.putExtra("info", words4?.single()?.info)
            intent1.putExtra("ingredienser", words4?.single()?.ingredienser)
            intent1.putExtra("picture", words4.single()?.picture)
            startActivity(intent1)
        }
        wordViewModel.getID(32).observe(this, Observer { words ->
            words?.let { adapter.setWords(it)
                this.words5 = words
                if (words.size >0)
                {
                    Log.i("Pia8", "ID: ${words.single().ID}")
                    Picasso.get().load(words.single().picture).fit().into(ourFood5)
                }
            }
        })

        ourFood5.setOnClickListener{
            val intent1 = Intent(context, detailViewRecept::class.java)
            intent1.putExtra("ID", words5?.single()?.ID)
            intent1.putExtra("titel", words5?.single()?.title)
            intent1.putExtra("recept", words5?.single()?.recept)
            intent1.putExtra("info", words5?.single()?.info)
            intent1.putExtra("ingredienser", words5?.single()?.ingredienser)
            intent1.putExtra("picture", words5.single()?.picture)
            startActivity(intent1)
        }
        wordViewModel.getID(33).observe(this, Observer { words ->
            words?.let { adapter.setWords(it)
                this.todayRecept = words
                if (words.size >0)
                {
                    Log.i("Pia8", "ID: ${words.single().ID}")
                    Picasso.get().load(words.single().picture).resize(400, 400).into(todaysRecept)
                }
            }
        })

        todaysRecept.setOnClickListener{
            val intent1 = Intent(context, detailViewRecept::class.java)
            intent1.putExtra("ID", todayRecept?.single()?.ID)
            intent1.putExtra("titel", todayRecept?.single()?.title)
            intent1.putExtra("recept", todayRecept?.single()?.recept)
            intent1.putExtra("info", todayRecept?.single()?.info)
            intent1.putExtra("ingredienser", todayRecept?.single()?.ingredienser)
            intent1.putExtra("picture", todayRecept.single()?.picture)
            startActivity(intent1)
        }

    }





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

