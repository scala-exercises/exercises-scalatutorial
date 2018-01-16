/*
 * scala-exercises - exercises-scalatutorial
 * Copyright (C) 2015-2016 47 Degrees, LLC. <http://www.47deg.com>
 */

package scalatutorial.utils

class BankAccount {

  private var balance = 0

  def deposit(amount: Int): Unit =
    if (amount > 0) balance = balance + amount

  def withdraw(amount: Int): Int =
    if (0 < amount && amount <= balance) {
      balance = balance - amount
      balance
    } else throw new Error("insufficient funds")
}
