/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backing;

import data.TimeChartData;
import java.io.Serializable;
import java.util.List;
import javafx.util.Pair;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.LineChartModel;
import org.primefaces.model.chart.LineChartSeries;

/**
 *
 * @author pancijan
 */
@ManagedBean
public class ChartView implements Serializable {
 
    private LineChartModel lineModel1;
    
    @PostConstruct
    public void init() {
        createLineModels();
    }
 
    public LineChartModel getLineModel1() {
        return lineModel1;
    }

    private void createLineModels() {
        lineModel1 = initLinearModel();
        lineModel1.setTitle("Linear Chart");
        lineModel1.setLegendPosition("e");
        Axis yAxis = lineModel1.getAxis(AxisType.Y);
        yAxis.setMin(0);
        yAxis.setMax(500);
         
    }
     
    public LineChartModel initLinearModel() {
        LineChartModel model = new LineChartModel();
        if (TimeChartData.timeLines == null) return model;
        LineChartSeries series1 = new LineChartSeries();
        series1.setLabel("Bruteforce");
        LineChartSeries series2 = new LineChartSeries();
        series2.setLabel("Treshold");
        
        
        for (int i = 0; i < TimeChartData.timeLines.size(); i++) {
            series1.set(i, TimeChartData.timeLines.get(i).getKey());
            series2.set(i, TimeChartData.timeLines.get(i).getValue());
        }
        model.addSeries(series1);
        model.addSeries(series2);
         
        return model;
    }
    
}