package main.model;

import main.service.MessageService;
import java.io.IOException;

public class MenuEntry {
    private int id;
    private String message;

    public MenuEntry(int id, String message) {
        this.id = id;
        this.message = message;
    }

    public void run() throws IOException {

    }

    public int getId() {
        return id;
    }

    public String getMessage() {
        return MessageService
                .getInstance()
                .getMessageByKey(message)
                .getMessage();
    }
}
