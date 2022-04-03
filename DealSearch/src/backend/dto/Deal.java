package backend.dto;

public class Deal {
	private String dongName;
	private String aptName;
	private int dealPrice;
	private double area;
	private int dealMonth;
	private int dealDay;
	
	
	public Deal(String dongName, String aptName, int dealPrice, double area, int dealMonth, int dealDay) {
		this.dongName = dongName;
		this.aptName = aptName;
		this.dealPrice = dealPrice;
		this.area = area;
		this.dealMonth = dealMonth;
		this.dealDay = dealDay;
	}

	public String getDongName() {
		return dongName;
	}

	public void setDongName(String dongName) {
		this.dongName = dongName;
	}

	public String getAptName() {
		return aptName;
	}

	public void setAptName(String aptName) {
		this.aptName = aptName;
	}

	public int getDealPrice() {
		return dealPrice;
	}

	public void setDealPrice(int dealPrice) {
		this.dealPrice = dealPrice;
	}

	public double getArea() {
		return area;
	}

	public void setArea(double area) {
		this.area = area;
	}
	public int getDealMonth() {
		return dealMonth;
	}

	public void setDealMonth(int dealMonth) {
		this.dealMonth = dealMonth;
	}

	public int getDealDay() {
		return dealDay;
	}

	public void setDealDay(int dealDay) {
		this.dealDay = dealDay;
	}

	@Override
	public String toString() {
		return "Deal [dongName=" + dongName + ", aptName=" + aptName + ", dealPrice=" + dealPrice + ", area=" + area
				+ ", dealMonth=" + dealMonth + ", dealDay=" + dealDay + "]";
	}

	

	
	
}
