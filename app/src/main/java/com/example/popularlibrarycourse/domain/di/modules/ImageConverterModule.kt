package com.example.popularlibrarycourse.domain.di.modules

import com.example.popularlibrarycourse.domain.converter.IImageConverter
import com.example.popularlibrarycourse.domain.converter.ImageConverterImpl
import dagger.Binds
import dagger.Module
import javax.inject.Singleton


@Module
interface ImageConverterModule {

    @Singleton
    @Binds
    fun bindImageConverter(converter: ImageConverterImpl): IImageConverter
}