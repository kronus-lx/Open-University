package TMA03Q1;

import java.util.Random;

/**
 * @brief Valid Machine Types
*/
enum MachineType {
    COMPUTER,
    ROBOT,
    TESTER,
    CNC,
    PLC,
    Controller
}

/**
 * @brief Machine class of type Item
*/
public abstract class Machine implements Item {
    /**
     * @brief private fields 
    */
    private String machineUuid;
    
    private long lastServiceDate;
    
    private MachineType type;
    
    protected final long companyStartupDate = 947089800;

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
     * @brief protected method to generate a Service Date
     * @return
     */
    protected long generateLastServiceDate(){
        Random rand = new Random(System.currentTimeMillis());
        long date = rand.nextLong(
            (System.currentTimeMillis() - companyStartupDate) + 1) 
                    + companyStartupDate;
        return date;
    }

    private MachineType generateMachineType(){

        Random rand = new Random(System.currentTimeMillis());

        MachineType[] machines = MachineType.values();

        MachineType t = machines[rand.nextInt(machines.length)];

        return t;
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
    public String uuid(){
        return this.machineUuid;
    }
    
    /**
     * @brief returns the type of item
     * @reutrn item type
    */
    public String itemType(){

        String itemTypeStr;

        switch (this.type) {
            case COMPUTER:
                itemTypeStr = "Computer";
                break;
            case ROBOT:
                itemTypeStr = "Robot";
                break;
            case TESTER:
                itemTypeStr = "Tester";
                break;
            case CNC:
                itemTypeStr = "CNC";
                break;
            case PLC:
                itemTypeStr = "PLC";
                break;
            case Controller:
                itemTypeStr = "Controller";
                break;
            default:
                itemTypeStr = "Unknown";
                break;
        }
        return itemTypeStr;
    }
    /**
     * @brief return the last service date
     * @return service date
    */
    public int lastServiced(){
        return Math.toIntExact(this.lastServiceDate);
    }
}