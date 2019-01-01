package actions.jiraAction

import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent

class JiraMoveAction : AnAction() {

    override fun actionPerformed(event: AnActionEvent) {
        val dialog = JiraMoveDialog(event.project!!)
        dialog.show()
    }
}