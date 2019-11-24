package Clases;

public class Cuenta {

    private int codigo;
    private int titular;
    private int retiros[];
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

    public int getTitular() {
        return titular;
    }

    public void setTitular(int titular) {
        this.titular = titular;
    }

    public int[] getRetiros() {
        return retiros;
    }

    public void setRetiros(int[] retiros) {
        this.retiros = retiros;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public boolean consignar(int valor) {
        if (valor > 0) {
            saldo += valor;
            return true;
        } else {
            return false;
        }
    }

    public String retirar(int valor) {
        if (retirosDisponibles() == 0) {
            return "No tiene retiros disponibles";
        } else if (valor == 0) {
            return "Error. Valor cero";
        } else if (saldo >= valor) {
            saldo -= valor;
            for (int i = 0; i < retiros.length; i++) {
                if (retiros[i] == 0) {
                    retiros[i] = valor;
                    break;
                }
            }
            return "Retiro exitoso";
        } else {
            return "Su saldo es: " + saldo;
        }
    }

    private int retirosDisponibles() {
        int cont = 0;
        for (int valor : retiros) {
            if (valor != 0) {
                cont++;
            } else {
                break;
            }
        }
        return (retiros.length - cont);
    }

    public String listarInformacion() {
        String datos = "Información cuenta";
        datos += "Número: " + codigo + "\n";
        datos += "Titular: " + titular + "\n";
        datos += "Saldo: " + saldo + "\n";
        datos += "Retiros: " + obtenerRetiros() + "\n";
        datos += "Retiros Disponibles: " + retirosDisponibles() + "\n";
        return datos;

    }

    private String obtenerRetiros() {
        String valores = "";
        for (int val : retiros) {
            if (val != 0) {
                valores += val + " ";
            } else {
                break;
            }
        }
        return valores;
    }

}
