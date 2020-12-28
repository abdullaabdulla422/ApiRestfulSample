package com.app.apirestful;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.nfc.Tag;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

import javax.net.ssl.HttpsURLConnection;

public class MainActivity extends AppCompatActivity {
    Button location, data;
    ListView listView;
    ArrayList<DataObject> dataObjectArrayList = new ArrayList<>();
    private String TAG = MainActivity.class.getSimpleName();
    MyAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        location = (Button) findViewById(R.id.map);
        location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(MainActivity.this,MapActivity.class);
                startActivity(i);
            }
        });

        data = (Button) findViewById(R.id.data);
        listView=(ListView)findViewById(R.id.listView);

        data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new GetContacts().execute();
            }
        });


    }

    private class GetContacts extends AsyncTask<Void, Void, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Toast.makeText(MainActivity.this,"loading",Toast.LENGTH_LONG).show();

        }

        @Override
        protected Void doInBackground(Void... arg0) {
            HttpHandler sh = new HttpHandler();
            // Making a request to url and getting response
            String url = "http://aryu.co.in/tracking/viewreport.php";
            String jsonStr = sh.makeServiceCall(url);

            Log.e(TAG, "Response from url: " + jsonStr);
            if (jsonStr != null) {
                try {
                    JSONObject jsonObj = new JSONObject(jsonStr);

                    // Getting JSON Array node
                    JSONArray contacts = jsonObj.getJSONArray("Success");

                    // looping through All Contacts

                    for (int i = 0; i < contacts.length(); i++) {
                        JSONObject jsonobject = contacts.getJSONObject(i);
                        int id = jsonobject.getInt("id");
                        String name = jsonobject.getString("name");
                        String category = jsonobject.getString("category");
                        String categoryid = jsonobject.getString("categoryid");
                        String address = jsonobject.getString("address");
                        String description = jsonobject.getString("description");
                        String contact = jsonobject.getString("contact");
                        String empcode = jsonobject.getString("empcode");
                        String image=jsonobject.getString("image");
                        Log.d("TAG", "id:" + id);
                        Log.d("TAG", "name:" + name);
                        Log.d("TAG", "category:" + category);
                        Log.d("TAG", "categoryid:" + categoryid);
                        Log.d("TAG", "address:" + address);
                        Log.d("TAG", "description:" + description);
                        Log.d("TAG", "contact:" + contact);
                        Log.d("TAG", "empcode:" + empcode);
                        Log.d("TAG", "empcode:" + image);
                        DataObject dataObject = new DataObject(id,name,category,categoryid,address,description,contact,empcode,image);
                        dataObject.setId(id);
                        dataObject.setName(name);
                        dataObject.setCategory(category);
                        dataObject.setCategoryid(categoryid);
                        dataObject.setContact(contact);
                        dataObject.setAddress(address);
                        dataObject.setDescription(description);
                        dataObject.setEmpcode(empcode);
                        dataObject.setImage(image);
                        dataObjectArrayList.add(dataObject);
                    }
                } catch (final JSONException e) {
                    Log.e(TAG, "Json parsing error: " + e.getMessage());
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getApplicationContext(),
                                    "Json parsing error: " + e.getMessage(),
                                    Toast.LENGTH_LONG).show();
                        }
                    });

                }

            } else {
                Log.e(TAG, "Couldn't get json from server.");
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(),
                                "Couldn't get json from server. Check LogCat for possible errors!",
                                Toast.LENGTH_LONG).show();
                    }
                });
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            adapter=new MyAdapter(MainActivity.this,dataObjectArrayList);
            Log.d("TAG", "dataObjectArrayList:" + dataObjectArrayList.size());

            listView.setAdapter(adapter);
        }
    }
    public class MyAdapter extends BaseAdapter {
        private Context context;
        LinearLayout famlist;
        private ArrayList<DataObject> arrayList;
        private TextView id,name,category;
        ImageView imageView;

        String strId,strname,strcategory,strcategoryId,strdescription,strempCode,strcontact,straddress,strImage;
        public MyAdapter(Context context, ArrayList<DataObject> arrayList) {
            this.context = context;
            this.arrayList = arrayList;
        }


        @Override
        public int getCount() {
            return arrayList.size();
        }

        @Override
        public Object getItem(int position) {
            return position;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int i, View convertView, ViewGroup parent) {
            convertView = LayoutInflater.from(context).inflate(R.layout.child_family, parent, false);
            id = convertView.findViewById(R.id.txtName);
            name = convertView.findViewById(R.id.txtName);
            category = convertView.findViewById(R.id.txtCategory);


                strId= String.valueOf(arrayList.get(i).getId());
                strname= arrayList.get(i).getName();
                strcategory=  arrayList.get(i).getCategory();
                strcategoryId=  arrayList.get(i).getCategoryid();
                straddress=  arrayList.get(i).getAddress();
                strdescription=  arrayList.get(i).getDescription();
                strempCode= arrayList.get(i).getEmpcode();
                strcontact=  arrayList.get(i).getContact();
                strImage= arrayList.get(i).getImage();

                id.setText( String.valueOf(arrayList.get(i).getId()));
                name.setText(arrayList.get(i).getName());
                category.setText(arrayList.get(i).getCategory());

            famlist = convertView.findViewById(R.id.fam_list);
            famlist.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                  SessionData.getInstance().setStrId(Integer.parseInt(strId));
                    SessionData.getInstance().setStrname(strname);
                    SessionData.getInstance().setStrcategory(strcategory);
                    SessionData.getInstance().setStrcategoryId(strcategoryId);
                    SessionData.getInstance().setStraddress(straddress);
                    SessionData.getInstance().setStrdescription(strdescription);
                    SessionData.getInstance().setStrcontact(strcontact);
                    SessionData.getInstance().setStrImage(strImage);
                    SessionData.getInstance().setStrempCode(strempCode);
                    Intent i=new Intent(context, DetailsActivity.class);
                    startActivity(i);
                }
            });
            return convertView;



        }

    }


}