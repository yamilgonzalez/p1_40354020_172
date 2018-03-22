package experimentalClasses;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Map;

import dataGenerator.DataReader;
import interfaces.IntersectionFinder;
import interfaces.MySet;
import mySetImplementations.Set1;
import mySetImplementations.Set2;
/**
 * An object of this type will contain the results of random experiments
 * to estimate the average execution time per size of a particular strategy. 
 * It also stores the partial sum of the times that the particular strategy
 * has taken during the experimental trials. 
 * 
 * An object of this type will embed a particular strategy. When that particular
 * strategy is executed from an ExperimentController object, this object will 
 * contain the computed average execution times for each input size that it
 * has experimented with. 
 * 
 * Notice that this is implemented as a subclass of 
 * ArrayList<Matp.Entry<Integer, Float>>
 * 
 * @author pedroirivera-vega
 * @author Maria A Marrero
 * @author Yamil J Gonzalez
 *
 */
public class StrategiesTimeCollection<E> 
extends ArrayList<Map.Entry<Integer, Float>> { 
    private IntersectionFinder<E> strategy;    
    private float sum;       
    // variable to accumulate the sum of times that different
    // executions for the same time take. It is eventually used
    // to determine the average execution time for a particular size

    
    public StrategiesTimeCollection(IntersectionFinder<E> strategy) { 
        this.strategy = strategy; 
    } 
    
    public String getStrategyName() { 
        return strategy.getName(); 
    }
    
    public void runTrial(Object [][][] data) throws FileNotFoundException { 
    	int m = data[0].length;
    	MySet[] t = new MySet[m];
    	//create union of sets 
    	for(int j = 0; j < data[0].length ; j++) {
    		t[j] = new Set2();
    		if(getStrategyName().equals("P1")) t[j] = new Set1();
    		for(int i = 0; i < data.length ; i++ )
    			for(int k = 0; k < data[i][j].length; k++){
    				t[j].add(data[i][j][k]);
    			}
    	}
    	//apply strategy
    	strategy.intersectSets(t);
    }
    
    public void resetSum() { 
    	sum = 0.0f; 
    }
    
    public void incSum(float t) { 
    	sum += t; 
    }
    
    public float getSum() { 
    	return sum; 
    }
    
}