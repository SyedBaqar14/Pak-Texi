package fidarides.eusopht.app;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import fidarides.eusopht.app.Activities.ActivityPassword;
import fidarides.eusopht.app.Activities.MainActivity;

public class signInAfterPage extends AppCompatActivity {

    TextView txt01, txt02, txt03;

    public Activity activity = signInAfterPage.this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in_after_page);
    }

    public void onClick(View v) {
        Toast.makeText(getApplicationContext(), "txtTitle_01", Toast.LENGTH_LONG).show();
    }

    public void onClick02(View v) {
        Toast.makeText(getApplicationContext(), "txtTitle_02", Toast.LENGTH_LONG).show();
    }

    public void onClick03(View v) {
        Toast.makeText(getApplicationContext(), "txtTitle_03", Toast.LENGTH_LONG).show();
    }


    public void GoToMainActivity(){
        Intent mainIntent = new Intent(activity, MainActivity.class);
        mainIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(mainIntent);
        overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
        activity.finish();
    }
    
}
