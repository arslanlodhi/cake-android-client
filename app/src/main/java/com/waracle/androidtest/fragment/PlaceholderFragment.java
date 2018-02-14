package com.waracle.androidtest.fragment;

/**
 * Created by arslanlodhi on 2/14/18.
 */

import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.waracle.androidtest.R;
import com.waracle.androidtest.adapter.MyAdapter;
import com.waracle.androidtest.manager.AppManager;
import com.waracle.androidtest.model.CakeModel;
import com.waracle.androidtest.network.NetworkCallBack;
import com.waracle.androidtest.utils.AppUtils;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

/**
 * Fragment is responsible for loading in some JSON and
 * then displaying a list of cakes with images.
 * Fix any crashes
 * Improve any performance issues
 * Use good coding practices to make code more secure
 */
public class PlaceholderFragment extends Fragment {
    private static final String TAG = PlaceholderFragment.class.getSimpleName();
    public final static String LIST_STATE_KEY = "recycler_list_state";

    RecyclerView recyclerView;
    private MyAdapter mAdapter;
    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this.getActivity());
    Parcelable listState;


    public PlaceholderFragment() { /**/ }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        listState = layoutManager.onSaveInstanceState();
        outState.putParcelable(LIST_STATE_KEY, listState);
        super.onSaveInstanceState(outState);

    }

    @Override
    public void onResume() {
        super.onResume();
        if (listState != null) {
            layoutManager.onRestoreInstanceState(listState);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        recyclerView=rootView.findViewById(R.id.rc_view);
        setRetainInstance(true);
        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        // Create and set the list adapter.
        mAdapter = new MyAdapter();
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(mAdapter);
        // Load data from net.
        loadData();

    }




    private void loadData() {
        AppManager.getInstance().getNetworkManager().enqueue(AppUtils.BASE_URL, new NetworkCallBack() {
            @Override
            public void onResponseReceived(String url, byte[] data, String charset) {
                String jsonText = null;
                ArrayList<CakeModel> cakes=null;
                try {
                    jsonText = new String(data, charset);
                    cakes= AppUtils.parseCakeListJsonArray(jsonText);

                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();

                } catch (Exception e) {
                    e.printStackTrace();

                }
                mAdapter.setItems(cakes);
            }

            @Override
            public void onFailure(String url, String message) {

            }

            @Override
            public void onStart(String url) {

            }

            @Override
            public void onStop(String url) {

            }
        });
    }




}