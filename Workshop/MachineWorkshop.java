package TMA03Q1;

import java.util.ArrayList;
import java.util.HashMap;

public class MachineWorkshop implements Workshop
{
    /**
     * @brief Total Items to be static across all objects
    */
    private static int totalItems = 0;
    /**
     * @brief Contain all Items
    */
    private HashMap<Integer, Machine> workshopItems; 

    /**
     * Initialise
    */
    public MachineWorkshop(){
        workshopItems = new HashMap<>();
    }

    /**
     * @brief (i) Add Item to Workshop
     * @param <T>
     * @param item
    */
    public <Machine> void add(Machine item){

    }
    
    /**
     * @brief (ii) clear all items
    */
    public void clear()
    {

    }
    
    /**
     * @brief (iii) populate with test data
    */
    public void populate()
    {

    }

    /**
     * @brief (iv) remove item from map using Id
     * @param <T>
     * @param id
     * @return
    */
    public <T> boolean remove(T id)
    {
        return false;
    }

    /**
     * @brief (v) update field from item
     * @param <T>
     * @param item
     * @param date
     */
    public <Machine> void updateServiceDate(Machine item, String date)
    {
        
    }

    /**
     * @brief (vi) return a ArrayList of Items that are between range
     * @param <T>
     * @param start
     * @param end
     * @return
     */
    public <Machine> ArrayList<Machine> itemsBetweenRange(int start, int end)
    {
        ArrayList<Machine> macArrayList = new ArrayList<Machine>();

        return macArrayList;
    } 

    /**
     * @brief (vii) return items that were serviced post audit
     * @return items that were serviced post audit
     */
    public int itemsPostAudit()
    {
        return 0;
    }

    /**
     * @brief (viii) display all items logged
     */
    public void display()
    {

    }
}