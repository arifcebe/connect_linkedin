package com.arifcebe.connectlinkedin.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.arifcebe.connectlinkedin.R;
import com.arifcebe.connectlinkedin.view.iface.MainInterfac;
import com.linkedin.platform.LISessionManager;

import org.json.JSONObject;

public class MainActivity extends AppCompatActivity implements MainInterfac {

    private static final String TAG = "Main";
    TextView jsonText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        /*try {
            PackageInfo info = getPackageManager().getPackageInfo(
                    "com.arifcebe.connectlinkedin",
                    PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.d("KeyHash:", "key hash " + Base64.encodeToString(md.digest(), Base64.DEFAULT));
            }
        } catch (PackageManager.NameNotFoundException e) {

        } catch (NoSuchAlgorithmException e) {

        }*/

        /*jsonText = (TextView) findViewById(R.id.main_json);

        Button btnClick = (Button) findViewById(R.id.main_btnClick);
        btnClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LISessionManager.getInstance(getApplicationContext()).init(MainActivity.this, buildScope(), new AuthListener() {
                    @Override
                    public void onAuthSuccess() {
                        // Authentication was successful.  You can now do
                        // other calls with the SDK.
                        Log.d(TAG, "success otentikasi");
                        setUpdateState();
                        Toast.makeText(getApplicationContext(), "success" + LISessionManager.getInstance(getApplicationContext()).getSession().getAccessToken().toString(), Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onAuthError(LIAuthError error) {
                        // Handle authentication errors
                        setUpdateState();
                        Log.d(TAG, "failed otentikasi " + error.toString());
                    }
                }, true);
            }
        });*/

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Add this line to your existing onActivityResult() method
        LISessionManager.getInstance(getApplicationContext())
                .onActivityResult(this, requestCode, resultCode, data);

    }

    @Override
    public void fetchProfile(JSONObject jo) {

    }

    @Override
    public void processLogin() {

    }

    @Override
    public void logout() {

    }

   /* private static Scope buildScope() {
        return Scope.build(Scope.R_BASICPROFILE, Scope.W_SHARE);
    }

    private void setUpdateState() {
        LISessionManager sessionManager = LISessionManager.getInstance(getApplicationContext());
        LISession session = sessionManager.getSession();
        boolean accessTokenValid = session.isValid();

        if(accessTokenValid){
            APIHelper apiHelper = APIHelper.getInstance(this);
            apiHelper.getRequest(this, Webservice.getInstance().getGetUrlPeopleProfile(), new ApiListener() {
                @Override
                public void onApiSuccess(ApiResponse apiResponse) {
                    Log.d(TAG,"result success "+apiResponse.getResponseDataAsJson().toString());
                    JSONObject jo = apiResponse.getResponseDataAsJson();
                    try {
                        //String status = jo.getString("StatusCode");
                        String name = jo.getString("firstName");
                        jsonText.setText(name);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }

                @Override
                public void onApiError(LIApiError LIApiError) {
                    jsonText.setText(LIApiError.getMessage().toString());
                }
            });
        }

    }*/

}
