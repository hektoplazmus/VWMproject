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
    
    
    
    
    public  DataLoader() {
    }
    
    public DataSet load(String fileName) {
        DataSet dataSet = new DataSet();
        
        try {
            System.out.println("loading dataset " + fileName);
            
            InputStream is = getClass().getClassLoader().getResourceAsStream("/" + fileName);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            CSVReader reader = new CSVReader(br);
            String [] nextLine;

            nextLine = reader.readNext();
            for (int i = 0; i < nextLine.length; i++) {
                dataSet.getColumnNames().add(nextLine[i]);
            }
                        
            while ((nextLine = reader.readNext()) != null) {
                Item newItem = new Item();
                for (int i = 0; i < nextLine.length; i++) {
                    newItem.addAttr(nextLine[i]);
                }
                dataSet.getItems().add(newItem);

            }  
        } catch (IOException ex) {
            System.out.println("not loaded");
            Logger.getLogger(DataLoader.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("succesfully loaded");
        return dataSet;
    }
    
    public DataSet loadWithValues(String fileName) {
        DataSet dataSet = new DataSet();
        
        try {
            System.out.println("loading dataset " + fileName);
            
            InputStream is = getClass().getClassLoader().getResourceAsStream("/" + fileName);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            //CSVReader reader = new CSVReader(br);
            

            String line = br.readLine();
            String [] nextLine = line.split(",");
            
            for (int i = 0; i < nextLine.length; i++) {
                dataSet.getColumnNames().add(nextLine[i]);
            }
                        
            while ((line = br.readLine()) != null) {
                nextLine = line.split(",");
                Item newItem = new Item();
                for (int i = 0; i < nextLine.length; i++) {
                    int index = nextLine[i].lastIndexOf(":");
                    if (index != -1){
                        float value = Float.parseFloat(nextLine[i].substring(index+1));
                        newItem.addValue(value);
                        newItem.addAttr(nextLine[i].substring(0,index));
                    } else{
                        newItem.addValue(0f);
                        newItem.addAttr(nextLine[i]);
                    }
                    
                }
                dataSet.getItems().add(newItem);

            }  
        } catch (IOException ex) {
            System.out.println("not loaded");
            Logger.getLogger(DataLoader.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("succesfully loaded");
        return dataSet;
    }
}
