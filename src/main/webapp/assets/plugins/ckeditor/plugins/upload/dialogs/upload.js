var imageUploadUrl = "";
var fileUploadUrl = "";
var account = "";
var password = "";
var adminUrl = "";

function parseHtml(content) {
    var obj_list = content.trim().split("|");
    var html = "";
    for(var i=0; i<obj_list.length-1; i++){
        html += parseObject(obj_list[i].trim());
        html += "<p>&nbsp;</p>"
    }
    return html;
}

function parseObject(code) {
    var tokens = code.split("*");
    if(tokens[0] == "image"){
        return '<p style="text-align: center"><img src="' + tokens[1] + '" style="width: 750px" /></p>';
    } else {
        return '<a class="' + tokens[0] + '" src="' + tokens[1] + '" > ' + tokens[2] + '</a>';
    }
}

(function () {
    CKEDITOR.dialog.add("upload",
        function (editor) {
            imageUploadUrl = editor.config.imageUploadUrl;
            fileUploadUrl = editor.config.fileUploadUrl;
            account = editor.config.account;
            password = editor.config.password;
            adminUrl = editor.config.adminUrl;

            var src = "../../assets/plugins/ckeditor/plugins/upload/dialogs/index.html";
            return {
                title: "上传资源",
                minWidth: "650px",
                minHeight: "400px",
                contents: [{
                    id: "tab1",
                    label: "",
                    title: "",
                    expand: true,
                    width: "100%",
                    height: "100%",
                    padding: 0,
                    margin: 0,
                    elements: [{
                        type: "html",
                        style: "width:690px; height:450px;",
                        html: '<iframe id="uploadFrame" src=' + src + ' frameborder="0"></iframe>'
                    }]
                }],
                onOk: function () {
                    var frame = document.getElementById('uploadFrame').contentWindow;
                    var content = frame.document.getElementById('content').value;
                    var html = parseHtml(content);
                    editor.insertHtml(html);
                },
                onShow: function () {
                    document.getElementById("uploadFrame").setAttribute("src", src);
                }
            }
        });
})();