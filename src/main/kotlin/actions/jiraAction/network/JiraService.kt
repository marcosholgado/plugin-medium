package actions.jiraAction.network

import io.reactivex.Completable
import io.reactivex.Single
import retrofit2.http.*

interface JiraService {

    @GET("issue/{issueId}/transitions")
    fun getTransitions(@Header("Authorization") authKey: String,
                       @Path("issueId") issueId: String): Single<TransitionsResponse>

    @POST("issue/{issueId}/transitions")
    fun doTransition(@Header("Authorization") authKey: String,
                     @Path("issueId") issueId: String,
                     @Body transitionData: TransitionData): Completable
}