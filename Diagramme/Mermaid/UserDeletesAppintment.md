```mermaid
sequenceDiagram
    actor Benutzer
    participant BenutzerVerwaltung
    participant TerminListe
    participant Termin

    Benutzer->>BenutzerVerwaltung: Anmelden()
    activate BenutzerVerwaltung
    BenutzerVerwaltung-->>Benutzer: Anmeldung erfolgreich
    deactivate BenutzerVerwaltung

    Benutzer->>TerminListe: getTermin(id)
    activate TerminListe
    TerminListe->>Termin: Suche nach Termin
    activate Termin
    Termin-->>TerminListe: Termin gefunden
    deactivate Termin
    TerminListe-->>Benutzer: Termin gefunden
    deactivate TerminListe

    Benutzer->>TerminListe: removeTermin(Termin)
    activate TerminListe
    TerminListe-->>Benutzer: Termin gelöscht
    deactivate TerminListe
```