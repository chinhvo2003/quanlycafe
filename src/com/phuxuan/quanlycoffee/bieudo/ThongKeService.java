/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.phuxuan.quanlycoffee.bieudo;

import com.phuxuan.quanlycoffee.model.Thongkemodel;
import com.phuxuan.quanlycoffee.model.Thongkesoluongbanmodel;
import java.util.List;

/**
 *
 * @author chinh
 */
public interface ThongKeService {

    public List<Thongkemodel> getListByHoadon();

    public List<Thongkesoluongbanmodel> getListByteCThoadon();

}
