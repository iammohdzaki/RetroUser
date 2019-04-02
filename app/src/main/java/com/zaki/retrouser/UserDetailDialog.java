package com.zaki.retrouser;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.zaki.retrouser.model.User;

import java.util.ArrayList;

public class UserDetailDialog extends AppCompatDialogFragment  {
    private ArrayList<User> list;
    private int position;

    private TextView tvAlertName,tvAlertId,tvAlertUserName,tvAlertUserPhone,tvAlertUserWebsite,tvAlertUserEmail;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
        LayoutInflater inflater=getActivity().getLayoutInflater();
        View view=inflater.inflate(R.layout.user_profile_view,null);
        builder.setView(view);

        tvAlertName=view.findViewById(R.id.alert_user_name);
        tvAlertId=view.findViewById(R.id.alert_user_id);
        tvAlertUserEmail=view.findViewById(R.id.alert_user_email);
        tvAlertUserPhone=view.findViewById(R.id.alert_user_phone);
        tvAlertUserWebsite=view.findViewById(R.id.alert_user_website);
        tvAlertUserName=view.findViewById(R.id.alert_user_unique_name);

        return builder.create();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }


//        tvAlertId.setText(String.valueOf(user.getUserId()));
//        tvAlertName.setText(user.getUserName());
//        tvAlertUserEmail.setText(user.getUserEmail());
//        tvAlertUserPhone.setText(user.getUserPhone());
//        tvAlertUserWebsite.setText(user.getUserWebsite());
//        tvAlertUserName.setText(user.getUserUniqueName());


    public interface setUserData{
        void applyTexts(ArrayList<User> userArrayList,int userPosition);
    }
}
