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
/**
 * <h1>Product Data Access Object</h1>
 * <p>
 *     This DAO validates SQL at compile-time and associates it with a method.
 *     This DAO uses annotations such as @Insert to represent most common database
 *     operations. By default all queries are executed on separate threads.
 * </p>
 */

@Dao
public interface ProductDao {
    // allowing the insert of the same word multiple times by passing a
    // conflict resolution strategy

    /**
     * add a new product to the database
     * @param product a single product in the database
     */
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(SingleProduct product);

    /**
     * Delete all products of the database
     */
    @Query("DELETE FROM product_table")
    void deleteAll();

    /**
     * Order products of the database by name
     */
    @Query("SELECT * from product_table ORDER BY name ASC")
    LiveData<List<SingleProduct>> getAlphabetizedWords();

    /**
     * Delete a product by ID
     */
    @Query("DELETE FROM " + SingleProduct.TABLE_NAME + " WHERE name = :name")
    int deleteByName(String name);

    /**
     * Edit product
     * @param product single product
     */
    //update
    @Update
    int updateEntity(SingleProduct product);
}
