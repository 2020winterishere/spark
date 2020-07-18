package cs246.project.Entity;

import android.provider.BaseColumns;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;


/**
 * <h1> Single Client Class</h1>
 * <p>
 *     This class will describe the Entity which represents an SQLite table for all clients.
 *     Each property in this class represents a column in the table.
 *     Room will ultimately use these properties to both create the table and instantiate objects
 *     from rows in the database.
 *</p>
 */


@Entity(tableName = SingleClient.TABLE_NAME)
public class  SingleClient {

    public static final String TABLE_NAME = "client_table";

    /*public static final String COLUMN_NAME = "client";*/

    /** The name od the ID column. */
    public static final String COLUMN_ID = BaseColumns._ID;

    @PrimaryKey
    @NonNull
    @ColumnInfo //(name = "client")
    private String name;

    @NonNull
    public String getName(){ return this.name;}

    public void setName(@NonNull String name) {this.name = name;}

    @ColumnInfo(name = "phone")
    private String phone;

    @ColumnInfo(name = "comments")
    private String comments;

    public SingleClient(@NonNull String name, String phone, String comments) {
        this.name = name;
        this.phone = phone;
        this.comments = comments;
    }

    public String getPhone() { return phone;}

    public void setPhone(String phone) {this.phone = phone;}

    public String getComments() {return comments;}

    public void setComments(String comments) {this.comments = comments;}
}