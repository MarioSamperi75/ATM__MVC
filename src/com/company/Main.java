package com.company;

import com.company.View.View;

public class Main {
    public static void main(String[] args) {
        View view = new View();
    }
}

/* Description of the logic

Requirements and solution
The ATM should operate under the given rules:
● The bills used for withdrawal is removed from the ATM
    - An ATM object has three Bill objects and a total amount available (totalAvailable)
    - Every Bill has a name (ex. "hundred"), a value (ex. 100) and a quantity (ex. 5)
    - totalAvailable: sum of all the (value * quantity)
    - after every withdrawal the instance variable quantity for the right bills is updated depending on the rules.

● The ATM should always return the smallest amount of bills possible
○ Example: a withdrawal of 500 should return 1 x 500 bill rather than 5 x 100 bills if possible
    - While-loops and if-statements check the withdrawal amount and the availability of the bills in the right order
    - That happens after all the controls of the availability of the transaction.

● The ATM can't give out more money than it contains
    - simple check of withdrawal and total amount.
    - if the transaction is not possible, the right response is created and sent.
● The ATM can't give out amounts that the remaining bills don’t add up to
    - check of the divisibility by 100 of a withdrawal.
    - if it is not, the right response is created and sent.
    - check of the last three digits of the withdrawal and the availability of 100 bills and 500 bills
        Details:
        - All the other controls are already done
        - If the withdrawal is minor than 500, just check if there is a sufficient quantity of hundreds
        - If the withdrawal is equal or bigger than 500  --> 2 options:
            - if there is at least a 500 bill, check if (withdrawal hundreds - 500) is available
            - if there is not a 500 bill, check if (withdrawal hundreds) is available
        -if the bills are not available, the right response is created and sent.


 ________________________________________
The assignment
The ATM starts with a predetermined amount of each bill:
● 2 x 1000
● 3 x 500
● 5 x 100
You will make seven withdrawals the specified order. Note that based on the rules listed earlier some of the withdrawals should be declined by the ATM:
1.	1500    --->    amount available
2.	 700    --->    amount available
3.	 400    --->    amount not payable
4.	1100    --->    amount available
5.	1000    --->    amount not available
6.	 700    --->    amount available
7.	 300    --->    amount not available

When all of these withdrawals are completed the ATM is expected to be completely empty of bills and will not have given out more money than it contained at the start.
*/