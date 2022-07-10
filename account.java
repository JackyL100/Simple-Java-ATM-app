public class account {
    private int count;
    public String accNum;
    public String pin;
    private int balance;
    public String owner;
    public int getBalance() {
        return balance;
    }
    public void deposit(int amount) {
        balance = amount + balance;
    }
    public void setBalance() {
        count++;
        if (count % 2 == 0) {
            balance = 0;
        } else {
            balance = 100;
        }
    }
    public void withdraw(int amount) {
        if (balance - amount > 0) {
            balance -= amount;
        }
    }
    account(String num, String p, String o) {
        balance = 0;
        accNum = num;
        pin = p;
        owner = o;
    }
}
