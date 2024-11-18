package CheckConstraint;

import java.util.Random;
import java.util.Scanner;

public class Check {
    Scanner sc = new Scanner(System.in);
    public Check(){};
    public boolean checkSDT(String sdt){
        if(sdt.isEmpty() || sdt.trim().isEmpty()){
            //nhận dấu cách và dấu enter
            System.out.println("!!Số điện thoại không được để trống!!");
            return false;
        }
        //quy dinh trong chuoi chi chua so, khong duoc co ki tu khac
        if(!sdt.matches("\\d+")){
            System.out.println("!!số điện thoại không được chứa chữ cái!!");
            return false;
        }else {
            if (sdt.length() == 10 || sdt.length() == 11) {
                return true;
            } else {
                System.out.println("!!số điện thoại phải đủ từ 10 đến 11 kí tự!!");
                return false;
            }
        }
    }
    public boolean checkCCCD(String cccd){
        if(cccd.isEmpty() || cccd.trim().isEmpty()){
            System.out.println("!!Căn cước công dân không được để trống!!");
            return false;
        }
        if(!cccd.matches("\\d+")){
            System.out.println("!!số căn cước chỉ được chứa chữ số!!");
            return false;
        }
        else{
            if(cccd.length() == 12){
                return true;
            }
            else{
                System.out.println("!!số căn cước phải đủ 12 kí tự!!");
                return false;
            }
        }
    }

    public boolean checkName(String name){
        if(name.isEmpty() ||name.trim().isEmpty()){
            System.out.println("!!Tên chủ tài khoản không được để trống!!");
            return false;
        }
        if(!name.matches("^[a-zA-Z\\s]+$")){
            System.out.println("!!Tên chỉ được bao gồm chữ cái!!");
            return false;
        }
        else return true;
    }
    //hàm kiểm tra mật khẩu 2 lần là giống nhau
    public boolean checkPassAgain(String pass1, String pass2){
        if(pass1.equals(pass2)){
            return true;
        }
        else{
            return false;
        }
    }
    public boolean CheckOTP(){
        //thêm case otp có số 0 ở đầu
        Random otpRandom = new Random();
        int otp = 10000 + otpRandom.nextInt(90000);
        System.out.println("!!Mã Otp gửi cho bạn: "+otp);
        System.out.print("=> Nhập OTP:");
        int yourOTP = sc.nextInt();
        sc.nextLine();
        if(yourOTP == otp){
            return true;
        }
        else return false;
    }
    public boolean checkPass(String pass){
        if(pass.isEmpty() || pass.trim().isEmpty()){
            System.out.println("!!Mật khẩu không được để trống!!");
            return false;
        }
        return true;
    }
}
