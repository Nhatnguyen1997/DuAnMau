package nhatph29877.fpoly.duanmau.Fragment.PhieuMuon;

import static android.content.Context.MODE_PRIVATE;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

import nhatph29877.fpoly.duanmau.DAO.PhieuMuonDAO;
import nhatph29877.fpoly.duanmau.DAO.SachDAO;
import nhatph29877.fpoly.duanmau.DAO.ThanhVienDAO;
import nhatph29877.fpoly.duanmau.Model.PhieuMuon;
import nhatph29877.fpoly.duanmau.Model.Sach;
import nhatph29877.fpoly.duanmau.Model.ThanhVien;
import nhatph29877.fpoly.duanmau.R;


public class PhieuMuonFragment extends Fragment {

    RecyclerView recyclerView;
    ArrayList<PhieuMuon> listpm;
    PhieuMuonDAO phieuMuonDAO;
    SachDAO sachDAO;
    ThanhVienDAO thanhVienDAO;
    AdapterPhieuMuon adapterPhieuMuon;
    ArrayList<HashMap<String, Object>> listspinnertv;
    ArrayList<HashMap<String, Object>> listspinnertensach;
    SharedPreferences.Editor editor;
    SharedPreferences sharedPreferences;

    public PhieuMuonFragment() {
        // Required empty public constructor
    }


    public static PhieuMuonFragment newInstance() {
        PhieuMuonFragment fragment = new PhieuMuonFragment();

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
        View view = inflater.inflate(R.layout.fragment_phieu_muon3, container, false);
        recyclerView = view.findViewById(R.id.qlpm_recycview);
        realoandata();
        FloatingActionButton addphieumuon = view.findViewById(R.id.qlpm_btnfloatingthemphieumuon);
        addphieumuon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ThemPhieuMuon();
            }
        });
        return view;

    }
    @Override
    public void onResume() {
        realoandata();
        super.onResume();
    }

    public ArrayList<HashMap<String,Object>> Spinnertentv(){
        thanhVienDAO = new ThanhVienDAO(getContext());
        ArrayList<ThanhVien> listthanhvien = thanhVienDAO.GetDSTV();
        listspinnertv = new ArrayList<>();
        for(ThanhVien thanhVien: listthanhvien){
            HashMap<String,Object> hashMap = new HashMap<>();
            hashMap.put("TenTV",thanhVien.getTentv());
            listspinnertv.add(hashMap);
        }
        return listspinnertv;
    }

    public ArrayList<HashMap<String,Object>> Spinnertensach(){
        sachDAO = new SachDAO(getContext());
        ArrayList<Sach> listsach = sachDAO.GetDSS();
        listspinnertensach = new ArrayList<>();
        for(Sach sach: listsach){
            HashMap<String,Object> hashMap = new HashMap<>();
            hashMap.put("TenS",sach.getTensach());
            listspinnertensach.add(hashMap);
        }
        return listspinnertensach;
    }
    public void realoandata(){
        phieuMuonDAO = new PhieuMuonDAO(getContext());
        listpm = phieuMuonDAO.GetDSPM();
        adapterPhieuMuon = new AdapterPhieuMuon(listpm,phieuMuonDAO,getContext(),Spinnertentv(),Spinnertensach());
        recyclerView.setAdapter(adapterPhieuMuon);
    }
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
    public void ThemPhieuMuon() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.dialogpm_themphieumuon, null);
        Spinner themtentv = view.findViewById(R.id.dialogpm_themtentv);
        Spinner themtensach = view.findViewById(R.id.dialogpm_themtensach);
        EditText themgiasach = view.findViewById(R.id.dialogpm_themgiathue);
        EditText themngaythue = view.findViewById(R.id.dialogpm_themngaythue);
        CheckBox themtrangthai = view.findViewById(R.id.dialogpm_themtrangthai);
        Button themphieumuon = view.findViewById(R.id.dialogpm_btnthempm);
        Button themthoatphieumuon = view.findViewById(R.id.dialogpm_btnthoatthempm);
        initPreferences();
        Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);
        themngaythue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        month = month + 1;
                        String date = dayOfMonth + "/" + month + "/" + year;
                        themngaythue.setText(date);
                    }
                }, year, month, day);
                datePickerDialog.show();
            }
        });
        builder.setView(view);
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
        SimpleAdapter adapterthemten = new SimpleAdapter(getContext(), listspinnertv, android.R.layout.simple_list_item_1,
                new String[]{"TenTV"}, new int[]{android.R.id.text1});
        themtentv.setAdapter(adapterthemten);
        SimpleAdapter adapterthemsach = new SimpleAdapter(getContext(), listspinnertensach, android.R.layout.simple_list_item_1,
                new String[]{"TenS"}, new int[]{android.R.id.text1});
        themtensach.setAdapter(adapterthemsach);

        themphieumuon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences = getContext().getSharedPreferences("DATA", MODE_PRIVATE);
                String tentt = sharedPreferences.getString("DATATEN", "");
                String trangthai = "";
                if (themtrangthai.isChecked() == true) {
                    trangthai = "Đã Trả Sách";

                } else if (themtrangthai.isChecked() == false) {
                    trangthai = "Chưa Trả Sách";

                }
                String themgaythuesach = themngaythue.getText().toString();
                String giasach = themgiasach.getText().toString();
                if (themngaythue.length() == 0) {
                    themngaythue.requestFocus();
                    themngaythue.setError("Không bỏ trống ngày thuê");
                } else {
                    HashMap<String, Object> chontentv = (HashMap<String, Object>) themtentv.getSelectedItem();
                    HashMap<String, Object> chontensach = (HashMap<String, Object>) themtensach.getSelectedItem();
                    String tentv = (String) chontentv.get("TenTV");
                    String tensach = (String) chontensach.get("TenS");
                    PhieuMuon themphieumuon = new PhieuMuon(themgaythuesach, trangthai, tentv, tensach, Integer.parseInt(giasach),tentt);
                    if (phieuMuonDAO.Thempm(themphieumuon) > 0) {
                        Toast.makeText(getContext(), "Thêm Thành Công", Toast.LENGTH_SHORT).show();
                        realoandata();
                        alertDialog.dismiss();
                    } else {
                        Toast.makeText(getContext(), "Thêm Thất Bại", Toast.LENGTH_SHORT).show();
                    }


                }
            }
        });
    }
    private void initPreferences() {

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        editor = sharedPreferences.edit();
    }
}