package dave.receptv2

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detail_view_recept.*

class detailViewRecept : AppCompatActivity() {

var click = true
   // lateinit var words : List<Word>


   // lateinit var words : Word
    lateinit var wordViewModel: WordViewModel
    var words = emptyList<Word>() // Cached copy of words
//    val adapter = WordListAdapter(this)




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_view_recept)


        wordViewModel = ViewModelProviders.of(this).get(WordViewModel::class.java)




//       Log.i("Pia8", intentID)

        var intentDetail = intent.getStringExtra("titel")
        var receptDetail = intent.getStringExtra("recept")
        var ingredienserDetail = intent.getStringExtra("ingredienser")
        var infoDetail = intent.getStringExtra("info")
       // var pictureDetail = intent.getStringExtra("picture")
        var pictureDetail = intent.getIntExtra("picture", -1)
        var intentID = intent.getIntExtra("ID", -1)

        //  Log.i("Pia50", "ID: ${intentID}")


        var testPic = ""

     //   val recept = intent.getStringArrayListExtra("Meat0")

        titleText.setText(intentDetail)
        receptText.setText(receptDetail)
        info.setText(infoDetail)
        ingrediText.setText(ingredienserDetail)
       // imageView2.setImageBitmap(pictureDetail.to)





            Picasso.get().load(pictureDetail).resize(350, 350).into(imageView2)

        val adapter = WordListAdapter(this)

        wordViewModel = ViewModelProviders.of(this).get(WordViewModel::class.java)



      //  Log.i("Pia8", "$intentID")
      //  Log.i("Pia8", "${words?.single()?.ID}")

        var iD = intentID
        favButton.setOnClickListener {
            if (click)
            {

                wordViewModel.getID(iD).observe(this, Observer { words ->
                    words?.let { adapter.setWords(it)}
                    this.words = words!!
                    Log.i("Pia8", "ID: $iD")

                        val theWord = words.single()
                    //NY TEST
                        theWord.favoriter = true

                        //wordViewModel.up

                        //  wordViewModel.update(true)
                       words.single().favoriter = true
                       Log.i("Pia8", "Favoriter: ${words.single().favoriter}")
                })




                click = false


            } else {
                Log.i("Pia8", "ID: $iD")

                //  wordViewModel.update(false)
                words.single().favoriter = false
                Log.i("Pia8", "Favoriter: ${words.single().favoriter}")

                click = true
            }


        }

//save()


    }

    fun save()
    {




    }

}
