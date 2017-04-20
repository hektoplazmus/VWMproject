/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author pancijan
 */
public class Item {
    
    //list of atributes for items
    private List<String> attr;
    private int id;
    private float tmpAgateValueBruteforce;
    private float tmpAgateValue;

    //list of normalize values for item
    private List<Float> values;
    
    public Item(){
        attr = new ArrayList<String>();
        values = new ArrayList<Float>();
        
    }
    
    public String getAttr(int i){
        return attr.get(i);
    }
    
    public void addAttr(String attrib){
        attr.add(attrib);
    }

    /**
     * @return the value
     */
    public float getValue(int i) {
        return values.get(i);
    }

    
    /**
     * @param values the values to set
     */
    public void addValue(float value) {
        this.values.add(value);
    }
    
    public void setValue(int index, float val){
        this.values.set(index, val);
    }
    public int valuesCount(){
        return values.size();
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the tmpAgateValue
     */
    public float getTmpAgateValue() {
        return tmpAgateValue;
    }

    /**
     * @param tmpAgateValue the tmpAgateValue to set
     */
    public void setTmpAgateValue(float tmpAgateValue) {
        this.tmpAgateValue = tmpAgateValue;
    }

    /**
     * @return the tmpAgateValueBruteforce
     */
    public float getTmpAgateValueBruteforce() {
        return tmpAgateValueBruteforce;
    }

    /**
     * @param tmpAgateValueBruteforce the tmpAgateValueBruteforce to set
     */
    public void setTmpAgateValueBruteforce(float tmpAgateValueBruteforce) {
        this.tmpAgateValueBruteforce = tmpAgateValueBruteforce;
    }
}
