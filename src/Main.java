import datastructure.LinkedList;
import model.User;
import service.BankingService;

public class Main {

  public static void main(String[] args) {

    /**
     * Assignment1 Task-1
     * Task-1: Model the list of users as a linked list where each account is a node in the list.
     * Users must be sorted by their ID in the linked list.
     */
    BankingService bankingService = new BankingService();

    /**
     * Assignment1 Task-2
     * Task-2: Write a method/function addUser(user) that adds a new user. Notice that the new user
     * should have a unique ID that is either 1 more than the last unique ID or equal to the first
     * free-up unique ID (by a user closing up their account), whichever comes first.
     */
    bankingService.addUser("Alex", "Irvine1", "111", 100);
    bankingService.addUser("Ben", "Irvine2", "222", 100);
    bankingService.addUser("Chris", "Irvine3", "333", 100);
    bankingService.addUser("Devin", "Irvine4", "444", 100);
    bankingService.addUser("Alex", "Irvine1", "111", 100);
    System.out.println("Task-2");
    // print the user list
    for (int i = 0; i < bankingService.usersList.size(); i++) {
      System.out.println("This is the user " + bankingService.usersList.get(i).getUniqueId() + ":"
          + bankingService.usersList.get(i));
    }

    /**
     * Assignment1 Task-3
     * Task-3: Write a method/function deleteUser(ID) that deletes an existing user. Free up the
     * unique ID while deleting the user. This unique ID can be re-assigned to a future new user.
     */
    bankingService.deleteUser(3);
    System.out.println("Task-3");
    System.out.println("delete User uniqueId 3");
    // print the user list
    for (int i = 0; i < bankingService.usersList.size(); i++) {
      System.out.println("This is the user " + bankingService.usersList.get(i).getUniqueId() + ":"
          + bankingService.usersList.get(i));
    }

    /**
     * Task-4: Write a method/function payUserToUser(payer ID, payee ID, amount) that lets the user
     * with ID1 pay the user with ID3 by amount.
     */
    bankingService.payUserToUser(2, 5, 50);
    System.out.println("Task-4");
    // print the user list
    for (int i = 0; i < bankingService.usersList.size(); i++) {
      System.out.println("This is the user " + bankingService.usersList.get(i).getUniqueId() + ":"
          + bankingService.usersList.get(i));
    }

    /**
     * Task-5: Write a method/function getMedianID() that returns the median of all the account IDs,
     * i.e., the middle node of the linked list. If the number of nodes is even, then you can return
     * the average of the ids of the middle two nodes (return float), and you can also return the
     * first middle node’s id.
     */
    bankingService.getMedianId();
    System.out.println("Task-5");
    System.out.println("This is the MedianID: " + bankingService.getMedianId());

    /**
     * Task-6: Write a method/function mergeAccounts(ID1, ID2) that merges two accounts into one.
     * This function only merges two accounts if they are owned by the same person and identified
     * by the same name, address, and SSN. While merging, sum the two balances, delete the account
     * with the biggest unique ID of the two, and keep the account with the smallest unique ID with
     * the new balance.
     */
    System.out.println("Task-6");
    String str = bankingService.mergeAccounts(1, 5);
    System.out.println(str);
    // print the user list
    for (int i = 0; i < bankingService.usersList.size(); i++) {
      System.out.println("This is the user " + bankingService.usersList.get(i).getUniqueId() + ":"
          + bankingService.usersList.get(i));
    }

    /**
     * Task-7: Imagine another bank, Bank of Los Angeles, which has the same banking protocol and
     * uses the same class as the Bank of Orange County. These two banks have decided to merge into
     * a new bank, Bank of Southern California. Merge the two linked lists into one in the method
     * mergeBanks(bankOfOrangeCounty, bankOfLosAngeles). If both lists have a node with the same ID,
     * create a new ID for one of the duplicates and add it to the new list. While creating the new
     * ID, you have to maintain the incremental property.
     */
    LinkedList<User> bankOfLosAngelesUsersList = new LinkedList<>();
    bankOfLosAngelesUsersList.add(new User(1, "LosAngelesAaron", "Irvine1", "111", 100));
    bankOfLosAngelesUsersList.add(new User(99, "LosAngelesKevin", "Irvine1", "111", 100));
    bankOfLosAngelesUsersList.add(new User(999, "LosAngelesIrene", "Irvine1", "111", 100));

    LinkedList<User> mergeBankUsers = bankingService.mergeBanks(bankingService.usersList,
        bankOfLosAngelesUsersList);
    System.out.println("Task-7");
    // print the user list
    for (int i = 0; i < mergeBankUsers.size(); i++) {
      System.out.println(
          "This is the user list after merged bank: " + mergeBankUsers.get(i).getUniqueId() + ":"
              + mergeBankUsers.get(i));
    }
  }
}