package actions.jiraAction.network

data class Transition(val id: String, val name: String = "") {
    override fun toString(): String = name
}

data class TransitionsResponse(val transitions: List<Transition>)

data class TransitionData(val transition: Transition)