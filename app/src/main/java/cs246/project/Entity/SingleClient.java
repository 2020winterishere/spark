package cs246.project.Entity;

import android.provider.BaseColumns;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = SingleClient.TABLE_NAME)
public class  SingleClient {

    public static final String TABLE_NAME = "client_table";

    public static final String COLUMN_NAME = "client";

    /** The name od the ID column. */
    public static final String COLUMN_ID = BaseColumns._ID;

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "client")
    private String mClient;


    public String getClient(){return this.mClient;}

    public void setmClient(String mClient) {this.mClient = mClient;}

    public SingleClient(String name) {
        this.mClient = name;
    }

    /*@ColumnInfo(name = "phone")
    private String phone;

    @ColumnInfo(name = "comments")
    private String comments;

    public SingleClient(String client, String phone, String cooments) {
        this.mClient = client;
        this.phone = phone;
        this.comments = comments;
    }

    public String getPhone() { return phone;}

    public void setPhone(String phone) {this.phone = phone;}

    public String getComments() {return comments;}

    public void setComments(String comments) {this.comments = comments;} */
}