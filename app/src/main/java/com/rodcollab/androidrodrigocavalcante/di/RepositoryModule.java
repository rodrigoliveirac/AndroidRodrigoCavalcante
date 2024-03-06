package com.rodcollab.androidrodrigocavalcante.di;

import com.rodcollab.androidrodrigocavalcante.repository.ClientRepository;
import com.rodcollab.androidrodrigocavalcante.repository.ClientRepositoryImpl;
import com.rodcollab.androidrodrigocavalcante.repository.OrdersHistoryRepository;
import com.rodcollab.androidrodrigocavalcante.repository.OrdersHistoryRepositoryImpl;

import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;

@Module
@InstallIn(SingletonComponent.class)
public abstract class RepositoryModule {

    @Singleton
    @Binds
    public abstract ClientRepository providesHabitRepository(ClientRepositoryImpl impl);

    @Singleton
    @Binds
    public abstract OrdersHistoryRepository providesProgressRepository(OrdersHistoryRepositoryImpl impl);
}
