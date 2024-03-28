import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

/**
 * @brief file handler for reporting
*/
/*
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
*/
public class RepairClub implements Club
{
    /**
     * @brief Total Items to be static across all objects
    */
    private static int totalItems;
    /**
     * @brief Contain all Items
    */
    private HashMap<Item, ArrayList<String>> clubItems; 

    /**
     * @brief Initialise private fields
    */
    public RepairClub()
    {
        this.clubItems = new HashMap<Item, ArrayList<String>>();
        RepairClub.totalItems = 0;
    }

    /**
     * @brief (i) Add Item to Workshop
    */
    private void add()
    {
       Machine machine = new Machine(); 

       ArrayList<String> properties = new ArrayList<String>();
       
       if(machine.type().equals("Computer")){
            Computer computer = new Computer(machine);
            
            properties.add(computer.type());
            properties.add(computer.uuid());
            properties.add(Long.toString(computer.lastServiced()));
            
            this.clubItems.put(computer, properties);
            RepairClub.totalItems++;
        }
       else if(machine.type().equals("Robot")){
            Robot robot = new Robot(machine);
            System.out.println("6");
            properties.add(robot.type());
            properties.add(robot.uuid());
            properties.add(Long.toString(robot.lastServiced()));
            
            this.clubItems.put(robot, properties);
            RepairClub.totalItems++;
       }
    }
    
    /**
     * @brief (ii) clear all items
    */
    @Override
    public void clear()
    {
        if(!this.clubItems.isEmpty()){
            this.clubItems.clear();
        }
    }
    
    /**
     * @throws InterruptedException 
     * @brief (iii) populate with test data
    */
    @Override
    public void populate()
    {
        for(int i = 0; i < 10; i++)
        {
            try {
                add();
                TimeUnit.MINUTES.sleep(1);
            }
            catch(InterruptedException ex){
                break;
            }
        }
    }

    /**
     * @brief (iv) remove item from map using Id
     * @param <String>
     * @param id
     * @return
    */
    @Override
    public <String> boolean remove(String uuid)
    {
        return false;
    }

    /**
     * @brief (v) update field from item
     * @param <Item>
     * @param item
     * @param date
     */
    @Override
    public <Item> void updateServiceDate(Item item, String date)
    {
        
    }

    /**
     * @brief (vi) return a ArrayList of Items that are between range of given utc times
     * @param <Item>
     * @param start
     * @param end
     * @return
     */
    @Override
    public <Item> ArrayList<Item> itemsBetweenRange(long start, long end)
    {
        ArrayList<Item> macArrayList = new ArrayList<Item>();

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
        for(HashMap.Entry<Item,ArrayList<String>> entry : this.clubItems.entrySet())
        {
            System.out.println(entry.getKey() + " -->" + entry.getValue().toString());
        }
    }
    /**
     * @brief Write Contents to CSV File
     * @param fname
    */
    public void writeCSVFile(String fname)
    {
        ;
    }
}
