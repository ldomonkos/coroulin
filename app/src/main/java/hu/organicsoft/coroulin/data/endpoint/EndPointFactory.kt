package hu.organicsoft.coroulin.data.endpoint

interface EndPointFactory {

    fun <T>create(endPointClass: Class<T>): T
}
