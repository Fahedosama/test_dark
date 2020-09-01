package com.example.testdark;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.nfc.Tag;
import android.os.Bundle;
import android.text.Html;
import android.text.Spanned;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import static android.text.Html.FROM_HTML_MODE_LEGACY;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
   // Button btn;
    TextView textView;
    private static final String TAG = "MainActivity";
    static Resources res;
    static boolean isDark = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        res = getResources();

        //btn = findViewById(R.id.button);
        textView = findViewById(R.id.textView);
        //btn.setOnClickListener(this);

        for (int i = 0; i < 10; i++) {
            textView.setText(textView.getText() + "\n" + getNum(res, i));
        }
        int count = 0;

        textView.setText(textView.getText() + "\n" + getNum(res, count));

        WebView webView = findViewById(R.id.webview);

        String text = getString(R.string.emal, "Ihab Hebo", "7726");
        Spanned styledText = Html.fromHtml(text, FROM_HTML_MODE_LEGACY);

        webView.loadData(text,"text/html","utf-8");
        textView.setText(styledText);
    }
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu1, menu);
        return true;
    }

    public void onGroupItemClick(MenuItem item) {
        // One of the group items (using the onClick attribute) was clicked
        // The item parameter passed here indicates which item it is
        // All other menu item clicks are handled by <code><a href="/reference/android/app/Activity.html#onOptionsItemSelected(android.view.MenuItem)">onOptionsItemSelected()</a></code>
        switch (item.getItemId()){
            case R.id.action_notifications:
                Toast.makeText(this, "sub 1 clicked", Toast.LENGTH_SHORT).show();
                break;
            default:
                Toast.makeText(this, "Menu Clicked", Toast.LENGTH_SHORT).show();
        }
    }
    void changeTheme() {
        //load image views array name should be like layout_name_imgViews
        TypedArray imgViews = getResources().obtainTypedArray(R.array.activity_main_imgViews);
        //we write name of array of dark and light image names naming layout_name_img_(dark,light)
        int arrayName = isDark ? R.array.activity_main_img_dark : R.array.activity_main_img_light;
        //load array of drawables
        TypedArray icons = getResources().obtainTypedArray(arrayName);
        //change dark status
        isDark = !isDark;
        int imgLen = imgViews.length();
        //we show the drawables
        for (int i = 0; i < imgLen; i++) {
            ImageView img = findViewById(imgViews.getResourceId(i, 0));
            img.setImageDrawable(icons.getDrawable(i));
        }
    }

    String getNum(Resources r, int x) {
        return r.getQuantityString(R.plurals.numberOfSongsAvailable, x, x);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.button:
                changeTheme();
                break;
            default:
                Toast.makeText(this, "Fhaed", Toast.LENGTH_SHORT).show();


        }
    }
}