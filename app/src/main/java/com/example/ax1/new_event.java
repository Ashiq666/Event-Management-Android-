package com.example.ax1;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

public class new_event extends AppCompatActivity {

    Button save, btn1, btn2, cancel;
    EditText name,place,date,capacity,budget,email,phone, description;
    TextView errMsg;
    RadioButton one, two, three;
    String key = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_event);

        one = findViewById(R.id.Indoor);
        two = findViewById(R.id.Outdoor);
        three = findViewById(R.id.Online);
        save = (Button) findViewById(R.id.BSave);
        name = findViewById(R.id.ETName);
        place = findViewById(R.id.ETPlace);
        date = findViewById(R.id.ETDateTime);
        capacity = findViewById(R.id.ETCapacity);
        budget = findViewById(R.id.ETBudget);
        email = findViewById(R.id.ETEmail);
        phone = findViewById(R.id.ETPhone);
        description = findViewById(R.id.ETDescription);
        cancel = (Button)findViewById(R.id.BCancel);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean indoor = one.isChecked();
                boolean outdoor = two.isChecked();
                boolean online = three.isChecked();
                String Name = name.getText().toString().trim();
                String Place = place.getText().toString().trim();
                String Capacity = capacity.getText().toString().trim();
                String Budget = budget.getText().toString().trim();
                String Phone = phone.getText().toString().trim();
                String Description = description.getText().toString().trim();
                String Date = date.getText().toString().trim();
                String Email = email.getText().toString().trim();

                String type;
                String errMsg = "";
                if ( Name.length() < 2){
                    errMsg += "Name is not valid";
                }
                if (Place.length() < 3){
                    errMsg += "Place is not valid";
                }
                if (Capacity.length() < 1){
                    errMsg += "Capacity is not valid";
                }
                if (Budget.length() < 0){
                    errMsg += "Capacity is not valid";
                }
                if (Phone.length() < 11){
                    errMsg += "Phone is not valid";
                }
                if (Description.length() < 10){
                    errMsg += "Description is not valid";
                }
                if (Date.length() < 9){
                    errMsg += "Date is not valid";
                }
                if (Email.length() < 11){
                    errMsg += "Email is not valid";
                }

                if (errMsg.length() == 0){
                    String key, value;
                    key = Name+"--"+System.currentTimeMillis();
                   value = Name+"--"+Place+"--"+Capacity+"--"+Budget+"--"+Phone+"--"+Email+"--"+Description+"--"+Date;
                    showDialog("Do you want to save save the event info?", "save info", "ok","back", key,value);
                }
                else{
                    showDialog(errMsg,"Error", "ok", "back","" ,"" );
                }
            }
        });
      Intent i = getIntent();
      key = i.getStringExtra("key");
      if (key != null){
          KeyValueDB keyvalue7 = new KeyValueDB(getApplicationContext());
          String value = keyvalue7.getValueByKey(key);
          String [] values = value.split("--");
          name.setText(values[0]);
          place.setText(values[1]);
          capacity.setText(values[2]);
          budget.setText(values[3]);
          phone.setText(values[4]);
          email.setText(values[5]);
          description.setText(values[6]);
          date.setText(values[7]);
      }
      }

    private void showDialog(String message, String title, String btn1, String btn2, String key, String value){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(message);
        builder.setTitle(title);
        //setting message manually and performing action on btn click
        builder.setCancelable(false).setPositiveButton(btn1, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        //Util.getInstance().deleteByKey(MainActivity.this, key);
                        dialog.cancel();
                        // loadData();
                        //  adapter.notifyDataSetChanged();
                        if (btn1=="ok"){
                            KeyValueDB keyvalue7 = new KeyValueDB(getApplicationContext());
                            keyvalue7.updateValueByKey(key,value);
                            Intent i = new Intent(getApplicationContext(), MainActivity.class);
                            startActivity(i);
                        }
                    }
                })
                .setNegativeButton(btn2, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {

                        dialog.cancel();
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();
    }
}