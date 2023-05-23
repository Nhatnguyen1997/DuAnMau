package nhatph29877.fpoly.duanmau.Model;

public class PhieuMuon {
    private int mapm;
    private String ngaythue;
    private String trangthai;
    private String tentv;
    private String tens;
    private int giathue;

    public PhieuMuon(int mapm, String ngaythue, String trangthai, String tentv, String tens,  int giathue) {
        this.mapm = mapm;
        this.ngaythue = ngaythue;
        this.trangthai = trangthai;
        this.tentv = tentv;
        this.tens = tens;
        this.giathue = giathue;
    }

    public PhieuMuon() {
    }

    public PhieuMuon(String ngaythue, String trangthai, String tentv, String tens,  int giathue) {
        this.ngaythue = ngaythue;
        this.trangthai = trangthai;
        this.tentv = tentv;
        this.tens = tens;
        this.giathue = giathue;
    }

    public PhieuMuon(int mapm, String ngaythue, String trangthai, String tentv, String tens) {
        this.mapm = mapm;
        this.ngaythue = ngaythue;
        this.trangthai = trangthai;
        this.tentv = tentv;
        this.tens = tens;
    }

    public int getMapm() {
        return mapm;
    }

    public void setMapm(int mapm) {
        this.mapm = mapm;
    }

    public String getNgaythue() {
        return ngaythue;
    }

    public void setNgaythue(String ngaythue) {
        this.ngaythue = ngaythue;
    }

    public String getTrangthai() {
        return trangthai;
    }

    public void setTrangthai(String trangthai) {
        this.trangthai = trangthai;
    }

    public String getTentv() {
        return tentv;
    }

    public void setTentv(String tentv) {
        this.tentv = tentv;
    }

    public String getTens() {
        return tens;
    }

    public void setTens(String tens) {
        this.tens = tens;
    }


    public int getGiathue() {
        return giathue;
    }

    public void setGiathue(int giathue) {
        this.giathue = giathue;
    }
}