package hu.organicsoft.coroulin.dagger

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import hu.organicsoft.coroulin.data.endpoint.EndPointFactory
import hu.organicsoft.coroulin.data.endpoint.EndPointFactoryImpl
import hu.organicsoft.coroulin.data.endpoint.LastFmEndPoint
import hu.organicsoft.coroulin.data.mapper.LastFmMapper
import hu.organicsoft.coroulin.data.mapper.LastFmMapperImpl
import hu.organicsoft.coroulin.data.repository.LastFmRepositoryImpl
import hu.organicsoft.coroulin.domain.repository.LastFmRepository

@Module
@InstallIn(ViewModelComponent::class)
class ApplicationModule {

    @Provides
    fun provideEndPointFactory(@ApplicationContext context: Context): EndPointFactory {
        return EndPointFactoryImpl(context)
    }

    @Provides
    fun provideLastFmEndPoint(endPointFactory: EndPointFactory): LastFmEndPoint {
        return endPointFactory.create(LastFmEndPoint::class.java)
    }

    @Provides
    fun provideLastFmMapper(): LastFmMapper {
        return LastFmMapperImpl()
    }

    @Provides
    fun provideLastFmRepository(@ApplicationContext context: Context,
                                lastFmEndPoint: LastFmEndPoint,
                                lastFmMapper: LastFmMapper): LastFmRepository {
        return LastFmRepositoryImpl(context, lastFmEndPoint, lastFmMapper)
    }
}
