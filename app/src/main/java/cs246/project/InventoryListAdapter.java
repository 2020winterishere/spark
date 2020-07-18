package cs246.project;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.lang.ref.WeakReference;
import java.util.List;

import cs246.project.Entity.SingleProduct;
/**
 * <h1> Inventory Recycler View</h1>
 * <p>
 *     We use a recycler view to display all data in product database instead of using a
 *     TextView. This will allow us to present data in a nicer way.
 * </p>
 */
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
    private WeakReference<Activity> mActivity;
    static final String PRODUCT_NAME = "Product Name";
    static final int PRODUCT_EDIT_REQUEST_CODE = 421;

    InventoryListAdapter(Activity context) {
        mInflater = LayoutInflater.from(context);
        mActivity = new WeakReference<>(context);
    }

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
                    Intent intent = new Intent(mActivity.get(), InventoryEditActivity.class);
                    intent.putExtra(PRODUCT_NAME, current.getName());
                    mActivity.get().startActivityForResult(intent, PRODUCT_EDIT_REQUEST_CODE);
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