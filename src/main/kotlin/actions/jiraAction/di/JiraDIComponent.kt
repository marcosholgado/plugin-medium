package actions.jiraAction.di

import actions.jiraAction.JiraMoveDialog
import dagger.Component

@Component(modules = [JiraModule::class])
interface JiraDIComponent {
    fun inject(jiraMoveDialog: JiraMoveDialog)
}