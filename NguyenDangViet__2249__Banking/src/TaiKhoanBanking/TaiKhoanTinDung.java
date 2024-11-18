package TaiKhoanBanking;

public class TaiKhoanTinDung extends TaiKhoan{
    private double hanMucThauChi = 10000000;
    private double laiSuatThauChi = 8;
    private double soTienThauChiDaSuDung ;
    public TaiKhoanTinDung() {}

    public TaiKhoanTinDung(String soTaiKhoan, String tenChuTk, String matKhau) {
        super(soTaiKhoan, tenChuTk, matKhau);
    }
    public double getHanMucThauChi() {
        return hanMucThauChi;
    }

    public void setHanMucThauChi(double hanMucThauChi) {
        this.hanMucThauChi = hanMucThauChi;
    }

    public double getLaiSuatThauChi() {
        return laiSuatThauChi;
    }

    public void setLaiSuatThauChi(double laiSuatThauChi) {
        this.laiSuatThauChi = laiSuatThauChi;
    }

    public double getSoTienThauChiDaSuDung() {
        return soTienThauChiDaSuDung;
    }

    public void setSoTienThauChiDaSuDung(double soTienThauChiDaSuDung) {
        this.soTienThauChiDaSuDung = soTienThauChiDaSuDung;
    }

    @Override
    public void XemThongTin() {
        System.out.println("=======THÔNG TIN TÀI KHOẢN TÍN DỤNG=======");
        System.out.println("Người dùng: "+this.getTenChuTk());
        System.out.println("Số tài khoản: " + this.getSoTaiKhoan());
        System.out.println("Hạn mức thấu chi cho phép: 10.000.000VNĐ");
        System.out.println("Số tiền thấu chi đã sử dụng: "+this.getSoTienThauChiDaSuDung());
        System.out.println("Hạn mức thấu chi còn lại: " + this.getHanMucThauChi());
        System.out.println("==========================================");
    }
}
