package service;

import datastructure.LinkedList;
import java.util.Comparator;
import model.User;

public class BankingService {

  public LinkedList<User> usersList = new LinkedList<>();


  public void addUser(String name, String address, String socialSecurity, double depositAmount) {
    int newUniqueId = getNewAvailableUniqueId(usersList);
    usersList.add(new User(newUniqueId, name, address, socialSecurity, depositAmount));
    usersList.sort(Comparator.comparingInt(User::getUniqueId));
  }

  public void deleteUser(int uniqueId) {
    usersList.removeIf(user -> user.getUniqueId() == uniqueId);
  }

  public void payUserToUser(int payerId, int payeeId, double amount) {

    // initial both accounts
    User payerAccount = null;
    User payeeAccount = null;

    for (int i = 0; i < usersList.size(); i++) {
      User userAccount = usersList.get(i);

      if (userAccount.getUniqueId() == payerId) {
        payerAccount = userAccount;
      } else if (userAccount.getUniqueId() == payeeId) {
        payeeAccount = userAccount;
      }

      if (payerAccount != null && payeeAccount != null) {
        break;
      }
    }

    if (payerAccount != null && payeeAccount != null) {
      double payerAmount = payerAccount.getDepositAmount();
      double payeeAmount = payeeAccount.getDepositAmount();

      if (payerAmount >= amount) {
        payerAccount.setDepositAmount(payerAmount - amount);
        payeeAccount.setDepositAmount(payeeAmount + amount);
      } else {
        throw new IllegalArgumentException("The payer account does not have enough money");
      }
    } else if (payerAccount == null && payeeAccount == null) {
      throw new IllegalArgumentException("Both payer ID and payee ID do not exist");
    } else if (payerAccount != null) {
      throw new IllegalArgumentException("The payee ID does not exist");
    } else {
      throw new IllegalArgumentException("The payer ID does not exist");
    }
  }


  public float getMedianId() {
    float medianId = 0;
    for (int i = 0; i < usersList.size() / 2; i++) {
      if (usersList.size() % 2 == 0) {
        medianId =
            (float) (usersList.get(i).getUniqueId() + usersList.get(i + 1).getUniqueId()) / 2;
      } else {
        medianId = usersList.get(i).getUniqueId();
      }
    }
    return medianId;
  }

  public String mergeAccounts(int id1, int id2) {
    // initial both accounts and index
    User account1 = null;
    User account2 = null;
    User removedUser = null;
    int index1 = -1;
    int index2 = -1;

    // assign index
    for (int i = 0; i < usersList.size(); i++) {
      if (usersList.get(i).getUniqueId() == id1) {
        account1 = usersList.get(i);
        index1 = i;
      } else if (usersList.get(i).getUniqueId() == id2) {
        account2 = usersList.get(i);
        index2 = i;
        break;
      }
    }

    if (account1 != null && account2 != null) {
      if (account1.getName().equals(account2.getName()) &&
          account1.getAddress().equals(account2.getAddress()) &&
          account1.getSocialSecurityNumber().equals(account2.getSocialSecurityNumber())) {

        // set the sum amount to the smaller ID
        account1.setDepositAmount(account1.getDepositAmount() + account2.getDepositAmount());

        // delete the bigger ID
        if (account1.getUniqueId() > account2.getUniqueId()) {
          removedUser = usersList.remove(index1);

        } else {
          removedUser = usersList.remove(index2);
        }
      } else {
        throw new IllegalArgumentException("The IDs have the different name or address or SSN");
      }
    } else {
      throw new IllegalArgumentException("The accounts with the IDs do not exist");
    }
    return "The User ID: " + removedUser.getUniqueId() + ", User name: " + removedUser.getName()
        + " has been removed after merged";
  }

  public LinkedList<User> mergeBanks(LinkedList<User> bankOfOrangeCounty,
      LinkedList<User> bankOfLosAngeles) {

    LinkedList<User> bankOfSouthernCaliforniaUserList = new LinkedList<>();
    // copy the accounts from the Bank of Orange County
    for (int i = 0; i < bankOfOrangeCounty.size(); i++) {
      User user = bankOfOrangeCounty.get(i);
      bankOfSouthernCaliforniaUserList.add(user);
    }

    for (int i = 0; i < bankOfLosAngeles.size(); i++) {
      User user = bankOfLosAngeles.get(i);

      // check if an account with the same ID already exists in the merged list
      if (containsUserWithId(bankOfSouthernCaliforniaUserList, user.getUniqueId())) {
        int newUniqueId = getNewAvailableUniqueId(bankOfSouthernCaliforniaUserList);
        user.setUniqueId(newUniqueId);
      }
      bankOfSouthernCaliforniaUserList.add(user);
    }
    return bankOfSouthernCaliforniaUserList;
  }

  private int getNewAvailableUniqueId(LinkedList<User> usersList) {
    if (usersList.isEmpty()) {
      return 1;
    } else {
      for (int i = 1; i <= usersList.size(); i++) {
        boolean idExists = false;
        for (int j = 0; j < usersList.size(); j++) {
          if (usersList.get(j).getUniqueId() == i) {
            idExists = true;
            break;
          }
        }
        if (!idExists) {
          return i;
        }
      }
      // no id can be re-assigned to a future new user, set id + 1 from current data
      return usersList.size() + 1;
    }
  }

  private boolean containsUserWithId(LinkedList<User> userList, int uniqueId) {
    for (int i = 0; i < userList.size(); i++) {
      if (userList.get(i).getUniqueId() == uniqueId) {
        return true;
      }
    }
    return false;
  }
}
