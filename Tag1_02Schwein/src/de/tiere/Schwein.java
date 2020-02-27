package de.tiere;

public class Schwein {

	private String name;
	private int gewicht;

	public Schwein() {
		this("nobody");
	}

	public Schwein(String name) {
		setName(name);
		setGewicht(10);
	}

	public String getName() {
		return name;
	}

	public final void setName(String name) {
		if (name == null || name.equalsIgnoreCase("elsa"))
			throw new IllegalArgumentException(String.format("Der Name '%s' ist nicht erlaubt!", name));
		this.name = name;
	}

	public int getGewicht() {
		return gewicht;
	}

	private void setGewicht(int gewicht) {
		this.gewicht = gewicht;

	}

	public void fressen() {
		setGewicht(getGewicht() + 1);
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Schwein [name=");
		builder.append(name);
		builder.append(", gewicht=");
		builder.append(gewicht);
		builder.append("]");
		return builder.toString();
	}

}
