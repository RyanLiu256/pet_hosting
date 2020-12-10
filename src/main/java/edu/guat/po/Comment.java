package edu.guat.po;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Comment {

    private Integer cid;
    private String comment;
    private String reply;
    private Date day;
    private String dayStr;

    public String getDayStr() {
        if (day!=null){
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            dayStr = sdf.format(day);
        }
        return dayStr;
    }

    public void setDayStr(String dayStr) {
        this.dayStr = dayStr;
    }

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getReply() {
        return reply;
    }

    public void setReply(String reply) {
        this.reply = reply;
    }

    public Date getDay() {
        return day;
    }

    public void setDay(Date day) {
        this.day = day;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "cid=" + cid +
                ", comment='" + comment + '\'' +
                ", reply='" + reply + '\'' +
                ", day=" + day +
                '}';
    }
}
