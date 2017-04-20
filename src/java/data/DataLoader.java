package data;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
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
    
    /*
    * unused
    */
    /*public DataSet load(String fileName) {
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
    }*/
    
    
    /*
    * load csv file with attribs separated by comma
    * if : is not found, normalize the attrib
    */
    
    public DataSet loadWithValues(String fileName) {
        DataSet dataSet = new DataSet();
        boolean [] attribsToRecalculate = new boolean[0];
        
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
            attribsToRecalculate = new boolean[nextLine.length];
            
            int itemIndex = 0;
            while ((line = br.readLine()) != null) {
                nextLine = line.split(",");
                Item newItem = new Item();
                for (int i = 0; i < nextLine.length; i++) {
                    int index = nextLine[i].lastIndexOf(":");
                    if (index != -1){
                        float value = Float.parseFloat(nextLine[i].substring(index+1));
                        newItem.addValue(value);
                        newItem.setId(itemIndex);
                        newItem.addAttr(nextLine[i].substring(0,index));
                    } else{
                        attribsToRecalculate[i] = true;
                        newItem.addValue(Float.parseFloat(nextLine[i]));
                        newItem.setId(itemIndex);
                        newItem.addAttr(nextLine[i]);
                    }
                    
                }
                dataSet.getItems().add(newItem);
                   itemIndex++;
            }  
        } catch (IOException ex) {
            System.out.println("not loaded");
            Logger.getLogger(DataLoader.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        for (int i = 0; i < attribsToRecalculate.length; i++) {
            if (attribsToRecalculate[i]) {
                
                //GETTING MAX VALUE
                float maxValue = 0;
                float minValue = Float.MAX_VALUE;
                for (int j = 0; j < dataSet.getItems().size(); j++){
                    float tmp = dataSet.getItems().get(j).getValue(i);
                    if (tmp > maxValue)
                        maxValue = tmp;
                    if (tmp < minValue)
                        minValue = tmp;
                }
                
                //NORMALIZING TO INTERVAL 0..1
                for (int j = 0; j < dataSet.getItems().size(); j++){
                    float tmp = dataSet.getItems().get(j).getValue(i);
                    float normalizedValue = 1-(tmp - minValue) / (maxValue - minValue);
                    dataSet.getItems().get(j).setValue(i, (float)(Math.round(normalizedValue * 100d) / 100d));
                    
                }
                
            }
        }
        
        System.out.println("succesfully loaded");
        return dataSet;
    }
}
