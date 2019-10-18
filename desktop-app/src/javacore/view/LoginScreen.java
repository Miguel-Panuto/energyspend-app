package javacore.view;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import javax.swing.BoxLayout;

public class LoginScreen extends JFrame // Tela de login
{
    private JPanel mainPanel = new JPanel(new BorderLayout()); // Definindo painel principal
    private JPanel lblPanel = new JPanel(); // Painel para os labels
    private JPanel fieldPanel = new JPanel(); // Painel para os inputs
    private JPanel centerPanel = new JPanel(); // Painel para centralizar os paineis de input e label

    private ImageIcon logo = new ImageIcon(getClass().getResource("Logo.png")); // Logo do projeto
 
    private JLabel lblLogin = new JLabel("Login:"); 
    private JLabel lblPass = new JLabel("Senha:");
    private JLabel logoIcon = new JLabel(logo); // Label para o logo

    private JButton btnConectar = new JButton("Conectar"); // Botão para se conectar

    private JTextField loginField = new JTextField(); // Onde irá ser inserido o login

    private JPasswordField passField = new JPasswordField();

    Color bg = new Color(100,100,100);

    public LoginScreen()
    {
        setTitle("Watt Control");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        initiateCenterPanel(); // Inicia o método

        mainPanel.add(logoIcon, BorderLayout.NORTH);
        mainPanel.add(centerPanel, BorderLayout.CENTER);
        mainPanel.add(btnConectar, BorderLayout.SOUTH);

        setLayouts(); // Inicia o método

        lblPanel.add(Box.createVerticalGlue());

        initComps(lblPanel, lblLogin, lblPass); // Inicia o método, passando o painel desejado e os componentes

        lblPanel.add(Box.createVerticalGlue());
        fieldPanel.add(Box.createVerticalGlue());

        initComps(fieldPanel, loginField, passField); // Inicia o método, passando o painel desejado e os componentes

        fieldPanel.add(Box.createVerticalGlue());

        setSizes(new Dimension(200, 30), loginField, passField, lblPass, lblLogin); // Seta o tamanho dos componentes
        setSizes(new Dimension(200, 30), btnConectar);

        add(mainPanel);

        setSize(500,500);
        setResizable(false);
        setLocationRelativeTo(null);

        btnConectar.setBackground(new Color(180,180,180));
        btnConectar.setForeground(new Color(50,50,50));

        btnConectar.addActionListener(this::actionPerformed); // Adiciona ação ao clicar no botão
        loginField.addActionListener(this::actionPerformed); // Adiciona ação caso aperte enter com o txt field selecionado
        passField.addActionListener(this::actionPerformed); // Adiciona ação caso aperte enter com o pass field selecionado

        setVisible(true);
    }

    private void initiateCenterPanel() // Adicionando tudo para ser centralizado
    {
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.X_AXIS)); // Defini o layout para o boxlayout
        centerPanel.add(Box.createHorizontalGlue()); // Uma caixa dar um espaço entre os elementos
        centerPanel.add(lblPanel); // Adicionando o painel dos labels
        centerPanel.add(Box.createHorizontalBox()); // Caixa para separar os text fields dos labels
        centerPanel.add(fieldPanel); // Text fields
        centerPanel.add(Box.createHorizontalGlue());
        centerPanel.setBackground(bg); // Denindo a cor do fundo
    }

    private void setLayouts() // Seta os layouts e muda a cor de fundo dos paineis
    {
        lblPanel.setLayout(new BoxLayout(lblPanel,BoxLayout.Y_AXIS));
        fieldPanel.setLayout(new BoxLayout(fieldPanel,BoxLayout.Y_AXIS));
        lblPanel.setBackground(bg);
        fieldPanel.setBackground(bg);
        mainPanel.setBackground(bg);
    }

    private void initComps(JPanel panel, JComponent... jComponents) // Inicia os componentes
    {
        for(int i = 0; i < jComponents.length; i++)
        {
            panel.add(jComponents[i]); // Eles são adicionados dentro do painel que é colocado como primeiro argumento
            jComponents[i].setForeground(new Color(10,10,10));
        }
    }

    private void setSizes(Dimension d, JComponent... jComponents) // O motivo é colocar todos os tamanhos
    {
        for (int i = 0; i < jComponents.length ; i++) // Sendo min, max, pref, basicamente todos , para que não haja erro
        {
            jComponents[i].setPreferredSize(d);
            jComponents[i].setMinimumSize(d);
            jComponents[i].setMaximumSize(d);
        }
    }

    public void actionPerformed(ActionEvent e)
    {
        // TODO cadastro, com logi n e senha
        if(loginField.getText().equals("admin") && passField.getText().equals("admin")) // Verifica se os textos são iguais às credenciais
        {
            new MainScreen(logoIcon).setVisible(true); // Caso sim, é instancializado a tela principal
            setVisible(false);
        } else {
            System.out.println("Wrong credentials"); // Caso não é printado no console que está errado
        }
    }
}
