/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deliverable2;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;

/**
 *
 * @author Yuki
 */
public class help2 {
    
    private ObservableList<PieChart.Data> data;
    
    public help2(){
        data = FXCollections.observableArrayList();
    }
    
    public void setData(ObservableList<PieChart.Data> data){
        this.data = data;
    }
    
    public ObservableList<PieChart.Data> getData(){
        return data;
    }
}
