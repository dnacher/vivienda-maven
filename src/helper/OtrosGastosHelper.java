package helper;

import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import persistence.entities.Concepto;
import persistence.entities.Monto;
import persistence.entities.Unidad;
import persistence.enums.errores;

public class OtrosGastosHelper {


    public static int validar(TextField txtSecuencia, ComboBox<Monto> cmbTipoMoneda, TextField txtMonto, DatePicker cmbFecha, ComboBox<Concepto> cmbConcepto, Unidad unidad) {
        int i = 0;
        if (txtSecuencia.getText().isEmpty()) {
            i = errores.FALTA_SECUENCIA.getCodigo();
        } else {
            if (cmbTipoMoneda.getValue() == null) {
                i = errores.FALTA_TIPO_MONEDA.getCodigo();
            } else {
                if (txtMonto.getText().isEmpty()) {
                    i = errores.FALTA_MONTO.getCodigo();
                } else {
                    if (cmbFecha.getValue() == null) {
                        i = errores.FALTA_FECHA.getCodigo();
                    } else {
                        if (cmbConcepto.getValue() == null) {
                            i = errores.FALTA_CONCEPTO.getCodigo();
                        } else {
                            if (unidad == null) {
                                i = errores.FALTA_UNIDAD.getCodigo();
                            }
                        }
                    }
                }
            }
        }
        return i;
    }

}
