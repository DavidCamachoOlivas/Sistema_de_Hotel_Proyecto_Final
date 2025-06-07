package models;

public class Tariff {
	int id_tariff;
	int id_room;
	float price_per_night;
	int capacity;
	String tariff_type;
	boolean refundable;
	String description;
	
	public Tariff(int id_tariff, int id_room, float price_per_night, int capacity, String tariff_type, boolean refundable, String description) {
		this.id_tariff =  id_tariff;
		this.id_room = id_room;
		this.price_per_night = price_per_night;
		this.capacity = capacity;
		this.tariff_type = tariff_type;
		this.refundable = refundable;
		this.description = description;
	}

	public Tariff() {
	}

	public int getId_tariff() {
		return id_tariff;
	}

	public void setId_tariff(int id_tariff) {
		this.id_tariff = id_tariff;
	}

	public int getId_room() {
		return id_room;
	}

	public void setId_room(int id_room) {
		this.id_room = id_room;
	}

	public float getPrice_per_night() {
		return price_per_night;
	}

	public void setPrice_per_night(float price_per_night) {
		this.price_per_night = price_per_night;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	public String getTariff_type() {
		return tariff_type;
	}

	public void setTariff_type(String tariff_type) {
		this.tariff_type = tariff_type;
	}

	public boolean isRefundable() {
		return refundable;
	}

	public void setRefundable(boolean refundable) {
		this.refundable = refundable;
	}
	
	    
    public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
    public String toString() {
        return tariff_type + " - $" + price_per_night;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Tariff other = (Tariff) obj;
        return id_tariff == other.id_tariff;
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(id_tariff);
    }

}
