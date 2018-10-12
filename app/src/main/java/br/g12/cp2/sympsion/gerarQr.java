package br.g12.cp2.sympsion;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import android.content.SharedPreferences;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;


public class gerarQr extends Activity {

    SharedPreferences dadosusuario;
    Button btnGerar;
    ImageView codigo;
    EditText campocpf;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gerar_qr);

        iniciarComponentes();
        clickButton();
    }

    private void clickButton() {
        btnGerar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { gerarQRcode(); }

            }

        );
    }

    private void gerarQRcode() {

        btnGerar.setOnClickListener(new View.OnClickListener(){
           @Override
           public void onClick(View view) {

               dadosusuario = PreferenceManager.getDefaultSharedPreferences(gerarQr.this);

               campocpf.setText(dadosusuario.getString("CPF", "Não encontrado"));

           }
        });

        MultiFormatWriter multiFormatWriter = new MultiFormatWriter();

        String resultado = campocpf + ";" + campocpf; // O primeiro "campocpf" seria o nome que está faltando no shared preferences

        try {
            BitMatrix bitMatrix = multiFormatWriter.encode(resultado, BarcodeFormat.QR_CODE, 300,300);
            BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
            Bitmap bitmap = barcodeEncoder.createBitmap(bitMatrix);
            codigo.setImageBitmap(bitmap);
        }catch (WriterException e)
        {
            e.printStackTrace();

        }
    }

    private void iniciarComponentes() {
        btnGerar = (Button) findViewById(R.id.btnGerar);
        //nome = (EditText) findViewById(R.id.nome);
        campocpf = (EditText) findViewById(R.id.cpf);
        codigo = (ImageView) findViewById(R.id.codigo);

    }
}

