/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package loginform;

import java.util.ArrayList;

/**
 *
 * @author chinh
 */
public class LoginModel {

    private ArrayList<Account> dstk;

    public LoginModel() {
        dstk = new ArrayList<>();
        dstk.add(new Account("chinhql", "1234", "admin"));
        dstk.add(new Account("haiql", "1234", "user"));
    }

    public LoginModel(ArrayList<Account> dstk) {
        this.dstk = dstk;
    }

    public Account timTaiKhoan(String username, String password) {
        for (Account tk : dstk) {
            if (username.equals(tk.getUsername()) && password.equals(tk.getPassword())) {
                return tk;
            }
        }
        return null;
    }
}
