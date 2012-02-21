var ver = "1";
var dbName = "SEMSURF12345test";
var pKey = dbName+"-"+ver;
var url = document.URL;
var key = pKey+"-"+url;
//var page = { url: document.URL, metaMap: getMap(['meta','title'], "") };
var page = { url: document.URL, attributes: getMap(['meta','title'], "") };
chrome.extension.sendRequest({do: "c_save", key: key, value: page});
function getMap(tags, m){
	var map = [];    
	for(var i in tags){	var cTag = tags[i];	var md = document.getElementsByTagName(cTag);
		for(var ii in md){	var currMD = md[ii]; if(null == currMD) continue;
			//if(undefined != currMD.content && "" != currMD.content) map[currMD.name.toLowerCase()] = currMD.content;
			//else if(undefined != currMD.innerHTML && "" != currMD.innerHTML) map[currMD.nodeName.toLowerCase()] = currMD.innerHTML;
		if(undefined != currMD.content && "" != currMD.content) map[ii] = {key:currMD.name.toLowerCase(), value:currMD.content};
		else if(undefined != currMD.innerHTML && "" != currMD.innerHTML) map[ii] = {key:currMD.nodeName.toLowerCase(), value:currMD.innerHTML};
		}
    } return map; 
};

