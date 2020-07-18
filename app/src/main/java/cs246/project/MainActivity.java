
package cs246.project;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * <h1>Spark</h1>
 * <p>The Spark application implements a database system for our sponsor;a hair stylist.
 * Sponsor needs an application that allows her to keep track of her clients and inventory.
 * </p>
 *
 * @author  Elizabeth Pomazal, Eduardo Mena and Carlos Contreras
 * @version 1.0
 * @since   2020
 */
public class MainActivity extends AppCompatActivity {

    public static final int CLIENT_REQUEST_CODE = 1;
    public static final int INVENTORY_REQUEST_CODE = 2;

    /** <h1> Main app page functionality</h1>
     * <p>This allows the program to check to see which selection is made in the main page.
     * Directs app to correct database portion client/product.     *
     * </p>
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.client).setOnClickListener((View v)->{
            Intent intent = new Intent(MainActivity.this, ClientActivity.class);
            startActivityForResult(intent, CLIENT_REQUEST_CODE);

        });
        findViewById(R.id.inventory).setOnClickListener((View v)->{
            Intent intent = new Intent(MainActivity.this, InventoryActivity.class);
            startActivityForResult(intent, INVENTORY_REQUEST_CODE);

        });

    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

    }



}