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
import android.widget.TextView;

import com.android.volley.toolbox.NetworkImageView;
import com.happyshop.R;
import com.happyshop.helper.VolleyHelper;
import com.happyshop.model.CategoryModel;

/**
 * Created by terril1 on 21/05/16.
 */
public class CategoryDetailFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_category_detail, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        NetworkImageView imvDataDetail = (NetworkImageView) view.findViewById(R.id.imvDataDetail);
        TextView lblTitleDetail = (TextView) view.findViewById(R.id.lblTitleDetail);
        TextView lblPricingDetail = (TextView) view.findViewById(R.id.lblPricingDetail);

        Bundle bundle = getArguments();
        CategoryModel modelData = bundle.getParcelable("categoryData");

        imvDataDetail.setImageUrl(modelData.getImgUrl() , new VolleyHelper(getActivity()).getImageLoader());
        lblTitleDetail.setText(modelData.getName());
        lblPricingDetail.setText(modelData.getPrice());
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
