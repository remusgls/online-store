package types;

import org.omg.CORBA.PUBLIC_MEMBER;

public class Errors {
    public static final String ERROR_DB_DRIVER_CLASS = "Error on location mysql driver. Check it out!";
    public static final String ERROR_DB_CLOSE = "Error on closing db connection. Check it out!";
    public static final String ERROR_DB_OPEN_CONNECTION = "Error on opening db connection. Check it out!";

    public static final String ERROR_DB_INSERT_ERROR_WRONG_CHARS = "Error on insert sql: missing params. Check it out!";
    public static final String ERROR_DB_INVALID_MARCA_ID = "Invalid marca_id selection. Check it out!";

    public static final String ERROR_DB_INVALID_MODEL_ID = "Invalid model_id selection. Check it out!";
    public static final String ERROR_DB_INVALID_CATEGORIE_PIESA_ID = "Invalid categorie_id selection. Check it out!";

    public static final String ERROR_DB_INVALID_MARCA = "Invalid marca selection. Check it out!";
    public static final String ERROR_DB_INVALID_MARCA_NOUA = "Invalid marca selection. Check it out!";

    public static final String ERROR_DB_INVALID_USER_DETAILS = "Wrong registration data. Check it out!";
    public static final String ERROR_DB_INVALID_EMAIL = "Invalid Email address. Check it out!";

    public static final String ERROR_DB_DUPLICATE_EMAIL = "Duplicate email adress. User already exists";
    public static final String ERROR_DB_PIESA_NOT_FOUND = "Invalid piesa. Check it out!";

    public static final String ERROR_DB_INVALID_MODEL = "Invalid model_auto. Check it out!";



}
