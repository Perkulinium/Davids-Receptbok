package dave.receptv2

import android.arch.persistence.db.SupportSQLiteDatabase
import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import kotlinx.coroutines.experimental.CoroutineScope
import kotlinx.coroutines.experimental.Dispatchers
import kotlinx.coroutines.experimental.IO
import kotlinx.coroutines.experimental.launch

@Database(entities = [Word::class], version = 12)
abstract class WordRoomDatabase : RoomDatabase() {

    abstract fun wordDao(): WordDao

    companion object {
        @Volatile
        private var INSTANCE: WordRoomDatabase? = null

        fun getDatabase(
            context: Context,
            scope: CoroutineScope
        ): WordRoomDatabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    WordRoomDatabase::class.java,
                    "word_database"
                )
                    // Wipes and rebuilds instead of migrating if no Migration object.
                    // Migration is not part of this codelab.
                    .fallbackToDestructiveMigration()
                    .addCallback(WordDatabaseCallback(scope))

                    .build()
                INSTANCE = instance
                // return instance
                instance
            }
        }


        private class WordDatabaseCallback(

            private val scope: CoroutineScope
        ) : RoomDatabase.Callback() {
            /**
             * Override the onOpen method to populate the database.
             * For this sample, we clear the database every time it is created or opened.
             */

            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                // If you want to keep the data through app restarts,
                // comment out the following line.


                   INSTANCE?.let { database ->


                       scope.launch(Dispatchers.IO) {
                           populateDatabase(database.wordDao())
                       }




                   }


            }



        }


        /**
         * Populate the database in a new coroutine.
         * If you want to start with more words, just add them.
         */
        fun populateDatabase(wordDao: WordDao) {
            // Start the app with a clean database every time.
            // Not needed if you only populate on creation.


            //wordDao.deleteAll()


            // wordDao.updateFavorite(favoriter = true)

//FÖRRÄTT





            var word = Word(
                "Toast skagen",
                "Tillagning\n 1. Skala och finhacka rödlök. Skölj och finhacka dill.\n 2. Sila av räkorna.\n 3. Blanda räkor, rödlök, dill, creme fraiche och majonnäs i en bunke. Tillsätt rivet citronskal och ca 1 tsk citronsaft. Smaka av med salt och peppar.\n 4. Skär bort kanterna från 4 brödskivor och skär brödskivorna i trekanter. Stek brödskivorna gyllene på båda sidor i smör i en varm stekpanna.\n 5. Lägg brödskivorna på fina tallrikar och toppa med skagenröra, löjrom och en dillkvist. Servera den ena brödskivan vid sidan av tillsammans med en citronklyfta.",
            "Forrat",
            "Ingredienser\n 0,5 st röd lök\n Färsk dill\n 240 gram handskalade räkor\n 4 msk crème fraiche\n 3 msk majonnäs\n 1 st citron\n 3 krm salt\n 40 gram amerikansk löjrom\n 1 krm svartpeppar\n 200 gram valfri toast\n 25 gram smör\n\n",
            R.drawable.bildtoastskagen,
            "15 minuter, 4 portioner",
            11,
            false
            )
            wordDao.insert(word)

            word = Word(
                "Moules marinieres",
                "Tillagning\n 1. Tvätta blåmusslorna noga under rinnande vatten. Knacka öppna musslor försiktigt mot diskbänken. De blåmusslor som inte sluter sig slängs.\n 2. Skala och finhacka schalottenlök och vitlök. stekt mjukt i olivoljan utan att det tar färg.\n 3. Tillsätt blåmusslorna och häll på vin, fond, vatten och grädde. Lägg på lock och låt koka i 2-3 min, eller tills musslorna är färdiga och har öppnat sig. Släng de musslor som inte öppnat sig.\n 4. Lyft ur musslorna och låt soppan koka ner lite. Smaka av med salt och peppar.\n 5. Lägg upp musslorna i en stor skål och slå över musselbuljongen och toppa med grovhackad persilja.",
            "Forrat",
            "Ingredienser\n 1 kg blåmusslor, färska\n 2 st Schalottenlökar\n 3 klyftor vitlök\n 1 msk olivolja\n 2 dl vitt vin\n 2 msk fiskfond\n 2 dl vatten\n 2 dl vispgrädde\n 1 krm salt\n 1 krm svartpeppar\n  Persilja\n\n",
            R.drawable.moule,
            "30 minuter, 4 portioner",
            12,
            false
            )
            wordDao.insert(word)

            word = Word(
                "Rårakor på halloumi",
                "Tillagning\n 1. Riv osten på den grova sidan av rivjärnet. Skala och riv potatisen. Blanda ost och potatis.\n 2. Hetta upp plättlaggenoch stek rårakorna i smör, ca 1/2 dl smet i varje, 3–4 min på varje sida, tills de är frasiga och har fin färg. Varmhåll dem i ugnen medan du steker nästa omgång.\n 3. Skala och hyvla rödlöken tunt. Blanda den med citronsaft och salt, det gör löken mildare.\n",
                "Forrat",
            "Ingredienser\n 150 g halloumiost\n 3 potatisar\n 1 msk smör\n 0,5 rödlök\n 1 msk citron, pressad\n 1 krm salt\n 100 g Skalade räkor\n 1,5 dl crème fraiche\n 50 g forellrom\n 1 msk gräslök\n 1 krm svartpeppar\n\n",
            R.drawable.raraka,
            "20 minuter, 2 portioner",
            13,
            false
            )
            wordDao.insert(word)

            word = Word(
                "Vitlöksbröd med Mozzarella(eller valfri ost)",
                "Tillagning\n Sätt ugnen på 225 grader grill.\n Smält smöret i en kastrull, tillsätt pressad vitlök & hackad persilja. Fördela ut en klick på varje brödskiva. Stoppa in brödet i ugnen i ca 10 min.\n Skär mozzarellan i skivor och lägg ut på brödet.\n Stoppa in brödet igen tills mozzarellan fått en gyllenbrun färg.\n Strö över flingsalt och sätt in i ugnen (8-9 min).",
                "Forrat",
            "Ingredienser\n4 st Skivor valfritt brod\n 75 g Smör\n 3 st Vitlöksklyftor\n Persilja\n 2 st Mozzarella\n Salt\n\n",
            R.drawable.vitloksbrod,
            "30 minuter, 4 portioner",
            14,
            false
            )
            wordDao.insert(word)

            word = Word(
                "Pizza med spenat och svamp",
                "Tillagning\nSätt ugnen på 225 grader.\n Stek spenaten i en msk av oljan i en stor kastrull cirka 1 minut. Låt kallna.\n Skiva svampen och fräs den i resten av oljan cirka 15 minuter. Rör om då och då.\n Rulla ut degen tillsammans med pappret på en plåt. Strö över pizzaosten. Lägg på spenat och svamp.\n Grädda pizzan i mitten av ugnen i 13-14 minuter eller tills pizzan fått vacker färg.",
                "Forrat",
            "Ingredienser\n100 g babyspenat\n 3 msk olivolja\n 4 medelstora karljohansvampar, frysta och tinade\n salt och peppar\n 6 dl riven pizzaost\n 1 dl flagad parmesan\n Valfri topping",
            R.drawable.pizzavegetarisk,
            "45 minuter, 10 bitar",
            15,
            false
            )
            wordDao.insert(word)


            //KÖTT
            word = Word(
                "Kyckling i jordnötssås ",
                "Tillagning\nKoka upp vatten i en kastrull till nudlarna.\n Skär gurkan i bitar och limen i klyftor.\n Koka nudlarna enligt anvisningen på förpackningen. Häll av dem och håll dem varma.\n Stek kycklingen i oljan i en stekpanna, krydda med salt och peppar.\n Sänk värmen och rör i grytbasen. Låt sjuda i ca 3 minuter.",
            "Meat",
            "Ingredienser\n 1 lime\n 2 port äggnudlar\n 300 g strimlad kycklingfilé\n 1/2 tsk olja\n 2 krm salt\n 1 krm peppar\n 400 ml grytbas thai satay curry\n \n",
            R.drawable.kycklingjordnotsas,
            "Under 45 minuter, 2 portioner",
            21,
            false
            )
            wordDao.insert(word)

            word = Word(
                "Enchiladas",
                "Tillagning\n Sätt ugnen på 225°C.\n Skala och hacka löken. Fräs färsen och hälften av löken  i oljan i en stekpanna eller gryta. Blanda ner kryddmixen och tomatpurén, bryn ytterligare några minuter.\n Tillsätt vattnet i färssåsen och låt puttra någon minut. Smaka av med salt och peppar.\n Fyll bröden med färssåsen och rulla ihop. Lägg dem med skarven nedåt i en ugnssäker form.Häll över pastasås och strö över ost.\n Gratinera mitt i ugnen ca 20 minuter tills osten fått gyllenbrun färg.\n",
                "Meat",
            "Ingredienser\n 1 rödlök\n ca 500 g blandfärs\n 1 msk olja\n 1 påse enchilada kryddmix\n 3 msk tomatpuré\n 1 dl vatten\n 8 tortillabröd\n 390 g pastasås\n 1 1/2 dl riven ost\n\n",
            R.drawable.enchilada,
            "Under 45 minuter, 4 portioner",
            22,
            false
            )
            wordDao.insert(word)

            word = Word(
                "Schnitzel",
                "Tillagning\n Börja med pepparsåsen. Finhacka löken och fräs den mjuk i smör i en kastrull.\n Blanda ner fond, vatten, grädde och rosépeppar. Koka upp och låt sjuda i 10 minuter under lock.\n Red eventuellt av med maizena för en tjockare konsistens. Smaka av med salt och sila sedan såsen.\n Banka ut schnitzlarna tills de är riktigt tunna.\n Vispa upp äggen lätt i en djup tallrik. Häll upp mjölet på en annan tallrik och ströbrödet på ytterligare en.\n Vänd varje schnitzel i mjöl, sedan i ägg och slutligen i ströbröd.\n Värm upp en stekpanna med rikligt med smör och stek schnitzlarna på medelvärme, tills de har fått en fin, gyllenbrun färg, cirka 3 minuter på varje sida. Krydda slutligen med salt och peppar.\n Garnera schnitzlarna med lite persilja och servera dem tillsammans med sås, kokt eller stekt potatis, gröna ärter och citronklyftor.",
            "Meat",
            "Ingredienser\n 4 kalvschnitzlar à ca 100 g\n 2 ägg\n 2 dl vetemjöl\n 2 dl ströbröd\n smör\n salt och peppar\n Pepparsås\n 1 gul lök\n 1 msk smör\n 2 msk koncentrerad kalvfond\n 4 msk vatten\n 4 dl grädde\n 1/2 msk stött rosépeppar\n\n ",
            R.drawable.schnitzel,
            "45 minuter, 4 portioner",
            23,
            false
            )
            wordDao.insert(word)

            word = Word(
                "Kottbullar med svampsås",
                "Tillagning\n Blötlägg havregrynen i vattnet och låt svälla i ca 15 min. Blanda sedan köttfärsen med övriga ingredienser.\n Forma stora köttbullar och lägg dem på oljade tallrikar. Hetta upp en stekpanna och bryn köttbullarna snabbt i omgångar, i lite olja blandad med smör. Skaka och vrid pannan på hög värme så att köttbullarna rullar och blir brynta runt om. Stek klart i ugnen på 150 grader så att köttbullarna blir genomstekta.\n Stek kantarellerna i torr panna och tillsätt smör när svampen har släppt vätskan. När den fått fin färg, pudra över lite vetemjöl och slå på kantarellfond och grädde. Smaka av med svartpeppar",
            "Meat",
            "Ingredienser\n Köttbullar:500 g blandfärs\n 1/2 dl havregryn\n 1 dl vatten\n 2 msk schalottenlöksfond\n 1 ägg\n 1 tsk salt\n 2 krm svartpeppar\n Kantarellsås:\n 200 g kantareller\n 2 msk smör\n 1 msk vetemjöl\n 2 dl grädde\n 2 dl mjölk\n\n",
            R.drawable.kottbullar,
            "Under 30 minuter, 4 portioner",
            24,
            false
            )
            wordDao.insert(word)

            word = Word(
                "Köttgryta",
                "Tillagning\n 1. Skär köttet i bitar. Skala och hacka löken och vitlöken.\n 2. Skär svamp, morot och palsternacka i bitar och tillsätt dessa då det är ca 10 minuter återstår av koktiden.\n 3. Blanda buljong, soja, tomatpuré och örtkryddor i en gryta. Låt koka några minuter.\n 4. Blanda i maizena såsredning till önskad konsistens och servera grytan med bulgur eller råris.",
            "Meat",
            "Ingredienser\n 500 gram nötkött, innan- eller ytterlår\n 1 st morot\n 1 st palsternacka4 st champinjoner\n 1 st oxbuljong\n 1 msk soya\n 2 msk tomatpuré\n 1 tsk rosmarin\n 1 st lök\n 2 st vitlöksklyftor\n maizena, såsredning\n 3 dl ris\n ",
            R.drawable.kottgryta,
            "Under 30 minuter, 4 portioner",
            25,
            false
            )
            wordDao.insert(word)

            //VEGETARISKT
            word = Word(
                "Spaghetti med pesto",
                "Tillagning\n Bryt av basilikabladen. Mixa ingredienserna till peston väl. Spar lite ost, pinjenötter och basilika till servering.Smaka av med salt och peppar.\n Koka spaghettin enligt anvisning på förpackningen. Blanda spaghetti och pesto. Toppa med extra basilika och ost. Servera genast.",
            "Vegetariskt",
            "Ingredienser\n Färsk basilika\n 1 vitlöksklyfta\n 100g riven ost\n 250g kvarg\n Pesto\n \n ",
            R.drawable.pestospaghetti,
            "Under 30 minuter, 4 portioner",
            31,
            false
            )
            wordDao.insert(word)


            word = Word(
                "Sparrissoppa",
                "Tillagning\n Mosa ihop ost med kvarg och citronskal. Värm soppan under omrörning. Toppa med röran, svartpeppar och mynta. Värm bröd och servera till soppan. ",
                "Vegetariskt",
            "Ingredienser\n 1 Liter Grönsparrissoppa\n Valfri ost\n 1 krm svartpeppar\n\n",
            R.drawable.sparrissoppa,
            "20 minuter, 4 portioner",
            32,
            false
            )
            wordDao.insert(word)

            word = Word(
                "Vegetariskt tacopaj",
                "Tillagning\n Sätt ugnen på 200°C. Låt majsen rinna av.\n Skala och hacka rödlöken.\n Koka upp pastasåsen och rör ner tacokrydda, majs, rödlök och färs. Låt bli varmt. \n Rulla ut pajdegen i en pajform, ca 25 cm i diameter. Fyll pajskalet med färsen och klicka över crème fraiche. Sätt in mitt i ugnen ca 10 minuter.",
            "Vegetariskt",
            "Ingredienser\n 340 g majskorn\n 1 rödlök\n 390 g pastasås med vitlök\n 1 påse taco kryddmix\n 600 g vegansk färs\n 250 g färsk pajdeg (gärna fullkorn)\n 2 dl lätt crème fraiche\n 3 morötter\n 1 huvudsallad\n \n",
            R.drawable.tacopaj,
            "Under 30 minuter, 4 portioner",
            33,
            false
            )
            wordDao.insert(word)

            word = Word(
                "Vitlokssoppa",
                "Tillagning\n Skala och hacka vitlökarna.\n Hetta upp en kastrull med olivolja och fräs vitlöken.\n Häll i vatten och buljongtärningarna, tillsätt de krossade tomaterna och paprikapulvret. Låt puttra i 40 minuter. Smaka av.\n Servera med rostat bröd och en klick crème fraiche.",
                "Vegetariskt",
            "Ingredienser\n 4 solovitlökar\n 2-3 msk olivolja, att steka i\n 5 dl vatten\n 2 grönsaksbuljongtärningar\n 1 burk krossade tomater\n 1 msk paprikapulver\n 0,5 tsk cayennepeppar\n Vitt bröd\n 1 dl crème fraiche till servering\n \n ",
            R.drawable.vitlokssoppa,
            "60 minuter, 2-4 portioner",
            34,
            false
            )
            wordDao.insert(word)

            word = Word(
                "Avokadopasta",
                "Tillagning\n Koka spaghettin enligt anvisningar på förpackningen.\n Skala och ta ur kärnan ur avokadon. Mixa avokado med en näve av spenaten, olja och vitlök.\n Mixa slätt och smaka sedan av med citronzest, citronsaft och salt.\n Häll av pastan med spara lite pastavatten i botten på kastrullen, ca 1 dl. Häll tillbaka pastan. Rör ner resterande spenat och avokadosåsen i kastrullen och blanda om ordentligt.",
            "Vegetariskt",
            "Ingredienser\n 3 avokado\n 1 msk neutral olja70 g spenat\n 1 klyfta vitlök\n 1 citron, skal och saft\n Salt\n \n",
            R.drawable.avokadopasta,
            "2-4 portioner",
            35,
            false
            )
            wordDao.insert(word)

            //FISH
            word = Word(
                "Tonfiskröra och bakad potatis",
                "Tillagning\n Rengör potatisarna, skär ett lätt snitt på toppen (inte för djupt för då faller de isär). Baka i mitten av ugn på ca 225 grader i ca 50 minuter.\n Hacka gurka och rödlök i små bitar. Krama ur lite vatten ur gurkan.\n Häll av vattnet från tonfisken och blanda tonfisk, crème fraiche, rödlök och gurka. Salta och peppra efter smak.\n Servera tonfiskröran till bakad potatis, toppad med persilja, picklad rödlök och granatäpplekärnor. Om önskas, skvätt över några droppar lime.",
                "Fish",
            "Ingredienser\n 0,5 gurka\n 1 rödlök\n 1 förp tonfisk i vatten\n 2 dl crème fraiche\n salt\n svartpeppar\n 0,5 dl picklad rödlök\n 4 bakpotatis\n \n ",
            R.drawable.tonfiskrora,
            "4 portioner",
            41,
            false
            )
            wordDao.insert(word)

            word = Word(
                "Fish and chips",
                "Tillagning\n Värm ugnen till 200 grader.\n Chips\n 1. Tvätta potatisen noga. Skär potatisarna på längden i jämna, centimetertjocka skivor och sen till 1/2 centimeter tjocka stavar.\n 2. Blanda potatisen i en bunke med oljan. Sprid ut potatisstavarna på en bakplåtsklädd plåt och rosta i ugnen i ca 30 min, eller tills potatisen har fått fin gyllene färg. Salta efter smak.\n Remouladsås\n 1. Blanda creme fraichen och majonnäs i en bunke.\n 2. Finhacka pickles och dill och blanda med creme fraicheröran. Smaka av med citronsaft, dijonsenap, salt och peppar.\n Fish\n 1. Stek fiskpinnarna i olja gyllene på båda sidor.\n 2. Servera gärna i en strut av tidningspapper eller lägg upp fisk, chips, sås, salladsblad och citronklyftor på tallrikar.",
                "Fish",
            "Ingredienser\n Chips\n 4 st bakpotatisar2 msk olivolja\n 1 tsk flingsalt\n 1 st bakplåtspapper\n Remouladsås\n 2 dl crème fraiche\n 1 msk majonnäs\n 1 dl pickles\n 0,5 st citroner\n 1 tsk dijonsenap\n 2 krm salt\n 1 krm svartpeppar\n Fish\n 450 gram fiskpinnar\n 1 msk olivolja\n \n",
            R.drawable.fishchips,
            "40 minuter, 4 portioner",
            42,
            false
            )
            wordDao.insert(word)

            word = Word(
                "Ugnsbakad lax",
                "Tillagning\n Lax:\n 1. Värm ugnen till 175 grader.\n 2. Lägg laxen i en ugnsfast form. Salta och peppra efter smak och ringla över olja. Stek i ugnen i ca 10 min.\n Avokadokräm:\n 1. Mixa samman avokado, persilja, limesaft, riven vitlök, riven parmesanost och olja med hjälp av en stavmixer eller i en matberedare till en slät kräm. Smaka av med salt och peppar.",
                "Fish",
            "Ingredienser\n Lax\n 250 gram laxfilé(er)\n 1 krm salt\n 1 krm svartpeppar\n 1 tsk olivolja\n Avokadokräm\n 1 st avokado(s)\n 0,5 kruka(or) färsk persilja\n 0,5 st lime\n 0,5 klyfta(or) vitlök\n 25 gram parmesanost\n 0,5 msk olivolja\n 1 krm salt\n 1 krm svartpeppar\n \n ",
            R.drawable.lax,
            "20 minuter, 2 portioner",
            43,
            false
            )
            wordDao.insert(word)

            word = Word(
                "Torskrygg i ugn",
                "Tillagning\n Värm ugnen till 140 °C.\n Torka av torskryggen med hushållspapper.\n Vänd torsken i mandelmjöl och bryn i stekpanna i ett par minuter för att få färg.\n Lägg torsken i en ugnsform och baka torsken i ugnen i 40 min.\n Skira smöret genom att smälta smör i en kastrull, utan att det bränns vid. Häll över smöret i en kanna och låt svalna en stund.\n Häll av den klara delen av smöret i en skål och häll bort det grumliga som är kvar i botten.\n Häll det klara smöret över sken, lägg på en tunt skivad citron och servera med kokt sparris och blomkålsmos.",
            "Fish",
            "Ingredienser\n 300 g torskrygg\n2 dl mandelmjöl\n1 krm salt\n1 krm vitpeppar\n50 g smör till stekning\nSkirat smör\n100 g smör\n1 citron, skivad\n \n",
            R.drawable.torsk,
            "30 minuter, 2 portioner",
            44,
            false
            )
            wordDao.insert(word)

            word = Word(
                "Lax- och räkgratäng",
                "Tillagning\n Sätt ugnen på 200 grader. Finhacka vitlöken. Strimla purjolöken fint. Skölj spenaten och halvera sockerärterna.\n Fräs lök, vitlök och spenat i smör. Sila bort överflödig vätska om det behövs. Fördela blandningen i en smord ugnsform tillsammans med sockerärterna.\n Putsa laxfiléerna och lägg dem på spenatbädden i formen.\n Blanda ihop bearnaisesås, yoghurt, ost, räkor och dill. Ta undan några räkor till garnering. Bred såsen över lax och spenat. Gratinera mitt i ugnen 18-20 minuter.\n Garnera gratängen med räkor, dill och limeklyftor och servera med kokt potatis, ris eller sallad.",
                "Fish",
            "Ingredienser\n 2 st Vitlöksklyftor, finhackade\n 1 st Purjolök, finhackad\n 400 g Färsk spenat\n 100 g Sockerärter\n 2 msk Smör\n 4 st Skinnfria laxfiléer a 175 g\n 2 dl Kyld bearnaisesås\n 2 dl Turkisk yoghurt\n 2 dl Riven lagrad ost\n 200 g Skalade räkor\n 2 msk Finhackad dill\n \n ",
            R.drawable.laxgrattang,
            "40 minuter, 4 portioner",
            45,
            false
            )
            wordDao.insert(word)

            //VEGANSKT
            word = Word(
                "Sojabönor på toast",
                "Tillagning\n Blötlägg sojabönorna och koka dem i vatten i en tryckkokare 2 puffar. Häll ut allt vatten.\n I en stekpanna häll i olja och när oljan är varm blanda i vitlöksklyftorna och gul löken samt chilin.\n Stek löken och vitlöken tills de har blivit mjuka.\n Lägg i tomaterna och stek tills de har blivit mjuka.\n Blanda därefter alla kryddor, garam masala, chili pulver och rör om.\n Blanda i sojabönorna och saltet. Stek i någon minut.",
                "Veganskt",
            "Ingredienser\n 3 dl torakde soja bönor\n 3 st vitlöksklyftor, hackade\n 1 st gullök hackad\n 2/3 tsk garam masla\n 0,5 tsk chili pulver\n 2 msk olja\n salt\n 1 st tomat, hackad\n 1 st torkad röd chili\n \n",
            R.drawable.sojabonor,
            "30 minuter, 4 portioner",
            51,
            false
            )
            wordDao.insert(word)

            word = Word(
                "Broccoliköttbullar",
                "Tillagning\n Rör ner buljongpulver i kokande vatten så det löses upp, blanda sedan med havregryn, sojasås och broccoli. Låt havregrynen suga upp lite vätska några minuter, mixa sedan och tillsätt dinkelmjöl, vitlök och kryddor. Prova dig fram med smaken. Smula sist ner tofu, rulla sedan till bollar och lägg på oljad plåt eller ugnsfast form. Rulla dem lite i oljan så får de en fin stekyta. Stek i ugnen tills de fått fin färg på 200 grader, ca 10-15 min. Servera med exempelvis pasta, en god tomatsås och sallad. Passar dock till det mesta. Smeten är även perfekt att göra biffar av.",
                "Veganskt",
            "Ingredienser\n 250 g Tofu (helst marinerad i t.ex. soja)\n200 g Broccoli (tinad fryst eller förvälld färsk)\n 2,5 dl Havregryn\n 0,5 dl Dinkelmjöl\n 0,5 dl Vatten\n 2-3 msk Sojasås\n 1-2 msk Rapsolja\n 1 tsk Örtsalt eller buljongpulver\n 2 st Pressade vitlöksklyftor\n svartpeppar\n \n",
            R.drawable.broccolikottbullar,
            "30 minuter, 4 portioner",
            52,
            false
            )
            wordDao.insert(word)

            word = Word(
                "Grön chili och myntasås",
                "Tillagning\n Mixa alla ingredienser till en slät sås.",
                "Veganskt",
            "Ingredienser\n 1 grön chili, välj jalapeno för mindre hetta\n 1 dl mynta, rejält packat mått plockad mynta\n 0,5 vitlöksklyfta\n 0,5 dl olivolja\n 1 msk vitvinsvinäger\n Salt\n \n ",
            R.drawable.gronchili ,
            "10 minuter, 4 portioner",
            53,
            false
            )
            wordDao.insert(word)

            word = Word(
                "Amerikanska veganpannkakor",
                "Tillagning\n Rör ihop vetemjöl, bakpulver, strösocker och vaniljsocker i en bunke.\n Vispa ner veganmjölk och vatten. \n När du har en klumpfri smet så hettar du upp lite mjölkfritt smör i en stekpanna och klickar ut små pannkakor med hjälp av en sked.\n Stek dina amerikanska pannkakor gyllenbruna på båda sidor och servera med färska bär och lönnsirap.",
                "Veganskt",
            "Ingredienser\n 5 dl vetemjöl\n 1 msk bakpulver\n 1 msk strösocker\n 2 tsk vaniljsocker\n 2,5 dl mjölk, t ex soja-, mandel- eller havremjölk\n 1,5 dl vatten\n mjölkfritt smör att steka i\n \n ",
            R.drawable.veganpannkakor ,
            "15 minuter, 4 portioner",
            54,
            false
            )
            wordDao.insert(word)

            word = Word(
                "Majsplättar med avokado",
                "Tillagning\n Blanda ihop majsmjöl, salt och bakpulver i en bunke. Hacka den gula löken, paprikan och koriandern fint.\n Rör ner yoghurt, vatten, olivolja, majskorn, gul lök, paprika och koriander i majsmjölsmixen.\n Hetta upp lite rapsolja i en stekpanna och klicka ut små plättar. Stek dem gyllenbruna på båda sidor.\n Gröp ur avokadon. Blanda den med övriga ingredienser i en matberedare och mixa till en slät kräm. Du kan även ta hjälp av en stavmixer eller mosa allt med en gaffel.",
                "Veganskt",
            "Ingredienser\n Majsplättar\n 3,5 dl majsmjöl\n 1 tsk salt\n 0,5 tsk bakpulver\n 0,5 gul lök\n 0,5 grön paprika\n 2 msk färsk koriander\n 2,5 dl yoghurt, t ex sojayoghurt\n 1 dl vatten\n 2 msk olivolja\n 1 dl majskorn\n Avokado-chimichurri\n 2 avokador\n 2 klyftor vitlök\n 0,5 kruka persilja\n 1 msk olivolja\n 1 msk rödvinsvinäger\n 1 tsk agavesirap\n 2 tsk torkad oregano\n salt och svartpeppar\n\n",
            R.drawable.majsplattar ,
            "25 minuter, 4 portioner",
            55,
            false
            )
            wordDao.insert(word)

            //DESSERT
            word = Word(
                "Fruktsallad med cottage cheese",
                "Tillagning\n Skär nektariner, melon och bananer i bitar och lägg i en skål.\n Blanda cottage cheese med kanel. Servera fruktsalladen med cottage cheese.",
                "Dessert",
            "Ingredienser\n 2 nektariner\n 1 melon\n 2 bananer\n 4 dl cottage cheese\n 1 tsk kanel\n\n ",
            R.drawable.fruktsallad ,
            "4 portioner",
            61,
            false
            )
            wordDao.insert(word)
            word = Word(
                "Vaniljgrädde",
                "Tillagning\n Vispa grädden hårt.\n Dela vaniljstången och skrapa ut vaniljen. Blanda grädde, vanilj och florsocker.\n Servera gärna tillsammans med Sockerskorpor och Hjortronconsommé.",
                "Dessert",
            "Ingredienser\n 3 dl vispgrädde\n, 1 - 1 1/2 msk strösocker\n1/2 vaniljstång\n\n",
            R.drawable.vaniljgradde ,
            "6 portioner",
            62,
            false
            )
            wordDao.insert(word)

            word = Word(
                "Citronmousse med hallonsås",
                "Tillagning\n Citronmousse:\n 1. Blötlägg gelatinbladen i en skål med vatten eller blanda gelatinpulvret med 1 msk vatten och låt svälla 5 min.\n 2. Separera äggulor och äggvitor och lägg i varsin skål.\n 3. Vispa samman äggulor med hälften av sockret vitt och pösigt. Tillsätt salt och lemoncurd och vispa samman snabbt till en slät konsistens.\n 4. Vispa äggvitorna med resten av sockret till en maräng.\n 5. Ta upp gelatinbladen ur vattnet och lägg dem i en kastrull tillsammans med 1 msk vatten. Eller blanda det svällda gelatinpulvret med 1 msk vatten i kastrullen. Låt smälta på svag värme.\n 6. Tillsätt citronskal och citronsaft och vänd sedan ner försiktigt i ägg-och lemoncurdsmeten. Blanda väl och låt svalna.\n 7. Vispa grädden och vänd försiktigt ner i den svala citronkrämen. Avsluta med att vända ner marängen till en fluffig mousse.\n 8. Fördela moussen i portionsglas och låt stå i kylen i minst 4 timmar men gärna över natten.\n Hallonsås:\n 1. Lägg frysta hallon och florsocker i en kastrull och värm på spisen. Mixa sedan till en sås med hjälp av en stavmixer.\n 2. Passera såsen genom en finmaskig sil. Ställ kallt till servering.\n Topping:\n 1. Baka kakorna i ugnen på 160 grader i ca 20 min.",
                "Dessert",
            "Ingredienser\n Citronmousse\n 1 tsk gelatinpulver\n 2 st ägg\n 0,75 dl strösocker\n 0,25 krm flingsalt\n 1 dl lemon curd\n 1 st citron(er)\n 1,25 dl vispgrädde\n Hallonsås\n 250 gram hallon\n 3 msk florsocker\n Topping\n 2 st cookies\n 20 gram maränger\n 0,25 kruka(or) färsk mynta\n 1 l vaniljglass\n \n ",
            R.drawable.citronmousse ,
            "4 timmar, 4 portioner",
            63,
            false
            )
            wordDao.insert(word)
            word = Word(
                "Tiramisu",
                "Tillagning\n Ta fram två skålar. Knäck äggen och separera gulan från vitan.\n I första skålen: Vispa ihop äggulorna med mascarpone och likör.\n I andra skålen: Vispa äggvitor med strösockret tills det är en blank, fluffig och fast smet.\n Blanda försiktigt ihop ingredienserna från båda skålarna.\n Doppa kexen snabbt i kaffet. Varva kex och äggsmet i portionsglas 2-3 lager. Ställ sedan kallt över natten eller minst 3-4 timmar. Pudra med kakao strax innan servering.",
            "Dessert",
            "Ingredienser\n 2 st ägg\n 250 gram Mascarpone\n 3 msk mandellikör , el annan likör alt rom\n 0,5 dl strösocker\n 8 st Savoiardikex, el 2 skivor sockerkaka\n 0,5 dl bryggkaffe, starkt, kallt\n 1 tsk kakao\n \n ",
            R.drawable.tiramisu ,
            "280, 4 portioner",
            64,
            false
            )
            wordDao.insert(word)

            word = Word(
                "Cheesecakeglass",
                "Tillagning\n Vispa Philadelphia med den kondenserade mjölken med en elvisp till en fluffigt.\n Vispa grädden i en separat bunke och vänd sedan ihop med Philadelphiakrämen.\n Krossa digestivekexen och rör ner i glassmassan. Häll upp i en form och ställ i frysen i ca 4 timmar.",
                "Dessert",
            "Ingredienser\n 300 g Philadelphia Original\n 0,75 dl kondenserad mjölk\n 2 dl vispgrädde\n 5 digestivekex\n\n",
            R.drawable. cheesecakeglass ,
            "5 minuter + tid i frys, 4 portioner",
            65,
            false
            )
            wordDao.insert(word)




        }
    }
}








