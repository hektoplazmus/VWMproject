package data;

import com.opencsv.CSVReader;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Singleton;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author pancijan
 */

public class DataLoader {
    
    
    public List<Item> items;
    
    public  DataLoader() {
        items = new ArrayList<Item>();
    }
    
    public void load() {
        try {
            System.out.println("TADZ");
            InputStream is = getClass().getClassLoader().getResourceAsStream("/data.csv");
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            CSVReader reader = new CSVReader(br);
            String [] nextLine;
            while ((nextLine = reader.readNext()) != null) {
                Item newItem = new Item();
                for (int i = 0; i < nextLine.length; i++) {
                    newItem.addAttr(nextLine[i]);
                }
                items.add(newItem);
                // nextLine[] is an array of values from the line
                System.out.println(nextLine[0] + " " + nextLine[1] );
            }  } catch (IOException ex) {
            Logger.getLogger(DataLoader.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
