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
    
    private LineChartModel lineModel2;

    @PostConstruct
    public void init() {
        createLineModels();
    }
 
    public LineChartModel getLineModel1() {
        return lineModel1;
    }
    
    public LineChartModel getLineModel2() {
        return lineModel2;
    }
    
    private void createLineModels() {
        lineModel1 = initLinearModel();
        lineModel1.setTitle("Searched items chart");
        lineModel1.setLegendPosition("e");
        
        
        lineModel2 = initLinearModel2();
        lineModel2.setTitle("Number of columns chart");
        lineModel2.setLegendPosition("e");
         
    }
     
    public LineChartModel initLinearModel() {
        LineChartModel model = new LineChartModel();
        if (TimeChartData.bruteforceTimeLineK == null || TimeChartData.tresholdTimeLineK ==null) return model;
        LineChartSeries series1 = new LineChartSeries();
        series1.setLabel("Bruteforce");
        LineChartSeries series2 = new LineChartSeries();
        series2.setLabel("Treshold");
        
        int maxVal = Integer.MIN_VALUE;
        
        for (int i = 0; i < TimeChartData.bruteforceTimeLineK.size(); i++) {
            maxVal = Math.max(maxVal, TimeChartData.bruteforceTimeLineK.get(i).getValue());
            maxVal = Math.max(maxVal, TimeChartData.tresholdTimeLineK.get(i).getValue());
            
            series1.set(TimeChartData.bruteforceTimeLineK.get(i).getKey(), TimeChartData.bruteforceTimeLineK.get(i).getValue());
            series2.set(TimeChartData.tresholdTimeLineK.get(i).getKey(), TimeChartData.tresholdTimeLineK.get(i).getValue());
        }
        
        Axis yAxis = model.getAxis(AxisType.Y);
        yAxis.setMin(0);
        yAxis.setMax(maxVal);
        yAxis.setLabel("time (ms)");
        Axis xAxis = model.getAxis(AxisType.X);
        xAxis.setLabel("k");
        
        model.addSeries(series1);
        model.addSeries(series2);
         
        return model;
    }
    
    public LineChartModel initLinearModel2() {
        LineChartModel model = new LineChartModel();
        if (TimeChartData.bruteforceTimeLineColumns == null || TimeChartData.tresholdTimeLineColumns == null) return model;
        LineChartSeries series1 = new LineChartSeries();
        series1.setLabel("Bruteforce");
        LineChartSeries series2 = new LineChartSeries();
        series2.setLabel("Treshold");
        
        int maxVal = Integer.MIN_VALUE;
        
        for (int i = 0; i < TimeChartData.bruteforceTimeLineColumns.size(); i++) {
            maxVal = Math.max(maxVal, TimeChartData.bruteforceTimeLineColumns.get(i).getValue());
            maxVal = Math.max(maxVal, TimeChartData.tresholdTimeLineColumns.get(i).getValue());
            
            series1.set(TimeChartData.bruteforceTimeLineColumns.get(i).getKey(),TimeChartData.bruteforceTimeLineColumns.get(i).getValue());
            series2.set(TimeChartData.tresholdTimeLineColumns.get(i).getKey(),TimeChartData.tresholdTimeLineColumns.get(i).getValue());
        }
        
        Axis yAxis = model.getAxis(AxisType.Y);
        yAxis.setMin(0);
        yAxis.setMax(maxVal);
        yAxis.setLabel("time (ms)");
        Axis xAxis = model.getAxis(AxisType.X);
        xAxis.setLabel("selected columns");
        
        model.addSeries(series1);
        model.addSeries(series2);
         
        return model;
    }
}