package nhatph29877.fpoly.duanmau.Fragment.Top10muon;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import nhatph29877.fpoly.duanmau.DAO.ThongKeDAO;
import nhatph29877.fpoly.duanmau.Model.TopBook;
import nhatph29877.fpoly.duanmau.R;


public class Top10muonSachNhieuNhatFragment extends Fragment {
    AdapterTopSach adapterTopSach;
    ArrayList<TopBook> listtop;
    RecyclerView recyclerView;
    ThongKeDAO thongKeDAO;



    public Top10muonSachNhieuNhatFragment() {
        // Required empty public constructor
    }


    public static Top10muonSachNhieuNhatFragment newInstance() {
        Top10muonSachNhieuNhatFragment fragment = new Top10muonSachNhieuNhatFragment();

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

        View view = inflater.inflate(R.layout.fragment_top10muon_sach_nhieu_nhat, container, false);
        recyclerView = view.findViewById(R.id.smnn_recycview);
        thongKeDAO = new ThongKeDAO(getContext());
        listtop = (ArrayList<TopBook>) thongKeDAO.getTop();
        adapterTopSach = new AdapterTopSach(getContext(),listtop);
        recyclerView.setAdapter(adapterTopSach);
        return view;
    }
}