package cs246.project.Database;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import cs246.project.Entity.SingleClient;
import cs246.project.Interface.ClientDao;
import cs246.project.Database.ClientRoomDatabase;

@Database(entities = {SingleClient.class}, version = 1, exportSchema = false)
public abstract class ClientRoomDatabase extends RoomDatabase {

    @SuppressWarnings("WeakerAcces")
    public abstract ClientDao clientDao();

    private static volatile ClientRoomDatabase INSTANCE;

    private static final int NUMBER_OF_THREADS = 4;

    public static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public static ClientRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (ClientRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            ClientRoomDatabase.class, "client_database")
                            .addCallback(sRoomDatabaseCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    private static Callback sRoomDatabaseCallback = new Callback() {
        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);

            // If you want to keep data through app restarts,
            // comment out the following block
            databaseWriteExecutor.execute(() -> {
                // Populate the database in the background.
                // If you want to start with more words, just add them.
                ClientDao dao = INSTANCE.clientDao();
                dao.deleteAll();

                //Delete sample clients

               /* SingleClient client = new SingleClient("Hello");
                dao.insert(client);
                client = new SingleClient("World");
                dao.insert(client);*/
            });
        }
    };
}