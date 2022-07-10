import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class atm implements ActionListener {
    private ArrayList<account> accounts;
    private account currentAccount;
    private JFrame frame;
    private JPanel login;
    private JPanel signU;
    private JPanel signI;
    private JPanel accountDetails;
    private JPanel depositPanel;
    private JPanel withdrawPanel;
    private JButton signUp;
    private JButton signIn;
    private JButton signOut;
    private JButton ccEnter;
    private JButton newEnter;
    private JButton withdraw;
    private JButton deposit;
    private JTextField creditCardNum;
    private JTextField pin;
    private JTextField newccNum;
    private JTextField newPin;
    private JTextField name;
    private JTextField depositText;
    private JTextField withdrawText;
    private JLabel ccNumLabel;
    private JLabel pinLabel;
    private JLabel Details;

    public account createAccount(String num, String p, String o) {
        return new account(num, p, o);
    }

    public atm() {
        accounts = new ArrayList<account>();
        accounts.add(createAccount("500","1234","Bear"));
        frame = new JFrame();
        login = new JPanel();
        signI = new JPanel();
        signU = new JPanel();
        depositPanel = new JPanel();
        withdrawPanel = new JPanel();
        accountDetails = new JPanel();
        signUp = new JButton("Sign Up");
        signIn = new JButton("Sign In");
        ccEnter = new JButton("Enter");
        newEnter = new JButton("Enter");
        signOut = new JButton("Sign Out");
        withdraw = new JButton("Withdraw");
        deposit = new JButton("Deposit");
        creditCardNum = new JTextField();
        pin = new JTextField();
        newccNum = new JTextField();
        newPin = new JTextField();
        name = new JTextField();
        depositText = new JTextField();
        withdrawText = new JTextField();
        ccNumLabel = new JLabel("Enter credit card number");
        pinLabel = new JLabel("Enter PIN");
        Details = new JLabel("");

        signUp.addActionListener(this);
        signIn.addActionListener(this);
        creditCardNum.addActionListener(this);
        pin.addActionListener(this);
        newccNum.addActionListener(this);
        newPin.addActionListener(this);
        ccEnter.addActionListener(this);
        newEnter.addActionListener(this);
        signOut.addActionListener(this);
        withdraw.addActionListener(this);
        deposit.addActionListener(this);
        depositText.addActionListener(this);
        withdrawText.addActionListener(this);

        creditCardNum.setColumns(12);
        pin.setColumns(4);
        newccNum.setColumns(12);
        newPin.setColumns(4);
        name.setColumns(10);
        depositText.setColumns(6);
        withdrawText.setColumns(6);

        login.setBorder(BorderFactory.createEmptyBorder(30,30,10,30));
        login.add(signIn);
        login.add(signUp);

        signI.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));
        signI.setLayout(new GridLayout(4,1));
        signI.add(ccNumLabel);
        signI.add(creditCardNum);
        signI.add(pinLabel);
        signI.add(pin);
        signI.add(ccEnter);

        signU.setBorder(BorderFactory.createEmptyBorder(30,30,10,30));
        signU.setLayout(new GridLayout(4,1));
        signU.add(new JLabel("Enter credit card"));
        signU.add(newccNum);
        signU.add(new JLabel("Enter pin"));
        signU.add(newPin);
        signU.add(new JLabel("Enter your name"));
        signU.add(name);
        signU.add(newEnter);

        accountDetails.setBorder(BorderFactory.createEmptyBorder(30,30,10,30));
        accountDetails.setLayout(new GridLayout(4,1));
        accountDetails.add(Details);
        accountDetails.add(signOut);
        accountDetails.add(withdraw);
        accountDetails.add(deposit);

        depositPanel.setBorder(BorderFactory.createEmptyBorder(30,30,10,30));
        depositPanel.setLayout(new GridLayout(4,1));
        depositPanel.add(new JLabel("Amount to be deposited"));
        depositPanel.add(depositText);

        withdrawPanel.setBorder(BorderFactory.createEmptyBorder(30,30,10,30));
        withdrawPanel.setLayout(new GridLayout(4,1));
        withdrawPanel.add(new JLabel("Amount to be withdrew"));
        withdrawPanel.add(withdrawText);

        frame.setLocation(100,100);
        frame.add(login, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Hello World");
        frame.setSize(500,300);
        frame.setVisible(true);
    }
    public static void main(String args[]) {
        new atm();
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == signIn) {
            frame.remove(login);
            frame.add(signI);
            frame.revalidate();
            frame.repaint();
        } else if (e.getSource() == signUp) {
            frame.remove(login);
            frame.add(signU);
            frame.revalidate();
            frame.repaint();
        } else if (e.getSource() == ccEnter) {
            for (account acc : accounts) {
                if (acc.accNum.equals(creditCardNum.getText()) && acc.pin.equals(pin.getText())) {
                    frame.remove(signI);
                    frame.add(accountDetails);
                    frame.revalidate();
                    frame.repaint();
                    Details.setText("Owner: " + acc.owner + " Balance: " + acc.getBalance());
                    currentAccount = acc;
                    break;
                } 
            }
        } else if (e.getSource() == signOut) {
            currentAccount = null;
            creditCardNum.setText(null);
            pin.setText(null);
            frame.remove(accountDetails);
            frame.add(login);
            frame.revalidate();
            frame.repaint();
        } else if (e.getSource() == newEnter) {
            accounts.add(createAccount(newccNum.getText(), newPin.getText(), name.getText()));
            newccNum.setText(null);
            newPin.setText(null);
            name.setAction(null);
            frame.remove(signU);
            frame.add(login);
            frame.revalidate();
            frame.repaint();
        } else if (e.getSource() == deposit) {
            frame.remove(accountDetails);
            frame.add(depositPanel);
            frame.revalidate();
            frame.repaint();
        } else if (e.getSource() == depositText) {
            int d = Integer.parseInt(depositText.getText());
            currentAccount.deposit(d);
            Details.setText("Owner: " + currentAccount.owner + " Balance: " + currentAccount.getBalance());
            depositText.setText(null);
            frame.remove(depositPanel);
            frame.add(accountDetails);
            frame.revalidate();
            frame.repaint();
        } else if (e.getSource() == withdraw) {
            frame.remove(accountDetails);
            frame.add(withdrawPanel);
            frame.revalidate();
            frame.repaint();
        } else if (e.getSource() == withdrawText) {
            int w = Integer.parseInt(withdrawText.getText());
            currentAccount.withdraw(w);
            Details.setText("Owner: " + currentAccount.owner + " Balance: " + currentAccount.getBalance());
            withdrawText.setText(null);
            frame.remove(withdrawPanel);
            frame.add(accountDetails);
            frame.revalidate();
            frame.repaint();
        }
    }
}