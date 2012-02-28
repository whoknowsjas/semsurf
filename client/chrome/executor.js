chrome.extension.sendRequest({do: 'null'},function(response){
	var ver = "1";
	var dbName = "SEMSURF12345test";
	var pKey = dbName+"-"+ver;
	var url = document.URL;
	var key = pKey+"-"+url;
	var map = [];
	getMap(response.param2, map);
	getAttrMap(response.param3, map);
	var page = { url: document.URL, attributes: map };
	chrome.extension.sendRequest({do: "c_save", key: key, value: page});
});
function getAttrMap(tags, map){
	for(var i in tags){	
		var tag = tags[i].split("|")[0];
		var attr = tags[i].split("|")[1];		
		var oTags = $(tag+'[name='+attr+']').get();			
		for(var ii in oTags){
			var currMD = oTags[ii];
		  if(null == currMD || undefined == currMD) continue;
			map[map.length++] = {key:currMD.name.toLowerCase(), value:currMD.content};
		}	
  } return map; 
};
function getMap(tags, map){
	for(var i in tags){	
		var oTags = $(tags[i]).get();		
		for(var ii in oTags){
			var currMD = oTags[ii];
		  if(null == currMD || undefined == currMD) continue;
			map[map.length++] = {key:currMD.nodeName.toLowerCase(), value:currMD.innerHTML};
		}	
  } return map; 
};
function filterMap(currMD, metaFilters){
	for(var filter in metaFilters){
		if(metaFilters[filter] == currMD.name.toLowerCase())	{ return true; }
	}return false;
};

