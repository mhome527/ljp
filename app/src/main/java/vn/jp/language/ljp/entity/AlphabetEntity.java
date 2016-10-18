package vn.jp.language.ljp.entity;

/**
 * Created by Administrator on 10/17/2016.
 */

public class AlphabetEntity {
    public String content;
    public String content2;


    public AlphabetEntity() {

    }

    public AlphabetEntity(String content, String content2) {
        this.content = content;
        this.content2 = content2;
    }

    public AlphabetEntity(String content) {
        this.content = content;
        this.content2 = "";
    }
}
