package com.happyshop.activity;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.happyshop.R;
import com.happyshop.adapter.CategorListAdapter;
import com.happyshop.fragment.CategoryDetailFragment;
import com.happyshop.helper.Commons;
import com.happyshop.helper.EndlessRecyclerViewScrollListener;
import com.happyshop.helper.NetworkHelper;
import com.happyshop.helper.RecyclerItemClickListener;
import com.happyshop.helper.VolleyHelper;
import com.happyshop.model.CategoryModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by terril1 on 20/05/16.
 */
public class CategoryListActivity extends BaseActivity {


    ArrayList<CategoryModel> arrayList;
    ProgressBar progressBar;
    private RecyclerView category_recycler_view;
    private CategorListAdapter adapter;
    private TextView emptyView;
    private String categoryname;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_list);

        categoryname = getIntent().getStringExtra("category");
        categoryname = categoryname.replace(" ", "");
        GridLayoutManager gridLayout = new GridLayoutManager(CategoryListActivity.this, 2);

        category_recycler_view = (RecyclerView) findViewById(R.id.category_recycler_view);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        emptyView = (TextView) findViewById(R.id.lblEmptyView);

        category_recycler_view.setHasFixedSize(true);
        category_recycler_view.setLayoutManager(gridLayout);

        arrayList = new ArrayList<>();
        if (NetworkHelper.isOnline(this)) {
            int page = 1;
            callWebserviceToLoadCategory(page, categoryname);
        } else {
            NetworkHelper.noNetworkToast(this);
        }
        category_recycler_view.addOnScrollListener(new EndlessRecyclerViewScrollListener(gridLayout) {
            @Override
            public void onLoadMore(int page, int totalItemsCount) {
                if (NetworkHelper.isOnline(CategoryListActivity.this))
                    callWebserviceToLoadMoreCategory(page + 1, categoryname);
                else
                    NetworkHelper.noNetworkToast(CategoryListActivity.this);
            }
        });

        category_recycler_view.addOnItemTouchListener(
                new RecyclerItemClickListener(CategoryListActivity.this, new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        Bundle bundle = new Bundle();
                        bundle.putParcelable("categoryData", arrayList.get(position));

                        fragmentCall(new CategoryDetailFragment(), bundle, R.id.containerFrame);
                    }
                })
        );
    }

    void callWebserviceToLoadCategory(final int page, final String category) {
        //  Log.e("TAG", page + "");

        new VolleyHelper(this).get("products.json?category=" + category.trim() + "&page=" + page, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {
                    progressBar.setVisibility(View.INVISIBLE);
                    JSONArray array = response.getJSONArray("products");
                    for (int i = 0; i < array.length(); i++) {
                        CategoryModel model = new CategoryModel();
                        JSONObject object = array.getJSONObject(i);
                        model.setId(object.getString("id"));
                        model.setName(object.getString("name"));
                        model.setCategory(object.getString("category"));
                        model.setPrice(Commons.PRICING_INITIALS + " " + object.getString("price"));
                        model.setImgUrl(object.getString("img_url"));
                        model.setUnderSale(object.getBoolean("under_sale"));

                        arrayList.add(model);
                    }

                    adapter = new CategorListAdapter(CategoryListActivity.this, arrayList);
                    category_recycler_view.setAdapter(adapter);

                    if (arrayList.isEmpty()) {
                        category_recycler_view.setVisibility(View.GONE);
                        emptyView.setVisibility(View.VISIBLE);
                    } else {
                        category_recycler_view.setVisibility(View.VISIBLE);
                        emptyView.setVisibility(View.GONE);
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

    void callWebserviceToLoadMoreCategory(final int page, final String category) {
//        Log.e("TAG", page + " Load waala screen hai ");

        new VolleyHelper(this).get("products.json?category=" + category + "&page=" + page, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {
                    progressBar.setVisibility(View.INVISIBLE);
                    JSONArray array = response.getJSONArray("products");
                    for (int i = 0; i < array.length(); i++) {
                        CategoryModel model = new CategoryModel();
                        JSONObject object = array.getJSONObject(i);
                        model.setId(object.getString("id"));
                        model.setName(object.getString("name"));
                        model.setCategory(object.getString("category"));
                        model.setPrice(Commons.PRICING_INITIALS + " " + object.getString("price"));
                        model.setImgUrl(object.getString("img_url"));
                        model.setUnderSale(object.getBoolean("under_sale"));

                        arrayList.add(model);
                    }

                    adapter.notifyItemRangeChanged(0, adapter.getItemCount());
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


}
