package com.wentongwang.mysports.model.module;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/3/29.
 */

public class CommentInfo implements Serializable {
    private String comment_content;
    private int comment_liked;
    public CommentInfo(String content, int liked){
        this.comment_content = content;
        this.comment_liked = liked;
    }
    public String getComment_content(){
        return comment_content;
    }
    public void setComment_content(String content){
        this.comment_content = content;
    }
    public int getComment_liked(){return comment_liked;}
    public void setComment_liked(int liked){
        this.comment_liked = liked;
    }
}
