package main.service;

import main.model.Message;

import java.util.ArrayList;

public class MessageService {
    private static final MessageService mc = new MessageService();
    private ArrayList<Message> messages = new ArrayList<>();

    private MessageService() {
        messages.add(new Message("start", "TaskManager wurde erfolgreich gestartet"));
        messages.add(new Message("ende", "Auf wiedersehen"));
        messages.add(new Message("back", "Zurück"));
        messages.add(new Message("no_task_created", "Es wurden noch keine Aufgaben erstellt. Erstellen Sie zuerst eine entsprechende Aufgabe."));
        messages.add(new Message("no_task_found", "Gewählte Aufgabe nicht gefunden. Wählen Sie eine verfügbare."));
        messages.add(new Message("menu_selection", "Bitte wählen Sie einen Menu Eintrag in dem Sie eine entsprechenden Menu Nummer eingeben."));
        messages.add(new Message("wrong_menu_selection", "Falscher Menu Eintrag, bitte wählen Sie eine enstprechende Nummer."));
        messages.add(new Message("number", "Nummer"));
        messages.add(new Message("short_description", "Kurzbeschreibung"));
        messages.add(new Message("description", "Beschreibung"));
        messages.add(new Message("priority", "Priorität (1 - Hoch, 2 - Medium, 3 - Niedrig)"));
        messages.add(new Message("state", "Status (1 - Open, 2 - In Progress, 3 - Done)"));
        messages.add(new Message("show_task_dialog_question1", "Bitte wählen Sie eine der folgenden Tasks aus um die Detailierten Informationen anzuzeigen."));
        messages.add(new Message("show_task_dialog_question2", "Nummer der anzuzeigenden Aufgaben:"));
        messages.add(new Message("edit_task_dialog_question1", "Bitte wählen Sie eine der folgenden Tasks aus um sie zu bearbeiten."));
        messages.add(new Message("edit_task_dialog_question2", "Nummer der zu bearbeitenden Aufgaben: "));
        messages.add(new Message("edit_task_dialog_question3", "Bitte wählen Sie eine entsprechende Nummer um ein Feld zu bearbeiten:"));
        messages.add(new Message("edit_task_dialog_success", "Die Änderungen wurden Erfolgreich durchgeführt"));
        messages.add(new Message("delete_task_dialog_question1", "Bitte wählen Sie eine der folgenden Aufgaben aus um sie zu löschen."));
        messages.add(new Message("delete_task_dialog_question2", "Nummer der zu löschenden Aufgaben:"));
        messages.add(new Message("delete_task_dialog_success", "Aufgabe wurde erfolgreich gelöscht."));
        messages.add(new Message("main_menu_exit", "Beenden"));
        messages.add(new Message("main_menu_add_task", "Aufgabe hinzufügen"));
        messages.add(new Message("main_menu_edit_task", "Aufgabe bearbeiten"));
        messages.add(new Message("main_menu_delete_task", "Aufgabe löschen"));
        messages.add(new Message("main_menu_show_task", "Aufgabe anzeigen"));
        messages.add(new Message("main_menu_show_all_tasks", "Alle Aufgaben Anzeigen"));
    }

    public static MessageService getInstance() {
        return mc;
    }

    public Message getMessageByKey (String key) {
        return this.messages
                .stream()
                .filter(m -> m.getKey() == key)
                .findAny()
                .orElse(null);
    }
}

