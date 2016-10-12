package ua.kyslytsia.tctcalc.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import ua.kyslytsia.tctcalc.R;

public class FragmentTime extends Fragment {
    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    private static final String ARG_SECTION_NUMBER = "section_number";
    private static final int FRAGMENT_TIME_ID = 1;

    public FragmentTime() {
    }

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static FragmentTime newInstance() {
        FragmentTime fragment = new FragmentTime();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, FRAGMENT_TIME_ID);
        fragment.setArguments(args);
        return fragment;
    }
//    public static FragmentTime newInstance(int sectionNumber) {
//        FragmentTime fragment = new FragmentTime();
//        Bundle args = new Bundle();
//        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
//        fragment.setArguments(args);
//        return fragment;
//    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_time, container, false);
        TextView textView = (TextView) rootView.findViewById(R.id.textViewTime);
        textView.setText(getString(R.string.section_format, getArguments().getInt(ARG_SECTION_NUMBER)));
        return rootView;
    }
}
