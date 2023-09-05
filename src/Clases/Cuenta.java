package Clases;

import java.util.Arrays;
import java.util.stream.IntStream;

public class Cuenta {

    private int codigo;
    private int titular;
    private final int[] retiros;
    private double saldo;

    public Cuenta() {
        retiros = new int[4];
    }

    public Cuenta(int codigo, int titular, double saldo, int cantRetiros) {
        this.codigo = codigo;
        this.titular = titular;
        this.saldo = saldo;
        retiros = new int[cantRetiros];
    }

    public Cuenta(int codigo, int titular, int[] retiros, double saldo) {
        this.codigo = codigo;
        this.titular = titular;
        this.retiros = retiros;
        this.saldo = saldo;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public void setTitular(int titular) {
        this.titular = titular;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public boolean consignar(int valor) {
        return valor > 0 && (saldo = Math.addExact ((int) saldo, valor)) >= 0;
    }

    public String retirar(int valor) {
        if (retirosDisponibles() == 0) {
            return "No tiene retiros disponibles";
        } else if (valor == 0) {
            return "Error. Valor cero";
        } else if (saldo >= valor) {
            saldo -= valor;
            IntStream.range (0, retiros.length)
                    .filter (i -> retiros[i] == 0)
                    .findFirst ()
                    .ifPresent (i -> retiros[i] = valor);
            return "Retiro exitoso";
        } else {
            return "Su saldo es: " + saldo;
        }
    }

    private int retirosDisponibles() {

        int cont = IntStream.range (0, retiros.length)
                .filter (i -> retiros[i] == 0)
                .findFirst ()
                .orElse (retiros.length);
        return (retiros.length - cont);
    }

    public String listarInformacion() {
        return String.format ("Información cuenta\nNúmero: %d\nTitular: %d\nSaldo: %s\nRetiros:" +
                " %s\nRetiros Disponibles: " +
                "%d\n", codigo, titular, saldo, obtenerRetiros (), retirosDisponibles ());
    }

    private String obtenerRetiros() {

        return Arrays.stream (retiros)
                .filter (val -> val != 0)
                .mapToObj (String::valueOf)
                .reduce ((val1, val2) -> val1 + " " + val2)
                .orElse ("");
    }

}
