package com.igor.caloriescalculator.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.igor.caloriescalculator.R;
import com.igor.caloriescalculator.adapters.MealListAdapter;
import com.igor.caloriescalculator.fragments.dialogs.DatePickerDialogFragment;
import com.igor.caloriescalculator.fragments.dialogs.StatisticsDialogFragment;
import com.igor.caloriescalculator.fragments.interfaces.SetDateFromFragmentInterface;
import com.igor.caloriescalculator.model.controllers.VisualizationMealsFragmentController;
import com.igor.caloriescalculator.model.entities.Meal;
import com.igor.caloriescalculator.model.repositories.MealRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Igor Guilherme Almeida Rocha
 * Classe que representa a tela de visualização das refeições
 */
public class VisualizationMealsFragment extends Fragment implements SetDateFromFragmentInterface {

    private RecyclerView rvMeals;
    private MealRepository repository;
    private TextView tvInitialDate;
    private TextView tvFinalDate;
    private ImageView ivInitialDatePicker;
    private ImageView ivFinalDatePicker;
    private Button btStatistics;
    private Button btBack;

    private VisualizationMealsFragmentController controller;
    private final Integer ID_INITAL_DATE_PICKER = 1;
    private final Integer ID_FINAL_DATE_PICKER = 2;

    private static final String DATE_PATTERN = "dd/MM/yyyy";

    private List<Meal> meals;

    public VisualizationMealsFragment(){

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(this.repository == null) this.repository = new MealRepository(getContext());
        if(this.meals == null) this.meals = new ArrayList<>();
        if(this.controller == null) this.controller = new VisualizationMealsFragmentController(getContext());

        DatePickerDialogFragment.setInterface(this);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.visualization_meal_fragment, container, false);
        initUIComponents(view);
        configRecyclerView();
        setEvents();
        dataBinding();
        return view;
    }

    /**
     * Inicializa os componentes
     * @param view View inflada
     */
    private void initUIComponents(View view){
        this.rvMeals = view.findViewById(R.id.rv_meals);
        this.ivInitialDatePicker = view.findViewById(R.id.iv_initial_date_picker);
        this.ivFinalDatePicker = view.findViewById(R.id.iv_final_date_picker);
        this.tvInitialDate = view.findViewById(R.id.tv_initial_date);
        this.tvFinalDate = view.findViewById(R.id.tv_final_date);
        this.btStatistics = view.findViewById(R.id.bt_statistics);
        this.btBack = view.findViewById(R.id.bt_back);


    }
    /**
     * Configura a RecyclerView
     */
    private void configRecyclerView(){
        this.rvMeals.setHasFixedSize(true);
        this.rvMeals.setLayoutManager(new LinearLayoutManager(getContext()));
        setRecyclerViewContent(ZonedDateTime.of(LocalDateTime.now().with(LocalTime.MIN), ZoneId.systemDefault()).toEpochSecond(),
                ZonedDateTime.of(LocalDateTime.now().with(LocalTime.MAX), ZoneId.systemDefault()).toEpochSecond() );

    }

    /**
     * Muda o conteudo da RecyclerView
     * @param beginDate data de inicio
     * @param endDate data final
     */
    private void setRecyclerViewContent(long beginDate, long endDate) {
        this.meals = repository.selectAllTodayMeals(beginDate, endDate);
        this.rvMeals.setAdapter(new MealListAdapter(meals));
    }

    /**
     * Seta as datas com o dia de hoje
     */
    private void dataBinding(){
        String today = getToday();
        this.tvInitialDate.setText(today);
        this.tvFinalDate.setText(today);
    }

    /**
     * Seta os eventos
     */
    private void setEvents(){
        this.ivInitialDatePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePicker(ID_INITAL_DATE_PICKER);
            }
        });

        this.ivFinalDatePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePicker(ID_FINAL_DATE_PICKER);
            }
        });

        this.btStatistics.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showStatisticsPopUp();
            }});

        this.btBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().popBackStack();
            }
        });
    }

    /**
     * Mostra o dialog contendo o datepicker
     * @param idDatePicker identificador do datepicker
     */
    private void showDatePicker(int idDatePicker){
        DatePickerDialogFragment datePickerDialogFragment = new DatePickerDialogFragment();
        DatePickerDialogFragment.setInterface(this);
        DatePickerDialogFragment.setChooseDate(idDatePicker);
        datePickerDialogFragment.show(getParentFragmentManager(), datePickerDialogFragment.getTag());
    }

    /**
     * Pega o dia de hoje
     * @return Uma String representando a data de hoje no formato dd/MM/yyyy
     */
    private String getToday(){
       return LocalDate.now().format(DateTimeFormatter.ofPattern(DATE_PATTERN));
    }

    /**
     * Muda a data, este método é chamado quando o usuário seleciona uma data no datepicker
     * @param date nova data
     */
    @Override
    public void setDate(String date) {
        final String EXTRACT_DATE_REGEX = "[0-9]\\s";
        String datePickerId = date.substring(0,1);
        date = date.replaceAll(EXTRACT_DATE_REGEX, "");

        if(datePickerId.equals("1")) this.tvInitialDate.setText(date);
        else this.tvFinalDate.setText(date);

        setRecyclerViewContent(stringDateToLong(this.tvInitialDate.getText().toString(), LocalTime.MIN),
                stringDateToLong(this.tvFinalDate.getText().toString(), LocalTime.MAX));

    }

    /**
     * Transforma a data de String para long
     * @param date Data em String
     * @param time Tempo do dia
     * @return data em long
     */
    private long stringDateToLong(String date, LocalTime time){
        LocalDate formatedDate = LocalDate.parse(date, DateTimeFormatter.ofPattern(DATE_PATTERN));
        return ZonedDateTime.of(LocalDateTime.of(formatedDate,(time)), ZoneId.systemDefault()).toEpochSecond();
    }

    /**
     * Mostra a tela de estatisticas
     */
    private void showStatisticsPopUp(){
        double sum = this.controller.getSum(this.meals);
        long days = calcDiffBetweenDates();
        if(days >= 0) {
            days ++ ;
            double mediaForDay = this.controller.mediaCaloriesForDay(sum, days);
            List<Meal> groupedMeals = this.controller.mediaCaloriesForDayAtMealType(this.meals, mediaForDay);

            DialogFragment dialogFragment = new StatisticsDialogFragment(getContext(), this.tvInitialDate.getText().toString(),
                    this.tvFinalDate.getText().toString(), sum, mediaForDay, groupedMeals);
            dialogFragment.show(getParentFragmentManager(), "dialog_statistics");
        }
    }

    /**
     * Cacula a diferença em dias entre duas datas
     * @return long representando o número de dias entre uma data e outra
     */
    private Long calcDiffBetweenDates(){
         long days = ChronoUnit.DAYS.between(LocalDate.parse(this.tvInitialDate.getText().toString(), DateTimeFormatter.ofPattern("dd/MM/yyyy")),
                LocalDate.parse(this.tvFinalDate.getText().toString(), DateTimeFormatter.ofPattern("dd/MM/yyy")));
        return days;
    }
}
