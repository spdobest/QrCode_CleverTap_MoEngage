package com.example.root.myvolleydemo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.ListPopupWindow;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.example.root.myvolleydemo.adapter.CardAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by root on 6/2/17.
 */

public class MyCardActivity extends AppCompatActivity {
    @BindView(R.id.buttonPopUpMenu)
    AppCompatButton buttonPopUpMenu;
    @BindView(R.id.recyclerViewCard)
    RecyclerView recyclerViewCard;


    List<String> listData = new ArrayList<>();
    CardAdapter cardAdapter;
    ListPopupWindow popupWindow;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mycard);
        ButterKnife.bind(this);

        setListData();
    }

    private void setListData() {
        recyclerViewCard.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        listData.add("asdasd");
        listData.add("asdasd");
        listData.add("asdasd");
        listData.add("asdasd");
        listData.add("asdasd");

        cardAdapter = new CardAdapter(MyCardActivity.this, listData);
        recyclerViewCard.setAdapter(cardAdapter);

    }

    @OnClick(R.id.buttonPopUpMenu)
    public void onViewClicked() {
        showListPopUp(buttonPopUpMenu);
    }

    private void showPopUp() {
        //Creating the instance of PopupMenu
        PopupMenu popup = new PopupMenu(MyCardActivity.this, buttonPopUpMenu);
        //Inflating the Popup using xml file
        popup.getMenuInflater()
                .inflate(R.menu.popup_menu, popup.getMenu());

        //registering popup with OnMenuItemClickListener
        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            public boolean onMenuItemClick(MenuItem item) {
                Toast.makeText(
                        MyCardActivity.this,
                        "You Clicked : " + item.getTitle(),
                        Toast.LENGTH_SHORT
                ).show();
                return true;
            }
        });

        popup.show(); //showing popup menu
    }

    private void showListPopUp(final View anchor) {

        List<HashMap<String, Object>> data = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            HashMap<String, Object> map = new HashMap<>();
            map.put("title", "abcd " + i);
            map.put("desc", "desc" + i);
            data.add(map);
        }


        popupWindow = new ListPopupWindow(this);

        ListAdapter adapter = new SimpleAdapter(
                this,
                data,
                R.layout.itemview_popup,
                new String[]{"title", "desc"}, // These are just the keys that the data uses (constant strings)
                new int[]{R.id.tvItemPopup, R.id.tvItemDescPopup}); // The view ids to map the data to

        popupWindow.setAnchorView(anchor);
        popupWindow.setAdapter(adapter);
        popupWindow.setWidth(400);
        popupWindow.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:

                        break;
                    case 1:
                        break;
                    default:
                        break;
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                    }
                });
                popupWindow.dismiss();
            }
        });
        popupWindow.show();
    }
}
