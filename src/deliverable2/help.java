/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deliverable2;

import java.util.ArrayList;
import javafx.scene.chart.XYChart;

/**
 *
 * @author Yuki
 */
public class help {
    
    private ArrayList<XYChart.Series> data;
    
    public help(){
        data = new ArrayList<>();
    }
    
    public void setData(ArrayList<XYChart.Series> data){
        this.data = data;
    }
    
    public ArrayList<XYChart.Series> getData(){
        return data;
    }
    
}
