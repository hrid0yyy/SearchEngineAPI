package sample;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MessageList {
    @SerializedName("messages")
    private List<Message> messages;

    public MessageList(List<Message> messages) {
        this.messages = messages;
    }

    private String result;

    public String getResult() {
        return result;
    }
}
