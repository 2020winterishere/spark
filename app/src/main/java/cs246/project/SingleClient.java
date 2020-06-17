package cs246.project;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "client_table")
public class  SingleClient {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "client")
    private String mClient;

    public SingleClient(String client) {this.mClient = client;}

    public String getClient(){return this.mClient;}
}