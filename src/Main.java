import service.BankingService;

public class Main {

  public static void main(String[] args) {
    BankingService bankingService = new BankingService();
    bankingService.addUser("Jason", "187 Steely", "123", 100);
    bankingService.addUser("Alex", "187 Steely", "123", 100);
    bankingService.addUser("Alex", "187 Steely", "123", 100);
//    bankingService.payUserToUser(1, 2, 50);
//    bankingService.deleteUser(1);

    bankingService.mergeAccounts(2, 3);

    System.out.println("This is the MedianID: " + bankingService.getMedianId());

    System.out.println(bankingService.usersList);
    for (int i = 0; i < bankingService.usersList.size(); i++) {
      System.out.println("This is the user " + bankingService.usersList.get(i).getUniqueId() + ":"
          + bankingService.usersList.get(i));
    }
  }

}