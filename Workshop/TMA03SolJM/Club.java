import java.io.IOException;
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
     * @param long date
     */
    public <T> void updateServiceDate(T item, long date);

    /**
     * @brief (vi) return a ArrayList of Items that are between range
     * @param <T>
     * @param start
     * @param end
     * @return
     */
    public <T> ArrayList<T> itemsServicedPostTime(long time, Class<T> cl); 

    /**
     * @brief (vii) return items that were serviced post audit
     * @return items that were serviced post audit
     */
    public int itemsPostAudit();

    /**
     * @brief (viii) display all items logged
     */
    public void display();

    /*
     * @brief (TEST) Get total number of items in Club
     */
    public int totalNumOfItems();

    /**
     * @brief Write to CSV File
     */
    public boolean writeCSVFile(String fname) throws IOException, Exception;
};
