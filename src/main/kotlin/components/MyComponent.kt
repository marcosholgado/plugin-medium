package components

import com.intellij.ide.plugins.PluginManager
import com.intellij.notification.NotificationDisplayType
import com.intellij.notification.NotificationGroup
import com.intellij.notification.NotificationType
import com.intellij.openapi.components.ApplicationComponent
import com.intellij.openapi.components.PersistentStateComponent
import com.intellij.openapi.components.State
import com.intellij.openapi.components.Storage
import com.intellij.openapi.extensions.PluginId
import com.intellij.util.xmlb.XmlSerializerUtil
import java.io.Serializable


@State(name = "MyConfiguration", storages = [Storage(value = "myConfiguration.xml")])
class MyComponent: ApplicationComponent, Serializable, PersistentStateComponent<MyComponent> {

    var localVersion: String = "0.0"
    private lateinit var version: String

    override fun initComponent() {
        super.initComponent()

        version = PluginManager.getPlugin(PluginId.getId("myplugin.myplugin"))!!.version

        if (isANewVersion()) {
            updateLocalVersion()
            val noti = NotificationGroup("myplugin", NotificationDisplayType.BALLOON, true)
            noti.createNotification("Plugin updated", "Welcome to the new version", NotificationType.INFORMATION, null).notify(null)
        }
    }

    override fun getState(): MyComponent? = this

    override fun loadState(state: MyComponent) = XmlSerializerUtil.copyBean(state, this)

    private fun isANewVersion(): Boolean {
        val s1 = localVersion.split("-")[0].split(".")
        val s2 = version.split("-")[0].split(".")

        if (s1.size != s2.size) return false
        var i = 0

        do {
            if (s1[i] < s2[i]) return true
            i++
        } while (i < s1.size && i < s2.size)

        return false
    }

    private fun updateLocalVersion() {
        localVersion = version
    }

}