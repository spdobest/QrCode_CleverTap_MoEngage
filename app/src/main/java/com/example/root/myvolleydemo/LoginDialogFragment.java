package com.example.root.myvolleydemo;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatEditText;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.root.myvolleydemo.model.LoginModel;
import com.example.root.myvolleydemo.net.CustomVolleyPostRequestWithTextPlain;
import com.example.root.myvolleydemo.util.VolleyUtil;
import com.google.gson.Gson;

import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class LoginDialogFragment extends DialogFragment {

    public static final String TAG = "LoginDialogFragment";
    @BindView(R.id.edittextEmail)
    AppCompatEditText edittextEmail;
    @BindView(R.id.edittextPassword)
    AppCompatEditText edittextPassword;
    @BindView(R.id.buttonLogin)
    AppCompatButton buttonLogin;
    @BindView(R.id.tilEmail)
    TextInputLayout tilEmail;
    @BindView(R.id.tilPassword)
    TextInputLayout tilPassword;
    @BindView(R.id.linearRootLoginDialog)
    LinearLayout linearRootLoginDialog;

    boolean isLoginSuccess = false;

    public static LoginDialogFragment newInstance(Context mcontext) {
        LoginDialogFragment mLoginDialogFragment = new LoginDialogFragment();
        Bundle bundle = new Bundle();
        try {
            bundle.putInt("", 1);
        } catch (Exception e) {
            Log.i(TAG, "newInstance:Error" + "\n" + e.getMessage());
        }
        mLoginDialogFragment.setArguments(bundle);
        return mLoginDialogFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //second parameter for always be interface
        Bundle mBundle = getArguments();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialogfragment_login, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        SharedPreferences prefs = getActivity().getSharedPreferences("MyData", Context.MODE_PRIVATE);
        String userName = prefs.getString("userName", "");
        String password = prefs.getString("password", "");

        if (!TextUtils.isEmpty(userName))
            edittextEmail.setText(userName);
        if (!TextUtils.isEmpty(password))
            edittextPassword.setText(password);
    }


    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        return dialog;
    }

    @Override
    public void onStart() {
        super.onStart();
        Dialog dialog = getDialog();
        if (dialog != null) {
            dialog.getWindow().setWindowAnimations(
                    R.style.styleDialogFragment);
            dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.WHITE));
        }
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @OnClick(R.id.buttonLogin)
    public void onViewClicked() {
        doLogin(edittextEmail.getText().toString().trim(), edittextPassword.getText().toString().trim());
    }

    private void doLogin(final String userName, final String password) {

        String url = "http://sb.angelbackoffice.com:8088/SB_Reg.svc/login";

        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("username", userName);
            jsonObject.put("password", password);

            CustomVolleyPostRequestWithTextPlain jsonObjectRequest =
                    new CustomVolleyPostRequestWithTextPlain(
                            Request.Method.POST,
                            url,
                            jsonObject.toString(),
                            new Response.Listener<String>() {

                                @Override
                                public void onResponse(String response) {

                                    Gson gsonMine = new Gson();
                                    LoginModel loginModel = gsonMine.fromJson(response, LoginModel.class);

                                    Log.i(TAG, "onResponse: ");

                                    if (loginModel.getResult()) {

                                        SharedPreferences prefs = getActivity().getSharedPreferences("MyData", Context.MODE_PRIVATE);
                                        SharedPreferences.Editor editor = prefs.edit();
                                        editor.putString("userName", userName);
                                        editor.putString("password", password);
                                        editor.commit();
                                        isLoginSuccess = true;
                                        dismiss();

                                    } else {

                                        /*tilEmail.setError("Invalid Email Id Or Password");
                                        tilPassword.setError("Invalid Email Id Or Password");*/
                                        Snackbar snackbar = Snackbar
                                                .make(linearRootLoginDialog, loginModel.getMessage(), Snackbar.LENGTH_LONG);

                                        snackbar.show();
                                    }


                                }
                            }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Log.i(TAG, "onErrorResponse: " + error.getMessage());
                            error.printStackTrace();
                        }
                    });

            jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(60000,
                    DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                    DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));


            VolleyUtil.getInstance(getActivity()).addToRequestQueue(jsonObjectRequest);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Override
    public void onDismiss(final DialogInterface dialog) {
        super.onDismiss(dialog);
            if(!isLoginSuccess)
                getActivity().finish();

    }
}