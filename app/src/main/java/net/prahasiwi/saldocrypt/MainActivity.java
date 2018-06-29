package net.prahasiwi.saldocrypt;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    EditText ednorek, ednama, edsaldo, edkey, edsaldoDe, edkeyDe;
    TextView txnorek, txnama, txsaldo, txsaldoDe;
    String norek, nama, saldo, key, saldoDe, keyDe, cipherSaldo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView(){
        ednorek = (EditText)findViewById(R.id.edtnorek);
        ednama = (EditText)findViewById(R.id.edtnama);
        edsaldo = (EditText)findViewById(R.id.edtsaldo);
        edkey = (EditText)findViewById(R.id.edtkey);
        edsaldoDe = (EditText)findViewById(R.id.edtsaldoDE);
        edkeyDe = (EditText)findViewById(R.id.edtkeyDe);
        txnorek = (TextView)findViewById(R.id.txtNorek);
        txnama = (TextView)findViewById(R.id.txtNama);
        txsaldo = (TextView)findViewById(R.id.txtSaldo);
        txsaldoDe = (TextView)findViewById(R.id.txtSaldoDe);
    }

    public void encrypt(View view) {
        if (ednorek.getText().toString().isEmpty()){
            ednorek.setError("Diisi Dulu");
        }
        if (ednama.getText().toString().isEmpty()){
            ednama.setError("Diisi Dulu");
        }
        if (edsaldo.getText().toString().isEmpty()){
            edsaldo.setError("Diisi Dulu");
        }
        if (edkey.getText().toString().isEmpty()){
            edkey.setError("Diisi Dulu");
        }
        if (!ednorek.getText().toString().isEmpty() && !ednama.getText().toString().isEmpty() &&
                !edsaldo.getText().toString().isEmpty() && !edkey.getText().toString().isEmpty()){
            norek = ednorek.getText().toString();
            nama = ednama.getText().toString();
            saldo = edsaldo.getText().toString();
            key = edkey.getText().toString();

            cipherSaldo = encryption(saldo, key);

            txnorek.setText(norek);
            txnama.setText(nama);
            txsaldo.setText(cipherSaldo);
            edsaldoDe.setText(cipherSaldo);
            edkeyDe.setText(key);
        }
    }

    public void resetEnc(View view) {
        ednorek.setText("");
        ednama.setText("");
        edsaldo.setText("");
        edkey.setText("");
        txnorek.setText("");
        txnama.setText("");
        txsaldo.setText("");
    }

    public void decrypt(View view) {
        if (edsaldoDe.getText().toString().isEmpty()){
            edsaldoDe.setError("Diisi Dulu");
        }
        if (edkeyDe.getText().toString().isEmpty()){
            edkeyDe.setError("Diisi Dulu");
        }
        if (!edsaldoDe.getText().toString().isEmpty() && !edkeyDe.getText().toString().isEmpty()){
            saldoDe = edsaldoDe.getText().toString();
            keyDe = edkeyDe.getText().toString();

            String decryptSaldo = decryption(saldoDe, keyDe);

            txsaldoDe.setText(decryptSaldo);
        }
    }

    public void resetDe(View view) {
        edkeyDe.setText("");
        edsaldoDe.setText("");
        txsaldoDe.setText("");
    }

    private String encryption(String plainText, String kunciRahasia) {
        StringBuffer encryptedString = new StringBuffer();
        int encryptedInt;
        for (int i = 0; i < plainText.length(); i++) {
            int plainTextInt = (int) plainText.charAt(i);
            int secretKeyInt = (int) kunciRahasia.charAt(i%kunciRahasia.length());
            encryptedInt = ((plainTextInt + secretKeyInt)*76)+3;
            encryptedString.append((char) encryptedInt);
        }
        return encryptedString.toString();
    }

    private String decryption(String decryptedText, String kunciRahasia) {
        StringBuffer decryptedString = new StringBuffer();
        int decryptedInt;
        for (int i = 0; i < decryptedText.length(); i++) {
            int decryptedTextInt = (int) decryptedText.charAt(i);
            int secretKeyInt = (int) kunciRahasia.charAt(i%kunciRahasia.length());
            decryptedInt = ((decryptedTextInt-3)/76)-secretKeyInt;
            decryptedString.append((char) decryptedInt);
        }
        return decryptedString.toString();
    }

    public void resetSemua(View view) {
        ednorek.setText("");
        ednama.setText("");
        edsaldo.setText("");
        edkey.setText("");
        txnorek.setText("");
        txnama.setText("");
        txsaldo.setText("");
        edkeyDe.setText("");
        edsaldoDe.setText("");
        txsaldoDe.setText("");
    }
}
