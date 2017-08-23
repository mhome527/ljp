package vn.jp.language.ljp.entity;

/**
 * Created by Administrator on 5/22/2017.
 */

public class BaseEntity {
    protected int num = 0;
    protected String text;

    //

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
