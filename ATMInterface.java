import java.util.Scanner;

class Account {
    String name;
    String userName;
    String password;
    String accountNumber;
    float balanceAmount = 1000000f;
    int noOfTranscations = 0;
    String transactionHistory = "";

    Scanner sc = new Scanner(System.in);

    public void register() {
        System.out.print("\nEnter Your Name - ");
        this.name = sc.nextLine();
        System.out.print("\nEnter Your Username - ");
        this.userName = sc.nextLine();
        System.out.print("\nEnter Your Password - ");
        this.password = sc.nextLine();
        System.out.print("\nEnter Your Account Number - ");
        this.accountNumber = sc.nextLine();
        System.out.println("\nRegistration completed..kindly login");
    }

    public boolean login() {
        boolean isLogin = false;
        while (!isLogin) {
            System.out.print("\nEnter Your Username - ");
            String Username = sc.nextLine();
            if (Username.equals(userName)) {
                while (!isLogin) {
                    System.out.print("\nEnter Your Password - ");
                    String Password = sc.nextLine();
                    if (Password.equals(password)) {
                        System.out.print("\nLogin successful!!");
                        isLogin = true;
                    } else {
                        System.out.println("\nIncorrect Password");
                    }
                }
            } else {
                System.out.println("\nUsername not found");
            }
        }
        return isLogin;
    }

    public void checkBalance() {
        System.out.println("\n" + balanceAmount + " Rs");
    }

    public void deposit() {

        System.out.print("\nEnter amount to deposit - ");
        float amount = sc.nextFloat();
        try {
            if (amount <= 100000f) {
                noOfTranscations++;
                balanceAmount += amount;
                System.out.println("\nSuccessfully Deposited");
                String str = amount + " Rs deposited\n";
                transactionHistory = transactionHistory.concat(str);
            } else {
                System.out.println("\nSorry...Limit is 100000.00");
            }
        } catch (Exception e) {
        }
    }

    public void withdraw() {
        System.out.print("\nEnter amount to withdraw - ");
        float amount = sc.nextFloat();
        try {
            if (balanceAmount >= amount) {
                noOfTranscations++;
                balanceAmount -= amount;
                System.out.println("\nWithdraw Successfully");
                String str = amount + " Rs Withdrawed\n";
                transactionHistory = transactionHistory.concat(str);
            } else {
                System.out.println("\nInsufficient Balance");
            }

        } catch (Exception e) {
        }
    }

    public void transfer() {

        Scanner sc = new Scanner(System.in);
        System.out.print("\nEnter Receipent's Name - ");
        String receipent = sc.nextLine();
        System.out.print("\nEnter amount to transfer - ");
        float amount = sc.nextFloat();

        try {
            if (balanceAmount >= amount) {
                if (amount <= 50000f) {
                    noOfTranscations++;
                    balanceAmount -= amount;
                    System.out.println("\nSuccessfully Transfered to " + receipent);
                    String str = amount + " Rs transfered to " + receipent + "\n";
                    transactionHistory = transactionHistory.concat(str);
                } else {
                    System.out.println("\nSorry...Limit is 50000.00");
                }
            } else {
                System.out.println("\nInsufficient Balance");
            }
        } catch (Exception e) {
        }
    }

    public void transactionHistory() {
        if (noOfTranscations == 0) {
            System.out.println("\nEmpty");
        } else {
            System.out.println("\n" + transactionHistory);
        }
    }
}

public class ATMInterface {

    public static int input(int val) {
        int userInput;
        Scanner sc = new Scanner(System.in);
        while (true) {
            userInput = sc.nextInt();
            if (userInput > val || userInput < 1) {
                System.out.println("Enter value between 1 to " + val);
            } else {
                break;
            }
        }
        return userInput;
    }

    public static void main(String args[]) {
        Account bank = new Account();
        System.out.println("--------------- WELCOME TO ATM INTERFACE ---------------");
        System.out.println("1.Register\n2.Exit\n");
        System.out.println("Enter choice");
        int choice = input(2);
        if (choice == 1) {
            bank.register();
            while (true) {
                System.out.println("\n1.Login \n2.Exit");
                System.out.print("Enter Your Choice - ");
                int ch = input(2);
                if (ch == 1 && bank.login()) {
                    while (true) {
                        System.out.println(
                                "\n1.Withdraw \n2.Deposit \n3.Transfer \n4.Check Balance \n5.Transaction History \n6.Exit");
                        System.out.print("\nEnter Your Choice - ");
                        int c = input(6);
                        switch (c) {
                            case 1:
                                bank.withdraw();
                                break;
                            case 2:
                                bank.deposit();
                                break;
                            case 3:
                                bank.transfer();
                                break;
                            case 4:
                                bank.checkBalance();
                                break;
                            case 5:
                                bank.transactionHistory();
                                break;
                            case 6:
                                System.exit(0);
                        }
                    }
                }
            }
        }
    }
}
