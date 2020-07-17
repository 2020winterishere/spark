package cs246.project;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import cs246.project.Entity.SingleProduct;

public class InventoryActivity  extends AppCompatActivity {

    private InventoryViewModel mInventoryViewModel;
    public static final int NEW_PRODUCT_ACTIVITY_REQUEST_CODE = 1;
    private static final String TAG="Added Product";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_list);

        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        final InventoryListAdapter adapter = new InventoryListAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        mInventoryViewModel = new ViewModelProvider(this).get(InventoryViewModel.class);


        mInventoryViewModel.getAllProducts().observe(this, new Observer<List<SingleProduct>>() {
            @Override
            public void onChanged(@Nullable final List<SingleProduct> products) {
                // Update the cached copy of the clients in the adapter.
                adapter.setProducts(products);
            }
        });

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(InventoryActivity.this, NewProductActivity.class);
                startActivityForResult(intent, NEW_PRODUCT_ACTIVITY_REQUEST_CODE);
            }
        });

        FloatingActionButton fab2 = findViewById(R.id.floatingActionButton2);
        fab2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == NEW_PRODUCT_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK) {
            String name = (data.getStringExtra(NewProductActivity.EXTRA_NAME));
            float stock = (data.getFloatExtra(NewProductActivity.EXTRA_STOCK, 0.25f));
            stock = (float)(Math.floor(stock * 4)/4);
            SingleProduct product = new SingleProduct(name == null ? "" : name,stock);
            mInventoryViewModel.insert(product);
            Log.i(TAG,"Prduct upated.");
        } else {
            Toast.makeText(
                    getApplicationContext(),
                    R.string.empty_not_saved,
                    Toast.LENGTH_LONG).show();
        }
    }



}