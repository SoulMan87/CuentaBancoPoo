package Clases;

import java.util.ArrayList;
import javax.swing.JOptionPane;

public class GestionarCuenta {

    ArrayList<Cuenta> lista;

    public void iniciar() {
        lista = new ArrayList();
        String menu = "Seleciona una opción";
        menu += "\n " + "1: Resgistrar cuenta.";
        menu += "\n " + "2: Consignar cuenta.";
        menu += "\n " + "3: Retirar de la cuenta.";
        menu += "\n " + "4: Mostrar cuenta.";
        menu += "\n " + "5: Salir.";
        int op;
        do {
            op = Integer.parseInt(JOptionPane.showInputDialog(menu));
            switch (op) {
                case 1:
                    registrarCuenta();
                    break;
                case 2:
                    consignarCuenta();
                    break;
                case 3:
                    retirarCuenta();
                    break;
                case 4:
                    mostrarCuenta();
                    break;
                case 5:
                    System.exit(0);
            }
        } while (true);

    }

    private void registrarCuenta() {
        int codigo = ingresar("Ingrese el número de la cuenta");
        int titular = ingresar("Ingrese el documento del titular");
        double saldo = (double) ingresar("Ingrese el saldo");
        int retiros = ingresar("Ingresar la cantidad de retiros");
        Cuenta cuenta;
        if (retiros == 0) {
            cuenta = new Cuenta();
            cuenta.setCodigo(codigo);
            cuenta.setTitular(titular);
            cuenta.setSaldo(saldo);
        } else if (retiros >= 1 && retiros <= 4) {
            cuenta = new Cuenta(codigo, titular, saldo, retiros);
        } else {
            cuenta = new Cuenta(codigo, titular, new int[retiros], saldo);
        }
        lista.add(cuenta);
        mostrar("Cuenta registrada exitosamente");
    }

    public void consignarCuenta() {
        if (lista.isEmpty()) {
            mostrar("Lista vacia");
        } else {
            int codigo = ingresar("Ingrese el número de la cuenta");
            Cuenta cuenta2 = validar(codigo);
            if (cuenta2 == null) {
                mostrar("No se encontró la cuenta");
            } else {
                boolean respuesta = cuenta2.consignar(ingresar("Ingrese el valor a consignar"));
                String mensaje = (respuesta == true) ? "Consignación exitosa" : "Error. Valor incorrecto";
                mostrar(mensaje);
            }
        }
    }

    public void retirarCuenta() {
        int codigo = ingresar("Ingrese el número de la cuenta");
        Cuenta cuenta3 = validar(codigo);
        if (cuenta3 == null) {
            mostrar("La cuenta no existe");
        } else {
            int valor = ingresar("Ingrese el valor a retirar");
            String respuesta = cuenta3.retirar(valor);
            mostrar(respuesta);
        }
    }

    public void mostrarCuenta() {
        int codigo = ingresar("Ingrese el número de la cuenta");
        Cuenta cuenta4 = validar(codigo);
        if (cuenta4 == null) {
            mostrar("La cuenta no existe");
        } else {
            mostrar(cuenta4.listarInformacion());
        }
    }

    public Cuenta validar(int cod) {
        for (Cuenta lista1 : lista) {
            if (lista1.getCodigo() == cod) {
                return lista1;
            }
        }
        return null;
    }

    private int ingresar(String mensaje) {
        int datos = Integer.parseInt(JOptionPane.showInputDialog(mensaje));
        return datos;
    }
    
    private void mostrar(String mensaje){
        JOptionPane.showMessageDialog(null, mensaje);
    }
}
