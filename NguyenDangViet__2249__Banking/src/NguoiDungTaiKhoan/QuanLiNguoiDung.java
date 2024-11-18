package NguoiDungTaiKhoan;

import CheckConstraint.Check;
import TaiKhoanBanking.TaiKhoan;
import TaiKhoanBanking.TaiKhoanThanhToan;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class QuanLiNguoiDung {
    Check check = new Check();
    Scanner sc = new Scanner(System.in);
    private HashMap<String, NguoiDung> danhSachNguoiDung;
    SimpleDateFormat spfm = new SimpleDateFormat("dd/MM/yyyy");
    ArrayList<String> listCCCD = new ArrayList();
    public QuanLiNguoiDung() {
        danhSachNguoiDung = new HashMap<>();
    }

    public HashMap<String, NguoiDung> getDanhSachNguoiDung() {
        return danhSachNguoiDung;
    }

    public void setDanhSachNguoiDung(HashMap<String, NguoiDung> danhSachNguoiDung) {
        this.danhSachNguoiDung = danhSachNguoiDung;
    }

    public ArrayList<String> getListCCCD() {
        return listCCCD;
    }

    public void setListCCCD(ArrayList<String> listCCCD) {
        this.listCCCD = listCCCD;
    }

    public NguoiDung timNguoiDung(String sdt){
        return this.getDanhSachNguoiDung().get(sdt);
    }
    public void dangKiTaiKhoan() throws IOException {
        String sdt, tentk, cccd;
        Date ngaysinh;
        SimpleDateFormat spfm = new SimpleDateFormat("dd/MM/yyyy");

        do {
            System.out.print("=> Nhập số điện thoại: ");
            sdt = sc.nextLine();
        } while (!check.checkSDT(sdt));
        if (danhSachNguoiDung.containsKey(sdt)) {
            System.out.println("!!Số điện thoại đã tồn tại!!");
            System.out.println("!!Vui lòng nhập số điện thoại khác!!");
            return;
        }
        do {
            System.out.print("=> Nhập tên người dùng:");
            tentk = sc.nextLine();
        } while (!check.checkName(tentk));

        do {
            System.out.print("=> Nhập số CCCD (12 chữ số): ");
            cccd = sc.nextLine();
            if (!check.checkCCCD(cccd)) {
                System.out.println("!!Số CCCD không hợp lệ. Vui lòng nhập lại!!");
            } else if (listCCCD.contains(cccd)) {
                System.out.println("!!Số CCCD này đã được dùng để đăng kí!!");
                System.out.println("!!Vui lòng nhập một số CCCD khác!!");
            }
        } while (!check.checkCCCD(cccd) || listCCCD.contains(cccd));
        listCCCD.add(cccd);

        try {
            System.out.print("=> Nhập ngày sinh (dd/MM/yyyy): ");
            String ngaySinhSTR = sc.nextLine();
            ngaysinh = spfm.parse(ngaySinhSTR);
        } catch (ParseException e) {
            System.out.println("!!Định dạng ngày không hợp lệ!!");
            Calendar date = new GregorianCalendar();
            date.set(1111, Calendar.JANUARY, 1);
            ngaysinh = date.getTime();
        }
        danhSachNguoiDung.put(sdt, new NguoiDung(sdt, tentk, cccd, ngaysinh));
        System.out.println("✓ Đăng ký tài khoản thành công ✓");
        this.GhiNguoiDungVaoDanhSach(new NguoiDung(sdt, tentk, cccd, ngaysinh));
        System.out.println("!!vui lòng đăng nhập lại!!");

    }

    public void dangNhap(QuanLiNguoiDung quanLiNguoiDung) {
        String sdt;
        do {
            System.out.print("=> Nhập số điện thoại: ");
            sdt = sc.nextLine();
        } while (!check.checkSDT(sdt));
        if (danhSachNguoiDung.containsKey(sdt)) {
            int count = 5;
            if (!(danhSachNguoiDung.get(sdt).danhSachTaiKhoan.isEmpty())) {
                do {
                    System.out.print("=> Nhập vào mật khẩu: ");
                    String mmkCheck = sc.nextLine();
                    if (danhSachNguoiDung.get(sdt).danhSachTaiKhoan.get(0).getMatKhau().equals(mmkCheck)) {
                        System.out.println("✓ Đăng nhập thành công ✓");
                        TaiKhoanThanhToan tktt = new TaiKhoanThanhToan();
                        for(TaiKhoan tk : danhSachNguoiDung.get(sdt).danhSachTaiKhoan){
                            if(tk instanceof TaiKhoanThanhToan)
                                tktt = (TaiKhoanThanhToan) tk;
                        }
                        tktt.ChucNangTaiKhoanThanhToan(danhSachNguoiDung.get(sdt), quanLiNguoiDung);
                        break;
                    }
                    else {
                        System.out.println("!!Nhập sai mật khẩu!!");
                        System.out.println("!!Bạn còn " + count-- + " lần nhập!!");

                    }
                } while (count > 0);

                if (count == 0) {
                    System.out.println("!!Đăng nhập thất bại!!");
                    return;
                }

            }
            else {
                System.out.println("!!Chưa tạo tài khoản thanh toán, vui lòng nhập OTP để đăng nhập!!");
                boolean checkDN = check.CheckOTP();
                if (checkDN) {
                    NguoiDung nguoiDung = danhSachNguoiDung.get(sdt);
                    TaiKhoanThanhToan tktt = new TaiKhoanThanhToan();
                    if(nguoiDung.danhSachTaiKhoan.isEmpty()){
                        System.out.println("!!Bạn chưa có tài khoản thanh toán nào!!");
                        System.out.println("!!Vui lòng tạo một tài khoản để sử dụng các dịch vụ của chúng tôi!!");
                        System.out.println("1. Đồng ý\n2. Để sau");
                        int luachon;
                        do{
                            try{
                                System.out.print("=> Nhập lựa chọn: ");
                                luachon = sc.nextInt();
                                if(luachon == 1){
                                    nguoiDung.Dangki(this);
                                    menuNguoiDung();
                                    break;
                                }
                                else if(luachon == 2){
                                    break;
                                }
                            }catch (Exception e){
                                System.out.println("!!Nhập đúng định dạng đi nào!!");
                            }
                        }while(true);
                    }
                }

            }


        }
        else
            System.out.println("!!Người dùng không tồn tại!!");
    }
    public void DocDanhSachNguoiDung() throws IOException, ParseException {
        File file = new File("FileRead\\DanhSachNguoiDung.txt");
        if (!file.exists()) {
            System.out.println("Không tìm thấy tệp tại vị trí: " + file.getAbsolutePath());
            return;
        }
        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);
        while(true){
            String line = br.readLine();
            if(line == null){
                break;
            }
            String [] DS = line.split(", ");
            this.getDanhSachNguoiDung().put(DS[0], new NguoiDung(DS[0], DS[1], DS[2], spfm.parse(DS[3])));
        }
        fr.close();
        br.close();
    }
    public void GhiNguoiDungVaoDanhSach(NguoiDung nd) throws IOException {
        File file = new File("FileRead\\DanhSachNguoiDung.txt");
        FileWriter fw = new FileWriter(file, true);
        BufferedWriter bw = new BufferedWriter(fw);
        bw.write("\n");
        bw.write(nd.toString());
        bw.close();
        fw.close();

    }
    public void menuNguoiDung() throws IOException {
        int choice;
        do {
            System.out.println("===== CHÀO MỪNG ĐẾN VỚI ĐĂNG VIỆT BANKING =====");
            System.out.println("Vui lòng chọn một tùy chọn bên dưới:");
            System.out.println("1. Đăng ký tài khoản mới");
            System.out.println("2. Đăng nhập tài khoản");
            System.out.println("3. Thoát chương trình");
            System.out.println("==================================");
            System.out.print("=> Lựa chọn của bạn: ");
            try{
                choice = sc.nextInt();
                sc.nextLine();
            }
            catch (Exception e)
            {
                System.out.println("Lỗi!");
                sc.nextLine();
                break;
            }
            switch (choice)
            {
                case 1:
                    dangKiTaiKhoan();
                    break;
                case 2:
                    dangNhap(this);
                    break;
            }
        }while (choice != 3);
    }

}