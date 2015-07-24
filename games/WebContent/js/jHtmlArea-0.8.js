/*
* jHtmlArea 0.8 - WYSIWYG Html Editor jQuery Plugin
* Copyright (c) 2013 Chris Pietschmann
* http://jhtmlarea.codeplex.com
* Licensed under the Microsoft Reciprocal License (Ms-RL)
* http://jhtmlarea.codeplex.com/license
*/
(function ($, window) {

    var $jhtmlarea = window.$jhtmlarea = {};
    var $browser = $jhtmlarea.browser = {};
    (function () {
        $browser.msie = false;
        $browser.mozilla = false;
        $browser.safari = false;
        $browser.version = 0;
        
        if (navigator.userAgent.match(/MSIE ([0-9]+)\./)) {
            $browser.msie = true;
            $browser.version = parseFloat(RegExp.$1);
        } else if (navigator.userAgent.match(/Trident\/([0-9]+)\./)) {
            $browser.msie = true;
            $browser.version = RegExp.$1;
            if (navigator.userAgent.match(/rv:([0-9]+)\./)) {
                $browser.version = parseFloat(RegExp.$1);
            }
        }
        if (navigator.userAgent.match(/Mozilla\/([0-9]+)\./)) {
            $browser.mozilla = true;
            if ($browser.version === 0) {
                $browser.version = parseFloat(RegExp.$1);
            }
        }
        if (navigator.userAgent.match(/Safari ([0-9]+)\./)) {
            $browser.safari = true;
            $browser.version = RegExp.$1;
            if (navigator.userAgent.match(/Version\/([0-9]+)\./)) {
                if ($browser.version === 0) {
                    $browser.version = parseFloat(RegExp.$1);
                }
            }
        }
    })();

    $.fn.htmlarea = function (opts) {
        if (opts && typeof (opts) === "string") {
            var args = [];
            for (var i = 1; i < arguments.length; i++) { args.push(arguments[i]); }
            var htmlarea = jHtmlArea(this[0]);
            var f = htmlarea[opts];
            if (f) { return f.apply(htmlarea, args); }
        }
        return this.each(function () { jHtmlArea(this, opts); });
    };
    var jHtmlArea = window.jHtmlArea = function (elem, options) {
        if (elem.jquery) {
            return jHtmlArea(elem[0]);
        }
        if (elem.jhtmlareaObject) {
            return elem.jhtmlareaObject;
        } else {
            return new jHtmlArea.fn.init(elem, options);
        }
    };
    jHtmlArea.fn = jHtmlArea.prototype = {

        // The current version of jHtmlArea being used
        jhtmlarea: "0.8",

        init: function (elem, options) {
            if (elem.nodeName.toLowerCase() === "textarea") {
                var opts = $.extend({}, jHtmlArea.defaultOptions, options);
                elem.jhtmlareaObject = this;

                var textarea = this.textarea = $(elem);
                var container = this.container = $("<div/>").addClass("jHtmlArea").width(textarea.width()).insertAfter(textarea);

                var toolbar = this.toolbar = $("<div/>").addClass("ToolBar").appendTo(container);
                priv.initToolBar.call(this, opts);

                var iframe = this.iframe = $("<iframe/>").height(textarea.height());
                iframe.width(textarea.width());

                var htmlarea = this.htmlarea = $("<div/>").append(iframe);

                container.append(htmlarea).append(textarea.hide());

                priv.initEditor.call(this, opts);
                priv.attachEditorEvents.call(this);

                // Fix total height to match TextArea
                iframe.height(iframe.height() - toolbar.height());
                toolbar.width(textarea.width());
                

                if (opts.loaded) { opts.loaded.call(this); }
            }
        },
        dispose: function () {
            this.textarea.show().insertAfter(this.container);
            this.container.remove();
            this.textarea[0].jhtmlareaObject = null;
        },
        execCommand: function (a, b, c) {
            this.iframe[0].contentWindow.focus();
            
            if ($browser.msie === true && $browser.version >= 11) {
                if (this.previousRange) {
                    var rng = this.previousRange;
                    var sel = this.getSelection()
                    sel.removeAllRanges();
                    sel.addRange(rng);
                }
            }
            
            this.editor.execCommand(a, b || false, c || null);
            this.updateTextArea();
        },
        ec: function (a, b, c) {
            this.execCommand(a, b, c);
        },
        queryCommandValue: function (a) {
            this.iframe[0].contentWindow.focus();
            return this.editor.queryCommandValue(a);
        },
        qc: function (a) {
            return this.queryCommandValue(a);
        },
        getSelectedHTML: function () {
            if ($browser.msie) {
                return this.getRange().htmlText;
            } else {
                var elem = this.getRange().cloneContents();
                return $("<p/>").append($(elem)).html();
            }
        },
        getSelection: function () {
            if ($browser.msie === true && $browser.version < 11) {
                //return (this.editor.parentWindow.getSelection) ? this.editor.parentWindow.getSelection() : this.editor.selection;
                return this.editor.selection;
            } else {
                return this.iframe[0].contentDocument.defaultView.getSelection();
            }
        },
        getRange: function () {
            var s = this.getSelection();
            if (!s) { return null; }
            if (s.getRangeAt) {
                if (s.rangeCount > 0) {
                    return s.getRangeAt(0);
                }
            }
            if (s.createRange) {
                return s.createRange();
            }
            return null;
            //return (s.getRangeAt) ? s.getRangeAt(0) : s.createRange();
        },
        html: function (v) {
            if (v !== undefined) {
                this.textarea.val(v);
                this.updateHtmlArea();
            } else {
                return this.toHtmlString();
            }
        },
        pasteHTML: function (html) {
            this.iframe[0].contentWindow.focus();
            var r = this.getRange();
            if ($browser.msie) {
                r.pasteHTML(html);
            } else if ($browser.mozilla) {
                r.deleteContents();
                r.insertNode($((html.indexOf("<") != 0) ? $("<span/>").append(html) : html)[0]);
            } else { // Safari
                r.deleteContents();
                r.insertNode($(this.iframe[0].contentWindow.document.createElement("span")).append($((html.indexOf("<") != 0) ? "<span>" + html + "</span>" : html))[0]);
            }
            r.collapse(false);
            r.select();
        },
        cut: function () {
            this.ec("cut");
        },
        copy: function () {
            this.ec("copy");
        },
        paste: function () {
            this.ec("paste");
        },
        bold: function () { this.ec("bold"); },
        italic: function () { this.ec("italic"); },
        underline: function () { this.ec("underline"); },
        strikeThrough: function () { this.ec("strikethrough"); },
        image: function (url) {
            if ($browser.msie === true && !url) {
                this.ec("insertImage", true);
            } else {
                this.ec("insertImage", false, (url || prompt("Image URL:", "http://")));
            }
        },
        removeFormat: function () {
            this.ec("removeFormat", false, []);
            this.unlink();
        },
        link: function () {
            if ($browser.msie === true) {
                this.ec("createLink", true);
            } else {
                this.ec("createLink", false, prompt("Link URL:", "http://"));
            }
        },
        unlink: function () { this.ec("unlink", false, []); },
        orderedList: function () { this.ec("insertorderedlist"); },
        unorderedList: function () { this.ec("insertunorderedlist"); },
        superscript: function () { this.ec("superscript"); },
        subscript: function () { this.ec("subscript"); },

        p: function () {
            this.formatBlock("<p>");
        },
        h1: function () {
            this.heading(1);
        },
        h2: function () {
            this.heading(2);
        },
        h3: function () {
            this.heading(3);
        },
        h4: function () {
            this.heading(4);
        },
        h5: function () {
            this.heading(5);
        },
        h6: function () {
            this.heading(6);
        },
        heading: function (h) {
            this.formatBlock($browser.msie === true ? "Heading " + h : "h" + h);
        },

        indent: function () {
            this.ec("indent");
        },
        outdent: function () {
            this.ec("outdent");
        },

        insertHorizontalRule: function () {
            this.ec("insertHorizontalRule", false, "ht");
        },

        justifyLeft: function () {
            this.ec("justifyLeft");
        },
        justifyCenter: function () {
            this.ec("justifyCenter");
        },
        justifyRight: function () {
            this.ec("justifyRight");
        },

        increaseFontSize: function () {
            if ($browser.msie === true) {
                this.ec("fontSize", false, this.qc("fontSize") + 1);
            } else if ($browser.safari) {
                this.getRange().surroundContents($(this.iframe[0].contentWindow.document.createElement("span")).css("font-size", "larger")[0]);
            } else {
                this.ec("increaseFontSize", false, "big");
            }
        },
        decreaseFontSize: function () {
            if ($browser.msie === true) {
                this.ec("fontSize", false, this.qc("fontSize") - 1);
            } else if ($browser.safari) {
                this.getRange().surroundContents($(this.iframe[0].contentWindow.document.createElement("span")).css("font-size", "smaller")[0]);
            } else {
                this.ec("decreaseFontSize", false, "small");
            }
        },

        forecolor: function (c) {
            this.ec("foreColor", false, c !== undefined ? c : prompt("Enter HTML Color:", "#"));
        },

        formatBlock: function (v) {
            this.ec("formatblock", false, v || null);
        },

        showHTMLView: function () {
            this.updateTextArea();
            this.textarea.show();
            this.htmlarea.hide();
            $("ul li:not(li:has(a.html))", this.toolbar).hide();
            $("ul:not(:has(:visible))", this.toolbar).hide();
            $("ul li a.html", this.toolbar).addClass("highlighted");
        },
        hideHTMLView: function () {
            this.updateHtmlArea();
            this.textarea.hide();
            this.htmlarea.show();
            $("ul", this.toolbar).show();
            $("ul li", this.toolbar).show().find("a.html").removeClass("highlighted");
        },
        toggleHTMLView: function () {
            (this.textarea.is(":hidden")) ? this.showHTMLView() : this.hideHTMLView();
        },

        toHtmlString: function () {
            return this.editor.body.innerHTML;
        },
        toString: function () {
            return this.editor.body.innerText;
        },

        updateTextArea: function () {
            this.textarea.val(this.toHtmlString());
        },
        updateHtmlArea: function () {
            this.editor.body.innerHTML = this.textarea.val();
        }
    };
    jHtmlArea.fn.init.prototype = jHtmlArea.fn;

    jHtmlArea.defaultOptions = {
        toolbar: [
        ["html"], ["bold", "italic", "underline", "strikethrough", "|", "subscript", "superscript"],
        ["increasefontsize", "decreasefontsize"],
        ["orderedlist", "unorderedlist"],
        ["indent", "outdent"],
        ["justifyleft", "justifycenter", "justifyright"],
        ["link", "unlink", "image", "horizontalrule"],
        ["p", "h1", "h2", "h3", "h4", "h5", "h6"],
        ["cut", "copy", "paste"]
    ],
        css: null,
        toolbarText: {
            bold: "Bold", italic: "Italic", underline: "Underline", strikethrough: "Strike-Through",
            cut: "Cut", copy: "Copy", paste: "Paste",
            h1: "Heading 1", h2: "Heading 2", h3: "Heading 3", h4: "Heading 4", h5: "Heading 5", h6: "Heading 6", p: "Paragraph",
            indent: "Indent", outdent: "Outdent", horizontalrule: "Insert Horizontal Rule",
            justifyleft: "Left Justify", justifycenter: "Center Justify", justifyright: "Right Justify",
            increasefontsize: "Increase Font Size", decreasefontsize: "Decrease Font Size", forecolor: "Text Color",
            link: "Insert Link", unlink: "Remove Link", image: "Insert Image",
            orderedlist: "Insert Ordered List", unorderedlist: "Insert Unordered List",
            subscript: "Subscript", superscript: "Superscript",
            html: "Show/Hide HTML Source View"
        }
    };
    var priv = {
        toolbarButtons: {
            strikethrough: "strikeThrough", orderedlist: "orderedList", unorderedlist: "unorderedList",
            horizontalrule: "insertHorizontalRule",
            justifyleft: "justifyLeft", justifycenter: "justifyCenter", justifyright: "justifyRight",
            increasefontsize: "increaseFontSize", decreasefontsize: "decreaseFontSize",
            html: function (btn) {
                this.toggleHTMLView();
            }
        },
        initEditor: function (options) {
            var edit = this.editor = this.iframe[0].contentWindow.document;
            edit.designMode = 'on';
            edit.open();
            edit.write(this.textarea.val());
            edit.close();
            if (options.css) {
                var e = edit.createElement('link'); e.rel = 'stylesheet'; e.type = 'text/css'; e.href = options.css; edit.getElementsByTagName('head')[0].appendChild(e);
            }
        },
        initToolBar: function (options) {
            var that = this;

            var menuItem = function (className, altText, action) {
                return $("<li/>").append($("<a href='javascript:void(0);'/>").addClass(className).attr("title", altText).click(function () { action.call(that, $(this)); }));
            };

            function addButtons(arr) {
                var ul = $("<ul/>").appendTo(that.toolbar);
                for (var i = 0; i < arr.length; i++) {
                    var e = arr[i];
                    if ((typeof (e)).toLowerCase() === "string") {
                        if (e === "|") {
                            ul.append($('<li class="separator"/>'));
                        } else {
                            var f = (function (e) {
                                // If button name exists in priv.toolbarButtons then call the "method" defined there, otherwise call the method with the same name
                                var m = priv.toolbarButtons[e] || e;
                                if ((typeof (m)).toLowerCase() === "function") {
                                    return function (btn) { m.call(this, btn); };
                                } else {
                                    return function () { this[m](); this.editor.body.focus(); };
                                }
                            })(e.toLowerCase());
                            var t = options.toolbarText[e.toLowerCase()];
                            ul.append(menuItem(e.toLowerCase(), t || e, f));
                        }
                    } else {
                        ul.append(menuItem(e.css, e.text, e.action));
                    }
                }
            };
            if (options.toolbar.length !== 0 && priv.isArray(options.toolbar[0])) {
                for (var i = 0; i < options.toolbar.length; i++) {
                    addButtons(options.toolbar[i]);
                }
            } else {
                addButtons(options.toolbar);
            }
        },
        attachEditorEvents: function () {
            var t = this;

            var fnHA = function () {
                t.updateHtmlArea();
            };

            this.textarea.click(fnHA).
                keyup(fnHA).
                keydown(fnHA).
                mousedown(fnHA).
                blur(fnHA);

            this.iframe.blur(function () {
                t.previousRange = t.getRange();
            });

            var fnTA = function () {
                t.updateTextArea();
            };

            $(this.editor.body).click(fnTA).
                keyup(fnTA).
                keydown(fnTA).
                mousedown(fnTA).
                blur(fnTA);

            $('form').submit(function () { t.toggleHTMLView(); t.toggleHTMLView(); });
            //$(this.textarea[0].form).submit(function() { //this.textarea.closest("form").submit(function() {


            // Fix for ASP.NET Postback Model
            if (window.__doPostBack) {
                var old__doPostBack = __doPostBack;
                window.__doPostBack = function () {
                    if (t) {
                        if (t.toggleHTMLView) {
                            t.toggleHTMLView();
                            t.toggleHTMLView();
                        }
                    }
                    return old__doPostBack.apply(window, arguments);
                };
            }

        },
        isArray: function (v) {
            return v && typeof v === 'object' && typeof v.length === 'number' && typeof v.splice === 'function' && !(v.propertyIsEnumerable('length'));
        }
    };
})(jQuery, window);
//upload image
var postUrl;
var oFile;
var imageMaxSize = 1048576; // 1MB
var fd;
var imagePreviousBytesLoaded = 0;
var imageBytesUploaded = 0;
var imageBytesTotal = 0;
var imageResultFileSize = '';

function imageSelected(url){
	postUrl=url;
    // get selected file element
    oFile = document.getElementById('image_file').files[0];
    
    // filter for image files
    var rFilter = /^(image\/bmp|image\/gif|image\/jpeg|image\/png|image\/tiff)$/i;
    if (! rFilter.test(oFile.type)) {
    	$("#image_error").show();
        return;
    }

    // little test for filesize
    if (oFile.size > imageMaxSize) {
        $("#image_warnsize").show();
        return;
    }

    // prepare HTML5 FileReader
    var oReader = new FileReader();
        oReader.onload = function(e){

        fd = new FormData();
        fd.append("fileToUpload", oFile);
       
    	startImageUploading();
    };
    // read selected file as DataURL
    oReader.readAsDataURL(oFile);
}
function startImageUploading() {
    // cleanup all temp states
	imagePreviousBytesLoaded = 0;
    $("#image_upload_response").hide();
    $("#image_error").hide();
    $("#image_error2").hide();
    $("#image_abort").hide();
    $("#image_warnsize").hide();
    $("#image_warnMinsize").hide();
    $("#image_progress_percent").html("");
    
    var oProgress =  $("#image_progress");
    oProgress.show();
    oProgress.width("0px");

    // get form data for POSTing
    //var vFD = document.getElementById('upload_form').getFormData(); // for FF3
    //var vFD = new FormData(document.getElementById('upload_form')); 
    // create XMLHttpRequest object, adding few event listeners, and POSTing our data
    var oXHR = new XMLHttpRequest();
    oXHR.upload.addEventListener('progress', uploadImageProgress, false);
    oXHR.addEventListener('load', uploadImageFinish, false);
    oXHR.addEventListener('error', uploadImageError, false);
    oXHR.addEventListener('abort', uploadImageAbort, false);
    oXHR.open('POST', postUrl,true);
    oXHR.send(fd);
    oXHR.onload = function(e) {
        if (this.status == 200) {
           //alert(this.responseText);
           //uploadCompute();
        }
    };

    // set inner timer
    oTimer = setInterval(doImageInnerUpdates, 300);
}
function uploadImageProgress(e) { // upload process in progress
    if (e.lengthComputable) {
    	imageBytesUploaded = e.loaded;
        imageBytesTotal = e.total;
        var iPercentComplete = Math.round(e.loaded * 100 / e.total);
        var iBytesTransfered = imageBytesToSize(imageBytesUploaded);

        $("#image_progress_percent").html(iPercentComplete.toString() + '%');
        $("#image_progress").width((iPercentComplete * 4).toString() + "px");
        $("#image_b_transfered").html(iBytesTransfered);
        if (iPercentComplete == 100) {
            var oUploadResponse =  $("#image_upload_response");
            oUploadResponse.html("<h1>Please wait...processing</h1>");
            oUploadResponse.show();
        }
    } else {
    	$("#image_progress").html("unable to compute");
    }
}
function uploadImageCompute(responseText){
	if(responseText!=null&&responseText!="null"){
		imagesrc=web_url+"/file.jsp?key="+responseText;
	}else{
	    $("#image_error").show();
	    $("#image_error2").show();
	}
}
function uploadImageFinish(e) { // upload successfully finished
    var oUploadResponse = $("#image_upload_response");
    //oUploadResponse.html(e.target.responseText);
    oUploadResponse.html("processing complete!");
    oUploadResponse.show();

    $("#image_progress_percent").html("100%");
    $("#image_progress").width("400px");

    clearInterval(oTimer);
    uploadImageCompute(e.target.responseText);
}

function uploadImageError(e) { // upload error
    $("#image_error2").show();
    clearInterval(oTimer);
}  

function uploadImageAbort(e) { // upload abort
    $("#image_abort").show();
    clearInterval(oTimer);
}
function doImageInnerUpdates() { // we will use this function to display upload speed
    var iCB = imageBytesUploaded;
    var iDiff = iCB - imagePreviousBytesLoaded;

    // if nothing new loaded - exit
    if (iDiff == 0)
        return;

    imagePreviousBytesLoaded = iCB;
    iDiff = iDiff * 2;
    var iBytesRem = imageBytesTotal - imagePreviousBytesLoaded;
    var secondsRemaining = iBytesRem / iDiff;

    // update speed info
    var iSpeed = iDiff.toString() + 'B/s';
    if (iDiff > 1024 * 1024) {
        iSpeed = (Math.round(iDiff * 100/(1024*1024))/100).toString() + 'MB/s';
    } else if (iDiff > 1024) {
        iSpeed =  (Math.round(iDiff * 100/1024)/100).toString() + 'KB/s';
    }
    
    $("#image_speed").html(iSpeed);
    $("#image_remaining").html('| ' + imageSecondsToTime(secondsRemaining));
}
function imageSecondsToTime(secs) { // we will use this function to convert seconds in normal time format
    var hr = Math.floor(secs / 3600);
    var min = Math.floor((secs - (hr * 3600))/60);
    var sec = Math.floor(secs - (hr * 3600) -  (min * 60));

    if (hr < 10) {hr = "0" + hr; }
    if (min < 10) {min = "0" + min;}
    if (sec < 10) {sec = "0" + sec;}
    if (hr) {hr = "00";}
    return hr + ':' + min + ':' + sec;
}
function imageBytesToSize(bytes) {
    var sizes = ['Bytes', 'KB', 'MB'];
    if (bytes == 0) return 'n/a';
    var i = parseInt(Math.floor(Math.log(bytes) / Math.log(1024)));
    return (bytes / Math.pow(1024, i)).toFixed(1) + ' ' + sizes[i];
}
$(document).ready(function() {
	$('#drag-and-drop-zone-htmlArea').dmUploader({
		url : cxp+'/uploadAreaImg.do',
		dataType : 'text',
		allowedTypes: 'image/*',
		onInit : function() {
		},
		onBeforeUpload : function(id) {
			$("#uploadHtmlArea").show();
			update_file_status('uploadHtmlArea', 'uploading', 'Uploading...');
		},
		onNewFile : function(id, file) {
			add_file('uploadHtmlArea', file);
			filename=file.name;
			filesize=bytesToSize(file.size);
		},
		onComplete : function() {
		},
		onUploadProgress : function(id, percent) {
			var percentStr = percent + '%';
			update_file_progress('uploadHtmlArea', percentStr);
		},
		onUploadSuccess : function(id, responseText) {
			if(responseText!=null&&responseText!=""){
				imagesrc=web_url+"/file.jsp?key="+responseText;
				update_file_status('uploadHtmlArea', 'success', 'Upload Complete');
				update_file_progress('uploadHtmlArea', '100%');
			}else{
				update_file_status('uploadHtmlArea', 'error', 'upload image 630X420 size please.');
			}
		},
		onUploadError : function(id, message) {
			update_file_status('uploadHtmlArea', 'error', message);
		},
		onFileTypeError : function(file) {
		},
		onFileWidthError : function(file) {
			
		},
		onFileSizeError : function(file) {
		},	
		onFallbackMode : function(message) {
		}
	});
});
function bytesToSize(bytes) {
	var sizes = [ 'Bytes', 'KB', 'MB' ];
	if (bytes == 0)
		return 'n/a';
	var i = parseInt(Math.floor(Math.log(bytes) / Math.log(1024)));
	return (bytes / Math.pow(1024, i)).toFixed(1) + ' ' + sizes[i];
}
function add_file(id, file) {
	$('#'+id).find('div.progress').width(0);
	$('#'+id).find('span.filename').html(file.name+" Size:"+bytesToSize(file.size));
}
function update_file_status(id, status, message) {
	$('#'+id).find('span.status').html(message).removeClass().addClass("status").addClass("uploading").addClass(status);
}

function update_file_progress(id, percent) {
	$('#'+id).find('div.progress').width(percent);
}