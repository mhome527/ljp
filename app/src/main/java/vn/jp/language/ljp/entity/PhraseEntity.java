package vn.jp.language.ljp.entity;

/**
 * Created by Administrator on 10/18/2016.
 */

public class PhraseEntity {
    public int num;
    public String jp;
    public String romaji;
    public String ot;
    public String sound;

    public PhraseEntity() {
    }

//    public PhraseEntity(String jp, String ot, String sound, String romaji) {
//        this.jp = jp;
//        this.romaji = romaji;
//        this.ot = ot;
//        this.sound = sound;
//    }


    public String getJp() {
        return jp;
    }

    public void setJp(String jp) {
        this.jp = jp;
    }

    public String getRomaji() {
        return romaji;
    }

    public void setRomaji(String romaji) {
        this.romaji = romaji;
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
