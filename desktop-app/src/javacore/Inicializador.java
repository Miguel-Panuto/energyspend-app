package javacore;

import javacore.controller.ReadData;

public class Inicializador {
    public static void main(String[] args)
    {
        ReadData rd = new ReadData(127);
        rd.initiate();
    }
}
