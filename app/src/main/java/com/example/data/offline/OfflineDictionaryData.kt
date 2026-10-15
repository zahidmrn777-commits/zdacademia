package com.example.data.offline

import com.example.data.model.PhraseCategory
import com.example.data.model.PhraseItem

object OfflineDictionaryData {

    // Comprehensive offline phrasebook items across all 12 languages
    val PHRASEBOOK_ITEMS: List<PhraseItem> = listOf(
        PhraseItem(
            id = "g_hello",
            englishText = "Hello",
            category = PhraseCategory.GREETINGS,
            translations = mapOf(
                "en" to "Hello",
                "es" to "Hola",
                "fr" to "Bonjour",
                "de" to "Hallo",
                "it" to "Ciao",
                "pt" to "Olá",
                "ja" to "こんにちは",
                "zh" to "你好",
                "ko" to "안녕하세요",
                "ru" to "Здравствуйте",
                "ar" to "مرحبا",
                "hi" to "नमस्ते"
            ),
            phonetics = mapOf(
                "ja" to "Konnichiwa",
                "zh" to "Nǐ hǎo",
                "ko" to "Annyeonghaseyo",
                "ru" to "Zdravstvuyte",
                "ar" to "Marhaban",
                "hi" to "Namaste"
            )
        ),
        PhraseItem(
            id = "g_good_morning",
            englishText = "Good morning",
            category = PhraseCategory.GREETINGS,
            translations = mapOf(
                "en" to "Good morning",
                "es" to "Buenos días",
                "fr" to "Bonjour",
                "de" to "Guten Morgen",
                "it" to "Buongiorno",
                "pt" to "Bom dia",
                "ja" to "おはようございます",
                "zh" to "早上好",
                "ko" to "좋은 아침입니다",
                "ru" to "Доброе утро",
                "ar" to "صباح الخير",
                "hi" to "सुप्रभात"
            ),
            phonetics = mapOf(
                "ja" to "Ohayō gozaimasu",
                "zh" to "Zǎoshang hǎo",
                "ko" to "Joeun achim-imnida",
                "ru" to "Dobroye utro",
                "ar" to "Sabah al-khair",
                "hi" to "Suprabhat"
            )
        ),
        PhraseItem(
            id = "g_good_evening",
            englishText = "Good evening",
            category = PhraseCategory.GREETINGS,
            translations = mapOf(
                "en" to "Good evening",
                "es" to "Buenas tardes",
                "fr" to "Bonsoir",
                "de" to "Guten Abend",
                "it" to "Buonasera",
                "pt" to "Boa noite",
                "ja" to "こんばんは",
                "zh" to "晚上好",
                "ko" to "좋은 저녁입니다",
                "ru" to "Добрый вечер",
                "ar" to "مساء الخير",
                "hi" to "शुभ संध्या"
            ),
            phonetics = mapOf(
                "ja" to "Konbanwa",
                "zh" to "Wǎnshang hǎo",
                "ko" to "Joeun jeonyeok-imnida",
                "ru" to "Dobryy vecher",
                "ar" to "Masa'a al-khair",
                "hi" to "Shubh sandhya"
            )
        ),
        PhraseItem(
            id = "g_goodbye",
            englishText = "Goodbye",
            category = PhraseCategory.GREETINGS,
            translations = mapOf(
                "en" to "Goodbye",
                "es" to "Adiós",
                "fr" to "Au revoir",
                "de" to "Auf Wiedersehen",
                "it" to "Arrivederci",
                "pt" to "Adeus",
                "ja" to "さようなら",
                "zh" to "再见",
                "ko" to "안녕히 계세요",
                "ru" to "До свидания",
                "ar" to "مع السلامة",
                "hi" to "अलविदा"
            ),
            phonetics = mapOf(
                "ja" to "Sayōnara",
                "zh" to "Zàijiàn",
                "ko" to "Annyeonghi gyeseyo",
                "ru" to "Do svidaniya",
                "ar" to "Ma'a as-salama",
                "hi" to "Alvida"
            )
        ),
        PhraseItem(
            id = "e_thank_you",
            englishText = "Thank you very much",
            category = PhraseCategory.ESSENTIALS,
            translations = mapOf(
                "en" to "Thank you very much",
                "es" to "Muchas gracias",
                "fr" to "Merci beaucoup",
                "de" to "Vielen Dank",
                "it" to "Grazie mille",
                "pt" to "Muito obrigado",
                "ja" to "どうもありがとうございます",
                "zh" to "非常感谢",
                "ko" to "정말 감사합니다",
                "ru" to "Большое спасибо",
                "ar" to "شكرا جزيلا",
                "hi" to "बहुत बहुत धन्यवाद"
            ),
            phonetics = mapOf(
                "ja" to "Dōmo arigatō gozaimasu",
                "zh" to "Fēicháng gǎnxiè",
                "ko" to "Jeongmal gamsahamnida",
                "ru" to "Bol'shoye spasibo",
                "ar" to "Shukran jazeelan",
                "hi" to "Bahut bahut dhanyavaad"
            )
        ),
        PhraseItem(
            id = "e_please",
            englishText = "Please",
            category = PhraseCategory.ESSENTIALS,
            translations = mapOf(
                "en" to "Please",
                "es" to "Por favor",
                "fr" to "S'il vous plaît",
                "de" to "Bitte",
                "it" to "Per favore",
                "pt" to "Por favor",
                "ja" to "お願いします",
                "zh" to "请",
                "ko" to "부탁합니다",
                "ru" to "Пожалуйста",
                "ar" to "من فضلك",
                "hi" to "कृपया"
            ),
            phonetics = mapOf(
                "ja" to "Onegaishimasu",
                "zh" to "Qǐng",
                "ko" to "Butakhamnida",
                "ru" to "Pozhaluysta",
                "ar" to "Min fadlik",
                "hi" to "Kripya"
            )
        ),
        PhraseItem(
            id = "e_excuse_me",
            englishText = "Excuse me",
            category = PhraseCategory.ESSENTIALS,
            translations = mapOf(
                "en" to "Excuse me",
                "es" to "Disculpe",
                "fr" to "Excusez-moi",
                "de" to "Entschuldigung",
                "it" to "Scusi",
                "pt" to "Com licença",
                "ja" to "すみません",
                "zh" to "打扰一下",
                "ko" to "실례합니다",
                "ru" to "Извините",
                "ar" to "عفوا",
                "hi" to "माफ कीजिये"
            ),
            phonetics = mapOf(
                "ja" to "Sumimasen",
                "zh" to "Dǎrǎo yīxià",
                "ko" to "Sillyehamnida",
                "ru" to "Izvinite",
                "ar" to "Afwan",
                "hi" to "Maaf kijiye"
            )
        ),
        PhraseItem(
            id = "e_how_are_you",
            englishText = "How are you?",
            category = PhraseCategory.CONVERSATION,
            translations = mapOf(
                "en" to "How are you?",
                "es" to "¿Cómo estás?",
                "fr" to "Comment allez-vous ?",
                "de" to "Wie geht es Ihnen?",
                "it" to "Come sta?",
                "pt" to "Como você está?",
                "ja" to "お元気ですか？",
                "zh" to "你好吗？",
                "ko" to "어떻게 지내세요?",
                "ru" to "Как ваши дела?",
                "ar" to "كيف حالك؟",
                "hi" to "आप कैसे हैं?"
            ),
            phonetics = mapOf(
                "ja" to "Ogenki desu ka?",
                "zh" to "Nǐ hǎo ma?",
                "ko" to "Eotteoke jinaeseyo?",
                "ru" to "Kak vashi dela?",
                "ar" to "Kayfa haluk?",
                "hi" to "Aap kaise hain?"
            )
        ),
        PhraseItem(
            id = "e_nice_to_meet_you",
            englishText = "Nice to meet you",
            category = PhraseCategory.CONVERSATION,
            translations = mapOf(
                "en" to "Nice to meet you",
                "es" to "Mucho gusto",
                "fr" to "Enchanté de vous rencontrer",
                "de" to "Freut mich, Sie kennenzulernen",
                "it" to "Piacere di conoscerti",
                "pt" to "Prazer em conhecê-lo",
                "ja" to "はじめまして",
                "zh" to "很高兴认识你",
                "ko" to "만나서 반갑습니다",
                "ru" to "Приятно познакомиться",
                "ar" to "تشرفت بمعرفتك",
                "hi" to "आपसे मिलकर खुशी हुई"
            ),
            phonetics = mapOf(
                "ja" to "Hajimemashite",
                "zh" to "Hěn gāoxìng rènshí nǐ",
                "ko" to "Mannaseo bangapseumnida",
                "ru" to "Priyatno poznakomit'sya",
                "ar" to "Tasharraftu bima'rifatik",
                "hi" to "Aapse milkar khushi hui"
            )
        ),
        PhraseItem(
            id = "e_speak_english",
            englishText = "Do you speak English?",
            category = PhraseCategory.CONVERSATION,
            translations = mapOf(
                "en" to "Do you speak English?",
                "es" to "¿Habla inglés?",
                "fr" to "Parlez-vous anglais ?",
                "de" to "Sprechen Sie Englisch?",
                "it" to "Parla inglese?",
                "pt" to "Você fala inglês?",
                "ja" to "英語を話せますか？",
                "zh" to "你会说英语吗？",
                "ko" to "영어 할 줄 아세요?",
                "ru" to "Вы говорите по-английски?",
                "ar" to "هل تتحدث الإنجليزية؟",
                "hi" to "क्या आप अंग्रेजी बोलते हैं?"
            ),
            phonetics = mapOf(
                "ja" to "Eigo o hanasemasu ka?",
                "zh" to "Nǐ huì shuō yīngyǔ ma?",
                "ko" to "Yeong-eo hal jul aseyo?",
                "ru" to "Vy govorite po-angliyski?",
                "ar" to "Hal tatakallam al-ingliziyya?",
                "hi" to "Kya aap angreji bolte hain?"
            )
        ),
        PhraseItem(
            id = "e_understand_not",
            englishText = "I don't understand",
            category = PhraseCategory.CONVERSATION,
            translations = mapOf(
                "en" to "I don't understand",
                "es" to "No entiendo",
                "fr" to "Je ne comprends pas",
                "de" to "Ich verstehe nicht",
                "it" to "Non capisco",
                "pt" to "Eu não entendo",
                "ja" to "分かりません",
                "zh" to "我不明白",
                "ko" to "이해하지 못했습니다",
                "ru" to "Я не понимаю",
                "ar" to "أنا لا أفهم",
                "hi" to "मुझे समझ नहीं आया"
            ),
            phonetics = mapOf(
                "ja" to "Wakarimasen",
                "zh" to "Wǒ bù míngbai",
                "ko" to "Ihae haji mothaesseumnida",
                "ru" to "Ya ne ponimayu",
                "ar" to "Ana la afham",
                "hi" to "Mujhe samajh nahi aaya"
            )
        ),
        PhraseItem(
            id = "t_where_is_bathroom",
            englishText = "Where is the bathroom?",
            category = PhraseCategory.TRAVEL,
            translations = mapOf(
                "en" to "Where is the bathroom?",
                "es" to "¿Dónde está el baño?",
                "fr" to "Où sont les toilettes ?",
                "de" to "Wo ist die Toilette?",
                "it" to "Dov'è il bagno?",
                "pt" to "Onde fica o banheiro?",
                "ja" to "トイレはどこですか？",
                "zh" to "洗手间在哪里？",
                "ko" to "화장실이 어디에 있나요?",
                "ru" to "Где находится туалет?",
                "ar" to "أين الحمام؟",
                "hi" to "शौचालय कहाँ है?"
            ),
            phonetics = mapOf(
                "ja" to "Toire wa doko desu ka?",
                "zh" to "Xǐshǒujiān zài nǎlǐ?",
                "ko" to "Hwajangsil-i eodi-e issnayo?",
                "ru" to "Gde nakhoditsya tualet?",
                "ar" to "Ayna al-hammam?",
                "hi" to "Shouchalay kahan hai?"
            )
        ),
        PhraseItem(
            id = "t_airport",
            englishText = "Where is the airport?",
            category = PhraseCategory.TRAVEL,
            translations = mapOf(
                "en" to "Where is the airport?",
                "es" to "¿Dónde está el aeropuerto?",
                "fr" to "Où est l'aéroport ?",
                "de" to "Wo ist der Flughafen?",
                "it" to "Dov'è l'aeroporto?",
                "pt" to "Onde fica o aeroporto?",
                "ja" to "空港はどこですか？",
                "zh" to "机场在哪里？",
                "ko" to "공항이 어디에 있습니까?",
                "ru" to "Где аэропорт?",
                "ar" to "أين المطار؟",
                "hi" to "हवाई अड्डा कहाँ है?"
            ),
            phonetics = mapOf(
                "ja" to "Kūkō wa doko desu ka?",
                "zh" to "Jīchǎng zài nǎlǐ?",
                "ko" to "Gonghang-i eodie isseumnikka?",
                "ru" to "Gde aeroport?",
                "ar" to "Ayna al-matar?",
                "hi" to "Hawai adda kahan hai?"
            )
        ),
        PhraseItem(
            id = "t_train_station",
            englishText = "Where is the train station?",
            category = PhraseCategory.TRAVEL,
            translations = mapOf(
                "en" to "Where is the train station?",
                "es" to "¿Dónde está la estación de tren?",
                "fr" to "Où est la gare ferroviaire ?",
                "de" to "Wo ist der Bahnhof?",
                "it" to "Dov'è la stazione ferroviaria?",
                "pt" to "Onde fica a estação de trem?",
                "ja" to "駅はどこですか？",
                "zh" to "火车站建在哪里？",
                "ko" to "기차역은 어디에 있습니까?",
                "ru" to "Где железнодорожный вокзал?",
                "ar" to "أين محطة القطار؟",
                "hi" to "रेलवे स्टेशन कहाँ है?"
            ),
            phonetics = mapOf(
                "ja" to "Eki wa doko desu ka?",
                "zh" to "Huǒchē zhàn zài nǎlǐ?",
                "ko" to "Gichayeok-eun eodie isseumnikka?",
                "ru" to "Gde zheleznodorozhnyy vokzal?",
                "ar" to "Ayna mahattat al-qitar?",
                "hi" to "Railway station kahan hai?"
            )
        ),
        PhraseItem(
            id = "t_hotel",
            englishText = "I have a hotel reservation",
            category = PhraseCategory.TRAVEL,
            translations = mapOf(
                "en" to "I have a hotel reservation",
                "es" to "Tengo una reserva de hotel",
                "fr" to "J'ai une réservation d'hôtel",
                "de" to "Ich habe eine Hotelreservierung",
                "it" to "Ho una prenotazione in albergo",
                "pt" to "Tenho uma reserva de hotel",
                "ja" to "ホテルの予約があります",
                "zh" to "我预订了酒店",
                "ko" to "호텔 예약이 되어 있습니다",
                "ru" to "У меня забронирован номер в отеле",
                "ar" to "لدي حجز فندقي",
                "hi" to "मेरे पास होटल का आरक्षण है"
            ),
            phonetics = mapOf(
                "ja" to "Hoteru no yoyaku ga arimasu",
                "zh" to "Wǒ yùdìng le jiǔdiàn",
                "ko" to "Hotel yeyak-i doeeo isseumnida",
                "ru" to "U menya zabronirovan nomer v otele",
                "ar" to "Ladai hajz funduqi",
                "hi" to "Mere paas hotel ka reservation hai"
            )
        ),
        PhraseItem(
            id = "t_taxi",
            englishText = "Please take me to this address",
            category = PhraseCategory.TRAVEL,
            translations = mapOf(
                "en" to "Please take me to this address",
                "es" to "Por favor lléveme a esta dirección",
                "fr" to "Veuillez m'emmener à cette adresse",
                "de" to "Bitte bringen Sie mich zu dieser Adresse",
                "it" to "Per favore mi porti a questo indirizzo",
                "pt" to "Por favor, leve-me a este endereço",
                "ja" to "この住所までお願いします",
                "zh" to "请带我去这个地址",
                "ko" to "이 주소로 가주세요",
                "ru" to "Пожалуйста, отвезите меня по этому адресу",
                "ar" to "يرجى أخذي إلى هذا العنوان",
                "hi" to "कृपया मुझे इस पते पर ले चलें"
            ),
            phonetics = mapOf(
                "ja" to "Kono jūsho made onegaishimasu",
                "zh" to "Qǐng dài wǒ qù zhège dìzhǐ",
                "ko" to "I jusoro gajuseyo",
                "ru" to "Pozhaluysta, otvezite menya po etomu adresu",
                "ar" to "Yurja akhdi ila hatha al-'unwan",
                "hi" to "Kripya mujhe is pate par le chalein"
            )
        ),
        PhraseItem(
            id = "d_water",
            englishText = "Can I have some water, please?",
            category = PhraseCategory.DINING,
            translations = mapOf(
                "en" to "Can I have some water, please?",
                "es" to "¿Me puede dar un poco de agua, por favor?",
                "fr" to "Puis-je avoir de l'eau, s'il vous plaît ?",
                "de" to "Kann ich bitte etwas Wasser haben?",
                "it" to "Posso avere dell'acqua, per favore?",
                "pt" to "Pode me dar um pouco de água, por favor?",
                "ja" to "お水をいただけますか？",
                "zh" to "请给我一杯水好吗？",
                "ko" to "물 좀 주시겠어요?",
                "ru" to "Можно мне воды, пожалуйста?",
                "ar" to "هل يمكنني الحصول على بعض الماء من فضلك؟",
                "hi" to "क्या मुझे थोड़ा पानी मिल सकता है, कृपया?"
            ),
            phonetics = mapOf(
                "ja" to "Omizu o itadakemasu ka?",
                "zh" to "Qǐng gěi wǒ yībēi shuǐ hǎo ma?",
                "ko" to "Mul jom jusigesseoyo?",
                "ru" to "Mozhno mne vody, pozhaluysta?",
                "ar" to "Hal yumkinuni al-husul ala ba'd al-ma?",
                "hi" to "Kya mujhe thoda paani mil sakta hai, kripya?"
            )
        ),
        PhraseItem(
            id = "d_menu",
            englishText = "Could we see the menu, please?",
            category = PhraseCategory.DINING,
            translations = mapOf(
                "en" to "Could we see the menu, please?",
                "es" to "¿Podríamos ver el menú, por favor?",
                "fr" to "Pourrions-nous voir le menu, s'il vous plaît ?",
                "de" to "Könnten wir bitte die Speisekarte sehen?",
                "it" to "Potremmo vedere il menu, per favore?",
                "pt" to "Poderíamos ver o cardápio, por favor?",
                "ja" to "メニューを見せていただけますか？",
                "zh" to "我们可以看一下菜单吗？",
                "ko" to "메뉴판을 볼 수 있을까요?",
                "ru" to "Можно нам меню, пожалуйста?",
                "ar" to "هل يمكننا رؤية قائمة الطعام من فضلك؟",
                "hi" to "क्या हम मेनू देख सकते हैं, कृपया?"
            ),
            phonetics = mapOf(
                "ja" to "Menyū o misete itadakemasu ka?",
                "zh" to "Wǒmen kěyǐ kàn yīxià càidān ma?",
                "ko" to "Menyupan-eul bol su isseulkkayo?",
                "ru" to "Mozhno nam menyu, pozhaluysta?",
                "ar" to "Hal yumkinuna ru'yat qa'imat at-ta'am?",
                "hi" to "Kya hum menu dekh sakte hain, kripya?"
            )
        ),
        PhraseItem(
            id = "d_bill",
            englishText = "The bill, please",
            category = PhraseCategory.DINING,
            translations = mapOf(
                "en" to "The bill, please",
                "es" to "La cuenta, por favor",
                "fr" to "L'addition, s'il vous plaît",
                "de" to "Die Rechnung, bitte",
                "it" to "Il conto, per favore",
                "pt" to "A conta, por favor",
                "ja" to "お会計をお願いします",
                "zh" to "请结账",
                "ko" to "계산서 주세요",
                "ru" to "Счет, пожалуйста",
                "ar" to "الحساب من فضلك",
                "hi" to "बिल ले आइये, कृपया"
            ),
            phonetics = mapOf(
                "ja" to "Okaikei o onegaishimasu",
                "zh" to "Qǐng jiézhàng",
                "ko" to "Gyesanseo juseyo",
                "ru" to "Schot, pozhaluysta",
                "ar" to "Al-hisab min fadlik",
                "hi" to "Bill le aaiye, kripya"
            )
        ),
        PhraseItem(
            id = "d_delicious",
            englishText = "The food was delicious",
            category = PhraseCategory.DINING,
            translations = mapOf(
                "en" to "The food was delicious",
                "es" to "La comida estaba deliciosa",
                "fr" to "La nourriture était délicieuse",
                "de" to "Das Essen war köstlich",
                "it" to "Il cibo era delizioso",
                "pt" to "A comida estava deliciosa",
                "ja" to "とても美味しかったです",
                "zh" to "食物非常美味",
                "ko" to "음식이 정말 맛있었습니다",
                "ru" to "Еда была очень вкусной",
                "ar" to "كان الطعام لذيذا جدا",
                "hi" to "खाना बहुत स्वादिष्ट था"
            ),
            phonetics = mapOf(
                "ja" to "Totemo oishikatta desu",
                "zh" to "Shíwù fēicháng měiwèi",
                "ko" to "Eumsig-i jeongmal mas-isseosseumnida",
                "ru" to "Yeda byla ochen' vkusnoy",
                "ar" to "Kana at-ta'am latheedhan jiddan",
                "hi" to "Khaana bahut swadisht tha"
            )
        ),
        PhraseItem(
            id = "m_help",
            englishText = "Help me, please!",
            category = PhraseCategory.EMERGENCY,
            translations = mapOf(
                "en" to "Help me, please!",
                "es" to "¡Ayúdeme, por favor!",
                "fr" to "Aidez-moi, s'il vous plaît !",
                "de" to "Helfen Sie mir bitte!",
                "it" to "Aiutatemi, per favore!",
                "pt" to "Ajude-me, por favor!",
                "ja" to "助けてください！",
                "zh" to "请帮帮我！",
                "ko" to "도와주세요!",
                "ru" to "Помогите мне, пожалуйста!",
                "ar" to "ساعدني من فضلك!",
                "hi" to "कृपया मेरी मदद करें!"
            ),
            phonetics = mapOf(
                "ja" to "Tasukete kudasai!",
                "zh" to "Qǐng bāng bāng wǒ!",
                "ko" to "Dowajuseyo!",
                "ru" to "Pomogite mne, pozhaluysta!",
                "ar" to "Sa'idni min fadlik!",
                "hi" to "Kripya meri madad karein!"
            )
        ),
        PhraseItem(
            id = "m_hospital",
            englishText = "I need a doctor or hospital",
            category = PhraseCategory.EMERGENCY,
            translations = mapOf(
                "en" to "I need a doctor or hospital",
                "es" to "Necesito un médico o un hospital",
                "fr" to "J'ai besoin d'un médecin ou d'un hôpital",
                "de" to "Ich brauche einen Arzt oder ein Krankenhaus",
                "it" to "Ho bisogno di un medico o di un ospedale",
                "pt" to "Preciso de um médico ou hospital",
                "ja" to "医者か病院が必要です",
                "zh" to "我需要看医生或去医院",
                "ko" to "의사나 병원이 필요합니다",
                "ru" to "Мне нужен врач или больница",
                "ar" to "أحتاج إلى طبيب أو مستشفى",
                "hi" to "मुझे डॉक्टर या अस्पताल की जरूरत है"
            ),
            phonetics = mapOf(
                "ja" to "Isha ka byōin ga hitsuyō desu",
                "zh" to "Wǒ xūyào kàn yīshēng huò qù yīyuàn",
                "ko" to "Uisana byeong-won-i piryohamnida",
                "ru" to "Mne nuzhen vrach ili bol'nitsa",
                "ar" to "Ahtaju ila tabeeb aw mustashfa",
                "hi" to "Mujhe doctor ya aspatal ki zaroorat hai"
            )
        ),
        PhraseItem(
            id = "m_police",
            englishText = "Call the police immediately",
            category = PhraseCategory.EMERGENCY,
            translations = mapOf(
                "en" to "Call the police immediately",
                "es" to "Llame a la policía de inmediato",
                "fr" to "Appelez la police immédiatement",
                "de" to "Rufen Sie sofort die Polizei",
                "it" to "Chiamate subito la polizia",
                "pt" to "Chame a polícia imediatamente",
                "ja" to "すぐに警察を呼んでください",
                "zh" to "请立刻报警",
                "ko" to "즉시 경찰을 불러주세요",
                "ru" to "Немедленно вызовите полицию",
                "ar" to "اتصل بالشرطة فوراً",
                "hi" to "तुरंत पुलिस को बुलाओ"
            ),
            phonetics = mapOf(
                "ja" to "Sugu ni keisatsu o yonde kudasai",
                "zh" to "Qǐng lìkè bàojǐng",
                "ko" to "Jeuksi gyeongchal-eul bulleojuseyo",
                "ru" to "Nemedlenno vyzovite politsiyu",
                "ar" to "Ittasil bish-shurta fawran",
                "hi" to "Turant police ko bulao"
            )
        ),
        PhraseItem(
            id = "s_how_much",
            englishText = "How much does this cost?",
            category = PhraseCategory.SHOPPING,
            translations = mapOf(
                "en" to "How much does this cost?",
                "es" to "¿Cuánto cuesta esto?",
                "fr" to "Combien ça coûte ?",
                "de" to "Wie viel kostet das?",
                "it" to "Quanto costa questo?",
                "pt" to "Quanto custa isto?",
                "ja" to "これはいくらですか？",
                "zh" to "这个多少钱？",
                "ko" to "이것은 얼마입니까?",
                "ru" to "Сколько это стоит?",
                "ar" to "كم يكلف هذا؟",
                "hi" to "इसकी कीमत क्या है?"
            ),
            phonetics = mapOf(
                "ja" to "Kore wa ikura desu ka?",
                "zh" to "Zhège duōshao qián?",
                "ko" to "Igeos-eun eolma-imnikka?",
                "ru" to "Skol'ko eto stoit?",
                "ar" to "Kam yukallifu hatha?",
                "hi" to "Iski keemat kya hai?"
            )
        ),
        PhraseItem(
            id = "s_credit_card",
            englishText = "Do you accept credit cards?",
            category = PhraseCategory.SHOPPING,
            translations = mapOf(
                "en" to "Do you accept credit cards?",
                "es" to "¿Aceptan tarjetas de crédito?",
                "fr" to "Acceptez-vous les cartes de crédit ?",
                "de" to "Akzeptieren Sie Kreditkarten?",
                "it" to "Accettate carte di credito?",
                "pt" to "Você aceita cartão de crédito?",
                "ja" to "クレジットカードは使えますか？",
                "zh" to "你们接受信用卡吗？",
                "ko" to "신용카드 받나요?",
                "ru" to "Вы принимаете кредитные карты?",
                "ar" to "هل تقبلون بطاقات الائتمان؟",
                "hi" to "क्या आप क्रेडिट कार्ड स्वीकार करते हैं?"
            ),
            phonetics = mapOf(
                "ja" to "Kurejitto kādo wa tsukaemasu ka?",
                "zh" to "Nǐmen jiēshòu xìnyòngkǎ ma?",
                "ko" to "Sinyongkadeu bannayo?",
                "ru" to "Vy prinimayete kreditnyye karty?",
                "ar" to "Hal taqbaluna bitaqat al-i'timan?",
                "hi" to "Kya aap credit card sweekar karte hain?"
            )
        ),
        PhraseItem(
            id = "n_one_two_three",
            englishText = "One, Two, Three, Four, Five",
            category = PhraseCategory.NUMBERS,
            translations = mapOf(
                "en" to "One, Two, Three, Four, Five",
                "es" to "Uno, Dos, Tres, Cuatro, Cinco",
                "fr" to "Un, Deux, Trois, Quatre, Cinq",
                "de" to "Eins, Zwei, Drei, Vier, Fünf",
                "it" to "Uno, Due, Tre, Quattro, Cinque",
                "pt" to "Um, Dois, Três, Quatro, Cinco",
                "ja" to "一、二、三、四、五",
                "zh" to "一、二、三、四、五",
                "ko" to "하나, 둘, 셋, 넷, 다섯",
                "ru" to "Один, Два, Три, Четыре, Пять",
                "ar" to "واحد، اثنان، ثلاثة، أربعة، خمسة",
                "hi" to "एक, दो, तीन, चार, पांच"
            ),
            phonetics = mapOf(
                "ja" to "Ichi, Ni, San, Shi, Go",
                "zh" to "Yī, Èr, Sān, Sì, Wǔ",
                "ko" to "Hana, Dul, Set, Net, Daseot",
                "ru" to "Odin, Dva, Tri, Chetyre, Pyat'",
                "ar" to "Wahid, Ithnan, Thalatha, Arba'a, Khamsa",
                "hi" to "Ek, Do, Teen, Chaar, Paanch"
            )
        )
    )

    // Extensive Dictionary of English words & common tokens -> translations in all 12 languages
    // Allows robust offline tokenized sentence translation for any freeform input
    val DICTIONARY_MAP: Map<String, Map<String, String>> = mapOf(
        "yes" to mapOf(
            "en" to "yes", "es" to "sí", "fr" to "oui", "de" to "ja", "it" to "sì",
            "pt" to "sim", "ja" to "はい", "zh" to "是", "ko" to "네", "ru" to "да", "ar" to "نعم", "hi" to "हाँ"
        ),
        "no" to mapOf(
            "en" to "no", "es" to "no", "fr" to "non", "de" to "nein", "it" to "no",
            "pt" to "não", "ja" to "いいえ", "zh" to "不", "ko" to "아니요", "ru" to "нет", "ar" to "لا", "hi" to "नहीं"
        ),
        "ok" to mapOf(
            "en" to "okay", "es" to "está bien", "fr" to "d'accord", "de" to "in Ordnung", "it" to "va bene",
            "pt" to "está bem", "ja" to "了解", "zh" to "好的", "ko" to "알겠습니다", "ru" to "хорошо", "ar" to "حسنا", "hi" to "ठीक है"
        ),
        "okay" to mapOf(
            "en" to "okay", "es" to "está bien", "fr" to "d'accord", "de" to "in Ordnung", "it" to "va bene",
            "pt" to "está bem", "ja" to "了解", "zh" to "好的", "ko" to "알겠습니다", "ru" to "хорошо", "ar" to "حسنا", "hi" to "ठीक है"
        ),
        "i" to mapOf(
            "en" to "I", "es" to "yo", "fr" to "je", "de" to "ich", "it" to "io",
            "pt" to "eu", "ja" to "私", "zh" to "我", "ko" to "나", "ru" to "я", "ar" to "أنا", "hi" to "मैं"
        ),
        "you" to mapOf(
            "en" to "you", "es" to "tú", "fr" to "vous", "de" to "du", "it" to "tu",
            "pt" to "você", "ja" to "あなた", "zh" to "你", "ko" to "당신", "ru" to "вы", "ar" to "أنت", "hi" to "आप"
        ),
        "we" to mapOf(
            "en" to "we", "es" to "nosotros", "fr" to "nous", "de" to "wir", "it" to "noi",
            "pt" to "nós", "ja" to "私たち", "zh" to "我们", "ko" to "우리", "ru" to "мы", "ar" to "نحن", "hi" to "हम"
        ),
        "he" to mapOf(
            "en" to "he", "es" to "él", "fr" to "il", "de" to "er", "it" to "lui",
            "pt" to "ele", "ja" to "彼", "zh" to "他", "ko" to "그", "ru" to "он", "ar" to "هو", "hi" to "वह"
        ),
        "she" to mapOf(
            "en" to "she", "es" to "ella", "fr" to "elle", "de" to "sie", "it" to "lei",
            "pt" to "ela", "ja" to "彼女", "zh" to "她", "ko" to "그녀", "ru" to "она", "ar" to "هي", "hi" to "वह"
        ),
        "they" to mapOf(
            "en" to "they", "es" to "ellos", "fr" to "ils", "de" to "sie", "it" to "loro",
            "pt" to "eles", "ja" to "彼ら", "zh" to "他们", "ko" to "그들", "ru" to "они", "ar" to "هم", "hi" to "वे"
        ),
        "what" to mapOf(
            "en" to "what", "es" to "qué", "fr" to "quoi", "de" to "was", "it" to "cosa",
            "pt" to "o que", "ja" to "何", "zh" to "什么", "ko" to "무엇", "ru" to "что", "ar" to "ماذا", "hi" to "क्या"
        ),
        "where" to mapOf(
            "en" to "where", "es" to "dónde", "fr" to "où", "de" to "wo", "it" to "dove",
            "pt" to "onde", "ja" to "どこ", "zh" to "在哪里", "ko" to "어디", "ru" to "где", "ar" to "أين", "hi" to "कहाँ"
        ),
        "when" to mapOf(
            "en" to "when", "es" to "cuándo", "fr" to "quand", "de" to "wann", "it" to "quando",
            "pt" to "quando", "ja" to "いつ", "zh" to "什么时候", "ko" to "언제", "ru" to "когда", "ar" to "متى", "hi" to "कब"
        ),
        "why" to mapOf(
            "en" to "why", "es" to "por qué", "fr" to "pourquoi", "de" to "warum", "it" to "perché",
            "pt" to "por que", "ja" to "なぜ", "zh" to "为什么", "ko" to "왜", "ru" to "почему", "ar" to "لماذا", "hi" to "क्यों"
        ),
        "how" to mapOf(
            "en" to "how", "es" to "cómo", "fr" to "comment", "de" to "wie", "it" to "come",
            "pt" to "como", "ja" to "どのように", "zh" to "怎么", "ko" to "어떻게", "ru" to "как", "ar" to "كيف", "hi" to "कैसे"
        ),
        "love" to mapOf(
            "en" to "love", "es" to "amor", "fr" to "amour", "de" to "Liebe", "it" to "amore",
            "pt" to "amor", "ja" to "愛", "zh" to "爱", "ko" to "사랑", "ru" to "любовь", "ar" to "حب", "hi" to "प्यार"
        ),
        "friend" to mapOf(
            "en" to "friend", "es" to "amigo", "fr" to "ami", "de" to "Freund", "it" to "amico",
            "pt" to "amigo", "ja" to "友達", "zh" to "朋友", "ko" to "친구", "ru" to "друг", "ar" to "صديق", "hi" to "दोस्त"
        ),
        "family" to mapOf(
            "en" to "family", "es" to "familia", "fr" to "famille", "de" to "Familie", "it" to "famiglia",
            "pt" to "família", "ja" to "家族", "zh" to "家庭", "ko" to "가족", "ru" to "семья", "ar" to "عائلة", "hi" to "परिवार"
        ),
        "time" to mapOf(
            "en" to "time", "es" to "tiempo", "fr" to "temps", "de" to "Zeit", "it" to "tempo",
            "pt" to "tempo", "ja" to "時間", "zh" to "时间", "ko" to "시간", "ru" to "время", "ar" to "وقت", "hi" to "समय"
        ),
        "today" to mapOf(
            "en" to "today", "es" to "hoy", "fr" to "aujourd'hui", "de" to "heute", "it" to "oggi",
            "pt" to "hoje", "ja" to "今日", "zh" to "今天", "ko" to "오늘", "ru" to "сегодня", "ar" to "اليوم", "hi" to "आज"
        ),
        "tomorrow" to mapOf(
            "en" to "tomorrow", "es" to "mañana", "fr" to "demain", "de" to "morgen", "it" to "domani",
            "pt" to "amanhã", "ja" to "明日", "zh" to "明天", "ko" to "내일", "ru" to "завтра", "ar" to "غدا", "hi" to "कल"
        ),
        "yesterday" to mapOf(
            "en" to "yesterday", "es" to "ayer", "fr" to "hier", "de" to "gestern", "it" to "ieri",
            "pt" to "ontem", "ja" to "昨日", "zh" to "昨天", "ko" to "어제", "ru" to "вчера", "ar" to "أمس", "hi" to "कल"
        ),
        "money" to mapOf(
            "en" to "money", "es" to "dinero", "fr" to "argent", "de" to "Geld", "it" to "denaro",
            "pt" to "dinheiro", "ja" to "お金", "zh" to "钱", "ko" to "돈", "ru" to "деньги", "ar" to "مال", "hi" to "पैसे"
        ),
        "food" to mapOf(
            "en" to "food", "es" to "comida", "fr" to "nourriture", "de" to "Essen", "it" to "cibo",
            "pt" to "comida", "ja" to "食べ物", "zh" to "食物", "ko" to "음식", "ru" to "еда", "ar" to "طعام", "hi" to "खाना"
        ),
        "water" to mapOf(
            "en" to "water", "es" to "agua", "fr" to "eau", "de" to "Wasser", "it" to "acqua",
            "pt" to "água", "ja" to "水", "zh" to "水", "ko" to "물", "ru" to "вода", "ar" to "ماء", "hi" to "पानी"
        ),
        "coffee" to mapOf(
            "en" to "coffee", "es" to "café", "fr" to "café", "de" to "Kaffee", "it" to "caffè",
            "pt" to "café", "ja" to "コーヒー", "zh" to "咖啡", "ko" to "커피", "ru" to "кофе", "ar" to "قهوة", "hi" to "कॉफी"
        ),
        "tea" to mapOf(
            "en" to "tea", "es" to "té", "fr" to "thé", "de" to "Tee", "it" to "tè",
            "pt" to "chá", "ja" to "お茶", "zh" to "茶", "ko" to "차", "ru" to "чай", "ar" to "شاي", "hi" to "चाय"
        ),
        "hotel" to mapOf(
            "en" to "hotel", "es" to "hotel", "fr" to "hôtel", "de" to "Hotel", "it" to "hotel",
            "pt" to "hotel", "ja" to "ホテル", "zh" to "酒店", "ko" to "호텔", "ru" to "отель", "ar" to "فندق", "hi" to "होटल"
        ),
        "airport" to mapOf(
            "en" to "airport", "es" to "aeropuerto", "fr" to "aéroport", "de" to "Flughafen", "it" to "aeroporto",
            "pt" to "aeroporto", "ja" to "空港", "zh" to "机场", "ko" to "공항", "ru" to "аэропорт", "ar" to "مطار", "hi" to "हवाई अड्डा"
        ),
        "station" to mapOf(
            "en" to "station", "es" to "estación", "fr" to "gare", "de" to "Bahnhof", "it" to "stazione",
            "pt" to "estação", "ja" to "駅", "zh" to "车站", "ko" to "역", "ru" to "станция", "ar" to "محطة", "hi" to "स्टेशन"
        ),
        "car" to mapOf(
            "en" to "car", "es" to "coche", "fr" to "voiture", "de" to "Auto", "it" to "auto",
            "pt" to "carro", "ja" to "車", "zh" to "汽车", "ko" to "자동차", "ru" to "машина", "ar" to "سيارة", "hi" to "गाड़ी"
        ),
        "bus" to mapOf(
            "en" to "bus", "es" to "autobús", "fr" to "bus", "de" to "Bus", "it" to "autobus",
            "pt" to "ônibus", "ja" to "バス", "zh" to "公交车", "ko" to "버스", "ru" to "автобус", "ar" to "حافلة", "hi" to "बस"
        ),
        "train" to mapOf(
            "en" to "train", "es" to "tren", "fr" to "train", "de" to "Zug", "it" to "treno",
            "pt" to "trem", "ja" to "電車", "zh" to "火车", "ko" to "기차", "ru" to "поезд", "ar" to "قطار", "hi" to "ट्रेन"
        ),
        "city" to mapOf(
            "en" to "city", "es" to "ciudad", "fr" to "ville", "de" to "Stadt", "it" to "città",
            "pt" to "cidade", "ja" to "街", "zh" to "城市", "ko" to "도시", "ru" to "город", "ar" to "مدينة", "hi" to "शहर"
        ),
        "street" to mapOf(
            "en" to "street", "es" to "calle", "fr" to "rue", "de" to "Straße", "it" to "strada",
            "pt" to "rua", "ja" to "通り", "zh" to "街道", "ko" to "거리", "ru" to "улица", "ar" to "شارع", "hi" to "सड़क"
        ),
        "left" to mapOf(
            "en" to "left", "es" to "izquierda", "fr" to "gauche", "de" to "links", "it" to "sinistra",
            "pt" to "esquerda", "ja" to "左", "zh" to "左", "ko" to "왼쪽", "ru" to "слева", "ar" to "يسار", "hi" to "बायां"
        ),
        "right" to mapOf(
            "en" to "right", "es" to "derecha", "fr" to "droite", "de" to "rechts", "it" to "destra",
            "pt" to "direita", "ja" to "右", "zh" to "右", "ko" to "오른쪽", "ru" to "справа", "ar" to "يمين", "hi" to "दायां"
        ),
        "straight" to mapOf(
            "en" to "straight", "es" to "recto", "fr" to "tout droit", "de" to "geradeaus", "it" to "dritto",
            "pt" to "direto", "ja" to "まっすぐ", "zh" to "直走", "ko" to "직진", "ru" to "прямо", "ar" to "مستقيم", "hi" to "सीधा"
        ),
        "good" to mapOf(
            "en" to "good", "es" to "bueno", "fr" to "bon", "de" to "gut", "it" to "buono",
            "pt" to "bom", "ja" to "良い", "zh" to "好", "ko" to "좋은", "ru" to "хороший", "ar" to "جيد", "hi" to "अच्छा"
        ),
        "bad" to mapOf(
            "en" to "bad", "es" to "malo", "fr" to "mauvais", "de" to "schlecht", "it" to "cattivo",
            "pt" to "mau", "ja" to "悪い", "zh" to "坏", "ko" to "나쁜", "ru" to "плохой", "ar" to "سيء", "hi" to "बुरा"
        ),
        "big" to mapOf(
            "en" to "big", "es" to "grande", "fr" to "grand", "de" to "groß", "it" to "grande",
            "pt" to "grande", "ja" to "大きい", "zh" to "大", "ko" to "큰", "ru" to "большой", "ar" to "كبير", "hi" to "बड़ा"
        ),
        "small" to mapOf(
            "en" to "small", "es" to "pequeño", "fr" to "petit", "de" to "klein", "it" to "piccolo",
            "pt" to "pequeno", "ja" to "小さい", "zh" to "小", "ko" to "작은", "ru" to "маленький", "ar" to "صغير", "hi" to "छोटा"
        ),
        "expensive" to mapOf(
            "en" to "expensive", "es" to "caro", "fr" to "cher", "de" to "teuer", "it" to "costoso",
            "pt" to "caro", "ja" to "高い", "zh" to "贵", "ko" to "비싼", "ru" to "дорогой", "ar" to "غالي", "hi" to "महंगा"
        ),
        "cheap" to mapOf(
            "en" to "cheap", "es" to "barato", "fr" to "pas cher", "de" to "billig", "it" to "economico",
            "pt" to "barato", "ja" to "安い", "zh" to "便宜", "ko" to "싼", "ru" to "дешевый", "ar" to "رخيص", "hi" to "सस्ता"
        ),
        "open" to mapOf(
            "en" to "open", "es" to "abierto", "fr" to "ouvert", "de" to "offen", "it" to "aperto",
            "pt" to "aberto", "ja" to "開いている", "zh" to "营业中", "ko" to "열린", "ru" to "открыто", "ar" to "مفتوح", "hi" to "खुला"
        ),
        "closed" to mapOf(
            "en" to "closed", "es" to "cerrado", "fr" to "fermé", "de" to "geschlossen", "it" to "chiuso",
            "pt" to "fechado", "ja" to "閉まっている", "zh" to "打烊", "ko" to "닫힌", "ru" to "закрыто", "ar" to "مغلق", "hi" to "बंद"
        ),
        "welcome" to mapOf(
            "en" to "welcome", "es" to "bienvenido", "fr" to "bienvenue", "de" to "willkommen", "it" to "benvenuto",
            "pt" to "bem-vindo", "ja" to "ようこそ", "zh" to "欢迎", "ko" to "환영합니다", "ru" to "добро пожаловать", "ar" to "أهلا بك", "hi" to "स्वागत है"
        ),
        "see you" to mapOf(
            "en" to "see you", "es" to "hasta luego", "fr" to "à bientôt", "de" to "bis bald", "it" to "a presto",
            "pt" to "até logo", "ja" to "またね", "zh" to "再会", "ko" to "또 봐요", "ru" to "до скорого", "ar" to "أراك لاحقا", "hi" to "फिर मिलेंगे"
        ),
        "i love you" to mapOf(
            "en" to "I love you", "es" to "Te quiero", "fr" to "Je t'aime", "de" to "Ich liebe dich", "it" to "Ti amo",
            "pt" to "Eu te amo", "ja" to "愛しています", "zh" to "我爱你", "ko" to "사랑해요", "ru" to "Я тебя люблю", "ar" to "أحبك", "hi" to "मैं तुमसे प्यार करता हूँ"
        ),
        "my name is" to mapOf(
            "en" to "my name is", "es" to "mi nombre es", "fr" to "je m'appelle", "de" to "mein Name ist", "it" to "mi chiamo",
            "pt" to "meu nome é", "ja" to "私の名前は", "zh" to "我的名字是", "ko" to "제 이름은", "ru" to "меня зовут", "ar" to "اسمي هو", "hi" to "मेरा नाम है"
        )
    )

    // Reverse lookup map built dynamically for fast O(1) multi-lingual lookups
    val REVERSE_LOOKUP_MAP: Map<String, Pair<String, Map<String, String>>> by lazy {
        val map = mutableMapOf<String, Pair<String, Map<String, String>>>()
        for ((englishKey, translations) in DICTIONARY_MAP) {
            for ((lang, text) in translations) {
                map[text.lowercase().trim()] = Pair(lang, translations)
            }
        }
        map
    }
}
