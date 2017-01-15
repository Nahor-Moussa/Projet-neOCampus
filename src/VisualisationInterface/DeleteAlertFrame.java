package VisualisationInterface;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.List;

public class DeleteAlertFrame extends JFrame implements ActionListener{

    private VisualisationFrame frame;

    private List<Alert> alertList = new ArrayList<>();

    private JScrollPane scrollPane = new JScrollPane();

    private JCheckBox[] boxTab;

    private JPanel listPanel;
    private JPanel buttonPanel;
    private JPanel mainPanel;

    private JButton ok;
    private JButton cancel;

    public DeleteAlertFrame( VisualisationFrame frame, List<Alert> alertList){
        super("Connexion au serveur");
        this.frame = frame;
        this.alertList = alertList;

        setSize(500, 250);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        initialize();
        setListener();
        setResizable(false);
        setVisible(true);
    }

    private void initialize() {
        Container content = getContentPane();
        listPanel = new JPanel(new GridLayout(alertList.size(),1));
        boxTab = new JCheckBox[alertList.size()];

        for (int i = 0; i < alertList.size(); i++) {
            boxTab[i] = new JCheckBox(alertList.get(i).getId()+", type: "+alertList.get(i).getSensorType()+
                    ", min :  "+alertList.get(i).getMin()+", max : "+alertList.get(i).getMax());
            listPanel.add(new JPanel().add(boxTab[i]));
        }
        scrollPane = new JScrollPane(listPanel, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        mainPanel = new JPanel(new GridLayout(2,1));

        ok = new JButton("Ok");
        cancel = new JButton("Annuler");
        buttonPanel = new JPanel();
        buttonPanel.add(cancel);
        buttonPanel.add(ok);

        mainPanel.add(scrollPane);
        mainPanel.add(buttonPanel);

        content.add(mainPanel);
    }

    private void setListener() {
        cancel.addActionListener(this);
        ok.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if( e.getSource() == cancel) dispose();
        if (e.getSource() == ok) {
            for (int i = 0; i < boxTab.length; i++)
                if (boxTab[i].isSelected()) alertList.remove(i);
            frame.setAlertList(alertList);
            frame.updateNbAlert(alertList.size());
            dispose();
        }
    }
}
