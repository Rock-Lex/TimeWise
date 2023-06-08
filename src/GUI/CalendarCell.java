package GUI;

import java.awt.Color;
import java.time.LocalDate;
import javax.swing.JButton;
import javax.swing.JLabel;

public class CalendarCell extends JButton {
    private LocalDate date;
    private boolean title;

    public CalendarCell() {
        setHorizontalAlignment(JLabel.CENTER);
    }

    public void asTitle() {
        title = true;
    }

    public boolean isTitle() {
        return title;
    }

    public void currentMonth(boolean act) {
        setEnabled(act);
    }
}
