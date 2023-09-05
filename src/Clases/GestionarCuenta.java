package Clases;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Optional;

public class GestionarCuenta {
    ArrayList<Cuenta> lista;
    public void iniciar() {
        lista = new ArrayList<> ();
        int op;
        while (true) {
            String menu = "Selecciona una opción" +
                    "\n 1: Registrar cuenta." +
                    "\n 2: Consignar cuenta." +
                    "\n 3: Retirar de la cuenta." +
                    "\n 4: Mostrar cuenta." +
                    "\n 5: Salir.";
            op = Integer.parseInt (JOptionPane.showInputDialog (menu));
            switch (op) {
                case 1:
                    registrarCuenta ();
                    break;
                case 2:
                    consignarCuenta ();
                    break;
                case 3:
                    retirarCuenta ();
                    break;
                case 4:
                    mostrarCuenta ();
                    break;
                case 5:
                    System.exit (0);
            }
        }
    }

    private void registrarCuenta() {
        int codigo = ingresar ("Ingrese el número de la cuenta");
        int titular = ingresar ("Ingrese el documento del titular");
        double saldo = ingresar ("Ingrese el saldo");
        int retiros = ingresar ("Ingresar la cantidad de retiros");
        Cuenta cuenta;
        if (retiros == 0) {
            cuenta = new Cuenta ();
            cuenta.setCodigo (codigo);
            cuenta.setTitular (titular);
            cuenta.setSaldo (saldo);
        } else if (retiros >= 1 && retiros <= 4) {
            cuenta = new Cuenta (codigo, titular, saldo, retiros);
        } else {
            cuenta = new Cuenta (codigo, titular, new int[retiros], saldo);
        }

        lista.add (cuenta);
        mostrar("Cuenta registrada exitosamente");
    }

    public void consignarCuenta() {
        lista.stream ()
                .findAny ()
                .ifPresent (
                        cuenta -> {
                            int codigo = ingresar ("Ingrese el número de la cuenta");
                            Optional<Cuenta> cuenta1 = Optional.ofNullable (validar (codigo));
                            if (cuenta1.isPresent ()) {
                                boolean respuesta = cuenta1.get ()
                                        .consignar (ingresar ("Ingrese el valor a consignar"));
                                String mensaje = respuesta ? "Consignación exitosa" : "Error. Valor incorrecto";
                                mostrar (mensaje);
                            } else {
                                mostrar ("No se encontró la cuenta");
                            }
                        }
                );
    }

    public void retirarCuenta() {
        int codigo = ingresar("Ingrese el número de la cuenta");
        Optional<Cuenta> optionalCuenta = Optional.ofNullable (validar (codigo));
        optionalCuenta.ifPresentOrElse (
                cuenta -> {
                    int valor = ingresar ("Ingrese el valor a retirar");
                    String respuesta = cuenta.retirar (valor);
                    mostrar (respuesta);
                },
                () -> mostrar ("La cuenta no existe")
        );
    }

    public void mostrarCuenta() {
        int codigo = ingresar("Ingrese el número de la cuenta");
        Optional<Cuenta> optionalCuenta = Optional.ofNullable (validar (codigo));
        optionalCuenta.ifPresentOrElse (
                cuenta -> mostrar (cuenta.listarInformacion ()),
                () -> mostrar ("La cuenta no existe")
        );
    }

    public Cuenta validar(int cod) {
        return lista.stream ()
                .filter (cuenta -> cuenta.getCodigo () == cod)
                .findFirst ()
                .orElse (null);
    }

    private int ingresar(String mensaje) {
        return Integer.parseInt (JOptionPane.showInputDialog (mensaje));
    }
    
    private void mostrar(String mensaje){
        JOptionPane.showMessageDialog(null, mensaje);
    }
}
