/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import java.util.List;
import static Control.Constants.*;

/*
 * implements all agregate functions
 * @author pancijan
 */
public class AgregateController {

    public float agFunc(int function, List<Float> values) {
        float val;
        switch (function) {
            case AG_FUNC_SUM:
                return agFuncSum(values);
            case AG_FUNC_MIN:
                return agFuncMin(values);
            case AG_FUNC_MAX:
                return agFuncMax(values);

        }
        return 0;
    }

    public float agFuncSum(List<Float> values) {
        float sum = 0;
        for (float x : values) {
            sum += x;
        }
        return sum;
    }

    public float agFuncMin(List<Float> values) {
        float min = Float.MAX_VALUE;
        for (float x : values) {
            if (min > x) {
                min = x;
            }
        }
        return min;
    }

    public float agFuncMax(List<Float> values) {
        float max = Float.MIN_VALUE;
        for (float x : values) {
            if (max < x) {
                max = x;
            }
        }
        return max;
    }

}
