package com.igor.caloriescalculator.fragments.dialogs;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.igor.caloriescalculator.R;
import com.igor.caloriescalculator.fragments.interfaces.SetDateFromFragmentInterface;

/**
 * @author Igor Guilherme Almeida Rocha
 * Esta classe representa o fragment em que o datepicker é apresentado.
 */
public class DatePickerDialogFragment extends DialogFragment {

    private static SetDateFromFragmentInterface setDateInterface;
    private static Integer chooseDatePicker;
    private DatePicker dpInjestionDate;
    private String previusDate;


    public DatePickerDialogFragment(){

    }

    public DatePickerDialogFragment(String previusDate){
        this.previusDate = previusDate;
        chooseDatePicker = null;
        setDateInterface = null;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.date_picker_fragment, container, false);
        initializeUiComponents(view);
        dataBinding();
        setEvents();
        return view;
    }

    /**
     * Inicializa os componentes
     * @param view View inflada
     */
    private void initializeUiComponents(View view){
        this.dpInjestionDate = view.findViewById(R.id.dp_injestion_date);
    }

    /**
     * Seta o valor das datas com base na data anterior, caso houver
     */
    private void dataBinding(){

        if(this.previusDate != null){
            int day = Integer.valueOf(this.previusDate.substring(0,2));
            int month = Integer.valueOf(this.previusDate.substring(3,5));
            int year = Integer.valueOf(this.previusDate.substring(6));
            this.dpInjestionDate.updateDate(year, month - 1, day);
        }
    }

    /**
     * Seta os eventos
     */
    private void setEvents(){
        this.dpInjestionDate.setOnDateChangedListener(new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
              selectDateAndCloseDialog(year, monthOfYear, dayOfMonth);
            }
        });
    }

    /**
     * Seleciona a data e fecha a janela do dilog.
     * @param year Ano
     * @param monthOfYear Mês
     * @param dayOfMonth Dia
     */
    private void selectDateAndCloseDialog( int year, int monthOfYear, int dayOfMonth ){
        String strMonth;
        String strDay;
        if(monthOfYear < 10) strMonth = "0" + (monthOfYear + 1);
        else strMonth = "" + (monthOfYear + 1);
        if(dayOfMonth < 10) strDay = "0" + (dayOfMonth);
        else strDay = "" + dayOfMonth;

        if(DatePickerDialogFragment.chooseDatePicker == null){
            setDateInterface.setDate(strDay + "/" + strMonth + "/" + year);
        }else{
            setDateInterface.setDate(chooseDatePicker + " " + strDay + "/" + strMonth + "/" + year);
        }

        this.dismiss();
    }

    /**
     * @see SetDateFromFragmentInterface
     * Seta uma interface, a classe que implementará esta interface vai ter o seu campo de data modificado.
     * @param setDateFromFragmentInterface classe que implmenta a interface setDateFromFramentIterface
     */
    public static void setInterface(SetDateFromFragmentInterface setDateFromFragmentInterface){
        DatePickerDialogFragment.setDateInterface = setDateFromFragmentInterface;

    }

    /**
     * Caso a classe que "chamar" este dialog, tenha mais de um campo de data, precisamos saber qual deles modificar
     * @param idDatePicker identificador do objeto que "chamou" este dialog
     */
    public static void setChooseDate(int idDatePicker){
        DatePickerDialogFragment.chooseDatePicker = idDatePicker;
    }


}
