import java.util.Objects;
import java.util.Random;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @brief Valid Machine Types
*/
enum MachineType{
    COMPUTER,
    ROBOT
}

/**
 * @brief Machine class of type Item
*/
public class Machine extends Object implements Item  {
    /**
     * @brief private fields 
    */
    private String machineUuid; // UUID of Machine
    
    private long lastServiceDate; // Last Date serviced UNIX TIME
    
    private MachineType type; // Type of machine
    
    protected final static long commissionDate = 947089800L;

    /**
     * @brief Protected Method to gen a uuid
     * @return Create a new UUID
    */
    protected String generateUuid(){
        
        UUID uuid = UUID.randomUUID();

        return uuid.toString();
    }

    /**
     * @brief Formate Service Date to DD/MM/YYYY
     * @return
     */
    protected String formattedServiceDate()
    {
        if(this.lastServiceDate > 0)
        {
            Date date = new Date(this.lastServiceDate);

            SimpleDateFormat formatter = new SimpleDateFormat("dd MM yyyy");
    
            String formattedDate = formatter.format(date);
    
            return formattedDate;
        }
        else
        {
            return "";
        }
    }

    /**
     * @brief protected method to generate a service date in unix time
     * @return date
     */
    public static long getCommissionDate()
    {
        return commissionDate;
    }

    /**
     * @brief protected method to generate a service date in unix time
     * @return date
     */
    protected long generateLastServiceDate()
    {
        Random rand = new Random();

        long currentTime = System.currentTimeMillis();

        long dateRange = currentTime - lastServiceDate;
        
        long offset = (long)(rand.nextDouble() * dateRange);

        if(offset < lastServiceDate){
            do {
                offset = (long)(rand.nextDouble() * dateRange);
            }
            while(offset < lastServiceDate);
        }
        return lastServiceDate + offset;
    }
    /**
     * @brief private method to generate a new machine type
     * @return machine type
     */
    private MachineType generateMachineType()
    {
        MachineType[] machines = MachineType.values();
        
        int randomIndex = ThreadLocalRandom.current().nextInt(machines.length);

        return machines[randomIndex];
    }

    public Machine(){
        /**
         * @brief Generate and Assign an uuid
        */
        this.machineUuid = this.generateUuid();
        /**
         * Assign a random service date
        */
        this.lastServiceDate = this.generateLastServiceDate();
        /**
         * Set a type
        */
        this.type = this.generateMachineType();
    }
    /**
     * @brief returns the machine uuid
     * @return uuid of machine
    */
    @Override
    public String uuid()
    {
        return this.machineUuid;
    }
    /**
     * @brief returns the type of item
     * @return item type
    */
    @Override
    public String type()
    {
        String itemTypeStr;

        switch (this.type) {
            case COMPUTER:
                itemTypeStr = "Computer";
                break;
            case ROBOT:
                itemTypeStr = "Robot";
                break;
            default:
                itemTypeStr = "Computer";
                break;
        }
        return itemTypeStr;
    }

     /**
     * @brief set uuid
     */
    @Override
    public void setUuid(String uuid)
    {
        if(!uuid.isEmpty()){
            this.machineUuid = uuid;
        }
    }

    /**
     * @brief Set a type
     * @return successful type assignment
     */
    @Override
    public boolean setType(String type)
    {
        if(type.equals(MachineType.COMPUTER.toString()))
        {
            this.type = MachineType.COMPUTER;
            return true;
        }
        else if(type.equals(MachineType.ROBOT.toString()))
        {
            this.type = MachineType.ROBOT;
            return true;
        }
        else
        {
            return false;
        }
    }

    /**
     * @brief return the last service date
     * @return service date
    */
    @Override
    public long lastServiced()
    {
        return this.lastServiceDate;
    }

    /**
     * @brief Set a new service date
     * @param New Item Service dates
     */
    @Override
    public void newServiceDate(long serviceDate)
    {
        this.lastServiceDate = serviceDate;
    }

    /**
     * @brief Generate HashCode
     * @return Object Hash
     */
    @Override
    public int hashCode()
    {
        String uuid = this.machineUuid;

        String service = Long.toString(commissionDate);

        String machineType = this.type();

        return Objects.hash(uuid, service, machineType);
    }

    /**
     * @brief Compare an object of two types
     */
    @Override
    public boolean equals(Object item)
    {
        if(item != null && item instanceof Machine)
        {
            Machine p = (Machine) item;
            /**
             * @brief UUIDs are Unique so this is the field used
             *        to determine equality
             */
            if(p.uuid().equals(this.machineUuid))
            {
                return true;
            }
            else
            {
                return false;
            }
        }
        return false;
    }

    /**
     * @brief return a string representation of an item
     * @return a string representation of the class
     */
    @Override
    public String toString()
    {
        StringBuilder builder = new StringBuilder();

        if(!this.machineUuid.isEmpty())
        {
            builder.append("\nUUID: " + machineUuid);
        }

        if(this.lastServiceDate > 0)
        {
            builder.append("\nService Date " + this.formattedServiceDate());
        }

        if(!this.type().isEmpty())
        {
            builder.append("\nType: " + this.type());
        }

        String str = builder.toString();

        return str;
    }

    /**
     * @brief Compare two Items with a UUID
     */
    @Override
    public boolean compareTo(String objectUUID)
    {
        if(this.machineUuid.equals(objectUUID))
        {
            return true;
        }
        else
        {
            return false;
        }
    }
}

class Computer extends Machine {
    /**
     * @brief private attributes
    */
    private String computerId;
    
    private long lastServiceDate;    
    /**
     * @brief Copy Contructor for a machine object
     * @param Machine object
    */
    public Computer(Machine machine)
    {
        if(machine.type() == "Computer"){
            this.lastServiceDate = machine.lastServiced();
            this.computerId = machine.uuid();
        }
        else {
            this.computerId = super.generateUuid();
            this.lastServiceDate = super.generateLastServiceDate();
        }
    }
    /**
     * @brief Default Constructor
    */
    public Computer()
    {
        /**
         * @brief Use parent classes methods to 
         *        do the generation of unique attributes
        */
        this.computerId = super.generateUuid();
        this.lastServiceDate = super.generateLastServiceDate();
    }
    /**
     * @brief UUID of Item Object
     * @return uuid of robot
    */
    @Override 
    public String uuid()
    {  
        return this.computerId;
    }

    /**
     * @brief last service of Machine
     * @return last service date of robot in unix time
     */
    @Override
    public long lastServiced()
    {
        return this.lastServiceDate;
    }    
}
class Robot extends Machine {
    /**
     * @brief private attributes
    */
    private String robotId;
    
    private long lastServiceDate;
        
    /**
     * @brief Copy Contructor for a machine object
     * @param Machine object
    */
    public Robot(Machine machine)
    {
        if(machine.type() == "Robot"){
            this.lastServiceDate = machine.lastServiced();
            this.robotId = machine.uuid();
        }
        else{
            this.robotId = super.generateUuid();
            this.lastServiceDate = super.generateLastServiceDate();
        }
    }
    /** 
     * @brief Default constructor for Robot
    */
    public Robot()
    {
        /**
         * @brief Use parent classes methods to 
         *        do the generation of unique attributes
        */
        this.robotId = super.generateUuid();
        this.lastServiceDate = super.generateLastServiceDate();
    }
    /**
     * @brief UUID of Item Object
     * @return uuid of robot
    */
    @Override 
    public String uuid()
    {  
        return this.robotId;
    }

    /**
     * @brief last service of Machine
     * @return last service date of robot in unix time
     */
    @Override
    public long lastServiced()
    {
        return this.lastServiceDate;
    }
}