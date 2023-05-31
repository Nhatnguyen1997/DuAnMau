package nhatph29877.fpoly.duanmau.Fragment.quanlyloai;

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
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import nhatph29877.fpoly.duanmau.DAO.LoaiSachDAO;
import nhatph29877.fpoly.duanmau.Model.LoaiSach;
import nhatph29877.fpoly.duanmau.R;


public class QuanLyLoaiFragment extends Fragment {

    RecyclerView recyclerViewls;
    ArrayList<LoaiSach> listloaisach;
    LoaiSachDAO loaiSachDAO;
    AdapterLoaiSach adapterLoaiSach;


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
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerViewls = view.findViewById(R.id.qlls_recycciew);
        FloatingActionButton floatingthemls = view.findViewById(R.id.qls_btnfloatingthemloaisach);
        realoandata();
        floatingthemls.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Themls();
            }
        });
    }

    public void realoandata() {
        loaiSachDAO = new LoaiSachDAO(getContext());
        listloaisach = loaiSachDAO.GetDSLS();
        adapterLoaiSach = new AdapterLoaiSach(listloaisach, getContext(), loaiSachDAO);
        recyclerViewls.setAdapter(adapterLoaiSach);
    }

    @Override
    public void onResume() {
        super.onResume();
        realoandata();
    }

    public void Themls() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_them_loaisach, null, false);
        EditText themtenls = view.findViewById(R.id.dialogls_themtenloaisach);
        Button btthemls = view.findViewById(R.id.dialogls_btnthemloais);
        Button btthemthoatls = view.findViewById(R.id.dialogls_btnthoatthemloais);
        builder.setView(view);
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
        btthemls.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String themten = themtenls.getText().toString();
                if (themtenls.length() == 0) {
                    themtenls.requestFocus();
                    themtenls.setError("Không được để trống tên loại sách");
                } else {
                    LoaiSach loaiSachthem = new LoaiSach(themten);
                    if (loaiSachDAO.ThemLS(loaiSachthem) > 0) {
                        Toast.makeText(getContext(), "Thêm Thành Công", Toast.LENGTH_SHORT).show();
                        realoandata();

                    } else {
                        Toast.makeText(getContext(), "Thêm Thất Bại", Toast.LENGTH_SHORT).show();
                    }
                }
                alertDialog.dismiss();
            }
        });
        btthemthoatls.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });

    }
}