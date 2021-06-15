package net.pelozo.parcial.leonardo.velozo.exception;

public class AlreadyInList extends RuntimeException{

    public AlreadyInList(String value) {
        super(String.format("%s  already exists in resource", value));
    }

}
