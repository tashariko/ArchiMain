package com.tasha.archimain.network

import com.tasha.archimain.data.ApiResult
import com.tasha.archimain.data.ErrorType
import com.tasha.archimain.data.source.local.entity.LoUser
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import retrofit2.Response

abstract class BaseRepository<RESULT, REQUEST> {

    fun repoWork(
        databaseQuery: () -> Flow<RESULT>?,
        networkCall: suspend () -> ApiResult<REQUEST>,
        saveCallResult: suspend (REQUEST) -> Unit,
        parseNetworkResponse: (REQUEST) -> ApiResult<RESULT>
    ): Flow<ApiResult<RESULT>> = flow<ApiResult<RESULT>> {

        if (shouldfetchDataFromDbBeforeNetwork()) {
            databaseQuery.invoke()?.let { flow ->
                emit(ApiResult.loading(flow.single()))
            } ?: run {
                emit(ApiResult.error<RESULT>(ErrorType(ErrorType.Type.Generic), null))
                throw Exception("Provide datebaseQuery as shouldfetchDataFromDbBeforeNetwork is true")
            }
        } else {
            emit(ApiResult.loading())
        }

        //run it(remoteSource.fetchData()) here on running invoke method
        val responseStatus = networkCall.invoke()
        if (responseStatus.status == ApiResult.Status.SUCCESS) {
            responseStatus.data?.let { req ->
                if (shouldStoreDataInDbAfterNetwork()) {
                    saveCallResult(req)
                }
                emit(parseNetworkResponse(req))
            } ?: run {
                emit(ApiResult.error<RESULT>(responseStatus.errorType, null))
                throw Exception("Response is null")
            }

        } else if (responseStatus.status == ApiResult.Status.ERROR) {
            if (shouldfetchDataFromDbBeforeNetwork()) {
                databaseQuery.invoke()?.let { flow ->
                    val source = flow.map { ApiResult.success(it) }
                    emit(ApiResult.error<RESULT>(responseStatus.errorType, source.first().data))
                } ?: run {
                    emit(ApiResult.error<RESULT>(ErrorType(ErrorType.Type.Generic), null))
                    throw Exception("Provide datebaseQuery as shouldfetchDataFromDbBeforeNetwork is true")
                }
            } else {
                emit(ApiResult.error<RESULT>(responseStatus.errorType, null))
            }
        }


    }.flowOn(Dispatchers.IO).catch { e ->
        emit(ApiResult.error<RESULT>(ErrorType(ErrorType.Type.Generic), null))
        e.printStackTrace()
    }


    /**
     * return false when we dont want to save database transactions for this response.
     */
    protected open fun shouldfetchDataFromDbBeforeNetwork() = true


    /**
     * return false when we dont want to save database transactions for this response.
     */
    protected open fun shouldStoreDataInDbAfterNetwork() = true
}