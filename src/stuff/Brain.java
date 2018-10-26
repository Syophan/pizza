package stuff;

import java.util.Random;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

public class Brain 
{
    List<Neuron> layer1;
    Random rng = new Random();
    
    private double[] decisionValues = new double[3];
    private double[] decisionCutoffs = new double[2];
    
    public Brain(int inputLayerSize)
    {
        initializeNeurons();
        computeCutoffs();
    }
    
    public void train()
    {
        double temp = 100;
        int iteration = 0;
        double newScore = 0;
        double bestScore = 0;
        double[] bestStrategy;
        
        double previousScore = 0;
        double[] previousStrategy;
        
        while(iteration < 1000000)
        {
            previousStrategy = Arrays.copyOf(decisionValues, 3);
            previousScore = newScore;
            decisionValues = generateNeighbourStrategy(decisionValues);
            newScore = play();
            
            if(newScore > previousScore) 
            {
                if(newScore > bestScore)
                {
                    bestScore = newScore;
                    bestStrategy = Arrays.copyOf(decisionValues, 3);
                }
            }
            
            if(accept(previousScore, newScore, temp))
            {
                previousScore = newScore;
            }
            else
            {
                decisionValues = previousStrategy;
            }
            
            if(iteration % 1000 == 0)
            {
                temp *= 0.98;
            }
        }
    }
    
    public double[] generateNeighbourStrategy(double[] originalStrategy)
    {
        0hoy       
    }
    
    public boolean accept(double oldScore, double newScore, double T)
    {
        return oldScore - newScore < T;
    }
    
    public double play()
    {
        // get W/L/D and compute+return score
        
        
        
        for(int i = 0; i < 1000; i++)
        {
            // Generate opponent move
            double decider = rng.nextDouble();
            int opponentMove = decider > 0.2 ? decider > 0.4 ? 2 : 1 : 0;
            
            // Generate our protagonist's move
            int ourMove = decide();
        }
        
        return 0.0d;
    }
    
    private void initializeNeurons()
    {
        decisionValues[0] = rng.nextDouble();
        decisionValues[1] = rng.nextDouble();
        decisionValues[2] = rng.nextDouble();
    }
    
    private void computeCutoffs()
    {
        double scale = decisionValues[0] + decisionValues[1] + decisionValues[2];
        decisionCutoffs[0] = decisionValues[0] / scale;
        decisionCutoffs[1] = decisionCutoffs[0] + decisionValues[1] / scale;
    }
    
    public int decide()
    {
        double x = rng.nextDouble();
        
        if(x < decisionCutoffs[0]) return 0;
        if(x < decisionCutoffs[1]) return 1;
        
        return 2;
    }
    
    public boolean makeBinaryDecision(float[] input)
    {
        float[] layer1Values = new float[layer1.size()];
        for(int i = 0; i < layer1.size(); i++)
        {
            Neuron n = layer1.get(i);
            layer1Values[i] = activator(n.value(input));
        }
        
        
        return true;
    }
    
    public float makeRangeDecision(float min, float max)
    {
        return max;
    }
    
    private float activator(float x)
    {
        if(x <= 0)
        {
            return 0.1f * x;
        }
        else
        {
            return x;
        }
    }
    
    class Neuron
    {
        int inputSize;
        int[] weights;
        
        Neuron(int inputSize)
        {
            
        }
        
        public float value(float[] input)
        {
            float value = 0;
            
            for(int i = 0; i < input.length; i++)
            {
                value += input[i] * weights[i];
            }
            
            return value;
        }
    }
}
