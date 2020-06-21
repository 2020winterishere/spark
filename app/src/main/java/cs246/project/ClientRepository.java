package cs246.project;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

import cs246.project.Database.ClientRoomDatabase;
import cs246.project.Entity.SingleClient;
import cs246.project.Interface.ClientDao;

class ClientRepository {

    private ClientDao mClientDao;
    private LiveData<List<SingleClient>> mAllClients;

    // Note that in order to unit test the WordRepository, you have to remove the Application
    // dependency. This adds complexity and much more code, and this sample is not about testing.
    // See the BasicSample in the android-architecture-components repository at
    // https://github.com/googlesamples
    ClientRepository(Application application) {
        ClientRoomDatabase db = ClientRoomDatabase.getDatabase(application);
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
        ClientRoomDatabase.databaseWriteExecutor.execute(() -> {
            mClientDao.insert(client);
        });
    }
}