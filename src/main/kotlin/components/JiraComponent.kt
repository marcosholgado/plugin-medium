package components

import com.intellij.openapi.components.*
import com.intellij.openapi.project.Project
import com.intellij.util.xmlb.XmlSerializerUtil
import java.io.Serializable

@State(name = "JiraConfiguration",
        storages = [Storage(value = "jiraConfiguration.xml")])
class JiraComponent(project: Project? = null) :
        AbstractProjectComponent(project),
        Serializable,
        PersistentStateComponent<JiraComponent> {

    var username: String = ""
    var token: String = ""
    var url: String = ""
    var regex: String = ""

    override fun getState(): JiraComponent? = this

    override fun loadState(state: JiraComponent) =
            XmlSerializerUtil.copyBean(state, this)

    companion object {
        fun getInstance(project: Project): JiraComponent =
                project.getComponent(JiraComponent::class.java)
    }
}