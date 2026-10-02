package com.example.data

import com.example.data.model.CategoryCard
import com.example.data.model.GameItem
import com.example.data.model.LanguageItem
import com.example.data.model.LanguageQuestion
import com.example.data.model.SubjectItem
import com.example.data.model.SubjectQuestion

object SushiData {

    val mainCategories = listOf(
        CategoryCard(
            id = "languages",
            title = "Langues",
            subtitle = "Voyage linguistique",
            countLabel = "9 langues",
            iconEmoji = "🍣",
            color = 0xFF58CC02,
            shadowColor = 0xFF46A302
        ),
        CategoryCard(
            id = "games",
            title = "Jeux",
            subtitle = "Tactique & Réflexion",
            countLabel = "2 jeux d'esprit",
            iconEmoji = "♟️",
            color = 0xFF1CB0F6,
            shadowColor = 0xFF1482BA
        ),
        CategoryCard(
            id = "extra",
            title = "Extra",
            subtitle = "Matières scolaires",
            countLabel = "4 disciplines",
            iconEmoji = "🎓",
            color = 0xFFFF9600,
            shadowColor = 0xFFCC7800
        )
    )

    val languages = listOf(
        LanguageItem(
            id = "it",
            name = "Italien",
            nativeGreeting = "Ciao ! Come stai ?",
            flagEmoji = "🇮🇹",
            level = "A1 Débutant",
            progress = 0.40f,
            completedLessons = 4,
            totalLessons = 10,
            description = "Explorez la dolce vita, la gastronomie et le rythme chantant de l'Italie.",
            questions = listOf(
                LanguageQuestion(
                    id = "it_1",
                    prompt = "Comment dit-on « Bonjour » en italien ?",
                    targetWord = "Bonjour",
                    options = listOf("Ciao", "Grazie", "Prego", "Buonasera"),
                    correctIndex = 0,
                    explanation = "« Ciao » sert aussi bien à dire bonjour qu'au revoir de manière amicale !"
                ),
                LanguageQuestion(
                    id = "it_2",
                    prompt = "Que signifie l'expression italienne « Grazie mille » ?",
                    targetWord = "Grazie mille",
                    options = listOf("Bonne nuit", "Merci mille fois", "S'il vous plaît", "À demain"),
                    correctIndex = 1,
                    explanation = "« Grazie mille » est l'équivalent de « Merci beaucoup / mille mercis »."
                ),
                LanguageQuestion(
                    id = "it_3",
                    prompt = "Quelle est la traduction de « Eau » en italien ?",
                    targetWord = "Eau",
                    options = listOf("Vino", "Pane", "Acqua", "Formaggio"),
                    correctIndex = 2,
                    explanation = "« Acqua » est le mot italien pour l'eau !"
                )
            )
        ),
        LanguageItem(
            id = "es",
            name = "Espagnol",
            nativeGreeting = "¡Hola! ¿Cómo estás?",
            flagEmoji = "🇪🇸",
            level = "A2 Intermédiaire",
            progress = 0.65f,
            completedLessons = 6,
            totalLessons = 10,
            description = "La 2e langue la plus parlée au monde, vibrante et chaleureuse.",
            questions = listOf(
                LanguageQuestion(
                    id = "es_1",
                    prompt = "Comment dit-on « Merci beaucoup » en espagnol ?",
                    targetWord = "Merci beaucoup",
                    options = listOf("De nada", "Muchas gracias", "Por favor", "Hola amigo"),
                    correctIndex = 1,
                    explanation = "« Muchas gracias » exprime la gratitude chaleureuse."
                ),
                LanguageQuestion(
                    id = "es_2",
                    prompt = "Que veut dire « La manzana » ?",
                    targetWord = "La manzana",
                    options = listOf("La pomme", "La maison", "Le chat", "Le pain"),
                    correctIndex = 0,
                    explanation = "« La manzana » signifie la pomme (ou aussi un pâté de maisons en Espagne !)."
                ),
                LanguageQuestion(
                    id = "es_3",
                    prompt = "Complétez : « ¿Dónde está el ...? » (Où est le restaurant ?)",
                    targetWord = "Restaurant",
                    options = listOf("aeropuerto", "restaurante", "supermercado", "parque"),
                    correctIndex = 1,
                    explanation = "« Restaurante » s'écrit avec un e final en espagnol."
                )
            )
        ),
        LanguageItem(
            id = "fr",
            name = "Français",
            nativeGreeting = "Bonjour ! Bienvenue.",
            flagEmoji = "🇫🇷",
            level = "Natif / Perfectionnement",
            progress = 0.90f,
            completedLessons = 9,
            totalLessons = 10,
            description = "Grammaire élégante, littérature et subtilités de la langue française.",
            questions = listOf(
                LanguageQuestion(
                    id = "fr_1",
                    prompt = "Quel est le pluriel du mot « bijou » ?",
                    targetWord = "Bijou",
                    options = listOf("Bijoux", "Bijous", "Bijou", "Bijoues"),
                    correctIndex = 0,
                    explanation = "Bijou fait partie des 7 mots en -ou qui prennent un « x » au pluriel."
                ),
                LanguageQuestion(
                    id = "fr_2",
                    prompt = "Trouvez le synonyme de « Éphémère » :",
                    targetWord = "Éphémère",
                    options = listOf("Éternel", "Passager", "Lumineux", "Ancien"),
                    correctIndex = 1,
                    explanation = "« Éphémère » qualifie ce qui ne dure que très peu de temps."
                )
            )
        ),
        LanguageItem(
            id = "ja",
            name = "Japonais",
            nativeGreeting = "こんにちは！ (Konnichiwa)",
            flagEmoji = "🇯🇵",
            level = "Hiragana & N5",
            progress = 0.50f,
            completedLessons = 5,
            totalLessons = 10,
            description = "Plongez dans l'archipel nippon, berceau des sushis, mangas et kanjis.",
            questions = listOf(
                LanguageQuestion(
                    id = "ja_1",
                    prompt = "Que signifie la formule de politesse « Arigatou » (ありがとう) ?",
                    targetWord = "Arigatou",
                    options = listOf("Bonjour", "Pardon", "Merci", "Au revoir"),
                    correctIndex = 2,
                    explanation = "« Arigatou » est la forme usuelle pour dire merci en japonais !"
                ),
                LanguageQuestion(
                    id = "ja_2",
                    prompt = "Comment s'appelle le riz vinaigré utilisé dans les sushis ?",
                    targetWord = "Riz sushi",
                    options = listOf("Shari (しゃり)", "Nori", "Wasabi", "Miso"),
                    correctIndex = 0,
                    explanation = "Le riz préparé et assaisonné pour les sushis s'appelle « Shari » !"
                ),
                LanguageQuestion(
                    id = "ja_3",
                    prompt = "Que signifie « Sayounara » (さようなら) ?",
                    targetWord = "Sayounara",
                    options = listOf("Bonne nuit", "À tout à l'heure", "Adieu / Au revoir", "Enchanté"),
                    correctIndex = 2,
                    explanation = "« Sayounara » exprime la séparation ou un au revoir formel."
                )
            )
        ),
        LanguageItem(
            id = "zh",
            name = "Chinois",
            nativeGreeting = "你好！ (Nǐ hǎo)",
            flagEmoji = "🇨🇳",
            level = "HSK 1 Découverte",
            progress = 0.20f,
            completedLessons = 2,
            totalLessons = 10,
            description = "Découvrez le mandarin, ses tons musicaux et ses caractères millénaires.",
            questions = listOf(
                LanguageQuestion(
                    id = "zh_1",
                    prompt = "Comment salue-t-on en mandarin avec « Nǐ hǎo » ?",
                    targetWord = "Nǐ hǎo",
                    options = listOf("Bonjour", "Merci", "Santé !", "Bon appétit"),
                    correctIndex = 0,
                    explanation = "« Nǐ » signifie toi et « hǎo » signifie bien/bon, soit « Bonjour » !"
                ),
                LanguageQuestion(
                    id = "zh_2",
                    prompt = "Que signifie « Xièxie » (谢谢) ?",
                    targetWord = "Xièxie",
                    options = listOf("De rien", "Merci", "Pardon", "Oui"),
                    correctIndex = 1,
                    explanation = "« Xièxie » est l'expression universelle de remerciement."
                )
            )
        ),
        LanguageItem(
            id = "pt",
            name = "Portugais",
            nativeGreeting = "Olá! Tudo bem?",
            flagEmoji = "🇵🇹",
            level = "A1 Débutant",
            progress = 0.30f,
            completedLessons = 3,
            totalLessons = 10,
            description = "Du Portugal aux plages du Brésil, une langue mélodieuse et chaleureuse.",
            questions = listOf(
                LanguageQuestion(
                    id = "pt_1",
                    prompt = "Comment dit-on « S'il vous plaît » en portugais ?",
                    targetWord = "S'il vous plaît",
                    options = listOf("Por favor", "Obrigado", "Com licença", "De nada"),
                    correctIndex = 0,
                    explanation = "« Por favor » est la formule de politesse la plus fréquente."
                ),
                LanguageQuestion(
                    id = "pt_2",
                    prompt = "Si vous êtes un homme, comment dites-vous « Merci » ?",
                    targetWord = "Merci (masculin)",
                    options = listOf("Obrigada", "Obrigado", "Valeu", "Prazer"),
                    correctIndex = 1,
                    explanation = "Un homme dit « Obrigado », une femme dit « Obrigada » !"
                )
            )
        ),
        LanguageItem(
            id = "ar",
            name = "Arabe",
            nativeGreeting = "مرحبا ! (Marhaban)",
            flagEmoji = "🇸🇦",
            level = "A1 Alphabet & Mots",
            progress = 0.25f,
            completedLessons = 2,
            totalLessons = 10,
            description = "Richesse poétique, calligraphie raffinée et vocabulaire millénaire.",
            questions = listOf(
                LanguageQuestion(
                    id = "ar_1",
                    prompt = "Comment souhaite-t-on la bienvenue avec « Marhaban » ?",
                    targetWord = "Marhaban",
                    options = listOf("Bienvenue / Bonjour", "Bonne nuit", "Merci", "À bientôt"),
                    correctIndex = 0,
                    explanation = "« Marhaban » est une salutation très accueillante et courante."
                ),
                LanguageQuestion(
                    id = "ar_2",
                    prompt = "Que signifie « Shukran » (شكراً) ?",
                    targetWord = "Shukran",
                    options = listOf("Pardon", "Merci", "Oui", "Non"),
                    correctIndex = 1,
                    explanation = "« Shukran » signifie Merci !"
                )
            )
        ),
        LanguageItem(
            id = "en",
            name = "Anglais",
            nativeGreeting = "Hello! How are you?",
            flagEmoji = "🇬🇧",
            level = "B1 Intermédiaire",
            progress = 0.80f,
            completedLessons = 8,
            totalLessons = 10,
            description = "La passerelle internationale pour le voyage, le travail et la culture.",
            questions = listOf(
                LanguageQuestion(
                    id = "en_1",
                    prompt = "Quelle est la traduction anglaise de « Voyage » ?",
                    targetWord = "Voyage",
                    options = listOf("Trip / Journey", "Meal", "Window", "Bridge"),
                    correctIndex = 0,
                    explanation = "« Trip » ou « Journey » correspondent au voyage en anglais."
                ),
                LanguageQuestion(
                    id = "en_2",
                    prompt = "Complétez : « Nice to ... you! »",
                    targetWord = "Meet",
                    options = listOf("meat", "meet", "mate", "made"),
                    correctIndex = 1,
                    explanation = "« Nice to meet you » signifie enchanté(e) de faire votre connaissance !"
                ),
                LanguageQuestion(
                    id = "en_3",
                    prompt = "Que signifie l'idiome « Piece of cake » ?",
                    targetWord = "Piece of cake",
                    options = listOf("Un gâteau au chocolat", "C'est très facile !", "Une part de pizza", "Un moment difficile"),
                    correctIndex = 1,
                    explanation = "« A piece of cake » est une métaphore qui veut dire « C'est un jeu d'enfant » !"
                )
            )
        ),
        LanguageItem(
            id = "de",
            name = "Allemand",
            nativeGreeting = "Guten Tag! Wie geht's?",
            flagEmoji = "🇩🇪",
            level = "A1 Débutant",
            progress = 0.35f,
            completedLessons = 3,
            totalLessons = 10,
            description = "Précision, logique et grand dynamisme économique au cœur de l'Europe.",
            questions = listOf(
                LanguageQuestion(
                    id = "de_1",
                    prompt = "Comment dit-on « Merci » en allemand ?",
                    targetWord = "Merci",
                    options = listOf("Bitte", "Danke", "Hallo", "Tschüss"),
                    correctIndex = 1,
                    explanation = "« Danke » est le mot magique pour remercier en allemand."
                ),
                LanguageQuestion(
                    id = "de_2",
                    prompt = "Que signifie « Guten Morgen » ?",
                    targetWord = "Guten Morgen",
                    options = listOf("Bonsoir", "Bonjour (matin)", "Bonne nuit", "À demain"),
                    correctIndex = 1,
                    explanation = "« Guten Morgen » est utilisé le matin jusqu'à midi."
                )
            )
        )
    )

    val games = listOf(
        GameItem(
            id = "chess",
            name = "Échecs",
            iconEmoji = "♟️",
            description = "Le roi des jeux de stratégie. Résolvez des puzzles tactiques ou analysez l'échiquier !",
            tag = "Stratégie & Puzzles",
            difficulty = "Débutant à Grand Maître",
            color = 0xFF58CC02
        ),
        GameItem(
            id = "checkers",
            name = "Dames",
            iconEmoji = "⚪⚫",
            description = "Capturez les pions adverses et couronnez vos dames sur le damier interactif 8x8.",
            tag = "Plateau Classique",
            difficulty = "Accessible & Rythmé",
            color = 0xFF1CB0F6
        )
    )

    val subjects = listOf(
        SubjectItem(
            id = "geo",
            name = "Géographie",
            iconEmoji = "🌍",
            description = "Explorez les capitales mondiales, les continents, fleuves et merveilles de la Terre.",
            badge = "Monde & Capitales",
            color = 0xFF1CB0F6,
            questions = listOf(
                SubjectQuestion(
                    question = "Quelle est la capitale du Japon ?",
                    options = listOf("Kyoto", "Tokyo", "Osaka", "Nagoya"),
                    correctIndex = 1,
                    explanation = "Tokyo est la capitale et la plus grande métropole du Japon !"
                ),
                SubjectQuestion(
                    question = "Sur quel continent se trouve le mont Kilimandjaro ?",
                    options = listOf("Asie", "Afrique", "Amérique du Sud", "Europe"),
                    correctIndex = 1,
                    explanation = "Le Kilimandjaro culmine en Tanzanie sur le continent africain (5 895 m)."
                ),
                SubjectQuestion(
                    question = "Quel est le plus long fleuve du monde ?",
                    options = listOf("L'Amazone", "Le Nil", "Le Yangtsé", "Le Mississippi"),
                    correctIndex = 0,
                    explanation = "L'Amazone est généralement reconnu comme le plus long et de loin le plus puissant fleuve."
                )
            )
        ),
        SubjectItem(
            id = "math",
            name = "Mathématiques",
            iconEmoji = "➕",
            description = "Boostez votre agilité mentale : calcul rapide, priorités d'opérations et suites logiques.",
            badge = "Calcul & Logique",
            color = 0xFF58CC02,
            questions = listOf(
                SubjectQuestion(
                    question = "Combien font : 7 x 8 ?",
                    options = listOf("54", "56", "58", "62"),
                    correctIndex = 1,
                    explanation = "7 fois 8 égale bien 56 !"
                ),
                SubjectQuestion(
                    question = "Quel est le résultat de : 15 + 5 x 2 ?",
                    options = listOf("40", "25", "30", "35"),
                    correctIndex = 1,
                    explanation = "Attention à la priorité opératoire ! La multiplication prime : 5 x 2 = 10, puis 15 + 10 = 25."
                ),
                SubjectQuestion(
                    question = "Quel est le nombre premier suivant après 19 ?",
                    options = listOf("21", "23", "25", "27"),
                    correctIndex = 1,
                    explanation = "23 n'est divisible que par 1 et par lui-même, c'est donc un nombre premier."
                )
            )
        ),
        SubjectItem(
            id = "geometry",
            name = "Géométrie",
            iconEmoji = "📐",
            description = "Angles, triangles, polygones, théorème de Pythagore et calculs d'aires.",
            badge = "Formes & Théorèmes",
            color = 0xFFFF9600,
            questions = listOf(
                SubjectQuestion(
                    question = "Quelle est la somme des angles d'un triangle ?",
                    options = listOf("90°", "180°", "270°", "360°"),
                    correctIndex = 1,
                    explanation = "Dans un espace euclidien, la somme des trois angles d'un triangle vaut toujours 180°."
                ),
                SubjectQuestion(
                    question = "Comment s'appelle un polygone à 8 côtés ?",
                    options = listOf("Hexagone", "Heptagone", "Octogone", "Décaèdre"),
                    correctIndex = 2,
                    explanation = "Un octogone possède 8 côtés et 8 sommets."
                ),
                SubjectQuestion(
                    question = "Dans un triangle rectangle, quel côté fait face à l'angle droit ?",
                    options = listOf("L'apothème", "La médiane", "L'hypoténuse", "La tangente"),
                    correctIndex = 2,
                    explanation = "L'hypoténuse est le côté le plus long opposé à l'angle droit."
                )
            )
        ),
        SubjectItem(
            id = "science",
            name = "Sciences",
            iconEmoji = "🔬",
            description = "Biologie, physique, chimie et astronomie pour aiguiser votre curiosité.",
            badge = "Découverte & Nature",
            color = 0xFFCE82FF,
            questions = listOf(
                SubjectQuestion(
                    question = "Quelle est la formule chimique de l'eau ?",
                    options = listOf("CO2", "H2O", "NaCl", "O2"),
                    correctIndex = 1,
                    explanation = "Deux atomes d'hydrogène et un atome d'oxygène forment la molécule d'eau (H2O)."
                ),
                SubjectQuestion(
                    question = "Quelle planète est la plus proche du Soleil ?",
                    options = listOf("Vénus", "Mars", "Mercure", "Jupiter"),
                    correctIndex = 2,
                    explanation = "Mercure est la première planète et la plus proche du Soleil."
                ),
                SubjectQuestion(
                    question = "Quel organe pompe le sang dans le corps humain ?",
                    options = listOf("Le foie", "Le cœur", "Les poumons", "Les reins"),
                    correctIndex = 1,
                    explanation = "Le cœur est le muscle vital agissant comme pompe centrale de circulation."
                )
            )
        )
    )
}
