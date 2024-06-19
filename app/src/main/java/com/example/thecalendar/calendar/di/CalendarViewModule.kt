package com.example.thecalendar.calendar.di

import com.example.thecalendar.calendar.data.CalendarLocalDataSource
import com.example.thecalendar.calendar.data.CalendarRepository
import com.example.thecalendar.calendar.data.GoogleAccountCredentialHelper
import com.example.thecalendar.calendar.data.GoogleCalendarHelper
import com.example.thecalendar.calendar.data.ICalendarLocalDataSource
import com.example.thecalendar.calendar.data.ICalendarRepository
import com.example.thecalendar.calendar.data.IGoogleCalendarHelper
import com.example.thecalendar.calendar.data.IGoogleCredentialsHelper
import com.example.thecalendar.calendar.domain.CreateEventUseCase
import com.example.thecalendar.calendar.domain.FetchAndSaveCalenderEventsUseCase
import com.example.thecalendar.calendar.domain.FetchDayEventsUseCase
import com.example.thecalendar.calendar.domain.GetCalendarDaysUseCase
import com.example.thecalendar.calendar.domain.ICreateEventUseCase
import com.example.thecalendar.calendar.domain.IFetchAndSaveCalenderEventsUseCase
import com.example.thecalendar.calendar.domain.IFetchDayEventsUseCase
import com.example.thecalendar.calendar.domain.IGetCalendarDaysUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class CalendarViewModule {

    @Binds
    abstract fun provideCalendarDaysUseCase(getCalendarDaysUseCase: GetCalendarDaysUseCase): IGetCalendarDaysUseCase

    @Binds
    abstract fun provideCFetchAndSaveUseCase(fetchAndSaveCalenderEventsUseCase: FetchAndSaveCalenderEventsUseCase): IFetchAndSaveCalenderEventsUseCase

    @Binds
    abstract fun provideFetchDayEventsUseCase(fetchDayEventsUseCase: FetchDayEventsUseCase): IFetchDayEventsUseCase

    @Binds
    abstract fun provideCreateEventUseCase(createEventUseCase: CreateEventUseCase): ICreateEventUseCase


    @Binds
    @Singleton
    abstract fun provideGoogleCredentialHelper(googleAccountCredentialHelper: GoogleAccountCredentialHelper): IGoogleCredentialsHelper

    @Binds
    @Singleton
    abstract fun provideGoogleCalendarHelper(googleCalendarHelper: GoogleCalendarHelper): IGoogleCalendarHelper

    @Binds
    @Singleton
    abstract fun provideCalendarRepository(calendarRepository: CalendarRepository): ICalendarRepository


}