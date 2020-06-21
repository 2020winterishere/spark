package cs246.project.Interface;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import cs246.project.Entity.SingleClient;
import cs246.project.Entity.SingleProduct;

@Dao
public interface ProductDao {
    // allowing the insert of the same word multiple times by passing a
    // conflict resolution strategy
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(SingleProduct product);

    @Query("DELETE FROM product_table")
    void deleteAll();

    @Query("SELECT * from product_table ORDER BY name ASC")
    LiveData<List<SingleProduct>> getAlphabetizedWords();

    //delete client
    @Query("DELETE FROM " + SingleProduct.TABLE_NAME + " WHERE " + SingleProduct.COLUMN_ID + " = :id")
    int deleteByID(String id);

    //update
    @Update
    int updateEntity(SingleProduct client);
}