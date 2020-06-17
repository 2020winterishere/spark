package cs246.project;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class NewClientActivity extends AppCompatActivity {

    public static final String EXTRA_REPLY = "com.example.android.clientlistsql.REPLY";

    private EditText mEditClientView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_client);
        mEditClientView = findViewById(R.id.edit_client);

        final Button button = findViewById(R.id.button_save);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent replyIntent = new Intent();
                if (TextUtils.isEmpty(mEditClientView.getText())) {
                    setResult(RESULT_CANCELED, replyIntent);
                } else {
                    String client = mEditClientView.getText().toString();
                    replyIntent.putExtra(EXTRA_REPLY, client);
                    setResult(RESULT_OK, replyIntent);
                }
                finish();
            }
        });
    }
}
