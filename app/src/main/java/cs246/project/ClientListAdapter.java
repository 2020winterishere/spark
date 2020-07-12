package cs246.project;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;
import cs246.project.Entity.SingleClient;

/**
 * <h1> Client Recycler View</h1>
 * <p>
 *     We use a recycler view to display all data in client database instead of using a
 *     TextView. This will allow us to present data in a nicer way.
 * </p>
 */

public class ClientListAdapter extends RecyclerView.Adapter<ClientListAdapter.ClientViewHolder> {

    class ClientViewHolder extends RecyclerView.ViewHolder {
        private final TextView clientItemView;

        private ClientViewHolder(View itemView) {
            super(itemView);
            clientItemView = itemView.findViewById(R.id.textView);
        }
    }

    private final LayoutInflater mInflater;
    private List<SingleClient> mClients; // Cached copy of clients

    ClientListAdapter(Context context) { mInflater = LayoutInflater.from(context); }

    @NonNull
    @Override
    public ClientViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.recyclerview_item, parent, false);
        return new ClientViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(@NonNull ClientViewHolder holder, int position) {
        if (mClients != null) {
            SingleClient current = mClients.get(position);
            holder.clientItemView.setText(current.getName());
        } else {
            // Covers the case of data not being ready yet.
            holder.clientItemView.setText(R.string.no_client);
        }
    }

    void setClients(List<SingleClient> clients){
        mClients = clients;
        notifyDataSetChanged();
    }

    // getItemCount() is called many times, and when it is first called,
    // mClients has not been updated (means initially, it's null, and we can't return null).
    @Override
    public int getItemCount() {
        if (mClients != null)
            return mClients.size();
        else return 0;
    }
}