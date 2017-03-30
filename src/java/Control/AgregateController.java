/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import java.util.List;

/**
 *
 * @author pancijan
 */
public class AgregateController {
    
    public float agFuncSum(List<Float> values) {
        float sum = 0;
        for (float x : values)
            sum+=x;
        return sum; 
    }
    
}
