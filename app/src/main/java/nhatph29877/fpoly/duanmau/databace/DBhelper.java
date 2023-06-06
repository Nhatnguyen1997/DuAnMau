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
        String bangthanhvien = "CREATE TABLE thanhvien(MaTV integer primary key autoincrement ,TenTV text NOT NULL,CCCD integer UNIQUE NOT NULL)";
        db.execSQL(bangthanhvien);
        String bangloaisach = "CREATE TABLE loaisach(MaLS integer primary key autoincrement,TenLS text UNIQUE NOT NULL)";
        db.execSQL(bangloaisach);
        String bangsach = "CREATE TABLE sach(MaS integer primary key autoincrement,TenS text  NOT NULL,Giathue integer NOT NULL," +
                "TenLS text NOT NULL REFERENCES loaisach(TenLS))";
        db.execSQL(bangsach);
        String bangphieumuon = "CREATE TABLE phieumuon(MaPM integer primary key autoincrement,ngaythue text ,trangthai text ," +
                "TenTV text  REFERENCES thanhvien(TenTV)," +
                "TenS text  REFERENCES sach(TenS)," +
                "Giathue integer  REFERENCES sach(Giathue) ,TenTT text REFERENCES thuthu(TenTT),MaS integer REFERENCES sach(MaS) )";
        db.execSQL(bangphieumuon);
        String bangthuthu = "CREATE TABLE thuthu(MaTT text primary key ,TenTT text  ,matkhau text )";
        db.execSQL(bangthuthu);
        String them = "INSERT INTO thuthu VALUES('admin','ngoctring','1234')";
        db.execSQL(them);

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
