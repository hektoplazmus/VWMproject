/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backing;

import data.DataLoader;
import data.DataSet;
import data.Item;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

/* test */

/**
 *
 * @author pancijan
 */
@ManagedBean
@SessionScoped
public class Index {

    private DataLoader dl;
    private DataSet dataSet;
    private String chosenDataSet;
    private List<Item> items;
    private List<String> columnNames;
    private int k = 1;
    private boolean showNormalization = false;
    
    public Index() {
        chosenDataSet = "notebooks-small";
        
        dl = new DataLoader();
        items = new ArrayList<Item>();
        columnNames = new ArrayList<String>();
        loadData();
    }

    public void loadData() {
        dataSet = dl.loadWithValues(chosenDataSet + ".csv");
        setItems(dataSet.getItems());
        setColumnNames(dataSet.getColumnNames());
    }



    /**
     * @return the chosenDataSet
     */
    public String getChosenDataSet() {
        return chosenDataSet;
    }

    /**
     * @param chosenDataSet the chosenDataSet to set
     */
    public void setChosenDataSet(String chosenDataSet) {
        this.chosenDataSet = chosenDataSet;
    }

    /**
     * @return the items
     */
    public List<Item> getItems() {
        return items;
    }

    /**
     * @param items the items to set
     */
    public void setItems(List<Item> items) {
        this.items = items;
    }

    /**
     * @return the columnNames
     */
    public List<String> getColumnNames() {
        return columnNames;
    }

    /**
     * @param columnNames the columnNames to set
     */
    public void setColumnNames(List<String> columnNames) {
        this.columnNames = columnNames;
    }

    public int getIterateIndex() {

        return columnNames.size();
    }

    /**
     * @return the showNormalization
     */
    public boolean getShowNormalization() {
        return showNormalization;
    }

    /**
     * @param showNormalization the showNormalization to set
     */
    public void setShowNormalization(boolean showNormalization) {
        this.showNormalization = showNormalization;
    }

    /**
     * @return the k
     */
    public int getK() {
        return k;
    }

    /**
     * @param k the k to set
     */
    public void setK(int k) {
        this.k = k;
    }

    
    
}
