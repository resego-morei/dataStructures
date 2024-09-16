/**
 * Name: Thabo Maduna
 * Student Number: u19116498
 */

public class TransposeOrganisingList extends OrganisingList {

    ////// Implement the function below this line //////

    /**
     * Retrieve the node with the specified key and swap the
     * accessed node with its predecessor, then recalculate all data fields.
     * @return The node with its data value before it has been moved,
     * otherwise 'null' if the key does not exist.
     */
    @Override
    public ListNode searchNode(Integer key) {

	//Your code goes here
        if(contains(key) && root != null){
            ListNode temp = root;
            ListNode prev = null;
            ListNode extra = null;
            while(temp != null) {
                if (temp.key == key) {
                    // calculateData();
                    if (extra != null) {
                        extra.next = temp;
                        prev.next = temp.next;
                        temp.next = prev;
                    }
                    else if (prev != null) {
                        prev.next = temp.next;
                        temp.next = prev;
                        root = temp;
                    }
                    calculateData();
                    return temp;
                }
                extra = prev;
                prev = temp;
                temp = temp.next;
                // calculateData();
            }
            return null;
        }
        calculateData();
        return null;
    }
}