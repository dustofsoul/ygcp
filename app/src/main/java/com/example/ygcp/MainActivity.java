package com.example.ygcp;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import android.content.Intent;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import android.net.Uri;

public class MainActivity extends AppCompatActivity {

    ImageView imgView;
    Bitmap bm;
    String  url1="http://ygcp.njtech.edu.cn/u/",
            sid,
            url2="/MyRecords.aspx/Load_Report",
            url3="https://ygcp.njtech.edu.cn/u/",
            url4="/MyRecords.aspx/AlreadyConfirmSports";

    EditText SID;
    Button login;
    Button llq;
    @Override

    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        imgView = (ImageView) findViewById(R.id.imgView);
        login = (Button) findViewById(R.id.login);
        SID=(EditText) findViewById(R.id.sid) ;
        llq=(Button) findViewById(R.id.llq);
        login.setOnClickListener(new View.OnClickListener() {
                                                                 @Override
                                                                 public void onClick(View v) {
                                                                        if(v.getId() == R.id.login) {
                                                                            fun();
                                                                        }
                                                                 }
        });
        llq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(v.getId() == R.id.llq) {
                    fun2();
                }
            }
        });
    }

    public void fun(){
        String url=url1+SID.getText().toString()+url2;
        Toast.makeText(this,url,Toast.LENGTH_SHORT).show();
        ShowPicture(url);
    }

    public void fun2(){
        String url=url3+SID.getText().toString()+url4;
        Intent i= new Intent(Intent.ACTION_VIEW,Uri.parse(url));
        startActivity(i);
    }


    public void ShowPicture(String url){
        Toast.makeText(this,"Image showed",Toast.LENGTH_SHORT).show();
        new AsyncTask<String, Void , String>() {
            @Override
            protected String doInBackground(String... params) {
                try{
                    URL url = new URL(params[0]);  //定义url为第一个参数
                    InputStream is = url.openStream();   //获取输入流
                    bm = BitmapFactory.decodeStream(is);   //解析图片
                    is.close();             //关闭输入流
                }catch (MalformedURLException e){
                    e.printStackTrace();
                }catch (Exception e){
                    e.printStackTrace();
                }
                return null;
            }
            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                imgView.setImageBitmap(bm);
            }
        }.execute(url);
    }
}

