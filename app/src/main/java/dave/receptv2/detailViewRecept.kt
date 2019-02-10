package dave.receptv2

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detail_view_recept.*

class detailViewRecept : AppCompatActivity() {
    lateinit var adapter : WordListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_view_recept)

        var intentDetail = intent.getStringExtra("titel")
        var receptDetail = intent.getStringExtra("recept")
        var ingredienserDetail = intent.getStringExtra("ingredienser")
        var infoDetail = intent.getStringExtra("info")
        var pictureDetail = intent.getStringExtra("picture")

        var testPic = ""

     //   val recept = intent.getStringArrayListExtra("Meat0")

        textView.setText(intentDetail)
        textView2.setText(receptDetail)
        Picasso.get()
            .load("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcT5sld6AcJjX6VvfNorWI99UgQyV_JlQy8jE8lOWn1XsFbhuciN1A")
            .placeholder(R.drawable.abc_ic_arrow_drop_right_black_24dp)
            .error(R.drawable.abc_ic_arrow_drop_right_black_24dp)
            .into(imageView2);

        Log.i("Pia8", "${textView.setText(intentDetail)}")


        // intent.getStringExtra("meat0", detailText.text)
    }
}
