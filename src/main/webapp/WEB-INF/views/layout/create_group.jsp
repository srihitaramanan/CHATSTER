<div id="groupModal" class="modal fade" role="dialog">
			<div class="modal-dialog">

				<!-- Modal content-->
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">&times;</button>
						<h4 class="modal-title">Create Group</h4>
					</div>
					<div class="modal-body">
						<form class="form-horizontal" name="group">

											<div class="form-group">
												<label class="col-lg-4 control-label">Group Name</label>
												<div class="col-lg-4">
													<input type="text" placeholder="Group Name"
														class="form-control" name="groupName" id="groupName"/>
												</div>
											</div>
											
											<div class="clearfix">&nbsp;</div>
											
											<div class="form-group">
												<label class="col-lg-4 control-label">&nbsp;</label>
												<div class="col-lg-8">
													<a href="students" class="btn btn-primary"><strong>Cancel</strong></a>
													&nbsp; &nbsp; <input type="submit" class="btn btn-primary"
														value="Save" id="createGroup" >
												</div>
											</div>
										</form>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
					</div>
				</div>
			</div>
		</div>
	<script type="text/javascript" src="js/jquery-1.10.2.js"></script>
	<script type="text/javascript">
	
	$(document).ready(function(){
		
		$("#createGroup").click(function(){
			
			var name = $("#groupName").val();
			if(name == null || name == ""){
				alert("Please Enter Group Name");
				$("#groupName").focus();
				return false;
			}else{
				$.ajax({
					method: "POST",
					async: false,
					url: 'saveGroup',
					data:{
						groupName : $("#groupName").val()
					},
					success: function(data){
						alert("Group created");
					}
				});
			}
			return false;
		});
	});
	</script>