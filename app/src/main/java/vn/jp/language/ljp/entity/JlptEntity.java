package vn.jp.language.ljp.entity;

/**
 * Created by HuynhTD on 10/17/2016.
 */

public class JlptEntity {
    public int level;
    public String test_date;
    public int mondai;
    public String title;
    public String filename;
    public String link_download;

    public JlptEntity(int level, String test_date) {
        this.level = level;
        this.test_date = test_date;
    }
    public JlptEntity(){

    }

}
