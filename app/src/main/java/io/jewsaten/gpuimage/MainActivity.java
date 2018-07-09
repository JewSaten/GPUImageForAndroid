package io.jewsaten.gpuimage;

import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import jp.co.cyberagent.android.gpuimage.GPUImageFilter;
import jp.co.cyberagent.android.gpuimage.GPUImageGaussianBlurFilter;
import jp.co.cyberagent.android.gpuimage.GPUImageGrayscaleFilter;
import jp.co.cyberagent.android.gpuimage.GPUImageView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        GPUImageView gpuImageView = findViewById(R.id.gpuImageView);
        gpuImageView.setImage(BitmapFactory.decodeResource(getResources(), R.mipmap.image));
        findViewById(R.id.btnGray).setOnClickListener(createOnClickListener(gpuImageView));
        findViewById(R.id.btnBlur).setOnClickListener(createOnClickListener(gpuImageView));
    }

    private GPUImageFilter createFilter(int id) {
        boolean grayScale = R.id.btnGray == id;
        return grayScale ? new GPUImageGrayscaleFilter() : new GPUImageGaussianBlurFilter(5);
    }

    private View.OnClickListener createOnClickListener(final GPUImageView target) {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                target.setFilter(createFilter(view.getId()));
            }
        };
    }
}
