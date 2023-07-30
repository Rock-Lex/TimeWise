package Utilities;

import Calendar.TerminListe;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
/**
 * Bei dieser Klasse handelt es sich um die Testklasse mit Units für die Klasse Termin.
 *
 * Autor: Philipp Voß
 * Version: 1.0.0
 * Erstellt am: 30.05.2023
 * Letzte Änderung: 30.07.2023
 */
public class TerminTypeSorter {
    /**
     * Diese Methode erzeugt eine Liste von Termin-Typen, sortiert nach ihrer Häufigkeit in absteigender Reihenfolge.
     *
     * @param terminListe Die Liste von Terminen, deren Typen extrahiert und gezählt werden sollen.
     * @return Eine Liste von Strings, die die Termin-Typen repräsentieren, sortiert nach ihrer Häufigkeit.
     *         Bei gleichem Zählerstand wird die Reihenfolge nicht festgelegt.
     */
    public List<String> getSortedTerminTypes(TerminListe terminListe) {

        Map<String, Long> typeFrequencies = terminListe.getTermine().stream()
                .collect(Collectors.groupingBy(t -> t.getType(), Collectors.counting()));

        return typeFrequencies.entrySet().stream()
                .sorted(Map.Entry.<String, Long>comparingByValue().reversed())
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }
}