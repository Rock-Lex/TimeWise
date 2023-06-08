package GUI;

import javax.swing.*;

import GUI.monthView.*;

import java.awt.*;

public class PanelMain extends JPanel{

    private JPanel panelMain;
    private JTabbedPane tabbedPane;
    private JPanel panelChange;
    private JFrame mainFrame;


    public PanelMain(){
        mainFrame = new JFrame("Main Panel");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setOpaque(false);
        setLayout(new BorderLayout());
        MonthView monthView = new MonthView();
        panelChange = new PanelChange();

        tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Monatsansicht", monthView);


        mainFrame.add(panelChange, BorderLayout.CENTER);
        mainFrame.add(tabbedPane, BorderLayout.SOUTH);
        mainFrame.setSize(800, 800);
        mainFrame.setVisible(true);

    }
    public static void main(String[] args){
        PanelMain panelMain = new PanelMain();

//        JFrame panelMain = new JFrame();
//        panelMain.setLayout(new OverlayLayout(panelMain.getContentPane()));
//        MonthView monthView = new MonthView();
//        JPanel panelChange = new PanelChange();
//
//
//
//        JTabbedPane tabbedPane = new JTabbedPane();
//
//        tabbedPane.addTab("Monatsansicht", monthView);
//
//        JPanel contentPane = new JPanel();
//        contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
//
//        panelMain.getContentPane().add(tabbedPane);
//        contentPane.add(panelChange);
//        panelMain.add(tabbedPane);
//        panelMain.setContentPane(contentPane);
//        panelMain.setSize(800, 600);
//        panelMain.setVisible(true);
    }
}
