package dave.receptv2

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {



    var homeFrag : HomeFragment? = null
    var receptFrag : ReceptFragment? = null
    var favoritFrag : FavoritFragment? = null

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {

                if(homeFrag == null)
                {
                    homeFrag = HomeFragment()


                }
                supportFragmentManager.beginTransaction().replace(R.id.main_fragment_holder, homeFrag!!).commit()

                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_dashboard -> {

                if(receptFrag == null)
                {
                    receptFrag = ReceptFragment()

                }
                supportFragmentManager.beginTransaction().replace(R.id.main_fragment_holder, receptFrag!!).commit()

                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_notifications -> {
                if (favoritFrag == null)
                {
                    favoritFrag = FavoritFragment()
                }

                supportFragmentManager.beginTransaction().replace(R.id.main_fragment_holder, favoritFrag!!).commit()
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        homeFrag = HomeFragment()

        supportFragmentManager.beginTransaction().replace(R.id.main_fragment_holder, homeFrag!!).commit()

          }
}
