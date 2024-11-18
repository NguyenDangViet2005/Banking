package TaiKhoanBanking;

import CheckConstraint.Check;
import Interface.DichVu;
import NguoiDungTaiKhoan.NguoiDung;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

public  class TaiKhoan implements DichVu {
    Check check = new Check();
    private String SoTaiKhoan, tenChuTk, matKhau;
    private double soDuTk;
    Scanner sc = new Scanner(System.in);
    LinkedList<String> lichSuGiaoDich = new LinkedList<>();
    DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
    String tgianNow = LocalDateTime.now().format(dateFormat);
    public TaiKhoan() {};
    public TaiKhoan(String soTaiKhoan, String tenChuTk, String matKhau, double soDuTk) {
        SoTaiKhoan = soTaiKhoan;
        this.tenChuTk = tenChuTk;
        this.matKhau = matKhau;
        this.soDuTk = soDuTk;
    }
    public TaiKhoan(String soTaiKhoan, String tenChuTk, String matKhau) {
        SoTaiKhoan = soTaiKhoan;
        this.tenChuTk = tenChuTk;
        this.matKhau = matKhau;
    }

    public String getSoTaiKhoan() {
        return SoTaiKhoan;
    }

    public void setSoTaiKhoan(String soTaiKhoan) {
        SoTaiKhoan = soTaiKhoan;
    }

    public String getTenChuTk() {
        return tenChuTk;
    }

    public void setTenChuTk(String tenChuTk) {
        this.tenChuTk = tenChuTk;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }

    public double getSoDuTk() {
        return soDuTk;
    }

    public void setSoDuTk(double soDuTk) {
        this.soDuTk = soDuTk;
    }

    public LinkedList<String> getLichSuGiaoDich() {
        return lichSuGiaoDich;
    }

    public void setLichSuGiaoDich(LinkedList<String> lichSuGiaoDich) {
        this.lichSuGiaoDich = lichSuGiaoDich;
    }

    public void NapTien(){
        System.out.print("=> Nhập số tiền nạp (số tiền phải lớn hơn hoặc bằng 10.000VNĐ): ");
        double tienNap = sc.nextDouble();
        sc.nextLine();
        if(tienNap >= 10000 ){
            System.out.println("✓ Nạp tiền thành công ✓");
            this.setSoDuTk(this.soDuTk+tienNap);
            lichSuGiaoDich.addFirst(tgianNow + ": Đã nạp vào "+tienNap+"VNĐ, số dư còn lại: "+this.getSoDuTk()+"VNĐ !!");
        }
        else{
            System.out.println("!!Không khả dụng!!");
        }
    }
    public void DoiMatKhau(){
        int count = 5;
        sc.nextLine();
        do{
            System.out.print("=> Nhập vào mật khẩu cũ:");
            String matKhauCu = sc.nextLine();
            if(matKhauCu.equals(this.getMatKhau())){
                System.out.print("=> Nhập mật khẩu mới:");
                String matKhauMoi = sc.nextLine();
                if(check.checkPass(matKhauMoi)){
                    this.setMatKhau(matKhauMoi);
                    System.out.println("✓ Đổi mật khẩu thành công ✓");
                    break;
                }
            }
            else{
                System.out.println("!!Nhập sai mật khẩu cũ!!");
                System.out.println("!!Bạn còn "+count-- +" lần nhập!!");
            }

        }while(count>0);
        if (count == 0) {
            System.out.println("!!Đổi mật khẩu thất bại!!");
            System.out.println("1. Quên mật khẩu\n0. Thoát");
            int luaChon ;
            do{
               try{
                   System.out.print("=> Nhập lựa chọn của bạn: ");
                   luaChon = sc.nextInt();
                   if(luaChon == 1){
                       if(check.CheckOTP()){
                           sc.nextLine();
                           System.out.print("=> Nhập mật khẩu mới:");
                           String matKhauMoi = sc.nextLine();
                           if(check.checkPass(matKhauMoi)){
                               System.out.println("✓ Đổi mật khẩu thành công ✓");
                               this.setMatKhau(matKhauMoi);
                               break;
                           }
                       }

                       else {
                           System.out.println("!!Otp không khả dụng!!");
                           return;
                       }
                   }
                   else if(luaChon == 0){
                       break;
                   }
               }
               catch(Exception e){
                   System.out.println("!!Nhập lỗi!!");
               }
            }while(true);
        }
    }

    @Override
    public String toString() {
        return "TaiKhoan:" +   "SoTaiKhoan: " + SoTaiKhoan.substring(3) +   ", tenChuTk: " + tenChuTk +    ", soDuTk: " + soDuTk;
    }

    @Override
    public void XemThongTin() {
    }

    @Override
    public void thongTinLienHe() {
        System.out.println("=====THÔNG TIN LIÊN HỆ HỖ TRỢ=====:");
        System.out.println("Điện thoại hỗ trợ: 1900-1234");
        System.out.println("Email hỗ trợ: nguyenDangVietcompanyBankingGroup@gmail.com");
        System.out.println("Website hỗ trợ: www.DangVietBankCompany.com/support");
        System.out.println("Giờ làm việc: Thứ 2 đến Thứ 6, từ 8:00 sáng đến 6:00 chiều");
    }

    @Override
    public void cacDichVu( NguoiDung nd) {

    }

    public  void tapToContinue(){
        Scanner sc = new Scanner(System.in);
        System.out.println("=> Nhấn phím ENTER để tiếp tục");
        sc.nextLine();
    }
}