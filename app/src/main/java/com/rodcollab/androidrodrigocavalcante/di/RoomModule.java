package com.rodcollab.androidrodrigocavalcante.di;

import android.app.Application;

import com.rodcollab.androidrodrigocavalcante.database.AppDatabase;
import com.rodcollab.androidrodrigocavalcante.database.ClientDao;
import com.rodcollab.androidrodrigocavalcante.database.ContactDao;
import com.rodcollab.androidrodrigocavalcante.database.OrderHistoryDao;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;

@Module
@InstallIn(SingletonComponent.class)
public class RoomModule {

    @Singleton
    @Provides
    public AppDatabase providesRoomDatabase(Application application) {
        return AppDatabase.Companion.getInstance(application);
    }

    @Singleton
    @Provides
    public ClientDao providesClientsDao(AppDatabase database) {
        return database.clientDao();
    }
    @Singleton
    @Provides
    public ContactDao providesContactsDao(AppDatabase database) {
        return database.contactDao();
    }

    @Singleton
    @Provides
    public OrderHistoryDao providesOrdersDao(AppDatabase database) {
        return database.orderHistoryDao();
    }
}
