import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class Productos(
    val nombre: String,
    val precio: Int,
    val imagenResId: Int,
    var cantidad: Int = 1,
    var activo: Boolean = false,
    var descripcion: String = ""
) : Parcelable

