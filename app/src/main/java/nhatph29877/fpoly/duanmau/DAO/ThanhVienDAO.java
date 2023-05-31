package nhatph29877.fpoly.duanmau.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import nhatph29877.fpoly.duanmau.Model.ThanhVien;
import nhatph29877.fpoly.duanmau.databace.DBhelper;


public class ThanhVienDAO {
    DBhelper dbHelper;

    public ThanhVienDAO(Context context) {
        dbHelper = new DBhelper(context);
    }

    public ArrayList<ThanhVien> GetDSTV() {
        ArrayList<ThanhVien> listtv = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM thanhvien", null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            do {
                ThanhVien thanhVien = new ThanhVien();
                thanhVien.setMatv(Integer.parseInt(cursor.getString(0)));
                thanhVien.setTentv(cursor.getString(1));
                thanhVien.setCccd(cursor.getString(2));
                listtv.add(thanhVien);

            } while (cursor.moveToNext());
        }
        return listtv;

    }

    public long ThemTV(ThanhVien thanhVien) {
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("TenTV", thanhVien.getTentv());
        contentValues.put("CCCD", thanhVien.getCccd());
        return sqLiteDatabase.insert("thanhvien", null, contentValues);
    }

    public long SuaTv(ThanhVien thanhVien) {
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("TenTV", thanhVien.getTentv());
        contentValues.put("macancuoc", thanhVien.getCccd());

        return sqLiteDatabase.update("thanhvien", contentValues, "MaTV = ?", new String[]{String.valueOf(thanhVien.getMatv())});
    }
    public long XoaTv(int matv ){
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        return sqLiteDatabase.delete("thanhvien","MaTV = ?",new String[]{String.valueOf(matv)});
    }

}
