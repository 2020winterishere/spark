package cs246.project;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class ClientViewModel extends AndroidViewModel {

    private ClientRepository mRepository;

    private LiveData<List<SingleClient>> mAllClients;

    public ClientViewModel (Application application) {
        super(application);
        mRepository = new ClientRepository(application);
        mAllClients = mRepository.getAllClients();
    }

    LiveData<List<SingleClient>> getAllClients() { return mAllClients; }

    public void insert(SingleClient client) { mRepository.insert(client); }
}