
function Node(id, pid, name, url, title, target, icon, iconOpen, open) { this.id = id; this.pid = pid; this.name = name; this.url = url; this.title = title; this.target = target; this.icon = icon; this.iconOpen = iconOpen; this._io = open || false; this._is = false; this._ls = false; this._hc = false; this._ai = 0; this._p; };
function dTree(objName, Container, imgRoot, width, height) {
    this.imgRoot = ((imgRoot == null || imgRoot == 'undefined') ? '' : imgRoot);
    this.config = {
        target: null,
        folderLinks: false,
        useSelection: true,
        useCookies: true,
        useLines: true,
        useIcons: true,
        useStatusText: false,
        closeSameLevel: false,
        inOrder: false,
		check : false
		// 检查是否有复选框
    };
    this.icon = {
        root: this.imgRoot + 'js/dtree/img/folder.gif',
        folder: this.imgRoot + 'js/dtree/img/folder.gif',
        folderOpen: this.imgRoot + 'js/dtree/img/folderopen.gif',
        node: this.imgRoot + 'js/dtree/img/page.gif',
        empty: this.imgRoot + 'js/dtree/img/empty.gif',
        line: this.imgRoot + 'js/dtree/img/line.gif',
        join: this.imgRoot + 'js/dtree/img/join.gif',
        joinBottom: this.imgRoot + 'js/dtree/img/joinbottom.gif',
        plus: this.imgRoot + 'js/dtree/img/plus.gif',
        plusBottom: this.imgRoot + 'js/dtree/img/plusbottom.gif',
        minus: this.imgRoot + 'js/dtree/img/minus.gif',
        minusBottom: this.imgRoot + 'js/dtree/img/minusbottom.gif',
        nlPlus: this.imgRoot + 'js/dtree/img/nolines_plus.gif',
        nlMinus: this.imgRoot + 'js/dtree/img/nolines_minus.gif',
        loading: this.imgRoot + 'js/dtree/img/loading.gif'
    };
    this.obj = objName;
    this.aNodes = [];
    this.aNodesData = [];
    this.container = Container || 'dtree';
    this.aIndent = [];
    this.root = new Node(-1);
    this.selectedNode = null;
    this.selectedFound = false;
    this.completed = false;
    this.width = ('width:' + width + ';' || '');
    this.height = ('height:' + height + ';' || '');
};
dTree.prototype.add = function(id, pid, name, url, title, target, icon, iconOpen, open) {var temp_node= new Node(id, pid, name, url, title, target, icon, iconOpen, open); this.aNodesData[this.aNodesData.length] = temp_node;return temp_node  };
dTree.prototype.openAll = function() { this.oAll(true); };
dTree.prototype.closeAll = function() { this.oAll(false); };
dTree.prototype.addNode = function(pNode) { var str = ''; var n = 0; if (this.config.inOrder) n = pNode._ai; for (n; n < this.aNodes.length; n++) { if (this.aNodes[n].pid == pNode.id) { var cn = this.aNodes[n]; cn._p = pNode; cn._ai = n; this.setCS(cn); if (!cn.target && this.config.target) { cn.target = this.config.target; } if (cn._hc && !cn._io && this.config.useCookies) { cn._io = this.isOpen(cn.id); } if (!this.config.folderLinks && cn._hc) { cn.url = null; } if (this.config.useSelection && cn.id == this.selectedNode && !this.selectedFound) { cn._is = true; this.selectedNode = n; this.selectedFound = true; } str += this.node(cn, n); if (cn._ls) { break; } } } return str; };
dTree.prototype.node = function(node, nodeId) {
    var str = '<div class="node">' + this.indent(node, nodeId);
    if (this.config.useIcons) {
        if (!node.icon) node.icon = (this.root.id == node.pid) ? this.icon.root : ((node._hc) ? this.icon.folder : this.icon.node);
        if (!node.iconOpen) node.iconOpen = (node._hc) ? this.icon.folderOpen : this.icon.node;
        if (this.root.id == node.pid) {
            node.icon = this.icon.root;
            node.iconOpen = this.icon.root;
        }
        str += '<img id="i' + this.obj + nodeId + '" src="' + ((node._io) ? node.iconOpen : node.icon) + '" alt="" />';
    }
	//添加上复选框
        if (this.config.check == true) {

            str += '<input type="checkbox" class="ami_checkbox" name=check value='+node.id+' id="c' + this.obj + node.id
                    + '" onclick="javascript:' + this.obj + '.cc(' + node.id
                    + ',' + node.pid + ')"/>';
        } 
    if (node.url) {
        str += '<a id="s' + this.obj + nodeId + '" class="' + ((this.config.useSelection) ? ((node._is ? 'nodeSel' : 'node')) : 'node') + '" href="' + node.url + '"';
        if (node.title) str += ' title="' + node.title + '"';
        if (node.target) str += ' target="' + node.target + '"';
        if (this.config.useStatusText) str += ' onmouseover="window.status=\'' + node.name + '\';return true;" onmouseout="window.status=\'\';return true;" ';
        if (this.config.useSelection && ((node._hc && this.config.folderLinks) || !node._hc))
            str += ' onclick="javascript: ' + this.obj + '.s(' + nodeId + ');"';
        str += '>';
    }
    else if ((!this.config.folderLinks || !node.url) && node._hc && node.pid != this.root.id)
        str += '<a href="javascript: ' + this.obj + '.o(' + nodeId + ');" class="node">';
    str += node.name;
    if (node.url || ((!this.config.folderLinks || !node.url) && node._hc)) str += '</a>';
    str += '</div>';
    if (node._hc) {
        str += '<div id="d' + this.obj + nodeId + '" class="clip" style="display:' + ((this.root.id == node.pid || node._io) ? 'block' : 'none') + ';">';
        str += this.addNode(node);
        str += '</div>';
    }
    this.aIndent.pop();
    return str;
};
dTree.prototype.indent = function(node, nodeId) {
    var str = '';
    if (this.root.id != node.pid) {
        for (var n = 0; n < this.aIndent.length; n++)
            str += '<img src="' + ((this.aIndent[n] == 1 && this.config.useLines) ? this.icon.line : this.icon.empty) + '" alt="" />';
        (node._ls) ? this.aIndent.push(0) : this.aIndent.push(1);
        if (node._hc) {
            str += '<a href="javascript: ' + this.obj + '.o(' + nodeId + ');"><img id="j' + this.obj + nodeId + '" src="';
            if (!this.config.useLines) str += (node._io) ? this.icon.nlMinus : this.icon.nlPlus;
            else str += ((node._io) ? ((node._ls && this.config.useLines) ? this.icon.minusBottom : this.icon.minus) : ((node._ls && this.config.useLines) ? this.icon.plusBottom : this.icon.plus));
            str += '" alt="" /></a>';
        } else str += '<img src="' + ((this.config.useLines) ? ((node._ls) ? this.icon.joinBottom : this.icon.join) : this.icon.empty) + '" alt="" />';
    }
    return str;
};
dTree.prototype.setCS = function(node) { var lastId; for (var n = 0; n < this.aNodes.length; n++) { if (this.aNodes[n].pid == node.id) node._hc = true; if (this.aNodes[n].pid == node.pid) lastId = this.aNodes[n].id; } if (lastId == node.id) node._ls = true; };
dTree.prototype.getSelected = function() { var sn = this.getCookie('cs' + this.obj); return ((sn) ? sn : null); };
dTree.prototype.s = function(id) {
    if (!this.config.useSelection) { return; }
    var cn = this.aNodes[id];
    this.selectNode(cn);
    if (cn._hc && !this.config.folderLinks) { return; }
    if (this.selectedNode != id) {
        if (this.selectedNode || this.selectedNode == 0) {
            eOld = document.getElementById("s" + this.obj + this.selectedNode);
            eOld.className = "node";
        }
        eNew = document.getElementById("s" + this.obj + id);
        eNew.className = "nodeSel";
        this.selectedNode = id;
        if (this.config.useCookies) { this.setCookie('cs' + this.obj, cn.id); }
    }
};
dTree.prototype.o = function(id) {
    var _this = this;
    var cn = this.aNodes[id];
    document.getElementById("i" + this.obj + id).src = this.icon.loading;
    setTimeout(function() {
        _this.nodeStatus(!cn._io, id, cn._ls);
        cn._io = !cn._io;
        if (_this.config.closeSameLevel) _this.closeLevel(cn);
        if (_this.config.useCookies) _this.updateCookie();
        _this.toggleNode(cn);
    }, 0);
};
dTree.prototype.toggleNode = function(id) { }
dTree.prototype.selectNode = function(id, name) { }
dTree.prototype.oAll = function(status) { for (var n = 0; n < this.aNodes.length; n++) { if (this.aNodes[n]._hc && this.aNodes[n].pid != this.root.id) { this.nodeStatus(status, n, this.aNodes[n]._ls); this.aNodes[n]._io = status; } } if (this.config.useCookies) { this.updateCookie(); } };
dTree.prototype.openTo = function(nId, bSelect, bFirst){if (!bFirst){for (var n = 0; n < this.aNodes.length; n++){if (this.aNodes[n].id == nId){nId = n; break;} }} var cn = this.aNodes[nId]; if (cn.pid == this.root.id || !cn._p){return;} cn._io = true; cn._is = bSelect; if (this.completed && cn._hc){this.nodeStatus(true, cn._ai, cn._ls);} if (this.completed && bSelect){this.s(cn._ai);} else if (bSelect){this._sn = cn._ai;} this.openTo(cn._p._ai, false, true);};
dTree.prototype.closeLevel = function(node){for (var n = 0; n < this.aNodes.length; n++){if (this.aNodes[n].pid == node.pid && this.aNodes[n].id != node.id && this.aNodes[n]._hc){this.nodeStatus(false, n, this.aNodes[n]._ls); this.aNodes[n]._io = false; this.closeAllChildren(this.aNodes[n]);} }};
dTree.prototype.closeAllChildren = function(node){for (var n = 0; n < this.aNodes.length; n++){if (this.aNodes[n].pid == node.id && this.aNodes[n]._hc){if (this.aNodes[n]._io) this.nodeStatus(false, n, this.aNodes[n]._ls); this.aNodes[n]._io = false; this.closeAllChildren(this.aNodes[n]);} }};
dTree.prototype.nodeStatus = function(status, id, bottom) {
    eDiv = document.getElementById('d' + this.obj + id);
    eJoin = document.getElementById('j' + this.obj + id);
    if (this.config.useIcons) {
        eIcon = document.getElementById('i' + this.obj + id);
        eIcon.src = (status) ? this.aNodes[id].iconOpen : this.aNodes[id].icon;
    }
    eJoin.src = (this.config.useLines) ?
	((status) ? ((bottom) ? this.icon.minusBottom : this.icon.minus) : ((bottom) ? this.icon.plusBottom : this.icon.plus)) :
	((status) ? this.icon.nlMinus : this.icon.nlPlus);
    eDiv.style.display = (status) ? 'block' : 'none';
};
dTree.prototype.clearCookie = function() {
    var now = new Date();
    var yesterday = new Date(now.getTime() - 1000 * 60 * 60 * 24);
    this.setCookie('co' + this.obj, 'cookieValue', yesterday);
    this.setCookie('cs' + this.obj, 'cookieValue', yesterday);
};
dTree.prototype.setCookie = function(cookieName, cookieValue, expires, path, domain, secure) {
    document.cookie =
		escape(cookieName) + '=' + escape(cookieValue)
		+ (expires ? '; expires=' + expires.toGMTString() : '')
		+ (path ? '; path=' + path : '')
		+ (domain ? '; domain=' + domain : '')
		+ (secure ? '; secure' : '');
};
dTree.prototype.getCookie = function(cookieName) {
    var cookieValue = '';
    var posName = document.cookie.indexOf(escape(cookieName) + '=');
    if (posName != -1) {
        var posValue = posName + (escape(cookieName) + '=').length;
        var endPos = document.cookie.indexOf(';', posValue);
        if (endPos != -1) cookieValue = unescape(document.cookie.substring(posValue, endPos));
        else cookieValue = unescape(document.cookie.substring(posValue));
    }
    return (cookieValue);
};
dTree.prototype.updateCookie = function() {
    var str = '';
    for (var n = 0; n < this.aNodes.length; n++) {
        if (this.aNodes[n]._io && this.aNodes[n].pid != this.root.id) {
            if (str) str += '.';
            str += this.aNodes[n].id;
        }
    }
    this.setCookie('co' + this.obj, str);
};
dTree.prototype.isOpen = function(id) {
    var aOpen = this.getCookie('co' + this.obj).split('.');
    for (var n = 0; n < aOpen.length; n++)
        if (aOpen[n] == id) return true;
    return false;
};
if (!Array.prototype.push) {
    Array.prototype.push = function array_push() {
        for (var i = 0; i < arguments.length; i++)
            this[this.length] = arguments[i];
        return this.length;
    }
};
if (!Array.prototype.pop) {
    Array.prototype.pop = function array_pop() {
        lastElement = this[this.length - 1];
        this.length = Math.max(this.length - 1, 0);
        return lastElement;
    }
};
dTree.prototype.draw = function() {
    this.aNodes = new Array();
    this.aIndent = new Array();
    for (var i = 0; i < this.aNodesData.length; i++) {
        var oneNode = this.aNodesData[i];
        this.aNodes[i] = new Node(oneNode.id, oneNode.pid, oneNode.name, oneNode.url, oneNode.title, oneNode.target, oneNode.icon, oneNode.iconOpen, oneNode.open);
    }
    this.rewriteHTML();
};
dTree.prototype.rewriteHTML = function() {
    var str = '';
    var container;
    container = document.getElementById(this.container);
    if (!container) {
        alert('dTree can\'t find your specified container to show your tree.\n\n Please check your code!');
        return;
    }
    if (this.config.useCookies) this.selectedNode = this.getSelected();
    str += this.addNode(this.root);
    if (!this.selectedFound) this.selectedNode = null;
    this.completed = true;
    container.innerHTML = str;
};
dTree.prototype.hasChildren = function(id) { for (var i = 0; i < this.aNodesData.length; i++) { var oneNode = this.aNodesData[i]; if (oneNode.pid == id) { return true; } } return false; };
Array.prototype.remove = function(dx) { if (isNaN(dx) || dx > this.length) { return false; } for (var i = 0, n = 0; i < this.length; i++) { if (this[i] != this[dx]) { this[n++] = this[i] } } this.length -= 1 };
dTree.prototype.remove = function(id) { if (!this.hasChildren(id)) { for (var i = 0; i < this.aNodesData.length; i++) { if (this.aNodesData[i].id == id) { this.aNodesData.remove(i); } } } };
dTree.prototype.cc=function(nodeId, nodePid){
    
    //首先获取这个多选框的id 
    var cs = document.getElementById("c" + this.obj + nodeId).checked;
    
    var n,node = this.aNodes[nodeId];
    
    var len = this.aNodes.length;
    
    for (n=0; n<len; n++) { //如果循环每一个节点 
        if (this.aNodes[n].pid == nodeId) { //如果选中的是非叶子节点,则要将所有的子节点选择和父节点一样 
            document.getElementById("c" + this.obj + this.aNodes[n].id).checked = cs;           
            this.cc(this.aNodes[n].id, nodeId);           
        }         
    }  
    
     if(cs==true){  //当前是选中状态
        var pid=nodePid;  
        var bSearch;  
        do{  
            bSearch=false;  
            for(n=0;n<len;n++){  //循环每一个节点 
                if(this.aNodes[n].id==pid){  //如果循环的节点的id等于PID 
                    document.getElementById("c"+this.obj+pid).checked=true; //那么这个循环的节点应该被选中  
                    pid=this.aNodes[n].pid;  
                    bSearch= true;        
                    break;    
                }    
            }    
        }while(bSearch==true);
      }
      
      if(cs==false){      //如果被取消选择 
        var pid = nodePid;  
        do{    
            for(j=0;j<len;j++){         //循环每一个多选框  如果这个节点的子节点有其他是选中的,则不取消 
                if(this.aNodes[j].pid==pid && document.getElementById("c" + this.obj + this.aNodes[j].id).checked==true){                
                    return;  
                }  
            }  
            if(j==len){   //循环结束 
                for(k=0;k<len;k++){    
                    if(this.aNodes[k].id==pid){   //如果找到父节点 
                        document.getElementById("c"+this.obj+this.aNodes[k].id).checked=false;    
                        pid=this.aNodes[k].pid;   
                        break;  
                    }       
                }    
            }    
        }while(pid!=-1);      
    }
}