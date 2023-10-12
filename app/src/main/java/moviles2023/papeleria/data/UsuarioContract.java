package moviles2023.papeleria.data;

import android.provider.BaseColumns;

public class UsuarioContract {
    public static abstract class UsuarioEntry implements BaseColumns{
        public static final String TABLE_NAME ="usuario";
        public static final String ID = "id";

        public static final String NAME  ="nomUsuario";
        public static final String PASSWORD ="password";

    }
}
