<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="../webjars/jquery/3.5.1/jquery.min.js"></script>
<link href="../webjars/bootstrap/4.6.0/css/bootstrap.min.css" rel="stylesheet">
<script src="../webjars/bootstrap/4.6.0/js/bootstrap.min.js"></script>


<script src="../webjars/vue/2.6.11/vue.min.js"></script>
<script src="../webjars/axios/0.19.0/dist/axios.min.js"></script>


</head>
<body>
<div id="main">
	<div class="container layoutSize box-wrapper">
		<div class="box">
			<h2>Process Dossier State Type </h2>
				<div class="row">
			              <div class="col-md-3"> from Dossier Status Type</div>
			              <div class="col-md-3">Action</div>
			              <div class="col-md-3">To Dossier Status Type</div>
			              <div class="col-md-3"></div>
			       </div>
				 <ul class="list-group" id="jbf_proc_doss_sts_type">
				        <li v-for="response in procd"   class="list-group-item">
					         <div class="row">
					                <div class="col-md-3">{{response.fromDossierStatusType.name}}</div>
					                <div class="col-md-3">{{response.name}}</div>
					                <div class="col-md-3">{{response.toDossierStatusType.name}}</div>
					                <div class="col-md-3"><button @click="updatepdst(response.id)">Update</button> <button @click="deletepdst(response.id)">Delete</button></div>
					         </div>
				        </li> 
			      </ul>
			      <div class="row">
			       <div class="col-md-12">  
			       <form>
					  <div class="form-row">
					    <div class="col">
					      <select   class="form-control" v-model="selected" >
					      		<option> select Dossier Status</option>
					      </select>
					    </div>
					    <div class="col">
					      <select   class="form-control" v-model="selected"  >
					      		<option> select Action</option>
					      </select>
					    </div>
					    <div class="col">
					      <select   class="form-control" v-model="selected" >
					      		<option> select Dossier Status</option>
					      </select>
					    </div>
					  </div>
					</form>
			        </div>
			            <div class="col-md-12"> <button @click="addpdst()">Add</button> </div>
			       </div>
		</div>
		
		<div class="box">
			<h2>Process Dossier State Type </h2>
				<div class="row">
			              <div class="col-md-3"> from Dossier Status Type</div>
			              <div class="col-md-3">Action</div>
			              <div class="col-md-3">To Dossier Status Type</div>
			              <div class="col-md-3"></div>
			       </div>
				  
		</div>
	</div> 
</div>
<script src="https://cdn.jsdelivr.net/gh/RubaXa/Sortable/Sortable.min.js"></script>
<script src="/js/admin.js"></script>

</body>
</html>