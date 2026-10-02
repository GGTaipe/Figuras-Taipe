import com.example.restservice.figuras.Figura

class Pentagono implements Figura {
    double lado

    double area() { Math.sqrt(5 * (5 + 2 * Math.sqrt(5))) / 4 * lado * lado }

    double perimetro() { 5 * lado }
}
