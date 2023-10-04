package service;

import java.util.Comparator;
import model.User;

import java.util.LinkedList;

public class BankingService {

  public LinkedList<User> usersList = new LinkedList<>();

  public void addUser(String name, String address, String socialSecurity, double depositAmount) {
    // auto apply with 1 if empty, otherwise check list for increasing 1
    int newUniqueId =
        usersList.isEmpty() ? 1 : usersList.get(usersList.size() - 1).getUniqueId() + 1;
    usersList.add(new User(newUniqueId, name, address, socialSecurity,
        depositAmount));
    usersList.sort(Comparator.comparingInt(User::getUniqueId));
  }

  public void deleteUser(int uniqueId) {
    usersList.removeIf(user -> user.getUniqueId() == uniqueId);
  }

  public void payUserToUser(int payerId, int payeeId, double amount) {
    for (int i = 0, j = i + 1; i < usersList.size() && j < usersList.size();
        i++, j++) {
      if (usersList.get(i).getUniqueId() == payerId && usersList.get(j).getUniqueId() == payeeId) {
        // set payer amount after transaction
        usersList.get(i).setDepositAmount(usersList.get(i).getDepositAmount() - amount);
        // set payee amount after transaction
        usersList.get(j).setDepositAmount(usersList.get(j).getDepositAmount() + amount);
        break;
      }
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

  public void mergeAccounts(int id1, int id2) {
    for (int i = 0, j = i + 1; i < usersList.size() && j < usersList.size();
        i++, j++) {
      if ((usersList.get(i).getUniqueId() == id1 && usersList.get(j).getUniqueId() == id2)
          && (usersList.get(i).getName().equals(usersList.get(j).getName()))
          && (usersList.get(i).getAddress().equals(usersList.get(j).getAddress())
          && (usersList.get(i).getSocialSecurityNumber()
          .equals(usersList.get(j).getSocialSecurityNumber())))) {
        double sumAmount =
            usersList.get(i).getDepositAmount() + usersList.get(j).getDepositAmount();
        usersList.get(i).setDepositAmount(sumAmount);
        usersList.remove(j);
      }
    }
  }

  public void mergeBanks(LinkedList<User> bankOfOrangeCounty, LinkedList<User> bankOfLosAngeles) {
    // TODO
    LinkedList<User> bankOfSouthernCaliforniaUserList = new LinkedList<>();
  }
}
