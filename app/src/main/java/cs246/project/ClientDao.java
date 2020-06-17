package cs246.project;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface ClientDao {

    // allowing the insert of the same word multiple times by passing a
    // conflict resolution strategy
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(SingleClient client);

    @Query("DELETE FROM client_table")
    void deleteAll();

    @Query("SELECT * from client_table ORDER BY client ASC")
    LiveData<List<SingleClient>> getAlphabetizedWords();
}