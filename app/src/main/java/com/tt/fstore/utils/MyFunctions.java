package com.tt.fstore.utils;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.util.Patterns;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MyFunctions {

    static String app_name = "FStore";
    private static ProgressDialog progressDialog;

    /**
     * Sets shared prefs.
     *
     * @param c     the c
     * @param key   the key
     * @param value the value
     */
    public static void setSharedPrefs(Context c, String key, Boolean value) {
        SharedPreferences.Editor editor = c.getSharedPreferences(app_name, Context.MODE_PRIVATE).edit();
        editor.putBoolean(key, value);
        editor.apply();
    }

    public static void setSharedPrefs(Context c, String key, String value) {
        SharedPreferences.Editor editor = c.getSharedPreferences(app_name, Context.MODE_PRIVATE).edit();
        editor.putString(key, value);
        editor.apply();
    }

    public static void setSharedPrefs(Context c, String key, int value) {
        SharedPreferences.Editor editor = c.getSharedPreferences(app_name, Context.MODE_PRIVATE).edit();
        editor.putInt(key, value);
        editor.apply();
    }

    public static void setSharedPrefs(Context c, String key, float value) {
        SharedPreferences.Editor editor = c.getSharedPreferences(app_name, Context.MODE_PRIVATE).edit();
        editor.putFloat(key, value);
        editor.apply();
    }

    /**
     * Gets shared prefs.
     *
     * @param c             the c
     * @param key           the key
     * @param default_value the default value
     * @return the shared prefs
     */
    public static Boolean getSharedPrefs(Context c, String key, Boolean default_value) {
        SharedPreferences prefs = c.getSharedPreferences(app_name, Context.MODE_PRIVATE);
        return prefs.getBoolean(key, default_value);
    }

    public static String getSharedPrefs(Context c, String key, String default_value) {
        SharedPreferences prefs = c.getSharedPreferences(app_name, Context.MODE_PRIVATE);
        return prefs.getString(key, default_value);
    }

    public static int getSharedPrefs(Context c, String key, int default_value) {
        if (c == null) {
            return default_value;
        } else {
            SharedPreferences prefs = c.getSharedPreferences(app_name,
                    Context.MODE_PRIVATE);
            return prefs.getInt(key, default_value);
        }
    }

    public static float getSharedPrefs(Context c, String key, float default_value) {
        if (c == null) {
            return default_value;
        } else {
            SharedPreferences prefs = c.getSharedPreferences(app_name,
                    Context.MODE_PRIVATE);
            return prefs.getFloat(key, default_value);
        }
    }

    /*Delete all local values*/
    public static void logout(Context c) {
        SharedPreferences.Editor prefs = c.getSharedPreferences(app_name, Context.MODE_PRIVATE).edit();
        prefs.clear();
        prefs.commit();
    }

    /*Edittext Empty validation*/
    public static boolean isEmpty(EditText editText) {
        if (editText.getText().toString().trim().isEmpty()){
            editText.setError(Constant.EMPTY_ERROR);
            editText.requestFocus();
            return true;
        }
        return false;
    }

    public static boolean isValidEmail(EditText editText) {
        String email = editText.getText().toString().trim();
        boolean valid = !TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches();
        if (!valid){
            editText.setError("Please fill valid email");
            editText.requestFocus();
            return false;
        }
        return true;
    }

    public static boolean isValidPassword(EditText editText) {
        if (editText.getText().toString().trim().length() < 8){
            editText.setError("Password must be minimum 8 characters");
            editText.requestFocus();
            return false;
        }
        return true;
    }

    public static void showLoader(Context context) {
        progressDialog = new ProgressDialog(context);
        progressDialog.setTitle("loading...");
        progressDialog.setCancelable(false);
        if (!progressDialog.isShowing()){
            progressDialog.show();
        }
    }

    public static void cancelLoader() {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
    }

    public static void serverError(Context c) {
        if (c != null)
            Toast.makeText(c, Constant.SERVER_ERROR, Toast.LENGTH_LONG).show();
    }

    public static final String md5(final String s) {
        final String MD5 = "MD5";
        try {
            // Create MD5 Hash
            MessageDigest digest = MessageDigest.getInstance(MD5);
            digest.update(s.getBytes());
            byte messageDigest[] = digest.digest();

            // Create Hex String
            StringBuilder hexString = new StringBuilder();
            for (byte aMessageDigest : messageDigest) {
                String h = Integer.toHexString(0xFF & aMessageDigest);
                while (h.length() < 2)
                    h = "0" + h;
                hexString.append(h);
            }
            return hexString.toString();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }


    public static String getText(TextInputEditText editText) {
        return editText.getText().toString().trim();
    }

    public static boolean isSamePassword(TextInputEditText passwordEt, TextInputEditText confirmPasswordEt) {
        if (!passwordEt.getText().toString().trim().equals(confirmPasswordEt.getText().toString().trim())){
            confirmPasswordEt.setError("Password doesn't match");
            confirmPasswordEt.requestFocus();
            return false;
        }
        return true;
    }

    public static boolean isValidPhone(TextInputEditText editText) {
        if (editText.getText().toString().trim().length() < 10){
            editText.setError("Phone number must contain 10 digits");
            editText.requestFocus();
            return false;
        }
        return true;
    }
}
