package main;

import main.controller.ConsoleController;
import main.service.MessageService;

import java.io.IOException;

public class Main {

    public static void main (String[] args) throws IOException {
        ConsoleController cc = new ConsoleController();
        System.out.println(MessageService.getInstance().getMessageByKey("start").getMessage());
        cc.getMainMenu();
    }

}
