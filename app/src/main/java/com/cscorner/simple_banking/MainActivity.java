package com.cscorner.simple_banking;


import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private double balance;
    private EditText initialBalanceEditText;
    private EditText transactionAmountEditText;
    private TextView balanceTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initialBalanceEditText = findViewById(R.id.initialBalanceEditText);
        transactionAmountEditText = findViewById(R.id.transactionAmountEditText);
        balanceTextView = findViewById(R.id.balanceTextView);
        Button createAccountButton = findViewById(R.id.createAccountButton);
        Button depositButton = findViewById(R.id.depositButton);
        Button withdrawButton = findViewById(R.id.withdrawButton);
        Button checkBalanceButton = findViewById(R.id.checkBalanceButton);

        createAccountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createAccount();
            }
        });

        depositButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deposit();
            }
        });

        withdrawButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                withdraw();
            }
        });

        checkBalanceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkBalance();
            }
        });
    }

    private void createAccount() {
        String initialBalanceString = initialBalanceEditText.getText().toString();
        if (initialBalanceString.isEmpty()) {
            Toast.makeText(this, "Please enter an initial balance", Toast.LENGTH_SHORT).show();
            return;
        }
        balance = Double.parseDouble(initialBalanceString);
        balanceTextView.setText("Balance: $" + balance);
        initialBalanceEditText.setText("");
        Toast.makeText(this, "Account created with balance $" + balance, Toast.LENGTH_SHORT).show();
    }

    private void deposit() {
        String amountString = transactionAmountEditText.getText().toString();
        if (amountString.isEmpty()) {
            Toast.makeText(this, "Please enter an amount to deposit", Toast.LENGTH_SHORT).show();
            return;
        }
        double amount = Double.parseDouble(amountString);
        balance += amount;
        balanceTextView.setText("Balance: $" + balance);
        transactionAmountEditText.setText("");
        Toast.makeText(this, "Deposited $" + amount, Toast.LENGTH_SHORT).show();
    }

    private void withdraw() {
        String amountString = transactionAmountEditText.getText().toString();
        if (amountString.isEmpty()) {
            Toast.makeText(this, "Please enter an amount to withdraw", Toast.LENGTH_SHORT).show();
            return;
        }
        double amount = Double.parseDouble(amountString);
        if (amount > balance) {
            Toast.makeText(this, "Insufficient balance", Toast.LENGTH_SHORT).show();
            return;
        }
        balance -= amount;
        balanceTextView.setText("Balance: $" + balance);
        transactionAmountEditText.setText("");
        Toast.makeText(this, "Withdrew $" + amount, Toast.LENGTH_SHORT).show();
    }

    private void checkBalance() {
        Toast.makeText(this, "Current balance: $" + balance, Toast.LENGTH_SHORT).show();
    }
}
