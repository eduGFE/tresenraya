package com.example.tresenraya;

public class LogicaTresEnRaya {


    private int tablero[];


    public LogicaTresEnRaya() {
        tablero = new int[9];
        for (int i = 0; i < tablero.length; i++) {
            tablero[i] = 0;
        }
    }

    public int getTablero(int pos) {
        int valor = 0;
        for (int i = 0; i < tablero.length; i++) {
            valor = tablero[pos];
        }
        return valor;
    }

    /* - boolean MovimientoValido(int pos), el método devolverá true si
     el movimiento es válido y false si no se puede realizar (la
     casilla ya está ocupada).*/
    public boolean MovimientoValido(int pos) {
        boolean MovimientoValido = true;
        if (tablero[pos - 1] != 0 || pos < 1 || pos > 9) {
            MovimientoValido = false;
        }
        return MovimientoValido;

    }

    public void Iniciar() {
        for (int i = 0; i < tablero.length; i++) {
            tablero[i] = 0;
        }
    }

    public boolean QuedanMovimientos() {
        boolean QuedanMovimientos = false;
        int pos = 0;
        while (QuedanMovimientos == false && pos < 9) {
            if (tablero[pos] == 0) {
                QuedanMovimientos = true;
            }
            pos++;
        }
        return QuedanMovimientos;
    }

    public boolean GanaJugador1() {
        boolean GanaJugador1 = false;
        if (tablero[0] == 1 && tablero[1] == 1 && tablero[2] == 1
                || tablero[0] == 1 && tablero[3] == 1 && tablero[6] == 1
                || tablero[0] == 1 && tablero[4] == 1 && tablero[8] == 1

                || tablero[1] == 1 && tablero[4] == 1 && tablero[7] == 1
                || tablero[3] == 1 && tablero[4] == 1 && tablero[5] == 1

                || tablero[2] == 1 && tablero[5] == 1 && tablero[8] == 1
                || tablero[6] == 1 && tablero[7] == 1 && tablero[8] == 1
                || tablero[6] == 1 && tablero[4] == 1 && tablero[2] == 1) {
            GanaJugador1 = true;
        } else {
            GanaJugador1 = false;
        }

        return GanaJugador1;
    }

    public boolean GanaJugador2() {
        boolean GanaJugador2 = false;
        if (tablero[0] == 2 && tablero[1] == 2 && tablero[2] == 2
                || tablero[0] == 2 && tablero[3] == 2 && tablero[6] == 2
                || tablero[0] == 2 && tablero[4] == 2 && tablero[8] == 2

                || tablero[1] == 2 && tablero[4] == 2 && tablero[7] == 2
                || tablero[3] == 2 && tablero[4] == 2 && tablero[5] == 2

                || tablero[2] == 2 && tablero[5] == 2 && tablero[8] == 2
                || tablero[6] == 2 && tablero[7] == 2 && tablero[8] == 2
                || tablero[6] == 2 && tablero[4] == 2 && tablero[2] == 2) {
            GanaJugador2 = true;
        } else {
            GanaJugador2 = false;
        }

        return GanaJugador2;
    }
    //No se usa
    public int MueveOrdenador1() {
        int numAle = (int) Math.round(Math.ceil(9 * Math.random()));
        if (MovimientoValido(numAle) == false) {
            while ((MovimientoValido(numAle)) == false) {
                numAle = (int) Math.round(Math.ceil(9 * Math.random()));
            }
            if ((MovimientoValido(numAle) == true) && (QuedanMovimientos() == true)) {
                MueveJugador1(numAle);
                return numAle;
            }
        } else if (MovimientoValido(numAle) == true) {
            MueveJugador1(numAle);
            return numAle;
        }
        return numAle;
    }

    public int MueveOrdenador2() {
        int numAle = (int) Math.round(Math.ceil(9 * Math.random()));
        if (MovimientoValido(numAle) == false) {
            while ((MovimientoValido(numAle)) == false) {
                numAle = (int) Math.round(Math.ceil(9 * Math.random()));
            }
            if ((MovimientoValido(numAle) == true) && (QuedanMovimientos() == true)) {
                MueveJugador2(numAle);
                return numAle;
            }
        } else if (MovimientoValido(numAle) == true) {
            MueveJugador2(numAle);
            return numAle;
        }
        return numAle;
    }

    public void MueveJugador1(int pos) {
        if (MovimientoValido(pos) == true) {
            tablero[pos - 1] = 1;
        }

    }

    public void MueveJugador2(int pos) {
        if (MovimientoValido(pos) == true) {
            tablero[pos - 1] = 2;
        }
    }


}
