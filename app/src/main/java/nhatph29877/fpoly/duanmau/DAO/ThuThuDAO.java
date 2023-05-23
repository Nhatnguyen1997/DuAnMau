package nhatph29877.fpoly.duanmau.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import nhatph29877.fpoly.duanmau.Model.ThuThu;
import nhatph29877.fpoly.duanmau.databace.DBhelper;


public class ThuThuDAO {
    DBhelper dbHelper;
    public ThuThuDAO(Context context){
        dbHelper = new DBhelper(context);
    }
    public ArrayList<ThuThu> GetDSTT(){
        ArrayList<ThuThu> listtt = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM MaTT",null);
        if(cursor.getCount() > 0){
            cursor.moveToFirst();
            do{
                ThuThu thuThu = new ThuThu();
                thuThu.setMatt(Integer.parseInt(cursor.getString(0)));
                thuThu.setTentt(cursor.getString(1));
                listtt.add(thuThu);
            }while (cursor.moveToNext());
        }
        return  listtt;
    }
    public long ThemTT(ThuThu thuThu){
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("MaTT",thuThu.getMatt());
        contentValues.put("TenTT",thuThu.getTentt());

        return sqLiteDatabase.insert("thuthu",null,contentValues);
    }
    public long SuaTT(ThuThu thuThu){
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("MaTT",thuThu.getMatt());
        contentValues.put("TenTT",thuThu.getTentt());
        return sqLiteDatabase.update("thuthu",contentValues,"MaTT = ?",new String[]{String.valueOf(thuThu.getMatt())});
    }
    public long XoaTT(ThuThu thuThu){
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        return sqLiteDatabase.delete("thuthu","MaTT = ?",new String[]{String.valueOf(thuThu.getMatt())});
    }

}
