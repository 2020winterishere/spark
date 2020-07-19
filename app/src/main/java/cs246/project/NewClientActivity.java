package cs246.project;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

/**
 * <h1>Display for a single client</h1>
 * <p>This is the display of a single client.
 * </p>
 *
 */
public class NewClientActivity extends AppCompatActivity {

    public static final String EXTRA_NAME = "client name";
    public static final String EXTRA_PHONE = "client phone";
    public static final String EXTRA_COMMENTS = "client comments";

    private EditText mEditClientView;

    //add first and last name to database
    private EditText mEditFirstNameView;
    private EditText mEditLastNameView;
    private EditText mEditPhoneNumberView;
    private EditText mEditComments;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_client);
        //mEditClientView = findViewById(R.id.edit_client);

        //add first name, last name and phone number
        mEditFirstNameView =findViewById(R.id.first_name);
        mEditLastNameView =findViewById(R.id.last_name);
        mEditPhoneNumberView = findViewById(R.id.phone_number);
        mEditComments = findViewById(R.id.editComments);


        final Button button = findViewById(R.id.button_save);
        button.setOnClickListener((View v) -> {

                Intent replyIntent = new Intent();
                //check if first name field is empty. If it is empty skip saving data.
                if (TextUtils.isEmpty(mEditFirstNameView.getText())) {
                    setResult(RESULT_CANCELED, replyIntent);
                } else {
                    //String client = mEditClientView.getText().toString();
                    String name = mEditFirstNameView.getText().toString() + " " +
                            mEditLastNameView.getText().toString();
                    replyIntent.putExtra(EXTRA_NAME, name);
                    String phone = mEditPhoneNumberView.getText().toString();
                    replyIntent.putExtra(EXTRA_PHONE, phone);
                    String comments = mEditComments.getText().toString();
                    replyIntent.putExtra(EXTRA_COMMENTS, comments);
                    setResult(RESULT_OK, replyIntent);
                }
                finish();

        });


    }
}
