package cs246.project;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import cs246.project.Database.RoomDatabase;
import cs246.project.Entity.SingleProduct;
import cs246.project.Interface.ProductDao;

public class InventoryEditActivity extends AppCompatActivity {
    private SingleProduct mProduct;
    private EditText mName, mQuantity;
    private ProductDao mPDB;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_product);
        Intent intent = getIntent();
        String name = intent.getStringExtra(InventoryListAdapter.PRODUCT_NAME);
        mPDB = RoomDatabase.getDatabase(this).productDao();
        mName = findViewById(R.id.product_name);
        mQuantity = findViewById(R.id.editStock);

        findViewById(R.id.button_delete).setOnClickListener(v -> {
            AlertDialog.Builder dlg = new AlertDialog.Builder(InventoryEditActivity.this);
            dlg.setTitle("Confirm Delete").setMessage("Are you sure you want to Delete" + mProduct.getName() + "?");
            DialogInterface.OnClickListener ocl = (dialog, which) -> {
                dialog.dismiss();
                if (which == DialogInterface.BUTTON_POSITIVE) {
                    new Thread() {
                        @Override
                        public void run() {
                            mPDB.deleteByName(mProduct.getName());
                            runOnUiThread(() -> finish());
                        }
                    }.start();
                }
            };
            dlg.setPositiveButton("Yes", ocl).setNegativeButton("Cancel", ocl).show();
        });
        findViewById(R.id.button_save).setOnClickListener(v -> {
            mProduct.setName(mName.getText().toString());

            float stock;
            try {
                stock = Float.parseFloat(mQuantity.getText().toString());
            } catch (NumberFormatException e) {
                stock = 0.25f;
            }
            mProduct.setStock(stock);
            new Thread() {
                @Override
                public void run() {
                    mPDB.updateEntity(mProduct);
                    runOnUiThread(() -> finish());
                }
            }.start();
        });
        new Thread() {
            @Override
            public void run() {
                mProduct = mPDB.getByName(name);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mName.setText(mProduct.getName());
                        mQuantity.setText(mProduct.getStock()+"");
                    }
                });
            }
        }.start();
    }
}
