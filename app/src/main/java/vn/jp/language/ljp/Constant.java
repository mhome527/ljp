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
    public static final int TEST_P = 0;
    public static final int TRIAL = 4;
    public static final int TRIAL_GRAMMAR = 30 + TEST_P;
    public static final int TRIAL_READING = 5 + TEST_P;
    public static final int TRIAL_LISTENING = 4 + TEST_P;
//    public static final int TRIAL_VOCABULARY = 3;
//    public static final int TRIAL_KANJI = 3;
    /////

    ////
    final static public String MY_TEXT;

    public static native String stringFromJNI();

    static {
        System.loadLibrary("native-lib");
        MY_TEXT = stringFromJNI();
    }

    //    public static final String DB_NAME = "ljp2.sqlite";
    public static final String DB_NAME = "ljp3.sqlite";
    public static final int DATABASE_VERSION = 2;

    public static final String KEY_UPDATE = "db_update_2"; // gia tri khac se delete database cu
    //
//	// /////////
    public static final String INTENT_KIND = "intent_kind";
    public static final String INTENT_LEVEL = "intent_level";
    public static final String INTENT_TITLE_Q = "intent_title_q";
    public static final String INTENT_V1 = "intent_v1";
    public static final String INTENT_V2 = "intent_v2";

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

}
