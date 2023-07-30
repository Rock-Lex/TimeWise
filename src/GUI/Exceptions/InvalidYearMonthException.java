package GUI.Exceptions;

class InvalidYearMonthException extends Exception {
    /**
     * Konstruktor für die InvalidYearMonthException. Diese Exception wird ausgelöst, wenn ein ungültiges Jahr oder Monat
     * bei der Terminerstellung angegeben wird. Die spezifische Fehlermeldung wird durch den Parameter message bereitgestellt.
     *
     * @param message Eine genaue Beschreibung des Fehlers, der aufgetreten ist.
     */
    public InvalidYearMonthException(String message) {
        super(message);
    }
}
