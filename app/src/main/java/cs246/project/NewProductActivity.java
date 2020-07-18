package cs246.project;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class NewProductActivity extends AppCompatActivity {

    /**
     * <h1>Display for a single product</h1>
     * <p>This is the display of adding a single product.
     * </p>
     *
     */

    public static final String EXTRA_NAME = "product name";
    public static final String EXTRA_STOCK = "product stock";

    private EditText mEditInventoryView;

    private EditText mEditProductNameView;
    private EditText mEditStock;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_product);


        //add product name and quantity
        mEditProductNameView =findViewById(R.id.product_name);
        mEditStock = findViewById(R.id.editStock);


        final Button button = findViewById(R.id.button_save);
        button.setOnClickListener((View v) -> {

            Intent replyIntent = new Intent();
            //check if product field is empty. If it is empty skip saving data.
            if (TextUtils.isEmpty(mEditProductNameView.getText())) {
                setResult(RESULT_CANCELED, replyIntent);
            } else {
                String name = mEditProductNameView.getText().toString();
                replyIntent.putExtra(EXTRA_NAME, name);
                float stock;
                try {
                    stock = Float.parseFloat(mEditStock.getText().toString());
                } catch (NumberFormatException e){
                    stock = 0.25f;
                };
                replyIntent.putExtra(EXTRA_STOCK, stock);
                setResult(RESULT_OK, replyIntent);
            }
            finish();

        });


    }
}

