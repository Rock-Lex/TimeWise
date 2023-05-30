# TimeWise

## Description
https://docs.google.com/document/d/1uNtRR81TT59xqEhV6PYmT0JE1Okxd2mY76Au3XIcCRc/edit#
#### Zielbestimmung: 
Das Ziel ist es, ein Programm zu entwickeln, welches einen digitalen Kalender abbildet. Dieser Kalender kann auf Monat-, Wochen- und Tagesebene betrachtet werden. Es lassen sich Termin minutengenau eintragen und ändern. Zu den Terminen können Datum, Startzeit, Endzeit, Titel, Beschreibung, Ort und Personen eingetragen werden. Des Weiteren sind Feiertage vorgemerkt. Im Kalender ist der aktuelle Tag markiert und es lässt sich zum aktuellen Tag navigieren. Außerdem können Serientermine eingestellt werden.
Wir entscheiden uns für eine agile Durchführung des Projektes. Um sicherzustellen, dass ausreichend Zeit für die Durchführung uns zur Verfügung steht, haben wir uns für eine vorzeitige Deadline für die Projektarbeit bis zum Projektende eingeplant. Somit haben wir einen zeitlichen Puffer, der uns flexibles Handeln ermöglicht.
#### Muss
Kalender, Termine erstellen und anzeigen (ganztägige Termine), Aktueller Tagesanzeiger, Tagesansicht, Wochenansicht, Monatsansicht
#### Kann
kann: Termin Eigenschaften (individuelle Zeitr%C3%A4ume, Wiederholungstermin), Termin Kategorisierung, Ressourcen-Buchung (Raum/Ort, Telefonspinne f. Hybrid, Beamer etc.), Teilnehmer, %C3%9Cbersicht zuk%C3%BCnftiger Termine, ICS Export, Termin per Mail versenden, Feiertage, Terminplaner/planungsassistent, Fahrzeit, Terminerinnerungen (Pop-Up Fenster), Eingabehilfe (bei Eingabe Termin-Titel Vorschlag mit Termin Eigenschaften bereits vorhandener Termine)

## Contributors
- #### Oleksandr Kamenskyi: </br> 
  - Aufgabenbereich: Teamleitung, Systemarchitektur, Git-Management, Dokumentation
- #### Philipp Voß: </br>
    - Aufgabenbereich: Programmierer, Systemarchitektur, Dokumentation
- #### Tobias Rehm: </br>
    - Aufgabenbereich: Programmierer, Dokumentation
- #### Simon Degmair: </br>
    - Aufgabenbereich: Programmierer, Dokumentation
- #### Leif Erik Maluck: </br>
    - Aufgabenbereich: Programmierer, Dokumentation

####  24.05.2023 </br> Berlin, Germany 


```mermaid
graph TB
    subgraph Projektphasen
        A[Projekt: Entwicklung eines Terminplaners]
        B[Analysephase]
        C[Planungsphase]
        D[Entwicklung]
        E[Testphase]
        F[Dokumentation]
        G[Abschluss und Qualitätssicherung]
    end

    subgraph Analysephase
        direction LR
        B1[Anforderungsanalyse]
        B2[Marktanalyse]
        B3[Zielgruppenanalyse]
        B4[Risikoanalyse]
    end

    subgraph Planungsphase
        C1[Zeitplan erstellen]
        C2[Aufgabenplan erstellen]
        C3[Programmaufbau planen]
        C4[GUI Planen]
    end

    subgraph Entwicklung
        D1[Backend-Entwicklung]
        D1_1[Entwicklung der Klassen]
        D1_2[Klassen miteinander Verbinden]
        D1_3[Datenbank implementieren und integrieren]
        D1_4[Exceptions Implementieren]
        D2[Frontend-Entwicklung]
        D2_1[Views Designen]
        D2_2[Views Implementieren]
        D2_3[Funktionalität zu Views hinzufügen]
    end

    subgraph Testphase
        E1[Unit-Tests erstellen]
        E2[Integrationstests erstellen]
        E3[Benutzertests erstellen]
    end

    subgraph Dokumentation
        F1[Benutzerhandbuch]
        F2[Technische Dokumentation]
    end

    subgraph Abschluss
        G1[Überprüfung der Projektergebnisse]
        G2[Fehlerbehebung und Optimierung]
        G3[Projektabschlussbericht schreiben]
    end

    


    A --> B
    B --> B1
    B --> B2
    B --> B3
    B --> B4
    A --> C
    C --> C1
    C --> C2
    C --> C3
    C --> C4
    A --> D
    D --> D1
    D1 --> D1_1
    D1 --> D1_2
    D1 --> D1_3
    D1 --> D1_4
    D --> D2
    D2 --> D2_1
    D2 --> D2_2
    D2 --> D2_3
    A --> E
    E --> E1
    E --> E2
    E --> E3
    A --> F
    F --> F1
    F --> F2
    A --> G
    G --> G1
    G --> G2
    G --> G3


```
