package vn.jp.language.ljp.entity;

/**
 * Created by Administrator on 12/23/2016.
 */
//[{"jp":"あなたも学生ですか？",
// "romaji":"Anata mo gakuseidesu ka?", "ot":"Are you a student too?"}]
public class GrammarExampleEntity {
    public String jp;
    public String romaji;
    public String ot;

    public GrammarExampleEntity() {
    }

    public GrammarExampleEntity(String jp, String romaji, String ot) {
        this.jp = jp;
        this.romaji = romaji;
        this.ot = ot;
    }

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
}
