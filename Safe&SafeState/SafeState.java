
/**
 * SafeState Class - SafeState Managers the State of Class Safe
 *                   Enabling Users to Set/Get the current State of a Safe 
 *                   Object
 *
 * @author Joel Manning
 * @version 23-10-2023
 */
public class SafeState
{
    /**
     * @brief part(b) private instance fields for SafeState
    */
    private String userCode, masterCode, display;    
    private boolean open;         
    /**
     * @brief Constructor for objects of class SafeState
     */
    public SafeState()
    {
        //(c) Initialise fields to default values
        this.userCode = null;
        this.masterCode = "9999";
        this.display = "WELCOME";
        this.open = true;
    }
    
    /**
     * @brief (d)(part i)Method for retrieving display field value
    */
    public String getDisplay()
    {
        return this.display;
    }
    
    /**
     * @brief part(d)(part ii)Method for retrieving open field value
    */
    public boolean isOpen()
    {
        return this.open;
    }
    
    /**
     * @brief part(e) Method for opening the safe
    */
    public boolean open(String aCode)
    {
        if( this.open == true )
        {
            this.display = "ALREADY OPEN";    
        }
        else if( this.open == false )
        {
            if( aCode == this.userCode || aCode == this.masterCode )
            {
                this.open = true;
                this.display = "OPEN";
                this.userCode = null;
            }
            else
            {
                this.display = "INVALID CODE";
            }
        }
        // Will return state of open field
        return this.open;
    }
    /**
     * @brief part(f) Method for checking a valid code for new user code
    */
    public boolean isValidUserCode(String aCode)
    {
        // return value indicating whether passed code is valid
        boolean validCode = false;
        // Immutable value of code length being 4 as specified
        final int codeLength = 4;
        // (f)part(i) Method for checking a valid code for new user code        
        if( aCode.length() != codeLength )
        {
            validCode = false;
        }
        // (f)part(ii) Check if passed code contains appropriate digits
        char[] aCodeArray = aCode.toCharArray();
        // Acquire each char in String and check against approved characters
        for (char c : aCodeArray)
        {
            boolean validChar;   
            // Check expression against approved digits 1 > 9
            validChar = ( ( c != '0' ) && 
                          ( c != '1' ) && 
                          ( c != '2' ) &&
                          ( c != '3' ) &&
                          ( c != '4' ) &&
                          ( c != '5' ) &&
                          ( c != '6' ) &&
                          ( c != '7' ) &&
                          ( c != '8' ) &&
                          ( c != '9' )  );
        
            // If true set validCode to false and break loop
            if( validChar )
            {
                validCode = false;
                break;
            }
            // if char are approved digits then set to true
            else
            {
                validCode = true;
            }
        }
        return validCode;
    }
    
    /**
     * @brief part(g) Method to lock the Safe Providing Correct String has been entered
    */
    public boolean lock (String aCode)
    {
        boolean lockedState = false;
        // part(h)(part i) Check if Safe is Locked
        if(this.open == false)
        {
            lockedState = false;
            this.display = "ALREADY LOCKED";
        }
        else
        {
            // part (g)(part ii) using isValidUserCode method determine is passed code is valid
            if(isValidUserCode(aCode) == true)
            {
                this.open = false;
                this.display = "LOCKED";
                this.userCode = aCode;
                lockedState = true;
            }
            // part (h)(part iii) Set display to `INVALID CODE` if code is invalid
            else      
            {
                this.display = "INVALID CODE";
            }
        }
        return lockedState;
    }
    /**
     * @brief part (h) Display the current status of the Safe
    */
     public String about()
     {
        return "Safe " + this.display;
     }
}