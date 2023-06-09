package nhatph29877.fpoly.duanmau.Model;

public class Sach {
    private int masach;
    private String tensach;
    private int giasach;
    private String tenls;

    public Sach(int masach, String tensach, int giasach, String tenls) {
        this.masach = masach;
        this.tensach = tensach;
        this.giasach = giasach;
        this.tenls = tenls;
    }

    public Sach(String tensach, int giasach, String tenls) {
        this.tensach = tensach;
        this.giasach = giasach;
        this.tenls = tenls;
    }

    public Sach() {
    }

    public int getMasach() {
        return masach;
    }

    public void setMasach(int masach) {
        this.masach = masach;
    }

    public String getTensach() {
        return tensach;
    }

    public void setTensach(String tensach) {
        this.tensach = tensach;
    }

    public int getGiasach() {
        return giasach;
    }

    public void setGiasach(int giasach) {
        this.giasach = giasach;
    }

    public String getTenls() {
        return tenls;
    }

    public void setTenls(String tenls) {
        this.tenls = tenls;
    }
}
