package NguoiDungTaiKhoan;

import CheckConstraint.Check;
import TaiKhoanBanking.TaiKhoan;
import TaiKhoanBanking.TaiKhoanThanhToan;
import TaiKhoanBanking.TaiKhoanTietKiem;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class NguoiDung {
    Check check = new Check();
    Scanner sc = new Scanner(System.in);
    private boolean checkTaiKhoanTietKiem = false;
    private String soDienThoai;
    private String tenNguoiDung;
    private String CCCD;
    private Date ngaySinh;
    ArrayList<TaiKhoan> danhSachTaiKhoan;
    public NguoiDung() {
        danhSachTaiKhoan = new ArrayList<>();
    }

    public NguoiDung(String soDienThoai, String tenNguoiDung, String CCCD, Date ngaySinh) {
        this.soDienThoai = soDienThoai;
        this.tenNguoiDung = tenNguoiDung;
        this.CCCD = CCCD;
        this.ngaySinh = ngaySinh;
        danhSachTaiKhoan = new ArrayList<>();
    }

    public String getSoDienThoai() {
        return soDienThoai;
    }

    public void setSoDienThoai(String soDienThoai) {
        this.soDienThoai = soDienThoai;
    }

    public String getTenNguoiDung() {
        return tenNguoiDung;
    }

    public void setTenNguoiDung(String tenNguoiDung) {
        this.tenNguoiDung = tenNguoiDung;
    }

    public String getCCCD() {
        return CCCD;
    }

    public void setCCCD(String CCCD) {
        this.CCCD = CCCD;
    }

    public Date getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(Date ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public ArrayList<TaiKhoan> getDanhSachTaiKhoan() {
        return danhSachTaiKhoan;
    }

    public void setDanhSachTaiKhoan(ArrayList<TaiKhoan> danhSachTaiKhoan) {
        this.danhSachTaiKhoan = danhSachTaiKhoan;
    }
    public void TaoTaiKhoanTietKiem(TaiKhoanThanhToan taiKhoanThanhToan){
        if(!checkTaiKhoanTietKiem){
            System.out.println("!!Bạn chưa có tài khoản tiết kiệm, muốn tạo ngay không?");
            System.out.print("!!Vui lòng nhập 'Có' hoặc 'Không':");
            do{
                String luaChon = sc.nextLine();
                if(luaChon.equalsIgnoreCase("co")){
                    TaiKhoanTietKiem taiKhoanTietKiem = new TaiKhoanTietKiem();
                    taiKhoanTietKiem  = taiKhoanTietKiem.thayDoiThongTin(taiKhoanThanhToan);
                    if(taiKhoanTietKiem != null){
                        danhSachTaiKhoan.add(taiKhoanTietKiem);
                        checkTaiKhoanTietKiem = true;
                        break;
                    }
                    else{
                        System.out.println("!!Tạo tài khoản tiết kiệm bị lỗi!!");
                    }
                }
                else if(luaChon.equalsIgnoreCase("khong")){
                    break;
                }
                else {
                    System.out.println("!!Vui lòng nhập đúng!!");
                }

            }while(true);
        }
    }

    public void Dangki(QuanLiNguoiDung ql){
        String matKhau;
        int count = 5;
        do{
            System.out.print("=> Xác thực số điện thoại:");
            String soDienThoai = sc.nextLine();
            if(soDienThoai.equals(this.getSoDienThoai())){
                System.out.println("✓ Xác thực thành công ✓");
                do{
                    System.out.print("=> Thiết lập mật khẩu:");
                     matKhau = sc.nextLine();
                    if(check.checkPass(matKhau)){
                        System.out.print("=> Nhập lại mật khẩu:");
                        String matKhauNhacLai = sc.nextLine();
                        if(check.checkPass(matKhauNhacLai)){
                            if(check.checkPassAgain(matKhau, matKhauNhacLai)){
                                System.out.println("✓ Số điện thoại của bạn được chọn làm số tài khoản mặc định ✓");
                                danhSachTaiKhoan.add(new TaiKhoanThanhToan(this.getSoDienThoai(), this.getTenNguoiDung(), matKhau));
                                System.out.println("✓ Mật khẩu tài khoản thanh toán sẽ được đồng bộ cho tài khoản người dùng ✓");
                                System.out.println("!!Vui lòng đăng nhập lại!!\n");
                                break;
                            }
                            else{
                                System.out.println("!!Không trùng khớp!!");
                            }
                        }
                    }
                }while(true);
                break;
            }else{
                System.out.println("!!Số điện thoại không chính xác!!");
                System.out.println("!!Bạn còn "+count-- +"lần xác thực!!");
            }
        }while(count > 0);
        if(count == 0){
            System.out.println("!!Tạo tài khoản thất bại!!");
        }
    }

    @Override
    public String toString() {
        SimpleDateFormat spdf = new SimpleDateFormat("dd/MM/yyyy");
        String ngaySinhStr = spdf.format(this.ngaySinh);
        return this.getSoDienThoai()+", " +this.tenNguoiDung + ", "+this.getCCCD()+", " + ngaySinhStr;
    }
}