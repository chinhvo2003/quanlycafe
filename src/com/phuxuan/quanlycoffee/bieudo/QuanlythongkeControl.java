/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.phuxuan.quanlycoffee.bieudo;

import com.phuxuan.quanlycoffee.model.Thongkemodel;
import com.phuxuan.quanlycoffee.model.Thongkesoluongbanmodel;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.List;
import javax.swing.JPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarPainter;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.gantt.Task;
import org.jfree.data.gantt.TaskSeries;
import org.jfree.data.gantt.TaskSeriesCollection;
import org.jfree.data.general.DefaultPieDataset;

/**
 *
 * @author chinh
 */
public class QuanlythongkeControl {

    private ThongKeService thongKeService = null;
    private PiePlot pie;

    public QuanlythongkeControl() {
        thongKeService = new ThongKeServiceImpl();
    }

    public void setDateTochar(JPanel jPnanel) {
        List<Thongkemodel> list = thongKeService.getListByHoadon();
        if (list != null) {
            DefaultCategoryDataset dataset = new DefaultCategoryDataset();
            for (Thongkemodel item : list) {
                dataset.addValue(item.getTongtien(), "Doanh thu", item.getNgayban());
            }
            JFreeChart chart = ChartFactory.createBarChart("BIỂU ĐỒ THỐNG KÊ DOANH THU THEO NGÀY",
                    "Ngày", "Doanh thu (đồng)", dataset);
            ChartPanel chartPanel = new ChartPanel(chart);
            chartPanel.setPreferredSize(new Dimension(jPnanel.getWidth(), 300));
            CategoryPlot categoryPlot = chart.getCategoryPlot();
            categoryPlot.setBackgroundPaint(new Color(140, 105, 104));
            jPnanel.removeAll();
            jPnanel.setLayout(new CardLayout());
            jPnanel.add(chartPanel);
            jPnanel.validate();
        }
    }

    public void setslban(JPanel jPianel) {
        List<Thongkesoluongbanmodel> listt = thongKeService.getListByteCThoadon();
        if (listt != null) {
            DefaultPieDataset dataset = new DefaultPieDataset();
            for (Thongkesoluongbanmodel item : listt) {
                dataset.setValue(item.getTentd(), item.getSlban());
            }
            JFreeChart chart = ChartFactory.createPieChart3D(
                    "DOANH THU THEO SẢN PHẨM", dataset);
            ChartPanel chartPanel = new ChartPanel(chart);
            pie = new PiePlot();
            pie = (PiePlot) chart.getPlot();
            pie.setBackgroundPaint(new Color(204, 204, 255));
            jPianel.removeAll();
            jPianel.setLayout(new CardLayout());
            jPianel.add(chartPanel);
            jPianel.validate();
        }

    }

    public void setsldoduong(JPanel jpan) {
        List<Thongkesoluongbanmodel> listtt = thongKeService.getListByteCThoadon();
        if (listtt != null) {
            DefaultCategoryDataset dataset = new DefaultCategoryDataset();
            for (Thongkesoluongbanmodel itemm : listtt) {
                dataset.setValue(itemm.getSlban(), "CJSH", itemm.getTentd());
            }
            JFreeChart liChart = ChartFactory.createLineChart("skh", "ksh", "ckshv", dataset, PlotOrientation.VERTICAL, false, true, false);
            CategoryPlot liCategoryPlot = liChart.getCategoryPlot();
            liCategoryPlot.setBackgroundPaint(Color.WHITE);
            LineAndShapeRenderer lineAndShapeRenderer = (LineAndShapeRenderer) liCategoryPlot.getRenderer();
            Color liColor = new Color(204, 0, 51);
            lineAndShapeRenderer.setSeriesPaint(0, liColor);
            ChartPanel liChartPanel = new ChartPanel(liChart);
            jpan.removeAll();
            jpan.add(liChartPanel, BorderLayout.CENTER);
            jpan.validate();
        }
    }
}
