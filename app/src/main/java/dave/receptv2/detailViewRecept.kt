package dave.receptv2

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detail_view_recept.*

class detailViewRecept : AppCompatActivity() {




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_view_recept)
        lateinit var wordViewModel: WordViewModel


        wordViewModel = ViewModelProviders.of(this).get(WordViewModel::class.java)


        //   var intentID = intent.getStringExtra("ID")

//       Log.i("Pia8", intentID)

        var intentDetail = intent.getStringExtra("titel")
        var receptDetail = intent.getStringExtra("recept")
        var ingredienserDetail = intent.getStringExtra("ingredienser")
        var infoDetail = intent.getStringExtra("info")
       // var pictureDetail = intent.getStringExtra("picture")
        var pictureDetail = intent.getIntExtra("picture", -1)

        var testPic = ""

     //   val recept = intent.getStringArrayListExtra("Meat0")

        titleText.setText(intentDetail)
        receptText.setText(receptDetail)
        info.setText(infoDetail)
        ingrediText.setText(ingredienserDetail)
       // imageView2.setImageBitmap(pictureDetail.to)



            Picasso.get().load(pictureDetail).resize(350, 350).into(imageView2)


    }
}
