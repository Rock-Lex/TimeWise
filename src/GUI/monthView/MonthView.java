package GUI.monthView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.YearMonth;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.time.DayOfWeek;
import java.util.Locale;
import javax.swing.border.LineBorder;

public class MonthView extends JPanel {
    private JPanel calendarPanel;
    private JButton[] dayButtons;

    public MonthView() {
        setLayout(new BorderLayout());

        JPanel monthYearPanel = new JPanel();
        monthYearPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));

        JLabel monthYearLabel = new JLabel("", SwingConstants.CENTER);
        monthYearPanel.add(monthYearLabel);

        add(monthYearPanel, BorderLayout.NORTH);

        calendarPanel = new JPanel();
        calendarPanel.setLayout(new GridLayout(0, 7)); // 7 columns for weekdays
        add(calendarPanel, BorderLayout.CENTER);

        dayButtons = new JButton[31]; // Assuming maximum of 31 days in a month
        renderMonth(YearMonth.now());
    }

    public void renderMonth(YearMonth yearMonth) {
        int year = yearMonth.getYear();
        int month = yearMonth.getMonthValue();
        YearMonth currentMonth = YearMonth.of(year, month);
        int numDays = currentMonth.lengthOfMonth();
        int firstDayOfWeek = currentMonth.atDay(1).getDayOfWeek().getValue();

        JLabel monthYearLabel = (JLabel) ((JPanel) getComponent(0)).getComponent(0);
        monthYearLabel.setText(currentMonth.getMonth().getDisplayName(TextStyle.FULL_STANDALONE, Locale.GERMAN) + " " + year);

        calendarPanel.removeAll();
        calendarPanel.revalidate();
        calendarPanel.repaint();

        TextStyle textStyle = TextStyle.SHORT_STANDALONE;
        Locale locale = Locale.GERMAN;
        for (DayOfWeek weekday : DayOfWeek.values()) {
            String weekdayName = weekday.getDisplayName(textStyle, locale);
            JLabel weekdayLabel = new JLabel(weekdayName, SwingConstants.LEFT); // Set left alignment
            calendarPanel.add(weekdayLabel);
        }

        LocalDate currentDate = LocalDate.now();
        int currentYear = currentDate.getYear();
        int currentMonthValue = currentDate.getMonthValue();
        int currentDayOfMonth = currentDate.getDayOfMonth();

        for (int i = 0; i < firstDayOfWeek - 1; i++) {
            JPanel emptyPanel = new JPanel();
            calendarPanel.add(emptyPanel);
        }

        for (int i = 0; i < numDays; i++) {
            int day = i + 1;
            JButton dayButton = createDayButton(String.valueOf(day));

            if (year == currentYear && month == currentMonthValue && day == currentDayOfMonth) {
                dayButton.setBackground(Color.CYAN); // Set background color to light blue
                dayButton.setForeground(Color.RED); // Set text color to red
            }

            dayButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    // Implement your action here
                    JButton clickedButton = (JButton) e.getSource();
                    int clickedDay = Integer.parseInt(clickedButton.getText());
                    System.out.println("Button " + clickedDay + " clicked!");
                }
            });

            dayButton.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e) {
                    dayButton.setBackground(Color.YELLOW); // Set background color to yellow when mouse enters
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    if (year == currentYear && month == currentMonthValue && day == currentDayOfMonth) {
                        dayButton.setBackground(Color.CYAN); // Set background color to light blue for the current day
                    } else {
                        dayButton.setBackground(calendarPanel.getBackground()); // Reset background color for other days
                    }
                }
            });


            dayButtons[i] = dayButton;
            calendarPanel.add(dayButton);
        }

        // Set the preferred height of each row in the calendar panel
        int rowHeight = calendarPanel.getFontMetrics(calendarPanel.getFont()).getHeight();
        calendarPanel.setPreferredSize(new Dimension(calendarPanel.getWidth(), rowHeight * 7));
    }

    private JButton createDayButton(String dayText) {
        JButton dayButton = new JButton(dayText);
        dayButton.setFocusPainted(false);
        dayButton.setContentAreaFilled(false);
        dayButton.setOpaque(true);
        dayButton.setBackground(calendarPanel.getBackground());
        dayButton.setHorizontalAlignment(SwingConstants.LEFT);
        dayButton.setVerticalAlignment(SwingConstants.TOP);
        dayButton.setBorder(new LineBorder(Color.BLACK)); // Set the border color and style

        return dayButton;
    }
}

