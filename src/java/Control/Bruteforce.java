/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import java.util.List;
import data.Item;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import javafx.util.Pair;

/**
 *
 * @author pancijan
 */
public class Bruteforce {

    private AgregateController ac;

    public Bruteforce() {
        ac = new AgregateController();
    }

    public List<Integer> compute(List<Item> items, boolean[] columns, int k) {
        List<Integer> result = new ArrayList<Integer>();

        List<Pair<Integer, Float>> tmp = new ArrayList<Pair<Integer, Float>>();

        for (int i = 0; i < items.size(); i++) {
            List<Float> values = new ArrayList<Float>();
            for (int j = 0; j < items.get(i).valuesCount(); j++) {
                if (columns[j]) {
                    values.add(items.get(i).getValues(j));
                }
            }
            float resultValue = ac.agFuncSum(values);
            tmp.add(new Pair<Integer, Float>(i, resultValue));
        }

        Collections.sort(tmp, new Comparator<Pair<Integer,Float>>() {
            @Override
            public int compare(Pair<Integer,Float> x, Pair<Integer,Float> y) {

                return (int) (x.getValue() - y.getValue());
            }
        });
        
        for (int i = 0; i < k; i++)
            result.add(tmp.get(i).getKey());
        
        return result;

    }

}
