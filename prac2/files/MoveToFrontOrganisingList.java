/**
 * Name: Thabo Maduna
 * Student Number: u19116498
 */

public class MoveToFrontOrganisingList extends OrganisingList {

    ////// Implement the function below this line //////

    /**
     * Retrieve the node with the specified key and move the accessed node
     * to the front, then recalculate all data fields.
     * @return The node with its data value before it has been moved to the front,
     * otherwise 'null' if the key does not exist.
     */
    @Override
    public ListNode searchNode(Integer key) {

        //Your code goes here
        if(root != null && contains(key)){
            
            ListNode temp = root;
            ListNode prev = null;        
            while(temp != null){
                if(temp.key == key){
                    if(prev != null){
                        prev.next = temp.next;
                        temp.next = root;
                        root = temp;
                    }
                    calculateData();
                    return root;
                }
                prev = temp;
                temp = temp.next;
            }
            return null;
        }
        return null;
    }


}