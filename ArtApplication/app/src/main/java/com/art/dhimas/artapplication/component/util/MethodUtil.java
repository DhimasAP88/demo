package com.art.dhimas.artapplication.component.util;

import android.app.Activity;
import android.app.Application;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.art.dhimas.artapplication.R;

public class MethodUtil extends Application {

    public static void showCustomToast(Activity activityContext, String message, int image) {

        if (activityContext != null && !TextUtils.isEmpty(message)) {
            LayoutInflater inflater = activityContext.getLayoutInflater();
            View layout = inflater.inflate(R.layout.custom_toast, (ViewGroup) activityContext.findViewById(R.id.toast_layout_root));
            TextView messageTextView = layout.findViewById(R.id.textview_message);
            ImageView imageView = layout.findViewById(R.id.imageview_icon);
            messageTextView.setText(message);
            if (image != 0) imageView.setImageResource(image);
            else imageView.setVisibility(View.GONE);
            Toast toast = new Toast(activityContext);
            toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
            toast.setDuration(Toast.LENGTH_SHORT);
            toast.setView(layout);
            toast.show();
        }
    }

    public static boolean validateLogin(String username, String password, Activity activity) {
        if (TextUtils.isEmpty(username)) {
            MethodUtil.showCustomToast(activity, "Username should not be empty", R.drawable.ic_error_login);
            return false;
        }

        if (TextUtils.isEmpty(password)) {
            MethodUtil.showCustomToast(activity, "Password should not be empty", R.drawable.ic_error_login);
            return false;
        }

        return true;
    }
}
