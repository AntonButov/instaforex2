package pro.butovanton.instaforex_2;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Signal {
    @SerializedName("Comment")
    @Expose
    String comment;
    @SerializedName("ActualTime")
    @Expose
    int time;
    @SerializedName("Pair")
    @Expose
    String pair;

    public String getComment() {
        return comment;
    }

    public int getTime() {
        return time;
    }

    public String getPair() {
        return pair;
    }
}
