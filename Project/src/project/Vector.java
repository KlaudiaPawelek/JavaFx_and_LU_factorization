package project;

/**
 *
 * @author Klaudia
 */
public class Vector {

    private final int size;
    private final double [] vector;
    
    public Vector(int size)
    {
        this.size = size;
        vector = new double[size];
    }
    
    public double[] getVector()
    {
        return this.vector;
    }
    
}
