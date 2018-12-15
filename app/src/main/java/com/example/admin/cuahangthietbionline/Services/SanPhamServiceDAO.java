package com.example.admin.cuahangthietbionline.Services;

import android.util.Log;

import com.example.admin.cuahangthietbionline.Class.SanPham;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.jar.Attributes;

public class SanPhamServiceDAO {
    private static final String BASE_HOST = "http://127.0.0.1:8000/api";
    public void create(SanPham sp) throws IOException,JSONException{
        JSONObject jsonParam = new JSONObject();
        jsonParam.put("id",sp.getId());
        jsonParam.put("tensp",sp.getTensp());
        jsonParam.put("giatien",sp.getGiaTien());
        jsonParam.put("hinhanh",sp.getHinhanh());

        String jsonString = jsonParam.toString();
        try{
            DefaultHttpClient httpClient = new DefaultHttpClient();
            HttpEntity httpEntity = null;
            HttpResponse httpResponse = null;

            HttpPost httpPost = new HttpPost(BASE_HOST);

            httpPost.setHeader("Accept","application/json");
            httpPost.setHeader("Content-type","application/json");

            httpPost.setEntity(new StringEntity(jsonString));

            httpResponse = httpClient.execute(httpPost);
        }catch(Exception ex){
            Log.e("Buffer Error","Error" + ex.toString());
        }
    }
    public List<SanPham> readAll(){
        InputStream is = null;
        String response = null;

        try{
            DefaultHttpClient httpClient = new DefaultHttpClient();
            HttpEntity httpEntity = null;
            HttpResponse httpResponse = null;

            HttpGet httpGet = new HttpGet(BASE_HOST);
            httpResponse = httpClient.execute(httpGet);
            httpEntity = httpResponse.getEntity();
            is = httpEntity.getContent();

        }catch (Exception e){
            Log.e("My Error","Error" + e.toString());
        }
        try{
            BufferedReader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"),  8);
            StringBuilder sb = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) !=null){
                sb.append(line+ "\n");
            }
            is.close();
            response = sb.toString();
        } catch ( Exception e) {
            Log.e( "Buffer Error",  "Error:"+ e.toString());
        }

        try {
            //Convert string respone into Json Object
            JSONArray jsonArray = new JSONArray(response);
            ArrayList<SanPham> listSanPham = new ArrayList<>();

            for (int i =0; i< jsonArray.length(); i++){
                JSONObject jsonItem = jsonArray.getJSONObject(i);
                SanPham sanPham = new SanPham();
                sanPham.setId(jsonItem.getInt( "Id"));
                sanPham.setTensp(jsonItem.getString("Tensp"));
                sanPham.setGiaTien(jsonItem.getInt("GiaTien"));
                sanPham.setHinhanh(jsonItem.getString( "Hinhanh"));

                listSanPham.add(sanPham);
            }
            return listSanPham;
        }
        catch (Exception ex){
            Log.e(  "Data", "Respone: " +response);
            Log.e( "Json Error","Error: " +ex.toString());
            return new ArrayList<SanPham>();
        }
    }
    public SanPham read (String id) {
        InputStream is = null;
        String respone =null;

        try {
            //http client
            DefaultHttpClient httpClient = new DefaultHttpClient();
            HttpEntity httpEntity = null;
            HttpResponse httpResponse = null;

            HttpGet httpGet = new HttpGet( BASE_HOST+"/"+id);
            httpResponse = httpClient.execute(httpGet);
            httpEntity = httpResponse.getEntity();
            is =httpEntity.getContent();
        } catch (Exception ex) {
            Log.d( "My Error", ex.toString());
        }
        try{
            BufferedReader reader = new BufferedReader(new InputStreamReader(is,  "UTF-8"),  8);
            StringBuilder sb = new StringBuilder();
            String line =null;
            while ((line = reader.readLine())!=null){
                sb.append(line +"\n");
            }
            is.close();
            respone = sb.toString();
        } catch ( Exception e){
            Log.e( "Buffer Error",  "Error" +e.toString());
        }
        try {
            JSONObject jsonObject = new JSONObject(respone);

            SanPham sanPham= new SanPham();
            sanPham.setId (jsonObject.getInt( "Id"));
            sanPham.setTensp(jsonObject.getString( "Tensp"));
            sanPham.setGiaTien(jsonObject.getInt( "GiaTien"));
            sanPham.setHinhanh(jsonObject.getString( "Hinhanh"));

            return sanPham;
        }
        catch (Exception ex){
            Log.e( "Data",  "Respone:" +respone);
            Log.e( "Json Error", "Error "+ex.toString());
            return new SanPham();
        }
    }
    public void update(SanPham sanPham) throws IOException, JSONException{
        JSONObject jsonParam = new JSONObject();
        jsonParam.put( "id",sanPham.getId());
        jsonParam.put("Tensp", sanPham.getTensp());
        jsonParam.put( "GiaTien", sanPham.getGiaTien());
        jsonParam.put( "Hinhanh",sanPham.getHinhanh());

        String jsonString = jsonParam.toString();
        try {
            //1.Create
            DefaultHttpClient httpClient= new DefaultHttpClient();
            HttpEntity httpEntity = null;
            HttpResponse httpResponse =null;

            //Kiem tra
            HttpPut httpPut = new HttpPut( BASE_HOST+"/"+sanPham.getId());

            httpPut.setHeader( "Accept", "application/json");
            httpPut.setHeader( "Content-type",  "application/json");

            httpPut.setEntity(new StringEntity(jsonString));

            httpResponse = httpClient.execute(httpPut);
        }
        catch (Exception ex){
            Log.e( "Buffer Error",  "Error" +ex.getLocalizedMessage());
            Log.e("Info", "Data" +jsonString);
            Log.e( "Info", "URL" +BASE_HOST+"/"+sanPham.getId());
        }
    }
    public boolean delete (String id) {
        InputStream is = null;
        String response = null;

        try {
            //http client

            DefaultHttpClient httpClient = new DefaultHttpClient();
            HttpEntity httpEntity = null;
            HttpResponse httpRespone = null;

            HttpDelete httpDelete = new HttpDelete(  BASE_HOST+"/"+id);
            httpRespone = httpClient.execute(httpDelete);
            httpEntity = httpRespone.getEntity();
            is = httpEntity.getContent();

            return true;
        } catch (Exception ex){
            Log.d( "My Error", ex.toString());
            return false;
        }
    }
}
