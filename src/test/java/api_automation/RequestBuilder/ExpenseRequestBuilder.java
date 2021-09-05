package api_automation.RequestBuilder;

public class ExpenseRequestBuilder {
	
	private String name;
	private double amount;
	private String expenseDateTime;
	private String expenseType;
	private Integer otherExpenseNameId;
	private String giftRecipient;
	

	
public String getGiftRecipient() {
		return giftRecipient;
	}
	public void setGiftRecipient(String giftRecipient) {
		this.giftRecipient = giftRecipient;
	}
public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public String getExpenseDateTime() {
		return expenseDateTime;
	}
	public void setExpenseDateTime(String expenseDateTime) {
		this.expenseDateTime = expenseDateTime;
	}
	public String getExpenseType() {
		return expenseType;
	}
	public void setExpenseType(String expenseType) {
		this.expenseType = expenseType;
	}
	public Integer getOtherExpenseNameId() {
		return otherExpenseNameId;
	}
	public void setOtherExpenseNameId(Integer otherExpenseNameId) {
		this.otherExpenseNameId = otherExpenseNameId;
	}


}
