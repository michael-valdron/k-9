package app.k9mail.feature.account.setup.domain.input

import assertk.all
import assertk.assertThat
import assertk.assertions.isEqualTo
import assertk.assertions.isFalse
import assertk.assertions.isNull
import assertk.assertions.isTrue
import assertk.assertions.prop
import org.junit.Test

class StringInputFieldTest {

    @Test
    fun `should set default values`() {
        val stringInputState = StringInputField()

        assertThat(stringInputState).all {
            prop(StringInputField::value).isEqualTo("")
            prop(StringInputField::errorMessage).isNull()
            prop(StringInputField::isValid).isFalse()
        }
    }

    @Test
    fun `should reset errorMessage and isValid when value changed`() {
        val initialInputState = StringInputField(errorMessage = "error", isValid = false)

        val result = initialInputState.updateValue("new value")

        assertThat(result).all {
            prop(StringInputField::value).isEqualTo("new value")
            prop(StringInputField::errorMessage).isNull()
            prop(StringInputField::isValid).isFalse()
        }
    }

    @Test
    fun `should reset isValid when error set`() {
        val initialInputState = StringInputField(value = "input", isValid = true)

        val result = initialInputState.updateErrorMessage("error")

        assertThat(result).all {
            prop(StringInputField::value).isEqualTo("input")
            prop(StringInputField::errorMessage).isEqualTo("error")
            prop(StringInputField::isValid).isFalse()
        }
    }

    @Test
    fun `should reset errorMessage when valid`() {
        val initialInputState = StringInputField(value = "input", errorMessage = "error")

        val result = initialInputState.updateValidity(isValid = true)

        assertThat(result).all {
            prop(StringInputField::value).isEqualTo("input")
            prop(StringInputField::errorMessage).isNull()
            prop(StringInputField::isValid).isTrue()
        }
    }

    @Test
    fun `should not reset errorMessage when invalid`() {
        val initialInputState = StringInputField(
            value = "input",
            errorMessage = "error",
        )

        val result = initialInputState.updateValidity(isValid = false)

        assertThat(result).all {
            prop(StringInputField::value).isEqualTo("input")
            prop(StringInputField::errorMessage).isEqualTo("error")
            prop(StringInputField::isValid).isFalse()
        }
    }
}
