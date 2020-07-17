package cs246.project;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import cs246.project.Entity.SingleProduct;

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