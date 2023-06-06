package nhatph29877.fpoly.duanmau.DAO;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import nhatph29877.fpoly.duanmau.Model.Sach;
import nhatph29877.fpoly.duanmau.Model.TopBook;
import nhatph29877.fpoly.duanmau.databace.DBhelper;


public class ThongKeDAO {
    private DBhelper dbHelper;
    private Context context;
    private SQLiteDatabase db;
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

    public ThongKeDAO(Context context) {
        this.context = context;
        dbHelper = new DBhelper(context);
        db = dbHelper.getWritableDatabase();
    }
    @SuppressLint("Range")
    public List<TopBook> getTop(){
        String sqlTop = "SELECT TenS,count(TenS) as soluong FROM phieumuon GROUP BY TenS ORDER BY soluong DESC LIMIT 10";
        List<TopBook> list = new ArrayList<>();
        SachDAO sachDAO = new SachDAO(context);
        Cursor cursor =db.rawQuery(sqlTop,null);
        while (cursor.moveToNext()){
            TopBook topBook = new TopBook();
            Sach sach = sachDAO.GetDSS().get(cursor.getColumnIndex("TenS"));
            topBook.setTensach(sach.getTensach());
            topBook.setSoluong(Integer.parseInt(cursor.getString(cursor.getColumnIndex("soluong"))));
            list.add(topBook);
        }
        return list;
    }
    @SuppressLint("Range")
    public int getDoanhThu(String tuNgay, String denNgay){
        String sqlDoanhThu = "SELECT SUM(Giathue) as doanhThu FROM phieumuon WHERE ngaythue BETWEEN ? AND ?";
        List<Integer> list = new ArrayList<>();
        Cursor cursor = db.rawQuery(sqlDoanhThu,new String[]{tuNgay,denNgay});
        while (cursor.moveToNext()){
            try{
                list.add(Integer.parseInt(cursor.getString(cursor.getColumnIndex("doanhThu"))));
            }catch (Exception e){
                list.add(0);
            }

        }
        return list.get(0);
    }
}
