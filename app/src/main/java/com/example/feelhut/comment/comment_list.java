package com.example.feelhut.comment;

import com.google.firebase.firestore.ServerTimestamp;

import java.util.Date;

public class comment_list {

    @ServerTimestamp
    Date mTimestamp;
    public Date getmTimestamp() {
        return mTimestamp;
    }

    public void setmTimestamp(Date mTimestamp) {
        this.mTimestamp = mTimestamp;
    }


    public comment_list( ){}
    public comment_list(String comments) {
        this.comments = comments;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    String comments;
}
