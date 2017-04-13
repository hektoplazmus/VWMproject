/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import data.Item;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.TreeSet;
import javafx.util.Pair;

/**
 *
 * @author pancijan
 */
public class Treshold {

    private AgregateController ac;
    public Treshold() {
        ac = new AgregateController();
    }

    public List<Integer> compute(List<Item> items, boolean[] columns, int k, int agregateFunc) {
        
      
        List<Integer> result = new ArrayList<Integer>();

        List<List<Pair<Integer, Float>>> treshList = new ArrayList<List<Pair<Integer, Float>>>();

        List<Integer> colIndexes = new ArrayList<Integer>();
        for (int i = 0; i < columns.length; i++) {
            if (columns[i]) {
                colIndexes.add(i);
            }
        }
        //System.out.println("COLS: " + colIndexes.toString() );
        for (int colIndex : colIndexes) {

            List<Pair<Integer, Float>> tmp = new ArrayList<Pair<Integer, Float>>();

            for (int j = 0; j < items.size(); j++) {
                tmp.add(new Pair<Integer, Float>(j, items.get(j).getValue(colIndex)));
            }
            Collections.sort(tmp, new Comparator<Pair<Integer, Float>>() {
                @Override
                public int compare(Pair<Integer, Float> x, Pair<Integer, Float> y) {
                    if (x.getValue() - y.getValue() < 0) {
                        return 1;
                    } else if (x.getValue() - y.getValue() > 0) {
                        return -1;
                    }
                    return 0;
                }
            });
            treshList.add(tmp);
        }

        List<Pair<Integer, List<Float>>> resultList = new ArrayList<Pair<Integer, List<Float>>>();


        PriorityQueue<Pair<Integer, Float>> resQueue = new PriorityQueue<Pair<Integer, Float>>((o1, o2) -> {
            if (o1.getValue() > o2.getValue()) {
                return 1;
            } else if (o1.getValue() < o2.getValue()) {
                return -1;
            } else {
                return 0;
            }
        });
        
        Set<Integer> processedItems = new TreeSet<Integer>();
        
        int depth = 0;
        float treshold = 1;
        float minAgValue = 1;
        
        while (true) {
            //System.out.println("depth = " + depth);
            if ((minAgValue >= treshold && resQueue.size() >= k) || depth >= items.size())
                break;
            
            List<Float> optimumValues = new ArrayList<Float>();
            for (int i = 0; i < colIndexes.size(); i++) {
                optimumValues.add(treshList.get(i).get(depth).getValue());
            }
            treshold = ac.agFunc(agregateFunc, optimumValues);
            //System.out.println("treshold = " + treshold);
            
            for (int i = 0; i < colIndexes.size(); i++) {
                
                List<Float> values = new ArrayList<Float>();

                int itemIndex = treshList.get(i).get(depth).getKey();

                if (processedItems.contains(itemIndex)) {
                    continue;
                }

                for (int colIndex : colIndexes) {
                    values.add(items.get(itemIndex).getValue(colIndex));

                }

                float agFuncValue = ac.agFunc(agregateFunc, values);
                
                resQueue.add(new Pair<Integer,Float>(itemIndex,agFuncValue));
                if (resQueue.size() > k)
                    resQueue.poll();
                minAgValue = resQueue.peek().getValue();
                //System.out.println("queue size = " + resQueue.size());
                //System.out.println("add item with val = " + agFuncValue);
                
                processedItems.add(itemIndex);
            }
            depth++;
        }
        System.out.println("DEPTH = " + depth);
        for (int i = 0; i < k; i++)
            result.add(resQueue.poll().getKey());
        
        return result;

    }
}
