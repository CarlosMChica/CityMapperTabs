package carlosdelachica.com.designsupport;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;

public class SecondActivity extends Activity {

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    new Handler().postDelayed(new Runnable() {
      @Override public void run() {
        finish();
      }
    }, 500);
  }
}
