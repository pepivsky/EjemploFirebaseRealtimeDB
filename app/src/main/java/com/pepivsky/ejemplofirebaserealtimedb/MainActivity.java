package com.pepivsky.ejemplofirebaserealtimedb;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    //Constantes para probrar Firebase
    private static final String PATH_START = "start";
    private static final String PATH_MESSAGE = "message";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Cast del textview
        final TextView tvMessage = findViewById(R.id.tvMessage);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        //Acceder al path message que es un nodo de "PATH_START"
        final DatabaseReference reference = database.getReference(PATH_START).child(PATH_MESSAGE);

        //Listener
        reference.addValueEventListener(new ValueEventListener() {
            //Método que se ejecuta cuando haya un cambio en la referencia
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                tvMessage.setText(dataSnapshot.getValue(String.class));
            }

            //Método para manipular errores
            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(MainActivity.this, "Error al consultar Firebase :(", Toast.LENGTH_LONG).show();
            }
        });
    }
}
