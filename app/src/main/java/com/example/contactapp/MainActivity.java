package com.example.contactapp;

import android.content.ContentResolver;
import android.database.Cursor;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    List<Contactdetail> contactList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fetch_Contacts();
        initializerecycleview();
    }
    public void fetch_Contacts() {
        contactList = new ArrayList();
        Contactdetail contactdetail;
        ContentResolver contentResolver = getContentResolver();
        Cursor cursor = contentResolver.query(ContactsContract.Contacts.CONTENT_URI, null, null, null, ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME + " ASC");
        if (cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                int hasnumber = Integer.parseInt(cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER)));
                if (hasnumber > 0) {
                    String id = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID));
                    String name = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
                    contactdetail = new Contactdetail();
                    contactdetail.setContactName(name);
                    Cursor phoneCursor = contentResolver.query(
                            ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                            null,
                            ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = ?",
                            new String[]{id},
                            null);
                    if (phoneCursor.moveToNext()) {
                        String phoneNumber = phoneCursor.getString(phoneCursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                        contactdetail.setContactNumber(phoneNumber);
                    }

                    phoneCursor.close();
                    contactList.add(contactdetail);
                    Log.d("innnnn",""+contactList);

                }
            }
        }
    }
                    public void initializerecycleview(){
                        RecyclerView recyclerView=findViewById(R.id.recylcer_view);
                        RecylerViewAdapter recylerViewAdapter=new RecylerViewAdapter(contactList,getApplicationContext());
                        recyclerView.setAdapter(recylerViewAdapter);
                        recyclerView.setLayoutManager(new LinearLayoutManager(this));


            }






}
