package nhatph29877.fpoly.duanmau.Fragment.QuanLySach;

import android.app.AlertDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.HashMap;

import nhatph29877.fpoly.duanmau.DAO.LoaiSachDAO;
import nhatph29877.fpoly.duanmau.DAO.SachDAO;
import nhatph29877.fpoly.duanmau.Model.LoaiSach;
import nhatph29877.fpoly.duanmau.Model.Sach;
import nhatph29877.fpoly.duanmau.R;


public class QuanLySachFragment extends Fragment {
    RecyclerView recyclerViews;
    ArrayList<Sach> lists;
    SachDAO sachDAO;
    LoaiSachDAO loaiSachDAO;
    AdapterSach adapterSach;
    ArrayList<HashMap<String, Object>> listspinnerls;


    public QuanLySachFragment() {
        // Required empty public constructor
    }


    public static QuanLySachFragment newInstance() {
        QuanLySachFragment fragment = new QuanLySachFragment();

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
        View view = inflater.inflate(R.layout.fragment_quan_ly_sach, container, false);
        recyclerViews = view.findViewById(R.id.qls_recycview);
        FloatingActionButton floatingthemsach = view.findViewById(R.id.qls_btnfloatingsach);
        sachDAO = new SachDAO(getContext());
        realoaddatasach();
        floatingthemsach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ThemSach();
            }
        });
        return view;
         }
    public ArrayList<HashMap<String, Object>> GetdataSpinnerloaisach() {
        loaiSachDAO = new LoaiSachDAO(getContext());
        ArrayList<LoaiSach> listls = loaiSachDAO.GetDSLS();
        listspinnerls = new ArrayList<>();
        for (LoaiSach loaiSach : listls) {
            HashMap<String, Object> hashMap = new HashMap<>();
            hashMap.put("TenLS", loaiSach.getTenls());
            listspinnerls.add(hashMap);
        }
        return listspinnerls;
    }

    public void realoaddatasach() {

        lists = sachDAO.GetDSS();
        adapterSach = new AdapterSach(lists, getContext(), sachDAO, GetdataSpinnerloaisach());
        recyclerViews.setAdapter(adapterSach);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    public void ThemSach() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        LayoutInflater layoutInflater = getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.dialog_them_sach, null);
        EditText themtensach = view.findViewById(R.id.dialogs_tensach);
        EditText themgiasach = view.findViewById(R.id.dialogs_giathues);
        Spinner themloaisach = view.findViewById(R.id.dialogs_loaisach);
        Button btnthemsach = view.findViewById(R.id.dialogs_btnthems);
        Button btnthemthoatsach = view.findViewById(R.id.dialogs_btnthoats);
        builder.setView(view);
        SimpleAdapter adapter = new SimpleAdapter(getContext(), GetdataSpinnerloaisach(),
                android.R.layout.simple_list_item_1, new String[]{"TenLS"}, new int[]{android.R.id.text1});
        themloaisach.setAdapter(adapter);
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
        btnthemsach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String themten = themtensach.getText().toString();
                int themgia = Integer.parseInt(themgiasach.getText().toString());
                if (themtensach.length() == 0) {
                    themtensach.requestFocus();
                    themtensach.setError("Không bỏ trống tên sách");
                } else if (themgiasach.length() == 0) {
                    themgiasach.requestFocus();
                    themgiasach.setError("Không bỏ trống giá sách");
                } else {
                    HashMap<String, Object> loaisachspinner = (HashMap<String, Object>) themloaisach.getSelectedItem();
                    String tenls = (String) loaisachspinner.get("TenLS");
                    Sach themsach = new Sach(themten, themgia, tenls);
                    if (sachDAO.ThemSach(themsach) > 0) {
                        Toast.makeText(getContext(), "Thêm Thành Công", Toast.LENGTH_SHORT).show();
                        realoaddatasach();
                    } else {
                        Toast.makeText(getContext(), "Thêm Thất Bại", Toast.LENGTH_SHORT).show();
                    }
                }
                alertDialog.dismiss();
            }
        });

        btnthemthoatsach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });


    }
}