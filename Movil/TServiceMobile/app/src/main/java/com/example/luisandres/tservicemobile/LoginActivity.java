package com.example.luisandres.tservicemobile;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;


public class LoginActivity extends ActionBarActivity implements OnClickListener {


    private Button  loginBtn;
    private EditText identificacionTxt , passWordTxt;
    private JSONArray jsonPostulantes , jsonPublicantes;
    private Login log=new Login();


    @Override
    public void onClick(View v){

        if(v.getId()==R.id.login){

            if (log.getPostulantes()!= null && log.getPublicantes() != null ) {

                boolean login = false;

                try {

                    try {


                        System.out.println("Postulantes " + log.getPostulantes());
                        System.out.println("Publicantes" + log.getPublicantes());
                        this.jsonPostulantes = new JSONArray(log.getPostulantes());
                        this.jsonPublicantes = new JSONArray(log.getPublicantes());
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }


                    for (int i = 0; i < jsonPostulantes.length(); i++) {
                        JSONObject explrObject = jsonPostulantes.getJSONObject(i);
                        System.out.println("Id "+explrObject.get("identificacion").toString());
                        if (identificacionTxt.getText().toString().trim().equals(explrObject.get("identificacion").toString().trim())) {
                            if (identificacionTxt.getText().toString().trim().equals(passWordTxt.getText().toString())) {
                                login = true;
                            }
                        }
                    }

                    if (!login) {
                        for (int i = 0; i < jsonPublicantes.length(); i++) {
                            JSONObject explrObject = jsonPublicantes.getJSONObject(i);
                            if (identificacionTxt.getText().toString().trim().equals(explrObject.get("identificacion").toString().trim())) {
                                if (identificacionTxt.getText().toString().trim().equals(passWordTxt.getText().toString())) {
                                    login = true;
                                }
                            }
                        }
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

                if (login) {
                    finish();
                    Intent homepage = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(homepage);
                } else {
                    new AlertDialog.Builder(this)
                            .setTitle("Login")
                            .setMessage("Verifique los datos nuevamente!!")
                            .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    // continue with delete
                                }
                            })
                            .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    // do nothing
                                }
                            })
                            .setIcon(android.R.drawable.ic_dialog_alert)
                            .show();

                }
            }else{

                new AlertDialog.Builder(this)
                        .setTitle("Login")
                        .setMessage("Cargando información espere un momento.")
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // continue with delete
                            }
                        })
                        .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // do nothing
                            }
                        })
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();

            }

            }

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);

        loginBtn = (Button) findViewById(R.id.login);
        passWordTxt=(EditText)findViewById(R.id.password);
        identificacionTxt=(EditText)findViewById(R.id.idPer);

        loginBtn.setOnClickListener(this);

        //Cargar personas
        log.execute("");

    }

    /*back key */
    //for android 3.0 and above
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        if(keyCode == KeyEvent.KEYCODE_BACK)
        {
            finish();

            return true;
        }

        return super.onKeyDown(keyCode, event);
    }

    //for android 2.0
    @Override
    public void onBackPressed(){

        finish();

        return;
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
