package cs246.project;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import cs246.project.Entity.SingleClient;

public class ClientActivity extends AppCompatActivity {

    private ClientViewModel mClientViewModel;
    public static final int NEW_CLIENT_ACTIVITY_REQUEST_CODE = 1;
    private static final String TAG="Added Client";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_list);

        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        final ClientListAdapter adapter = new ClientListAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        mClientViewModel = new ViewModelProvider(this).get(ClientViewModel.class);


        mClientViewModel.getAllClients().observe(this, new Observer<List<SingleClient>>() {
            @Override
            public void onChanged(@Nullable final List<SingleClient> clients) {
                // Update the cached copy of the clients in the adapter.
                adapter.setClients(clients);
            }
        });

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ClientActivity.this, NewClientActivity.class);
                startActivityForResult(intent, NEW_CLIENT_ACTIVITY_REQUEST_CODE);
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

        if (requestCode == NEW_CLIENT_ACTIVITY_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                String name = (data.getStringExtra(NewClientActivity.EXTRA_NAME));
                String phone = (data.getStringExtra(NewClientActivity.EXTRA_PHONE));
                String comments = (data.getStringExtra(NewClientActivity.EXTRA_COMMENTS));
                SingleClient client = new SingleClient(name == null ? "" : name, phone, comments);
                mClientViewModel.insert(client);
                Log.i(TAG, "Client updated.");
            } else {
                Toast.makeText(
                        getApplicationContext(),
                        R.string.empty_not_saved,
                        Toast.LENGTH_LONG).show();
            }
        }
    }



}