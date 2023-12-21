/**
 * @brief Java Library Imports
*/
import java.util.ArrayList;
/**
 * Class 'Safe' for containing a number of limited items to be safely stored
 *
 * @author Joel Manning
 * @version 31-10-2023
 */
public class Safe
{
    /**
     * @brief  part(b) importing external class state and 
     *         declaring maxItems and contents of safe
    */
    private SafeState state;
    private int maxItems;
    public ArrayList<String> contents;
    /**
     * @brief part(c)(i,ii,iii)
     * @param number of maximum items used
     */
    public Safe(int aMaxItems)
    {
        this.state = new SafeState();
        this.contents = new ArrayList<String>();
        this.maxItems = aMaxItems;
    }
    /**
     * part(d) Attempt to add contents
     * @param item to add to the safe ArrayList
    */
    public void addToContents(String item)
    {
        if(this.contents.size() < this.maxItems)
        {
            contents.add(item);   
        }
    }
    /**
     * @brief part(e) remove an item from contents
     * @param stored item for removal 
    */
    public void removeFromContents(String item)
    {
        boolean found = false;
        // index used to enable removal of item using java String remove(index)
        for(int i = 0; i < this.contents.size(); i++)
        {
            if(contents.get(i) == item)
            {
                found = true;
                this.contents.remove(i);
            }
        }
        if(found)
        {
           System.out.println("Removed " + item); 
        }
        else
        {
            System.out.println("Item " + item + " not in safe"); 
        }
    }
    /**
     * @brief part(f) Display all item contents 
    */
    public void display()
    {
        for(String prop : this.contents) // Capture and Display using for_each
        {
            System.out.println(prop);
        }
    }
    
    /**
     * @brief part(g) Empty method to clear the array list
    */
    public void empty()
    {
        for(int i = 0; i < this.contents.size(); i++) // Using index to remove individual Strings in array
        {
            System.out.println("Removed " + this.contents.get(i));
            this.contents.remove(i);
        }
    }
    /**
     * @brief part(h) Attempt to open the safe
     * @param Code to open the safe must be valid code or master code
     * @return boolean value indicating the open status of the safe
    */
    public boolean open(String aCode)
    {
        boolean SafeOpen = false;
        
        if(this.state.isOpen() == true)
        {
            SafeOpen = true;
        }
        else
        {
            SafeOpen = this.state.open(aCode);
        }
        
        return SafeOpen;
    }
    /**
     * @brief part(i) Lock the Safe using field state object
     * @param Code passed to lock the safe(String)
     * @return boolean indicating the locked status of the safe true | false
    */
    public boolean lock(String aCode)
    {
        boolean isLocked = false;
        
        if(this.state.isOpen() == false)
        {
            isLocked = false;
        }
        else 
        {  
            isLocked = this.state.lock(aCode);
        }
        return isLocked;
    }
}
