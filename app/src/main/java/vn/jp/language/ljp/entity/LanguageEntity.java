package vn.jp.language.ljp.entity;

/**
 * Created by HuynhTD on 5/31/2017.
 */

public class LanguageEntity extends BaseEntity {
    int res;
    String text;
    String lang;

    ////
    public LanguageEntity(String lang, String text, int res) {
        this.text = text;
        this.lang = lang;
        this.res = res;
    }

    ///////////

    public int getRes() {
        return res;
    }

    public void setRes(int res) {
        this.res = res;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }


    @Override
    public String getText() {
        return text;
    }

    @Override
    public void setText(String text) {
        this.text = text;
    }
}
