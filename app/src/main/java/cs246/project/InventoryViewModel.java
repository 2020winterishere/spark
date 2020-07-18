package cs246.project;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import cs246.project.Entity.SingleProduct;
/**
 * <h1> Inventory View Model</h1>
 * <p>
 *     This ViewModel's role is to provide data to the UI and survive configuration changes.
 *     The ViewModel acts as a communication center between the Repository and the UI.
 * </p>
 */
public class InventoryViewModel extends AndroidViewModel {

        private InventoryRepository mRepository;

        private LiveData<List<SingleProduct>> mAllProducts;

        public InventoryViewModel (Application application) {
            super(application);
            mRepository = new InventoryRepository(application);
            mAllProducts = mRepository.getAllProducts();
        }

        LiveData<List<SingleProduct>> getAllProducts() { return mAllProducts; }

        public void insert(SingleProduct product) { mRepository.insert(product); }
    }