package nhatph29877.fpoly.duanmau.Fragment.quanlyloai;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import nhatph29877.fpoly.duanmau.R;


public class QuanLyLoaiFragment extends Fragment {



    public QuanLyLoaiFragment() {
        // Required empty public constructor
    }


    public static QuanLyLoaiFragment newInstance() {
        QuanLyLoaiFragment fragment = new QuanLyLoaiFragment();

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
        return inflater.inflate(R.layout.fragment_quan_ly_loai, container, false);
    }
}