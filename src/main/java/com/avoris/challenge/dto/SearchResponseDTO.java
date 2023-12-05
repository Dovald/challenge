package com.avoris.challenge.dto;

public class SearchResponseDTO{		
	
	private Integer searchId;
	
	public Integer getSearchId() {
		return searchId;
	}

	public void setSearchId(Integer searchId) {
		this.searchId = searchId;
	}

	@Override
    public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("class SearchResponseDTO {\n");
    
      sb.append("    searchId: ").append(toIndentedString(searchId)).append("\n");
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