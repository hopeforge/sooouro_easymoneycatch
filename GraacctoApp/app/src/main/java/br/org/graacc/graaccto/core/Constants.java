package br.org.graacc.graaccto.core;

public abstract class Constants {

    public static final String TAG = "graaccto-app";
    public static final String PREF_NAME = "pf_graaccto";
    public static final String PREF_KEY_CAMERA = "pf_camera";
    public static final String PREF_KEY_USER = "pf_user";
    public static final int REQUEST_CODE_CAMERA = 1;

    public static final String API_URL = "http://172.20.160.109:8080/graaccto/";
    public static final String API_URL_EXTERNA = "http://54.67.21.164:8081/graaccto/";

    public static final String API_SALVAR = "usuario/salvar";
    public static final String API_LOGAR = "usuario/logarApp";
    public static final String API_GRATO = "grato/listarUsuario";
    public static final String API_DOACAO = "doacao/listarUsuario";
    public static final String API_GRATO_SALVAR = "grato/salvar";
    public static final String API_DOACAO_SALVAR = "doacao/salvar";

}
