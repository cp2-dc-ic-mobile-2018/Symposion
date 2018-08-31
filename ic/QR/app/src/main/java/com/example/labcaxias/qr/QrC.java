package com.example.labcaxias.qr;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class QrC extends Activity {
    Button AbreS;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qr_c);
        AbreS = (Button) findViewById(R.id.AbreS);

        alert("Programa inciado");

        final Activity activity = this;
        AbreS.setOnClickListener(new View.OnClickListener() {

                                     @Override
                                     public void onClick(View view) {
                                         IntentIntegrator integrator = new IntentIntegrator(activity);
                                         integrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE_TYPES);
                                         integrator.setPrompt("Scanner");
                                         integrator.setCameraId(0);
                                         integrator.initiateScan();
                                     }
                                 }

        );
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if(result != null){
            if(result.getContents() != null){
                //String content = result.getContents();
                //alert(content);

                alert(result.getContents());
            }
            else{
                alert("Scanner cancelado");
            }
        }
        else{
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    private void alert(String msg){
        Toast.makeText(getApplicationContext(),msg,Toast.LENGTH_LONG).show();
        TextView msgem = findViewById(R.id.msgee);
        msgem.setText(msg);
    }
}
