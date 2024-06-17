package com.example.thecalendar.calendar.di

import com.example.thecalendar.calendar.domain.GetCalendarDaysUseCase
import com.example.thecalendar.calendar.domain.IGetCalendarDaysUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class CalendarViewModule {

    @Binds
    abstract fun provideCalendarDaysUseCase(getCalendarDaysUseCase: GetCalendarDaysUseCase): IGetCalendarDaysUseCase
}