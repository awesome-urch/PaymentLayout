package com.awesomeurch.paymentlayout.page;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.interpolator.view.animation.FastOutSlowInInterpolator;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import com.awesomeurch.paymentlayout.R;
import com.transitionseverywhere.ChangeText;
import com.transitionseverywhere.Explode;
import com.transitionseverywhere.Transition;
import com.transitionseverywhere.TransitionManager;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    private static String TAG = "MainAkt";
    private Calendar calendar;
    private EditText dateEdit;
    private EditText nameEdit;
    private EditText numberEdit;
    private EditText ccvEdit;
    private TextView nameView,numberView,ccvView,dateView;
    private EditText[] editTexts;
    private TextView[] textViews;
    private DatePickerDialog.OnDateSetListener date;
    private ViewGroup transitionsContainer;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        calendar = Calendar.getInstance();
        dateEdit = findViewById(R.id.date);
        nameEdit = findViewById(R.id.name);
        numberEdit = findViewById(R.id.number);
        ccvEdit = findViewById(R.id.ccv);

        nameView = findViewById(R.id.card_holder);
        numberView = findViewById(R.id.card_number);
        ccvView = findViewById(R.id.card_ccv);
        dateView = findViewById(R.id.card_date);

        editTexts = new EditText[] {dateEdit,nameEdit,numberEdit,ccvEdit};
        textViews = new TextView[] {dateView,nameView,numberView,ccvView};

        transitionsContainer = (ViewGroup) findViewById(R.id.transitions_container);

        for(EditText editText : editTexts){
            editText.addTextChangedListener(textWatcher(editText));
        }

        //getDatePicker().findViewById(Resources.getSystem().getIdentifier("year", "id", "android")).setVisibility(View.GONE);

        date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {

                //view.findViewById(Resources.getSystem().getIdentifier("year", "id", "android")).setVisibility(View.GONE);

                // TODO Auto-generated method stub
                calendar.set(Calendar.YEAR, year);
                calendar.set(Calendar.MONTH, monthOfYear);
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }

        };

        //new DatePickerDialog(getActivity(),android.R.style.Theme_Holo_Dialog, this, "20",month, day);
        //dialog.getDatePicker().findViewById(getResources().getIdentifier("day","id","android")).setVisibility(View.GONE);

        dateEdit.setOnClickListener(popUpCalender);

        /*TextWatcher textWatcher1 = textWatcher(nameEdit);
        nameEdit.addTextChangedListener(textWatcher1);*/

    }

    View.OnClickListener popUpCalender = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            // TODO Auto-generated method stub
            new DatePickerDialog(MainActivity.this, getDate(), getCalendar()
                    .get(Calendar.YEAR), getCalendar().get(Calendar.MONTH),
                    getCalendar().get(Calendar.DAY_OF_MONTH)).show();
        }
    };

    private void updateLabel() {
        //String myFormat = "MM/dd/yy"; //In which you need put here
        String myFormat = "MM/yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        dateEdit.setText(sdf.format(calendar.getTime()));
    }

    private TextWatcher textWatcher(final EditText editText){
        return new TextWatcher() {
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // TODO Auto-generated method stub
                /*if(editText1.length()==1)
                {
                    editText1.clearFocus();
                    editText2.requestFocus();
                    editText2.setCursorVisible(true);
                }*/

                if(editText==numberEdit){
                    Log.d(TAG,"number");
                }else if(editText==dateEdit){
                    Log.d(TAG,"date");
                }else if(editText==nameEdit){
                    Log.d(TAG,"name");

                    Transition explode = new Explode();
                    explode.setDuration(1000);

                    Transition changeText = new ChangeText().setChangeBehavior(ChangeText.CHANGE_BEHAVIOR_OUT_IN);
                    changeText.setInterpolator(new LinearInterpolator());

                    TransitionManager.beginDelayedTransition(transitionsContainer,
                            explode);
                    nameView.setText(String.format("%s",s));

                }else if(editText==ccvEdit){
                    Log.d(TAG,"ccv");
                }

                //Log.d(TAG,s.toString());

            }

            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {

            }

            public void afterTextChanged(Editable s) {

            }
        };
    }


    public Calendar getCalendar() {
        return calendar;
    }

    public void setCalendar(Calendar calendar) {
        this.calendar = calendar;
    }

    public DatePickerDialog.OnDateSetListener getDate() {
        return date;
    }

    public void setDate(DatePickerDialog.OnDateSetListener date) {
        this.date = date;
    }
}
