package actions

import com.intellij.notification.NotificationType
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import utils.StringsBundle
import utils.Utils

class MyAction: AnAction() {

    override fun actionPerformed(e: AnActionEvent) {
        Utils.createNotification(
                StringsBundle.message("action.title"),
                StringsBundle.message("action.message"),
                e.project,
                NotificationType.INFORMATION,
                null
        )
    }
}