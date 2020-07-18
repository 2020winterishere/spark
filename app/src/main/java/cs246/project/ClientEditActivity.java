package cs246.project;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

import cs246.project.Database.RoomDatabase;
import cs246.project.Entity.SingleClient;
import cs246.project.Entity.SingleProduct;
import cs246.project.Interface.ClientDao;
import cs246.project.Interface.ProductDao;

public class ClientEditActivity extends AppCompatActivity {
    private SingleClient mClient;
    private ClientDao mCDB;
    private EditText mName, mPhone, mComments;
    private ProductDao mPDB;

    //static final int
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_client);
        Intent intent = getIntent();
        String name = intent.getStringExtra(ClientListAdapter.CLIENT_NAME);
        mCDB = RoomDatabase.getDatabase(this).clientDao();
        mPDB = RoomDatabase.getDatabase(this).productDao();
        mName = findViewById(R.id.name);
        mPhone = findViewById(R.id.phone_number);
        mComments = findViewById(R.id.editComments);

        findViewById(R.id.button_delete).setOnClickListener(v -> {
            AlertDialog.Builder dlg = new AlertDialog.Builder(ClientEditActivity.this);
            dlg.setTitle("Confirm Delete").setMessage("Are you sure you want to Delete" + mClient.getName() + "?");
            DialogInterface.OnClickListener ocl = (dialog, which) -> {
                dialog.dismiss();
                if (which == DialogInterface.BUTTON_POSITIVE) {
                    new Thread() {
                        @Override
                        public void run() {
                            mCDB.deleteByName(mClient.getName());
                            runOnUiThread(() -> finish());
                        }
                    }.start();
                }
            };
            dlg.setPositiveButton("Yes", ocl).setNegativeButton("Cancel", ocl).show();
        });
        findViewById(R.id.button_save).setOnClickListener(v -> {
            mClient.setName(mName.getText().toString());
            mClient.setPhone(mPhone.getText().toString());
            mClient.setComments(mComments.getText().toString());
            new Thread() {
                @Override
                public void run() {
                    mCDB.updateEntity(mClient);
                    runOnUiThread(() -> finish());
                }
            }.start();
        });
        findViewById(R.id.button_use_product).setOnClickListener(v -> {
            new Thread() {
                @Override
                public void run() {
                    final ArrayList<SingleProduct> products = new ArrayList<>(mPDB.getAllProducts());
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            View view = getLayoutInflater().inflate(R.layout.inventory_selection, new FrameLayout(ClientEditActivity.this));
                            final AtomicInteger index = new AtomicInteger(-1);
                            Spinner spinner = view.findViewById(R.id.inventory);
                            spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                @Override
                                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                    index.set(position);
                                }

                                @Override
                                public void onNothingSelected(AdapterView<?> parent) {
                                    index.set(-1);
                                }
                            });
                            spinner.setAdapter(new BaseAdapter() {
                                @Override
                                public int getCount() {
                                    return products.size();
                                }

                                @Override
                                public Object getItem(int position) {
                                    return products.get(position);
                                }

                                @Override
                                public long getItemId(int position) {
                                    return 0;
                                }

                                @Override
                                public View getView(int position, View convertView, ViewGroup parent) {
                                    TextView tv;
                                    if (convertView instanceof TextView) {
                                        tv = (TextView) convertView;
                                    } else {
                                        tv = new TextView(ClientEditActivity.this);
                                        tv.setTextSize(TypedValue.COMPLEX_UNIT_SP, 15);
                                        tv.setGravity(Gravity.CENTER_HORIZONTAL);
                                    }
                                    tv.setText(products.get(position).getName());
                                    return tv;
                                }
                            });
                            AlertDialog.Builder dlg = new AlertDialog.Builder(ClientEditActivity.this);
                            dlg.setTitle("Select a Product.").setView(view).setNegativeButton("Cancel",
                                    (dialog, which) -> dialog.dismiss()).setPositiveButton("Ok", (dialog, which) -> {
                                dialog.dismiss();
                                if (index.get() == -1) {
                                    return;
                                }
                                View pq = getLayoutInflater().inflate(R.layout.product_quantity, new FrameLayout(ClientEditActivity.this));
                                ((TextView) pq.findViewById(R.id.quantity_message)).setText("Select Amount: ");
                                final SingleProduct sp = products.get(index.get());
                                final AtomicInteger counter = new AtomicInteger(0);
                                final TextView quantity = pq.findViewById(R.id.quantity);
                                View.OnClickListener buttonQuantity = v1 -> {
                                    if (v1.getId() == R.id.quantity_increment) {
                                        float newValue = ((float)counter.get() + 1)/4f;
                                        if (newValue > sp.getStock()) {
                                            Toast.makeText(ClientEditActivity.this, "You don't have enough. Sorry.", Toast.LENGTH_SHORT).show();
                                            return;
                                        }
                                        counter.incrementAndGet();
                                    } else {
                                        if (counter.get() == 0) {
                                            Toast.makeText(ClientEditActivity.this, "You can't go less than 0. Sorry.", Toast.LENGTH_SHORT).show();
                                            return;
                                        }
                                        counter.decrementAndGet();
                                    }
                                    quantity.setText(String.valueOf((float) counter.get() / 4f));
                                };
                                pq.findViewById(R.id.quantity_decrement).setOnClickListener(buttonQuantity);
                                pq.findViewById(R.id.quantity_increment).setOnClickListener(buttonQuantity);
                                AlertDialog.Builder dlgB = new AlertDialog.Builder(ClientEditActivity.this);
                                dlgB.setTitle("Use " + sp.getName() + " (" + sp.getStock() + ")").setView(pq);
                                dlgB.setNegativeButton("Cancel", ((dialog1, which1) -> dialog1.dismiss()))
                                        .setPositiveButton("Ok", ((dialog1, which1) -> {
                                            dialog1.dismiss();
                                            sp.setStock(sp.getStock() - ((float) counter.get() / 4f));
                                            String comments = mClient.getComments();
                                            comments += "\nUsed " + ((float) counter.get() / 4f) + " of " + sp.getName();
                                            mClient.setComments(comments);
                                            mComments.setText(comments);
                                            new Thread() {
                                                @Override
                                                public void run() {
                                                    mPDB.updateEntity(sp);
                                                    mCDB.updateEntity(mClient);
                                                }
                                            }.start();
                                        })).show();
                            }).show();
                        }
                    });
                }
            }.start();
        });
        new Thread() {
            @Override
            public void run() {
                mClient = mCDB.getByName(name);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mName.setText(mClient.getName());
                        mPhone.setText(mClient.getPhone());
                        mComments.setText(mClient.getComments());
                    }
                });
            }
        }.start();
    }
}

