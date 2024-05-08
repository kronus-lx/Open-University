import java.util.ArrayList;
import java.util.HashMap;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.Scanner;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.IOException;  
import java.io.Closeable;
import java.nio.charset.StandardCharsets;

abstract class FileHandler implements Closeable
{    
    private String fileName;
    /**
     * 
     * @param fname
     */
    public FileHandler(String fname)
    {
        this.fileName = fname;
    }
    /**
     * 
     * @param fname
     * @return
     * @throws IOException
     * @throws FileNotFoundException
     */
    public static FileHandler csvFileHandler(String fname) throws IOException, FileNotFoundException
    {
        FileHandler csv = new CSVHandler(fname);

        return csv;
    }
    /**
     * 
     * @param value
     * @return
     */
    public abstract boolean write(String value);
    /**
     * 
     * @return
     */
    public abstract String read();
    /**
     * 
     * @return
     */
    public String filePath() 
    {
        return new File(this.fileName).getAbsolutePath();
    }
    /**
     * 
     * @return
     */
    public String filename()
    {
        return this.fileName;
    } 
    @Override
    public void close() throws IOException, SecurityException, OutOfMemoryError {}
}

class CSVHandler extends FileHandler
{
    private File handler;
    /**
     * 
     * @param fname
     */    
    public CSVHandler(String fname)
    {
        super(fname);

        this.handler = new File(fname);
        
        if(this.handler == null){
            throw new IllegalArgumentException("[ERROR]: Invalid Path Assigned to FileHandler");
        }
    }

    /**
     * @brief Write to file
     * @param string content
    */
    @Override
    public boolean write(String value)
    {   
        try
        {
            if(!this.handler.exists())
            {
                try
                {
                    this.handler.createNewFile();
                }
                catch(IOException ex)
                {
                    System.err.println(ex.getMessage());
                }
            }

            try(FileWriter writer = new FileWriter(handler, true))
            {
                writer.append(String.valueOf(value))
                .append(',')
                .append('\n');
            }

            return true;
        }
        catch(IOException ex)
        {
            System.err.println(ex.getMessage());
            
            ex.printStackTrace();

            return false;
        }
        catch(SecurityException ex)
        {
            System.err.println(ex.getMessage());
            
            ex.printStackTrace();
            
            return false;
        }
    }   
     /**
     * @brief Read file
    */
    @Override
    public String read()
    {
        try {
            if(!this.handler.exists())
            {
                try
                {
                    handler.createNewFile();
                }
                catch(IOException ex)
                {
                    System.err.println(ex.getMessage());
                }
            } 
            Path filePath = Paths.get(handler.getAbsolutePath());
            
            byte[] fileBytes = Files.readAllBytes(filePath);
            
            return new String(fileBytes,StandardCharsets.UTF_8);
        }
        catch(IOException ex)
        {
            System.out.println(ex.getMessage());
            
            ex.printStackTrace();
            
            return null;
        }
        catch(SecurityException ex)
        {
            System.out.println(ex.getMessage());

            ex.printStackTrace();
            
            return null;
        }
        catch(OutOfMemoryError ex)
        {
            System.out.println(ex.getMessage());

            ex.printStackTrace();
            
            return null;
        }
    }
}

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
    @Override
    public boolean writeCSVFile(String fname) throws IOException, Exception
    {
        boolean state = false;

        try( FileHandler handler = FileHandler.csvFileHandler(fname) )
        {
            for( ArrayList<String> i : this.clubItems.values() )
            {
                state = handler.write(String.join(",",i));
                if(!state) break;
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

    /**
     * @return
     */
    @Override
    public String recreateFirst(String file)
    {
       Machine machine = new Machine();

        try{
            // Create a File Object
            File object = new File(file);

            // Create a Scanner Object
            Scanner reader = new Scanner(object);

            // Read the First Line of the CSV
            String[] firstObject = reader.nextLine().split(",");

            if(firstObject.length >= 3)
            {
                // Recreate the object using fields 
                machine.setType(firstObject[0]);

                machine.setUuid(firstObject[1]);

                machine.newServiceDate(Long.parseLong(firstObject[2]));
            } else {
                System.out.println("[ERROR] Insufficient Properties\n");
            }
            // Close the IO Scanner Object
            reader.close();
        }
        catch(FileNotFoundException ex){
            System.out.println(ex);
            ex.printStackTrace();
        }
        return machine.toString();
    }
}