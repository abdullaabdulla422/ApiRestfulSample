package com.app.apirestful;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class DetailsActivity extends AppCompatActivity {
    TextView id,name,category,categoryId,description,empCode,contact,address;
    ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        id = findViewById(R.id.txtName);
        name = findViewById(R.id.txtName);
        category = findViewById(R.id.txtCategory);
        categoryId = findViewById(R.id.txtCategoryId);
        description = findViewById(R.id.txtDescp);
        empCode = findViewById(R.id.txtEmpCode);
        contact = findViewById(R.id.txtContact);
        address = findViewById(R.id.txtAddress);
        imageView = findViewById(R.id.image);
        String id1= String.valueOf(SessionData.getInstance().getStrId());
        id.setText(id1);
        name.setText(SessionData.getInstance().getStrname());
        category.setText(SessionData.getInstance().getStrcategory());
        categoryId.setText(SessionData.getInstance().getStrcategoryId());
        description.setText(SessionData.getInstance().getStrdescription());
        empCode.setText(SessionData.getInstance().getStrempCode());
        contact.setText(SessionData.getInstance().getStrcontact());
        address.setText(SessionData.getInstance().getStraddress());



    }
}