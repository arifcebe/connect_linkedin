package com.arifcebe.connectlinkedin.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.arifcebe.connectlinkedin.R;
import com.arifcebe.connectlinkedin.presenter.MainPresenter;
import com.arifcebe.connectlinkedin.view.iface.MainInterfac;
import com.linkedin.platform.LISessionManager;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity implements MainInterfac {

    private static final String TAG = "Main";
    private MainPresenter presenter;

    private ImageView btnLogin,imgProfile;
    private TextView name, headline;
    private View mainContent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        presenter = new MainPresenter(this,this);

        btnLogin = (ImageView) findViewById(R.id.main_btnClick);
        imgProfile = (ImageView) findViewById(R.id.main_profile);
        name = (TextView) findViewById(R.id.main_name);
        headline = (TextView) findViewById(R.id.main_headline);
        mainContent = findViewById(R.id.main_content);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.getSessionLogin();
            }
        });

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

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Add this line to your existing onActivityResult() method
        LISessionManager.getInstance(getApplicationContext())
                .onActivityResult(this, requestCode, resultCode, data);
    }

    @Override
    public void fetchProfile(JSONObject jo) {
        Log.d(TAG,"result profile "+jo.toString());
        try {
            name.setText(jo.getString("firstName")+" "+jo.getString("lastName"));
            headline.setText(jo.getString("publicProfileUrl"));
            Picasso.with(this)
                    .load(jo.getString("pictureUrl"))
                    .into(imgProfile);

            mainContent.setVisibility(View.VISIBLE);
            btnLogin.setVisibility(View.GONE);

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void processLogin() {

    }

    @Override
    public void logout() {

    }

}
