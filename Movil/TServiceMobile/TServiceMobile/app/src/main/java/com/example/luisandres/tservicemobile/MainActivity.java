package com.example.luisandres.tservicemobile;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.view.View.OnClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;


public class MainActivity extends ActionBarActivity implements OnClickListener,DialogInterface.OnDismissListener,OnItemSelectedListener, AdapterView.OnItemClickListener {

    private String postulantes , publicantes , ofertas , tipo;
    private boolean choose=false;
    private boolean isPostulante=false;
    boolean finder=false;
    private Button scanBtn , addPersona;
    private TextView formatTxt, contentTxt;
    private EditText identificacionTxt , nombreTxt , correoTxt , telefonoTxt, paisTxt ,ciudadTxt , regionTxt , fechaNaTxt,dirTxt,hVidaTxt , fechaActTxt, aspSalTxt , expTXT;
    private Spinner spinnerTipo;
    private String fecha;
    private int year;
    private int month;
    private int day;
    Connection con;
    ProgressDialog ringProgressDialog;

    static final int DATE_PICKER_ID = 1111;

    public void onItemSelected(AdapterView<?> parent, View view,
                               int pos, long id) {
        // An item was selected. You can retrieve the selected item using

        String tipo=parent.getItemAtPosition(pos).toString();
        System.out.print("Valor ++++"+tipo.trim());

        if(tipo.trim().equals("Postulante")){
            isPostulante=true;
            choose=true;
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
            isPostulante=false;
            choose=true;
            aspSalTxt.setEnabled(false);
            //Publicante
            expTXT.setEnabled(true);
        }

    }

    public void onNothingSelected(AdapterView<?> parent) {
        // Another interface callback
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

            if(this.tipo.equals("Menu")) {
                Intent homepage = new Intent(MainActivity.this, MenuActivity.class);
                homepage.putExtra("Postulantes", this.postulantes);

                System.out.println("Postulantes " + this.postulantes);

                System.out.println("Publicantes " + this.publicantes);

                homepage.putExtra("Publicantes", this.publicantes);

                homepage.putExtra("Ofertas", this.ofertas);
                startActivity(homepage);
            }else{
                Intent homepage = new Intent(MainActivity.this, LoginActivity.class);
                homepage.putExtra("Postulantes", this.postulantes);

                System.out.println("Postulantes " + this.postulantes);

                System.out.println("Publicantes " + this.publicantes);

                homepage.putExtra("Publicantes", this.publicantes);

                homepage.putExtra("Ofertas", this.ofertas);
                startActivity(homepage);

            }
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
    public void onClick(View v){
        //Se responde al evento click
        //if(v.getId()==R.id.scan_button){
            //Se instancia un objeto de la clase IntentIntegrator
          //  IntentIntegrator scanIntegrator = new IntentIntegrator(this);
            //Se procede con el proceso de scaneo
            //scanIntegrator.initiateScan();
        //}

        String tipo="Postulante";

        if(v.getId()==R.id.addPersona){

            if(choose) {

                JSONObject json = new JSONObject();
                try {


                    //Generales
                    json.put("identificacion",Integer.parseInt(identificacionTxt.getText().toString()));
                    json.put("nombre", nombreTxt.getText());
                    json.put("fechaNacimiento", fechaNaTxt.getText());
                    json.put("correo", correoTxt.getText());
                    json.put("direccion", dirTxt.getText());
                    json.put("telefono", telefonoTxt.getText());
                    json.put("pais", paisTxt.getText());

                    json.put("ciudad", ciudadTxt.getText());

                    if (isPostulante) {
                        JSONObject jsonHD = new JSONObject();
                        json.put("region", regionTxt.getText());
                        jsonHD.put("hojaDeVida",hVidaTxt.getText());
                        jsonHD.put("fechaActualizacion",fechaActTxt.getText());
                        jsonHD.put("foto","");
                        json.put("hojaDeVida",jsonHD);
                        json.put("ofertas",JSONObject.NULL);
                        json.put("ofertas_1",JSONObject.NULL);
                        json.put("intereses",JSONObject.NULL);
                        json.put("experienciaLaborals",JSONObject.NULL);
                        json.put("aspiracionSalarial",Integer.parseInt(aspSalTxt.getText().toString()));
                    }else{
                        tipo="Publicante";
                        json.put("ragion", regionTxt.getText());

                        Calendar c = Calendar.getInstance();
                        int mYear = c.get(Calendar.YEAR);
                        int mMonth = c.get(Calendar.MONTH);
                        int mDay = c.get(Calendar.DAY_OF_MONTH);

                        String sYear;
                        String sMonth;
                        String sDay;

                        sYear  = String.valueOf(mYear);
                        sMonth =  String.valueOf(mMonth+1);

                        if(sMonth.trim().length()==1){
                            sMonth="0"+sMonth;
                        }

                        sDay =  String.valueOf(mDay);

                        if(sDay.trim().length()==1){
                            sDay="0"+sDay;
                        }


                        json.put("fechaUltimaLicecia",
                                new StringBuilder().append(sYear).append("-").append(sMonth)
                                        .append("-").append(sDay).append(" "));
                        json.put("experiencia",expTXT.getText());
                        json.put("facturas",JSONObject.NULL);
                        json.put("ofertas",JSONObject.NULL);
                    }
                } catch (Exception ae) {

                }

                 con = new Connection(tipo);

                con.execute(json);


                Handler handle=null;

                finder=false;

                ringProgressDialog = ProgressDialog.show(this, "Please wait ...",	"Agregando Persona ...", true);
                ringProgressDialog.setCancelable(false);
                ringProgressDialog.setMax(100);

                Thread th=new Thread(new Runnable() {
                    @Override
                    public void run() {

                        while (!finder) {

                            try {

                                Thread.sleep(100);
                            } catch (Exception e) {

                            }

                            if (con.getResultado() != null) {
                                finder=true;
                                ringProgressDialog.dismiss();
                            }

                        }
                    }
                });

                th.start();

                ringProgressDialog.show();

                ringProgressDialog.setOnDismissListener(this);

            }else{
                new AlertDialog.Builder(this)
                        .setTitle("Agregar Persona")
                        .setMessage("Debe escoger un tipo de persona")
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
        if (scanningResult != null) {
            //Quiere decir que se obtuvo resultado pro lo tanto:
            //Desplegamos en pantalla el contenido del código de barra scaneado
            String scanContent = scanningResult.getContents();
            contentTxt.setText("Contenido: " + scanContent);
            //Desplegamos en pantalla el nombre del formato del código de barra scaneado
            String scanFormat = scanningResult.getFormatName();
            formatTxt.setText("Formato: " + scanFormat);
        }else{
            //Quiere decir que NO se obtuvo resultado
            Toast toast = Toast.makeText(getApplicationContext(),
                    "No se ha recibido datos del scaneo!", Toast.LENGTH_SHORT);
            toast.show();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
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
            this.tipo=extras.getString("Tipo");

        }



        spinnerTipo = (Spinner)findViewById(R.id.tipo_spinner);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
        R.array.planets_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinnerTipo.setAdapter(adapter);

        spinnerTipo.setOnItemSelectedListener(this);

        //Persona General
        identificacionTxt = (EditText) findViewById(R.id.identificacion);

        nombreTxt = (EditText) findViewById(R.id.nombre);

        correoTxt = (EditText) findViewById(R.id.correo);

        telefonoTxt= (EditText) findViewById(R.id.telefono);

        paisTxt = (EditText) findViewById(R.id.pais);

        ciudadTxt = (EditText) findViewById(R.id.ciudad);

        regionTxt = (EditText) findViewById(R.id.region);

        fechaNaTxt= (EditText) findViewById(R.id.dNacimiento);

        fechaNaTxt.setOnFocusChangeListener(new View.OnFocusChangeListener() {

            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                // On button click show datepicker dialog
                fecha = "fechaNacimiento";
                showDialog(DATE_PICKER_ID);
            }
        });


        dirTxt= (EditText) findViewById(R.id.direccion);

        //Postulante
        hVidaTxt = (EditText) findViewById(R.id.hVida);

        fechaActTxt= (EditText) findViewById(R.id.dActu);

        fechaActTxt.setOnFocusChangeListener(new View.OnFocusChangeListener() {

            @Override
            public void onFocusChange(View v, boolean hasFocus) {

                // On button click show datepicker dialog
                fecha = "fechaActual";
                showDialog(DATE_PICKER_ID);

            }

        });


        aspSalTxt = (EditText) findViewById(R.id.aspSal);

        //Publicante
        expTXT= (EditText) findViewById(R.id.experiencia);

        addPersona= (Button)findViewById(R.id.addPersona);

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

            String sYear;
            String sMonth;
            String sDay;

            sYear  = String.valueOf(selectedYear);
            sMonth =  String.valueOf(selectedMonth+1);

            if(sMonth.trim().length()==1){
                sMonth="0"+sMonth;
            }

            sDay =  String.valueOf(selectedDay);

            if(sDay.trim().length()==1){
                sDay="0"+sDay;
            }

            if(fecha.equals("fechaActual")){

                // Show selected date
                fechaActTxt.setText(new StringBuilder().append(sYear).append("-").append(sMonth)
                        .append("-").append(sDay).append(" "));


            }else{
                // Show selected date
                fechaNaTxt.setText(new StringBuilder().append(sYear).append("-").append(sMonth)
                        .append("-").append(sDay).append(" "));


            }

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

    @Override
    public void onDismiss(DialogInterface dialog) {
        if (con.getResultado().trim().equals("")) {
            new AlertDialog.Builder(this)
                    .setTitle("Agregar Persona")
                    .setMessage("Persona agregada correctamente!!")
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
            } else {
            new AlertDialog.Builder(this)
                    .setTitle("Agregar Persona")
                    .setMessage(con.getResultado().trim())
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
