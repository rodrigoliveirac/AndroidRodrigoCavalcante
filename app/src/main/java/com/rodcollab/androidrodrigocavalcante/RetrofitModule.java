package com.rodcollab.androidrodrigocavalcante;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
@InstallIn(SingletonComponent.class)
public class RetrofitModule {

    private static final String BASE_URL = "https://private-anon-c0d84e6d79-maximatech.apiary-mock.com/";
    @Provides
    @Singleton
    public MaxApi provideMaxApiService(
    ) {
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(MaxApi.class);
    }
}
