package moviles2023.papeleria.data;

import android.content.ContentValues;
import android.database.Cursor;
import moviles2023.papeleria.data.UsuarioContract.UsuarioEntry;

public class Usuario {
    private int id;
    private String nomUsuario;
    private int password;

    public Usuario(int id, int nomUsuario, String password) {
        this.id = id;
        this.nomUsuario = nomUsuario;
        this.password = password;
    }

    public Usuario(Cursor cur) {
        id = cur.getInt( cur.getColumnIndex( UsuarioEntry.ID ) );
        nomUsuario= cur.getString(cur.getColumnIndex(UsuarioEntry.NAME));
        password= cur.getInt(cur.getColumnIndex(UsuarioEntry.PASSWORD));
    }
    public ContentValues toContentValues(){
        ContentValues conten = new ContentValues();
        conten.put( UsuarioEntry.ID,id );
        conten.put(UsuarioEntry.NAME, nomUsuario);
        conten.put(UsuarioEntry.PASSWORD, password);

        return conten;
    }

    public int getId() {
        return id;
    }

    public String getNumUsuario() {
        return nomUsuario;
    }

    public int getPassword() {
        return password;
    }

}
