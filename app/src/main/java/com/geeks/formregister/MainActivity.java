package com.geeks.formregister;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

public class MainActivity extends AppCompatActivity {

    private EditText emailEditText;
    private EditText passwordEditText;
    private Button login_button;
    private Drawable orangeBackground;
    private Drawable grayBackground;
    private TextView welcomeTextView;
    private TextView descriptionTextView;
    private TextView forgotPasswordTextView;
    private TextView headerTitleTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        orangeBackground = ContextCompat.getDrawable(this, R.drawable.btn_orange);
        grayBackground = ContextCompat.getDrawable(this, R.drawable.btn_gray);

        emailEditText = findViewById(R.id.edit_email);
        passwordEditText = findViewById(R.id.edit_password);
        login_button = findViewById(R.id.login_button);
        welcomeTextView = findViewById(R.id.welcome);
        descriptionTextView = findViewById(R.id.description);
        forgotPasswordTextView = findViewById(R.id.forgot_password);
        headerTitleTextView = findViewById(R.id.header_title);

        updateButtonState();

        emailEditText.addTextChangedListener(textWatcher);
        passwordEditText.addTextChangedListener(textWatcher);

        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkUser();
            }
        });
    }

    private final TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            updateButtonState();
        }

        @Override
        public void afterTextChanged(Editable editable) {
        }
    };

    private void updateButtonState() {
        String email = emailEditText.getText().toString().trim();
        String password = passwordEditText.getText().toString().trim();

        if (!email.isEmpty() && !password.isEmpty()) {
            login_button.setBackground(orangeBackground);
            login_button.setEnabled(true);
        } else {
            login_button.setBackground(grayBackground);
            login_button.setEnabled(false);
        }
    }
    private void checkUser() {
        String email = emailEditText.getText().toString().trim();
        String password = passwordEditText.getText().toString().trim();

        if (email.equals("admin") && password.equals("admin")) {
            Toast.makeText(this, "Вы успешно зарегистрировались" , Toast.LENGTH_SHORT).show();
            showWelcomeScreen();
        } else {
            Toast.makeText(this, "Неправильный логин и пароль", Toast.LENGTH_SHORT).show();
        }
    }

    private void showWelcomeScreen() {
        emailEditText.setVisibility(View.GONE);
        passwordEditText.setVisibility(View.GONE);
        login_button.setVisibility(View.GONE);
        forgotPasswordTextView.setVisibility(View.GONE);
        descriptionTextView.setVisibility(View.GONE);
        headerTitleTextView.setVisibility(View.GONE);
        welcomeTextView.setText("Добро пожаловать!");
    }
}
