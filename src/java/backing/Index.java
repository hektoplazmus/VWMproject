/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backing;

import data.DataLoader;
import data.Item;
import java.util.List;
import javax.ejb.Stateless;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;


/**
 *
 * @author pancijan
 */
@ManagedBean
@SessionScoped
public class Index {

    private String name = "Ahoj"; 
    private DataLoader dl;
    private List<Item> items;
    
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    public Index() {
         dl = new DataLoader();
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }
    
    public void loadData(){
        dl.load();
        items = dl.items;
        System.out.println("ITEMS " + items.size());
    }

    /**
     * @return the items
     */
    public List<Item> getItems() {
        return items;
    }
}
