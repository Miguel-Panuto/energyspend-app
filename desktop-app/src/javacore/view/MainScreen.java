package javacore.view;

import javacore.controller.ReadData;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class MainScreen extends JFrame
{
    private ReadData rd;

    private JPanel verticalPanel = new JPanel();
    private JPanel centerLogo = new JPanel();
    private JPanel voltagePanel = new JPanel();
    private JPanel mainPanel = new JPanel();
    private JPanel lblsPanel = new JPanel();
    private JPanel dataPanel = new JPanel();

    private JLabel lblActualMeasure = new JLabel("Consumo Atual(kWh)");
    private JLabel lblMonthMeasure = new JLabel("Consumo Mensal(kWh)");
    private JLabel lblPrice = new JLabel("Custo(R$/mês)");

    private JTextField txtActualMeasure = new JTextField();
    private JTextField txtMonthMeasure = new JTextField();
    private JTextField txtPrice = new JTextField();

    private ButtonGroup btnRadio = new ButtonGroup();
    private JRadioButton voltageOne = new JRadioButton("127");
    private JRadioButton voltageTwo = new JRadioButton("220");

    private JButton sendVoltage = new JButton("Enviar tensão");
    private JButton btnStartStop = new JButton("Começar");

    private boolean isRuning = false;

    public MainScreen(JLabel logo)
    {
        setTitle("Watt Control");

        setBoxLayout(BoxLayout.Y_AXIS, verticalPanel, lblsPanel, dataPanel);
        setBoxLayout(BoxLayout.X_AXIS, centerLogo, voltagePanel, mainPanel);
        setOnCenter(centerLogo, true, logo);
        setVerticalComponents();

        addComponentsHorizontal(voltagePanel, voltageOne, voltageTwo, sendVoltage);
        addComponentsHorizontal(mainPanel, lblsPanel, dataPanel);

        addComponentsVertical(lblsPanel, lblActualMeasure, lblMonthMeasure, lblPrice);
        addComponentsVertical(dataPanel, txtActualMeasure, txtMonthMeasure, txtPrice);

        setAllFalse(txtActualMeasure, txtMonthMeasure, txtPrice);

        add(verticalPanel);
        btnRadio.add(voltageOne);
        btnRadio.add(voltageTwo);
        voltageOne.setSelected(true);

        sendVoltage.addActionListener(new SendVoltage()::actionPerformed);
        btnStartStop.addActionListener(this::actionPerformed);
        btnStartStop.setEnabled(false);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 500);
        setResizable(false);

        setBgs(verticalPanel, centerLogo, voltagePanel, mainPanel, lblsPanel, dataPanel, voltageOne, voltageTwo, txtActualMeasure, txtMonthMeasure, txtPrice);
        setFgs(lblActualMeasure, lblMonthMeasure, lblPrice, txtActualMeasure, txtMonthMeasure, txtPrice, voltageOne, voltageTwo);

        sendVoltage.setBackground(new Color(200,200,200));
        sendVoltage.setForeground(new Color(50,50,50));

        btnStartStop.setBackground(new Color(200,200,200));
        btnStartStop.setForeground(new Color(50,50,50));

        setLocationRelativeTo(null);
    }

    private void setBgs(JComponent... jComponents)
    {
        for (int i = 0; i < jComponents.length; i++)
        {
            jComponents[i].setBackground(new Color(100,100,100));
        }
    }

    private void setFgs(JComponent... jComponents)
    {
        for (int i = 0; i < jComponents.length; i++)
        {
            jComponents[i].setForeground(new Color(10,10,10));
        }
    }

    private void setVerticalComponents()
    {
        verticalPanel.add(centerLogo);
        verticalPanel.add(Box.createVerticalGlue());
        verticalPanel.add(voltagePanel);
        verticalPanel.add(Box.createRigidArea(new Dimension(10,50)));
        verticalPanel.add(mainPanel);
        verticalPanel.add(Box.createVerticalGlue());
        verticalPanel.add(btnStartStop);
        verticalPanel.add(Box.createRigidArea(new Dimension(10,80)));
    }

    private void addComponentsHorizontal(JPanel panel, JComponent... jComponents)
    {
        for (int i = 0; i < jComponents.length; i++)
        {
            boolean want;
            if (i + 1 == jComponents.length)
                want = true;
            else
                want = false;
            setOnCenter(panel, want, jComponents[i]);
        }
    }

    private void addComponentsVertical(JPanel panel, JComponent... jComponents)
    {
        for (int i = 0; i < jComponents.length; i++)
        {
            panel.add(jComponents[i]);
            if (!(i + 1 == jComponents.length))
                panel.add(Box.createVerticalGlue());
        }
    }

    private void setOnCenter(JPanel panel, boolean wantLast, JComponent comp)
    {
        panel.add(Box.createHorizontalGlue());
        panel.add(comp);
        if (wantLast)
            panel.add(Box.createHorizontalGlue());
    }

    private void setBoxLayout(int direction, JPanel... panels)
    {
        for (int i = 0; i < panels.length; i++)
        {
            if(direction == BoxLayout.Y_AXIS)
                panels[i].setLayout(new BoxLayout(panels[i], BoxLayout.Y_AXIS));
            else
                panels[i].setLayout(new BoxLayout(panels[i], BoxLayout.X_AXIS));

        }
    }

    private void setAllFalse(JComponent... jComponents)
    {
        for (int i = 0; i < jComponents.length; i++)
        {
            jComponents[i].setEnabled(false);
            jComponents[i].setPreferredSize(new Dimension(200, 30));
            jComponents[i].setMinimumSize(new Dimension(200, 30));
            jComponents[i].setMaximumSize(new Dimension(200, 30));
        }
    }

    public void actionPerformed(ActionEvent e)
    {
        isRuning = !isRuning;
        if (isRuning)
        {
            btnStartStop.setText("Parar");
        } else {
            btnStartStop.setText("Começar");
        }
        rd.turnOnOff(isRuning);
    }

    private void createRd(double num)
    {
        rd = new ReadData(num, this);
    }

    public void setTxtFields(Double actualMeasure, Double monthMeasure, Double price)
    {
        txtActualMeasure.setText(actualMeasure.toString());
        txtMonthMeasure.setText(monthMeasure.toString());
        txtPrice.setText("R$ " + actualMeasure.toString());
    }

    public class SendVoltage
    {
        public void actionPerformed(ActionEvent e)
        {
            double num;
            if (voltageOne.isSelected())
                num = 127;
            else
                num = 220;

            createRd(num);

            btnStartStop.setEnabled(true);
            sendVoltage.setEnabled(false);
        }
    }
}
