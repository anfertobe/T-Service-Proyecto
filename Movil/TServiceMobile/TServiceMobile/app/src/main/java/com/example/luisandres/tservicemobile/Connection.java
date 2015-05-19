package com.example.luisandres.tservicemobile;

import android.os.AsyncTask;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;


/**
 * Created by LuisAndres on 2015/04/08.
 */
public class Connection extends AsyncTask<JSONObject,Integer,String> {


    private String tipo;
    private String resultado;


    public String getResultado(){
        return resultado;
    }

    public Connection(String tipo){
        this.tipo=tipo;

    }


    @Override
    protected void onPreExecute() {
    }
    @Override
    protected String doInBackground(JSONObject... params){

        if (params.length>0){

            JSONObject jso= params[0];

            DefaultHttpClient dhhtpc=new DefaultHttpClient();
            HttpPut postreq=new HttpPut("http://proyecto-tservise-cosw.herokuapp.com/rest/tservice/Postulantes/");

            if (tipo.trim().equals("Publicante")){

                System.out.println("Publicante");

                postreq=new HttpPut("http://proyecto-tservise-cosw.herokuapp.com/rest/tservice/Publicantes/");


            }


            StringEntity se=null;

            System.out.println("LLamando post ...");

            try{
                se=new StringEntity(jso.toString());
                System.out.println("JSon "+jso.toString());

            }catch (Exception ae){


           }



            System.out.print(se);

            se.setContentType("application/json;charset=UTF-8");
            se.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE,"application/json;charset=UTF-8"));
            postreq.setEntity(se);

            System.out.println("Enviar post ...");

            HttpResponse httpr;


            try{

                System.out.println("Ejecutando post ...");

                httpr=dhhtpc.execute(postreq);

                this.resultado = EntityUtils.toString(httpr.getEntity());

                System.out.println("Resultado "+ this.resultado);

                return resultado;

            }catch (Exception ar){

                this.resultado=ar.getMessage();

                System.out.println("Error "+this.resultado);

                return                 this.resultado;

            }
        }

        System.out.println(" ");


        return "";



    }
    @Override
    protected void onPostExecute(String result) {
    }
}
