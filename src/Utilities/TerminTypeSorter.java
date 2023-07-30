package Utilities;

import Calendar.TerminListe;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class TerminTypeSorter {
    public List<String> getSortedTerminTypes(TerminListe terminListe) {

        Map<String, Long> typeFrequencies = terminListe.getTermine().stream()
                .collect(Collectors.groupingBy(t -> t.getType(), Collectors.counting()));

        return typeFrequencies.entrySet().stream()
                .sorted(Map.Entry.<String, Long>comparingByValue().reversed())
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }
}