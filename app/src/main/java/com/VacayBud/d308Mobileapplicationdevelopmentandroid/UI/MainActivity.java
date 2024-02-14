package com.VacayBud.d308Mobileapplicationdevelopmentandroid.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.VacayBud.d308Mobileapplicationdevelopmentandroid.R;

public class MainActivity extends AppCompatActivity {

    public static int numAlert;
    public static int numAlert2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button=findViewById(R.id.button);
//        Vacation vacation=new Vacation(0, "Himalayan", 800.0);
//        Repository repository=new Repository(getApplication());
//        repository.insert(vacation);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,VacationList.class);
                intent.putExtra("test", "Information sent");
                startActivity(intent);
            }
        });
    }
}