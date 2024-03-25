public class Robot extends Machine {
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
    public int lastServiced()
    {
        return Math.toIntExact(this.lastServiceDate);
    }
}
