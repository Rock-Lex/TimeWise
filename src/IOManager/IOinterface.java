package IOManager;

import java.io.*;
import java.util.ArrayList;


/**
 * Bei dieser Klasse handelt es sich um einen Database Manager.
 * *
 * Autor: Oleksandr Kamenskyi
 * Version: 1.0.0
 * Erstellt am: 30.07.2023
 * Letzte Änderung: 30.07.2023
 */

public class IOinterface {

    private String version =    "VERSION:1.0\n";
    private String prodid =     "PRODID://\n";
    private String calBegin =   "BEGIN:VCALENDAR\n";
    private String calEnd =     "END:VCALENDAR\n";
    private String eventBegin = "BEGIN:VEVENT\n";
    private String eventEnd =   "END:VEVENT\n";

    public void IOinterface(){
    }

    public void exportICS( String name ){
        StringBuilder builder = new StringBuilder();
        builder.append(name);
        builder.append(".ics");

        String testExample = "UID:uid1@example.com\n" +
                "DTSTAMP:19970714T170000Z\n" +
                "ORGANIZER;CN=John Doe:MAILTO:john.doe@example.com\n" +
                "DTSTART:19970714T170000Z\n" +
                "DTEND:19970715T035959Z\n" +
                "SUMMARY:Bastille Day Party\n";

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
            bw.write(testExample);
            bw.write(eventEnd);
            bw.write(calEnd);

            bw.close();

            System.out.println("Done");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
