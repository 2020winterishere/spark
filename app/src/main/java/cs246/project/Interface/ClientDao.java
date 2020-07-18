package cs246.project.Interface;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import cs246.project.Entity.SingleClient;

/**
 * <h1>Client Data Access Object</h1>
 * <p>
 *     This DAO validates SQL at compile-time and associates it with a method.
 *     This DAO uses annotations such as @Insert to represent most common database
 *     operations. By default all queries are executed on separate threads.
 * </p>
 */

@Dao
public interface ClientDao {

    // allowing the insert of the same client multiple times by passing a
    // conflict resolution strategy
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(SingleClient client);

    @Query("DELETE FROM client_table")
    void deleteAll();

    @Query("SELECT * from client_table ORDER BY name ASC")
    LiveData<List<SingleClient>> getAlphabetizedWords();

    //delete client
    @Query("DELETE FROM " + SingleClient.TABLE_NAME + " WHERE name = :name")
    int deleteByName(String name);

    //select client
    @Query("SELECT * FROM " + SingleClient.TABLE_NAME + " WHERE name = :name")
    SingleClient getByName(String name);

    //update
    @Update
    int updateEntity(SingleClient client);
}