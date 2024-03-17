package TMA03Q1;

public class Robot extends Machine{
    /**
     * @brief Copy Contructor
     * @param machine
    */
    public Robot(Machine machine)
    {

    }
    /***
     * @brief UUID of Item Object
     * @return
    */ 
    public String uuid(){
        return "";
    }
    
    /**
     * @brief Type of Item
     * @return
     */
    public String itemType(){
        return "";
    }
    
    /**
     * @brief last service of Machine
     * @return
     */
    public int lastServiced(){
        return Math.toIntExact(0);
    }
}
