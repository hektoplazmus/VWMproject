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

/* test */
/**
 *
 * @author pancijan
 */
@ManagedBean
@SessionScoped
public class Index {

    //helper class for loading data
    private DataLoader dl;
    
    //loaded dataset
    private DataSet dataSet;

    //dataset to be loaded
    private String chosenDataSet;

    //copy of item list for datatable view
    private List<Item> items;
    
    //items in result datatable
    private List<Item> searchedItems;
    
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

    
    public Index() {
        //init values
        chosenDataSet = "notebooks-small";
        agregateFunc = "max";

       
        
        items = new ArrayList<Item>();
        columnNames = new ArrayList<String>();
         //loades default dataset
         dl = new DataLoader();
        loadData();
    }

    public void loadData() {
        dataSet = dl.loadWithValues(chosenDataSet + ".csv");
        setItems(dataSet.getItems());
        setColumnNames(dataSet.getColumnNames());
       
        choosenAttribs = new boolean[dataSet.getColumnNames().size()];
    }

    public void search() {
        Bruteforce bf = new Bruteforce();
          
        boolean[] tmp = new boolean[dataSet.getColumnNames().size()];
        for (String chosenAttrib : selectedAttribs) {
            for (int i = 0; i < columnNames.size(); i++) {
                if (chosenAttrib.equals(columnNames.get(i))){
                    tmp[i] = true;
                    break;
                }                    
            }                   
        }
        
        
        List<Integer> res = bf.compute(dataSet.getItems(), tmp, k);
        System.out.println("CHOSEN ATTRIBS: ");

        searchedItems = new ArrayList<Item>();
        for (int itemIndex : res) {
            searchedItems.add(dataSet.getItems().get(itemIndex));
        }
        for (int s : res) {
            System.out.println("id = " + s + " : " + items.get(s).getAttr(0));

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
    public List<Item> getSearchedItems() {
        return searchedItems;
    }

    /**
     * @param searchedItems the searchedItems to set
     */
    public void setSearchedItems(List<Item> searchedItems) {
        this.searchedItems = searchedItems;
    }

    
    
}
