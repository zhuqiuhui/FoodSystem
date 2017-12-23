

public class PageConstruct {
	// {"accessPath":"","checked":false,"delFlag":0,"parentID":1,"resourceCode":""
	// ,"resourceDesc":"","resourceGrade":2,
	// "resourceID":3,"resourceName":"基础数据","resourceOrder":0,"resourceType":""
	private String accessPath;
	private boolean checked;
	private int delFlag;
	private int parentID;
	private String resourceCode;
	private String resourceDesc;
	private int resourceGrade;
	private int resourceID;
	private String resourceName;
	private int resourceOrder;
	private String resourceType;

	public String getAccessPath() {
		return accessPath;
	}

	public void setAccessPath(String accessPath) {
		this.accessPath = accessPath;
	}

	public boolean isChecked() {
		return checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}

	public int getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(int delFlag) {
		this.delFlag = delFlag;
	}

	public int getParentID() {
		return parentID;
	}

	public void setParentID(int parentID) {
		this.parentID = parentID;
	}

	public String getResourceCode() {
		return resourceCode;
	}

	public void setResourceCode(String resourceCode) {
		this.resourceCode = resourceCode;
	}

	public String getResourceDesc() {
		return resourceDesc;
	}

	public void setResourceDesc(String resourceDesc) {
		this.resourceDesc = resourceDesc;
	}

	public int getResourceGrade() {
		return resourceGrade;
	}

	public void setResourceGrade(int resourceGrade) {
		this.resourceGrade = resourceGrade;
	}

	public int getResourceID() {
		return resourceID;
	}

	public void setResourceID(int resourceID) {
		this.resourceID = resourceID;
	}

	public String getResourceName() {
		return resourceName;
	}

	public void setResourceName(String resourceName) {
		this.resourceName = resourceName;
	}

	public int getResourceOrder() {
		return resourceOrder;
	}

	public void setResourceOrder(int resourceOrder) {
		this.resourceOrder = resourceOrder;
	}

	public String getResourceType() {
		return resourceType;
	}

	public void setResourceType(String resourceType) {
		this.resourceType = resourceType;
	}

	public PageConstruct(String accessPath, boolean checked, int delFlag,
			int parentID, String resourceCode, String resourceDesc,
			int resourceGrade, int resourceID, String resourceName,
			int resourceOrder, String resourceType) {
		super();
		this.accessPath = accessPath;
		this.checked = checked;
		this.delFlag = delFlag;
		this.parentID = parentID;
		this.resourceCode = resourceCode;
		this.resourceDesc = resourceDesc;
		this.resourceGrade = resourceGrade;
		this.resourceID = resourceID;
		this.resourceName = resourceName;
		this.resourceOrder = resourceOrder;
		this.resourceType = resourceType;
	}

	public PageConstruct() {
		super();
		// TODO Auto-generated constructor stub
	}

}
