package TMA03Q1;

/**
 * @brief Interface of an Item class
*/
public interface Item {
    /***
     * @brief UUID of Item Object
     * @return
    */ 
    public String uuid();
    
    /**
     * @brief Type of Item
     * @return
     */
    public String type();
    
    /**
     * @brief last service of Machine
     * @return
     */
    public int lastServiced();
}
