package com.example.ex38_floatingactionbutton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {

    FloatingActionButton fab;
    ExtendedFloatingActionButton extFab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view,"clicked FAB",Snackbar.LENGTH_INDEFINITE).setAction("OK", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                    }
                }).show();
            }
        });

        extFab = findViewById(R.id.ext_fab);
        extFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if( extFab.isExtended()){
                    CoordinatorLayout layout = findViewById(R.id.snackbar_container);
                    Snackbar.make(layout,"clicked ADD",Snackbar.LENGTH_INDEFINITE).setAction("OK", new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            extFab.shrink();
                        }
                    }).show();
                }else{
                    extFab.extend();
                }
            }
        });
    }
}