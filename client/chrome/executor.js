var ver = "1";
var dbName = "SEMSURF12345test";
var pKey = dbName+"-"+ver;
var url = document.URL;
var key = pKey+"-"+url;
var page = {
    url: document.URL,
    metaMap: getMap(['meta','title'], "")
};


chrome.extension.sendRequest({do: "save", key: key, value: page});


//returns a map of elements found
function getMap(tags, m){
		var map = {};    
		for(var i in tags){
			var cTag = tags[i];
			var md = document.getElementsByTagName(cTag);
			for(var ii in md){
				var currMD = md[ii];
				if(null == currMD) continue;
				if(undefined != currMD.content && "" != currMD.content){
					//alert("Name: "+currMD.name+",\r\n Value: "+currMD.content);
					map[currMD.name] = currMD.content;
				}else if(undefined != currMD.innerHTML && "" != currMD.innerHTML){
					//alert("Name: "+currMD.nodeName+",\r\n Value: "+currMD.innerHTML);
					map[currMD.nodeName] = currMD.innerHTML;
				}
			}
    }
		return map;
};

