package com.example.employeedirectory.di

import com.example.employeedirectory.data.remote.EmployeeRemoteDataSource
import com.example.employeedirectory.data.remote.RetrofitDataSource
import com.example.employeedirectory.data.repository.EmployeeRepository
import com.example.employeedirectory.data.repository.EmployeeRepositoryImpl
import com.example.employeedirectory.data.network.EmployeeApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Qualifier

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    const val baseUrl = "https://s3.amazonaws.com/"

    @Provides
    fun provideEmployeeApi(): EmployeeApi =
        Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(MoshiConverterFactory.create())
        .build().create(EmployeeApi::class.java)

    @Provides
    fun provideEmployeeRemoteDataSource(employeeApi: EmployeeApi): EmployeeRemoteDataSource =
        RetrofitDataSource(employeeApi)

    @Provides
    fun provideEmployeeRepository(
        remoteDataSource: EmployeeRemoteDataSource,
        @IoDispatcher dispatcher: CoroutineDispatcher): EmployeeRepository =
        EmployeeRepositoryImpl(remoteDataSource, dispatcher)

    @IoDispatcher
    @Provides
    fun providesIoDispatcher(): CoroutineDispatcher = Dispatchers.IO

    @Retention(AnnotationRetention.BINARY)
    @Qualifier
    annotation class IoDispatcher
}