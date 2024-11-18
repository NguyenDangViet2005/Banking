package TaiKhoanBanking;
import CheckConstraint.Check;
import DichVuOnline.dichVuTaiKhoan;
import Interface.DichVu;
import NguoiDungTaiKhoan.NguoiDung;
import NguoiDungTaiKhoan.QuanLiNguoiDung;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class TaiKhoanThanhToan extends TaiKhoan {
    private double phiThuongNien = 60000;
    private boolean checkPhiTN = false;
    private double hanMucChuyenTien = 10000000;
    private boolean khoaTheTinDung = false;

    public TaiKhoanThanhToan(String soTaiKhoan, String tenChuTk, String matKhau) {
        super(soTaiKhoan, tenChuTk, matKhau, 0);
    }

    public TaiKhoanThanhToan() {
    }

    public double getPhiThuongNien() {
        return phiThuongNien;
    }

    public void setPhiThuongNien(double phiThuongNien) {
        this.phiThuongNien = phiThuongNien;
    }

    public double getHanMucChuyenTien() {
        return hanMucChuyenTien;
    }

    public void setHanMucChuyenTien(double hanMucChuyenTien) {
        this.hanMucChuyenTien = hanMucChuyenTien;
    }

    public boolean isKhoaTheTinDung() {
        return khoaTheTinDung;
    }

    public void setKhoaTheTinDung(boolean khoaTheTinDung) {
        this.khoaTheTinDung = khoaTheTinDung;
    }

    public void RutTien(NguoiDung nguoiDung) {
        if(this.isKhoaTheTinDung()){
            System.out.println("!! Thẻ tín dụng đã bị khóa !!");
            return;
        }
        else {
            boolean checkTKTD = false;
            System.out.print("=> Nhập số tiền muốn rút: ");
            double soTien = Double.parseDouble(sc.nextLine());
            if (soTien <= 0) {
                System.out.println("!!Số tiền rút phải lớn hơn 0!!");
            }
            if (soTien > this.getSoDuTk()) {
                for (TaiKhoan taiKhoan : nguoiDung.getDanhSachTaiKhoan()) {
                    if (taiKhoan instanceof TaiKhoanTinDung) {
                        checkTKTD = true;
                        TaiKhoanTinDung tk = (TaiKhoanTinDung) taiKhoan;
                        if (soTien > this.getSoDuTk() + tk.getHanMucThauChi()) {
                            System.out.println("!!Số tiền rút vượt quá số dư và hạn mức thấu chi cho phép!!");
                            break;
                        }
                        do {
                            System.out.println("!!Số tiền vượt quá số dư hiện tại của bạn!!");
                            System.out.println("!!Bạn có muốn sử dụng thêm tiền thấu chi để thanh toán!!");
                            System.out.print("!!Vui lòng nhập 'Có' hoặc 'Không':");
                            String luachon = sc.nextLine();
                            if (luachon.equalsIgnoreCase("co")) {
                                if (check.CheckOTP()) {
                                    tk.setSoTienThauChiDaSuDung(soTien - this.getSoDuTk());
                                    this.setSoDuTk(0);
                                    tk.setHanMucThauChi(tk.getHanMucThauChi() - tk.getSoTienThauChiDaSuDung());
                                    double tienLai = tk.getSoTienThauChiDaSuDung() * tk.getLaiSuatThauChi() / 100;
                                    lichSuGiaoDich.addFirst(tgianNow + ": Đã rút ra " + soTien + "VNĐ bao gồm " +
                                            this.getSoDuTk() + "VNĐ trong tài khoản và " + tk.getSoTienThauChiDaSuDung() + "VNĐ hạn mức đã thấu chi, số dư tài khoản hiện tại: "+
                                            this.getSoDuTk() +"VNĐ !!");
                                    System.out.println("!!Đã thấu chi " + tk.getSoTienThauChiDaSuDung() + "VNĐ với lãi suất " + tk.getLaiSuatThauChi() + "% !!");
                                    System.out.println("!!Số tiền lãi trên khoản thấu chi: " + tienLai + "VNĐ !!");
                                    System.out.println("✓ Giao dịch thành công ✓");
                                    break;
                                } else {
                                    System.out.println("!!OTP không khả dụng!!");
                                    break;
                                }


                            } else if (luachon.equalsIgnoreCase("khong")) {
                                break;
                            }
                        } while (true);

                    }
                }
                if (!checkTKTD) {
                    System.out.println("!!Giao dịch thất bại do số tiền rút vượt quá số dư TK của bạn!!");
                    System.out.println("!!Bạn có muốn tạo thêm tài khoản tín dụng để có thêm hạn mức thấu chi không?");
                    do {
                        System.out.print("!!Vui lòng nhập 'Có' hoặc 'Không':");
                        String luachon = sc.nextLine();
                        if (luachon.equalsIgnoreCase("co")) {
                            this.taoTaiKhoanTinDung(nguoiDung);
                            System.out.println("!!Vui lòng thực hiện lại giao dịch!!");
                            break;
                        } else if (luachon.equalsIgnoreCase("khong")) {
                            break;
                        }
                    } while (true);
                }
            }
            if (soTien <= this.getSoDuTk()) {
                if (check.CheckOTP()) {
                    System.out.println("✓ Rút tiền thành công ✓");
                    this.setSoDuTk(this.getSoDuTk() - soTien);
                    lichSuGiaoDich.addFirst(tgianNow + ": Đã rút ra " + soTien + "VNĐ, số dư còn lại: "+ this.getSoDuTk() +"VNĐ !!");
                } else {
                    System.out.println("!!OTP không khả dụng!!");
                }
            }
        }
    }

    @Override
    public void NapTien() {
        if(this.isKhoaTheTinDung()){
            System.out.println("!! Thẻ tín dụng đã bị khóa !!");
        }
        else{
            super.NapTien();
            if (this.getSoDuTk() >= phiThuongNien && !checkPhiTN) {
                System.out.println("!!Tài khoản của bạn sẽ bị trừ phí thường niên (60.000VNĐ)!!");
                this.setSoDuTk(this.getSoDuTk() - phiThuongNien);
                checkPhiTN = true;
            }
        }
    }

    @Override
    public void DoiMatKhau() {
        super.DoiMatKhau();
    }

    public void ChuyenTien(NguoiDung nguoiDung, QuanLiNguoiDung quanLiNguoiDung) {
        String sdtChuyenTien;
        do {
            System.out.print("=> Nhập vào số điện thoại cần chuyển:");
            sdtChuyenTien = sc.nextLine();
        }
        while (!check.checkSDT(sdtChuyenTien));
        if (quanLiNguoiDung.timNguoiDung(sdtChuyenTien) != null) {
            NguoiDung nguoiNhan = quanLiNguoiDung.timNguoiDung(sdtChuyenTien);
            if (sdtChuyenTien.equals(this.getSoTaiKhoan())) {
                System.out.println("!!Đây là số điện thoại của bạn mà :(((");
                return;
            }

            if (nguoiNhan.getDanhSachTaiKhoan().isEmpty())
                System.out.println("!!Người nhận tạm thời chưa có tài khoản thanh toán!!");
            else {
                System.out.println("||Người nhận :" + nguoiNhan.getTenNguoiDung().toUpperCase());
                System.out.println("1.Xác nhận\n2.Hủy");
                int chon;
                do {
                    System.out.print("=> Nhập lựa chọn:");
                    chon = sc.nextInt();
                    if (chon == 1) {
                        NoiDungChuyenKhoan(nguoiDung, nguoiNhan);
                        break;
                    }
                } while (chon != 2);
            }
        } else
            System.out.println("!!Người dùng không tồn tại!!");

    }

    public void NoiDungChuyenKhoan(NguoiDung nguoiDung, NguoiDung nguoiNhan) {
        do {
            System.out.print("=> Nhập vào số tiền cần chuyển:");
            double tienChuyen = sc.nextDouble();
            sc.nextLine();
            if (tienChuyen > 0 && tienChuyen < getSoDuTk() && tienChuyen < this.getHanMucChuyenTien()) {
                System.out.println("===== KIỂM TRA THÔNG TIN GIAO DỊCH =====");
                System.out.println("Vui lòng xác nhận lại chi tiết chuyển tiền của bạn:");
                System.out.println("Tên người nhận: " + nguoiNhan.getTenNguoiDung());
                System.out.println("Số tiền cần chuyển: " + tienChuyen + " VND");
                System.out.println("========================================");
                System.out.println("\nBạn có chắc chắn muốn thực hiện giao dịch này không?");
                System.out.println("1. Xác nhận giao dịch");
                System.out.println("2. Hủy giao dịch");
                System.out.print("Lựa chọn của bạn: ");
                int choice;
                try {
                    choice = sc.nextInt();
                    sc.nextLine();
                } catch (Exception e) {
                    System.out.println("!!Thao tác lỗi, vui lòng thực hiện lại!!");
                    sc.nextLine();
                    return;
                }
                if (choice == 1) {
                    Boolean checkGD = check.CheckOTP();
                    if (checkGD) {
                        System.out.println("✓ Giao dịch thành công ✓");
                        System.out.println("!!Tài khoản -" + tienChuyen);
                        this.setSoDuTk(this.getSoDuTk() - tienChuyen);
                        nguoiNhan.getDanhSachTaiKhoan().get(0).setSoDuTk(nguoiNhan.getDanhSachTaiKhoan().get(0).getSoDuTk() + tienChuyen);
                        lichSuGiaoDich.addFirst(tgianNow + ": Bạn đã chuyển số tiền " + tienChuyen + " cho người dùng " +
                                nguoiNhan.getTenNguoiDung() + "(" + nguoiNhan.getSoDienThoai() + "), số dư còn lại: "+this.getSoDuTk()+"VNĐ !!");
                        nguoiNhan.getDanhSachTaiKhoan().get(0).getLichSuGiaoDich().addFirst(tgianNow + ": " + this.getTenChuTk()
                                + "(" + this.getSoTaiKhoan() + ") đã chuyển cho bạn " + tienChuyen + ", số dư còn lại: "+nguoiNhan.getDanhSachTaiKhoan().get(0).getSoDuTk()+"VNĐ !!");
                        break;
                    }
                } else return;
            }
            if (tienChuyen < this.getSoDuTk() && tienChuyen > this.getHanMucChuyenTien()) {
                System.out.println("!!Quá hạn mức cho phép, không thể thực hiện giao dịch!!");
                return;
            } else if (tienChuyen < 0) System.out.println("!!Tiền chuyển phải lớn hơn 0!!");
            else if (tienChuyen > this.getSoDuTk()) {
                boolean checkTk = false;
                for (TaiKhoan tk : nguoiDung.getDanhSachTaiKhoan()) {
                    if (tk instanceof TaiKhoanTinDung) {
                        checkTk = true;
                        if (tienChuyen > this.getHanMucChuyenTien()) {
                            System.out.println("!!Quá hạn mức cho phép, không thể thực hiện giao dịch!!");
                            return;
                        }
                        TaiKhoanTinDung taiKhoan = (TaiKhoanTinDung) tk;
                        System.out.println("!!Số dư tài khoản hiện không đủ, bạn có muốn sử dụng thêm hạn mức thấu chi để hoàn thành giao dịch không?");
                        do {
                            System.out.print("!!Vui lòng nhập 'Có' hoặc 'Không':");
                            String luachon = sc.nextLine();
                            if (luachon.equalsIgnoreCase("co")) {
                                if (tienChuyen > taiKhoan.getHanMucThauChi() + this.getSoDuTk()) {
                                    System.out.println("!!Số tiền chuyển vượt quá số dư khả dụng cho phép!!");
                                    return;
                                }
                                System.out.println("===== KIỂM TRA THÔNG TIN GIAO DỊCH =====");
                                System.out.println("Vui lòng xác nhận lại chi tiết chuyển tiền của bạn:");
                                System.out.println("Tên người nhận: " + nguoiNhan.getTenNguoiDung());
                                System.out.println("Số tiền cần chuyển: " + tienChuyen + " VND");
                                System.out.println("========================================");
                                System.out.println("\nBạn có chắc chắn muốn thực hiện giao dịch này không?");
                                System.out.println("1. Xác nhận giao dịch");
                                System.out.println("2. Hủy giao dịch");
                                System.out.print("Lựa chọn của bạn: ");

                                int choice;
                                try {
                                    choice = sc.nextInt();
                                    sc.nextLine();
                                } catch (Exception e) {
                                    System.out.println("!!Thao tác lỗi, vui lòng thực hiện lại!!");
                                    sc.nextLine();
                                    return;
                                }
                                if (choice == 1) {
                                    Boolean checkGD = check.CheckOTP();
                                    if (checkGD) {
                                        System.out.println("✓ Giao dịch thành công ✓");
                                        taiKhoan.setSoTienThauChiDaSuDung(tienChuyen - this.getSoDuTk());
                                        this.setSoDuTk(0);
                                        lichSuGiaoDich.addFirst(tgianNow + ": Đã chuyển " + tienChuyen + "VNĐ bao gồm " + this.getSoDuTk() + "VNĐ trong tài khoản và "
                                                + taiKhoan.getSoTienThauChiDaSuDung() + "VNĐ hạn mức đã thấu chi!! cho " + nguoiNhan.getTenNguoiDung() + "(" + nguoiNhan.getSoDienThoai()
                                                + "), số dư còn lại: "+this.getSoDuTk()+"VNĐ !!");
                                        nguoiNhan.getDanhSachTaiKhoan().get(0).setSoDuTk(nguoiNhan.getDanhSachTaiKhoan().get(0).getSoDuTk() + tienChuyen);
                                        nguoiNhan.getDanhSachTaiKhoan().get(0).getLichSuGiaoDich().addFirst(tgianNow + ": " + this.getTenChuTk() + "(" + this.getSoTaiKhoan()
                                                + ") đã chuyển cho bạn " + tienChuyen + ", số dư còn lại: "+nguoiNhan.getDanhSachTaiKhoan().get(0).getSoDuTk()+"VNĐ !!");
                                        taiKhoan.setHanMucThauChi(taiKhoan.getHanMucThauChi() - taiKhoan.getSoTienThauChiDaSuDung());
                                        double tienLai = taiKhoan.getSoTienThauChiDaSuDung() * taiKhoan.getLaiSuatThauChi() / 100;
                                        System.out.println("!!Đã thấu chi " + taiKhoan.getSoTienThauChiDaSuDung() + "VNĐ với lãi suất " + taiKhoan.getLaiSuatThauChi() + "% !!");
                                        System.out.println("!!Số tiền lãi trên khoản thấu chi: " + tienLai + "VNĐ !!");
                                        System.out.println("✓ Giao dịch thành công ✓");
                                        return;
                                    } else {
                                        System.out.println("!!OTP không khả dụng!!");
                                        break;
                                    }
                                } else return;


                            } else if (luachon.equalsIgnoreCase("khong")) {
                                return;
                            }
                        } while (true);
                    }
                }
                if (!checkTk) {
                    System.out.println("!!Giao dịch thất bại do số tiền rút vượt quá số dư TK của bạn!!");
                    System.out.println("!!Bạn có muốn tạo thêm tài khoản tín dụng để có thêm hạn mức thấu chi không?");
                    do {
                        System.out.print("!!Vui lòng nhập 'Có' hoặc 'Không':");
                        String luachon = sc.nextLine();
                        if (luachon.equalsIgnoreCase("co")) {
                            this.taoTaiKhoanTinDung(nguoiDung);
                            System.out.println("!!Vui lòng thực hiện lại giao dịch!!");
                            return;
                        } else if (luachon.equalsIgnoreCase("khong")) {
                            return;
                        }
                    } while (true);
                }
            } else if (tienChuyen > this.getHanMucChuyenTien())
                System.out.println("!!Tiền chuyển phải nhỏ hơn hạn múc chuyển tiền (" + this.getHanMucChuyenTien() + ")");
        } while (true);

    }
    public void taoTaiKhoanTinDung(NguoiDung nguoiDung) {
        System.out.println("!!Bạn có muốn giữ lại mật khẩu của tài khoản thanh toán để sử dụng cho tài khoản tín dụng không?");
        do {
            System.out.print("!!Vui lòng nhập 'Có' hoặc 'Không':");
            String luachon = sc.nextLine();
            if (luachon.equalsIgnoreCase("co")) {
                nguoiDung.getDanhSachTaiKhoan().add(new TaiKhoanTinDung(this.getSoTaiKhoan(), this.getTenChuTk(), this.getMatKhau()));
                System.out.println("✓ Tạo tài khoản tín dụng thành công ✓");
                break;
            } else if (luachon.equalsIgnoreCase("khong")) {
                String mkNew;
                do {
                    System.out.print("=> Thiết lập mật khẩu cho tài khoản tín dụng: ");
                    mkNew = sc.nextLine();
                } while (!check.checkPass(mkNew));
                nguoiDung.getDanhSachTaiKhoan().add(new TaiKhoanTinDung(this.getSoTaiKhoan(), this.getTenChuTk(), mkNew));
                System.out.println("✓ Tạo tài khoản tín dụng thành công ✓");
                break;
            }
        } while (true);
    }

    public void doiHanMucChuyenTien() {
        System.out.println("!!Bạn muốn cập nhật hạn mức chuyển tiền?");
        System.out.println("1. Cập nhật\n0. Không");
        System.out.println("!!Lưu ý: Nếu không cập nhật thì hạn mức chuyển tiền của bạn vẫn giữ nguyên 10.000.000VNĐ/ 1 ngày");
        int luachon;
        do {
            System.out.print("=> Nhập lựa chọn: ");
            luachon = sc.nextInt();

            if (luachon == 1) {
                System.out.print("=> Nhập hạn mức chuyển tiền mới: ");
                double hanMucNew = sc.nextDouble();
                setHanMucChuyenTien(hanMucNew);
                System.out.println("✓ Thay đổi thành công ✓");
                break;
            }
        }
        while (luachon != 0);
    }

    public void NapTienVaoDienThoai() {
        System.out.println("=====================================================");
        System.out.println("!!Bạn muốn nạp vào điện thoại với mệnh giá bao nhiêu?");
        System.out.println("10.000VNĐ\n20.000VNĐ\n50.000VNĐ\n100.000VNĐ\n200.000VNĐ\n500.000VNĐ");
        int luachon;
        do {
            System.out.print("=> Số tiền: ");
            luachon = sc.nextInt();
            String soDienThoai;
            sc.nextLine();
            if (luachon == 10000 || luachon == 20000 || luachon == 50000 || luachon == 100000 || luachon == 200000 || luachon == 500000) {
                System.out.println("!!Mệnh giá " + luachon + " VNĐ!!");
                do {
                    System.out.print("=> Nhập số điện thoại bạn muốn nạp tiền: ");
                    soDienThoai = sc.nextLine();
                } while (!check.checkSDT(soDienThoai));
                if (this.getSoDuTk() > luachon) {
                    if (check.CheckOTP()) {
                        System.out.println("✓ Giao dịch thành công ✓");
                        System.out.println(soDienThoai + " vừa được nạp " + luachon + "VNĐ");
                        this.setSoDuTk(this.getSoDuTk() - luachon);
                        lichSuGiaoDich.addFirst(tgianNow+ ": Thanh toán thẻ cào mệnh giá: "+luachon+ "VNĐ, số dư hiên tại: "+this.getSoDuTk());
                        break;
                    } else System.out.println("Sai OTP!");
                } else {
                    System.out.println("!!Số dư không đủ!!");
                    break;
                }
            } else System.out.println("!!Không có mệnh giá này");
        } while (true);
        System.out.println("=====================================================");
    }

    @Override
    public String toString() {
        return "Chủ tài khoản: " + this.getTenChuTk().toUpperCase() + " \nSố tài khoản: ****" + this.getSoTaiKhoan().substring(3) + "\nSố dư: " + this.getSoDuTk();
    }

    public void khoaThe(){
        sc.nextLine();
        if(this.isKhoaTheTinDung()){
            System.out.println("!! Thẻ tín dụng của bạn hiện tại đang bị khóa !!");
            System.out.println("!! Bạn có muốn mở lại không !!");
            String lc;
            do{
                System.out.println("!! Vui lòng nhập 'có' hoặc 'không': ");
                lc = sc.nextLine();
                if(lc.equalsIgnoreCase("co")){
                    this.setKhoaTheTinDung(false);
                    System.out.println("✓ Mở khóa thẻ tín dụng thành công ✓");
                    break;
                }
                else if(lc.equalsIgnoreCase("khong")){
                    break;
                }
            }while(true);
        }
        else{
            System.out.println("!! Nếu khóa thẻ tin dụng sẽ không thể nạp tiền và rút tiền !!");
            System.out.println("!! Bạn có chắc không?");
            String lc;
            do{
                System.out.println("!! Vui lòng nhập 'có' hoặc 'không': ");
                lc = sc.nextLine();
                if(lc.equalsIgnoreCase("co")){
                    this.setKhoaTheTinDung(true);
                    System.out.println("✓ Khóa thẻ tín dụng thành công ✓");
                    break;
                }
                else if(lc.equalsIgnoreCase("khong")){
                    break;
                }
            }while(true);
        }
    }
    public void XemLichSu(){
        if (lichSuGiaoDich.isEmpty())
            System.out.println("!!Chưa có lịch sử giao dịch nào :(((");
        else {
            System.out.println("=======Lịch sử giao dịch của bạn=======");
            for (String x : this.getLichSuGiaoDich()) {
                System.out.println(x);
            }
            System.out.println("=======================================");
        }
        System.out.println("1. In sao kê giao dịch");
        System.out.println("0. Trờ về");
        do{
            try{
                System.out.print("=> Nhập lựa chon: ");
                int luachon = sc.nextInt();
                if(luachon == 1){
                    this.GhiSaoKe();
                    break;
                }
                else if(luachon == 0){
                    break;
                }
            }catch(IOException e) {
                e.printStackTrace();
            }
        }while(true);
    }
    @Override
    public void XemThongTin() {
        int chon;
        do {
            System.out.println("=========THÔNG TIN TÀI KHOẢN===========");
            System.out.println(this);
            if (checkPhiTN) System.out.println("✓ Phí thường niên đã thanh toán ✓");
            else System.out.println("!! Phí thường niên chưa thanh toán !!");
            System.out.println("========================================");
            System.out.println("1. Thay đổi mật khẩu");
            System.out.println("2. Lịch sử giao dịch");
            System.out.println("3. Cập nhật hạn mức chuyển tiền");
            System.out.println("4. Khóa thẻ tín dụng");
            System.out.println("0. Trở về");
            System.out.print("Vui lòng chọn một chức năng: ");
            chon = sc.nextInt();
            switch (chon) {
                case 1 -> {
                    this.DoiMatKhau();
                    tapToContinue();
                }
                case 2 -> {
                    this.XemLichSu();
                }
                case 3 -> {
                    this.doiHanMucChuyenTien();
                    tapToContinue();
                }
                case 4 -> {
                    this.khoaThe();
                    tapToContinue();
                }

            }

        } while (chon != 0) ;
    }
    public void taiKhoanTinDung(NguoiDung nguoiDung){
        boolean check = false;
        Check c = new Check();
        for(TaiKhoan taiKhoan : nguoiDung.getDanhSachTaiKhoan()){
            if(taiKhoan instanceof TaiKhoanTinDung) {
                check = true;
                String pass;
                do{
                    System.out.print("=> Nhập mật khẩu để vào tài khoản tín dụng: ");
                    pass = sc.nextLine();
                }while(!c.checkPass(pass));
                if(pass.equals(taiKhoan.getMatKhau())){
                    if(((TaiKhoanTinDung) taiKhoan).getSoTienThauChiDaSuDung()>0){
                        int lua;
                        do{
                            System.out.println("!! Vui lòng thanh toán số nợ của bạn !!");
                            double tienNo = ((TaiKhoanTinDung) taiKhoan).getSoTienThauChiDaSuDung()+((TaiKhoanTinDung) taiKhoan).getSoTienThauChiDaSuDung()*((TaiKhoanTinDung) taiKhoan).getLaiSuatThauChi()/100;
                            System.out.println("!!Bạn còn nợ "+tienNo+"VNĐ");
                            System.out.println("1. Trả nợ ngay");
                            System.out.println("2. Để sau");
                            System.out.print("=> Nhập lựa chọn: ");
                            lua = sc.nextInt();
                            if(lua == 1){
                                if(this.getSoDuTk() < tienNo){
                                    System.out.println("!! Số dư hiện tại không đủ !!");
                                }
                                else{
                                    this.setSoDuTk(this.getSoDuTk() - tienNo);
                                    ((TaiKhoanTinDung) taiKhoan).setHanMucThauChi( ((TaiKhoanTinDung) taiKhoan).getHanMucThauChi() + ((TaiKhoanTinDung) taiKhoan).getSoTienThauChiDaSuDung() );
                                    ((TaiKhoanTinDung) taiKhoan).setSoTienThauChiDaSuDung(0);
                                    System.out.println("✓ Thanh toán thành công ✓");
                                    ((TaiKhoanTinDung)taiKhoan).XemThongTin();
                                }
                            }
                            else if(lua == 2){
                                ((TaiKhoanTinDung)taiKhoan).XemThongTin();
                                break;
                            }


                        }while(lua != 1);
                    }
                    else{
                        taiKhoan.XemThongTin();
                    }

                }
                else{
                    System.out.println("!!Nhập sai mật khẩu!!");
                }
            }
        }
        if(!check){
            System.out.println("!!Bạn chưa có tài khoản tín dụng nào!!");
            System.out.println("!!Bạn có muốn tạo thêm tài khoản tín dụng để có thêm hạn mức thấu chi không?");
            do{
                System.out.print("!!Vui lòng nhập 'Có' hoặc 'Không':");
                String chonlua = sc.nextLine();
                if(chonlua.equalsIgnoreCase("co")){
                    taoTaiKhoanTinDung(nguoiDung);
                    break;
                }
                else if(chonlua.equalsIgnoreCase("khong")){
                    break;
                }
            }while(true);

        }
    }
    public void taiKhoanTK(NguoiDung nguoiDung){
        boolean check = false;
        Check c = new Check();
        for(TaiKhoan taiKhoan : nguoiDung.getDanhSachTaiKhoan()){
            if(taiKhoan instanceof TaiKhoanTietKiem) {
                check = true;
                String pass;
                do{
                    System.out.print("=> Nhập mật khẩu để vào tài khoản tiết kiệm: ");
                    pass = sc.nextLine();
                }while(!c.checkPass(pass));
                if(pass.equals(taiKhoan.getMatKhau())){
                    ((TaiKhoanTietKiem) taiKhoan).ChucNangTaiKhoanTietKiem(this);
                }
                else{
                    System.out.println("!!Nhập sai mật khẩu!!");
                }
            }
        }
        if(!check){
            nguoiDung.TaoTaiKhoanTietKiem(this);
        }
    }
    public void GhiSaoKe() throws IOException {
        if(this.getLichSuGiaoDich() != null){
            String title = "===== SAO KÊ GIAO DỊCH =====";
            String name = "Tên chủ tài khoản: "+this.getTenChuTk();
            String stk = "Số tài khoản: "+this.getSoTaiKhoan();
            File file = new File("FileRead\\SaoKe.txt");
            FileWriter fw = new FileWriter(file);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(title);
            bw.write("\n");
            bw.write(name);
            bw.write("\n");
            bw.write(stk);
            bw.write("\n");
            for(String x : this.getLichSuGiaoDich()){
                bw.write(x);
                bw.newLine();
            }
            System.out.println("Ghi sao kê thành công!");
            bw.close();
            fw.close();
        }
        else{
            System.out.println("!! Lịch sử giao dịch trống !!");
        }
    }

    public void ChucNangTaiKhoanThanhToan(NguoiDung nguoiDung, QuanLiNguoiDung quanLiNguoiDung){
        int luaChon;
            do{
                System.out.println("--------------------------------");
                System.out.println("===== TÀI KHOẢN THANH TOÁN =====");
                System.out.println("1. Thông tin tài khoản");
                System.out.println("2. Thực hiện chuyển tiền");
                System.out.println("3. Rút tiền từ tài khoản");
                System.out.println("4. Nạp tiền vào tài khoản");
                System.out.println("5. Quản lý tài khoản tiết kiệm");
                System.out.println("6. Tài khoản tín dụng");
                System.out.println("7. Nạp tiền điện thoại");
                System.out.println("8. Liên hệ hỗ trợ");
                System.out.println("9. Dịch vụ...");
                System.out.println("0. Đăng xuất");
                System.out.println("================================");
                System.out.print("Vui lòng chọn một chức năng: ");
                luaChon = sc.nextInt();
                sc.nextLine();
                switch (luaChon){
                    case 1:{
                        this.XemThongTin();
                        break;
                    }
                    case 2:{
                        ChuyenTien(nguoiDung, quanLiNguoiDung);
                        tapToContinue();
                        break;
                    }
                    case 3:{
                        this.RutTien(nguoiDung);
                        tapToContinue();
                        break;
                    }
                    case 4:{
                        this.NapTien();
                        tapToContinue();
                        break;
                    }
                    case 5:{
                        this.taiKhoanTK(nguoiDung);
                        break;
                    }
                    case 6:{
                        this.taiKhoanTinDung(nguoiDung);
                        tapToContinue();
                        break;
                    }
                    case 7:{
                        this.NapTienVaoDienThoai();
                        tapToContinue();
                        break;
                    }
                    case 8:{
                        super.thongTinLienHe();
                        tapToContinue();
                        break;
                    }
                    case 9:{
                        dichVuTaiKhoan dv = new dichVuTaiKhoan();
                        dv.cacDichVu(nguoiDung);
                        break;
                    }
                }
            }while(luaChon != 0);

    }

}
