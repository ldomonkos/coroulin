package hu.organicsoft.coroulin.data.endpoint

import android.content.Context
import hu.organicsoft.coroulin.R
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import timber.log.Timber


open class EndPointFactoryImpl(protected val context: Context): EndPointFactory {

    protected val retrofit: Retrofit

    init {
        val logging = HttpLoggingInterceptor {
            Timber.d(it)
        }
        logging.level = HttpLoggingInterceptor.Level.BODY

        val client: OkHttpClient = OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()

        retrofit = Retrofit.Builder()
            .baseUrl(context.getString(R.string.base_url))
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }

    override fun <T> create(endPointClass: Class<T>): T {
        return retrofit.create(endPointClass)
    }
}