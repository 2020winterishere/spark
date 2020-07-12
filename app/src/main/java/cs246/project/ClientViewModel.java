package cs246.project;

import android.app.Application;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import java.util.List;
import cs246.project.Entity.SingleClient;

/**
 * <h1> Client View Model</h1>
 * <p>
 *     This ViewModel's role is to provide data to the UI and survive configuration changes.
 *     The ViewModel acts as a communication center between the Repository and the UI.
 * </p>
 */
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