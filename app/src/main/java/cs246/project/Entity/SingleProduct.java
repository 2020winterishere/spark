package cs246.project.Entity;

import android.provider.BaseColumns;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = SingleProduct.TABLE_NAME)
public class SingleProduct {
    public static final String TABLE_NAME = "product_table";

    public static final String COLUMN_NAME = "product";

    /** The name od the ID column. */
    public static final String COLUMN_ID = BaseColumns._ID;

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(index = true, name = COLUMN_ID)
    private long id;

    @ColumnInfo(name = "name")
    private String name;

    @ColumnInfo(name = "description")
    private String description;

    @ColumnInfo(name = "stock")
    private int stock;

    public SingleProduct(long id, String name, String description, int stock) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.stock = stock;
    }

    /**
     * Get ID of the product
     * @return
     */
    public long getId() { return id; }

    /**
     * Set ID of the product
     * @return
     */
    public void setId(long id) { this.id = id; }

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
     * Get description of the product
     * @return
     */
    public String getDescription() { return description; }

    /**
     * Set descripton of the product
     * @return
     */
    public void setDescription(String description) { this.description = description; }

    /**
     * Get number of units on inventory of the product
     * @return
     */
    public int getStock() { return stock; }

    /**
     * Set number of units on inventory of the product
     * @return
     */
    public void setStock(int stock) { this.stock = stock; }
}
