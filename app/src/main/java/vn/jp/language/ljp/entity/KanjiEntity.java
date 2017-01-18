package vn.jp.language.ljp.entity;

/**
 * Created by HuynhTD on 10/17/2016.
 */

public class KanjiEntity {
    private int num;
    private String kanji;
    private String jp1;
    private String jp2;
    private String romaji;
    private String example;
    private String ot;
    private String imgPath;

    public KanjiEntity() {

    }

    /////////////


    public String getOt() {
        return ot;
    }

    public void setOt(String ot) {
        this.ot = ot;
    }

    public String getRomaji() {
        if (romaji == null)
            return "";
        return romaji;
    }

    public void setRomaji(String romaji) {
        this.romaji = romaji;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getKanji() {
        return kanji;
    }

    public void setKanji(String kanji) {
        this.kanji = kanji;
    }

    public String getJp1() {
        if (jp1 == null)
            return "";
        return jp1;
    }

    public void setJp1(String jp1) {
        this.jp1 = jp1;
    }

    public String getJp2() {
        if (jp2 == null)
            return "";
        return jp2;
    }

    public void setJp2(String jp2) {
        this.jp2 = jp2;
    }

    public String getExample() {
        if (example == null)
            return "";
        return example;
    }

    public void setExample(String example) {
        this.example = example;
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }
}
