package TMA03Q1;


import java.util.ArrayList;

/**
 * @brief Interface for a Workshop Class
*/
public interface Club {
    /**
     * @brief (ii) clear all items
    */
    public void clear();
    
    /**
     * @brief (iii) populate with test data
    */
    public void populate();

    /**
     * @brief (iv) remove item from map using Id
     * @param <T>
     * @param id
     * @return
    */
    public <T> boolean remove(T id);

    /**
     * @brief (v) update field from item
     * @param <T>
     * @param item
     * @param date
     */
    public <T> void updateServiceDate(T item, String date);

    /**
     * @brief (vi) return a ArrayList of Items that are between range
     * @param <T>
     * @param start
     * @param end
     * @return
     */
    public <T> ArrayList<T> itemsBetweenRange(long start, long end); 

    /**
     * @brief (vii) return items that were serviced post audit
     * @return items that were serviced post audit
     */
    public int itemsPostAudit();

    /**
     * @brief (viii) display all items logged
     */
    public void display();
};