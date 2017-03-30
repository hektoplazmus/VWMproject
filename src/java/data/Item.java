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
}
