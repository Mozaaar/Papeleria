package moviles2023.papeleria.data;

import android.content.ContentValues;
import android.database.Cursor;
import moviles2023.papeleria.data.UsuarioContract.UsuarioEntry;

public class Usuario {
    private int id;
    private int password;
    private String nombre;

    public Usuario(int id, int password, String nombre) {
        this.id = id;
        this.password = password;
        this.nombre = nombre;
    }

    public Usuario(Cursor cur) {
        id = cur.getInt( cur.getColumnIndex( UsuarioEntry.ID ) );
        password= cur.getInt(cur.getColumnIndex(UsuarioEntry.PASSWORD));
        nombre= cur.getString(cur.getColumnIndex(UsuarioEntry.NAME));
    }
    public ContentValues toContentValues(){
        ContentValues values = new ContentValues();
        values.put( UsuarioEntry.ID,id );
        values.put( UsuarioEntry.PASSWORD,password );
        values.put( UsuarioEntry.NAME,nombre );
        return values;
    }

    public int getId() {

        return id;
    }
    public int getPassword() {

        return password;
    }
    public String getNumUsuario() {

        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }


}