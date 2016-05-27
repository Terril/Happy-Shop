package com.happyshop.helper;

import android.content.Context;

import com.android.volley.Cache;
import com.android.volley.Network;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.BasicNetwork;
import com.android.volley.toolbox.DiskBasedCache;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;

import static com.android.volley.Request.Method.GET;

/**
 * Created by terrilthomas on 21/03/15.
 */
public class VolleyHelper {

    private final Context mContext;
    private RequestQueue mRequestQueue;
    private ImageLoader mImageLoader;

    public VolleyHelper(Context c) {
        mContext = c;
        mRequestQueue = getRequestQueue();//Volley.newRequestQueue(mContext);;
        if (mImageLoader == null) {
            mImageLoader = new ImageLoader(mRequestQueue, new BitmapLruCache());
        }
    }

    public RequestQueue getRequestQueue() {
        if (mRequestQueue == null) {
            Cache cache = new DiskBasedCache(mContext.getCacheDir(), 10 * 1024 * 1024);
            Network network = new BasicNetwork(new HurlStack());
            mRequestQueue = new RequestQueue(cache, network);
            mRequestQueue.start();
        }
        return mRequestQueue;
    }

    private String contructUrl(String method) {

        // Log.e("URL Form Hua ki nahi bhai" ,Commons.BASE_URL + "/" + method);
        return Commons.BASE_URL + "/" + method;
    }

    public ImageLoader getImageLoader() {
        return mImageLoader;
    }

    public void get(String method, JSONObject jsonRequest,
                    Response.Listener<JSONObject> listener, Response.ErrorListener errorListener) {

        // JsonArrayRequest arrayRequest = new JsonArrayRequest(Request.Method.GET, contructUrl(method), jsonRequest, listener, errorListener);
        JsonObjectRequest objRequest = new JsonObjectRequest(Request.Method.GET, contructUrl(method), jsonRequest, listener, errorListener);
        // Log.e("TAG", "This is the url bro : " + objRequest.getUrl());
        mRequestQueue.add(objRequest);
    }

    public void put(String method, JSONObject jsonRequest,
                    Response.Listener<JSONObject> listener, Response.ErrorListener errorListener) {

        JsonObjectRequest objRequest = new JsonObjectRequest(Request.Method.PUT, contructUrl(method), jsonRequest, listener, errorListener);
        mRequestQueue.add(objRequest);
    }

    public void post(String method,JSONObject jsonRequest,
                     Response.Listener<JSONObject> listener, Response.ErrorListener errorListener) {

        JsonObjectRequest objRequest = new JsonObjectRequest(Request.Method.POST, contructUrl(method), jsonRequest, listener, errorListener);
      //  StringRequest stringRequest = new StringRequest(Request.Method.POST, contructUrl(method), map, listener, errorListener);
        mRequestQueue.add(objRequest);
    }

    public void delete(String method, JSONObject jsonRequest,
                       Response.Listener<JSONObject> listener, Response.ErrorListener errorListener) {

        JsonObjectRequest objRequest = new JsonObjectRequest(Request.Method.DELETE, contructUrl(method), jsonRequest, listener, errorListener);
        mRequestQueue.add(objRequest);
    }

    public void getArray(String method, JSONObject jsonRequest,
                         Response.Listener<JSONArray> listener, Response.ErrorListener errorListener) {

        JsonArrayRequest objRequest = new JsonArrayRequest(contructUrl(method), jsonRequest, listener, errorListener);
        mRequestQueue.add(objRequest);
    }

}
