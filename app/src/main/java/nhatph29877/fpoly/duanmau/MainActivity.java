
package nhatph29877.fpoly.duanmau;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import nhatph29877.fpoly.duanmau.Fragment.DoanhThu.DoanhThuFragment;
import nhatph29877.fpoly.duanmau.Fragment.DoiMatKhau.DoiMatKhau;
import nhatph29877.fpoly.duanmau.Fragment.PhieuMuon.PhieuMuonFragment;
import nhatph29877.fpoly.duanmau.Fragment.QuanLySach.QuanLySachFragment;
import nhatph29877.fpoly.duanmau.Fragment.QuanLyThanhVien.QuanLyThanhVienFragment;
import nhatph29877.fpoly.duanmau.Fragment.Top10muon.Top10muonSachNhieuNhatFragment;
import nhatph29877.fpoly.duanmau.Fragment.quanlyloai.QuanLyLoaiFragment;
import nhatph29877.fpoly.duanmau.databace.DBhelper;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.tollbar);
        setSupportActionBar(toolbar);
        drawerLayout = findViewById(R.id.id_drawer);
        ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(MainActivity.this,drawerLayout,toolbar,
                0,0);
        drawerToggle.syncState();
        navigationView =findViewById(R.id.nav);
        navigationView.setNavigationItemSelectedListener(this);
        DBhelper dBhelper=new DBhelper(getApplicationContext());
        dBhelper.getWritableDatabase();
    }
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.phieumuon:
                repleaceFragment(PhieuMuonFragment.newInstance());
                drawerLayout.close();
                Toast.makeText(getApplicationContext(),"phieumuon",Toast.LENGTH_LONG).show();
                break;
            case R.id.quanlyloai:
                repleaceFragment(QuanLyLoaiFragment.newInstance());
                drawerLayout.close();
                Toast.makeText(getApplicationContext(),"quan lý loại",Toast.LENGTH_LONG).show();
                break;
            case R.id.quanlysach:
                repleaceFragment(QuanLySachFragment.newInstance());
                drawerLayout.close();
                Toast.makeText(getApplicationContext(),"quan ly sách",Toast.LENGTH_LONG).show();
                break;
            case R.id.quanly:
                repleaceFragment(QuanLyThanhVienFragment.newInstance());
                drawerLayout.close();
                Toast.makeText(getApplicationContext(),"quan lý thanh viên",Toast.LENGTH_LONG).show();
                break;
            case R.id.thongke:
                repleaceFragment(Top10muonSachNhieuNhatFragment.newInstance());
                drawerLayout.close();
                Toast.makeText(getApplicationContext(),"top 10",Toast.LENGTH_LONG).show();
                break;
            case R.id.doanhthu:
                repleaceFragment(DoanhThuFragment.newInstance());
                drawerLayout.close();
                Toast.makeText(getApplicationContext(),"doanh thu",Toast.LENGTH_LONG).show();
                break;
            case R.id.add_user:
                setTitle("Thêm Thành Viên");
                repleaceFragment(new QuanLyThanhVienFragment());
                drawerLayout.close();
                break;
            case R.id.change_password:
                setTitle("Đổi Mật Khẩu");
                repleaceFragment(new DoiMatKhau());
                drawerLayout.close();
                break;
            case R.id.log_out:
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(MainActivity.this);
                alertDialog.setMessage("Bạn có muốn đăng xuất hay không");
                alertDialog.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                alertDialog.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                        MainActivity.super.onBackPressed();
                    }
                });
                alertDialog.show();


                break;

        }
        return true;
    }
    public void repleaceFragment(Fragment fragment){
        FragmentTransaction transaction1 =getSupportFragmentManager().beginTransaction();
        transaction1.replace(R.id.layout_conten, fragment);
        transaction1.commit();
    }

    @Override
    public void onBackPressed() {
        if(drawerLayout.isOpen()){
            drawerLayout.close();
        }else{
            super.onBackPressed();
        }

    }
}