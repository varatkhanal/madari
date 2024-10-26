package com.madari.model.request;


public class PostRequestModel {
	  
	    private Long id;    
	    private String title;    
	    private String description;    
	    private String userId;
	      
	    public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}


		public String getTitle() {
			return title;
		}

		public void setTitle(String title) {
			this.title = title;
		}

		public String getUserId() {
			return userId;
		}

		public void setUserId(String userId) {
			this.userId = userId;
		}

		public String getDescription() {
			return description;
		}

		public void setDescription(String description) {
			this.description = description;
		}

}
