package utils

import com.intellij.CommonBundle
import com.intellij.reference.SoftReference
import org.jetbrains.annotations.NonNls
import org.jetbrains.annotations.PropertyKey
import java.lang.ref.Reference
import java.util.*

object StringsBundle {
    @NonNls
    private val BUNDLE_NAME = "messages.strings"
    private var ourBundle: Reference<ResourceBundle>? = null

    private fun getBundle(): ResourceBundle {
        var bundle = SoftReference.dereference(ourBundle)
        if (bundle == null) {
            bundle = ResourceBundle.getBundle(BUNDLE_NAME)
            ourBundle = SoftReference(bundle)
        }
        return bundle!!
    }

    fun message(@PropertyKey(resourceBundle = "messages.strings") key: String, vararg params: Any): String {
        return CommonBundle.message(getBundle(), key, *params)
    }

}