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
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;


public class InfoActivity extends ActionBarActivity implements OnClickListener,OnItemSelectedListener, AdapterView.OnItemClickListener {



    private String postulantes , publicantes , ofertas;

    public void onItemSelected(AdapterView<?> parent, View view,
                               int pos, long id) {
        // An item was selected. You can retrieve the selected item using

        String tipo=parent.getItemAtPosition(pos).toString();

    }

    public void onNothingSelected(AdapterView<?> parent) {
        // Another interface callback
    }



    @Override
    public void onClick(View v){

    }

    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        //Se obtiene el resultado del proceso de scaneo y se parsea
        IntentResult scanningResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, intent);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_info);
        //Se Instancia el botón de Scan
        //scanBtn = (Button)findViewById(R.id.scan_button);
        //Se Instancia el Campo de Texto para el nombre del formato de código de barra
        //formatTxt = (TextView)findViewById(R.id.scan_format);
        //Se Instancia el Campo de Texto para el contenido  del código de barra
        //contentTxt = (TextView)findViewById(R.id.scan_content);

        Bundle extras = getIntent().getExtras();
        if(extras !=null) {
            this.postulantes= extras.getString("Postulantes");
            this.publicantes= extras.getString("Publicantes");
            this.ofertas= extras.getString("Ofertas");
        }

    }

    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {
        }
        return null;
    }

    private DatePickerDialog.OnDateSetListener pickerListener = new DatePickerDialog.OnDateSetListener() {

        // when dialog box is closed, below method will be called.
        @Override
        public void onDateSet(DatePicker view, int selectedYear,
                              int selectedMonth, int selectedDay) {

        }
    };


    /*back key */
    //for android 3.0 and above
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        if(keyCode == KeyEvent.KEYCODE_BACK)
        {
            finish();
            Intent homepage = new Intent(InfoActivity.this, MenuActivity.class);
            homepage.putExtra("Postulantes",this.postulantes);

            System.out.println("Postulantes " +this.postulantes);

            System.out.println("Publicantes " +this.publicantes);

            homepage.putExtra("Publicantes",this.publicantes);

            homepage.putExtra("Ofertas",this.ofertas);
            startActivity(homepage);

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

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }
}
