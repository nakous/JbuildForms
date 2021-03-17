Vue.config.devtools = true;
$(document).ready(function(){
	var app = new Vue({
	    el: '#main',
	    data: {
	    		procd:[],
	            roles: "helo",
	            url:'http://localhost:8080/api/',
	            DossStsType:[]
	        
	    },
	    created: function(){
	    	this.getProssDossStsType()
	    	this.getDossStsType()
	    },
	    methods: {
	    	updatepdst(id){},
	    	addpdst(){
	    		
	    	},
	    	deletepdst(){
	    		
	    	},
	    	getProssDossStsType(){
	    		that = this,
		    	axios.get( this.url +  "proc-doss-sts-type")
		        .then(function (res) {
		           
		        	that.procd = res.data;
		        	console.log(that.procd);
		        })
		        .catch(function (error) {
		            // handle error
		        }) .then(function () {
		        	//console.log(this.procd);
		        }); ;
	    	},
	    	getDossStsType(){
	    		that = this,
		    	axios.get( this.url +  "doss-sts-type")
		        .then(function (res) {
		           
		        	that.DossStsType = res.data; 
		        })
		        .catch(function (error) {
		            // handle error
		        }) .then(function () {
		        	//console.log(this.procd);
		        }); ;
	    	}
	    }
	})
	Sortable.create(jbf_proc_doss_sts_type, {
		  animation: 100,
		  group: 'list-1',
		  draggable: '.list-group-item',
		  handle: '.list-group-item',
		  sort: true,
		  filter: '.sortable-disabled',
		  chosenClass: 'active'
		});
})