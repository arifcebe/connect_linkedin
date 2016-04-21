package com.arifcebe.connectlinkedin.presenter;

import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.arifcebe.connectlinkedin.api.Webservice;
import com.arifcebe.connectlinkedin.view.iface.MainInterfac;
import com.linkedin.platform.APIHelper;
import com.linkedin.platform.LISession;
import com.linkedin.platform.LISessionManager;
import com.linkedin.platform.errors.LIApiError;
import com.linkedin.platform.errors.LIAuthError;
import com.linkedin.platform.listeners.ApiListener;
import com.linkedin.platform.listeners.ApiResponse;
import com.linkedin.platform.listeners.AuthListener;
import com.linkedin.platform.utils.Scope;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by arifcebe on 21/04/16.
 */
public class MainPresenter {
    private static final String TAG = "mainPresenter";
    private MainInterfac iMain;
    private AppCompatActivity context;

    public MainPresenter(AppCompatActivity context, MainInterfac iMain) {
        this.iMain = iMain;
        this.context = context;
    }

    public void getSessionLogin() {

        LISessionManager.getInstance(context)
                .init(context, buildScope(), new AuthListener() {
                    @Override
                    public void onAuthSuccess() {
                        // Authentication was successful.  You can now do
                        // other calls with the SDK.
                        Log.d(TAG, "success otentikasi");
                        setUpdateState();
                        Toast.makeText(context, "success"
                                + LISessionManager.getInstance(context)
                                .getSession().getAccessToken().toString(), Toast.LENGTH_LONG)
                                .show();
                    }

                    @Override
                    public void onAuthError(LIAuthError error) {
                        // Handle authentication errors
                        setUpdateState();
                        Log.d(TAG, "failed otentikasi " + error.toString());
                    }
                }, true);
    }

    private void setUpdateState() {
        LISessionManager sessionManager = LISessionManager.getInstance(context);
        LISession session = sessionManager.getSession();
        boolean accessTokenValid = session.isValid();

        if (accessTokenValid) {
            APIHelper apiHelper = APIHelper.getInstance(context);
            apiHelper.getRequest(context, Webservice.getInstance().getGetUrlPeopleProfile(), new ApiListener() {
                @Override
                public void onApiSuccess(ApiResponse apiResponse) {
                    Log.d(TAG, "result success " + apiResponse.getResponseDataAsJson().toString());
                    JSONObject jo = apiResponse.getResponseDataAsJson();
                    try {
                        //String status = jo.getString("StatusCode");
                        String name = jo.getString("firstName");
                        // fetch profile
                        iMain.fetchProfile(jo);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }

                @Override
                public void onApiError(LIApiError LIApiError) {
                    //jsonText.setText(LIApiError.getMessage().toString());
                }
            });
        }

    }

    private static Scope buildScope() {
        return Scope.build(Scope.R_BASICPROFILE, Scope.W_SHARE);
    }
}
