package vn.jp.language.ljp.entity;

/**
 * Created by HuynhTD on 10/17/2016.
 */

public class WordEntity {
    public String jp1;
    public String jp2;
    public String ot;
    public String sound;
    public String img;
    public int kind;
    public String romaji;

    public WordEntity() {

    }

    public WordEntity(int kind, String jp1, String jp2, String ot, String sound, String img) {
        this.kind = kind;
        this.jp1 = jp1;
        this.jp2 = jp2;
        this.ot = ot;
        this.sound = sound;
        this.img = img;
    }

    /////


    public String getJp1() {
        if (jp1 == null || jp1.equals(""))
            return jp2;
        return jp1;
    }

    ///


    public String getRomaji() {
        return romaji;
    }

    public void setRomaji(String romaji) {
        this.romaji = romaji;
    }

    public void setJp1(String jp1) {
        this.jp1 = jp1;
    }

    public String getJp2() {
        return jp2;
    }

    public void setJp2(String jp2) {
        this.jp2 = jp2;
    }

    public String getOt() {
        return ot;
    }

    public void setOt(String ot) {
        this.ot = ot;
    }

    public String getSound() {
        return sound;
    }

    public void setSound(String sound) {
        this.sound = sound;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public int getKind() {
        return kind;
    }

    public void setKind(int kind) {
        this.kind = kind;
    }
}
