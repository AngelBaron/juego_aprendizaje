/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Graficos;

import entes.criatura.CARRO2;
import entes.criatura.CARRO3;
import entes.criatura.CARRO4;
import entes.criatura.CARRO5;
import entes.criatura.CARRO6;
import entes.criatura.CARRO7;
import entes.criatura.CARRO8;
import entes.criatura.Jugador;
import entes.criatura.Persona;
import mapa.cuadro.Cuadro;

/**
 *
 * @author lucho
 */
public class pantalla {

    private final int ancho;
    private final int alto;

    private int diferenciaX;
    private int diferenciaY;

    public final int[] pixeles;

    public pantalla(final int ancho, final int alto) {
        this.ancho = ancho;
        this.alto = alto;

        pixeles = new int[ancho * alto];
    }

    public void limpiar() {
        for (int i = 0; i < pixeles.length; i++) {
            pixeles[i] = 0;
        }
    }

    public void mostrarCuadro(int compensacionX, int compensacionY, Cuadro cuadro) {
        compensacionX -= diferenciaX;
        compensacionY -= diferenciaY;
        for (int y = 0; y < cuadro.sprite.obtenLado(); y++) {
            int posicionY = y + compensacionY;
            for (int x = 0; x < cuadro.sprite.obtenLado(); x++) {
                int posicionX = x + compensacionX;
                if (posicionX < -cuadro.sprite.obtenLado() || posicionX >= ancho || posicionY < 0 || posicionY >= alto) {
                    break;
                }
                if (posicionX < 0) {
                    posicionX = 0;
                }
                pixeles[posicionX + posicionY * ancho] = cuadro.sprite.pixeles[x + y * cuadro.sprite.obtenLado()];
            }
        }
    }

    public void mostrarJugador(int compensacionX, int compensacionY, Jugador jugador) {
        compensacionX -= diferenciaX;
        compensacionY -= diferenciaY;
        for (int y = 0; y < jugador.obtenSprite().obtenLado(); y++) {
            int posicionY = y + compensacionY;
            for (int x = 0; x < jugador.obtenSprite().obtenLado(); x++) {
                int posicionX = x + compensacionX;
                if (posicionX < -jugador.obtenSprite().obtenLado() || posicionX >= ancho || posicionY < 0 || posicionY >= alto) {
                    break;
                }
                if (posicionX < 0) {
                    posicionX = 0;
                }
                //pixeles[posicionX + posicionY * ancho] = jugador.obtenSprite().pixeles[x+y * jugador.obtenSprite().obtenLado()];
                int colorPixelJugador = jugador.obtenSprite().pixeles[x + y * jugador.obtenSprite().obtenLado()];
                if (colorPixelJugador != 0xffbc0000) {
                    pixeles[posicionX + posicionY * ancho] = colorPixelJugador;
                }
            }
        }
    }

    public void mostrarJugador(int compensacionX, int compensacionY, CARRO2 jugador) {
        compensacionX -= diferenciaX;
        compensacionY -= diferenciaY;
        for (int y = 0; y < jugador.obtenSprite().obtenLado(); y++) {
            int posicionY = y + compensacionY;
            for (int x = 0; x < jugador.obtenSprite().obtenLado(); x++) {
                int posicionX = x + compensacionX;
                if (posicionX < -jugador.obtenSprite().obtenLado() || posicionX >= ancho || posicionY < 0 || posicionY >= alto) {
                    break;
                }
                if (posicionX < 0) {
                    posicionX = 0;
                }
                //pixeles[posicionX + posicionY * ancho] = jugador.obtenSprite().pixeles[x+y * jugador.obtenSprite().obtenLado()];
                int colorPixelJugador = jugador.obtenSprite().pixeles[x + y * jugador.obtenSprite().obtenLado()];
                if (colorPixelJugador != 0xffbc0000) {
                    pixeles[posicionX + posicionY * ancho] = colorPixelJugador;
                }
            }
        }
    }

    public void mostrarJugador(int compensacionX, int compensacionY, CARRO3 jugador) {
        compensacionX -= diferenciaX;
        compensacionY -= diferenciaY;
        for (int y = 0; y < jugador.obtenSprite().obtenLado(); y++) {
            int posicionY = y + compensacionY;
            for (int x = 0; x < jugador.obtenSprite().obtenLado(); x++) {
                int posicionX = x + compensacionX;
                if (posicionX < -jugador.obtenSprite().obtenLado() || posicionX >= ancho || posicionY < 0 || posicionY >= alto) {
                    break;
                }
                if (posicionX < 0) {
                    posicionX = 0;
                }
                //pixeles[posicionX + posicionY * ancho] = jugador.obtenSprite().pixeles[x+y * jugador.obtenSprite().obtenLado()];
                int colorPixelJugador = jugador.obtenSprite().pixeles[x + y * jugador.obtenSprite().obtenLado()];
                if (colorPixelJugador != 0xffbc0000) {
                    pixeles[posicionX + posicionY * ancho] = colorPixelJugador;
                }
            }
        }
    }

    public void mostrarJugador(int compensacionX, int compensacionY, CARRO4 jugador) {
        compensacionX -= diferenciaX;
        compensacionY -= diferenciaY;
        for (int y = 0; y < jugador.obtenSprite().obtenLado(); y++) {
            int posicionY = y + compensacionY;
            for (int x = 0; x < jugador.obtenSprite().obtenLado(); x++) {
                int posicionX = x + compensacionX;
                if (posicionX < -jugador.obtenSprite().obtenLado() || posicionX >= ancho || posicionY < 0 || posicionY >= alto) {
                    break;
                }
                if (posicionX < 0) {
                    posicionX = 0;
                }
                //pixeles[posicionX + posicionY * ancho] = jugador.obtenSprite().pixeles[x+y * jugador.obtenSprite().obtenLado()];
                int colorPixelJugador = jugador.obtenSprite().pixeles[x + y * jugador.obtenSprite().obtenLado()];
                if (colorPixelJugador != 0xffbc0000) {
                    pixeles[posicionX + posicionY * ancho] = colorPixelJugador;
                }
            }
        }
    }

    public void mostrarJugador(int compensacionX, int compensacionY, CARRO5 jugador) {
        compensacionX -= diferenciaX;
        compensacionY -= diferenciaY;
        for (int y = 0; y < jugador.obtenSprite().obtenLado(); y++) {
            int posicionY = y + compensacionY;
            for (int x = 0; x < jugador.obtenSprite().obtenLado(); x++) {
                int posicionX = x + compensacionX;
                if (posicionX < -jugador.obtenSprite().obtenLado() || posicionX >= ancho || posicionY < 0 || posicionY >= alto) {
                    break;
                }
                if (posicionX < 0) {
                    posicionX = 0;
                }
                //pixeles[posicionX + posicionY * ancho] = jugador.obtenSprite().pixeles[x+y * jugador.obtenSprite().obtenLado()];
                int colorPixelJugador = jugador.obtenSprite().pixeles[x + y * jugador.obtenSprite().obtenLado()];
                if (colorPixelJugador != 0xffbc0000) {
                    pixeles[posicionX + posicionY * ancho] = colorPixelJugador;
                }
            }
        }
    }

    public void mostrarJugador(int compensacionX, int compensacionY, CARRO6 jugador) {
        compensacionX -= diferenciaX;
        compensacionY -= diferenciaY;
        for (int y = 0; y < jugador.obtenSprite().obtenLado(); y++) {
            int posicionY = y + compensacionY;
            for (int x = 0; x < jugador.obtenSprite().obtenLado(); x++) {
                int posicionX = x + compensacionX;
                if (posicionX < -jugador.obtenSprite().obtenLado() || posicionX >= ancho || posicionY < 0 || posicionY >= alto) {
                    break;
                }
                if (posicionX < 0) {
                    posicionX = 0;
                }
                //pixeles[posicionX + posicionY * ancho] = jugador.obtenSprite().pixeles[x+y * jugador.obtenSprite().obtenLado()];
                int colorPixelJugador = jugador.obtenSprite().pixeles[x + y * jugador.obtenSprite().obtenLado()];
                if (colorPixelJugador != 0xffbc0000) {
                    pixeles[posicionX + posicionY * ancho] = colorPixelJugador;
                }
            }
        }
    }

    public void mostrarJugador(int compensacionX, int compensacionY, CARRO7 jugador) {
        compensacionX -= diferenciaX;
        compensacionY -= diferenciaY;
        for (int y = 0; y < jugador.obtenSprite().obtenLado(); y++) {
            int posicionY = y + compensacionY;
            for (int x = 0; x < jugador.obtenSprite().obtenLado(); x++) {
                int posicionX = x + compensacionX;
                if (posicionX < -jugador.obtenSprite().obtenLado() || posicionX >= ancho || posicionY < 0 || posicionY >= alto) {
                    break;
                }
                if (posicionX < 0) {
                    posicionX = 0;
                }
                //pixeles[posicionX + posicionY * ancho] = jugador.obtenSprite().pixeles[x+y * jugador.obtenSprite().obtenLado()];
                int colorPixelJugador = jugador.obtenSprite().pixeles[x + y * jugador.obtenSprite().obtenLado()];
                if (colorPixelJugador != 0xffbc0000) {
                    pixeles[posicionX + posicionY * ancho] = colorPixelJugador;
                }
            }
        }
    }

    public void mostrarJugador(int compensacionX, int compensacionY, CARRO8 jugador) {
        compensacionX -= diferenciaX;
        compensacionY -= diferenciaY;
        for (int y = 0; y < jugador.obtenSprite().obtenLado(); y++) {
            int posicionY = y + compensacionY;
            for (int x = 0; x < jugador.obtenSprite().obtenLado(); x++) {
                int posicionX = x + compensacionX;
                if (posicionX < -jugador.obtenSprite().obtenLado() || posicionX >= ancho || posicionY < 0 || posicionY >= alto) {
                    break;
                }
                if (posicionX < 0) {
                    posicionX = 0;
                }
                //pixeles[posicionX + posicionY * ancho] = jugador.obtenSprite().pixeles[x+y * jugador.obtenSprite().obtenLado()];
                int colorPixelJugador = jugador.obtenSprite().pixeles[x + y * jugador.obtenSprite().obtenLado()];
                if (colorPixelJugador != 0xffbc0000) {
                    pixeles[posicionX + posicionY * ancho] = colorPixelJugador;
                }
            }
        }
    }

    public void mostrarJugador(int compensacionX, int compensacionY, Persona persona) {
        compensacionX -= diferenciaX;
        compensacionY -= diferenciaY;

        int nuevoTamaño = 16;

        for (int y = 0; y < persona.obtenSprite().obtenLado(); y++) {
            int posicionY = y * nuevoTamaño / persona.obtenSprite().obtenLado() + compensacionY;

            for (int x = 0; x < persona.obtenSprite().obtenLado(); x++) {
                int posicionX = x * nuevoTamaño / persona.obtenSprite().obtenLado() + compensacionX;

                if (posicionX < -nuevoTamaño || posicionX >= ancho || posicionY < 0 || posicionY >= alto) {
                    break;
                }
                if (posicionX < 0) {
                    posicionX = 0;
                }

                int colorPixelPersona = persona.obtenSprite().pixeles[x + y * persona.obtenSprite().obtenLado()];

                if (colorPixelPersona != 0xff00ff) {
                    for (int dy = 0; dy < nuevoTamaño / persona.obtenSprite().obtenLado(); dy++) {
                        for (int dx = 0; dx < nuevoTamaño / persona.obtenSprite().obtenLado(); dx++) {
                            int escalaX = posicionX + dx;
                            int escalaY = posicionY + dy;

                            if (escalaX < ancho && escalaY < alto) {
                                pixeles[escalaX + escalaY * ancho] = colorPixelPersona;
                            }
                        }
                    }
                }
            }
        }
    }

    public void mostrarSemaforo(int x, int y, Sprite spriteSemaforo) {
        x -= diferenciaX;
        y -= diferenciaY;

        for (int spriteY = 0; spriteY < spriteSemaforo.obtenLado(); spriteY++) {
            int posicionY = y + spriteY;
            for (int spriteX = 0; spriteX < spriteSemaforo.obtenLado(); spriteX++) {
                int posicionX = x + spriteX;

                // Verifica que la posición esté dentro de los límites de la pantalla
                if (posicionX < 0 || posicionX >= ancho || posicionY < 0 || posicionY >= alto) {
                    continue;
                }

                int color = spriteSemaforo.pixeles[spriteX + spriteY * spriteSemaforo.obtenLado()];

                // Condición para no dibujar píxeles transparentes
                if (color != 0x20e4c9) { // Suponiendo que 0xff00ff es el color transparente
                    pixeles[posicionX + posicionY * ancho] = color;
                }
                
            }
        }
    }

    public void estableceDiferencia(int diferenciaX, int diferenciaY) {
        this.diferenciaX = diferenciaX;
        this.diferenciaY = diferenciaY;
    }

    public int obtenAncho() {
        return ancho;

    }

    public int obtenAlto() {
        return alto;
    }

}
