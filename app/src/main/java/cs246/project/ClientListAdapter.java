package cs246.project;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import cs246.project.Entity.SingleClient;

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

    @Override
    public ClientViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.recyclerview_item, parent, false);
        return new ClientViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ClientViewHolder holder, int position) {
        if (mClients != null) {
            SingleClient current = mClients.get(position);
            holder.clientItemView.setText(current.getName());
        } else {
            // Covers the case of data not being ready yet.
            holder.clientItemView.setText("No Client");
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