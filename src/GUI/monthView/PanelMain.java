package GUI.monthView;

import javax.swing.*;

public class PanelMain {

    private JPanel panel1;
    private JTabbedPane tabbedPane1;

    public static void main(String[] args){
        MonthView monthView = new MonthView();

        JFrame PanelMain = new JFrame();
        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Monatsansicht", monthView);
        PanelMain.add(tabbedPane);
        PanelMain.getContentPane().add(tabbedPane);
        PanelMain.setSize(800, 600);
        PanelMain.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Zeige das Hauptfenster an
        PanelMain.setVisible(true);
    }

}
