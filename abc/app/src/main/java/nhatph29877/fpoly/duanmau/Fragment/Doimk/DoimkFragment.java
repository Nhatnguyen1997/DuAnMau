package nhatph29877.fpoly.duanmau.Fragment.Doimk;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import nhatph29877.fpoly.duanmau.R;


public class DoimkFragment extends Fragment {



    public DoimkFragment() {
        // Required empty public constructor
    }


    public static DoimkFragment newInstance() {
        DoimkFragment fragment = new DoimkFragment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_doimk, container, false);
    }
}