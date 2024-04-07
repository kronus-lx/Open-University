import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

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
    
    private final long auditDate;

    /**
     * @brief Initialise private fields
    */
    public RepairClub()
    {
        this.clubItems = new HashMap<Item, ArrayList<String>>();
        this.auditDate = 1522755900;
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
            try 
            {
                add();
            }
            catch( Exception ex )
            {
                System.out.println(ex.getMessage());
                break;
            }
        }
    }

    /**
     * @brief (iv) remove item from map using Id
     * @param <Item>
     * @param id
     * @return
    */
    @Override
    public <Item> boolean remove(Item object)
    {
        if(this.clubItems.isEmpty()) {
            System.out.println("[ERROR]: Empty Map");
            return false;
        }
        else 
        {
            try
            {
                this.clubItems.remove(object);
            }
            catch(IndexOutOfBoundsException e)
            {
                System.out.println("[ERROR]: Specified Object Index doesn't exist");
                return false;
            }
        }
        return false;
    }

    /**
     * @brief (v) update field from item
     * @param <Item>
     * @param item
     * @param date
     */
    @Override
    public <Item> void updateServiceDate(Item item, long date)
    {
        /**
         * @brief Find Item and use hashcode to compare and update
        */
        String newDate = Long.toString(date);

        try
        {
            for(Object i : this.clubItems.keySet())
            {
                if(i.hashCode() == item.hashCode())
                {
                    ArrayList<String> properties = this.clubItems.get(i);
                    properties.set(2, newDate);
                }
            }
        }
        catch(Exception e)
        {
            System.out.println("[ERROR]: Error setting new date for object");
        }
    }

    /**
     * @brief (vi) return a ArrayList of Items that were serviced between two timestamps
     * @param <Item>
     * @param start
     * @param end
     * @return
     */
    @Override
    public <Item> ArrayList<Item> itemsServicedPostTime(long time, Class<Item> cl)
    {
        ArrayList<Item> machineList = new ArrayList<Item>();

        try
        {
            for(Object item : this.clubItems.keySet())
            {
                ArrayList<String> tempList =  this.clubItems.get(item);
                
                long serviceTime = (long)Double.parseDouble(tempList.get(2));
                
                if( serviceTime > time )
                {
                    Item t = cl.cast(item);

                    machineList.add(cl.cast(t));
                }
            }
        }
        catch(IndexOutOfBoundsException e)
        {
            System.err.println(e.getMessage());
            return new ArrayList<>();
        }
        catch(ClassCastException e)
        {
            System.err.println(e.getMessage());
            return new ArrayList<>();
        }
        return machineList;
    } 

    /**
     * @brief (vii) return number of items that were serviced post audit date for club
     * @return items that were serviced post audit
     */
    @Override
    public int itemsPostAudit()
    {
        int postAuditItems = 0;

        try
        {
            for(ArrayList<String> i : this.clubItems.values())
            {
                if(Long.parseLong(i.get(2)) > this.auditDate )
                {
                    postAuditItems++;
                }
            }
            
            return postAuditItems;
        }
        catch(ArrayIndexOutOfBoundsException ex)
        {
            System.err.println(ex.getMessage());
            return -1;
        }
    }

    /**
     * @brief (viii) display all items logged
     */
    @Override
    public void display()
    {
        int index = 1;

        for(HashMap.Entry<Item,ArrayList<String>> entry : this.clubItems.entrySet())
        {
            System.out.println("Item: "+ Integer.toString(index) + " "+ entry.getKey() + " --> " + 
                "Attributes: " + entry.getValue().toString());

            index++;
        }
    }
    /**
     * @brief Write Contents to CSV File
     * @param fname
     * @throws Exception 
     * @throws IOException 
    */
    public boolean writeCSVFile(String path, String fname) throws IOException, Exception
    {
        boolean state = false;

        for( ArrayList<String> i : this.clubItems.values() )
        {
            try( FileHandler handler = FileHandler.csvFileHandler(path, fname))
            {
                state = handler.write(String.join(" ",i));
                if(!state) break;
            }
            catch(IOException ex)
            {
                System.err.println(ex.getMessage());
                return false;
            }
        }
        return state;
    }

    /*
     * @brief return total number of items (TESTING!!)
     */
    public int totalNumOfItems()
    {
        return totalItems;
    }
}