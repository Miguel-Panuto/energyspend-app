package javacore.controller;

import javacore.view.MainScreen;

import java.io.*;

public class ReadData
{
    // Atributos
    private double voltage;
    private boolean isRuning = true;
    private double pot = readFile("pot.txt"); // Abrir o arquivo da potencia atual, medida do banco de dados e voltada a cada 10s
    private double kwhPot;
    private double actualPot = 0;

    // Constante do preço de WS
    private static final double priceWS = 0.0000001464;

    private MainScreen ms;

    public ReadData(double voltage, MainScreen ms)
    {
        this.ms = ms;
        this.voltage = voltage;
    }


    private double readFile(String whatFile) // Isso irá abrir o arquivo, no caso, o arquivo escolhido
    {
        try
        {
            File file = new File(System.getProperty("user.dir") + "\\" + whatFile);
            FileReader fr = new FileReader(file);
            BufferedReader bf = new BufferedReader(fr);
            return Double.parseDouble(bf.readLine()) * voltage;
        }
        catch (IOException e)
        {
            return 0; // Caso não seja encontrado o arquivo, é retornado o valor 0
        }
    }

    private void initiate()
    {
        Thread thread = new Thread(()  ->
        {
            while (true)
            {
                if(!isRuning)
                    break;
                try
                {
                    actualPot = readFile("data.txt") * 2.7777777777777776 * Math.pow(10, -7);
                    pot += actualPot;
                    ms.setTxtFields(actualPot, pot, pot * priceWS);
                    Thread.sleep(1000);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();
    }

    public void turnOnOff(boolean isRuning)
    {
        this.isRuning = isRuning;
        if (isRuning)
            initiate();
    }

    public void setVoltage(double voltage)
    {
        this.voltage = voltage;
    }

    public double getPot()
    {
        return pot;
    }

    public double getKwhPot()
    {
        return kwhPot;
    }
}
