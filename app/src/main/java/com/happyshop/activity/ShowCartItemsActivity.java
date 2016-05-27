package com.happyshop.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.happyshop.R;
import com.happyshop.helper.VolleyHelper;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by terril1 on 27/05/16.
 */
public class ShowCartItemsActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart_items);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.cartItem_recycler_view);

        String cartId = getIntent().getStringExtra("cartId");

        callWebserviceToLoadCartItem(cartId);
    }


    private void callWebserviceToLoadCartItem(String cartId) {
        JSONObject object = new JSONObject();
        try {
            object.put("api_key","afa3f22f9d8a67e60b08aa95748df255");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        new VolleyHelper(this).get("carts/" + cartId + ".json?api_key=afa3f22f9d8a67e60b08aa95748df255",
                null,
                new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {

                Log.e("TAG", response.toString());
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

    }
}
