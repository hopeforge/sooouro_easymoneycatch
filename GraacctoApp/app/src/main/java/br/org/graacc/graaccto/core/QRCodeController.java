package br.org.graacc.graaccto.core;

public class QRCodeController {

    private static QRCodeController instance = null;
    private String value;

    public static QRCodeController getInstance() {
        if (instance == null) {
            instance = new QRCodeController();
        }
        return instance;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
