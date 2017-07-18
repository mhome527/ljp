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

    public static final int TRIAL = 4;
    public static final int TRIAL_GRAMMAR = 5;
    public static final int TRIAL_READING = 2;
    public static final int TRIAL_LISTENING = 3;
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
    public static final int DATABASE_VERSION = 1;

    public static final String KEY_UPDATE = "db_update_1"; // gia tri khac se delete database cu
    //
//	// /////////
    public static final String INTENT_KIND = "intent_kind";
    public static final String INTENT_LEVEL = "intent_level";
    public static final String INTENT_TITLE_Q = "intent_title_q";

    public static final String INTENT_DETAIL_LEVEL = "intent_detail_level";
    public static final String INTENT_DETAIL_NUM = "intent_detail_num";
    public static final String INTENT_FILE_NAME = "intent_filename";

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
