package br.g12.cp2.sympsion;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

public class gerarQr extends Activity {

    Button btnGerar;
    EditText nome;
    EditText cpf;
    ImageView codigo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gerar_qr);

        iniciarComponentes();
        clickButton();
    }

    private void clickButton() {
        Intent intent  = new Intent(this, cadastrar.class);
        btnGerar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { gerarQRcode(); }

            }

        );
    }

    private void gerarQRcode() {
        String texto = nome.getText().toString();
        String tcpf = cpf.getText().toString();
        MultiFormatWriter multiFormatWriter = new MultiFormatWriter();

        String resultado = texto + " " + tcpf;

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
        nome = (EditText) findViewById(R.id.nome);
        cpf = (EditText) findViewById(R.id.cpf);
        codigo = (ImageView) findViewById(R.id.codigo);

    }
}

