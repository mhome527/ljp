package vn.jp.language.ljp.entity;

/**
 * Created by Administrator on 7/7/2017.
 */

public class PracticeEntity {
    int num;
    int numId;
    int kind;
    int bookmarks; //1:checked
    String title;
    String question;
    String q1;
    String q2;
    String q3;
    String q4;
    int ans;
    int review; //0:read; 1: unread; 2: wrong answer
    int ref;
    String sound;

    String hint;
    ///////


    public String getSound() {
        return sound;
    }

    public void setSound(String sound) {
        this.sound = sound;
    }

    public int getNumId() {
        return numId;
    }

    public void setNumId(int numId) {
        this.numId = numId;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getQuestion() {
        if (question == null)
            return "";
        return question;
    }

//    public String getQuestion2() {
//        if (question == null) //temp
//            question = "";
//
//        if (review == -1) //answer wrong
//            return "<font color='red'>" + question + "</font>";
//        else if (review == 1)
//            return "<font color='gray'>" + question + "</font>";
//        else
//            return "<font color='black'>" + question + "</font>";
//    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public int getKind() {
        return kind;
    }

    public void setKind(int kind) {
        this.kind = kind;
    }

    public int getBookmarks() {
        return bookmarks;
    }

    public void setBookmarks(int bookmarks) {
        this.bookmarks = bookmarks;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getQ1() {
        return q1;
    }

    public void setQ1(String q1) {
        this.q1 = q1;
    }

    public String getQ2() {
        return q2;
    }

    public void setQ2(String q2) {
        this.q2 = q2;
    }

    public String getQ3() {
        return q3;
    }

    public void setQ3(String q3) {
        this.q3 = q3;
    }

    public String getQ4() {
        return q4;
    }

    public void setQ4(String q4) {
        this.q4 = q4;
    }

    public int getAns() {
        return ans;
    }

    public void setAns(int ans) {
        this.ans = ans;
    }

    public int getReview() {
        return review;
    }

    public void setReview(int review) {
        this.review = review;
    }

    public int getRef() {
        return ref;
    }

    public void setRef(int ref) {
        this.ref = ref;
    }

    public String getHint() {
        return hint;
    }

    public void setHint(String hint) {
        this.hint = hint;
    }
}
