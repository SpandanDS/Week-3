class InventoryItem {
    String itemName;
    int itemID;
    int quantity;
    double price;
    InventoryItem next;

    public InventoryItem(String itemName, int itemID, int quantity, double price) {
        this.itemName = itemName;
        this.itemID = itemID;
        this.quantity = quantity;
        this.price = price;
        this.next = null;
    }

    public void printItem() {
        System.out.println("Item ID = " + itemID + ", Item Name = " + itemName + ", Quantity = " + quantity + ", Price = " + price);
    }
}

class InventoryManager {
    private InventoryItem head = null;

    public void addAtBeginning(InventoryItem newItem) {
        newItem.next = head;
        head = newItem;
    }

    public void addAtEnd(InventoryItem newItem) {
        if (head == null) {
            head = newItem;
        } else {
            InventoryItem temp = head;
            while (temp.next != null) {
                temp = temp.next;
            }
            temp.next = newItem;
        }
    }

    public void addAtPosition(InventoryItem newItem, int position) {
        if (position <= 0 || head == null) {
            addAtBeginning(newItem);
            return;
        }

        InventoryItem temp = head;
        for (int i = 0; i < position - 1 && temp.next != null; i++) {
            temp = temp.next;
        }
        newItem.next = temp.next;
        temp.next = newItem;
    }

    public void removeByID(int itemID) {
        if (head == null) return;

        if (head.itemID == itemID) {
            head = head.next;
            System.out.println("Item ID = " + itemID + " removed");
            return;
        }

        InventoryItem prev = head;
        InventoryItem current = head.next;
        while (current != null) {
            if (current.itemID == itemID) {
                prev.next = current.next;
                System.out.println("Item ID = " + itemID + " removed");
                return;
            }
            prev = current;
            current = current.next;
        }
        System.out.println("Item ID = " + itemID + " not found");
    }

    public void updateQuantity(int itemID, int newQuantity) {
        InventoryItem temp = head;
        while (temp != null) {
            if (temp.itemID == itemID) {
                temp.quantity = newQuantity;
                System.out.println("Quantity updated for item ID = " + itemID);
                return;
            }
            temp = temp.next;
        }
        System.out.println("Item ID = " + itemID + " not found");
    }

    public void search(String keyword) {
        InventoryItem temp = head;
        boolean found = false;
        while (temp != null) {
            if (String.valueOf(temp.itemID).equals(keyword) || temp.itemName.equalsIgnoreCase(keyword)) {
                temp.printItem();
                found = true;
            }
            temp = temp.next;
        }
        if (!found) System.out.println("No items found for keyword = " + keyword);
    }

    public void displayInventory() {
        if (head == null) {
            System.out.println("No items to display");
            return;
        }

        InventoryItem temp = head;
        System.out.println("Inventory:");
        while (temp != null) {
            temp.printItem();
            temp = temp.next;
        }
    }

    public void calcTotalValue() {
        double total = 0;
        InventoryItem temp = head;
        while (temp != null) {
            total += temp.price * temp.quantity;
            temp = temp.next;
        }
        System.out.printf("Total value = %.2f\n", total);
    }

    public void sort(String criteria, boolean ascending) {
        head = mergeSort(head, criteria, ascending);
        System.out.println("inventory sorted by " + criteria + " in " + (ascending ? "ascending" : "descending") + " order");
    }

    private InventoryItem mergeSort(InventoryItem head, String criteria, boolean asc) {
        if (head == null || head.next == null) return head;

        InventoryItem middle = getMiddle(head);
        InventoryItem nextToMiddle = middle.next;
        middle.next = null;

        InventoryItem left = mergeSort(head, criteria, asc);
        InventoryItem right = mergeSort(nextToMiddle, criteria, asc);

        return sortedMerge(left, right, criteria, asc);
    }

    private InventoryItem sortedMerge(InventoryItem a, InventoryItem b, String criteria, boolean asc) {
        if (a == null) return b;
        if (b == null) return a;

        InventoryItem result;

        boolean condition;

        if (criteria.equalsIgnoreCase("name")) {
            condition = asc ? a.itemName.compareToIgnoreCase(b.itemName) <= 0 : a.itemName.compareToIgnoreCase(b.itemName) > 0;
        } else {
            condition = asc ? a.price <= b.price : a.price > b.price;
        }

        if (condition) {
            result = a;
            result.next = sortedMerge(a.next, b, criteria, asc);
        } else {
            result = b;
            result.next = sortedMerge(a, b.next, criteria, asc);
        }

        return result;
    }

    private InventoryItem getMiddle(InventoryItem head) {
        if (head == null) return head;
        InventoryItem slow = head, fast = head.next;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }
}

public class InventorySystem {
    public static void main(String[] args) {
        InventoryManager manager = new InventoryManager();

        manager.addAtEnd(new InventoryItem("Pen", 101, 50, 1.5));
        manager.addAtBeginning(new InventoryItem("Notebook", 102, 30, 3.75));
        manager.addAtPosition(new InventoryItem("Marker", 103, 20, 2.0), 1);

        manager.displayInventory();

        manager.updateQuantity(103, 25);
        manager.search("Marker");

        manager.calcTotalValue();

        manager.sort("price", true);
        manager.displayInventory();
    }
}
