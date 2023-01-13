package com.igor.caloriescalculator.model.errors;

import android.content.Context;
import android.view.View;

import com.igor.caloriescalculator.model.Util.Utils;

public class RegisterMealValidations {

    private static final String CALORIES_NOT_NULL_OR_0 = "As calories devem ser maiores que 0.";
    private static final String NAME_NOT_BLANK = "Preencha o campo nome.";
    private static final Integer WARNING = 2;


    public static boolean validFields(Context context, String name, String calories, View toastStyle){
        if(name.replaceAll("\\s","").equals("")){
            Utils.showCustomizedToast(context, WARNING, NAME_NOT_BLANK, toastStyle);
            return false;
        }
        try{
            Double.valueOf(calories);
        }catch (NumberFormatException e){
            Utils.showCustomizedToast(context, WARNING, CALORIES_NOT_NULL_OR_0, toastStyle);
            return false;
        }
        return true;
    }



}
