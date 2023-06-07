package com.example.auraria;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {


    private EditText user_name_field,user_bio_field;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        user_name_field = findViewById(R.id.user_name_field);
        user_bio_field = findViewById(R.id.user_bio_field);
    }

    public void saveData(View v){
        String user_name = user_name_field.getText().toString();
        String user_bio = user_bio_field.getText().toString();

        try {
            FileOutputStream fileOutput = openFileOutput("user_data.txt",MODE_PRIVATE);
            fileOutput.write((user_name+". "+user_bio).getBytes());
            fileOutput.close();

            user_name_field.setText("");
            user_bio_field.setText("");
            Toast.makeText(this,"Ваши данные сохранены",Toast.LENGTH_SHORT).show();
        } catch (FileNotFoundException e) {
            Toast.makeText(this,"Не можем обработать файл",Toast.LENGTH_SHORT).show();
            throw new RuntimeException(e);
        } catch (IOException e) {
            Toast.makeText(this,"Не можем обработать файл",Toast.LENGTH_SHORT).show();
            throw new RuntimeException(e);
        }
    }

    public void getData(View v){
        try {
            //указываем с каким файлом мы работаем
            FileInputStream fileInput = openFileInput("user_data.txt");
            //считываем информавцию
            InputStreamReader reader = new InputStreamReader(fileInput);
            //преобразовываем
            BufferedReader br = new BufferedReader(reader);

            String lines = "";
            StringBuilder result = new StringBuilder();
            while((lines = br.readLine()) != null){   // readLine - считывает каждую строку
                result.append(lines).append("\n");
            }

            Toast.makeText(this,result, Toast.LENGTH_LONG).show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void goContacts(View view){
        Intent intent = new Intent(this, ContactsActivity.class);
        startActivity(intent);
    }

    public void goWeb(View view){
        Intent intent = new Intent(this, WebActivity.class);
        startActivity(intent);
    }

    public void goBack(View view){
        onBackPressed();
    }
}