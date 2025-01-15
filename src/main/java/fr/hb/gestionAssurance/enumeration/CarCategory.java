package fr.hb.gestionAssurance.enumeration;

public enum CarCategory {
    SUV("SUV"),
    BERLINE("Berline"),
    COUPE("Coupé"),
    CABRIOLET("Cabriolet"),
    MONOSPACE("Monospace"),
    PICKUP("Pickup"),
    SPORT("Sport"),
    COMPACTE("Compacte"),
    MINIVAN("Minivan"),
    HATCHBACK("Hatchback"),
    WAGON("Wagon");

    private final String label;

    CarCategory(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}