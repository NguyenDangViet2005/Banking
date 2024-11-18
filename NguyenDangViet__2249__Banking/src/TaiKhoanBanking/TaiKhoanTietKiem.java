package TaiKhoanBanking;

import NguoiDungTaiKhoan.NguoiDung;

import java.util.ArrayList;
import java.util.LinkedList;

public class TaiKhoanTietKiem extends TaiKhoan{
    private int thoiGianKihan, kiHan;
    private double LaiSuat , soDuTietKiem;
    LinkedList<String> lichSuTietKiem = new LinkedList<>();
    public TaiKhoanTietKiem(String soTaiKhoan, String tenChuTk, String matKhau, int thoiGianKihan, int kiHan, double laiSuat) {
        super(soTaiKhoan, tenChuTk, matKhau);
        this.thoiGianKihan = thoiGianKihan;
        this.kiHan = kiHan;
        this.LaiSuat = laiSuat;
    }
    public TaiKhoanTietKiem(){
        super();
    }

    public int getThoiGianKihan() {
        return thoiGianKihan;
    }

    public void setThoiGianKihan(int thoiGianKihan) {
        this.thoiGianKihan = thoiGianKihan;
    }

    public int getKiHan() {
        return kiHan;
    }

    public void setKiHan(int kiHan) {
        this.kiHan = kiHan;
    }

    public double getLaiSuat() {
        return LaiSuat;
    }

    public void setLaiSuat(double laiSuat) {
        this.LaiSuat = laiSuat;
    }

    public double getSoDuTietKiem() {
        return soDuTietKiem;
    }

    public void setSoDuTietKiem(double soDuTietKiem) {
        this.soDuTietKiem = soDuTietKiem;
    }

    public LinkedList<String> getLichSuTietKiem() {
        return lichSuTietKiem;
    }

    public void setLichSuTietKiem(LinkedList<String> lichSuTietKiem) {
        this.lichSuTietKiem = lichSuTietKiem;
    }

    public void NapTien(TaiKhoanThanhToan taiKhoanThanhToan) {
        System.out.println("!!Nạp tiền vào khoản tiết kiệm!!\n!!Số dư tài khoản thanh toán của bạn hiện còn ("+taiKhoanThanhToan.getSoDuTk()+")!!");
        System.out.print("=> Nhập số tiền muốn nạp: ");
        double tienNap = sc.nextDouble();
        sc.nextLine();
        if(tienNap > 0 && tienNap <= taiKhoanThanhToan.getSoDuTk()){
            if(check.CheckOTP()){
                System.out.println("✓ Nạp tiền thành công ✓");
                this.setSoDuTietKiem(this.getSoDuTietKiem()+tienNap);
                taiKhoanThanhToan.setSoDuTk(taiKhoanThanhToan.getSoDuTk()-tienNap);
                lichSuTietKiem.addFirst(tgianNow+ ": Đã nạp vào sổ tiết kiệm "+tienNap+"VNĐ, số dư tiết kiệm còn lại: "+this.getSoDuTietKiem()+"VNĐ !!");
            }
            else System.out.println("!!Otp không khả dụng!!");
        }
        else{
            System.out.println("!!Không khả dụng!!");
        }
    }


    public void RutTien(TaiKhoanThanhToan taiKhoanThanhToan) {
        System.out.println("!!Rút về tài khoản thanh toán của bạn!!");
        String luaChon;
        do{
            System.out.print("!!Vui lòng nhập 'Có' hoặc 'Không':");
            luaChon = sc.nextLine();
            if(luaChon.equalsIgnoreCase("co")){
                System.out.print("!!Nhập số tiên muốn rút: (Phải ít hơn số tiền hiện còn trong tài khoản tiết kiệm("+this.getSoDuTietKiem()+")):");
                double tienRut = sc.nextDouble();
                sc.nextLine();
                if(tienRut <= this.getSoDuTietKiem()){
                    if(check.CheckOTP()){
                        System.out.println("✓ Rút tiền thành công ✓");
                        this.setSoDuTietKiem(this.getSoDuTietKiem()-tienRut);
                        taiKhoanThanhToan.setSoDuTk(taiKhoanThanhToan.getSoDuTk()+tienRut);
                        lichSuTietKiem.addFirst(tgianNow + ": Đã rút "+tienRut+"VNĐ ra khỏi tài khoản tiết kiệm, số dư tiết kiệm còn lại: "+this.getSoDuTietKiem()+"VNĐ !!");
                        break;
                    }
                    else System.out.println("!!Otp không khả dụng!!");
                }
                else{
                    System.out.println("!!Không khả dụng!!");
                }
            }
            else if(luaChon.equalsIgnoreCase("khong")){
                break;
            }
            else{
                System.out.println("!!Vui lòng nhập đúng!!");
            }

        }while (true);


    }

    public void luaChonKiHan(){
        System.out.println("!!Bạn muốn tiết kiệm với kì hạn bao lâu?");
        do{
            System.out.println("1. Kỳ hạn theo tháng");
            System.out.println("2. Kỳ hạn theo năm");
            System.out.print("=> Mời đưa ra lựa chọn: ");
            this.setKiHan(sc.nextInt());
            if(this.getKiHan() != 1 && this.getKiHan() != 2){
                System.out.println("!!Vui lòng chọn đúng!!");
            }
            else{
                if(this.getKiHan() == 1){
                    System.out.println("✓ Quý khách đã chọn gửi tiết kiệm theo tháng ✓");
                    System.out.println("| 6 tháng          : 4.5% lãi suất |");
                    System.out.println("| 9 tháng          : 6.5% lãi suất |");
                    System.out.println("| 12 tháng         : 7.0% lãi suất |");
                    System.out.println("| 24 tháng         : 7.5% lãi suất |");
                    System.out.println("| 36 tháng         : 8.0% lãi suất |");
                    System.out.println("| 48 tháng         : 8.5% lãi suất |");
                    System.out.println("| 60 tháng trở lên : 9.0% lãi suất |");
                    do{
                        System.out.print("=> Nhập vào số tháng (phải hơn 6 tháng):");
                        this.setThoiGianKihan(sc.nextInt());
                        if(this.getThoiGianKihan() >= 6){
                            System.out.println("✓ Đã ghi tiết kiệm của bạn ✓");
                            if(this.getThoiGianKihan() >= 6 && this.getThoiGianKihan() < 9){
                                this.setLaiSuat(4.5);
                                System.out.println("✓ Tiết kiệm của bạn có kì hạn "+this.getThoiGianKihan()+" tháng với " +this.getLaiSuat()+ "% lãi suất ✓");
                                break;
                            }
                            if(this.getThoiGianKihan() >= 9 && this.getThoiGianKihan() < 12){
                                this.setLaiSuat(6.5);
                                System.out.println("✓ Tiết kiệm của bạn có kì hạn "+this.getThoiGianKihan()+" tháng với " +this.getLaiSuat()+ "% lãi suất ✓");
                                break;
                            }
                            if(this.getThoiGianKihan() >= 12 && this.getThoiGianKihan() < 24){
                                this.setLaiSuat(7);
                                System.out.println("✓ Tiết kiệm của bạn có kì hạn "+this.getThoiGianKihan()+" tháng với " +this.getLaiSuat()+ "% lãi suất ✓");
                                break;
                            }
                            if(this.getThoiGianKihan() >= 24 && this.getThoiGianKihan() < 36){
                                this.setLaiSuat(7.5);
                                System.out.println("✓ Tiết kiệm của bạn có kì hạn "+this.getThoiGianKihan()+" thángvới " +this.getLaiSuat()+ "% lãi suất ✓");
                                break;
                            }
                            if(this.getThoiGianKihan() >= 36 && this.getThoiGianKihan() < 48){
                                this.setLaiSuat(8);
                                System.out.println("✓ Tiết kiệm của bạn có kì hạn "+this.getThoiGianKihan()+" tháng với " +this.getLaiSuat()+ "% lãi suất ✓");
                                break;
                            }
                            if(this.getThoiGianKihan() >= 48 && this.getThoiGianKihan() < 60){
                                this.setLaiSuat(8.5);
                                System.out.println("✓ Tiết kiệm của bạn có kì hạn "+this.getThoiGianKihan()+" tháng với " +this.getLaiSuat()+ "% lãi suất ✓");
                                break;
                            }
                            if(this.getThoiGianKihan() >= 60){
                                this.setLaiSuat(9);
                                System.out.println("✓ Tiết kiệm của bạn có kì hạn "+this.getThoiGianKihan()+" tháng với " +this.getLaiSuat()+ "% lãi suất ✓");
                                break;
                            }

                        }
                        else System.out.println("!!Lỗi, Phải nhập kì hạn từ 6 tháng trở lên!!");
                    }while(true);
                }
                else{
                    System.out.println("✓ Quý khách đã chọn gửi tiết kiệm theo năm ✓");
                    System.out.println("| 1 năm          : 7.2% lãi suất |");
                    System.out.println("| 2 năm          : 7.7% lãi suất |");
                    System.out.println("| 3 năm          : 8.2% lãi suất |");
                    System.out.println("| 4 năm          : 8.7% lãi suất |");
                    System.out.println("| 5 năm trở lên  : 8.0% lãi suất |");
                    do{
                        System.out.print("=> Nhập vào số năm (phải hơn 1 năm):");
                        this.setThoiGianKihan(sc.nextInt());
                        if(this.getThoiGianKihan() >= 1){
                            System.out.println("✓ Đã ghi tiết kiệm của bạn ✓");
                            if(this.getThoiGianKihan() == 1) {
                                this.setLaiSuat(7.2);
                                System.out.println("✓ Tiết kiệm của bạn có kì hạn "+this.getThoiGianKihan()+" năm với "+this.getLaiSuat()+"% lãi suất ✓");
                                break;
                            }
                            if(this.getThoiGianKihan() == 2) {
                                this.setLaiSuat(7.7);
                                System.out.println("✓ Tiết kiệm của bạn có kì hạn "+this.getThoiGianKihan()+" năm với "+this.getLaiSuat()+"% lãi suất ✓");
                                break;
                            }
                            if(this.getThoiGianKihan() == 3) {
                                this.setLaiSuat(8.2);
                                System.out.println("✓ Tiết kiệm của bạn có kì hạn "+this.getThoiGianKihan()+" năm  với "+this.getLaiSuat()+"% lãi suất ✓");
                                break;
                            }
                            if(this.getThoiGianKihan() == 4) {
                                this.setLaiSuat(8.7);
                                System.out.println("✓ Tiết kiệm của bạn có kì hạn "+this.getThoiGianKihan()+" năm  với "+this.getLaiSuat()+"% lãi suất ✓");
                                break;
                            }
                            if(this.getThoiGianKihan() >= 5) {
                                this.setLaiSuat(10);
                                System.out.println("✓ Tiết kiệm của bạn có kì hạn "+this.getThoiGianKihan()+" năm với "+this.getLaiSuat()+"% lãi suất ✓");
                                break;
                            }

                        }
                        else System.out.println("!!Lỗi, Phải nhập kì hạn hơn 1 năm!!");
                    }while(true);
                }
                break;
            }
        }while(true);
    }
    public TaiKhoanTietKiem thayDoiThongTin(TaiKhoanThanhToan taiKhoanThanhToan){
        TaiKhoanTietKiem taiKhoanTietKiem = null;
        System.out.println("!!Bạn có muốn giữ lại thông tin của tài khoản thanh toán không?");
        System.out.print("!!Vui lòng nhập 'Có' hoặc 'Không':");
        String luachon = sc.nextLine();
        if(luachon.equalsIgnoreCase("co")){
            luaChonKiHan();
            taiKhoanTietKiem = new TaiKhoanTietKiem(taiKhoanThanhToan.getSoTaiKhoan(), taiKhoanThanhToan.getTenChuTk(), taiKhoanThanhToan.getMatKhau(), thoiGianKihan, kiHan, this.getLaiSuat());
        }
        else if(luachon.equalsIgnoreCase("khong")){
            String tenTkNew , matKhauNew, mkNewAgain;
            do{
                System.out.print("=> Nhập tên mới cho tài khoản tiết kiệm:");
                tenTkNew = sc.nextLine();
            }while(!check.checkName(tenTkNew));
            do{
                System.out.print("=> Nhập mật khẩu:");
                matKhauNew = sc.nextLine();
                if(check.checkPass(matKhauNew)){
                    System.out.print("=> Nhập lại mật khẩu:");
                    mkNewAgain = sc.nextLine();
                    if(check.checkPass(mkNewAgain)){
                        if(check.checkPassAgain(matKhauNew, mkNewAgain)){
                            luaChonKiHan();
                            taiKhoanTietKiem = new TaiKhoanTietKiem(taiKhoanThanhToan.getSoTaiKhoan(), tenTkNew, matKhauNew, thoiGianKihan, kiHan, this.getLaiSuat());
                            System.out.println("✓ Tạo tài khoản tiết kiệm với thông tin mới thành công ✓");
                            break;
                        }
                        else System.out.println("!!Xác nhận mật khẩu không trùng khớp!!");
                    }
                }
            }while(true);

        }
        else System.out.println("!!Vui lòng nhập đúng!!");
        return taiKhoanTietKiem;
    }
    public double tinhLaiSuatKep() {
        double laiSuat = this.getLaiSuat() / 100;
        int soLanGhepLai;
        double thoiGian;

        if (this.getKiHan() == 1) {
            soLanGhepLai = 12;
            thoiGian = (double) this.getThoiGianKihan() / 12;
        } else {
            soLanGhepLai = 1;
            thoiGian = (double) this.getThoiGianKihan();
        }

        double tienSauLai = this.soDuTietKiem *  Math.pow(1 + (laiSuat / soLanGhepLai), soLanGhepLai * thoiGian);
        return tienSauLai;
    }

    public void ThongTinLai(){
        System.out.println("=========THÔNG TIN LÃI SUẤT=========");
        System.out.println("Số dư hiện tiết kiệm hiện tại: "+this.getSoDuTietKiem()+"VNĐ");
        System.out.println("Lãi suất: "+this.getLaiSuat()+"%/năm");
        if(this.getKiHan() == 1)
            System.out.println("Kì hạn: "+this.getThoiGianKihan()+ " tháng");
        else
            System.out.println("Kì hạn: "+this.getThoiGianKihan() + " năm");
        double tienSauLai = tinhLaiSuatKep();
        System.out.println("Số tiền lãi nhận được: "+(tienSauLai-this.getSoDuTietKiem()) +"VNĐ");
        System.out.println("Tổng số tiền sau tiết kiệm: "+tienSauLai+ "VNĐ");
    }

    @Override
    public String toString() {
        return "Chủ tài khoản:"+this.getTenChuTk().toUpperCase() + "\nSố tài khoản: ****"+this.getSoTaiKhoan().substring(3)+"\nSố dư tài khoản tiết kiệm: "+ this.getSoDuTietKiem()+"VNĐ";
    }

    @Override
    public void DoiMatKhau() {
        super.DoiMatKhau();
    }

    @Override
    public void XemThongTin() {
        int chon;
        do{
            System.out.println("======== THÔNG TIN TÀI KHOÀN TIẾT KIỆM ==========");
            System.out.println(this);
            System.out.println("========================================");
            System.out.println("1. Xem thông tin lãi suất tiết kiệm");
            System.out.println("2. Thay đổi mật khẩu");
            System.out.println("3. Lịch sử giao dịch");
            System.out.println("0. Trở về");
            System.out.print("Vui lòng chọn một chức năng: ");
            chon = sc.nextInt();
            switch (chon){
                case 1 -> {
                    this.ThongTinLai();
                    tapToContinue();
                }
                case 2 -> {
                    this.DoiMatKhau();
                    tapToContinue();
                }
                case 3 ->{
                    if(lichSuTietKiem.isEmpty())
                        System.out.println("!!Chưa có lịch sử giao dịch nào :(((");
                    else{
                        System.out.println("=======Lịch sử giao dịch của tài khoản tiết kiệm=======");
                        for(String x : this.getLichSuTietKiem()){
                            System.out.println(x);
                        }
                    }
                    tapToContinue();
                }

            }
        }while(chon != 0);
    }

    public void ChucNangTaiKhoanTietKiem(TaiKhoanThanhToan taiKhoanThanhToan) {
        int luaChon;
        do{
            System.out.println("--------------------------------");
            System.out.println("===== TÀI KHOẢN TIẾT KIỆM =====");
            System.out.println("1. Kiểm tra thông tin tài khoản của bạn");
            System.out.println("2. Nạp tiền vào tài khoản");
            System.out.println("3. Rút tiền từ tài khoản");
            System.out.println("0. Trở về");
            System.out.println("================================");
            System.out.print("Vui lòng nhập lựa chọn của bạn: ");

            luaChon = sc.nextInt();
            switch (luaChon){
                case 1:{
                    this.XemThongTin();
                    break;
                }
                case 2:{
                    this.NapTien(taiKhoanThanhToan);
                    tapToContinue();
                    break;
                }
                case 3:{
                    sc.nextLine();
                    this.RutTien(taiKhoanThanhToan);
                    tapToContinue();
                    break;
                }
            }
        }while(luaChon != 0);
    }

}