UE.Editor.prototype._bkGetActionUrl = UE.Editor.prototype.getActionUrl;
UE.Editor.prototype.getActionUrl = function(action) {
    if (action == 'uploadimage') {
        return fzqblog.realpath+ '/ueditorImageUpload.action';
    }else {
        return this._bkGetActionUrl.call(this, action);
    }
}