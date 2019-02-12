package actions

import com.intellij.notification.NotificationDisplayType
import com.intellij.notification.NotificationGroup
import com.intellij.notification.NotificationType
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import utils.StringsBundle

class MyAction: AnAction() {

    override fun actionPerformed(e: AnActionEvent) {
        val noti = NotificationGroup("myplugin", NotificationDisplayType.BALLOON, true)
        noti.createNotification(
                StringsBundle.message("action.title"),
                StringsBundle.message("action.message"),
                NotificationType.INFORMATION,
                null
        ).notify(e.project)
    }
}