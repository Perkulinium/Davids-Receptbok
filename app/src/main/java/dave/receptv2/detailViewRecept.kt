package dave.receptv2

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import kotlinx.android.synthetic.main.activity_detail_view_recept.*

class detailViewRecept : AppCompatActivity() {
    lateinit var adapter : WordListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_view_recept)

        var intentDetail = intent.getStringExtra("titel")
        var receptDetail = intent.getStringExtra("recept")


     //   val recept = intent.getStringArrayListExtra("Meat0")

        textView.setText(intentDetail)
        textView2.setText(receptDetail)

        Log.i("Pia8", "${textView.setText(intentDetail)}")


        // intent.getStringExtra("meat0", detailText.text)
    }
}
