package project;

/**
 * Very simple interface, which is the base for class Matrix and Vector
 * @author Klaudia Pawelek
 */
public interface Storage {
    
    /**
     * Name of storage.
     */
    String name = "Storage";
    
    /**
     * Return size of storage.
     * @return int
     */
    public int getSize();

    /**
     * Return name of storage.
     * @return String
     */
    public String getName();

    
}
