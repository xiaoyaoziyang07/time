
/**
 * 给URL后 自动增加 随机数
 */
function geturl(url)
{
	url+="&random="+Math.random();
	
	return url;
}

function exportFile()
{
		
		$("#export_file").modal("show");
		
		var params = $('#searchForm').formSerialize();
		
		var url = $('#searchForm').attr('action')+"&op=-1" ;
		
		$.post(url,params,function (data){
			
			setTimeout(function(){
				
				$('#exporting').hide();
	 			$('#exportLabel').html("数据导出成功，点击下载！");
	 			$('#fileName').html(data.substring(data.indexOf("/")+1));
	 			$('#fileName').attr('href',"analysis.do?method=donwload&fileName="+data);
			}),2000});
}

String.prototype.endWith=function(str){     
	  var reg=new RegExp(str+"$");     
	  return reg.test(this);        
	}

