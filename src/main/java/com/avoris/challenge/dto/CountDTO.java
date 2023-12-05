package com.avoris.challenge.dto;

public class CountDTO {
	
	private Integer searchId;	
	private Integer count;
	private SearchDTO search;
	
	
	public Integer getSearchId() {
		return searchId;
	}
	public void setSearchId(Integer searchId) {
		this.searchId = searchId;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	public SearchDTO getSearchDTO() {
		return search;
	}
	public void setSearchDTO(SearchDTO searchDTO) {
		this.search = searchDTO;
	}
	
	@Override
    public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("class CountDTO {\n");
    
      sb.append("    searchId: ").append(toIndentedString(searchId)).append("\n");
      sb.append("    count: ").append(toIndentedString(count)).append("\n");
      sb.append("    search: ").append(search.toString()).append("\n");
      sb.append("}");
      return sb.toString();
    }
	
	private String toIndentedString(java.lang.Object o) {
      if (o == null) {
        return "null";
      }
      return o.toString().replace("\n", "\n    ");
    }

}
