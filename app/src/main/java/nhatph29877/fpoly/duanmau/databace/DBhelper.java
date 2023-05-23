package nhatph29877.fpoly.duanmau.databace;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBhelper  extends SQLiteOpenHelper {
    public DBhelper(Context context) {
        super(context, "data", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String thuthu = "CREATE TABLE Thuthu(matt INTEGER PRIMARY KEY AUTOINCREMENT," +
                " matkhau TEXT," +
                " hoten TEXT)";
        db.execSQL(thuthu);


        String thanhvien = "CREATE TABLE thanhvien(matv INTEGER PRIMARY KEY AUTOINCREMENT," +
                " tentv TEXT," +
                " macancuoc INTEGER)";
        db.execSQL(thanhvien);


        String loaisach = "CREATE TABLE loaisach(maloai INTEGER PRIMARY KEY AUTOINCREMENT," +
                " tenloai TEXT)";
        db.execSQL(loaisach);

        String sach = "CREATE TABLE sach(masach INTEGER PRIMARY KEY AUTOINCREMENT," +
                " tensach TEXT," +
                "giamuon TEXT NOT NULL,"+
                "maloai INTEGER NOT NULL REFERENCES loaisach(maloai))";
        db.execSQL(sach);
        String phieumuon = "CREATE TABLE phieumuon(maphieumuon INTEGER PRIMARY KEY AUTOINCREMENT," +
                " matv INTEGER NOT NULL REFERENCES thanhvien(matv)," +
                "masach INTEGER NOT NULL REFERENCES sach(masach),"+
                "maloai INTEGER NOT NULL REFERENCES loaisach(maloai))";
        db.execSQL(phieumuon);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS phieumuon");
        db.execSQL("DROP TABLE IF EXISTS thanhvien");
        db.execSQL("DROP TABLE IF EXISTS loaisach");
        db.execSQL("DROP TABLE IF EXISTS masach");
        db.execSQL("DROP TABLE IF EXISTS Thuthu");
    }
}
