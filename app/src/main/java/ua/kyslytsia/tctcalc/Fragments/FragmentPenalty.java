package ua.kyslytsia.tctcalc.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.GregorianCalendar;

import ua.kyslytsia.tctcalc.R;

public class FragmentPenalty extends Fragment {
    /**
     * The fragment argument representing the section number for this fragment.
     */
    private static final String ARG_SECTION_NUMBER = "section_number";
    private static final int FRAGMENT_PENALTY_ID = 0;

    private static EditText inputMinutes, inputSeconds, inputMillis, inputDistancePenalty, penaltyCost;
    private static GregorianCalendar gregorianCalendar;
    private static TextView timeWithPenalty;

    private static Button buttonTakeResult, buttonClear;

    public FragmentPenalty() {
    }

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static FragmentPenalty newInstance() {
        FragmentPenalty fragment = new FragmentPenalty();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, FRAGMENT_PENALTY_ID);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_penalty, container, false);

        inputMinutes = (EditText) rootView.findViewById(R.id.inputMinutes);
        inputSeconds = (EditText) rootView.findViewById(R.id.inputSeconds);
        inputMillis = (EditText) rootView.findViewById(R.id.inputMillis);
        inputDistancePenalty = (EditText) rootView.findViewById(R.id.inputDistancePenalty);
        penaltyCost = (EditText) rootView.findViewById(R.id.penaltyCost);
        timeWithPenalty = (TextView) rootView.findViewById(R.id.timeWithPenalty);

        buttonTakeResult = (Button) rootView.findViewById(R.id.buttonPenaltyGetResultTime);
        buttonTakeResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                takeResult(v);
            }
        });

        buttonClear = (Button) rootView.findViewById(R.id.buttonPenaltyClear);
        buttonClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearFields(v);
            }
        });

        gregorianCalendar = new GregorianCalendar();
        gregorianCalendar.clear();

        return rootView;
    }

    public void takeResult (View v) {

        // If user not input some values set it to "0"
        if (inputMinutes.getText().toString().isEmpty()) {
            inputMinutes.setText("0");
        }

        if (inputSeconds.getText().toString().isEmpty()) {
            inputSeconds.setText("0");
        }

        if (inputMillis.getText().toString().isEmpty()) {
            inputMillis.setText("0");
        }

        if (inputDistancePenalty.getText().toString().isEmpty()) {
            inputDistancePenalty.setText("0");
        }

        if (penaltyCost.getText().toString().isEmpty()) {
            penaltyCost.setText("0");
        }

        // Calculate the result
        int penaltyTime = Integer.valueOf(inputDistancePenalty.getText().toString()) * Integer.valueOf(penaltyCost.getText().toString());
        gregorianCalendar.set(gregorianCalendar.MINUTE, Integer.valueOf(inputMinutes.getText().toString()));
        gregorianCalendar.set(gregorianCalendar.SECOND, Integer.valueOf(inputSeconds.getText().toString()));
        gregorianCalendar.set(gregorianCalendar.MILLISECOND, Integer.valueOf(inputMillis.getText().toString()));

        gregorianCalendar.add(gregorianCalendar.SECOND, penaltyTime);

        timeWithPenalty.setText(gregorianCalendar.get(gregorianCalendar.MINUTE) + ":" + gregorianCalendar.get(gregorianCalendar.SECOND) + ":" + gregorianCalendar.get(gregorianCalendar.MILLISECOND));
    }

    public void clearFields(View v) {
        inputMinutes.setText("");
        inputSeconds.setText("");
        inputMillis.setText("");
        inputDistancePenalty.setText("");

    }

}