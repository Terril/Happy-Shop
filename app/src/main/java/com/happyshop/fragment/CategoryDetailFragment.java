package com.happyshop.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.NetworkImageView;
import com.happyshop.R;
import com.happyshop.adapter.CategorListAdapter;
import com.happyshop.helper.Commons;
import com.happyshop.helper.NetworkHelper;
import com.happyshop.helper.VolleyHelper;
import com.happyshop.model.CategoryModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by terril1 on 21/05/16.
 */
public class CategoryDetailFragment extends Fragment {

    private NetworkImageView imvDataDetail;
    private TextView lblTitleDetail;
    private TextView lblPricingDetail;
    private TextView lblStatus, lblDesc;
    private ProgressBar progressBar;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_category_detail, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        imvDataDetail = (NetworkImageView) view.findViewById(R.id.imvDataDetail);
        lblTitleDetail = (TextView) view.findViewById(R.id.lblTitleDetail);
        lblPricingDetail = (TextView) view.findViewById(R.id.lblPricingDetail);
        lblStatus = (TextView) view.findViewById(R.id.lblStatusDetail);
        lblDesc = (TextView) view.findViewById(R.id.lblDesc);

        progressBar = (ProgressBar) view.findViewById(R.id.progressDetail);

        Bundle bundle = getArguments();
        CategoryModel modelData = bundle.getParcelable("categoryData");

//        imvDataDetail.setImageUrl(modelData.getImgUrl(), new VolleyHelper(getActivity()).getImageLoader());
//        lblTitleDetail.setText(modelData.getName());
//        lblPricingDetail.setText(modelData.getPrice());
//
//        if (!modelData.getUnderSale()) {
//            lblStatus.setVisibility(View.INVISIBLE);
//        }

        if (NetworkHelper.isOnline(getActivity()))
            callWebserviceToLoadCategoryDetail(modelData.getId());
        else
            NetworkHelper.noNetworkToast(getActivity());
    }

    void callWebserviceToLoadCategoryDetail(String id) {
        //  Log.e("TAG", page + "");

        new VolleyHelper(getActivity()).get("products/" + id + ".json", null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                progressBar.setVisibility(View.INVISIBLE);
                try {
                    JSONObject object = response.getJSONObject("product");
                    lblTitleDetail.setText(object.getString("name"));
                    lblPricingDetail.setText(Commons.PRICING_INITIALS + " " + object.getString("price"));
                    lblDesc.setText(object.getString("description"));
                    imvDataDetail.setImageUrl(object.getString("img_url"), new VolleyHelper(getActivity()).getImageLoader());
                    if (object.getBoolean("under_sale")) {
                        lblStatus.setVisibility(View.VISIBLE);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();

                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
    }

//    @Override
//    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
//        super.onCreateOptionsMenu(menu, inflater);
//            inflater.inflate(R.menu.menu_basket, menu);
//            MenuItem item = menu.findItem(R.id.cartItems);
//            MenuItemCompat.setActionView(item, R.layout.cart_view);
//            View view = MenuItemCompat.getActionView(item);
//            ImageView imvCart = (ImageView) view.findViewById(R.id.imvCart);
//            imvCart.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    goToBasket();
//                }
//            });
//            notifCount = (TextView) view.findViewById(R.id.actionbar_notifcation_textview);
//            notifCount.setText(String.valueOf(mNotifCount));
//
//    }
}
