package settings

import com.intellij.openapi.options.Configurable
import com.intellij.openapi.project.Project
import components.JiraComponent
import javax.swing.*
import javax.swing.event.DocumentEvent
import javax.swing.event.DocumentListener


class JiraSettings(private val project: Project): Configurable, DocumentListener {
    private val passwordField = JPasswordField()
    private val txtUsername = JTextField()

    private var modified = false

    override fun isModified(): Boolean = modified

    override fun getDisplayName(): String = "MyPlugin Jira"

    override fun apply() {
        val config = JiraComponent.getInstance(project)
        config.username = txtUsername.text
        config.password = String(passwordField.password)

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
        mainPanel.setBounds(0, 0, 452, 120)
        mainPanel.layout = null

        val lblUsername = JLabel("Username")
        lblUsername.setBounds(30, 25, 83, 16)
        mainPanel.add(lblUsername)

        val lblPassword = JLabel("Password")
        lblPassword.setBounds(30, 74, 83, 16)
        mainPanel.add(lblPassword)

        passwordField.setBounds(125, 69, 291, 26)
        mainPanel.add(passwordField)

        txtUsername.setBounds(125, 20, 291, 26)
        mainPanel.add(txtUsername)
        txtUsername.columns = 10

        passwordField.document?.addDocumentListener(this)
        txtUsername.document?.addDocumentListener(this)

        val config = JiraComponent.getInstance(project)
        txtUsername.text = config.username
        passwordField.text = config.password

        return mainPanel
    }
}