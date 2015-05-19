package com.example.luisandres.tservicemobile;

import android.os.AsyncTask;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;


/**
 * Created by LuisAndres on 2015/04/08.
 */
public class Login extends AsyncTask<String,Integer,String> {


    private String publicantes;
    private String postulantes;
    private String ofertas;


    @Override
    protected void onPreExecute() {
    }

    protected String getASCIIContentFromEntity(HttpEntity entity) throws IllegalStateException, IOException {

        InputStream in = entity.getContent();

        StringBuffer out = new StringBuffer();
        int n = 1;
        while (n>0) {
            byte[] b = new byte[4096];

            n =  in.read(b);

            if (n>0) out.append(new String(b, 0, n));

        }

        return out.toString();

    }


    @Override
    protected String doInBackground(String... params){

        if (params.length>0){

            DefaultHttpClient dhhtpc=new DefaultHttpClient();
            DefaultHttpClient dhhtpc2=new DefaultHttpClient();
            DefaultHttpClient dhhtpc3=new DefaultHttpClient();

            HttpGet post=new HttpGet("http://proyecto-tservise-cosw.herokuapp.com/rest/tservice/Postulantes/");
            HttpGet pub=new HttpGet("http://proyecto-tservise-cosw.herokuapp.com/rest/tservice/Publicantes/");
            HttpGet of=new HttpGet("http://proyecto-tservise-cosw.herokuapp.com/rest/tservice/Ofertas/");


            System.out.println("LLamando Get ...");

            HttpResponse httpr;

            HttpResponse httpp;

            HttpResponse httpo;


            try{

                System.out.println("Ejecutando post ...");

                httpr=dhhtpc.execute(post);

                httpp=dhhtpc2.execute(pub);

                httpo=dhhtpc3.execute(of);

                HttpEntity entityPos = httpr.getEntity();

                HttpEntity entityPub = httpp.getEntity();

                HttpEntity entityOf = httpo.getEntity();

                this.postulantes = getASCIIContentFromEntity(entityPos);

                this.publicantes = getASCIIContentFromEntity(entityPub);

                this.ofertas = getASCIIContentFromEntity(entityOf);

            }catch (Exception ar){

                System.out.println("Error "+ar.getMessage());

            }
        }

        System.out.println(" ");


        return "";

    }
    @Override
    protected void onPostExecute(String result) {
    }

    public String getPublicantes() {
        return publicantes;
    }

    public void setPublicantes(String publicantes) {
        this.publicantes = publicantes;
    }

    public String getPostulantes() {
        return postulantes;
    }

    public void setPostulantes(String postulantes) {
        this.postulantes = postulantes;
    }

    public String getOfertas() {
        return ofertas;
    }

    public void setOfertas(String ofertas) {
        this.ofertas = ofertas;
    }
}
