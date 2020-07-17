package cs246.project;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import cs246.project.Entity.SingleProduct;

public class InventoryListAdapter  extends RecyclerView.Adapter<InventoryListAdapter.ProductViewHolder> {

    class ProductViewHolder extends RecyclerView.ViewHolder {
        private final TextView ProductItemView;

        private ProductViewHolder(View itemView) {
            super(itemView);
            ProductItemView = itemView.findViewById(R.id.textView);
        }
    }

    private final LayoutInflater mInflater;
    private List<SingleProduct> mProducts; // Cached copy of Products

    InventoryListAdapter(Context context) { mInflater = LayoutInflater.from(context); }

    @NonNull
    @Override
    public InventoryListAdapter.ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.recyclerview_item, parent, false);
        return new InventoryListAdapter.ProductViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(@NonNull InventoryListAdapter.ProductViewHolder holder, int position) {
        if (mProducts != null) {
            final SingleProduct current = mProducts.get(position);
            holder.ProductItemView.setText(current.getName() + " : " + current.getStock());
            holder.ProductItemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
        }
    }

    void setProducts(List<SingleProduct> Products){
        mProducts = Products;
        notifyDataSetChanged();
    }

    // getItemCount() is called many times, and when it is first called,
    // mProducts has not been updated (means initially, it's null, and we can't return null).
    @Override
    public int getItemCount() {
        if (mProducts != null)
            return mProducts.size();
        else return 0;
    }
}