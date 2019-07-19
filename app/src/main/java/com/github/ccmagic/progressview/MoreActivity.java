package com.github.ccmagic.progressview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.github.ccmagic.progressviewlib.ProgressView1;
import com.github.ccmagic.progressviewlib.ProgressView2;
import com.github.ccmagic.progressviewlib.ProgressView;

/**
 * 多种样式扩展
 *
 * @author kxmc
 * <a href="http://www.kxmc.top">kxmc.top</a>
 * <a href="https://github.com/ccMagic">github(kxmc)</a>
 * @date 19-7-19 10:28
 */
public class MoreActivity extends AppCompatActivity {
    private ProgressView1 progressView1;
    private ProgressView2 progressView2;
    private ProgressView progressView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more);

        progressView1 = findViewById(R.id.progressView1);
        progressView2 = findViewById(R.id.progressView2);
        progressView = findViewById(R.id.progressView3);
    }

    @Override
    protected void onDestroy() {
        progressView1.cancellation();
        progressView2.cancellation();
        progressView.cancellation();
        super.onDestroy();
    }
}
