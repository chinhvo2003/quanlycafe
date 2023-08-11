/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package loginform;

/**
 *
 * @author chinh
 */
public class LoginController {

    private LoginView view;
    private LoginModel model;

    public LoginController() {
    }

    public LoginController(LoginView view, LoginModel model) {
        this.view = view;
        this.model = model;
    }

    public void dangnhap() {
        view.hienthiLoginView();
        String u = view.getUsername();
        String p = view.getPassword();
        Account kq = model.timTaiKhoan(u, p);
        if (kq != null) {
            view.dangnhapThanhCong();
        } else {
            view.dangnhapThatbai();
        }
    }

    public static void main(String[] args) {
        LoginView view = new LoginView();
        LoginModel model = new LoginModel();
        LoginController controller = new LoginController(view, model);
        controller.dangnhap();
    }
}
