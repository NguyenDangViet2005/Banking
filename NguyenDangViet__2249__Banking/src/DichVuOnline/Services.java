package DichVuOnline;

import CheckConstraint.Check;
import Interface.DichVu;
import NguoiDungTaiKhoan.NguoiDung;
import TaiKhoanBanking.TaiKhoan;
import TaiKhoanBanking.TaiKhoanThanhToan;
import TaiKhoanBanking.TaiKhoanTinDung;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;
import java.util.Scanner;

public abstract class Services implements DichVu {
    Scanner sc = new Scanner(System.in);
    Check check = new Check();
    DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
    String tgian = LocalDateTime.now().format(dateFormat);
    boolean ggtc = false;
    @Override
    public void cacDichVu(NguoiDung nd) {
        TaiKhoanThanhToan tktt = (TaiKhoanThanhToan) nd.getDanhSachTaiKhoan().get(0);
        System.out.println("==== DỊCH VỤ ONLINE ====");
        System.out.println("1. Đặt vé xem phim");
        System.out.println("2. Đặt vé máy bay");
        System.out.println("3. Đặt phòng khách sạn");
        System.out.println("0. Trở về");
        int luachon;
        luachon = sc.nextInt();
        sc.nextLine();

        switch (luachon) {
            case 1 -> {
                datVeXemPhim(tktt, nd);
            }

            case 2 -> {
                datVeMayBay(tktt, nd);
            }
            case 3 -> {
                datPhongKhachSan(tktt, nd);
            }
        }
    }

    private void datVeXemPhim(TaiKhoanThanhToan tktt, NguoiDung nd) {
        System.out.println("ĐẶT VÉ XEM PHIM");
        System.out.println("Các phim hiện đang được công chiếu...");
        System.out.println("1. Venom: Kèo cuối");
        System.out.println("2. Quỷ ăn Tạng");
        System.out.println("3. Học viện anh hùng");
        System.out.println("..............more");


        int luachon2 = sc.nextInt();
        sc.nextLine();

        String kyTuHang = "ABCDEF";
        System.out.println("---------------Màn Hình---------------");
        for (int i = 0; i < 6; i++) {
            System.out.print("Hàng " + kyTuHang.charAt(i) + ": ");
            for (int j = 1; j <= 10; j++) {
                System.out.print(kyTuHang.charAt(i) + "" + j + " ");
            }
            System.out.println();
        }

        int giaVe = 0;
        String tenPhim = "";

        switch (luachon2) {
            case 1:
                tenPhim = "Venom: Kèo cuối";
                giaVe = 75000;
                break;
            case 2:
                tenPhim = "Quỷ ăn Tạng";
                giaVe = 85000;
                break;
            case 3:
                tenPhim = "Học viện anh hùng";
                giaVe = 90000;
                break;
            default:
                System.out.println("Lựa chọn không hợp lệ.");
                return;
        }

        System.out.printf("%s -- giá vé: %d VNĐ%n", tenPhim, giaVe);
        System.out.print("=> Bạn muốn đặt mấy vé: ");
        int soLuong = sc.nextInt();
        sc.nextLine();

        for (int i = 1; i <= soLuong; i++) {
            System.out.print("Vị trí ghế: ");
            String vt = sc.nextLine();
        }
        System.out.println("Tổng tiền : " +(soLuong*giaVe)+ "VNĐ");
        thanhToan(tktt, nd, soLuong * giaVe);
        if(ggtc)
            for(int i = 1; i<= soLuong ; i++){
                Random rd = new Random();
                int ticketCode = 10000 + rd.nextInt(90000);
                System.out.println("Mã vé của bạn: "+ticketCode);
            }
    }

    private void datVeMayBay(TaiKhoanThanhToan tktt, NguoiDung nd) {
        System.out.println("ĐẶT VÉ MÁY BAY");
        System.out.println("Các điểm đến hiện có...");
        System.out.println("1. Hà Nội - TP Hồ Chí Minh (giá: 1.500.000 VNĐ)");
        System.out.println("2. Hà Nội - Đà Nẵng (giá: 1.200.000 VNĐ)");
        System.out.println("3. TP Hồ Chí Minh - Phú Quốc (giá: 1.000.000 VNĐ)");

        int luachon = sc.nextInt();
        sc.nextLine();

        int giaVe = 0;
        String diemDen = "";

        switch (luachon) {
            case 1:
                diemDen = "Hà Nội - TP Hồ Chí Minh";
                giaVe = 1500000;
                break;
            case 2:
                diemDen = "Hà Nội - Đà Nẵng";
                giaVe = 1200000;
                break;
            case 3:
                diemDen = "TP Hồ Chí Minh - Phú Quốc";
                giaVe = 1000000;
                break;
            default:
                System.out.println("Lựa chọn không hợp lệ.");
                return;
        }

        System.out.printf("Điểm đến: %s -- giá vé: %d VNĐ%n", diemDen, giaVe);
        System.out.print("=> Bạn muốn đặt mấy vé: ");
        int soLuong = sc.nextInt();
        sc.nextLine();
        System.out.println("Tổng tiền : " +(soLuong*giaVe)+ "VNĐ");
        thanhToan(tktt, nd, soLuong * giaVe);
        if(ggtc)
            for(int i = 1; i<= soLuong ; i++){
                Random rd = new Random();
                int ticketCode = 10000 + rd.nextInt(90000);
                System.out.println("Mã vé của bạn: "+ticketCode);
            }
    }

    private void datPhongKhachSan(TaiKhoanThanhToan tktt, NguoiDung nd) {
        System.out.println("ĐẶT PHÒNG KHÁCH SẠN");
        System.out.println("Các khách sạn hiện có...");
        System.out.println("1. Khách sạn A (giá: 500.000 VNĐ/đêm)");
        System.out.println("2. Khách sạn B (giá: 700.000 VNĐ/đêm)");
        System.out.println("3. Khách sạn C (giá: 1.000.000 VNĐ/đêm)");

        int luachon = sc.nextInt();
        sc.nextLine();

        int giaPhong = 0;
        String tenKhachSan = "";

        switch (luachon) {
            case 1:
                tenKhachSan = "Khách sạn A";
                giaPhong = 500000;
                break;
            case 2:
                tenKhachSan = "Khách sạn B";
                giaPhong = 700000;
                break;
            case 3:
                tenKhachSan = "Khách sạn C";
                giaPhong = 1000000;
                break;
            default:
                System.out.println("Lựa chọn không hợp lệ.");
                return;
        }

        System.out.printf("Tên khách sạn: %s -- giá phòng: %d VNĐ/đêm%n", tenKhachSan, giaPhong);
        System.out.print("=> Bạn muốn đặt bao nhiêu đêm: ");
        int soLuongDem = sc.nextInt();
        sc.nextLine();
        System.out.println("Tổng tiền : " +(soLuongDem*giaPhong)+ "VNĐ");
        thanhToan(tktt, nd, soLuongDem * giaPhong);
        if(ggtc){
            Random rd = new Random();
            int RoomCode = 10000 + rd.nextInt(90000);
            System.out.println("Mã phòng được bạn đặt: "+RoomCode);
            System.out.println("Vui lòng đến quầy lễ tân tại khách sạn để check in sớm nhất");
            System.out.println(tenKhachSan + " xin chân thành cảm ơn!");
        }
    }

    private void thanhToan(TaiKhoanThanhToan tktt, NguoiDung nd, int soTien) {
        if (tktt.getSoDuTk() >= soTien) {
            if (check.CheckOTP()) {
                tktt.setSoDuTk(tktt.getSoDuTk() - soTien);
                System.out.println("✓ Giao dịch thành công ✓");
                ggtc = true;
                tktt.getLichSuGiaoDich().addFirst(tgian + ": Đã thanh toán phí dịch vụ "+soTien+"VNĐ, số dư còn lại: "+ tktt.getSoDuTk() +"VNĐ!!" );
            }
        } else {
            xuLyThanhToanThauChi(tktt,nd, soTien);
        }
    }


    // Hàm xử lý thanh toán thấu chi
    private void xuLyThanhToanThauChi(TaiKhoanThanhToan tktt, NguoiDung nd, int soTien) {
        boolean ktra = false;
        for (TaiKhoan tk : nd.getDanhSachTaiKhoan()) {
            if (tk instanceof TaiKhoanTinDung) {
                ktra = true;
                System.out.println("!! Bạn có muốn sử dụng tài khoản tín dụng để tiếp tục giao dịch?");
                System.out.println("!! Vui lòng nhập 'có' hoặc 'không': ");
                String option = sc.nextLine();

                if (option.equalsIgnoreCase("co")) {
                    if (check.CheckOTP()) {
                        TaiKhoanTinDung tkTinDung = (TaiKhoanTinDung) tk;
                        double tienThieu = soTien - tktt.getSoDuTk();
                        tkTinDung.setHanMucThauChi(tkTinDung.getHanMucThauChi() - tienThieu);
                        tkTinDung.setSoTienThauChiDaSuDung(tkTinDung.getSoTienThauChiDaSuDung() + tienThieu);
                        double tienLai = tkTinDung.getSoTienThauChiDaSuDung() * tkTinDung.getLaiSuatThauChi() / 100;
                        System.out.println("!! Đã thấu chi " + tkTinDung.getSoTienThauChiDaSuDung() + " VNĐ với lãi suất " + tkTinDung.getLaiSuatThauChi() + "% !!");
                        System.out.println("!! Số tiền lãi trên khoản thấu chi: " + tienLai + " VNĐ !!");
                        System.out.println("✓ Giao dịch thành công ✓");
                        ggtc = true;
                        tktt.setSoDuTk(0);
                        tktt.getLichSuGiaoDich().addFirst(tgian + ": Đã thanh toán phí dịch vụ "+soTien+"VNĐ, số dư còn lại: "+ tktt.getSoDuTk() +"VNĐ!!" );
                        return;
                    } else {
                        System.out.println("!! Giao dịch thất bại !!");
                    }
                } else if (option.equalsIgnoreCase("khong")) {
                    return;
                }
            }
        }
        if (!ktra) {
            System.out.println("!! Giao dịch thất bại do số tiền rút vượt quá số dư TK của bạn !!");
            System.out.println("!! Bạn có muốn tạo thêm tài khoản tín dụng để có thêm hạn mức thấu chi không?");
            while (true) {
                System.out.print("!! Vui lòng nhập 'Có' hoặc 'Không': ");
                String chon = sc.nextLine();
                if (chon.equalsIgnoreCase("co")) {
                    tktt.taoTaiKhoanTinDung(nd);
                    System.out.println("!! Vui lòng thực hiện lại giao dịch !!");
                    return;
                } else if (chon.equalsIgnoreCase("khong")) {
                    return;
                }
            }
        }
    }

}
