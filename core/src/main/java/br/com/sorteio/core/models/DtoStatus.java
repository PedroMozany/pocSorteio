package br.com.sorteio.core.models;

public class DtoStatus {

    private String message;
    private int status;

    public DtoStatus(int status, String message) {
        this.message = message;
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public int getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return "Status: " + status + "\n" +
                "Message: " + message;
    }
}
