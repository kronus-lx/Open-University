package TMA03Q1;

import java.util.ArrayList;
import java.util.HashMap;
import java.io.File;

/**
 * @brief file handler for reporting
*/
class FileHandler
{
    private String fileName;
    private File handler; 
    
    public FileHandler(String fname)
    {
        this.fileName = fname;
        this.handler = new File(fname);
    }

}

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
     * @param <Machine>
     * @param item
    */
    @Override
    public <Machine> void add(Machine item){

    }
    
    /**
     * @brief (ii) clear all items
    */
    @Override
    public void clear()
    {

    }
    
    /**
     * @brief (iii) populate with test data
    */
    @Override
    public void populate()
    {

    }

    /**
     * @brief (iv) remove item from map using Id
     * @param <Integer>
     * @param id
     * @return
    */
    @Override
    public <Integer> boolean remove(Integer id)
    {
        return false;
    }

    /**
     * @brief (v) update field from item
     * @param <T>
     * @param item
     * @param date
     */
    @Override
    public <Machine> void updateServiceDate(Machine item, String date)
    {
        
    }

    /**
     * @brief (vi) return a ArrayList of Items that are between range of given utc times
     * @param <Machine>
     * @param start
     * @param end
     * @return
     */
    @Override
    public <Machine> ArrayList<Machine> itemsBetweenRange(long start, long end)
    {
        ArrayList<Machine> macArrayList = new ArrayList<Machine>();

        return macArrayList;
    } 

    /**
     * @brief (vii) return items that were serviced post audit
     * @return items that were serviced post audit
     */
    @Override
    public int itemsPostAudit()
    {
        return 0;
    }

    /**
     * @brief (viii) display all items logged
     */
    @Override
    public void display()
    {

    }
    /**
     * @brief Write Contents to CSV File
     * @param fname
    */
    public void writeCSVFile(String fname){

    }
}