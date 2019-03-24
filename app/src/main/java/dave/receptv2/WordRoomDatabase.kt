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
                "Tillagning\n1. Skala och finhacka rödlök. Skölj och finhacka dill.\n2. Sila av räkorna.\n3. Blanda räkor, rödlök, dill, creme fraiche och majonnäs i en bunke. Tillsätt rivet citronskal och ca 1 tsk citronsaft. Smaka av med salt och peppar.\n4. Skär bort kanterna från 4 brödskivor och skär brödskivorna i trekanter. Stek brödskivorna gyllene på båda sidor i smör i en varm stekpanna.\n5. Lägg brödskivorna på fina tallrikar och toppa med skagenröra, löjrom och en dillkvist. Servera den ena brödskivan vid sidan av tillsammans med en citronklyfta.",
            "Forrat",
            "Ingredienser\n0,5 st röd lök\nFärsk dill\n240 gram handskalade räkor\n4 msk crème fraiche\n3 msk majonnäs\n1 st citron\n3 krm salt\n40 gram amerikansk löjrom\n1 krm svartpeppar\n200 gram valfri toast\n25 gram smör\n\n",
            R.drawable.bildtoastskagen,
            "15 minuter, 4 portioner",
            11,
            false
            )
            wordDao.insert(word)

            word = Word(
                "Moules marinieres",
                "Tillagning\n1. Tvätta blåmusslorna noga under rinnande vatten. Knacka öppna musslor försiktigt mot diskbänken. De blåmusslor som inte sluter sig slängs.\n2. Skala och finhacka schalottenlök och vitlök. stekt mjukt i olivoljan utan att det tar färg.\n3. Tillsätt blåmusslorna och häll på vin, fond, vatten och grädde. Lägg på lock och låt koka i 2-3 min, eller tills musslorna är färdiga och har öppnat sig. Släng de musslor som inte öppnat sig.\n4. Lyft ur musslorna och låt soppan koka ner lite. Smaka av med salt och peppar.\n5. Lägg upp musslorna i en stor skål och slå över musselbuljongen och toppa med grovhackad persilja.",
            "Forrat",
            "Ingredienser\n1 kg blåmusslor, färska\n2 st Schalottenlökar\n3 klyftor vitlök\n1 msk olivolja\n2 dl vitt vin\n2 msk fiskfond\n2 dl vatten\n2 dl vispgrädde\n1 krm salt\n1 krm svartpeppar\nPersilja\n\n",
            R.drawable.moule,
            "30 minuter, 4 portioner",
            12,
            false
            )
            wordDao.insert(word)

            word = Word(
                "Rårakor på halloumi",
                "Tillagning\n1. Riv osten på den grova sidan av rivjärnet. Skala och riv potatisen. Blanda ost och potatis.\n2. Hetta upp plättlaggenoch stek rårakorna i smör, ca 1/2 dl smet i varje, 3–4 min på varje sida, tills de är frasiga och har fin färg. Varmhåll dem i ugnen medan du steker nästa omgång.\n3. Skala och hyvla rödlöken tunt. Blanda den med citronsaft och salt, det gör löken mildare.\n",
                "Forrat",
            "Ingredienser\n150 g halloumiost\n3 potatisar\n1 msk smör\n0,5 rödlök\n1 msk citron, pressad\n1 krm salt\n100 g Skalade räkor\n1,5 dl crème fraiche\n50 g forellrom\n1 msk gräslök\n1 krm svartpeppar\n\n",
            R.drawable.raraka,
            "20 minuter, 2 portioner",
            13,
            false
            )
            wordDao.insert(word)

            word = Word(
                "Vitlöksbröd",
                "Tillagning\nSätt ugnen på 225 grader grill.\nSmält smöret i en kastrull, tillsätt pressad vitlök & hackad persilja. Fördela ut en klick på varje brödskiva. Stoppa in brödet i ugnen i ca 10 min.\nSkär mozzarellan i skivor och lägg ut på brödet.\nStoppa in brödet igen tills mozzarellan fått en gyllenbrun färg.\nStrö över flingsalt och sätt in i ugnen (8-9 min).",
                "Forrat",
            "Ingredienser\n4 st Skivor valfritt brod\n75 g Smör\n3 st Vitlöksklyftor\nPersilja\n2 st Mozzarella\n Salt\n\n",
            R.drawable.vitloksbrod,
            "30 minuter, 4 portioner",
            14,
            false
            )
            wordDao.insert(word)

            word = Word(
                "Pizza med spenat",
                "Tillagning\nSätt ugnen på 225 grader.\nStek spenaten i en msk av oljan i en stor kastrull cirka 1 minut. Låt kallna.\nSkiva svampen och fräs den i resten av oljan cirka 15 minuter. Rör om då och då.\nRulla ut degen tillsammans med pappret på en plåt. Strö över pizzaosten. Lägg på spenat och svamp.\nGrädda pizzan i mitten av ugnen i 13-14 minuter eller tills pizzan fått vacker färg.",
                "Forrat",
            "Ingredienser\n100 g babyspenat\n3 msk olivolja\n4 medelstora karljohansvampar, frysta och tinade\nsalt och peppar\n6 dl riven pizzaost\n1 dl flagad parmesan\n Valfri topping",
            R.drawable.pizzavegetarisk,
            "45 minuter, 10 bitar",
            15,
            false
            )
            wordDao.insert(word)


            //KÖTT
            word = Word(
                "Kyckling i jordnötssås ",
                "Tillagning\nKoka upp vatten i en kastrull till nudlarna.\nSkär gurkan i bitar och limen i klyftor.\nKoka nudlarna enligt anvisningen på förpackningen. Häll av dem och håll dem varma.\nStek kycklingen i oljan i en stekpanna, krydda med salt och peppar.\nSänk värmen och rör i grytbasen. Låt sjuda i ca 3 minuter.",
            "Meat",
            "Ingredienser\n1 lime\n2 port äggnudlar\n300 g strimlad kycklingfilé\n1/2 tsk olja\n2 krm salt\n1 krm peppar\n400 ml grytbas thai satay curry\n\n",
            R.drawable.kycklingjordnotsas,
            "Under 45 minuter, 2 portioner",
            21,
            false
            )
            wordDao.insert(word)

            word = Word(
                "Enchiladas",
                "Tillagning\nSätt ugnen på 225°C.\nSkala och hacka löken. Fräs färsen och hälften av löken  i oljan i en stekpanna eller gryta. Blanda ner kryddmixen och tomatpurén, bryn ytterligare några minuter.\nTillsätt vattnet i färssåsen och låt puttra någon minut. Smaka av med salt och peppar.\nFyll bröden med färssåsen och rulla ihop. Lägg dem med skarven nedåt i en ugnssäker form.Häll över pastasås och strö över ost.\nGratinera mitt i ugnen ca 20 minuter tills osten fått gyllenbrun färg.\n",
                "Meat",
            "Ingredienser\n1 rödlök\nca 500 g blandfärs\n1 msk olja\n1 påse enchilada kryddmix\n3 msk tomatpuré\n1 dl vatten\n8 tortillabröd\n390 g pastasås\n1 1/2 dl riven ost\n\n",
            R.drawable.enchilada,
            "Under 45 minuter, 4 portioner",
            22,
            false
            )
            wordDao.insert(word)

            word = Word(
                "Schnitzel",
                "Tillagning\nBörja med pepparsåsen. Finhacka löken och fräs den mjuk i smör i en kastrull.\nBlanda ner fond, vatten, grädde och rosépeppar. Koka upp och låt sjuda i 10 minuter under lock.\nRed eventuellt av med maizena för en tjockare konsistens. Smaka av med salt och sila sedan såsen.\nBanka ut schnitzlarna tills de är riktigt tunna.\nVispa upp äggen lätt i en djup tallrik. Häll upp mjölet på en annan tallrik och ströbrödet på ytterligare en.\nVänd varje schnitzel i mjöl, sedan i ägg och slutligen i ströbröd.\nVärm upp en stekpanna med rikligt med smör och stek schnitzlarna på medelvärme, tills de har fått en fin, gyllenbrun färg, cirka 3 minuter på varje sida. Krydda slutligen med salt och peppar.\nGarnera schnitzlarna med lite persilja och servera dem tillsammans med sås, kokt eller stekt potatis, gröna ärter och citronklyftor.",
            "Meat",
            "Ingredienser\n4 kalvschnitzlar à ca 100 g\n2 ägg\n2 dl vetemjöl\n2 dl ströbröd\nsmör\nsalt och peppar\nPepparsås\n1 gul lök\n1 msk smör\n2 msk koncentrerad kalvfond\n4 msk vatten\n4 dl grädde\n1/2 msk stött rosépeppar\n\n ",
            R.drawable.schnitzel,
            "45 minuter, 4 portioner",
            23,
            false
            )
            wordDao.insert(word)

            word = Word(
                "Kottbullar med svampsås",
                "Tillagning\nBlötlägg havregrynen i vattnet och låt svälla i ca 15 min. Blanda sedan köttfärsen med övriga ingredienser.\nForma stora köttbullar och lägg dem på oljade tallrikar. Hetta upp en stekpanna och bryn köttbullarna snabbt i omgångar, i lite olja blandad med smör. Skaka och vrid pannan på hög värme så att köttbullarna rullar och blir brynta runt om. Stek klart i ugnen på 150 grader så att köttbullarna blir genomstekta.\nStek kantarellerna i torr panna och tillsätt smör när svampen har släppt vätskan. När den fått fin färg, pudra över lite vetemjöl och slå på kantarellfond och grädde. Smaka av med svartpeppar",
            "Meat",
            "Ingredienser\nKöttbullar:500 g blandfärs\n1/2 dl havregryn\n1 dl vatten\n2 msk schalottenlöksfond\n1 ägg\n1 tsk salt\n2 krm svartpeppar\nKantarellsås:\n200 g kantareller\n2 msk smör\n1 msk vetemjöl\n2 dl grädde\n2 dl mjölk\n\n",
            R.drawable.kottbullar,
            "Under 30 minuter, 4 portioner",
            24,
            false
            )
            wordDao.insert(word)

            word = Word(
                "Köttgryta",
                "Tillagning\n1. Skär köttet i bitar. Skala och hacka löken och vitlöken.\n2. Skär svamp, morot och palsternacka i bitar och tillsätt dessa då det är ca 10 minuter återstår av koktiden.\n3. Blanda buljong, soja, tomatpuré och örtkryddor i en gryta. Låt koka några minuter.\n4. Blanda i maizena såsredning till önskad konsistens och servera grytan med bulgur eller råris.",
            "Meat",
            "Ingredienser\n500 gram nötkött, innan- eller ytterlår\n1 st morot\n1 st palsternacka4 st champinjoner\n1 st oxbuljong\n1 msk soya\n2 msk tomatpuré\n1 tsk rosmarin\n1 st lök\n2 st vitlöksklyftor\nmaizena, såsredning\n3 dl ris\n",
            R.drawable.kottgryta,
            "Under 30 minuter, 4 portioner",
            25,
            false
            )
            wordDao.insert(word)

            //VEGETARISKT
            word = Word(
                "Spaghetti med pesto",
                "Tillagning\nBryt av basilikabladen. Mixa ingredienserna till peston väl. Spar lite ost, pinjenötter och basilika till servering.Smaka av med salt och peppar.\nKoka spaghettin enligt anvisning på förpackningen. Blanda spaghetti och pesto. Toppa med extra basilika och ost. Servera genast.",
            "Vegetariskt",
            "Ingredienser\nFärsk basilika\n1 vitlöksklyfta\n100g riven ost\n250g kvarg\nPesto\n\n ",
            R.drawable.pestospaghetti,
            "Under 30 minuter, 4 portioner",
            31,
            false
            )
            wordDao.insert(word)


            word = Word(
                "Sparrissoppa",
                "Tillagning\nMosa ihop ost med kvarg och citronskal. Värm soppan under omrörning. Toppa med röran, svartpeppar och mynta. Värm bröd och servera till soppan. ",
                "Vegetariskt",
            "Ingredienser\n1 Liter Grönsparrissoppa\nValfri ost\n1 krm svartpeppar\n\n",
            R.drawable.sparrissoppa,
            "20 minuter, 4 portioner",
            32,
            false
            )
            wordDao.insert(word)

            word = Word(
                "Vegetariskt tacopaj",
                "Tillagning\nSätt ugnen på 200°C. Låt majsen rinna av.\nSkala och hacka rödlöken.\nKoka upp pastasåsen och rör ner tacokrydda, majs, rödlök och färs. Låt bli varmt. \nRulla ut pajdegen i en pajform, ca 25 cm i diameter. Fyll pajskalet med färsen och klicka över crème fraiche. Sätt in mitt i ugnen ca 10 minuter.",
            "Vegetariskt",
            "Ingredienser\n340 g majskorn\n1 rödlök\n390 g pastasås med vitlök\n1 påse taco kryddmix\n600 g vegansk färs\n250 g färsk pajdeg (gärna fullkorn)\n2 dl lätt crème fraiche\n3 morötter\n1 huvudsallad\n\n",
            R.drawable.tacopaj,
            "Under 30 minuter, 4 portioner",
            33,
            false
            )
            wordDao.insert(word)

            word = Word(
                "Vitlokssoppa",
                "Tillagning\nSkala och hacka vitlökarna.\nHetta upp en kastrull med olivolja och fräs vitlöken.\nHäll i vatten och buljongtärningarna, tillsätt de krossade tomaterna och paprikapulvret. Låt puttra i 40 minuter. Smaka av.\nServera med rostat bröd och en klick crème fraiche.",
                "Vegetariskt",
            "Ingredienser\n4 solovitlökar\n2-3 msk olivolja, att steka i\n5 dl vatten\n2 grönsaksbuljongtärningar\n1 burk krossade tomater\n1 msk paprikapulver\n0,5 tsk cayennepeppar\nVitt bröd\n1 dl crème fraiche till servering\n\n ",
            R.drawable.vitlokssoppa,
            "60 minuter, 2-4 portioner",
            34,
            false
            )
            wordDao.insert(word)

            word = Word(
                "Avokadopasta",
                "Tillagning\nKoka spaghettin enligt anvisningar på förpackningen.\nSkala och ta ur kärnan ur avokadon. Mixa avokado med en näve av spenaten, olja och vitlök.\nMixa slätt och smaka sedan av med citronzest, citronsaft och salt.\nHäll av pastan med spara lite pastavatten i botten på kastrullen, ca 1 dl. Häll tillbaka pastan. Rör ner resterande spenat och avokadosåsen i kastrullen och blanda om ordentligt.",
            "Vegetariskt",
            "Ingredienser\n3 avokado\n1 msk neutral olja70 g spenat\n1 klyfta vitlök\n1 citron, skal och saft\nSalt\n\n",
            R.drawable.avokadopasta,
            "2-4 portioner",
            35,
            false
            )
            wordDao.insert(word)

            //FISH
            word = Word(
                "Tonfiskröra med potatis",
                "Tillagning\nRengör potatisarna, skär ett lätt snitt på toppen (inte för djupt för då faller de isär). Baka i mitten av ugn på ca 225 grader i ca 50 minuter.\nHacka gurka och rödlök i små bitar. Krama ur lite vatten ur gurkan.\nHäll av vattnet från tonfisken och blanda tonfisk, crème fraiche, rödlök och gurka. Salta och peppra efter smak.\nServera tonfiskröran till bakad potatis, toppad med persilja, picklad rödlök och granatäpplekärnor. Om önskas, skvätt över några droppar lime.",
                "Fish",
            "Ingredienser\n0,5 gurka\n1 rödlök\n1 förp tonfisk i vatten\n2 dl crème fraiche\nsalt\n svartpeppar\n0,5 dl picklad rödlök\n4 bakpotatis\n\n ",
            R.drawable.tonfiskrora,
            "4 portioner",
            41,
            false
            )
            wordDao.insert(word)

            word = Word(
                "Fish and chips",
                "Tillagning\nVärm ugnen till 200 grader.\nChips\n1. Tvätta potatisen noga. Skär potatisarna på längden i jämna, centimetertjocka skivor och sen till 1/2 centimeter tjocka stavar.\n2. Blanda potatisen i en bunke med oljan. Sprid ut potatisstavarna på en bakplåtsklädd plåt och rosta i ugnen i ca 30 min, eller tills potatisen har fått fin gyllene färg. Salta efter smak.\nRemouladsås\n 1.Blanda creme fraichen och majonnäs i en bunke.\n2. Finhacka pickles och dill och blanda med creme fraicheröran. Smaka av med citronsaft, dijonsenap, salt och peppar.\nFish\n1. Stek fiskpinnarna i olja gyllene på båda sidor.\n2. Servera gärna i en strut av tidningspapper eller lägg upp fisk, chips, sås, salladsblad och citronklyftor på tallrikar.",
                "Fish",
            "Ingredienser\nChips\n4 st bakpotatisar2 msk olivolja\n1 tsk flingsalt\n1 st bakplåtspapper\nRemouladsås\n2 dl crème fraiche\n1 msk majonnäs\n1 dl pickles\n0,5 st citroner\n1 tsk dijonsenap\n2 krm salt\n1 krm svartpeppar\nFish\n450 gram fiskpinnar\n1 msk olivolja\n \n",
            R.drawable.fishchips,
            "40 minuter, 4 portioner",
            42,
            false
            )
            wordDao.insert(word)

            word = Word(
                "Ugnsbakad lax",
                "Tillagning\nLax:\n1. Värm ugnen till 175 grader.\n2. Lägg laxen i en ugnsfast form. Salta och peppra efter smak och ringla över olja. Stek i ugnen i ca 10 min.\nAvokadokräm:\n1. Mixa samman avokado, persilja, limesaft, riven vitlök, riven parmesanost och olja med hjälp av en stavmixer eller i en matberedare till en slät kräm. Smaka av med salt och peppar.",
                "Fish",
            "Ingredienser\nLax\n250 gram laxfilé(er)\n1 krm salt\n1 krm svartpeppar\n1 tsk olivolja\nAvokadokräm\n1 st avokado(s)\n0,5 kruka(or) färsk persilja\n0,5 st lime\n0,5 klyfta(or) vitlök\n25 gram parmesanost\n0,5 msk olivolja\n1 krm salt\n1 krm svartpeppar\n \n ",
            R.drawable.lax,
            "20 minuter, 2 portioner",
            43,
            false
            )
            wordDao.insert(word)

            word = Word(
                "Torskrygg i ugn",
                "Tillagning\nVärm ugnen till 140 °C.\nTorka av torskryggen med hushållspapper.\nVänd torsken i mandelmjöl och bryn i stekpanna i ett par minuter för att få färg.\nLägg torsken i en ugnsform och baka torsken i ugnen i 40 min.\nSkira smöret genom att smälta smör i en kastrull, utan att det bränns vid. Häll över smöret i en kanna och låt svalna en stund.\nHäll av den klara delen av smöret i en skål och häll bort det grumliga som är kvar i botten.\nHäll det klara smöret över sken, lägg på en tunt skivad citron och servera med kokt sparris och blomkålsmos.",
            "Fish",
            "Ingredienser\n300 g torskrygg\n2 dl mandelmjöl\n1 krm salt\n1 krm vitpeppar\n50 g smör till stekning\nSkirat smör\n100 g smör\n1 citron, skivad\n \n",
            R.drawable.torsk,
            "30 minuter, 2 portioner",
            44,
            false
            )
            wordDao.insert(word)

            word = Word(
                "Lax- och räkgratäng",
                "Tillagning\nSätt ugnen på 200 grader. Finhacka vitlöken. Strimla purjolöken fint. Skölj spenaten och halvera sockerärterna.\nFräs lök, vitlök och spenat i smör. Sila bort överflödig vätska om det behövs. Fördela blandningen i en smord ugnsform tillsammans med sockerärterna.\nPutsa laxfiléerna och lägg dem på spenatbädden i formen.\nBlanda ihop bearnaisesås, yoghurt, ost, räkor och dill. Ta undan några räkor till garnering. Bred såsen över lax och spenat. Gratinera mitt i ugnen 18-20 minuter.\nGarnera gratängen med räkor, dill och limeklyftor och servera med kokt potatis, ris eller sallad.",
                "Fish",
            "Ingredienser\n2 st Vitlöksklyftor, finhackade\n1 st Purjolök, finhackad\n400 g Färsk spenat\n100 g Sockerärter\n2 msk Smör\n4 st Skinnfria laxfiléer a 175 g\n2 dl Kyld bearnaisesås\n2 dl Turkisk yoghurt\n2 dl Riven lagrad ost\n200 g Skalade räkor\n2 msk Finhackad dill\n \n ",
            R.drawable.laxgrattang,
            "40 minuter, 4 portioner",
            45,
            false
            )
            wordDao.insert(word)

            //VEGANSKT
            word = Word(
                "Sojabönor på toast",
                "Tillagning\nBlötlägg sojabönorna och koka dem i vatten i en tryckkokare 2 puffar. Häll ut allt vatten.\nI en stekpanna häll i olja och när oljan är varm blanda i vitlöksklyftorna och gul löken samt chilin.\nStek löken och vitlöken tills de har blivit mjuka.\nLägg i tomaterna och stek tills de har blivit mjuka.\nBlanda därefter alla kryddor, garam masala, chili pulver och rör om.\nBlanda i sojabönorna och saltet. Stek i någon minut.",
                "Veganskt",
            "Ingredienser\n3 dl torakde soja bönor\n3 st vitlöksklyftor, hackade\n1 st gullök hackad\n2/3 tsk garam masla\n0,5 tsk chili pulver\n2 msk olja\nsalt\n1 st tomat, hackad\n1 st torkad röd chili\n\n",
            R.drawable.sojabonor,
            "30 minuter, 4 portioner",
            51,
            false
            )
            wordDao.insert(word)

            word = Word(
                "Broccoliköttbullar",
                "Tillagning\nRör ner buljongpulver i kokande vatten så det löses upp, blanda sedan med havregryn, sojasås och broccoli. Låt havregrynen suga upp lite vätska några minuter, mixa sedan och tillsätt dinkelmjöl, vitlök och kryddor. Prova dig fram med smaken. Smula sist ner tofu, rulla sedan till bollar och lägg på oljad plåt eller ugnsfast form. Rulla dem lite i oljan så får de en fin stekyta. Stek i ugnen tills de fått fin färg på 200 grader, ca 10-15 min. Servera med exempelvis pasta, en god tomatsås och sallad. Passar dock till det mesta. Smeten är även perfekt att göra biffar av.",
                "Veganskt",
            "Ingredienser\n250 g Tofu (helst marinerad i t.ex. soja)\n200 g Broccoli (tinad fryst eller förvälld färsk)\n2,5 dl Havregryn\n0,5 dl Dinkelmjöl\n0,5 dl Vatten\n2-3 msk Sojasås\n1-2 msk Rapsolja\n1 tsk Örtsalt eller buljongpulver\n2 st Pressade vitlöksklyftor\nsvartpeppar\n \n",
            R.drawable.broccolikottbullar,
            "30 minuter, 4 portioner",
            52,
            false
            )
            wordDao.insert(word)

            word = Word(
                "Grön chili och myntasås",
                "Tillagning\nMixa alla ingredienser till en slät sås.",
                "Veganskt",
            "Ingredienser\n1 grön chili, välj jalapeno för mindre hetta\n1 dl mynta, rejält packat mått plockad mynta\n0,5 vitlöksklyfta\n0,5 dl olivolja\n1 msk vitvinsvinäger\nSalt\n \n ",
            R.drawable.gronchili ,
            "10 minuter, 4 portioner",
            53,
            false
            )
            wordDao.insert(word)

            word = Word(
                "Veganska pannkakor",
                "Tillagning\nRör ihop vetemjöl, bakpulver, strösocker och vaniljsocker i en bunke.\nVispa ner veganmjölk och vatten.\nNär du har en klumpfri smet så hettar du upp lite mjölkfritt smör i en stekpanna och klickar ut små pannkakor med hjälp av en sked.\nStek dina amerikanska pannkakor gyllenbruna på båda sidor och servera med färska bär och lönnsirap.",
                "Veganskt",
            "Ingredienser\n5 dl vetemjöl\n1 msk bakpulver\n1 msk strösocker\n2 tsk vaniljsocker\n2,5 dl mjölk, t ex soja-, mandel- eller havremjölk\n1,5 dl vatten\nmjölkfritt smör att steka i\n \n ",
            R.drawable.veganpannkakor ,
            "15 minuter, 4 portioner",
            54,
            false
            )
            wordDao.insert(word)

            word = Word(
                "Majsplättar med avokado",
                "Tillagning\nBlanda ihop majsmjöl, salt och bakpulver i en bunke. Hacka den gula löken, paprikan och koriandern fint.\nRör ner yoghurt, vatten, olivolja, majskorn, gul lök, paprika och koriander i majsmjölsmixen.\nHetta upp lite rapsolja i en stekpanna och klicka ut små plättar. Stek dem gyllenbruna på båda sidor.\nGröp ur avokadon. Blanda den med övriga ingredienser i en matberedare och mixa till en slät kräm. Du kan även ta hjälp av en stavmixer eller mosa allt med en gaffel.",
                "Veganskt",
            "Ingredienser\nMajsplättar\n3,5 dl majsmjöl\n1 tsk salt\n0,5 tsk bakpulver\n0,5 gul lök\n0,5 grön paprika\n2 msk färsk koriander\n2,5 dl yoghurt, t ex sojayoghurt\n1 dl vatten\n2 msk olivolja\n1 dl majskorn\nAvokado-chimichurri\n2 avokador\n2 klyftor vitlök\n0,5 kruka persilja\n1 msk olivolja\n1 msk rödvinsvinäger\n1 tsk agavesirap\n2 tsk torkad oregano\nsalt och svartpeppar\n\n",
            R.drawable.majsplattar ,
            "25 minuter, 4 portioner",
            55,
            false
            )
            wordDao.insert(word)

            //DESSERT
            word = Word(
                "Fruktsallad",
                "Tillagning\nSkär nektariner, melon och bananer i bitar och lägg i en skål.\nBlanda cottage cheese med kanel. Servera fruktsalladen med cottage cheese.",
                "Dessert",
            "Ingredienser\n2 nektariner\n1 melon\n2 bananer\n4 dl cottage cheese\n1 tsk kanel\n\n ",
            R.drawable.fruktsallad ,
            "4 portioner",
            61,
            false
            )
            wordDao.insert(word)
            word = Word(
                "Vaniljgrädde",
                "Tillagning\nVispa grädden hårt.\nDela vaniljstången och skrapa ut vaniljen. Blanda grädde, vanilj och florsocker.\nServera gärna tillsammans med Sockerskorpor och Hjortronconsommé.",
                "Dessert",
            "Ingredienser\n3 dl vispgrädde\n1 - 1 1/2 msk strösocker\n1/2 vaniljstång\n\n",
            R.drawable.vaniljgradde ,
            "6 portioner",
            62,
            false
            )
            wordDao.insert(word)

            word = Word(
                "Citronmousse med hallonsås",
                "Tillagning\nCitronmousse:\n1. Blötlägg gelatinbladen i en skål med vatten eller blanda gelatinpulvret med 1 msk vatten och låt svälla 5 min.\n2. Separera äggulor och äggvitor och lägg i varsin skål.\n3. Vispa samman äggulor med hälften av sockret vitt och pösigt. Tillsätt salt och lemoncurd och vispa samman snabbt till en slät konsistens.\n4. Vispa äggvitorna med resten av sockret till en maräng.\n5. Ta upp gelatinbladen ur vattnet och lägg dem i en kastrull tillsammans med 1 msk vatten. Eller blanda det svällda gelatinpulvret med 1 msk vatten i kastrullen. Låt smälta på svag värme.\n6. Tillsätt citronskal och citronsaft och vänd sedan ner försiktigt i ägg-och lemoncurdsmeten. Blanda väl och låt svalna.\n7. Vispa grädden och vänd försiktigt ner i den svala citronkrämen. Avsluta med att vända ner marängen till en fluffig mousse.\n8. Fördela moussen i portionsglas och låt stå i kylen i minst 4 timmar men gärna över natten.\nHallonsås:\n1. Lägg frysta hallon och florsocker i en kastrull och värm på spisen. Mixa sedan till en sås med hjälp av en stavmixer.\n2. Passera såsen genom en finmaskig sil. Ställ kallt till servering.\nTopping:\n1. Baka kakorna i ugnen på 160 grader i ca 20 min.",
                "Dessert",
            "Ingredienser\nCitronmousse\n1 tsk gelatinpulver\n2 st ägg\n0,75 dl strösocker\n0,25 krm flingsalt\n1 dl lemon curd\n1 st citron(er)\n1,25 dl vispgrädde\nHallonsås\n250 gram hallon\n3 msk florsocker\nTopping\n2 st cookies\n20 gram maränger\n0,25 kruka(or) färsk mynta\n1 l vaniljglass\n \n ",
            R.drawable.citronmousse ,
            "4 timmar, 4 portioner",
            63,
            false
            )
            wordDao.insert(word)
            word = Word(
                "Tiramisu",
                "Tillagning\nTa fram två skålar. Knäck äggen och separera gulan från vitan.\nI första skålen: Vispa ihop äggulorna med mascarpone och likör.\nI andra skålen: Vispa äggvitor med strösockret tills det är en blank, fluffig och fast smet.\nBlanda försiktigt ihop ingredienserna från båda skålarna.\nDoppa kexen snabbt i kaffet. Varva kex och äggsmet i portionsglas 2-3 lager. Ställ sedan kallt över natten eller minst 3-4 timmar. Pudra med kakao strax innan servering.",
            "Dessert",
            "Ingredienser\n2 st ägg\n250 gram Mascarpone\n3 msk mandellikör , el annan likör alt rom\n0,5 dl strösocker\n8 st Savoiardikex, el 2 skivor sockerkaka\n0,5 dl bryggkaffe, starkt, kallt\n1 tsk kakao\n \n ",
            R.drawable.tiramisu ,
            "280, 4 portioner",
            64,
            false
            )
            wordDao.insert(word)

            word = Word(
                "Cheesecakeglass",
                "Tillagning\nVispa Philadelphia med den kondenserade mjölken med en elvisp till en fluffigt.\nVispa grädden i en separat bunke och vänd sedan ihop med Philadelphiakrämen.\nKrossa digestivekexen och rör ner i glassmassan. Häll upp i en form och ställ i frysen i ca 4 timmar.",
                "Dessert",
            "Ingredienser\n300 g Philadelphia Original\n0,75 dl kondenserad mjölk\n2 dl vispgrädde\n5 digestivekex\n\n",
            R.drawable. cheesecakeglass ,
            "5 minuter + tid i frys, 4 portioner",
            65,
            false
            )
            wordDao.insert(word)




        }
    }
}








