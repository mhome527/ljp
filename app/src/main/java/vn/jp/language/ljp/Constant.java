package vn.jp.language.ljp;

public class Constant {

    //    final static public boolean isMyDebug = true;
    // ////////
    final static public String PACKAGE_LVN = "market://details?id=lvn.vietnam.vn.staging";

    // ========== Purchase ===========
    public static final String BASE_64_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAxUMxIhJ+tM1Ljj9x6eqL52tUVOpKsihGfgSD0GJ2tB5vTiS5E2+xA0iqfsFgTX3atsSNKefIY8gET//T0czPNCdF7bmnQJC0xDYPRXzebh0KbxAWqkRWr7eUb0fGafOc68v9YcjgCpM4q/S0T1lkvDJlax4CBLgEd4N/GEKaxHospLyxVbzoQ4yn0b2K4XZUGB5Dvo6XwHxI6w+D668uZPW/oz/zZW7KgoXpvtNYVwTFUBCMvSPhqFu8Ek4+OBo7feYIsPUyXf1fk4M8b+6nMybBxUnyzr3osM8kDltGg64ZqVD8BXVxvbJ7RWCZPIL8hL/IV+WFesso/MLktXPpSQIDAQAB";
    public static final String SKU = "study.vn.jp";
    //    public static final String SKU = "android.test.purchased";
    public static final int PURCHASE_REQUEST_CODE = 1080;
    public static final boolean ITEM_PURCHASED = true;
    /// ===========
    public static final int TEST_P = 300;
    public static final int TRIAL = 4;
    public static final int TRIAL_GRAMMAR = 30 + TEST_P;
    public static final int TRIAL_READING = 5 + TEST_P;
    public static final int TRIAL_LISTENING = 4 + TEST_P;

    public static final String FOLDER_JLPT = "/learn_japanese";
    public static final String LINK_JLPT = "https://firebasestorage.googleapis.com/v0/b/learnjapanese-966af.appspot.com/o/";


//    public static final int TRIAL_VOCABULARY = 3;
//    public static final int TRIAL_KANJI = 3;
    /////

    ////
//    final static public String MY_TEXT;

//    public static native String stringFromJNI();

//    static {
//        System.loadLibrary("native-lib");
//        MY_TEXT = stringFromJNI();
//    }

    //    public static final String DB_NAME = "ljp2.sqlite";
    public static final String DB_NAME = "ljp4.sqlite";
    public static final int DATABASE_VERSION = 2;

    public static final String KEY_UPDATE = "db_update_2"; // gia tri khac se delete database cu
    //
//	// /////////
    public static final String INTENT_KIND = "intent_kind";
    public static final String INTENT_LEVEL = "intent_level";
    public static final String INTENT_TITLE_Q = "intent_title_q";
    public static final String INTENT_V1 = "intent_v1";
    public static final String INTENT_V2 = "intent_v2";
    public static final String INTENT_TEST_DATE = "intent_test_date";
    public static final String INTENT_MONDAI = "intent_mondai";
    public static final String INTENT_QUESTION_ID = "intent_question_id";
    public static final String INTENT_FILENAME = "intent_filename";


    public static final String INTENT_DETAIL_LEVEL = "intent_detail_level";
    public static final String INTENT_NUM = "intent_detail_num";
    public static final String INTENT_BOOKMARK = "intent_bookmark";
    public static final String INTENT_DETAIL_NUM = "intent_num";
    public static final String INTENT_FILE_NAME = "intent_filename";

    public static final String PREF_GRAMMAR_LEVEL = "pref_grammar_level";
    public static final String PREF_LEVEL = "pref_level";

    public static final String PREF_READING_N = "pref_reading_";
    public static final String PREF_GRAMMAR_N = "pref_grammar_";
    public static final String PREF_VOCABULARY_N = "pref_vocabulary_";
    public static final String PREF_LISTENING_N = "pref_listening_";
    public static final String PREF_KANJI_N = "pref_kanji_";

//   public static final String PREF_READING_N1 = "pref_reading_1";
//    public static final String PREF_GRAMMAR_N1 = "pref_grammar_1";
//    public static final String PREF_VOCABULARY_N1 = "pref_vocabulary_1";
//    public static final String PREF_LISTENING_N1 = "pref_listening_1";
//    public static final String PREF_KANJI_N1 = "pref_kanji_1";
//
//    public static final String PREF_READING_N2 = "pref_reading_2";
//    public static final String PREF_GRAMMAR_N2 = "pref_grammar_2";
//    public static final String PREF_VOCABULARY_N2 = "pref_vocabulary_2";
//    public static final String PREF_LISTENING_N2 = "pref_listening_2";
//    public static final String PREF_KANJI_N2 = "pref_kanji_2";
//
//    public static final String PREF_READING_N3 = "pref_reading_3";
//    public static final String PREF_GRAMMAR_N3 = "pref_grammar_3";
//    public static final String PREF_VOCABULARY_N3 = "pref_vocabulary_3";
//    public static final String PREF_LISTENING_N3 = "pref_listening_3";
//    public static final String PREF_KANJI_N3 = "pref_kanji_3";
//
//    public static final String PREF_READING_N4 = "pref_reading_4";
//    public static final String PREF_GRAMMAR_N4 = "pref_grammar_4";
//    public static final String PREF_VOCABULARY_N4 = "pref_vocabulary_4";
//    public static final String PREF_LISTENING_N4 = "pref_listening_4";
//    public static final String PREF_KANJI_N4 = "pref_kanji_4";
//
//    public static final String PREF_READING_N5 = "pref_reading_5";
//    public static final String PREF_GRAMMAR_N5 = "pref_grammar_5";
//    public static final String PREF_VOCABULARY_N5 = "pref_vocabulary_5";
//    public static final String PREF_LISTENING_N5 = "pref_listening_5";
//    public static final String PREF_KANJI_N5 = "pref_kanji_5";


    public enum TYPE_ALPHABET {
        HIRAGANA, KATAKANA
    }

    public enum TYPE_WORD {
        ANIMAL, FRUIT, OTHER
    }

    public enum TYPE_NUMBERS {
        NUMBER, PERSON, LONG, THING, BOOK, ANIMAL, AGE, SMALL_OBJECT, TIME, LOCATION, GENERIC, MONTH
    }

    public static final int KIND_VOCABULARY = 1;
    public static final int KIND_GRAMMAR = 2;
    public static final int KIND_READING = 3;
    public static final int KIND_LISTENING = 4;

    public static String[] N2_MONDAI_TEXT = {
            "",
            "問題1: ＿＿＿の言葉の読み方として最もよいものを、1・2・3・4から一つ選びなさい。",
            "問題2: ＿＿＿の言葉を漢字で書く時、最もよいものを、1・2・3・4から一つ 選びなさい。",
            "問題3: （　　　）に入れるのに最もよいものを、1・2・3・4から一つ選びなさい。",
            "問題4: （　　　）に入れるのに最もよいものを、1・2・3・4から一つ選びなさい。",
            "問題5: ＿＿＿の言葉に意味が最も近いものを、1・2・3・4から一つ選びなさい。",
            "問題6: 次の言葉の使い方として最もよいものを、1・2・3・4から一つ選びなさい。",
            "問題7: 次の文の（　　　）に入れるのに最もよいものを、1・2・3・4から一つ選びなさい。",
            "問題8: 次の文の_★_　に入る最もよいものを、1・2・3・4から一つ選びなさい。",
            "問題9: 次の文章を読んで、(48)から(52 )の中に入る最もよいものを、1・2・3・4から一つ選びなさい。",
            "問題10 次の(1)から(5)の文章を読んで、後の問いに対する答えとして最もよいものを、1・2・3・4から一つ選びなさい。",
            "問題11 次の(1)から(3)の文章を読んで、後の問いに対する答えとして最もよいものを、1・2・3・4から一つ選びなさい。",
            "問題12 次のAとBの文章を読んで、後の問いに対する答えとして最もよいものを、1・2・3・4から一つ選びなさい。",
            "問題13 次の文章を読んで、後の問いに対する答えとして最もよいものを、1・2・3・4から一つ選びなさい。",
            "問題14右のページは、ある大学の国際センターのポスターである。下の問いに対する答えとして最もよいものを、1・2・3・4から一つ選びなさい。"
    };

    public static String[] N3_MONDAI_GRAMMAR_TEXT = {
            "",
            "問題1: つぎの文の(　　)に入れるのに最もよいものを、1・2・3・4から一つえらびなさい。",
            "問題2: つぎの文の_★_ に入る最もよいものを、1・2・3・4から一つえらびなさい。",
            "問題3: つぎの文章を読んで、文章全体の内容を考えて、(19)から(23)の中に入る最もよいものを、1・2・3・4から一つえらびなさい。",
            "問題4: つぎの(1)から(4)の文章を読んで、質問に答えなさい。答えは、1・2・3・4から最もよいもの を一つえらびなさい。",
            "問題5: つぎの(1)と(2)の文章を読んで、質問に答えなさい。答えは、1・2・3・4から最もよいものを一 つえらびなさい。",
            "問題6: つぎの文章を読んで、質問に答えなさい。答えは、1.2.3.4 から最もよいものを一つえらびなさい。",
            "問題7: 右のページの A と B は、クリーニング店にはってある二つのお知らせである。これを読んで、下の質 問に答えなさい。答えは、1・2・3・4から最もよいものを一つえらびなさい。",
    };

    public static String[] N3_MONDAI_VOCABULARY_TEXT = {
            "",
            "問題 1__のことばの読み方として最もよいものを、1・2・3・4から一つえらびなさい。",
            "問題 2__のことばを漢字で書くとき、最もよいものを、1・2・3・4から一つえらびなさい。",
            "問題 3 (　　)に入れるのに最もよいものを、1・2・3・4から一つえらびなさい。",
            "問題 4に意味が最も近いものを、1・2・3・4から一つえらびなさい。",
            "問題 5 つぎのことばの使い方として最もよいものを、1・2・3・4から一つえらびなさい。"
    };

    public static String[] N4_MONDAI_GRAMMAR_TEXT = {
            "",
            "もんだい１　（　　　）に　何を　入れますか。１・２・３・４からいちばんいいものを一つえらんでください。",
            "もんだい２ ★  に入るものはどれですか。１·２·３·４からいちばんいいものを一つえらんでください。",
            "もんだい３　（２１）から（２５）に何を入れますか。文章の意味を考えて、１・２・３・４からいちばんいいものを一つえらんでください",
            "もんだい４　つぎの（１）から（４）の文章を読んで、質問に答えてください。こたえは、１・２・３・４からいちばんいいものを一つえらんでください。",
            "もんだい５　つぎの文章を読んで、質問に答えてください。答えは、１・２・３・４から、いちばんいいものを一つえらんでください。",
            "もんだい６　つぎのページは、「　」の知らせである。これを読んで、下の質問に答えてください。答えは1-2 3-4からいちばんいいものを一つえらんでください。",
            "", "", "", "", ""
    };

    public static String[] N4_MONDAI_VOCABULARY_TEXT = {
            "",
            "もんだい１ ＿＿＿のことばは ひらがなで どう かきますか。１・２・３・４から いちばん いい ものを ひとつ えらんでください。",
            "もんだい２ ＿＿＿のことばは どう　かきますか。１・２・３・４から いちばん いいものを ひとつ えらんでください。",
            "もんだい３（　　　）に なにを いれますか。１・２・３・４から いちばん いい ものを ひとつ えらんでください。",
            "もんだい４ ＿＿＿の ぶんとだいたいおなじいみのぶんがあります。１・２・３・４からいちばんいいものをひとつえらんでください。",
            "もんだい５ つぎの　ことばの つかいかたで いちばん いい ものを　１・２・３・４ から ひとつ えらんで ください。",
            "", "", ""
    };

    public static String[] N5_MONDAI_GRAMMAR_TEXT = {
            "",
            "もんだい１　（　　　）に　何を　入れますか。１・２・３・４からいちばんいいものを一つえらんでください。",
            "もんだい２ ★  に入るものはどれですか。１·２·３·４からいちばんいいものを一つえらんでください。",
            "もんだい３　（22）から（26）に何を入れますか。文章の意味を考えて、１・２・３・４からいちばんいいものを一つえらんでください",
            "もんだい４つぎの（１）から（３）のぶんしょうを読んで、しつもんにこたえてください。こたえは、１·２·３·４からいちばんいいものを一つえらんでください。",
            "もんだい５つぎのぶんしょうを読んで、しつもんにこたえてください。こたえは、１·２·３·４からうちばんいいものを一つえらんでください。",
            "もんだい６右のページを見て、下のしつもんにこたえてください。こたえは、１・２・３・４からいちばんいいものを一つえらんでください。",
            "", "", "", ""
    };

    public static String[] N5_MONDAI_VOCABULARY_TEXT = {
            "",
            "もんだい１ ＿＿＿のことばは ひらがなで どう かきますか。１・２・３・４から いちばん いい ものを ひとつ えらんでください。",
            "もんだい２ ＿＿＿のことばは どう　かきますか。１・２・３・４から いちばん いいものを ひとつ えらんでください。",
            "もんだい３（　　　）に なにを いれますか。１・２・３・４から いちばん いい ものを ひとつ えらんでください。",
            "もんだい４ ＿＿＿の ぶんとだいたいおなじいみのぶんがあります。１・２・３・４からいちばんいいものをひとつえらんでください。",
            "もんだい５ つぎの　ことばの つかいかたで いちばん いい ものを１・２・３・４ から ひとつ えらんで ください。",
            "", "", "", "", ""
    };

    public static String getMondai(int level, int kind, int mondai) {
        if (level == 2) {
            return N2_MONDAI_TEXT[mondai];
        } else if (level == 3) {
            if (kind == 1)
                return N3_MONDAI_VOCABULARY_TEXT[mondai];
            else
                return N3_MONDAI_GRAMMAR_TEXT[mondai];
        } else if (level == 4) {
            if (kind == 1)
                return N4_MONDAI_VOCABULARY_TEXT[mondai];
            else
                return N4_MONDAI_GRAMMAR_TEXT[mondai];
        } else if (level == 5) {
            if (kind == 1)
                return N4_MONDAI_VOCABULARY_TEXT[mondai];
            else
                return N4_MONDAI_GRAMMAR_TEXT[mondai];
        }
        return "";
    }

}