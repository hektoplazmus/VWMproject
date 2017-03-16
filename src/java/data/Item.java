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
    
    List<String> attr;
    
    public Item(){
        attr = new ArrayList<String>();
        
    }
    
    public String getAttr(int i){
        return attr.get(i);
    }
    
    public void addAttr(String attrib){
        attr.add(attrib);
    }
    
}
