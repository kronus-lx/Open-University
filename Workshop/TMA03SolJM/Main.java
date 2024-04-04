import java.util.ArrayList;

public class Main 
{
    public static void main (String[] args)
    {
        /**
         * @brief New Instance of RepairClub  
         */ 
        Club mClub = new RepairClub();
        /**
         * @brief Populate RepairClub with Machines
         */ 
        mClub.populate();
        /**
         * @brief Display all the devices in the repair shop
         */ 
        mClub.display();
        /**
         * @brief return a set of items betweeb two times
         * @params company start date, random date
        */
        ArrayList<Item> items = mClub.itemsServicedPostTime(1712176111L, Item.class);
        System.out.println("Items Serviced Post Date: " + items.toString());
        /**
         *  @brief retrieve an object and remove it from the RepairClub
        */
        Item toBeRemoved = items.get(0);
        mClub.remove(toBeRemoved);
        mClub.display();
        /**
         * @brief Update the Service Date 
        */
        Item toBeUpdated = items.get(1);
        long newServiceDate = 1712243166;
        mClub.updateServiceDate(toBeUpdated, newServiceDate);
        mClub.display();
    }
}