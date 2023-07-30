package GUI;

import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;
import java.util.stream.Collectors;

public class EditableComboBoxExample {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Editable JComboBox Example");

        // Erstellen Sie ein eigenes ComboBoxModel, das die Einträge sortiert speichert
        SortedComboBoxModel<String> model = new SortedComboBoxModel<>();

        JComboBox<String> comboBox = new JComboBox<>(model);
        comboBox.setEditable(true);

        // Fügen Sie die automatische Vervollständigung hinzu
        AutoCompleteDecorator.decorate(comboBox);

        // Fügen Sie einen KeyListener hinzu, um neue Einträge zur ComboBox hinzuzufügen,
        // wenn die Enter-Taste gedrückt wird
        comboBox.getEditor().getEditorComponent().addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    String newEntry = (String) comboBox.getEditor().getItem();
                    // Fügen Sie den Eintrag zum Modell hinzu, das automatisch sortiert
                    model.addOrIncrementEntry(newEntry);
                }
            }
        });

        frame.add(comboBox);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    static class SortedComboBoxModel<E> extends DefaultComboBoxModel<E> {
        private final Map<E, Integer> frequencyMap = new HashMap<>();

        void addOrIncrementEntry(E entry) {
            if (entry == null) return;
            frequencyMap.put(entry, frequencyMap.getOrDefault(entry, 0) + 1);
            sortModel();
        }

        private void sortModel() {
            List<E> sortedList = frequencyMap.entrySet().stream()
                    .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                    .map(Map.Entry::getKey)
                    .collect(Collectors.toList());

            removeAllElements();
            for (E entry : sortedList) {
                addElement(entry);
            }
        }
    }
}

