package vn.jp.language.ljp.entity;

/**
 * Created by HuynhTD on 10/17/2016.
 */

public class AlphabetEntity {
    public int num;
    public String jp1;
    public String jp2;
    public String ot;
    public String sound;

    public AlphabetEntity() {
    }

    public AlphabetEntity(int num, String jp1, String jp2, String ot, String sound) {
        this.num = num;
        this.jp1 = jp1;
        this.jp2 = jp2;
        this.ot = ot;
        this.sound = sound;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getJp1() {
        return jp1;
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
}
