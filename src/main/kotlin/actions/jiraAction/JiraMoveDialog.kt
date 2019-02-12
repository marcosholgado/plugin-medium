package actions.jiraAction

import actions.jiraAction.di.JiraModule
import actions.jiraAction.di.DaggerJiraDIComponent
import actions.jiraAction.network.Transition
import com.intellij.notification.NotificationType
import com.intellij.openapi.project.Project
import com.intellij.openapi.ui.DialogWrapper
import utils.StringsBundle
import utils.Utils
import javax.inject.Inject
import javax.swing.JComponent

class JiraMoveDialog constructor(private val project: Project) :
        DialogWrapper(true) {

    @Inject
    lateinit var presenter: JiraMoveDialogPresenter

    private val panel: JiraMovePanel = JiraMovePanel()

    init {
        DaggerJiraDIComponent.builder()
                .jiraModule(JiraModule(this, project))
                .build().inject(this)
        isModal = true
        presenter.load()
        init()
    }

    override fun createCenterPanel(): JComponent? = panel

    override fun doOKAction() = presenter.doTransition(panel.getTransition(), panel.txtIssue.text)

    fun setIssue(issue: String) = panel.setIssue(issue)

    fun setTransitions(transitionList: List<Transition>) {
        for (transition in transitionList) {
            panel.addTransition(transition)
        }
    }

    fun success() {
        close(DialogWrapper.OK_EXIT_CODE)
        Utils.createNotification(
                StringsBundle.message("common.success"),
                StringsBundle.message("issue.moved"),
                project,
                NotificationType.INFORMATION,
                null
        )
    }

    fun error(throwable: Throwable) {
        Utils.createNotification(
                StringsBundle.message("common.error"),
                throwable.localizedMessage,
                project,
                NotificationType.ERROR,
                null
        )
    }
}