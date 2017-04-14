/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backing;

import Control.Bruteforce;
import data.DataLoader;
import data.DataSet;
import data.Item;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import static Control.Constants.*;
import Control.Treshold;
import data.TimeChartData;
import javafx.util.Pair;
/* test */
/**
 *
 * @author pancijan
 */
@ManagedBean
@SessionScoped
public class Index {

    /**
     * @return the timeBruteforce
     */
    public long getTimeBruteforce() {
        return timeBruteforce;
    }

    /**
     * @param timeBruteforce the timeBruteforce to set
     */
    public void setTimeBruteforce(long timeBruteforce) {
        this.timeBruteforce = timeBruteforce;
    }

    /**
     * @return the timeTreshold
     */
    public long getTimeTreshold() {
        return timeTreshold;
    }

    /**
     * @param timeTreshold the timeTreshold to set
     */
    public void setTimeTreshold(long timeTreshold) {
        this.timeTreshold = timeTreshold;
    }

    //helper class for loading data
    private DataLoader dl;
    
    //loaded dataset
    private DataSet dataSet;

    //dataset to be loaded
    private String chosenDataSet;

    //copy of item list for datatable view
    private List<Item> items;
    
    //items in result datatable
    private List<Item> searchedItemsBruteforce;
    
    //items in result datatable
    private List<Item> searchedItemsTreshold;
    
    //names of table columns
    private List<String> columnNames;

    //agreagate function used in search
    private String agregateFunc;
    
    //number of products to find
    private int k = 1;
    
    //true id arrtib is used in agregate function
    private boolean[] choosenAttribs;
    
    //names of selected attribs
    private String[] selectedAttribs;

    //true if normalization is shown
    private boolean showNormalization = false;
    
    private long timeBruteforce = 0;
    
    private long timeTreshold = 0;
    
    private Bruteforce bf = new Bruteforce();
    private Treshold tr = new Treshold();
        
    public Index() {
        //init values
        chosenDataSet = "notebooks-huge";
        agregateFunc = "max";

       
        
        items = new ArrayList<Item>();
        columnNames = new ArrayList<String>();
         //loades default dataset
         dl = new DataLoader();
        loadData();
    }
    public void processTimeTest(){
        List<Pair<Integer,Integer> > times = new ArrayList<>();
        int agregateFuncCode;
        if (agregateFunc.equals("sum")){
            agregateFuncCode = AG_FUNC_SUM;
        } else if (agregateFunc.equals("min")) {
            agregateFuncCode = AG_FUNC_MIN;
        } else if (agregateFunc.equals("max")){
            agregateFuncCode = AG_FUNC_MAX;
        } else return;
        
        //set TRUE if the column is chosen for searching
        boolean[] tmp = new boolean[dataSet.getColumnNames().size()];
        for (String chosenAttrib : selectedAttribs) {
            for (int i = 0; i < columnNames.size(); i++) {
                if (chosenAttrib.equals(columnNames.get(i))){
                    tmp[i] = true;
                    break;
                }                    
            }                   
        }
        
        for (int i = 1; i < 100; i++) {
            long startTime = System.currentTimeMillis();
            bf.compute(dataSet.getItems(), tmp, i, agregateFuncCode);
            int bruteforceTime = (int) (System.currentTimeMillis() - startTime);

            startTime = System.currentTimeMillis();
            tr.compute(dataSet.getItems(), tmp, i, agregateFuncCode);
            int tresholdTime = (int) (System.currentTimeMillis() - startTime);
            times.add(new Pair<Integer,Integer>(bruteforceTime,tresholdTime));
        }
        TimeChartData.timeLines = times;
        
    }
    public void loadData() {
        dataSet = dl.loadWithValues(chosenDataSet + ".csv");
        setItems(dataSet.getItems());
        setColumnNames(dataSet.getColumnNames());
       
        choosenAttribs = new boolean[dataSet.getColumnNames().size()];
        tr.initData(items);
        
    }

    public void search() {
        
        int agregateFuncCode;
        if (agregateFunc.equals("sum")){
            agregateFuncCode = AG_FUNC_SUM;
        } else if (agregateFunc.equals("min")) {
            agregateFuncCode = AG_FUNC_MIN;
        } else if (agregateFunc.equals("max")){
            agregateFuncCode = AG_FUNC_MAX;
        } else return;
        
        //set TRUE if the column is chosen for searching
        boolean[] tmp = new boolean[dataSet.getColumnNames().size()];
        for (String chosenAttrib : selectedAttribs) {
            for (int i = 0; i < columnNames.size(); i++) {
                if (chosenAttrib.equals(columnNames.get(i))){
                    tmp[i] = true;
                    break;
                }                    
            }                   
        }
        System.out.println("AGRE " + agregateFuncCode);
        //computes indexes of searched items
        
        long startTime = System.currentTimeMillis();
        List<Integer> res = bf.compute(dataSet.getItems(), tmp, k, agregateFuncCode);
        setTimeBruteforce(System.currentTimeMillis() - startTime);

        startTime = System.currentTimeMillis();
        List<Integer> resTr = tr.compute(dataSet.getItems(), tmp, k, agregateFuncCode);
        setTimeTreshold(System.currentTimeMillis() - startTime);
        
        //finds items in dataset
        searchedItemsBruteforce = new ArrayList<>();
        for (int itemIndex : res) {
            searchedItemsBruteforce.add(dataSet.getItems().get(itemIndex));
        }
        
        searchedItemsTreshold = new ArrayList<>();
        for (int itemIndex : resTr) {
            searchedItemsTreshold.add(dataSet.getItems().get(itemIndex));
        }

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

    /**
     * @return the agregateFunc
     */
    public String getAgregateFunc() {
        return agregateFunc;
    }

    /**
     * @param agregateFunc the agregateFunc to set
     */
    public void setAgregateFunc(String agregateFunc) {
        this.agregateFunc = agregateFunc;
    }

    /**
     * @return the selectedAttribs
     */
    public String[] getSelectedAttribs() {
        return selectedAttribs;
    }

    /**
     * @param selectedAttribs the selectedAttribs to set
     */
    public void setSelectedAttribs(String[] selectedAttribs) {
        this.selectedAttribs = selectedAttribs;
    }

    /**
     * @return the searchedItems
     */
    public List<Item> getSearchedItemsBruteforce() {
        return searchedItemsBruteforce;
    }

    /**
     * @param searchedItems the searchedItems to set
     */
    public void setSearchedItemsBruteforce(List<Item> searchedItems) {
        this.searchedItemsBruteforce = searchedItems;
    }

    /**
     * @return the searchedItemsTreshold
     */
    public List<Item> getSearchedItemsTreshold() {
        return searchedItemsTreshold;
    }

    /**
     * @param searchedItemsTreshold the searchedItemsTreshold to set
     */
    public void setSearchedItemsTreshold(List<Item> searchedItemsTreshold) {
        this.searchedItemsTreshold = searchedItemsTreshold;
    }

    
    
}
