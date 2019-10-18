package javacore.view;

import javacore.controller.ReadData;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class MainScreen extends JFrame
{
    // Objeto para ler os dados
    private ReadData rd;

    // Paineis
    private JPanel verticalPanel = new JPanel();
    private JPanel centerLogo = new JPanel();
    private JPanel voltagePanel = new JPanel();
    private JPanel mainPanel = new JPanel();
    private JPanel lblsPanel = new JPanel();
    private JPanel dataPanel = new JPanel();

    // Labels para cada atributo a ser mostrado
    private JLabel lblActualMeasure = new JLabel("Consumo Atual(kWh)");
    private JLabel lblMonthMeasure = new JLabel("Consumo Mensal(kWh)");
    private JLabel lblPrice = new JLabel("Custo(R$/mês)");

    // Text fields desabilitados para mostrar os daods
    private JTextField txtActualMeasure = new JTextField();
    private JTextField txtMonthMeasure = new JTextField();
    private JTextField txtPrice = new JTextField();

    // Radio buttons para enviar a tensão a ser usada na tomada
    private ButtonGroup btnRadio = new ButtonGroup();
    private JRadioButton voltageOne = new JRadioButton("127");
    private JRadioButton voltageTwo = new JRadioButton("220");

    // Botões para enviar a tensão e outro para começar o processo
    private JButton sendVoltage = new JButton("Enviar tensão");
    private JButton btnStartStop = new JButton("Começar");

    // Boolean que serve para ativar e desativar todo o processo
    private boolean isRuning = false;

    public MainScreen(JLabel logo) // O construtor recebe o logo, cujo qual vem da tela de login
    {
        setTitle("Watt Control");

        setBoxLayout(BoxLayout.Y_AXIS, verticalPanel, lblsPanel, dataPanel); // Chama função para já deixar setado como box layout em Y os paineis
        setBoxLayout(BoxLayout.X_AXIS, centerLogo, voltagePanel, mainPanel); // Chama função para já deixar setado como box layout em X os paineis
        setOnCenter(centerLogo, true, logo); // Isso irá deixar algum objeto ao meio, no caso dentro de 1 ou 2
        setVerticalComponents();

        // Será adicionado componentes horizontalmente
        addComponentsHorizontal(voltagePanel, voltageOne, voltageTwo, sendVoltage); 
        addComponentsHorizontal(mainPanel, lblsPanel, dataPanel);

        // Será adicionado componentes verticalmente
        addComponentsVertical(lblsPanel, lblActualMeasure, lblMonthMeasure, lblPrice);
        addComponentsVertical(dataPanel, txtActualMeasure, txtMonthMeasure, txtPrice);

        //Isso deixa todos os text fields falso, no caso para a inserção de dados
        setAllFalse(txtActualMeasure, txtMonthMeasure, txtPrice);

        add(verticalPanel);
        // Adiciona os botões de radio
        btnRadio.add(voltageOne);
        btnRadio.add(voltageTwo);
        voltageOne.setSelected(true); // Deixa o de 127v selecionado

        sendVoltage.addActionListener(new SendVoltage()::actionPerformed); // A ação de enviar a tensão para o objeto de ler os dados
        btnStartStop.addActionListener(this::actionPerformed); // O botão para começar a aparecer na tela a potencia e o gasto
        btnStartStop.setEnabled(false); // Deixa o botão de começar/parar falso, impossibilitanto de ser usado, feito mais para segurança

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 500);
        setResizable(false);

        // Isso muda todas as cores do plano de fundo
        setBgs(verticalPanel, centerLogo, voltagePanel, mainPanel, lblsPanel, dataPanel, voltageOne, voltageTwo, txtActualMeasure, txtMonthMeasure, txtPrice);
        // Isso muda a cor de textos
        setFgs(lblActualMeasure, lblMonthMeasure, lblPrice, txtActualMeasure, txtMonthMeasure, txtPrice, voltageOne, voltageTwo);

        // Muda as cores dos botões
        sendVoltage.setBackground(new Color(200,200,200));
        sendVoltage.setForeground(new Color(50,50,50));
        btnStartStop.setBackground(new Color(200,200,200));
        btnStartStop.setForeground(new Color(50,50,50));

        setLocationRelativeTo(null);
    }

    private void setBgs(JComponent... jComponents)
    {
        Color c = new Color(100,100,100);
        for (int i = 0; i < jComponents.length; i++)
        {
            jComponents[i].setBackground(c);
        }
    }

    private void setFgs(JComponent... jComponents)
    {
        Color c = new Color(10,10,10);
        for (int i = 0; i < jComponents.length; i++)
        {
            jComponents[i].setForeground(c);
        }
    }

    private void setVerticalComponents()
    {
        // Método para organizar tudo verticalmente
        verticalPanel.add(centerLogo);
        verticalPanel.add(Box.createVerticalGlue());
        verticalPanel.add(voltagePanel);
        verticalPanel.add(Box.createRigidArea(new Dimension(10,50)));
        verticalPanel.add(mainPanel);
        verticalPanel.add(Box.createVerticalGlue());
        verticalPanel.add(btnStartStop);
        verticalPanel.add(Box.createRigidArea(new Dimension(10,80)));
    }

    private void addComponentsHorizontal(JPanel panel, JComponent... jComponents) // Ele pede o painel e os componentes
    {
        for (int i = 0; i < jComponents.length; i++) 
        {
            boolean want;
            if (i + 1 == jComponents.length) // No caso irá ser verificado se é o ultimo a ser adicionado
                want = true; // Se sim será adionado mais 1 glue a direita
            else
                want = false; // Se não, não irá adicionar
            // Isso adicionando os componentes com o glue a esquerda sempre, e a direita dependendo se for o útimo ou não
            setOnCenter(panel, want, jComponents[i]); 
        }
    }

    private void addComponentsVertical(JPanel panel, JComponent... jComponents) // Ele pede o painel e os componentes
    {
        for (int i = 0; i < jComponents.length; i++)
        {
            panel.add(jComponents[i]); // Isso vai adicionando os componentes ao painel
            if (i + 1 != jComponents.length) // Caso não seja o último elemento
                panel.add(Box.createVerticalGlue()); // Irá ser adicionado o um vertical glue
        }
    }

    private void setOnCenter(JPanel panel, boolean wantLast, JComponent comp)
    {
        panel.add(Box.createHorizontalGlue()); // Irá ser criado um glue na esquerda ao componente
        panel.add(comp); // Componente adicionado ao painel desejado
        if (wantLast) // Caso o want last for acionado
            panel.add(Box.createHorizontalGlue()); // Será adicionado à direita também
    }

    private void setBoxLayout(int direction, JPanel... panels) // Recebe a constate de qual o sentido desejado
    {
        for (int i = 0; i < panels.length; i++) // Entra no loop com os paineis
        {
            panels[i].setLayout(new BoxLayout(panels[i], direction)); // Seta o painel atual como box layout e com a direção vinda da assinatura do método
        }
    }

    private void setAllFalse(JComponent... jComponents)
    {
        Dimension d = new Dimension(200, 30);
        for (int i = 0; i < jComponents.length; i++)
        {
            jComponents[i].setEnabled(false); // Impossibilita a insersão de dados
            // Seta todos os tamanhos
            jComponents[i].setPreferredSize(d);
            jComponents[i].setMinimumSize(d);
            jComponents[i].setMaximumSize(d);
        }
    }

    public void actionPerformed(ActionEvent e)
    {
        isRuning = !isRuning; // Inverte o estado da booleana
        if (isRuning)
        {
            btnStartStop.setText("Parar"); // Se estiver rodando o texto do botão muda para 'Parar'
        } else {
            btnStartStop.setText("Começar"); // Caso contrário muda para 'Começar' 
        }
        rd.turnOnOff(isRuning); // Isso liga/desliga o objeto de ler dados
    }

    private void createRd(double num)
    {
        rd = new ReadData(num, this); // Cria um novo objeto passando a tensão e o próprio objeto da tela
    }

    public void setTxtFields(Double actualMeasure, Double monthMeasure, Double price) // Método chamado pelo leitor de dados
    {
        // Isso basicamnte muda os textos dentro dos input fields
        txtActualMeasure.setText(actualMeasure.toString());
        txtMonthMeasure.setText(monthMeasure.toString());
        txtPrice.setText("R$ " + actualMeasure.toString());
    }

    public class SendVoltage
    {
        public void actionPerformed(ActionEvent e)
        {
            double num;
            if (voltageOne.isSelected()) // Verifica qual o botão de radio selecionado
                num = 127;
            else
                num = 220;

            createRd(num);

            btnStartStop.setEnabled(true); // Torna o botão de começar verdadeiro, assim possibilitando usar ele
            sendVoltage.setEnabled(false); // Torna o botão de enviar a tenão como falso
        }
    }
}