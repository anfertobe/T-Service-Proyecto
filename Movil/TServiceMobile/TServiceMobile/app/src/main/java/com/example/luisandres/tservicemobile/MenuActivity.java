package com.example.luisandres.tservicemobile;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class MenuActivity extends ActionBarActivity implements OnClickListener {


    private ImageButton buscarPerBtn,buscarOfBtn , addPerBtn , infoBTN;

    private String postulantes , publicantes , ofertas;

    @Override
    public void onClick(View v){

        if(v.getId()==R.id.buscarPersona){
            finish();
            Intent homepage = new Intent(MenuActivity.this, CPersonaActivity.class);
            homepage.putExtra("Postulantes",this.postulantes);

            System.out.println("Postulantes " +this.postulantes);

            System.out.println("Publicantes " +this.publicantes);

            homepage.putExtra("Publicantes",this.publicantes);

            homepage.putExtra("Ofertas",this.ofertas);
            startActivity(homepage);
        }


        if(v.getId()==R.id.buscarOferta){
            finish();
            Intent homepage = new Intent(MenuActivity.this, COfertaActivity.class);
            homepage.putExtra("Postulantes",this.postulantes);

            System.out.println("Postulantes " +this.postulantes);

            System.out.println("Publicantes " +this.publicantes);

            homepage.putExtra("Publicantes",this.publicantes);

            homepage.putExtra("Ofertas",this.ofertas);
            startActivity(homepage);

        }


        if(v.getId()==R.id.addPersonaM){
            finish();

            Intent homepage = new Intent(MenuActivity.this, MainActivity.class);
            homepage.putExtra("Postulantes",this.postulantes);

            System.out.println("Postulantes " +this.postulantes);

            System.out.println("Publicantes " +this.publicantes);

            homepage.putExtra("Publicantes",this.publicantes);

            homepage.putExtra("Ofertas",this.ofertas);
            homepage.putExtra("Tipo","Menu");
            startActivity(homepage);
        }


        if(v.getId()==R.id.info){
            finish();
            Intent homepage = new Intent(MenuActivity.this, InfoActivity.class);
            homepage.putExtra("Postulantes",this.postulantes);

            System.out.println("Postulantes " +this.postulantes);

            System.out.println("Publicantes " +this.publicantes);

            homepage.putExtra("Publicantes",this.publicantes);

            homepage.putExtra("Ofertas",this.ofertas);
            startActivity(homepage);

        }

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_menu);

        Bundle extras = getIntent().getExtras();
        if(extras !=null) {
            this.postulantes= extras.getString("Postulantes");
            this.publicantes= extras.getString("Publicantes");
            this.ofertas= extras.getString("Ofertas");
        }

        buscarPerBtn=(ImageButton)findViewById(R.id.buscarPersona);
        buscarPerBtn.setOnClickListener(this);
        buscarOfBtn=(ImageButton)findViewById(R.id.buscarOferta);
        buscarOfBtn.setOnClickListener(this);
        addPerBtn=(ImageButton)findViewById(R.id.addPersonaM);
        addPerBtn.setOnClickListener(this);
        infoBTN =(ImageButton)findViewById(R.id.info);
        infoBTN.setOnClickListener(this);



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
