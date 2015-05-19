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


public class COfertaActivity extends ActionBarActivity implements OnClickListener,OnItemSelectedListener, AdapterView.OnItemClickListener {


    private String postulantes , publicantes , ofertas;
    private Button scanBtn , addPersona;
    private JSONArray  jsonOfertas ;
    private EditText identificacionTxt , descripcionTxt , valorTxt , EstadoTxt ,PostulanteTxt  , PublicanteTxt;
    private EditText buscarTxt;

    static final int DATE_PICKER_ID = 1111;

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

        if(v.getId()==R.id.buscarPersonaOBtn){

            boolean login=false;

            try{

             for (int i = 0; i < jsonOfertas.length(); i++) {

                JSONObject explrObject = jsonOfertas.getJSONObject(i);
                System.out.println("Buscar "+ buscarTxt.getText().toString());

                if (buscarTxt.getText().toString().trim().equals(explrObject.get("id").toString().trim())|| (buscarTxt.getText().toString().trim().contains(explrObject.get("descripcion").toString().trim()) &&  explrObject.get("nombre").toString().trim().length()>0)) {

                    System.out.println("Oferta encontrada postulantes");

                    //Datos generales
                    identificacionTxt.setText(explrObject.get("id").toString().trim());

                    descripcionTxt.setText(explrObject.get("descripcion").toString().trim());

                    valorTxt.setText(explrObject.get("valor").toString().trim());

                    EstadoTxt.setText(explrObject.get("estado").toString().trim());

                    String text = "No hay";

                    try{
                        if (explrObject.getJSONObject("postulante")!= null){
                            text=explrObject.getJSONObject("postulante").get("identificacion").toString().trim() +"(" + (explrObject.getJSONObject("postulante").get("nombre").toString().trim()  )+")";
                        }

                        PostulanteTxt.setText(text);
                    }catch (JSONException ex) {
                        PostulanteTxt.setText(text);
                        ex.printStackTrace();
                    }


                    text = "No hay";

                    try{
                        if (explrObject.getJSONObject("publicante")!= null){
                            text=explrObject.getJSONObject("publicante").get("identificacion").toString().trim() +"(" + (explrObject.getJSONObject("publicante").get("nombre").toString().trim() )+")";;
                        }
                    PublicanteTxt.setText(text);
                    } catch (JSONException ex) {
                        PublicanteTxt.setText(text);
                        ex.printStackTrace();
                    }


                    login = true;

                    break;

                }
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

          if(!login){
                new AlertDialog.Builder(this)
                        .setTitle("Login")
                        .setMessage("No se encontraron ofertas!!")
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

    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        //Se obtiene el resultado del proceso de scaneo y se parsea
        IntentResult scanningResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, intent);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_consultaroferta);
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

            try {
                this.jsonOfertas = new JSONArray(this.ofertas);
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }

        //Persona General
        identificacionTxt = (EditText) findViewById(R.id.identificacionOC);

        descripcionTxt = (EditText) findViewById(R.id.descripcionOC);

        valorTxt = (EditText) findViewById(R.id.valorOC);

        EstadoTxt = (EditText) findViewById(R.id.estadoOC);

        PostulanteTxt = (EditText) findViewById(R.id.postulanteOC);

        PublicanteTxt = (EditText) findViewById(R.id.empleadoOC);

        buscarTxt=(EditText) findViewById(R.id.buscarPersonaOCTXT);


        addPersona= (Button)findViewById(R.id.buscarPersonaOBtn);

        addPersona.setOnClickListener(this);

        //Se agrega la clase MainActivity.java como Listener del evento click del botón de Scan
        //scanBtn.setOnClickListener(this);
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case DATE_PICKER_ID:

                // open datepicker dialog.
                // set date picker for current date
                // add pickerListener listner to date picker
                Calendar c = Calendar.getInstance();
                int mYear = c.get(Calendar.YEAR);
                int mMonth = c.get(Calendar.MONTH);
                int mDay = c.get(Calendar.DAY_OF_MONTH);


                return new DatePickerDialog(this, pickerListener, mYear, mMonth,mDay);
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
            Intent homepage = new Intent(COfertaActivity.this, MenuActivity.class);
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
