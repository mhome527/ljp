package vn.jp.language.ljp.entity;

/**
 * Created by Administrator on 10/17/2016.
 */

public class DateEntity {
    public String content;
    public String content2;


    public DateEntity() {

    }

    public DateEntity(String content, String content2) {
        this.content = content;
        this.content2 = content2;
    }

    public DateEntity(String content) {
        this.content = content;
        this.content2 = "";
    }
}
