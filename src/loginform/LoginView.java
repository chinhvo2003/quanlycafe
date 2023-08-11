/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package loginform;

import java.util.Scanner;

/**
 *
 * @author chinh
 */
public class LoginView {

    private String username;
    private String password;

    public LoginView() {
    }

    private String nhapUsername() {
        System.out.print("Hay nhap username: ");
        Scanner sc = new Scanner(System.in);
        String s = sc.next();
        return s;
    }

    private String nhapPassword() {
        System.out.print("Hay nhap password: ");
        Scanner sc = new Scanner(System.in);
        String s = sc.next();
        return s;
    }

    public void hienthiLoginView() {
        System.out.println("FORM DANG NHAP");
        username = nhapUsername();
        password = nhapPassword();
    }

    public void dangnhapThanhCong() {
        System.out.println("DANG NHAP THANH CONG");
    }

    public void dangnhapThatbai() {
        System.out.println("DANG NHAP THAT BAI");
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
