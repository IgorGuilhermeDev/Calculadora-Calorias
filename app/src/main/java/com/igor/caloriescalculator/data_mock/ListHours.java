package com.igor.caloriescalculator.data_mock;

import java.util.Arrays;
import java.util.List;

/**
 * @author Igor Guilherme Almeida Rocha
 * Classe de suporte para mockar os dados das horas do dia.
 */
public class ListHours {

    /**
     * @return Retorna uma lista de Strings com as horas de 1 a 24.
     */
    public static List<String> getListHours(){
        return  Arrays.asList("1 hora", "2 horas", "3 horas", "4 horas", "5 horas", "6 horas", "7 horas",
                "8 horas", "9 horas", "10 horas", "11 horas", "12 horas", "13 horas", "14 horas", "15 horas",
                "16 horas", "17 horas", "18 horas", "19 horas", "20 horas", "21 horas", "22 horas", "23 horas",
                "24 horas");
    }
}
