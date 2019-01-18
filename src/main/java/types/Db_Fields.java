package types;

public class Db_Fields {
    public static class DB_MODEL {
        private static final String DB_NAME = "model_auto";
        private static final String id = "id";
        private static final String model = "model";
        private static final String id_marca = "id_marca";

        public static String getId() {
            return id;
        }

        public static String getModel() {
            return model;
        }

        public static String getIdMarca() {
            return id_marca;
        }

        public static String getDbName() {
            return DB_NAME;
        }
    }

    public static class DB_MARCA {
        private static final String DB_NAME = "marca_auto";
        private static final String id = "id";
        private static final String marca = "marca";

        public static String getDbName() {
            return DB_NAME;
        }

        public static String getId() {
            return id;
        }

        public static String getMarca() {
            return marca;
        }
    }

    public static class DB_PIESA {
        private static final String DB_NAME = "piesa";
        private static final String id = "id";
        private static final String piesa = "piesa";
        private static final String stoc = "stoc";
        private static final String id_categorie = "id_categorie";
        private static final String id_model = "id_model";

        public static String getDbName() {
            return DB_NAME;
        }

        public static String getId() {
            return id;
        }

        public static String getPiesa() {
            return piesa;
        }

        public static String getStoc() {
            return stoc;
        }

        public static String getId_categorie() {
            return id_categorie;
        }

        public static String getId_model() {
            return id_model;
        }
    }
}
