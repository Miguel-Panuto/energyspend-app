package javacore.view;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import javax.swing.BoxLayout;

public class LoginScreen extends JFrame
{
    private JPanel mainPanel = new JPanel(new BorderLayout());
    private JPanel lblPanel = new JPanel();
    private JPanel fieldPanel = new JPanel();
    private JPanel centerPanel = new JPanel();

    private ImageIcon logo = new ImageIcon(getClass().getResource("Logo.png"));

    private JLabel lblLogin = new JLabel("Login:");
    private JLabel lblPass = new JLabel("Senha:");
    private JLabel logoIcon = new JLabel(logo);

    private JButton btnConectar = new JButton("Conectar");

    private JTextField loginField = new JTextField();

    private JPasswordField passField = new JPasswordField();

    Color bg = new Color(100,100,100);

    public LoginScreen()
    {
        setTitle("Watt Control");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        initiateCenterPanel();
        mainPanel.add(logoIcon, BorderLayout.NORTH);
        mainPanel.add(centerPanel, BorderLayout.CENTER);
        mainPanel.add(btnConectar, BorderLayout.SOUTH);
        setLayouts();
        lblPanel.add(Box.createVerticalGlue());
        initComps(lblPanel, lblLogin, lblPass);
        lblPanel.add(Box.createVerticalGlue());
        fieldPanel.add(Box.createVerticalGlue());
        initComps(fieldPanel, loginField, passField);
        fieldPanel.add(Box.createVerticalGlue());
        setSizes(new Dimension(200, 30), loginField, passField, lblPass, lblLogin);
        setSizes(new Dimension(200, 30), btnConectar);
        add(mainPanel);
        setSize(500,500);
        setResizable(false);
        setLocationRelativeTo(null);
        btnConectar.setBackground(new Color(180,180,180));
        btnConectar.setForeground(new Color(50,50,50));
        btnConectar.addActionListener(this::actionPerformed);
        loginField.addActionListener(this::actionPerformed);
        passField.addActionListener(this::actionPerformed);
        setVisible(true);
    }

    private void initiateCenterPanel()
    {
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.X_AXIS));
        centerPanel.add(Box.createHorizontalGlue());
        centerPanel.add(lblPanel);
        centerPanel.add(Box.createHorizontalBox());
        centerPanel.add(fieldPanel);
        centerPanel.add(Box.createHorizontalGlue());
        centerPanel.setBackground(bg);
    }

    private void setLayouts()
    {
        lblPanel.setLayout(new BoxLayout(lblPanel,BoxLayout.Y_AXIS));
        fieldPanel.setLayout(new BoxLayout(fieldPanel,BoxLayout.Y_AXIS));
        lblPanel.setBackground(bg);
        fieldPanel.setBackground(bg);
        mainPanel.setBackground(bg);
    }

    private void initComps(JPanel panel, JComponent... jComponents)
    {
        for(int i = 0; i < jComponents.length; i++)
        {
            panel.add(jComponents[i]);
            jComponents[i].setForeground(new Color(10,10,10));
        }
    }

    private void setSizes(Dimension d, JComponent... jComponents)
    {
        for (int i = 0; i < jComponents.length ; i++)
        {
            jComponents[i].setPreferredSize(d);
            jComponents[i].setMinimumSize(d);
            jComponents[i].setMaximumSize(d);
        }
    }

    public void actionPerformed(ActionEvent e)
    {
        if(loginField.getText().equals("admin") && passField.getText().equals("admin"))
        {
            new MainScreen(logoIcon).setVisible(true);
            setVisible(false);
        } else {
            System.out.println("Wrong credentials");
        }
    }
}
