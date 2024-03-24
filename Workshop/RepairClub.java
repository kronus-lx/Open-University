package TMA03Q1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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

public class RepairClub implements Club
{
    /**
     * @brief Total Items to be static across all objects
    */
    private static int totalMachines;
    /**
     * @brief Contain all Items
    */
    private HashMap<Item, List<String>> clubItems; 

    /**
     * @brief Initialise private fields
    */
    public RepairClub()
    {
        workshopItems = new HashMap<Machine, ArrayList>();
        totalMachines = 0;
    }

    /**
     * @brief (i) Add Item to Workshop
    */
    private void add()
    {
       Item machine = new Machine(); 

       List<String> properties = new ArrayList<String>();
       
       if(machine.type() == "Computer"){
            Computer computer = new Computer(machine);

            properties.add(computer.type());
            properties.add(computer.uuid());
            properties.add(Integer.toString(computer.lastServiced()));
            
            clubItems.put(computer, properties);
        }
       else if(machine.type() == "Robot"){
            Robot robot = new Robot(machine);

            properties.add(robot.type());
            properties.add(robot.uuid());
            properties.add(Integer.toString(robot.lastServiced()));
            
            clubItems.put(robot, properties);
       }
    }
    
    /**
     * @brief (ii) clear all items
    */
    @Override
    public void clear()
    {
        if(!this.workshopItems.isEmpty()){
            this.workshopItems.clear();
        }
    }
    
    /**
     * @brief (iii) populate with test data
    */
    @Override
    public void populate()
    {
        this.clear();
    }

    /**
     * @brief (iv) remove item from map using Id
     * @param <Machine>
     * @param id
     * @return
    */
    @Override
    public <Machine> boolean remove(Machine id)
    {
        return false;
    }

    /**
     * @brief (v) update field from item
     * @param <Machine>
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
    public <Item> ArrayList<Item> itemsBetweenRange(long start, long end)
    {
        ArrayList<Item> macArrayList = new ArrayList<Machine>();

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