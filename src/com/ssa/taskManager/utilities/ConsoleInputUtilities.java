package com.ssa.taskManager.utilities;

import java.util.Scanner;

public class ConsoleInputUtilities {
    public static String readString () {
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }

    public static int readInt () {
        Scanner sc = new Scanner(System.in);
        boolean isValidInt = false;
        String input = "";

        while (!isValidInt) {
            input = sc.nextLine();
            isValidInt = isInt(input);
        }

        return Integer.parseInt(input);
    }

    private static boolean isInt(String value) {
        boolean ok = true;
        for (int position = 0; position < value.length(); position++) {
            char character = value.charAt(position);
            if (!Character.isDigit(character)) {
                ok = false;
                break;
            }
        }
        return ok;
    }

}
