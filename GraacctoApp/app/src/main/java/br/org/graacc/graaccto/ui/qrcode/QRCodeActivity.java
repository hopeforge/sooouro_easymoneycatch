package br.org.graacc.graaccto.ui.qrcode;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.google.zxing.Result;

import br.org.graacc.graaccto.R;
import br.org.graacc.graaccto.core.QRCodeController;
import br.org.graacc.graaccto.util.MessageUtil;
import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class QRCodeActivity extends AppCompatActivity implements ZXingScannerView.ResultHandler {

    private ZXingScannerView zxscan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qrcode);

        QRCodeController.getInstance().setValue(null);

        findViews();

        zxscan.setResultHandler(QRCodeActivity.this);
        zxscan.startCamera();
    }

    private void findViews() {
        zxscan = findViewById(R.id.zxscan);
    }

    @Override
    protected void onDestroy() {
        zxscan.stopCamera();
        super.onDestroy();
    }

    @Override
    public void handleResult(Result result) {
        if (result != null) {
            QRCodeController.getInstance().setValue(result.getText());
            zxscan.stopCamera();
            finish();
        }
    }

}
