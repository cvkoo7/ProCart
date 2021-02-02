package com.horizon.procart.procart;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

public class Bill extends Activity {

    Button pay1;
    private TextView txtInput;

    DatabaseReference databaseRef;
    ListView listviewitems;

    List<Item> itemList;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bill_layout);
        FirebaseApp.initializeApp(this);
        txtInput = (TextView)findViewById(R.id.bill_cartno);
        databaseRef = FirebaseDatabase.getInstance().getReference("Carts/Cart01");
        listviewitems = (ListView) findViewById(R.id.listViewItems);
        Intent i = getIntent();
        final String input = i.getStringExtra("input");
        txtInput.setText("Cart Number : " + input);
        pay1 = (Button) findViewById(R.id.pay1);
        pay1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(Bill.this, MakePayment.class);
                i.putExtra("input",input);
                startActivity(i);
            }
        });
    }
        @Override
        protected void onStart() {
            super.onStart();
            databaseRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                    for(DataSnapshot artistSnapshot : dataSnapshot.getChildren())
                    {
                        Item itms = artistSnapshot.getValue(Item.class);

                        itemList.add(itms);
                    }
                    ItemsList adapter = new ItemsList(Bill.this,itemList);
                    listviewitems.setAdapter(adapter);

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }




}
