package com.happyshop.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by terril1 on 27/04/16.
 */
public class BaseActivity extends AppCompatActivity {

    protected void fragmentCall(Fragment classfragment, Bundle bundle, int containerID) {


        Fragment fragment = classfragment;
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        if (bundle != null)
            fragment.setArguments(bundle);

        transaction.replace(containerID, fragment);
        transaction.addToBackStack(null);
        transaction.commit();

    }

    protected void intentCall(Class<?> className, Bundle bundle, int flag) {
        //  Log.d("TAG", "Checkered Flag : " + flag);
        Intent intent = new Intent();
        intent.setClass(getBaseContext(), className);
        if (bundle != null)
            intent.putExtras(bundle);
        if (flag != 0) {
            intent.addFlags(flag);
        }
        startActivity(intent);
    }
}
