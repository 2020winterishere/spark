package cs246.project;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        System.out.println("Elizabeth Change");
        System.out.println("Making a sample change, it works");
        System.out.println("Third change. This is exciting.");
    }
}