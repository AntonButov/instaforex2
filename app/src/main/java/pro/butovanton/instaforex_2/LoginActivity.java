package pro.butovanton.instaforex_2;

import android.app.Activity;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        final EditText loginEditText = findViewById(R.id.login);
        loginEditText.setText("20234561");
        final EditText passwordEditText = findViewById(R.id.password);
        passwordEditText.setText("ladevi31");
        final Button loginButton = findViewById(R.id.buttonStart);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("login", loginEditText.getText().toString());
                intent.putExtra("password", passwordEditText.getText().toString());
                setResult(Activity.RESULT_OK, intent);
                finish();
            }
        });


    }

}


