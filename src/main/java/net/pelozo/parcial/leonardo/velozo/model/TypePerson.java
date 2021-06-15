package net.pelozo.parcial.leonardo.velozo.model;

public enum TypePerson {
    PLAYER("jugador"),
    MANAGER("Representante");

    private String description;

    TypePerson(String description) {
        this.description = description;
    }

    public static TypePerson find(String value){
        for(TypePerson person: values()){
            if(person.toString().equals(value)){
                return person;
            }
        }
        throw new IllegalArgumentException(String.format("Invalid person type: %s", value));
    }

    public String getDescription() {
        return description;
    }
}
