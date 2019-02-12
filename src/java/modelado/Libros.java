
package modelado;


public class Libros {
    private int idLibro,no_pag;
    private String nombre,editorial;

    public Libros() {
    }

    public Libros(int no_pag, String nombre, String editorial) {
        this.no_pag = no_pag;
        this.nombre = nombre;
        this.editorial = editorial;
    }

    public Libros(int idLibro, int no_pag, String nombre, String editorial) {
        this.idLibro = idLibro;
        this.no_pag = no_pag;
        this.nombre = nombre;
        this.editorial = editorial;
    }

    public int getIdLibro() {
        return idLibro;
    }

    public void setIdLibro(int idLibro) {
        this.idLibro = idLibro;
    }

    public int getNo_pag() {
        return no_pag;
    }

    public void setNo_pag(int no_pag) {
        this.no_pag = no_pag;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEditorial() {
        return editorial;
    }

    public void setEditorial(String editorial) {
        this.editorial = editorial;
    }
    

}

