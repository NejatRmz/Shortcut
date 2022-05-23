package com.example.shortcut.di
import com.example.shortcut.data.remote.IService
import com.example.shortcut.data.remote.RemoteDataSource
import com.example.shortcut.data.remote.repository.TestCardRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
class NetworkModule {

    @Provides
    fun provideService(retrofit: Retrofit): IService = retrofit.create(IService::class.java)

    @Singleton
    @Provides
    fun provideRemoteDataSource(service: IService) = RemoteDataSource(service)

    @Singleton
    @Provides
    fun provideRepository(remoteDataSource: RemoteDataSource) = TestCardRepositoryImpl(remoteDataSource)

}