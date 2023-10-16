import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Restaurant {
    private String name;
    private String location;
    public LocalTime openingTime;
    public LocalTime closingTime;
    private List<Item> menu = new ArrayList<Item>();
    Item itemObject;
    public Restaurant(String name, String location, LocalTime openingTime, LocalTime closingTime) {
        this.name = name;
        this.location = location;
        this.openingTime = openingTime;
        this.closingTime = closingTime;
    }

    public boolean isRestaurantOpen() {
        LocalTime currentTime = LocalTime.now();
        return currentTime.isAfter(openingTime) && currentTime.isBefore(closingTime);
    }

    public List<Item> getMenu() {
        return menu;
    }

    private Item findItemByName(String itemName){
        for(Item item: menu) {
            if(item.getName().equals(itemName))
                return item;
        }
        return null;
    }

    public void addToMenu(String name, int price) {
        Item newItem = new Item(name,price);
        menu.add(newItem);
    }

    public void removeFromMenu(String itemName) throws itemNotFoundException {
        Item itemToBeRemoved = findItemByName(itemName);
        if (itemToBeRemoved == null)
            throw new itemNotFoundException(itemName);

        menu.remove(itemToBeRemoved);
    }

    public void displayDetails(){
        System.out.println("Restaurant:"+ name + "\n"
                +"Location:"+ location + "\n"
                +"Opening time:"+ openingTime +"\n"
                +"Closing time:"+ closingTime +"\n"
                +"Menu:"+"\n"+getMenu());

    }

    public String getName() {
        return name;
    }




    public int totalValue(String itemName){
        int total = 0;

        String[] itemsToBeSelected = itemName.split(", ");

        for(String i: itemsToBeSelected){
            Item selectedItem = findItemByName(i);
            if(menu.contains(selectedItem)){
                total = total + selectedItem.getPrice();
            }
        }
        return total;
    }
    //    implemented getOrderValue
    public int getOrderValue(List<String> itemNames) {
        int total = 0;

        for (String itemName : itemNames) {
            Item item = findItemByName(itemName);
            if (item != null) {
                total += item.getPrice();
            }
        }
        return total;
    }



}
