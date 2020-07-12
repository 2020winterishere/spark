package cs246.project;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

import cs246.project.Database.RoomDatabase;
import cs246.project.Entity.SingleClient;
import cs246.project.Interface.ClientDao;

/**
 * <h1> Client Repository</h1>
 * <p>
 *     This repository class abstracts access to multiple data sources. The repository is not
 *     parts of the Architecture Components Libraries. It provides clean API for data access
 *     to the rest of the application.
 * </p>
 */

class ClientRepository {

    private ClientDao mClientDao;
    private LiveData<List<SingleClient>> mAllClients;


    ClientRepository(Application application) {
        RoomDatabase db = RoomDatabase.getDatabase(application);
        mClientDao = db.clientDao();
        mAllClients = mClientDao.getAlphabetizedWords();
    }

    // Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the data has changed.
    LiveData<List<SingleClient>> getAllClients() {
        return mAllClients;
    }

    // You must call this on a non-UI thread or your app will throw an exception. Room ensures
    // that you're not doing any long running operations on the main thread, blocking the UI.
    void insert(SingleClient client) {
        RoomDatabase.databaseWriteExecutor.execute(() ->
            mClientDao.insert(client)
        );
    }




}