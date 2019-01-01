package actions.jiraAction.di

import actions.jiraAction.JiraMoveDialog
import actions.jiraAction.network.JiraService
import com.intellij.openapi.project.Project
import components.JiraComponent
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

@Module
class JiraModule(
        private val view: JiraMoveDialog,
        private val project: Project
) {
    @Provides
    fun provideView() : JiraMoveDialog = view

    @Provides
    fun provideProject() : Project = project

    @Provides
    fun provideComponent() : JiraComponent =
            JiraComponent.getInstance(project)

    @Provides
    fun providesJiraService(component: JiraComponent) : JiraService {
        val jiraURL = component.url
        val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(jiraURL)
                .build()

        return retrofit.create(JiraService::class.java)
    }
}