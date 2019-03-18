package io.clientapp.mobile.android;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Button;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by @shridutt.kothari on 08-03-2019.
 */
public class MainActivity extends AppCompatActivity implements NetworkListener{

    private static final String TAG = MainActivity.class.getSimpleName();
    private static final String THING_NAME_RAISE_THE_BAR = "RaiseTheBar";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initForgotPasswordFlow();
        initRegisterUserFlowFlow();
        initHomeScreenFlowFlow();
    }

    /**
     * Initiaize the Home screen UI Flow
     */
    private void initHomeScreenFlowFlow(){
        Button btnLogin = (Button) findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email =  ((EditText) findViewById(R.id.txtEmail)).getText().toString();
                String password =  ((EditText) findViewById(R.id.txtPwd)).getText().toString();
                try {
                    postDetailsOnServer(email, password);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void postDetailsOnServer(String email, String password) throws JSONException {
        NetworkUtils networkUtils = NetworkUtils.getInstance(MainActivity.this);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("email", email);
        jsonObject.put("password", password);
        String restAPIURL = DweetRestServerUtil.getCreateOrDweetUrl(THING_NAME_RAISE_THE_BAR);
        networkUtils.doRestAPICall(restAPIURL, NetworkUtils.METHOD_POST, jsonObject, MainActivity.this);
    }

    /**
     * Initialize the Register User UI Flow
     */
    private void initRegisterUserFlowFlow(){
        TextView register = (TextView)findViewById(R.id.lnkRegister);
        register.setMovementMethod(LinkMovementMethod.getInstance());
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, RegistrationActivity.class);
                startActivity(intent);
            }
        });
    }

    /**
     * Initialize the Forgot Password UI Flow
     */
    private void initForgotPasswordFlow(){
        TextView forgotPassword = (TextView) findViewById(R.id.lnkForgotPassword);
        forgotPassword.setMovementMethod(LinkMovementMethod.getInstance());
        forgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ForgotPasswordActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onSuccessResponse(JSONObject response) {
        if (response != null) {
            Log.i(TAG, ""+response);
            Toast toast = Toast.makeText(getApplicationContext(),
                    "Successfully posted login details on server" + DweetRestServerUtil.getDweetUrl(THING_NAME_RAISE_THE_BAR),
                    Toast.LENGTH_LONG);

            toast.show();
            Intent intent = new Intent(MainActivity.this, HomeActivity.class);
            startActivity(intent);
        } else {
            Log.e(TAG, "Null Response Received From Server");
            Toast toast = Toast.makeText(getApplicationContext(),
                    "Null Response Received From Server",
                    Toast.LENGTH_LONG);

            toast.show();
        }
    }

    @Override
    public void onErrorResponse(Throwable error) {
        if (error != null) {
            Log.e(TAG, ""+error.getMessage());
            Toast toast = Toast.makeText(getApplicationContext(),
                    error.getMessage(),
                    Toast.LENGTH_LONG);

            toast.show();
        }
    }
}