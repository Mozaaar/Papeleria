package moviles2023.papeleria.data;

import android.content.ContentValues;
import android.database.Cursor;
import moviles2023.papeleria.data.ProductosContract.ProductoEntry;

public class Producto {
        private int codigo;
        private String nombreProducto;
        private int stock;
        private int salidas;
        private int valor;

    public Producto(int codigo, String nombreProducto, int stock, int salidas, int valor) {
        this.codigo = codigo;
        this.nombreProducto = nombreProducto;
        this.stock = stock;
        this.salidas = salidas;
        this.valor = valor;
    }

    public Producto(Cursor cursor){
        codigo = cursor.getInt(cursor.getColumnIndex(ProductoEntry.CODIGO));
        nombreProducto = cursor.getString(cursor.getColumnIndex(ProductoEntry.NAMEP));
        stock = cursor.getInt(cursor.getColumnIndex(ProductoEntry.STOCK));
        salidas = cursor.getInt(cursor.getColumnIndex(ProductoEntry.SALIDA));
        valor = cursor.getInt(cursor.getColumnIndex(ProductoEntry.VALOR));
    }

    public ContentValues toContentValues(){
        ContentValues values = new ContentValues();
        values.put( ProductoEntry.CODIGO,codigo);
        values.put( ProductoEntry.NAMEP,nombreProducto);
        values.put( ProductoEntry.STOCK,stock);
        values.put( ProductoEntry.SALIDA, salidas);
        values.put( ProductoEntry.VALOR, valor);
        return values;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public int getStock() {
        return stock;
    }

    public int getSalidas() {
        return salidas;
    }

    public int getValor() {
        return valor;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public void setSalidas(int salidas) {
        this.salidas = salidas;
    }
}
