package IOManager;

import java.io.*;


/**
 * Bei dieser Klasse handelt es sich um einen ICS Export/Import Interface.
 * *
 * Autor: Oleksandr Kamenskyi
 * Version: 1.0.0
 * Erstellt am: 28.07.2023
 * Letzte Änderung: 28.07.2023
 */

public class ioICS {

    private String version =    "VERSION:1.0\n";
    private String prodid =     "PRODID://\n";
    private String calBegin =   "BEGIN:VCALENDAR\n";
    private String calEnd =     "END:VCALENDAR\n";
    private String eventBegin = "BEGIN:VEVENT\n";
    private String eventEnd =   "END:VEVENT\n";

    public void IOinterface(){
    }

    public void exportICS( String fileName, String id, String dtStamp, String dtStart, String dtEnd, String teilnehmer, String summary){
        StringBuilder builder = new StringBuilder();
        builder.append(fileName);
        builder.append(".ics");

//                String testExample = "UID:uid1@example.com\n" +
//                "DTSTAMP:19970714T170000Z\n" +
//                "ORGANIZER;CN=John Doe:MAILTO:john.doe@example.com\n" +
//                "DTSTART:19970714T170000Z\n" +
//                "DTEND:19970715T035959Z\n" +
//                "SUMMARY:Bastille Day Party\n";

        String terminTemplate = String.format("UID:%s\n" +
                "DTSTAMP:%s\n" +
                "ORGANIZER;CN=John Doe:MAILTO:john.doe@example.com%s\n" +
                "DTSTART:%s\n" +
                "DTEND:%s\n" +
                "SUMMARY:%s\n", id, dtStamp, teilnehmer, dtStart, dtEnd, summary);

        try {

            File file = new File(builder.toString());

            // if file doesnt exists, then create it
            if (!file.exists()) {
                file.createNewFile();
            }

            FileWriter fw = new FileWriter(file.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(calBegin);
            bw.write(version);
            bw.write(prodid);
            bw.write(eventBegin);
            bw.write(terminTemplate);
            bw.write(eventEnd);
            bw.write(calEnd);

            bw.close();

            System.out.println("Done");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
