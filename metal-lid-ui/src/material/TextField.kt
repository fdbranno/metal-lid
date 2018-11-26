@file:JsModule("@material-ui/core/TextField")

package material

import react.RClass
import react.RProps

@JsName("default")
external val TextField: RClass<TextFieldProps>

external interface TextFieldProps : RProps {
    var id: String
    var label: String
    var className: String
    var margin: String
    var variant: String
}