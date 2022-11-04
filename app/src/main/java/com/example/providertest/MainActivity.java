package com.example.providertest;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    private String newId;
    ArrayAdapter<String> adapter;
    List<String> contactsList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d("运行","结点1");

        ListView contactsView = (ListView) findViewById(R.id.contacts_view);
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, contactsList);
        contactsView.setAdapter(adapter);

        Button query = (Button) findViewById(R.id.query);
        Log.i("anniu","========anniu");
        query.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("运行","结点2");
                Uri uri = Uri.parse("content://com.example.android_sy7.provider/contacts");
                getContentResolver();
                Log.d("运行",getContentResolver().toString());
                Cursor cursor = getContentResolver().query(uri,null,null,null,null);
//                Log.i("chaxun","========chaxun");
                Log.d("运行","cursor:"+cursor);
                if(cursor!=null){
                    while (cursor.moveToNext()){
                        @SuppressLint("Range") String name = cursor.getString(cursor.getColumnIndex("name"));
                        @SuppressLint("Range") String tel = cursor.getString(cursor.getColumnIndex("tel"));
                        contactsList.add(name+"                                                 "+tel);

                        Log.i("运行","========jieguo");
                    }
                    adapter.notifyDataSetChanged();
                }else{
                    Log.d("运行","cursor:为空");
                }
            }
        });
//        if(ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS)!= PackageManager.PERMISSION_GRANTED){
//            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_CONTACTS},1);
//        }else{
//            readContacts();
//        }
//    }

//    private void readContacts() {
//
    }
}