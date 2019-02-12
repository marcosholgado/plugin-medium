package actions.jiraAction

import actions.jiraAction.network.Transition
import com.intellij.openapi.ui.ComboBox
import utils.StringsBundle
import java.awt.Dimension
import javax.swing.JLabel
import javax.swing.JPanel
import javax.swing.JTextField

class JiraMovePanel : JPanel() {

    private val comboTransitions = ComboBox<Transition>()
    val txtIssue = JTextField()

    init {
        initComponents()
    }

    private fun initComponents() {
        layout = null

        val lblJiraTicket = JLabel(StringsBundle.message("issue"))
        lblJiraTicket.setBounds(25, 33, 77, 16)
        add(lblJiraTicket)

        txtIssue.setBounds(114, 28, 183, 26)
        add(txtIssue)

        val lblTransition = JLabel(StringsBundle.message("transition"))
        lblTransition.setBounds(25, 75, 77, 16)
        add(lblTransition)

        comboTransitions.setBounds(114, 71, 183, 27)
        add(comboTransitions)
    }

    override fun getPreferredSize() = Dimension(300, 110)

    fun addTransition(transition: Transition) = comboTransitions.addItem(transition)

    fun setIssue(issue: String) {
        txtIssue.text = issue
    }

    fun getTransition() : Transition = comboTransitions.selectedItem as Transition
}