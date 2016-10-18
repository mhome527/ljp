package vn.jp.language.ljp.entity;

/**
 * Created by Administrator on 10/18/2016.
 */

public class PhraseEntity {
    public String jp1;
    public String jp2;
    public String ot;

    public PhraseEntity(String jp1, String jp2, String ot) {
        this.jp1 = jp1;
        this.jp2 = jp2;
        this.ot = ot;
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
}
