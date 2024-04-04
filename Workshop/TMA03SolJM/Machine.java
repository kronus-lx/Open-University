import java.util.Random;

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
public class Machine implements Item {
    /**
     * @brief private fields 
    */
    private String machineUuid; // UUID of Machine
    
    private long lastServiceDate; // Last Date serviced UNIX TIME
    
    private MachineType type; // Type of machine
    
    protected final static long commissionDate = 947089800L;

    /**
     * @brief Protected Method to gen a uuid
    */
    protected String generateUuid(){
        
        String hex = "0123456789Aabcdef";
        
        final int uuid_max_length = 36;
        
        StringBuilder uuid = new StringBuilder();
        
        Random rand = new Random(System.currentTimeMillis());
        
        for(int i = 0; i < uuid_max_length; ++i){
            if(i == 8 || i == 13 || i == 18 || i == 23){
                uuid.append('-');
            } else {
                uuid.append(hex.charAt(rand.nextInt(16)));
            }
        }
        return uuid.toString();
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

        Random rand = new Random(System.currentTimeMillis());

        MachineType[] machines = MachineType.values();

        MachineType type = machines[rand.nextInt(machines.length)];

        return type;
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
     * @brief return the last service date
     * @return service date
    */
    @Override
    public long lastServiced()
    {
        return this.lastServiceDate;
    }
}