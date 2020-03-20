package utils;

import persistence.entities.ReglaBonificacion;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;

public class UtilsGenerals {

    public static Date TraeFecha(LocalDate localDate) {
        Date date = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        return date;
    }

    public static boolean esNumero(String s) {
        boolean esNumero;
        try {
            Integer.valueOf(s);
            esNumero = true;
        } catch (Exception ex) {
            esNumero = false;
        }
        return esNumero;
    }

    public static int traePeriodo(int y, int m) {
        int periodo;
        if (m > 9) {
            periodo = Integer.valueOf(String.valueOf(y) + String.valueOf(m));
        } else {
            periodo = Integer.valueOf(y + "0" + m);
        }
        return periodo;
    }


    public static int devuelvePeriodoActual() {
        int periodoActual;
        int year = Calendar.getInstance().get(Calendar.YEAR);
        int month = Calendar.getInstance().get(Calendar.MONTH) + 1;
        periodoActual = traePeriodo(year, month);
        return periodoActual;
    }

    public static int devuelveCuotas(int mesInicial, int mesFinal, int anioInicial, int anioFinal) {
        int cuotas = 0;
        boolean flagMesInicial = true;
        for (int y = anioInicial; y <= anioFinal; y++) {
            if (anioFinal != y) {
                if (flagMesInicial) {
                    for (int m = mesInicial; m <= 12; m++) {
                        cuotas++;
                    }
                    flagMesInicial = false;
                } else {
                    for (int m = 1; m <= 12; m++) {
                        cuotas++;
                    }
                }
            } else if (flagMesInicial) {
                for (int m = mesInicial; m <= mesFinal; m++) {
                    cuotas++;
                }
                flagMesInicial = false;
            } else {
                for (int m = 1; m <= mesFinal; m++) {
                    cuotas++;
                }
            }
        }
        return cuotas;
    }

    /**
     * Esta funcion devuelve el valor de descuento de acuerdo al tipo de
     * bonificacion
     *
     * @param rb
     * @param subTotal
     * @return Boolean true or false
     */
    public static int calculaBonificacion(ReglaBonificacion rb, int subTotal) {
        int total = 0;
        switch (rb.getTipoBonificacion()) {
            case 0: //Valor
            case 2: //Habitaciones
                total = rb.getValor();
                break;
            case 1: //Porcentaje
                total = subTotal * (rb.getValor() / 100);
                break;
        }
        return total;
    }
}
