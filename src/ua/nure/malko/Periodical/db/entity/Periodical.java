package ua.nure.malko.Periodical.db.entity;

public class Periodical extends Entity {

	private static final long serialVersionUID = 3L;

	private String name;
	
	private Integer price;
	
	private Long categoryId;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public Long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

	@Override
	public String toString() {
		return "Periodical [name=" + name + ", price=" + price + ", categoryId="
				+ categoryId + ", getId()=" + getId() + "]";
	}

}