package vn.jp.language.ljp.entity;

/**
 * Created by Administrator on 7/7/2017.
 */

public class PracticeEntity {
    int kind;
    String grammar;
    String reading;
    String vocabulary;
    String kanji;
    String listening;

    ///////

    public PracticeEntity(int kind, String grammar, String reading, String vocabulary, String kanji, String listening) {
        this.kind = kind;
        this.grammar = grammar;
        this.reading = reading;
        this.vocabulary = vocabulary;
        this.kanji = kanji;
        this.listening = listening;
    }

    public int getKind() {
        return kind;
    }

    public void setKind(int kind) {
        this.kind = kind;
    }

    public String getGrammar() {
        return grammar;
    }

    public void setGrammar(String grammar) {
        this.grammar = grammar;
    }

    public String getReading() {
        return reading;
    }

    public void setReading(String reading) {
        this.reading = reading;
    }

    public String getVocabulary() {
        return vocabulary;
    }

    public void setVocabulary(String vocabulary) {
        this.vocabulary = vocabulary;
    }

    public String getKanji() {
        return kanji;
    }

    public void setKanji(String kanji) {
        this.kanji = kanji;
    }

    public String getListening() {
        return listening;
    }

    public void setListening(String listening) {
        this.listening = listening;
    }
}
