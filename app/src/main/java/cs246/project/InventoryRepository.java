package cs246.project;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

import cs246.project.Database.RoomDatabase;
import cs246.project.Entity.SingleClient;
import cs246.project.Entity.SingleProduct;
import cs246.project.Interface.ClientDao;
import cs246.project.Interface.ProductDao;

public class InventoryRepository {

    private ProductDao mProductDao;
    private LiveData<List<SingleProduct>> mAllProducts;

    InventoryRepository(Application application) {
        RoomDatabase db = RoomDatabase.getDatabase(application);
        mProductDao = db.productDao();
        mAllProducts = mProductDao.getAlphabetizedWords();
    }

    // Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the data has changed.
    LiveData<List<SingleProduct>> getAllProducts() {
        return mAllProducts;
    }

    // You must call this on a non-UI thread or your app will throw an exception. Room ensures
    // that you're not doing any long running operations on the main thread, blocking the UI.
    void insert(SingleProduct product) {
        RoomDatabase.databaseWriteExecutor.execute(() ->
                mProductDao.insert(product)
        );
    }
}
