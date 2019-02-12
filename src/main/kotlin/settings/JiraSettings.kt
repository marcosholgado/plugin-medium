package settings

import com.intellij.openapi.options.Configurable
import com.intellij.openapi.project.Project
import components.JiraComponent
import utils.StringsBundle
import javax.swing.*
import javax.swing.event.DocumentEvent
import javax.swing.event.DocumentListener
import javax.swing.JTextField
import javax.swing.JPasswordField

class JiraSettings(private val project: Project): Configurable, DocumentListener {
    private val tokenField: JPasswordField = JPasswordField()
    private val txtUsername: JTextField = JTextField()
    private val txtUrl: JTextField = JTextField()
    private val txtRegEx: JTextField = JTextField()

    private var modified = false

    override fun isModified(): Boolean = modified

    override fun getDisplayName(): String = StringsBundle.message("plugin.name")

    override fun apply() {
        val config = JiraComponent.getInstance(project)
        config.username = txtUsername.text
        config.token = String(tokenField.password)
        config.url = txtUrl.text
        config.regex = txtRegEx.text

        modified = false
    }

    override fun changedUpdate(e: DocumentEvent?) {
        modified = true
    }

    override fun insertUpdate(e: DocumentEvent?) {
        modified = true
    }

    override fun removeUpdate(e: DocumentEvent?) {
        modified = true
    }

    override fun createComponent(): JComponent {

        val mainPanel = JPanel()
        mainPanel.setBounds(0, 0, 452, 254)
        mainPanel.layout = null

        val lblUsername = JLabel(StringsBundle.message("settings.username"))
        lblUsername.setBounds(30, 25, 83, 16)
        mainPanel.add(lblUsername)

        val lblPassword = JLabel(StringsBundle.message("settings.token"))
        lblPassword.setBounds(30, 74, 83, 16)
        mainPanel.add(lblPassword)

        val lblUrl = JLabel(StringsBundle.message("settings.jiraUrl"))
        lblUrl.setBounds(30, 123, 83, 16)
        mainPanel.add(lblUrl)

        val lblRegEx = JLabel(StringsBundle.message("settings.regEx"))
        lblRegEx.setBounds(30, 172, 83, 16)
        mainPanel.add(lblRegEx)

        txtUsername.setBounds(125, 20, 291, 26)
        txtUsername.columns = 10
        mainPanel.add(txtUsername)

        tokenField.setBounds(125, 69, 291, 26)
        mainPanel.add(tokenField)

        txtUrl.setBounds(125, 118, 291, 26)
        txtUrl.columns = 10
        mainPanel.add(txtUrl)

        txtRegEx.setBounds(125, 167, 291, 26)
        txtRegEx.columns = 10
        mainPanel.add(txtRegEx)

        val config = JiraComponent.getInstance(project)
        txtUsername.text = config.username
        tokenField.text = config.token
        txtUrl.text = config.url
        txtRegEx.text = config.regex

        tokenField.document?.addDocumentListener(this)
        txtUsername.document?.addDocumentListener(this)
        txtUrl.document?.addDocumentListener(this)
        txtRegEx.document?.addDocumentListener(this)

        return mainPanel
    }
}