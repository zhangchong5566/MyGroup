// common variables
var iBytesUploaded = 0;
var iBytesTotal = 0;
var iPreviousBytesLoaded = 0;
var iMaxFilesize = 1048576; // 1MB
var oTimer = 0;
var sResultFileSize = '';

String.prototype.NoSpace = function() 
{ 
	return this.replace(/\s+/g, ""); 
};

// source
var sMaxFilesize = 52428800; // 50MB

function secondsToTime(secs) { // we will use this function to convert seconds in normal time format
    var hr = Math.floor(secs / 3600);
    var min = Math.floor((secs - (hr * 3600))/60);
    var sec = Math.floor(secs - (hr * 3600) -  (min * 60));

    if (hr < 10) {hr = "0" + hr; }
    if (min < 10) {min = "0" + min;}
    if (sec < 10) {sec = "0" + sec;}
    if (hr) {hr = "00";}
    return hr + ':' + min + ':' + sec;
};

function bytesToSize(bytes) {
    var sizes = ['Bytes', 'KB', 'MB'];
    if (bytes == 0) return 'n/a';
    var i = parseInt(Math.floor(Math.log(bytes) / Math.log(1024)));
    return (bytes / Math.pow(1024, i)).toFixed(1) + ' ' + sizes[i];
};



var fileid=1;
var fileNum;        
var oFile;
var fd;
var postUrl;
var oId;     
var oFileName;

var isSource=false;

function uploadCompute(responseText){
	var showerror=true;
	if(responseText!=null){
		var hidenval;
		var uploadDate=getDate();
		if(isSource){
			var dataObj=eval("("+responseText+")");
			if(dataObj.FileOpen=="Succeed"){
				oFileName=oFile.name.split(".")[0].NoSpace();
				sResultFileSize = bytesToSize(oFile.size);
				var timeDir=dataObj.timeDir;
				var content='<div style="width:950px;height:300px;border: 0px solid #999;position:relative;">';
				content+='<div style="width:400px;position:absolute; top:0; left:0;">';
				content+='<div id="banner-fade'+fileid+'">';
				content+='<ul class="bjqs">';
				content+='<li><img class="thumb" src="'+cxp+'/file.jsp?fileName='+oFileName+'_FrontView.png&timeDir='+timeDir+'"></li>';
				content+='<li><img class="thumb" src="'+cxp+'/file.jsp?fileName='+oFileName+'_BackView.png&timeDir='+timeDir+'"></li>';
				content+='<li><img class="thumb" src="'+cxp+'/file.jsp?fileName='+oFileName+'_TopView.png&timeDir='+timeDir+'"></li>';
				content+='<li><img class="thumb" src="'+cxp+'/file.jsp?fileName='+oFileName+'_RightView.png&timeDir='+timeDir+'"></li>';
				content+='<li><img class="thumb" src="'+cxp+'/file.jsp?fileName='+oFileName+'_LeftView.png&timeDir='+timeDir+'"></li>';
				content+='<li><img class="thumb" src="'+cxp+'/file.jsp?fileName='+oFileName+'_BottomView.png&timeDir='+timeDir+'"></li>';
				content+='<li><img class="thumb" src="'+cxp+'/file.jsp?fileName='+oFileName+'_TopFrontLeftView.png&timeDir='+timeDir+'"></li>';
				content+='</ul></div></div>';
				content+='<div style="width:540px;margin-left:405px;height:260px;"><table id="source_table" cellspacing="0" cellpadding="0"	border="1" width="100%" style="height:260px">';
				content+='<tr><td colspan="6" align="center"><b>'+oFile.name+'</b></td></tr>';
				content+='<tr><td align="center">Size</td><td>'+sResultFileSize+'</td><td align="center">uploadDate</td><td colspan="3">'+uploadDate+'</td></tr>';
				content+='<tr><td align="center">Unit</td><td>'+dataObj.Unit+'</td><td align="center">Type</td><td colspan="3">'+dataObj.Type+'</td></tr>';
				content+='<tr><td align="center">Volume</td><td>'+dataObj.Volume+'</td><td align="center">BoundingDiameter</td><td colspan="3">'+dataObj.BoundingDiameter+'</td></tr>';
				content+='<tr><td align="center">ShortestEdge</td><td>'+dataObj.ShortestEdge+'</td><td align="center">NumPoints</td><td>'+dataObj.NumPoints+'</td><td align="center">NumFacets</td><td>'+dataObj.NumFacets+'</td></tr>';
				content+='<tr><td align="center">MIN_X</td><td>'+dataObj.MIN_X+'</td><td align="center">MIN_Y</td><td>'+dataObj.MIN_Y+'</td><td align="center">MIN_Z</td><td>'+dataObj.MIN_Z+'</td></tr>';
				content+='<tr><td align="center">MAX_X</td><td>'+dataObj.MAX_X+'</td><td align="center">MAX_Y</td><td>'+dataObj.MAX_Y+'</td><td align="center">MAX_Z</td><td>'+dataObj.MAX_Z+'</td></tr>';
				content+='<tr><td align="center">Delta_X</td><td>'+dataObj.Delta_X+'</td><td align="center">Delta_Y</td><td>'+dataObj.Delta_Y+'</td><td align="center">Delta_Z</td><td>'+dataObj.Delta_Z+'</td></tr>';
				//content+='<tr><td align="center">Header</td><td colspan="5">'+dataObj.Header+'</td></tr>';
				content+='</table></div></div>';
				$("#"+oId+"_View").append(content);
				dataObj["fileId"]=fileid+"";
				dataObj["uploadType"]="server";
				dataObj["fileName"]=oFile.name;
				dataObj["fileSize"]=sResultFileSize;
				dataObj["uploadDate"]=uploadDate;
				dataObj["timeDir"]=timeDir;
				dataObj["filePath"]=timeDir+"/"+oFile.name;
				$('#banner-fade'+fileid).bjqs({
					height : 352,
					width : 540,
					responsive : true
				});
				hidenval=jQuery.toJSON(dataObj);
				var filehidden = $("<input type=\"hidden\" name=\""+oId+"_Files\" />");
				filehidden.val(hidenval);
				$("#"+oId+"_hidden").append(filehidden);
				showerror=false;
			}
		}else{
			hidenval={};
			hidenval["fileId"]=fileid+"";
			hidenval["uploadType"]="designer";
			hidenval["fileName"]=oFile.name;
			hidenval["fileSize"]=sResultFileSize;
			hidenval["uploadDate"]=uploadDate;
			hidenval["timeDir"]=responseText;
			hidenval["filePath"]=responseText+"/"+oFile.name;
			hidenval=jQuery.toJSON(hidenval);
			var filehidden = $("<input type=\"hidden\" name=\""+oId+"_Files\" />");
			filehidden.val(hidenval);
			$("#"+oId+"_hidden").append(filehidden);
			showerror=false;
		}
		fileid++;
	}
	if(showerror){
	    $("#"+oId+"_error").show();
	    $("#"+oId+"_error2").show();
	}
}

function hideWarnings(){
	 // hide different warnings
   $("#"+oId+"_upload_response").hide();
   $("#"+oId+"_error").hide();
   $("#"+oId+"_error2").hide();
   $("#"+oId+"_abort").hide();
   $("#"+oId+"_warnsize").hide();
}

function fileSelected(idPar,url){
	isSource=true;
	postUrl=url;
	oId=idPar;
	 // hide different warnings
	hideWarnings();
	
	oFile = document.getElementById(oId+'_file').files[0];
	// filter for stl files
	var fileval=$("#"+oId+"_file").val();
	var photoExt=fileval.substr(fileval.lastIndexOf(".")).toLowerCase();
	if(photoExt!='.stl'){
		$("#"+oId+"_error").show();
		return;
    }
	// little test for filesize
    if (oFile.size > sMaxFilesize) {
        $("#"+oId+"_warnsize").show();
        return;
    }
    
    // prepare HTML5 FileReader
    var oReader = new FileReader();
        oReader.onload = function(e){

        fd = new FormData();
        fd.append("fileToUpload", oFile);
        
       $("#"+oId+'_fileinfo').show();
        
        startUploading();
    };
    
    // read selected file as DataURL
    oReader.readAsDataURL(oFile);
}

function imgSelected(idPar,url) {
	postUrl=url;
	oId=idPar;
	isSource=false;
    // hide different warnings
	hideWarnings();

    // get selected file element
    oFile = document.getElementById(oId+'_file').files[0];
    
    // filter for image files
    var rFilter = /^(image\/bmp|image\/gif|image\/jpeg|image\/png|image\/tiff)$/i;
    if (! rFilter.test(oFile.type)) {
    	$("#"+oId+"_error").show();
        return;
    }

    // little test for filesize
    if (oFile.size > iMaxFilesize) {
        $("#"+oId+"_warnsize").show();
        return;
    }

    // get preview element
    var oImage = $("#"+oId+"_preview");

    // prepare HTML5 FileReader
    var oReader = new FileReader();
        oReader.onload = function(e){

        // e.target.result contains the DataURL which we will use as a source of the image
        oImage.src = e.target.result;
        //oImage.append('<img class="thumb" src='+e.target.result+' >');
        imgview(oImage,e.target.result);
        
        fd = new FormData();
        fd.append("fileToUpload", oFile);
        
        oImage.onload = function () { // binding onload event

            // we are going to display some custom image information here
//            sResultFileSize = bytesToSize(oFile.size);
//            document.getElementById('fileinfo').style.display = 'block';
//            document.getElementById('filename').innerHTML = 'Name: ' + oFile.name;
//            document.getElementById('filesize').innerHTML = 'Size: ' + sResultFileSize;
//            document.getElementById('filetype').innerHTML = 'Type: ' + oFile.type;
//            document.getElementById('filedim').innerHTML = 'Dimension: ' + oImage.naturalWidth + ' x ' + oImage.naturalHeight;
        };
        startUploading();
    };

    // read selected file as DataURL
    oReader.readAsDataURL(oFile);
}

function imgview(o,imgsrc){
	o.append('<img class="thumb" src='+imgsrc+' >');
}

function startUploading() {
    // cleanup all temp states
    iPreviousBytesLoaded = 0;
    $("#"+oId+"_upload_response").hide();
    $("#"+oId+"_error").hide();
    $("#"+oId+"_error2").hide();
    $("#"+oId+"_abort").hide();
    $("#"+oId+"_warnsize").hide();
    $("#"+oId+"_progress_percent").html("");
    
    var oProgress =  $("#"+oId+"_progress");
    oProgress.show();
    oProgress.width("0px");

    // get form data for POSTing
    //var vFD = document.getElementById('upload_form').getFormData(); // for FF3
    //var vFD = new FormData(document.getElementById('upload_form')); 
    // create XMLHttpRequest object, adding few event listeners, and POSTing our data
    var oXHR = new XMLHttpRequest();
    oXHR.upload.addEventListener('progress', uploadProgress, false);
    oXHR.addEventListener('load', uploadFinish, false);
    oXHR.addEventListener('error', uploadError, false);
    oXHR.addEventListener('abort', uploadAbort, false);
    oXHR.open('POST', postUrl,true);
    oXHR.send(fd);
    oXHR.onload = function(e) {
        if (this.status == 200) {
           //alert(this.responseText);
           //uploadCompute();
        }
    };

    // set inner timer
    oTimer = setInterval(doInnerUpdates, 300);
}

function doInnerUpdates() { // we will use this function to display upload speed
    var iCB = iBytesUploaded;
    var iDiff = iCB - iPreviousBytesLoaded;

    // if nothing new loaded - exit
    if (iDiff == 0)
        return;

    iPreviousBytesLoaded = iCB;
    iDiff = iDiff * 2;
    var iBytesRem = iBytesTotal - iPreviousBytesLoaded;
    var secondsRemaining = iBytesRem / iDiff;

    // update speed info
    var iSpeed = iDiff.toString() + 'B/s';
    if (iDiff > 1024 * 1024) {
        iSpeed = (Math.round(iDiff * 100/(1024*1024))/100).toString() + 'MB/s';
    } else if (iDiff > 1024) {
        iSpeed =  (Math.round(iDiff * 100/1024)/100).toString() + 'KB/s';
    }
    
    $("#"+oId+"_speed").html(iSpeed);
    $("#"+oId+"_remaining").html('| ' + secondsToTime(secondsRemaining));
}

function uploadProgress(e) { // upload process in progress
    if (e.lengthComputable) {
        iBytesUploaded = e.loaded;
        iBytesTotal = e.total;
        var iPercentComplete = Math.round(e.loaded * 100 / e.total);
        var iBytesTransfered = bytesToSize(iBytesUploaded);

        $("#"+oId+"_progress_percent").html(iPercentComplete.toString() + '%');
        $("#"+oId+"_progress").width((iPercentComplete * 4).toString() + "px");
        $("#"+oId+"_b_transfered").html(iBytesTransfered);
        if (iPercentComplete == 100) {
            var oUploadResponse =  $("#"+oId+"_upload_response");
            oUploadResponse.html("<h1>Please wait...processing</h1>");
            oUploadResponse.show();
        }
    } else {
    	$("#"+oId+"_progress").html("unable to compute");
    }
}

function uploadFinish(e) { // upload successfully finished
    var oUploadResponse = $("#"+oId+"_upload_response");
    //oUploadResponse.html(e.target.responseText);
    oUploadResponse.html("processing complete!");
    oUploadResponse.show();

    $("#"+oId+"_progress_percent").html("100%");
    $("#"+oId+"_progress").width("400px");
    $("#"+oId+"_filesize").html(sResultFileSize);
    $("#"+oId+"_remaining").html("| 00:00:00");

    clearInterval(oTimer);
    uploadCompute(e.target.responseText);
}

function uploadError(e) { // upload error
    $("#"+oId+"_error2").show();
    clearInterval(oTimer);
}  

function uploadAbort(e) { // upload abort
    $("#"+oId+"_abort").show();
    clearInterval(oTimer);
}