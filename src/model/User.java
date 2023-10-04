package model;

import java.io.Serializable;

public class User implements Serializable {

  private static final long serialVersionUID = 1L;
  private int uniqueId;
  private String name;
  private String address;
  // Social Security Number, aka SSN
  private String socialSecurityNumber;
  // An initial deposit amount.
  private double depositAmount;

  public User(int uniqueId, String name, String address, String socialSecurityNumber,
      double depositAmount) {
    this.uniqueId = uniqueId;
    this.name = name;
    this.address = address;
    this.socialSecurityNumber = socialSecurityNumber;
    this.depositAmount = depositAmount;
  }

  public int getUniqueId() {
    return uniqueId;
  }

  public void setUniqueId(int uniqueId) {
    this.uniqueId = uniqueId;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public String getSocialSecurityNumber() {
    return socialSecurityNumber;
  }

  public void setSocialSecurityNumber(String socialSecurityNumber) {
    this.socialSecurityNumber = socialSecurityNumber;
  }

  public double getDepositAmount() {
    return depositAmount;
  }

  public void setDepositAmount(double depositAmount) {
    this.depositAmount = depositAmount;
  }
}