 

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
    public long lastServiced();

    /**
     * @brief Set Last Serviced
     * @param New Item Service Date
     */
    public void newServiceDate(long serviceDate);

    /**
     * @brief Compare two Items with a UUID
     */
    public boolean compareTo(String objectUUID);
}
