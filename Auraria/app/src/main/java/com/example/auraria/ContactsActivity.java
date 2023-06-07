package com.example.auraria;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GestureDetectorCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;


//GestureDetector.OnGestureListener - отслеживание жестов
//GestureDetector.OnDoubleTapListener - полноценное одиночное нажатие
public class ContactsActivity extends AppCompatActivity implements GestureDetector.OnGestureListener, GestureDetector.OnDoubleTapListener {

    private String[] nameArr = new String[]{"Аким","Пётр","Иван","Астемир","Мурат"};
    private ListView listView;

    //активировать GestureDetector
    private GestureDetectorCompat gd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);

        listView = findViewById(R.id.list_view);

        //Контакты массив данных
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.name_item, R.id.user_name,nameArr);
        listView.setAdapter(adapter);


        //Вывод информации когда пользователь нажмет на какую либо строку, кнопку и т.д
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // getItemAtPosition - позиция элемента на которую мы нажали
                Toast.makeText(ContactsActivity.this, (String)listView.getItemAtPosition(position), Toast.LENGTH_LONG).show();
            }
        });
        // указать контекст и в каком классе будут находиться все обработчики собития
        gd = new GestureDetectorCompat(this,this);
        // указать что я смогу отслеживать длительные нажатия
        gd.setIsLongpressEnabled(true);
        // чтобы работали OnDoubleTap
        gd.setOnDoubleTapListener(this);
    }

    // обрабатывать функционал который я написал
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        gd.onTouchEvent(event);
        return super.onTouchEvent(event);
    }

    public void goBack(View view){
        onBackPressed();
    }

    public void goHome(View view){
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }

    public void goWeb(View view){
        Intent intent = new Intent(this,WebActivity.class);
        startActivity(intent);
    }


    @Override
    public boolean onDown(@NonNull MotionEvent e) {
        return false;
    }

    @Override
    public void onShowPress(@NonNull MotionEvent e) {

    }

    @Override
    public boolean onSingleTapUp(@NonNull MotionEvent e) {
        return false;
    }

    @Override
    public boolean onScroll(@NonNull MotionEvent e1, @NonNull MotionEvent e2, float distanceX, float distanceY) {
        return false;
    }

    @Override
    public void onLongPress(@NonNull MotionEvent e) { // длительное нажатие
        Toast.makeText(this, e.toString(),Toast.LENGTH_LONG).show();
    }

    @Override
    public boolean onFling(@NonNull MotionEvent e1, @NonNull MotionEvent e2, float velocityX, float velocityY) {
        return false;
    }

    @Override
    public boolean onSingleTapConfirmed(@NonNull MotionEvent e) {
        return false;
    }

    @Override
    public boolean onDoubleTap(@NonNull MotionEvent e) {
        return false;
    }

    @Override
    public boolean onDoubleTapEvent(@NonNull MotionEvent e) {
        return false;
    }
}