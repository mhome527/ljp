package vn.jp.language.ljp;

public class Constant {

    //    final static public boolean isMyDebug = true;
    // ////////
    final static public boolean isPro;
    final static public String PACKAGE_VOICE = "market://details?id=com.google.android.voicesearch";
    final static public String PACKAGE_PREMIUM = "market://details?id=learn.vietnamese.vn.pro";
    final static public String PACKAGE_UPDATE = "market://details?id=" + BuildConfig.APPLICATION_ID;
    final static public String API_MARKER = "https://androidquery.appspot.com/api/market?app=" + BuildConfig.APPLICATION_ID;

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

    //    public static final String JSON_WORDS_NAME;
    public static final String JSON_RECOGNIZE_NAME;
    public static final String JSON_MAPNAME_NAME;
//    public static final String CREATE_DB = "DB72";

    public static final String FILE_JA;
    public static final String FILE_KO;
    public static final String FILE_RU;
    public static final String FILE_FR;
    public static final String FILE_ES;
    public static final String FILE_IT;
    public static final String FILE_EN;
//    public static final String macAllow = "a";


    static {
//        if (isMyDebug) {
        JSON_RECOGNIZE_NAME = "recognize.txt";
        JSON_MAPNAME_NAME = "MapName.txt";

        FILE_JA = "ja.txt";
        FILE_KO = "ko.txt";
        FILE_RU = "ru.txt";
        FILE_FR = "fr.txt";
        FILE_ES = "es.txt";
        FILE_IT = "it.txt";
        FILE_EN = "en.txt";


//        if (Utility.getMacAddress(MyApplication.getInstance()).equals(macAllow))
//            isPro = true;
//        else
        if (BuildConfig.APPLICATION_ID.equals("learn.vietnamese.vn.pro"))
            isPro = true;
        else
            isPro = false;
    }

    public static final String KEY_UPDATE = "db_update_1"; // gia tri khac se delete database cu
    public static final String KEY_SOUND = "key_sound";
    public static final String VALUE_SOUND = "sound_1"; //gia tri tang khi file Mapname.txt thay doi.
    //
//	// /////////
//	public static final String COLUMN_SOUND = tblMapNameDao.Properties.Sound.columnName;
    public static final String INTENT_KIND = "intent_kind";
    public static final String INTENT_POSITION = "intent_pos";

    //	public static String LIST_STREET_HCM = "listStreetHCM";
//	public static String LIST_STREET_HN = "listStreetHN";
//
//	public static String KEY_CITY = "city";
//	public static final String PREF_MODIFY_AD = "date_modify_ad";
    //

    public static final String INTENT_DETAIL_LEVEL = "intent_detail_level";
    public static final String INTENT_DETAIL_NUM = "intent_detail_num";
    public static final String INTENT_WORD = "intent_word";

    //	public static String GA_RECOGNIZE_LEARN_FRAGMENT = "LEARN RECOGNIZE";
//	public static String GA_RECOGNIZE_TEST_FRAGMENT = "TEST RECOGNIZE";
    public static final String PREF_BG_THEME = "bg_theme2";
    public static final int REQ_CODE_SPEECH_INPUT = 1000;
    public static boolean bLog = BuildConfig.DEBUG;
    public static String PREF_PAGE = "curr_page";
    // GA
//    public static String KEY_ANALYSIS = "UA-54709178-3"; // daohuynh7
    public static String KEY_ANALYSIS = "UA-54709178-4"; // daohuynh7-new

    public enum TYPE_ALPHABET{
        HIRAGANA, KATAKANA
    }

  public enum TYPE_WORD{
        ANIMAL, FRUIT, OTHER
    }

    public enum TYPE_NUMBERS{
       NUMBER, PERSON, LONG, THING, BOOK, ANIMAL, AGE, SMALL_OBJECT, TIME, LOCATION, GENERIC, MONTH
    }

}
