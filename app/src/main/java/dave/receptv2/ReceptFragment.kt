package dave.receptv2

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.activity_genre_recept_.*
import kotlinx.android.synthetic.main.fragment_recept.*


class ReceptFragment : Fragment() {

    var recept: ArrayList<String> = ArrayList()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_recept, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        meatKnapp.setOnClickListener {
            val intent = Intent(context, GenreRecept_Activity::class.java)
            recept.clear()
            intent.putExtra("textNamn", "Kött")
            startActivity(intent)
        }
        forrattKnapp.setOnClickListener {
            val intent1 = Intent(context, GenreRecept_Activity::class.java)

            intent1.putExtra("textNamn", "Förrätt")
            startActivity(intent1)
        }

        vegetarisktKnapp.setOnClickListener {
            val intent1 = Intent(context, GenreRecept_Activity::class.java)
            intent1.putExtra("textNamn", "Vegetariskt")
            startActivity(intent1)
        }

        fishKnapp.setOnClickListener {

            val intent1 = Intent(context, GenreRecept_Activity::class.java)
            intent1.putExtra("textNamn", "Fisk")
            startActivity(intent1)
        }


        veganKnapp.setOnClickListener {
            val intent1 = Intent(context, GenreRecept_Activity::class.java)
            intent1.putExtra("textNamn", "Veganskt")

            startActivity(intent1)
        }

        dessertKnapp.setOnClickListener {
            val intent1 = Intent(context, GenreRecept_Activity::class.java)
            intent1.putExtra("textNamn", "Dessert")

            startActivity(intent1)
        }
    }







}
