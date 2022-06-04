import impl.ExamRegistrations
import org.junit.Assert.{assertArrayEquals, assertEquals}
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should

class TestExamRegistrations extends AnyFlatSpec with should.Matchers {

    private val registrations = List(("Willi", 373583, 2), ("Anselma", 476749, 2), ("Noll", 345909, 2), ("Inessa", 307055, 2), ("Godart", 423496, 2), ("Sissie", 393508, 1), ("Allin", 434824, 1), ("Catharine", 374286, 2), ("Kore", 319004, 1), ("Cornell", 325856, 1), ("Mikkel", 468023, 1), ("Ross", 383096, 2), ("Robbie", 434105, 1), ("Cariotta", 451072, 1), ("Wendye", 334066, 2), ("Janey", 494932, 2), ("Nonna", 303659, 2), ("Franklin", 460296, 1), ("Kikelia", 466208, 2), ("Jade", 497277, 1), ("Traver", 451487, 2), ("Alain", 304500, 1), ("Jude", 335189, 2), ("Gaile", 396638, 2), ("Hilarius", 352284, 1), ("Bengt", 463248, 1), ("Brok", 473778, 1), ("Keri", 345246, 2), ("Ingar", 488058, 2), ("Almeta", 422016, 1), ("Hanny", 460693, 1), ("Mattias", 337679, 1), ("Cristabel", 356625, 1), ("Banky", 320692, 2), ("Karolina", 487674, 1), ("Osmond", 397483, 2), ("Essy", 384638, 1), ("Katha", 320650, 2), ("Dorey", 476369, 1), ("Harlan", 499766, 2), ("Jess", 416688, 1), ("Bevon", 338526, 2), ("Phaidra", 367390, 2), ("Arthur", 341507, 1), ("Krista", 318817, 1), ("Riki", 470347, 1), ("Jany", 355970, 1), ("Jane", 399307, 2), ("Phedra", 470191, 2), ("Conn", 436363, 2), ("Wendy", 327985, 1), ("Cristine", 401982, 1), ("Lek", 389489, 1), ("Rogerio", 428090, 1), ("Nikki", 372321, 2), ("Penrod", 494075, 2), ("Tamma", 484586, 1), ("Rhona", 335827, 1), ("Bobby", 308589, 2), ("Jyoti", 306454, 1), ("Aluino", 386429, 1), ("Rustin", 360078, 1), ("Mortie", 473443, 2), ("Anson", 332631, 1), ("Elvira", 352928, 2), ("Milo", 476955, 1), ("Luelle", 322544, 1), ("Robbie", 309935, 2), ("Cornie", 425221, 2), ("Gideon", 401259, 2), ("Wyn", 430514, 2), ("Bernhard", 379803, 2), ("Cherish", 336386, 2), ("Page", 498418, 2), ("Darelle", 443513, 2), ("Ted", 314594, 1), ("Christalle", 320557, 2), ("Wini", 490064, 2), ("Bunny", 420658, 1), ("Brenda", 467174, 1), ("Cort", 388939, 1), ("Samara", 423193, 1), ("Calida", 390394, 2), ("Audra", 451600, 2), ("Wendel", 308909, 1), ("Lorain", 316230, 1), ("Birch", 485136, 2), ("Gerianne", 468863, 1), ("Cesya", 385406, 2), ("Natty", 476175, 1), ("Phillipe", 397080, 2), ("Ase", 310180, 1), ("Lusa", 354163, 1), ("Reinaldos", 415241, 1), ("Stacee", 406341, 1), ("Rodolph", 352132, 2), ("Marthena", 306780, 2), ("Gwyneth", 447756, 2), ("Lonnie", 418389, 1), ("Hedy", 426474, 2), ("Minnnie", 351507, 1), ("Hugh", 480261, 1), ("Roch", 450263, 2), ("Angelika", 468944, 1), ("Olympia", 339104, 2), ("Enrica", 423696, 2), ("Nester", 376777, 2), ("Giuseppe", 449030, 2), ("Karie", 362662, 2), ("Leonerd", 344486, 2), ("Kellen", 324913, 1), ("Correna", 371078, 2), ("Pooh", 463995, 1), ("Charissa", 357421, 1), ("Lavinie", 324443, 2), ("Geordie", 393727, 1), ("Olia", 354548, 2), ("Davon", 420125, 2), ("Farris", 421025, 1), ("Lira", 366235, 1), ("Garik", 434965, 2), ("Ambrosio", 392368, 1), ("Marcille", 354294, 2), ("Jasen", 346194, 1), ("Guthrie", 440618, 2), ("Sophronia", 315133, 2), ("Tracey", 456177, 2), ("Mendie", 432341, 2), ("Alanson", 463918, 1), ("Kin", 444571, 2), ("Remus", 465991, 1), ("Jermaine", 489177, 1), ("Felizio", 368157, 1), ("Elonore", 377748, 2), ("Mirabella", 467049, 1), ("Lavena", 424718, 2), ("Husein", 420700, 2), ("Aland", 454372, 1), ("Cayla", 326111, 1), ("Shelden", 396329, 1), ("Roanna", 399525, 1), ("Rosalinde", 491358, 2), ("Chandler", 307438, 2), ("Demetris", 369639, 2), ("Carley", 366693, 2), ("Alicea", 463210, 2), ("Alina", 383776, 2), ("Godfry", 346284, 2), ("Mikkel", 311544, 2), ("Nadiya", 443338, 1), ("Shane", 479883, 2), ("Blake", 365065, 2), ("Elsey", 482538, 2), ("Reena", 466258, 2), ("Wyn", 350590, 1), ("Kahaleel", 405681, 1), ("Leandra", 470497, 2), ("Filippo", 377169, 1), ("Brigitta", 472191, 1), ("Agace", 304849, 2), ("Michele", 352998, 2), ("Cheslie", 458826, 1), ("Roddy", 456498, 1), ("Elaina", 490096, 2), ("Ben", 482663, 1), ("Vinson", 385719, 2), ("Massimo", 463067, 2), ("Kirsti", 338813, 2), ("Clarey", 344394, 2), ("Ilyssa", 350547, 1), ("Penrod", 494264, 2), ("Lexine", 304296, 1), ("Byrle", 380608, 2), ("Walton", 380670, 1), ("Teriann", 341747, 1), ("Emmit", 424400, 2), ("Patsy", 416996, 1), ("Farrell", 358908, 2), ("Brear", 419992, 1), ("Duffy", 301899, 1), ("Alley", 329110, 1), ("Diane", 441326, 1), ("Nat", 470922, 2), ("Sisile", 469071, 1), ("Aron", 378838, 1), ("Tandie", 457381, 2), ("Avery", 308873, 2), ("Udell", 330247, 1), ("Fawn", 341368, 1), ("Yorker", 349769, 1), ("Hannie", 448904, 2), ("Lotte", 323425, 1), ("Cyrus", 497140, 2), ("Marne", 365312, 2), ("Lolita", 411960, 1), ("Rorie", 468673, 2), ("Debee", 429552, 1), ("Garrott", 461271, 1), ("Gerrie", 315735, 1), ("Erwin", 445342, 1), ("Cyndie", 403744, 2), ("Loni", 301330, 2), ("Marcello", 343248, 2), ("Marketa", 454362, 2), ("Anetta", 433561, 2), ("Davidson", 384408, 2), ("Morgan", 389285, 1), ("Evy", 321859, 2), ("Raffaello", 377716, 1), ("Tyrus", 427345, 2), ("Whitney", 471226, 1), ("Carlee", 443842, 1), ("Anallese", 341453, 1), ("Garnet", 419507, 2), ("Kristi", 479735, 1), ("Dorotea", 414796, 1), ("Julianne", 413113, 1), ("Sallie", 383595, 2), ("Rudd", 320684, 2), ("Carlene", 310767, 1), ("Leanora", 368991, 2), ("Ermin", 454619, 2), ("Dionisio", 475625, 2), ("Gerick", 319516, 2), ("Kirstin", 365711, 1), ("Hugibert", 497241, 2), ("Cordelia", 439709, 2), ("Raven", 385762, 2), ("Caleb", 412285, 1), ("Edy", 349012, 1), ("Jackie", 414781, 1), ("Millie", 371344, 1), ("Saudra", 376604, 2), ("Camala", 361434, 2), ("Kassi", 401436, 2), ("Norry", 369719, 1), ("Shane", 375327, 2), ("Jeane", 485430, 2), ("Blayne", 491679, 1), ("Francois", 323492, 2), ("Roarke", 324551, 2), ("Mariele", 333531, 1), ("Cully", 335890, 2), ("Allsun", 407234, 1), ("Anthony", 329683, 2), ("Horace", 426248, 2), ("Kizzee", 483928, 2), ("Burnard", 421583, 1), ("Cullie", 410145, 2), ("Emmy", 421416, 2), ("Isidro", 403252, 1), ("Suzann", 420409, 2), ("Gradey", 331161, 2), ("Bernadina", 300124, 2), ("Jojo", 355999, 1), ("Rees", 304014, 2), ("Svend", 497757, 1), ("Shelden", 462287, 1), ("Kaye", 432709, 2), ("Josy", 410039, 1), ("Michele", 322084, 1), ("Cookie", 336193, 2), ("Andres", 340695, 1), ("Jeffry", 458709, 2), ("Jewelle", 380650, 2), ("Rube", 406468, 1), ("Emelita", 405004, 2), ("Vitoria", 414702, 2), ("Felita", 333039, 2), ("Kelsi", 320337, 1), ("Martie", 362352, 1), ("Stevena", 405869, 2), ("Carl", 399194, 2), ("Morey", 483496, 1), ("Kelcey", 461617, 2), ("Leelah", 452310, 1), ("Pattin", 355014, 1), ("Alejandro", 316036, 1), ("Hugues", 394687, 1), ("Arturo", 309353, 1), ("Arleyne", 455939, 1), ("Fraser", 342110, 2), ("Kamilah", 399904, 1), ("Ardeen", 476140, 1), ("Solly", 453806, 2), ("Antonietta", 437553, 1), ("Grete", 467187, 1), ("Filippa", 465931, 1), ("Brocky", 388750, 1), ("Vitia", 483575, 1), ("Yankee", 497094, 1), ("Lena", 328989, 2), ("Phaidra", 415749, 1), ("Lazar", 399241, 1), ("Lenora", 434712, 1), ("Dmitri", 344391, 2), ("Anya", 429810, 2), ("Sherrie", 398420, 2), ("Dee", 361726, 1), ("Scarlet", 442577, 2), ("Dee", 363694, 2), ("Laurie", 390908, 1), ("Teresina", 494109, 1), ("Rodolfo", 451275, 1), ("Madelon", 499383, 1), ("Chadd", 496239, 1), ("Shelden", 456664, 1), ("Yolane", 386453, 1), ("Rianon", 433954, 1), ("Agatha", 365990, 2), ("Bonnibelle", 434164, 2), ("Gerrard", 466597, 1), ("Elsey", 317124, 1), ("Harrietta", 416559, 2), ("Krystalle", 351194, 2), ("Nichol", 326903, 1), ("Margarete", 493051, 1), ("Currey", 467805, 2), ("Adlai", 388456, 2), ("Raye", 471177, 2), ("Prent", 418039, 2), ("Bryana", 320421, 1), ("Bondon", 431367, 1), ("Delcina", 372630, 1), ("Jen", 363584, 1), ("Zorina", 496429, 1), ("Renaldo", 471824, 1), ("Pieter", 302243, 2), ("Fee", 361060, 1), ("Gothart", 337356, 1), ("Darleen", 303197, 1), ("Theo", 467761, 2), ("Justino", 366673, 1), ("Derward", 364830, 2), ("Irina", 360305, 1), ("Eleanora", 353945, 2), ("Hale", 486242, 1), ("Shannen", 390649, 1), ("Birdie", 375931, 2), ("Borg", 491228, 1), ("Hale", 305904, 1), ("Chantal", 329403, 2), ("Nedi", 329827, 1), ("Christophorus", 360486, 1), ("Milt", 311974, 2), ("Melanie", 489755, 1), ("Demetre", 410278, 2), ("Guthry", 319947, 2), ("Winnie", 357181, 2), ("Trixi", 486486, 1), ("Kathi", 433849, 2), ("Stern", 308151, 1), ("Claudine", 382218, 1), ("Tabb", 387472, 2), ("Blake", 360555, 2), ("Jeannine", 328556, 2), ("Rutherford", 342405, 2), ("Kevin", 429252, 1), ("Gabie", 471166, 2), ("Stacy", 333785, 1), ("Enrika", 490707, 1), ("Chilton", 348649, 2), ("Elsinore", 425910, 2), ("Aurelie", 485836, 2), ("Trina", 447027, 2), ("Galvin", 459639, 2), ("Wenda", 406329, 1), ("Adelle", 392450, 1), ("Ax", 418558, 1), ("Brannon", 463742, 2), ("Amitie", 430666, 1), ("Blisse", 411541, 2), ("Egbert", 390647, 1), ("Jaquelyn", 418876, 1), ("Garrott", 404855, 2), ("Marlena", 406620, 1), ("Nerte", 325940, 1), ("Shelbi", 333396, 2), ("Ailina", 376929, 1), ("Krishnah", 455097, 2), ("Shell", 315599, 2), ("Minnnie", 411764, 1), ("Reiko", 483919, 2), ("Blayne", 464378, 2), ("Rosaleen", 393040, 1), ("Celina", 453616, 2), ("Callida", 329683, 2), ("Meade", 320159, 2), ("Cati", 382792, 2), ("Filberte", 463345, 2), ("Traver", 442256, 2), ("Mano", 449683, 1), ("Stephen", 471613, 2), ("Wildon", 397286, 1), ("Dennison", 472669, 1), ("Mal", 478967, 1), ("Terese", 303634, 2), ("Ashien", 372849, 2), ("Knox", 322326, 1), ("Aloise", 487686, 1), ("Micah", 441115, 1), ("Kalie", 398270, 1), ("Gwenora", 430390, 2), ("Dion", 459194, 1), ("Cletus", 449465, 2), ("Gwynne", 326744, 2), ("Floria", 483986, 2), ("Francois", 350430, 2), ("Kirk", 362894, 1), ("Emalee", 388865, 1), ("Leopold", 329490, 1), ("Crissie", 438147, 2), ("Gaelan", 473414, 2), ("Steve", 476701, 1), ("Carlynne", 473555, 1), ("Fayre", 363820, 2), ("Martha", 339966, 1), ("Riki", 428390, 1), ("Weidar", 320410, 1), ("Ardisj", 346123, 2), ("Melvyn", 312369, 2), ("Saleem", 480349, 2), ("Veriee", 349523, 2), ("Gwenore", 400226, 2), ("Van", 391692, 2), ("Tabbie", 361057, 2), ("Nevsa", 464180, 2), ("Manny", 319790, 1), ("Ketty", 399156, 1), ("Lynelle", 498592, 1), ("Idelle", 408211, 2), ("Francklyn", 466759, 1), ("Livvyy", 457185, 2), ("Nappie", 473551, 1), ("Evangelia", 469722, 2), ("Camilla", 447964, 2), ("Loraine", 439496, 1), ("Delainey", 350220, 1), ("Willy", 351442, 1), ("Rubie", 412486, 2), ("Guenevere", 477027, 1), ("Lynnea", 351204, 1), ("Jessie", 340982, 2), ("Maribelle", 389603, 2), ("Waldo", 320544, 2), ("Anita", 354338, 2), ("Sabine", 479102, 2), ("Dona", 394789, 1), ("Sophey", 303398, 1), ("Gwenni", 390158, 1), ("Thebault", 462418, 2), ("Antone", 328876, 2), ("Marla", 322816, 2), ("Cirstoforo", 396095, 1), ("Chloette", 318229, 2), ("Gretchen", 314145, 1), ("Floris", 391799, 2), ("Rania", 385596, 1), ("Evangelina", 387778, 1), ("Kristina", 305909, 2), ("Polly", 348859, 1), ("Hamid", 377404, 1), ("Valerye", 322730, 2), ("Bill", 304671, 1), ("Gwyn", 353855, 1), ("Kingston", 358158, 1), ("Vivie", 360534, 2), ("Gregg", 340042, 1), ("Gerick", 478122, 2), ("Hobart", 352683, 1), ("Morly", 386674, 2), ("Killy", 430071, 1), ("Othello", 333426, 2), ("Hart", 483112, 1), ("Timmi", 310488, 1), ("Romain", 460813, 2), ("Philly", 408613, 2), ("Ulberto", 361748, 1), ("Rodrick", 473262, 2), ("Bil", 374457, 1), ("Morton", 305159, 1), ("Sylas", 302385, 2), ("Miquela", 396901, 2), ("Jerald", 381324, 2), ("Ricky", 331232, 1), ("Drud", 346841, 1), ("Gaby", 383482, 2), ("Florenza", 386634, 1), ("Walt", 321618, 2), ("Fionna", 391672, 1), ("Lorry", 452635, 2), ("Rollie", 354806, 1), ("Belita", 306465, 1), ("Agnes", 474323, 1), ("Laird", 465818, 2), ("Alfonse", 392177, 1), ("Wadsworth", 329141, 2), ("Lew", 343333, 2), ("Smith", 388444, 1), ("Trefor", 472394, 1), ("Parnell", 479711, 2), ("Ellie", 411510, 1), ("Marius", 497933, 2), ("Brandie", 492688, 1), ("Priscella", 378524, 1), ("Serena", 466344, 1), ("Kit", 456201, 2), ("Solomon", 371780, 1), ("Merrilee", 437490, 1), ("Flo", 367112, 2), ("Tricia", 345420, 2), ("Auberon", 350897, 2), ("Aurelea", 305152, 1), ("Arlinda", 420768, 1), ("Gilberte", 389466, 2), ("Wilma", 387496, 1), ("Dino", 379952, 1), ("Hasty", 401665, 1), ("Meir", 375312, 1), ("Wallis", 414751, 1), ("Kristen", 377229, 1), ("Rob", 382525, 2), ("Benedikta", 434037, 1), ("Cairistiona", 313680, 1), ("Legra", 354394, 1), ("Wallis", 479619, 2), ("Niel", 343217, 2), ("Jay", 450288, 2), ("Wilek", 394161, 2), ("Shawna", 470382, 2), ("Cecilla", 433363, 1), ("Isaiah", 413560, 1), ("Clare", 413038, 1), ("Johannes", 388948, 2), ("Dionis", 423799, 1), ("Babbie", 476541, 2), ("Rayshell", 329259, 1), ("Phylis", 413380, 2), ("Gavrielle", 469700, 1), ("Svend", 467669, 2), ("Lucienne", 497971, 1), ("Abbott", 302570, 1), ("Meggy", 442612, 1), ("Bowie", 337628, 1), ("Eustacia", 455314, 2), ("Fayth", 325597, 2), ("Bil", 339293, 2), ("Jemmie", 455881, 2), ("Northrop", 379168, 2), ("Byrle", 385758, 2), ("Gabriele", 416910, 2), ("Delinda", 476113, 1), ("Alexa", 316431, 1), ("Adina", 413596, 1), ("Tisha", 459859, 1), ("Kinny", 461312, 1), ("Sib", 458397, 1), ("Jenna", 424465, 1), ("Alix", 470164, 1), ("Keene", 357748, 2), ("Sasha", 338262, 2), ("Enrica", 423314, 2), ("Adaline", 432594, 1), ("Ruben", 368385, 2), ("Bev", 320807, 2), ("Coleman", 323552, 1), ("Tad", 303047, 2), ("Brigida", 482137, 2), ("Karlotta", 300386, 1), ("Clio", 397407, 2), ("Godard", 338333, 2), ("Terrell", 345868, 2), ("Tristam", 455936, 2), ("Sarah", 308074, 1), ("Nickolai", 427990, 2), ("Elna", 331999, 1), ("Gabriel", 360181, 1), ("Myles", 365005, 2), ("Kizzee", 301929, 1), ("Beryl", 401383, 2), ("Tobie", 491074, 1), ("Blakelee", 437520, 1), ("Archer", 333584, 2), ("Issy", 385672, 1), ("Yoko", 394994, 1), ("Lindy", 476951, 1), ("Gail", 316308, 1), ("Adelaide", 452057, 2), ("Devora", 310735, 2), ("Vinny", 341439, 2), ("Mufi", 429901, 2), ("Rosalie", 339165, 2), ("Deanne", 474604, 1), ("Em", 382348, 1), ("Haroun", 374932, 2), ("Weylin", 336993, 2), ("Fields", 343249, 1), ("Courtnay", 339294, 1), ("Mickie", 483031, 2), ("Clemente", 466624, 2), ("Audie", 377126, 2), ("Jodie", 477156, 1), ("Ajay", 474676, 2), ("Jessamine", 307315, 2), ("Charla", 453787, 1), ("Keary", 485923, 1), ("Erda", 411231, 1), ("Elka", 486368, 2), ("Bel", 481467, 1), ("Jaclyn", 459666, 1), ("Brandice", 343673, 1), ("Johnathan", 374337, 1), ("Howard", 333224, 1), ("Danny", 439279, 2), ("Fanya", 410003, 1), ("Winnie", 491858, 1), ("Joana", 370155, 2), ("Ceil", 414953, 1), ("Vonni", 380684, 2), ("Eda", 389892, 1), ("Franciska", 400967, 1), ("Alvira", 451512, 1), ("Suzi", 425317, 1), ("Vail", 397307, 1), ("Chandler", 375966, 1), ("Genna", 424787, 1), ("Sheri", 408748, 1), ("Fredericka", 388431, 2), ("Korella", 427461, 2), ("Perry", 333718, 1), ("Trixie", 494713, 2), ("Lizabeth", 387257, 2), ("Shaina", 352309, 2), ("Patten", 418266, 2), ("Frazer", 339471, 2), ("Erin", 427984, 1), ("Kirbie", 458573, 2), ("Abel", 339994, 2), ("Corty", 426652, 2), ("Meara", 346030, 2), ("Julee", 448208, 2), ("Hunter", 366509, 1), ("Augustine", 413824, 1), ("Brendis", 322561, 2), ("Hamil", 321881, 1), ("Garfield", 324382, 1), ("Fran", 339326, 1), ("Cordy", 327745, 2), ("Hedi", 410191, 1), ("Edwin", 467658, 1), ("Tressa", 424153, 2), ("Kristel", 367980, 2), ("Gallard", 456651, 2), ("Lorri", 490364, 2), ("Orsola", 351327, 1), ("Shela", 331520, 2), ("Forrester", 328660, 2), ("Sydney", 470697, 1), ("Demetris", 430614, 1), ("Elton", 424179, 2), ("Truman", 462537, 2), ("Bay", 342489, 2), ("Yankee", 350864, 1), ("Anica", 340632, 1), ("Celestyna", 325130, 2), ("Hayward", 435543, 2), ("Belia", 433145, 2), ("Niven", 365077, 2), ("Hersch", 425746, 2), ("Vern", 330132, 2), ("Eddie", 365626, 2), ("Sherm", 384286, 2), ("Adena", 486011, 1), ("Miles", 401853, 2), ("Peder", 432088, 2), ("Ardisj", 410444, 2), ("Amble", 359663, 2), ("Vivianna", 335226, 2), ("Ferdinand", 328339, 2), ("Jens", 426073, 1), ("Jelene", 403500, 2), ("Jeddy", 403752, 1), ("Lorettalorna", 343657, 1), ("Lyndel", 460961, 1), ("Burnard", 325430, 2), ("Alexandre", 421751, 1), ("Donny", 310814, 2), ("Mychal", 450878, 1), ("Bianka", 353956, 2), ("Lilla", 349253, 1), ("Lyle", 353777, 2), ("Martino", 474904, 2), ("Francene", 428674, 2), ("Lief", 484822, 1), ("Talya", 403597, 1), ("Manny", 395556, 2), ("Alonzo", 405379, 1), ("Terrence", 391551, 2), ("Elfrieda", 474070, 2), ("Adelle", 320165, 1), ("Marcelle", 331686, 2), ("Alvan", 486492, 2), ("Minette", 412343, 1), ("Hieronymus", 411823, 2), ("Vivian", 404777, 2), ("Hoyt", 482229, 1), ("Creigh", 300928, 1), ("Tonie", 353008, 1), ("Melva", 403467, 1), ("Biddie", 461101, 2), ("Colman", 421608, 1), ("Dot", 351763, 2), ("Burl", 409638, 2), ("Mick", 381692, 2), ("Raquela", 417714, 2), ("Syman", 308078, 2), ("Gnni", 389781, 2), ("Meyer", 370004, 2), ("Selena", 388334, 2), ("Hobey", 401499, 2), ("Tiphany", 439620, 2), ("Monique", 374837, 1), ("Mattie", 493864, 1), ("Kayla", 483989, 2), ("Gabie", 434992, 1), ("Joly", 401322, 2), ("Trent", 361940, 1), ("Cristian", 475343, 2), ("Mendy", 466633, 2), ("Kikelia", 384755, 1), ("Ives", 413912, 1), ("Cinderella", 412479, 1), ("Concordia", 401438, 1), ("Nyssa", 437009, 2), ("Fernando", 349501, 1), ("Rozina", 310787, 2), ("Kippy", 404656, 2), ("Basilius", 316277, 2), ("Sela", 412201, 2), ("Sayers", 392561, 2), ("Kirsten", 475241, 2), ("Jermaine", 339485, 2), ("Jasun", 410902, 1), ("Daisi", 436371, 1), ("Crystie", 480559, 2), ("Nanette", 345400, 1), ("Jillie", 417154, 1), ("Gaby", 301041, 2), ("Ernesta", 378744, 1), ("Hartwell", 460288, 2), ("Dodi", 493292, 2), ("Andi", 324547, 2), ("Mildrid", 419549, 2), ("Cass", 378878, 2), ("Aland", 459003, 2), ("Cassandre", 309404, 1), ("Stern", 410720, 2), ("Eachelle", 387625, 2), ("Tabitha", 366465, 2), ("Letitia", 316367, 2), ("Emlynn", 332360, 2), ("Minnnie", 412451, 1), ("Adrea", 459395, 1), ("Ermengarde", 351670, 1), ("Parrnell", 411090, 2), ("Merrili", 410055, 1), ("Obadias", 468728, 1), ("Kassi", 352876, 1), ("Anatol", 371440, 1), ("Marni", 369683, 1), ("Forster", 352491, 1), ("Jerrie", 409891, 1), ("Brenda", 346343, 2), ("Ernaline", 410497, 1), ("Vernon", 457573, 1), ("Perrine", 356638, 1), ("Roi", 435389, 2), ("Mada", 359723, 2), ("Kynthia", 328967, 2), ("Malvina", 351922, 2), ("Trip", 499911, 2), ("Bili", 374730, 2), ("Ara", 458343, 2), ("Ruthe", 469210, 2), ("Patty", 342172, 1), ("Danice", 317373, 2), ("Shina", 300025, 2), ("Alvy", 306197, 2), ("Maegan", 453060, 2), ("Winnie", 482390, 1), ("Haze", 496576, 1), ("Mick", 463539, 1), ("Abe", 480236, 2), ("Theressa", 331420, 2), ("Karine", 487184, 2), ("Adamo", 433395, 2), ("Roland", 486979, 1), ("Fleming", 438222, 1), ("Janella", 413107, 2), ("Nikola", 491491, 2), ("Cullen", 378208, 1), ("Skipton", 336011, 1), ("Valencia", 482663, 2), ("Winona", 476199, 2), ("Lon", 374825, 2), ("Marley", 399652, 2), ("Craggie", 309331, 2), ("Filbert", 494667, 2), ("Clarance", 498495, 1), ("Bethena", 366468, 2), ("Sofie", 486907, 1), ("Melamie", 468390, 2), ("Shanta", 463615, 2), ("Wyatan", 381744, 1), ("Cynthie", 492621, 2), ("Crawford", 327543, 2), ("Arvy", 380816, 1), ("Javier", 347460, 1), ("Max", 347437, 2), ("Dacie", 347254, 2), ("Antonella", 433193, 1), ("Gwendolin", 429677, 2), ("Cornelius", 404291, 2), ("Lulu", 495087, 1), ("Karee", 339578, 1), ("Gael", 499222, 2), ("Palm", 431174, 1), ("Liam", 410517, 1), ("Diandra", 417879, 1), ("Pooh", 336862, 2), ("Emlynn", 473320, 1), ("Idaline", 374420, 1), ("Harper", 413314, 1), ("Humphrey", 414026, 2), ("Sisile", 389343, 2), ("Brandtr", 339340, 1), ("Fredericka", 308733, 2), ("Munroe", 461856, 2), ("Martyn", 496913, 1), ("Ilario", 353861, 2), ("Estell", 374053, 2), ("Adelind", 485949, 2), ("Alaine", 336716, 2), ("Issiah", 449803, 1), ("Batholomew", 494147, 2), ("Klarrisa", 360938, 2), ("Eugenie", 333246, 2), ("Georges", 383200, 1), ("Joelle", 497348, 2), ("Vivie", 390780, 2), ("Nita", 328350, 1), ("Junia", 436687, 1), ("Donal", 426790, 1), ("Isaak", 459427, 1), ("Arel", 361280, 1), ("Hillier", 358099, 1), ("Teador", 330956, 2), ("Caralie", 396924, 2), ("Malcolm", 485022, 2), ("Cointon", 388022, 2), ("Huntington", 474949, 2), ("Carmel", 459816, 2), ("Lorna", 438559, 1), ("Roddy", 345810, 2), ("Birgit", 424699, 2), ("Gwenni", 335213, 2), ("Hugh", 426664, 2), ("Mia", 309167, 2), ("Sherry", 373286, 1), ("Herbert", 387766, 2), ("Jordana", 395326, 2), ("Sally", 477541, 1), ("Terri", 464542, 2), ("Roseanne", 494317, 2), ("Winny", 376831, 2), ("Tamma", 368170, 1), ("Berty", 465041, 1), ("Corrie", 336958, 1), ("Vladamir", 383698, 2), ("Rees", 335220, 1), ("Willetta", 307784, 2), ("Cordey", 311408, 2), ("Janessa", 405588, 1), ("Misti", 411121, 1), ("Salomi", 344032, 1), ("Briant", 348209, 1), ("Enrique", 339495, 2), ("Guenna", 305256, 2), ("Leilah", 367746, 1), ("Shell", 398405, 2), ("Spense", 458511, 1), ("Galvin", 397734, 1), ("Isabelita", 379569, 2), ("Anabel", 413920, 2), ("Tobit", 422054, 2), ("Nicole", 402309, 1), ("Nerty", 419864, 2), ("Garwood", 362295, 2), ("Katha", 425780, 2), ("Ferrel", 446366, 1), ("Grissel", 366600, 2), ("Andrus", 444353, 1), ("Mandi", 453474, 1), ("Marita", 337052, 2), ("Trixi", 443543, 2), ("Honoria", 457975, 1), ("Arnie", 494905, 1), ("Gwyneth", 309456, 1), ("Rogers", 491174, 2), ("Marje", 373155, 1), ("Roldan", 337556, 2), ("Georgie", 380896, 1), ("Elfie", 426830, 1), ("Ora", 483466, 1), ("Nero", 388723, 2), ("Eden", 456826, 1), ("Denni", 480107, 1), ("Maressa", 410189, 1), ("Cookie", 309155, 2), ("Katerine", 339826, 2), ("Cherice", 434106, 1), ("Brucie", 368414, 1), ("Heidie", 356897, 2), ("Pippo", 361634, 1), ("Dmitri", 393363, 1), ("Klement", 345834, 1), ("Verla", 332051, 1), ("Glennie", 361632, 1), ("Shel", 486243, 1), ("Ardyth", 481816, 1), ("Claudette", 384294, 1), ("Stesha", 407487, 1), ("Nannette", 345805, 2), ("Carita", 343749, 2), ("Joy", 328722, 2), ("Cristy", 305198, 1), ("Minna", 488844, 2), ("Kirstin", 453017, 2), ("Alanna", 314370, 1), ("Anastassia", 469278, 2), ("Chic", 359838, 1), ("Jackelyn", 451375, 2), ("Maryjo", 394872, 1), ("Claudetta", 402984, 2), ("Sadella", 408749, 2), ("Lilllie", 350018, 2), ("Charil", 348331, 1), ("Kaile", 461845, 1), ("Leora", 303197, 2), ("Etta", 376972, 2), ("Eddie", 327435, 2), ("Murdoch", 387563, 1), ("Torrie", 455085, 1), ("Dolf", 493483, 2), ("Dorian", 352553, 1), ("Hadrian", 327980, 1), ("Jobie", 379387, 1), ("Rodolph", 321417, 2), ("Bealle", 368042, 1), ("Hollie", 414610, 1), ("Clayson", 472569, 2), ("Kimberlee", 425130, 2), ("Killy", 309834, 2), ("Garrard", 387243, 1), ("Tedman", 438358, 1), ("Fredra", 445890, 1), ("Ezechiel", 337751, 1), ("Tessa", 310674, 2), ("Royal", 472254, 1), ("Leroi", 376933, 2), ("Ainslee", 438840, 1), ("Jard", 496080, 1), ("Cosimo", 326003, 1), ("Alexine", 331989, 1), ("Raina", 450976, 2), ("Kriste", 341560, 1), ("Grove", 432357, 1), ("Roch", 347856, 1), ("Danika", 363736, 1), ("Adaline", 368174, 1), ("Raquel", 413542, 1), ("Delcine", 464721, 1), ("Ignacio", 431332, 2), ("Binny", 314091, 2), ("Madelle", 381944, 2), ("Tucky", 388246, 2), ("Willamina", 422073, 2), ("Siegfried", 428286, 2), ("Gunther", 370496, 2), ("Benny", 451708, 2), ("Edd", 395498, 2), ("Clarinda", 425635, 2), ("Reagen", 402824, 1), ("Kenn", 413143, 2), ("Caroljean", 354628, 2), ("Lincoln", 380160, 2), ("Maryann", 443969, 1), ("Ellary", 428999, 1), ("Gherardo", 462171, 2), ("Timoteo", 438072, 2))
    private val results = List((373583, 5.0), (476749, 5.0), (345909, 4.0), (307055, 5.0), (423496, 5.0), (393508, 4.0), (434824, 5.0), (374286, 5.0), (319004, 3.0), (325856, 3.7), (468023, 5.0), (383096, 3.7), (434105, 3.7), (451072, 3.3), (334066, 5.0), (494932, 5.0), (303659, 5.0), (460296, 5.0), (466208, 2.7), (497277, 3.3), (451487, 3.3), (304500, 3.7), (335189, 5.0), (396638, 4.0), (352284, 5.0), (463248, 3.7), (473778, 5.0), (345246, 4.0), (488058, 5.0), (422016, 5.0), (460693, 4.0), (337679, 3.7), (356625, 5.0), (320692, 3.7), (487674, 5.0), (397483, 3.7), (384638, 5.0), (320650, 3.7), (476369, 5.0), (499766, 5.0), (416688, 5.0), (338526, 4.0), (367390, 5.0), (341507, 5.0), (318817, 3.7), (470347, 3.7), (355970, 2.3), (399307, 3.7), (470191, 5.0), (436363, 4.0), (327985, 4.0), (401982, 5.0), (389489, 3.7), (428090, 3.7), (372321, 5.0), (494075, 3.3), (484586, 4.0), (335827, 5.0), (308589, 4.0), (306454, 3.3), (386429, 2.3), (360078, 3.3), (473443, 5.0), (332631, 4.0), (352928, 2.3), (476955, 5.0), (322544, 3.7), (309935, 4.0), (425221, 5.0), (401259, 1.3), (430514, 3.7), (379803, 5.0), (336386, 5.0), (498418, 2.7), (443513, 5.0), (314594, 5.0), (320557, 5.0), (490064, 5.0), (420658, 5.0), (467174, 5.0), (388939, 4.0), (423193, 5.0), (390394, 3.3), (451600, 5.0), (308909, 5.0), (316230, 5.0), (485136, 4.0), (468863, 3.3), (385406, 5.0), (476175, 5.0), (397080, 5.0), (310180, 4.0), (354163, 5.0), (415241, 5.0), (406341, 3.7), (352132, 5.0), (306780, 4.0), (447756, 4.0), (418389, 5.0), (426474, 5.0), (351507, 4.0), (480261, 3.3), (450263, 5.0), (468944, 5.0), (339104, 2.3), (423696, 5.0), (376777, 3.0), (449030, 4.0), (362662, 3.7), (344486, 5.0), (324913, 4.0), (371078, 3.0), (463995, 4.0), (357421, 5.0), (324443, 5.0), (393727, 5.0), (354548, 5.0), (420125, 5.0), (421025, 5.0), (366235, 5.0), (434965, 5.0), (392368, 5.0), (354294, 5.0), (346194, 3.3), (440618, 5.0), (315133, 4.0), (456177, 5.0), (432341, 2.3), (463918, 5.0), (444571, 3.7), (465991, 5.0), (489177, 5.0), (368157, 3.7), (377748, 5.0), (467049, 5.0), (424718, 4.0), (420700, 5.0), (454372, 5.0), (326111, 5.0), (396329, 4.0), (399525, 5.0), (491358, 4.0), (307438, 3.7), (369639, 4.0), (366693, 4.0), (463210, 5.0), (383776, 5.0), (346284, 3.0), (311544, 5.0), (443338, 4.0), (479883, 4.0), (365065, 4.0), (482538, 3.7), (466258, 5.0), (350590, 5.0), (405681, 5.0), (470497, 5.0), (377169, 4.0), (472191, 5.0), (304849, 5.0), (352998, 4.0), (458826, 4.0), (456498, 3.3), (490096, 5.0), (482663, 5.0), (385719, 5.0), (463067, 5.0), (338813, 3.0), (344394, 5.0), (350547, 4.0), (494264, 5.0), (304296, 3.7), (380608, 5.0), (380670, 3.7), (341747, 4.0), (424400, 5.0), (416996, 3.0), (358908, 5.0), (419992, 3.3), (301899, 4.0), (329110, 2.0), (441326, 5.0), (470922, 5.0), (469071, 3.3), (378838, 5.0), (457381, 3.7), (308873, 5.0), (330247, 5.0), (341368, 4.0), (349769, 5.0), (448904, 5.0), (323425, 5.0), (497140, 5.0), (365312, 5.0), (411960, 4.0), (468673, 4.0), (429552, 5.0), (461271, 5.0), (315735, 4.0), (445342, 5.0), (403744, 4.0), (301330, 4.0), (343248, 4.0), (454362, 4.0), (433561, 3.3), (384408, 3.0), (389285, 5.0), (321859, 4.0), (377716, 5.0), (427345, 4.0), (471226, 3.7), (443842, 2.0), (341453, 5.0), (419507, 4.0), (479735, 3.3), (414796, 5.0), (413113, 4.0), (383595, 5.0), (320684, 4.0), (310767, 4.0), (368991, 5.0), (454619, 3.7), (475625, 5.0), (319516, 3.0), (365711, 4.0), (497241, 4.0), (439709, 5.0), (385762, 3.7), (412285, 5.0), (349012, 3.7), (414781, 5.0), (371344, 5.0), (376604, 4.0), (361434, 5.0), (401436, 5.0), (369719, 5.0), (375327, 5.0), (485430, 5.0), (491679, 3.0), (323492, 5.0), (324551, 4.0), (333531, 5.0), (335890, 3.3), (407234, 3.0), (329683, 4.0), (426248, 4.0), (483928, 3.3), (421583, 3.0), (410145, 5.0), (421416, 5.0), (403252, 5.0), (420409, 4.0), (331161, 5.0), (300124, 3.3), (355999, 3.3), (304014, 5.0), (497757, 5.0), (462287, 5.0), (432709, 4.0), (410039, 5.0), (322084, 5.0), (336193, 5.0), (340695, 3.3), (458709, 5.0), (380650, 5.0), (406468, 5.0), (405004, 5.0), (414702, 4.0), (333039, 3.7), (320337, 4.0), (362352, 3.7), (405869, 3.7), (399194, 5.0), (483496, 2.7), (461617, 4.0), (452310, 5.0), (355014, 5.0), (316036, 5.0), (394687, 4.0), (309353, 4.0), (455939, 4.0), (342110, 4.0), (399904, 5.0), (476140, 5.0), (453806, 4.0), (437553, 5.0), (467187, 3.3), (465931, 5.0), (388750, 5.0), (483575, 3.3), (497094, 5.0), (328989, 5.0), (415749, 5.0), (399241, 3.3), (434712, 5.0), (344391, 4.0), (429810, 4.0), (398420, 4.0), (361726, 5.0), (442577, 3.3), (363694, 5.0), (390908, 2.3), (494109, 3.7), (451275, 5.0), (499383, 3.0), (496239, 5.0), (456664, 5.0), (386453, 3.7), (433954, 3.7), (365990, 4.0), (434164, 5.0), (466597, 5.0), (317124, 5.0), (416559, 4.0), (351194, 5.0), (326903, 3.7), (493051, 5.0), (467805, 5.0), (388456, 1.7), (471177, 4.0), (418039, 3.0), (320421, 5.0), (431367, 4.0), (372630, 4.0), (363584, 4.0), (496429, 5.0), (471824, 5.0), (302243, 5.0), (361060, 2.7), (337356, 5.0), (303197, 5.0), (467761, 4.0), (366673, 4.0), (364830, 4.0), (360305, 3.0), (353945, 5.0), (486242, 2.3), (390649, 5.0), (375931, 5.0), (491228, 3.0), (305904, 4.0), (329403, 4.0), (329827, 5.0), (360486, 5.0), (311974, 5.0), (489755, 3.7), (410278, 3.7), (319947, 4.0), (357181, 4.0), (486486, 4.0), (433849, 5.0), (308151, 5.0), (382218, 3.7), (387472, 3.3), (360555, 4.0), (328556, 5.0), (342405, 5.0), (429252, 4.0), (471166, 3.7), (333785, 5.0), (490707, 4.0), (348649, 5.0), (425910, 4.0), (485836, 4.0), (447027, 3.7), (459639, 3.7), (406329, 5.0), (392450, 5.0), (418558, 3.0), (463742, 3.7), (430666, 4.0), (411541, 5.0), (390647, 3.7), (418876, 4.0), (404855, 3.7), (406620, 3.7), (325940, 5.0), (333396, 5.0), (376929, 5.0), (455097, 4.0), (315599, 5.0), (411764, 5.0), (483919, 3.7), (464378, 5.0), (393040, 5.0), (453616, 3.3), (329683, 5.0), (320159, 5.0), (382792, 4.0), (463345, 5.0), (442256, 4.0), (449683, 5.0), (471613, 5.0), (397286, 5.0), (472669, 5.0), (478967, 5.0), (303634, 4.0), (372849, 5.0), (322326, 3.3), (487686, 5.0), (441115, 4.0), (398270, 5.0), (430390, 5.0), (459194, 5.0), (449465, 3.3), (326744, 3.7), (483986, 2.7), (350430, 5.0), (362894, 5.0), (388865, 5.0), (329490, 5.0), (438147, 5.0), (473414, 5.0), (476701, 5.0), (473555, 3.7), (363820, 5.0), (339966, 5.0), (428390, 5.0), (320410, 3.0), (346123, 5.0), (312369, 5.0), (480349, 5.0), (349523, 4.0), (400226, 3.3), (391692, 5.0), (361057, 4.0), (464180, 5.0), (319790, 5.0), (399156, 3.3), (498592, 4.0), (408211, 3.7), (466759, 5.0), (457185, 3.0), (473551, 5.0), (469722, 4.0), (447964, 5.0), (439496, 4.0), (350220, 4.0), (351442, 5.0), (412486, 5.0), (477027, 2.0), (351204, 4.0), (340982, 5.0), (389603, 4.0), (320544, 5.0), (354338, 3.0), (479102, 5.0), (394789, 3.3), (303398, 4.0), (390158, 5.0), (462418, 3.3), (328876, 5.0), (322816, 4.0), (396095, 4.0), (318229, 5.0), (314145, 5.0), (391799, 3.3), (385596, 4.0), (387778, 1.3), (305909, 3.0), (348859, 4.0), (377404, 4.0), (322730, 5.0), (304671, 4.0), (353855, 5.0), (358158, 3.7), (360534, 5.0), (340042, 5.0), (478122, 3.3), (352683, 5.0), (386674, 5.0), (430071, 4.0), (333426, 5.0), (483112, 5.0), (310488, 5.0), (460813, 5.0), (408613, 5.0), (361748, 5.0), (473262, 3.7), (374457, 5.0), (305159, 5.0), (302385, 5.0), (396901, 5.0), (381324, 4.0), (331232, 4.0), (346841, 3.7), (383482, 3.7), (386634, 5.0), (321618, 5.0), (391672, 4.0), (452635, 5.0), (354806, 5.0), (306465, 5.0), (474323, 5.0), (465818, 5.0), (392177, 4.0), (329141, 5.0), (343333, 5.0), (388444, 5.0), (472394, 5.0), (479711, 3.0), (411510, 4.0), (497933, 5.0), (492688, 3.3), (378524, 4.0), (466344, 3.7), (456201, 5.0), (371780, 4.0), (437490, 5.0), (367112, 5.0), (345420, 5.0), (350897, 4.0), (305152, 5.0), (420768, 5.0), (389466, 4.0), (387496, 5.0), (379952, 5.0), (401665, 4.0), (375312, 5.0), (414751, 5.0), (377229, 5.0), (382525, 5.0), (434037, 5.0), (313680, 5.0), (354394, 5.0), (479619, 4.0), (343217, 5.0), (450288, 5.0), (394161, 3.3), (470382, 5.0), (433363, 3.7), (413560, 5.0), (413038, 5.0), (388948, 5.0), (423799, 5.0), (476541, 4.0), (329259, 3.3), (413380, 5.0), (469700, 5.0), (467669, 5.0), (497971, 4.0), (302570, 5.0), (442612, 5.0), (337628, 4.0), (455314, 4.0), (325597, 5.0), (339293, 5.0), (455881, 4.0), (379168, 3.3), (385758, 4.0), (416910, 3.7), (476113, 5.0), (316431, 4.0), (413596, 4.0), (459859, 5.0), (461312, 5.0), (458397, 4.0), (424465, 3.7), (470164, 4.0), (357748, 4.0), (338262, 4.0), (423314, 3.3), (432594, 5.0), (368385, 4.0), (320807, 5.0), (323552, 5.0), (303047, 5.0), (482137, 5.0), (300386, 3.7), (397407, 4.0), (338333, 5.0), (345868, 3.7), (455936, 3.7), (308074, 4.0), (427990, 3.0), (331999, 2.3), (360181, 5.0), (365005, 5.0), (301929, 3.7), (401383, 4.0), (491074, 5.0), (437520, 5.0), (333584, 5.0), (385672, 4.0), (394994, 5.0), (476951, 5.0), (316308, 4.0), (452057, 5.0), (310735, 3.7), (341439, 4.0), (429901, 4.0), (339165, 4.0), (474604, 5.0), (382348, 4.0), (374932, 5.0), (336993, 3.3), (343249, 5.0), (339294, 5.0), (483031, 3.7), (466624, 5.0), (377126, 3.7), (477156, 3.7), (474676, 3.7), (307315, 5.0), (453787, 4.0), (485923, 4.0), (411231, 5.0), (486368, 5.0), (481467, 3.7), (459666, 4.0), (343673, 4.0), (374337, 4.0), (333224, 5.0), (439279, 4.0), (410003, 3.0), (491858, 5.0), (370155, 5.0), (414953, 4.0), (380684, 5.0), (389892, 3.3), (400967, 5.0), (451512, 5.0), (425317, 5.0), (397307, 5.0), (375966, 3.3), (424787, 4.0), (408748, 3.7), (388431, 5.0), (427461, 5.0), (333718, 5.0), (494713, 3.7), (387257, 4.0), (352309, 5.0), (418266, 5.0), (339471, 3.3), (427984, 5.0), (458573, 3.0), (339994, 5.0), (426652, 4.0), (346030, 4.0), (448208, 5.0), (366509, 5.0), (413824, 5.0), (322561, 4.0), (321881, 3.7), (324382, 4.0), (339326, 5.0), (327745, 4.0), (410191, 3.7), (467658, 5.0), (424153, 3.3), (367980, 4.0), (456651, 5.0), (490364, 4.0), (351327, 5.0), (331520, 3.7), (328660, 3.7), (470697, 3.7), (430614, 4.0), (424179, 5.0), (462537, 3.3), (342489, 3.0), (350864, 5.0), (340632, 5.0), (325130, 5.0), (435543, 4.0), (433145, 4.0), (365077, 5.0), (425746, 5.0), (330132, 3.7), (365626, 5.0), (384286, 5.0), (486011, 5.0), (401853, 5.0), (432088, 3.7), (410444, 4.0), (359663, 5.0), (335226, 4.0), (328339, 4.0), (426073, 3.3), (403500, 2.3), (403752, 3.0), (343657, 4.0), (460961, 5.0), (325430, 3.7), (421751, 4.0), (310814, 4.0), (450878, 4.0), (353956, 5.0), (349253, 4.0), (353777, 5.0), (474904, 5.0), (428674, 5.0), (484822, 4.0), (403597, 3.3), (395556, 4.0), (405379, 4.0), (391551, 5.0), (474070, 3.7), (320165, 3.7), (331686, 5.0), (486492, 3.7), (412343, 4.0), (411823, 5.0), (404777, 4.0), (482229, 4.0), (300928, 5.0), (353008, 5.0), (403467, 3.7), (461101, 4.0), (421608, 3.7), (351763, 4.0), (409638, 2.7), (381692, 3.7), (417714, 4.0), (308078, 4.0), (389781, 5.0), (370004, 5.0), (388334, 5.0), (401499, 3.3), (439620, 5.0), (374837, 4.0), (493864, 4.0), (483989, 5.0), (434992, 4.0), (401322, 3.3), (361940, 5.0), (475343, 5.0), (466633, 4.0), (384755, 5.0), (413912, 4.0), (412479, 4.0), (401438, 2.7), (437009, 3.7), (349501, 5.0), (310787, 3.0), (404656, 5.0), (316277, 5.0), (412201, 3.3), (392561, 3.7), (475241, 2.3), (339485, 5.0), (410902, 5.0), (436371, 3.7), (480559, 3.0), (345400, 5.0), (417154, 3.7), (301041, 5.0), (378744, 5.0), (460288, 2.7), (493292, 4.0), (324547, 5.0), (419549, 5.0), (378878, 5.0), (459003, 4.0), (309404, 5.0), (410720, 5.0), (387625, 5.0), (366465, 5.0), (316367, 4.0), (332360, 5.0), (412451, 5.0), (459395, 5.0), (351670, 3.7), (411090, 5.0), (410055, 4.0), (468728, 5.0), (352876, 5.0), (371440, 4.0), (369683, 5.0), (352491, 5.0), (409891, 5.0), (346343, 4.0), (410497, 5.0), (457573, 5.0), (356638, 3.7), (435389, 5.0), (359723, 3.7), (328967, 5.0), (351922, 4.0), (499911, 5.0), (374730, 3.0), (458343, 5.0), (469210, 5.0), (342172, 5.0), (317373, 4.0), (300025, 5.0), (306197, 4.0), (453060, 4.0), (482390, 5.0), (496576, 4.0), (463539, 3.7), (480236, 4.0), (331420, 5.0), (487184, 4.0), (433395, 5.0), (486979, 4.0), (438222, 4.0), (413107, 5.0), (491491, 5.0), (378208, 3.7), (336011, 5.0), (482663, 4.0), (476199, 2.3), (374825, 5.0), (399652, 3.3), (309331, 4.0), (494667, 5.0), (498495, 5.0), (366468, 5.0), (486907, 5.0), (468390, 5.0), (463615, 5.0), (381744, 5.0), (492621, 3.3), (327543, 5.0), (380816, 3.7), (347460, 5.0), (347437, 5.0), (347254, 5.0), (433193, 4.0), (429677, 4.0), (404291, 5.0), (495087, 3.3), (339578, 3.7), (499222, 5.0), (431174, 4.0), (410517, 5.0), (417879, 5.0), (336862, 4.0), (473320, 4.0), (374420, 4.0), (413314, 5.0), (414026, 5.0), (389343, 5.0), (339340, 4.0), (308733, 4.0), (461856, 5.0), (496913, 4.0), (353861, 3.0), (374053, 5.0), (485949, 3.7), (336716, 3.7), (449803, 5.0), (494147, 4.0), (360938, 4.0), (333246, 5.0), (383200, 5.0), (497348, 5.0), (390780, 5.0), (328350, 4.0), (436687, 3.3), (426790, 5.0), (459427, 5.0), (361280, 4.0), (358099, 5.0), (330956, 5.0), (396924, 4.0), (485022, 4.0), (388022, 5.0), (474949, 5.0), (459816, 5.0), (438559, 4.0), (345810, 3.3), (424699, 5.0), (335213, 5.0), (426664, 3.0), (309167, 4.0), (373286, 4.0), (387766, 3.7), (395326, 5.0), (477541, 5.0), (464542, 3.7), (494317, 4.0), (376831, 5.0), (368170, 3.0), (465041, 4.0), (336958, 4.0), (383698, 4.0), (335220, 5.0), (307784, 5.0), (311408, 4.0), (405588, 5.0), (411121, 5.0), (344032, 3.0), (348209, 4.0), (339495, 4.0), (305256, 5.0), (367746, 4.0), (398405, 4.0), (458511, 5.0), (397734, 3.7), (379569, 5.0), (413920, 5.0), (422054, 4.0), (402309, 5.0), (419864, 4.0), (362295, 2.7), (425780, 5.0), (446366, 4.0), (366600, 3.7), (444353, 4.0), (453474, 5.0), (337052, 5.0), (443543, 5.0), (457975, 5.0), (494905, 4.0), (309456, 4.0), (491174, 5.0), (373155, 4.0), (337556, 5.0), (380896, 5.0), (426830, 5.0), (483466, 2.7), (388723, 5.0), (456826, 3.3), (480107, 3.7), (410189, 3.7), (309155, 5.0), (339826, 5.0), (434106, 3.7), (368414, 5.0), (356897, 5.0), (361634, 5.0), (393363, 3.3), (345834, 5.0), (332051, 3.7), (361632, 5.0), (486243, 3.7), (481816, 2.3), (384294, 3.7), (407487, 5.0), (345805, 4.0), (343749, 3.7), (328722, 3.7), (305198, 5.0), (488844, 5.0), (453017, 4.0), (314370, 4.0), (469278, 3.3), (359838, 4.0), (451375, 4.0), (394872, 5.0), (402984, 5.0), (408749, 5.0), (350018, 5.0), (348331, 4.0), (461845, 3.0), (303197, 5.0), (376972, 5.0), (327435, 5.0), (387563, 5.0), (455085, 5.0), (493483, 3.0), (352553, 3.7), (327980, 5.0), (379387, 3.7), (321417, 3.7), (368042, 5.0), (414610, 5.0), (472569, 4.0), (425130, 5.0), (309834, 5.0), (387243, 4.0), (438358, 5.0), (445890, 5.0), (337751, 3.7), (310674, 3.7), (472254, 5.0), (376933, 5.0), (438840, 4.0), (496080, 4.0), (326003, 3.3), (331989, 4.0), (450976, 4.0), (341560, 5.0), (432357, 3.7), (347856, 5.0), (363736, 4.0), (368174, 3.3), (413542, 5.0), (464721, 4.0), (431332, 3.7), (314091, 3.7), (381944, 4.0), (388246, 3.7), (422073, 4.0), (428286, 3.7), (370496, 5.0), (451708, 3.7), (395498, 3.7), (425635, 3.0), (402824, 4.0), (413143, 3.7), (354628, 4.0), (380160, 5.0), (443969, 4.0), (428999, 5.0), (462171, 4.0), (438072, 5.0))

    "Reading resource files" should " give us a list of registrations" in {
        val registrationsFromFile = ExamRegistrations.readRegistrationsFromFile("registrations.csv")

        assertEquals(1000, registrationsFromFile.size)
        assertEquals(registrations, registrationsFromFile)
    }

    it should " give us a list of results" in {
        val resultsFromFile = ExamRegistrations.readResultsFromFile("results.csv")

        assertEquals(1000, resultsFromFile.size)
        assertEquals(results, resultsFromFile)
    }

    "Your implementation" should " find the best student" in {
        val expected = "Gideon"
        assertEquals(expected, ExamRegistrations.findBestStudent(registrations, results))
    }

    it should " find the adequate students" in {
        val expected = List(466208, 352928, 401259, 498418, 339104, 376777, 371078, 432341, 346284, 338813, 384408, 319516, 388456, 418039, 483986, 457185, 354338, 305909, 479711, 427990, 458573, 342489, 403500, 409638, 310787, 475241, 480559, 460288, 374730, 476199, 353861, 426664, 362295, 493483, 425635)
        assertEquals(expected, ExamRegistrations.findAdequateStudents(registrations, results))
    }

    it should " find the duplicate registrations" in {
        val expected = List(303197, 329683, 482663).toArray
        assertArrayEquals(expected, ExamRegistrations.findDuplicateRegistrations(registrations))
    }

    it should " find the average grade of students who needed two attempts" in {
        val expected = 4.336679536679534
        assertEquals(expected, ExamRegistrations.findAvgGradeOfRepeatExams(registrations, results), 0.001)
    }
}