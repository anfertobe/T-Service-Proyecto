package com.example.luisandres.tservicemobile;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.text.InputFilter;
import android.text.Spanned;
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


public class CPersonaActivity extends ActionBarActivity implements OnClickListener,OnItemSelectedListener, AdapterView.OnItemClickListener {


    private String postulantes , publicantes , ofertas;
    private Button scanBtn , addPersona;
    private JSONArray  jsonPostulantes ,jsonPublicantes;
    private EditText identificacionTxt , nombreTxt , correoTxt , telefonoTxt ,ciudadTxt  , fechaNaTxt,hVidaTxt , fechaActTxt, aspSalTxt , expTXT;
    private EditText buscarTxt;
    private Spinner spinnerTipo;

    static final int DATE_PICKER_ID = 1111;

    public void onItemSelected(AdapterView<?> parent, View view,
                               int pos, long id) {
        // An item was selected. You can retrieve the selected item using

        String tipo=parent.getItemAtPosition(pos).toString();


        if(tipo.trim().equals("Postulante")){
            //Postulante
            hVidaTxt.setEnabled(true);
            fechaActTxt.setEnabled(true);
            aspSalTxt.setEnabled(true);
            //Publicante
            expTXT.setEnabled(false);
        }else if(tipo.trim().equals("Publicante")){
            //Postulante
            hVidaTxt.setEnabled(false);
            fechaActTxt.setEnabled(false);
            aspSalTxt.setEnabled(false);
            //Publicante
            expTXT.setEnabled(true);
        }

    }

    public void onNothingSelected(AdapterView<?> parent) {
        // Another interface callback
    }



    @Override
    public void onClick(View v){

        if(v.getId()==R.id.buscarPersonaBtn){

            boolean login=false;

            try{
            for (int i = 0; i < jsonPostulantes.length(); i++) {

                JSONObject explrObject = jsonPostulantes.getJSONObject(i);
                System.out.println("Buscar "+ buscarTxt.getText().toString());

                if (buscarTxt.getText().toString().trim().equals(explrObject.get("identificacion").toString().trim())|| (buscarTxt.getText().toString().trim().contains(explrObject.get("nombre").toString().trim()) &&  explrObject.get("nombre").toString().trim().length()>0)) {

                    System.out.println("Persona encontrada postulantes");

                    System.out.println("Nombre" + explrObject.get("identificacion").toString().trim());

                    System.out.println("Parametro " +buscarTxt.getText().toString().trim().contains(explrObject.get("nombre").toString().trim()));

                    this.spinnerTipo.setSelection(0);


                    //Datos generales
                    identificacionTxt.setText(explrObject.get("identificacion").toString().trim());

                    nombreTxt.setText(explrObject.get("nombre").toString().trim());

                    fechaNaTxt.setText(explrObject.get("fechaNacimiento").toString().trim());

                    correoTxt.setText(explrObject.get("correo").toString().trim());

                    telefonoTxt.setText(explrObject.get("telefono").toString().trim());

                    ciudadTxt.setText(explrObject.get("ciudad").toString().trim());

                    //Postulante
                    hVidaTxt.setText(explrObject.getJSONObject("hojaDeVida").get("hojaDeVida").toString().trim());

                    fechaActTxt.setText(explrObject.getJSONObject("hojaDeVida").get("fechaActualizacion").toString().trim());

                    aspSalTxt.setText(explrObject.get("aspiracionSalarial").toString().trim());

                    expTXT.setText("");


                    login = true;

                    break;

                }
            }

            if (!login) {
                for (int i = 0; i < jsonPublicantes.length(); i++) {
                    JSONObject explrObject = jsonPublicantes.getJSONObject(i);
                    if (buscarTxt.getText().toString().trim().equals(explrObject.get("identificacion").toString().trim())|| (buscarTxt.getText().toString().trim().contains(explrObject.get("nombre").toString().trim()) &&  explrObject.get("nombre").toString().trim().length()>0))  {


                        System.out.println("Persona encontrada publicantes");

                        System.out.println("Nombre" + explrObject.get("nombre").toString().trim());

                        this.spinnerTipo.setSelection(1);


                        //Datos generales
                        identificacionTxt.setText(explrObject.get("identificacion").toString().trim());

                        nombreTxt.setText(explrObject.get("nombre").toString().trim());

                        fechaNaTxt.setText(explrObject.get("fechaNacimiento").toString().trim());

                        correoTxt.setText(explrObject.get("correo").toString().trim());

                        telefonoTxt.setText(explrObject.get("telefono").toString().trim());

                        ciudadTxt.setText(explrObject.get("ciudad").toString().trim());

                       //Publicante
                        expTXT.setText(explrObject.get("experiencia").toString().trim());

                        hVidaTxt.setText("");

                        fechaActTxt.setText("");

                        aspSalTxt.setText("");


                        login=true;

                        break;

                    }
                }
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

            if(!login){
                new AlertDialog.Builder(this)
                        .setTitle("Login")
                        .setMessage("No se encontraron personas!!")
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


    /*back key */
    //for android 3.0 and above
    /*back key */
    //for android 3.0 and above
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        if(keyCode == KeyEvent.KEYCODE_BACK)
        {
            finish();
            Intent homepage = new Intent(CPersonaActivity.this, MenuActivity.class);
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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_consultarpersona);
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
                this.jsonPostulantes = new JSONArray(this.postulantes);
                this.jsonPublicantes = new JSONArray( this.publicantes);
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }

        spinnerTipo = (Spinner)findViewById(R.id.tipo_spinnerC);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
        R.array.planets_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinnerTipo.setAdapter(adapter);

        spinnerTipo.setOnItemSelectedListener(this);

        //Persona General
        identificacionTxt = (EditText) findViewById(R.id.identificacionC);

        nombreTxt = (EditText) findViewById(R.id.nombreC);

        correoTxt = (EditText) findViewById(R.id.correoC);

        telefonoTxt= (EditText) findViewById(R.id.telefonoC);

        ciudadTxt = (EditText) findViewById(R.id.ciudadC);

        fechaNaTxt= (EditText) findViewById(R.id.dNacimientoC);

        buscarTxt=(EditText) findViewById(R.id.buscarPersonaTXT);

        //Postulante
        hVidaTxt = (EditText) findViewById(R.id.hVidaC);

        fechaActTxt= (EditText) findViewById(R.id.dActuC);


        aspSalTxt = (EditText) findViewById(R.id.aspSalC);

        //Publicante
        expTXT= (EditText) findViewById(R.id.experienciaC);

        addPersona= (Button)findViewById(R.id.buscarPersonaBtn);

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
