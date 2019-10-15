package javacore.controller;

import java.io.*;

public class ReadData
{
    private double voltage;
    private boolean isRuning = true;
    private double pot = readFile("pot.txt");
    private double kwhPot;
    private double actualPot = 0;

    public ReadData(double voltage)
    {
        this.voltage = voltage;
    }

    private double readFile(String whatFile)
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
            return 0;
        }
    }

    public void initiate()
    {
        Thread thread = new Thread(()  ->
        {
            while (true)
            {
                if(!isRuning)
                    break;
                try
                {
                    actualPot = readFile("data.txt");
                    pot += actualPot;
                    kwhPot = pot * 2.7777777777777776 * Math.pow(10, -7);
                    Thread.sleep(1000);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();
    }

    public void turnOnOff()
    {
        isRuning = !isRuning;
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
