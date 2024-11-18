package Test;

import NguoiDungTaiKhoan.QuanLiNguoiDung;

import java.io.IOException;
import java.text.ParseException;

public class Main {
    public static void main(String[] args) throws IOException, ParseException {
        QuanLiNguoiDung quanLiNguoiDung = new QuanLiNguoiDung();
        quanLiNguoiDung.DocDanhSachNguoiDung();
        quanLiNguoiDung.menuNguoiDung();
    }
}