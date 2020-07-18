package cs246.project.Entity;

import android.provider.BaseColumns;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
/**
 * <h1> Single Product Class</h1>
 * <p>
 *     This class will describe the Entity which represents an SQLite table for all products.
 *     Each property in this class represents a column in the table.
 *     Room will ultimately use these properties to both create the table and instantiate objects
 *     from rows in the database.
 *</p>
 */
@Entity(tableName = SingleProduct.TABLE_NAME)
public class SingleProduct {
    public static final String TABLE_NAME = "product_table";

    public static final String COLUMN_NAME = "product";

    /** The name od the ID column. */
    public static final String COLUMN_ID = BaseColumns._ID;

    @PrimaryKey @NonNull
    @ColumnInfo(name = "name")
    private String name;

    @ColumnInfo(name = "stock")
    private float stock;

    public SingleProduct(String name, float stock) {
        this.name = name;
        this.stock = stock;
    }
    /**
     * Get name of the product
     * @return
     */
    public String getName() { return name; }

    /**
     * Set name of the product
     * @return
     */
    public void setName(String name) { this.name = name; }

    /**
     * Get number of units on inventory of the product
     * @return
     */
    public float getStock() { return stock; }

    /**
     * Set number of units on inventory of the product
     * @return
     */
    public void setStock(float stock){ this.stock = stock; }
}
