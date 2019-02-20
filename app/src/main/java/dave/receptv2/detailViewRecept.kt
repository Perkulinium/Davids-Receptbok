package dave.receptv2

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.graphics.Color
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



        titleText.setText(intentDetail)
        receptText.setText(receptDetail)
        info.setText(infoDetail)
        ingrediText.setText(ingredienserDetail)
        // imageView2.setImageBitmap(pictureDetail.to)


        Picasso.get().load(pictureDetail).resize(350, 350).into(imageView2)

        val adapter = WordListAdapter(this)

        wordViewModel = ViewModelProviders.of(this).get(WordViewModel::class.java)

        var iD = intentID
        Log.i("Pia8", "ID: $iD")

        wordViewModel.getID(iD).observe(this, Observer { words ->
            words?.let { adapter.setWords(it) }
            this.words = words!!



            if(words.single().favoriter == true)
            {
                favButton.setBackgroundColor(Color.BLACK)
                click = false
            }
            if(words.single().favoriter == false)
            {
                favButton.setBackgroundColor(Color.WHITE)
                click = true
            }

            favButton.setOnClickListener {
                if (click == true)
                {
                    var theWord = words.single()
                    theWord.favoriter = true
                    wordViewModel.update(theWord)


                }
                else
                {
                    var theWord = words.single()
                    theWord.favoriter = false
                    wordViewModel.update(theWord)

                }
            }

            Log.i("Pia8", "Favoriter: ${words.single().favoriter}")
        })
      }
    }
