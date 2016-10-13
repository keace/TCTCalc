package ua.kyslytsia.tctcalc.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Calendar;
import java.util.GregorianCalendar;

import ua.kyslytsia.tctcalc.R;

public class TimeFragment extends Fragment {
    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    private static final String ARG_SECTION_NUMBER = "section_number";
    private static final int FRAGMENT_TIME_ID = 1;

    private EditText firstMin, firstSec, firstDec, secondMin, secondSec, secondDec;
    private TextView resultTime;

    private static GregorianCalendar gregorianCalendar;
    private static StringBuffer sb;

    public TimeFragment() {
    }

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static TimeFragment newInstance() {
        TimeFragment fragment = new TimeFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, FRAGMENT_TIME_ID);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_time, container, false);

        firstMin = (EditText) rootView.findViewById(R.id.editTextTimeFirstMin);
        firstSec = (EditText) rootView.findViewById(R.id.editTextTimeFirstSec);
        firstDec = (EditText) rootView.findViewById(R.id.editTextTimeFirstDec);

        secondMin = (EditText) rootView.findViewById(R.id.editTextTimeSecondMin);
        secondSec = (EditText) rootView.findViewById(R.id.editTextTimeSecondSec);
        secondDec = (EditText) rootView.findViewById(R.id.editTextTimeSecondDec);

        resultTime = (TextView) rootView.findViewById(R.id.textViewTimeResult);

        Button buttonResult = (Button) rootView.findViewById(R.id.buttonTimeResult);
        buttonResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timeResult();
            }
        });

        Button buttonCancel = (Button) rootView.findViewById(R.id.buttonTimeCancel);
        buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timeCancel();
            }
        });

        gregorianCalendar = new GregorianCalendar();
        gregorianCalendar.clear();

        sb = new StringBuffer();

        return rootView;
    }

    private void timeResult () {

        // If user not input some values set it to "0"
        if (firstMin.getText().toString().isEmpty()) {
            firstMin.setText("0");
        }
        if (firstSec.getText().toString().isEmpty()) {
            firstSec.setText("0");
        }
        if (firstDec.getText().toString().isEmpty()) {
            firstDec.setText("0");
        }
        if (secondMin.getText().toString().isEmpty()) {
            secondMin.setText("0");
        }
        if (secondSec.getText().toString().isEmpty()) {
            secondSec.setText("0");
        }
        if (secondDec.getText().toString().isEmpty()) {
            secondDec.setText("0");
        }

        // Calculate the result
        gregorianCalendar.set(Calendar.MINUTE, Integer.valueOf(firstMin.getText().toString()));
        gregorianCalendar.set(Calendar.SECOND, Integer.valueOf(firstSec.getText().toString()));
        gregorianCalendar.set(Calendar.MILLISECOND, Integer.valueOf(firstDec.getText().toString()));

        gregorianCalendar.add(Calendar.MILLISECOND, Integer.valueOf(secondDec.getText().toString()));
        gregorianCalendar.add(Calendar.SECOND, Integer.valueOf(secondSec.getText().toString()));
        gregorianCalendar.add(Calendar.MINUTE, Integer.valueOf(secondMin.getText().toString()));

        sb.append(gregorianCalendar.get(Calendar.MINUTE)).append(":").append(gregorianCalendar.get(Calendar.SECOND)).append(":").append(gregorianCalendar.get(Calendar.MILLISECOND));
        resultTime.setText(sb.toString());
        sb.delete(0, sb.length());
    }

    private void timeCancel () {
        firstMin.setText("");
        firstSec.setText("");
        firstDec.setText("");

        secondMin.setText("");
        secondSec.setText("");
        secondDec.setText("");

        firstMin.requestFocus();
    }
}
