package com.ssa.taskManager.utilities;

import java.util.Locale;
import java.util.ResourceBundle;

public class Localization {

    private static Locale locale = new Locale("en", "US");
    private static ResourceBundle labels = ResourceBundle.getBundle("com.ssa.taskManager.resources.resourceBundles.locale", locale);

    public static ResourceBundle getLabels() {
        return labels;
    }
}
