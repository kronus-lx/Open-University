package TMA03Q1;

public class Computer extends Machine {
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
        if(machine.itemType() == "Computer"){
            this.lastServiceDate = machine.lastServiced();
            this.computerId = machine.uuid();
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
    public int lastServiced()
    {
        return Math.toIntExact(this.lastServiceDate);
    }
}