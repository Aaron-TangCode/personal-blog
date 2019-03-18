"use strict";
//# sourceURL=main.js
 
// DOM 加载完再执行
$(function() {
	
	var _pageSize; // 存储用于搜索
	
	// 根据用户名、页面索引、页面大小获取用户列表
	function getUersByName(pageIndex, pageSize) {
		 $.ajax({ 
			 url: "/blog", 
			 contentType : 'application/json',
			 data:{
				 "async":true, 
				 "pageIndex":pageIndex,
				 "pageSize":pageSize,
				 "name":$("#searchName").val()
			 },
			 success: function(data){
				 alert(data);
				 $("#mainContainer").html(data);
		     },
		     error : function() {
		    	 toastr.error("error!");
		     }
		 });
	}
	
	// 分页
	$.tbpage("#mainContainer", function (pageIndex, pageSize) {
		getUersByName(pageIndex, pageSize);
		_pageSize = pageSize;
	});
   
	// 搜索
	$("#searchNameBtn").click(function() {
		getUersByName(0, _pageSize);
	});
	
	// 获取添加用户的界面
	$("#addBlog").click(function() {
		$.ajax({ 
			 url: "/users/add", 
			 success: function(data){
				 $("#blogFormContainer").html(data);
		     },
		     error : function(data) {
		    	 toastr.error("error!");
		     }
		 });
	});
	
	// 获取编辑用户的界面
	$("#rightContainer").on("click",".blog-edit-blog", function () { 
		$.ajax({ 
			 url: "/blog/edit/" + $(this).attr("blogId"), 
			 success: function(data){
				 alert(data);
				 $("#blogFormContainer").html(data);
		     },
		     error : function() {
		    	 toastr.error("error!");
		     }
		 });
	});
	
	// 提交变更后，清空表单
	$("#submitEdit").click(function() {
		$.ajax({ 
			 url: "/blog", 
			 type: 'POST',
			 data:$('#blogForm').serialize(),
			 success: function(data){
				 $('#blogForm')[0].reset();  
				 
				 if (data.success) {
					 // 从新刷新主界面
					 getUersByName(0, _pageSize);
				 } else {
					 toastr.error(data.message);
				 }

		     },
		     error : function() {
		    	 toastr.error("error!");
		     }
		 });
	});
	
	// 删除用户
	$("#rightContainer").on("click",".blog-delete-blog", function () { 
		// 获取 CSRF Token 
		var csrfToken = $("meta[name='_csrf']").attr("content");
		var csrfHeader = $("meta[name='_csrf_header']").attr("content");
		
		
		$.ajax({ 
			 url: "/users/" + $(this).attr("blogId") , 
			 type: 'DELETE', 
			 beforeSend: function(request) {
                 request.setRequestHeader(csrfHeader, csrfToken); // 添加  CSRF Token 
             },
			 success: function(data){
				 if (data.success) {
					 // 从新刷新主界面
					 getUersByName(0, _pageSize);
				 } else {
					 toastr.error(data.message);
				 }
		     },
		     error : function() {
		    	 toastr.error("error!");
		     }
		 });
	});
});