package vn.jp.language.ljp.entity;

/**
 * Created by Administrator on 10/17/2016.
 */

public class WordEntity {
    public String jp1;
    public String jp2;
    public String ot;
    public String sound;
    public String img;
    public int kind;

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
}
