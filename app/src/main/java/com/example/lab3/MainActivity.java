package com.example.lab3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Button add, replace, change, btnview, save;
    EditText exitname, exittime;
    DatabaseHelper mData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        add = (Button) findViewById(R.id.btnadd);
        replace = (Button) findViewById(R.id.btreplace);
        change = (Button) findViewById(R.id.btnDel);
        btnview = (Button) findViewById(R.id.btnView);
        save = (Button) findViewById(R.id.btnsave);
        exitname = (EditText) findViewById(R.id.addname);
        exittime = (EditText) findViewById(R.id.addtime);
        mData = new DatabaseHelper(this);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                exitname.setVisibility(View.VISIBLE);
                exittime.setVisibility(View.VISIBLE);
                save.setVisibility(View.VISIBLE);
            }
        });
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               if(exitname.getText().length() == 0 || exittime.getText().length() == 0){
                   toastMessage("Поля не должны быть пустыми!");
               }
               else{
                   String name = exitname.getText().toString();
                   String time = exittime.getText().toString();
                   exitname.setText("");
                   exittime.setText("");
                   mData.addData(name, time);
                   exitname.setVisibility(View.GONE);
                   exittime.setVisibility(View.GONE);
                   save.setVisibility(View.GONE);
               }
            }
        });
        change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DeleteData();
                change.setVisibility(View.GONE);
            }
        });
        btnview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ListDataActivity.class);
                startActivity(intent);
            }
        });
        replace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mData.replace("Иван Иванович Иванов");
            }
        });
    }
    private void toastMessage(String s){
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
    }
    public void DeleteData() {
        Random random=new Random();
        String[] Names={"Семен Семенович Семенов","Илья Ильич Ильичов","Андрей Андреев Андреевич","Федор Федоров Федорович","Максим Максимович Максимов","Матвей Матвеевич Матвеев","Данила Данилович Данилов","Григорий Григорьев Григорьевич","Давид Давидович Давидов"};
        String[]time={"12:14","13:21","10:10","15:15","23:56","14:32","12:23","14:21"};
        int i=random.nextInt(8);
        mData.DeleteandAdd(0,Names[i],time[i]);
        i=random.nextInt(8);
        mData.DeleteandAdd(1,Names[i],time[i]);
        i=random.nextInt(8);
        mData.DeleteandAdd(1,Names[i],time[i]);
        i=random.nextInt(8);
        mData.DeleteandAdd(1,Names[i],time[i]);
        i=random.nextInt(8);
        mData.DeleteandAdd(1,Names[i],time[i]);
    }
}
