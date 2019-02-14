var file_type = {
    "folder": {
        name: "文件夹",
        icon: "i_folder",
        ext: ""
    },
    "file": {
        name: "文件",
        icon: "i_file",
        ext: ""
    },
    "text": {
        name: "文本文件",
        icon: "i_text_file",
        ext: "txt,js,css,html,htm,xml,jsp,php,java,properties,tld,"
    },
    "image": {
        name: "图片文件",
        icon: "i_jpg_jpeg",
        ext: "jpeg,jpg,png,gif,bmp,"
    },
    "audio": {
        name: "音频文件",
        icon: "i_file_document_p",
        ext: "mp3,wma,wav,"
    },
    "video": {
        name: "视频文件",
        icon: "i_document_video",
        ext: "mp4,avi,mpeg,swf,flv,rmvb,mov,"
    },
    "zip": {
        name: "压缩文件",
        icon: "i_zipped_document",
        ext: "zip,jar,"
    }
}

/**
 * 装载文件
 * @param fileInfoList
 */
function loadFile(fileInfoList) {
    fileInfoList.sort(function (a, b) {
        var compA = a.isFolder ? 1 : -1;
        var compB = b.isFolder ? 1 : -1;
        return compB - compA;
    })
    var items = new Array();
    var length = fileInfoList.length;
    if (length > 0) {
        for (var i = 0; i < length; i++) {
            var info = fileInfoList[i];
            var item = {name: info.name};
            if (info.isFolder) {
                item.typeText = file_type.folder.name;
                item.size = "";
                item.icon = '<i class="'+ file_type.folder.icon +'"></i>';
                item.typeName = "folder";
            } else {
                var fileType = getFileType(info.name);
                item.typeText = fileType.text;
                item.icon = '<i class="'+ fileType.icon +'"></i>';
                item.size = getFileSizeStr(info.size);
                item.typeName = fileType.name;
            }
            items.push(item);
        }
    }
    return items;
}

function getFileType(fileName) {
    var fileType = {};
    var index = fileName.lastIndexOf(".");
    var ext = "";
    if (index != -1) {
        var ext = fileName.substr(index + 1).toLowerCase();
        for (key in file_type) {
            fileType.name = key;
            if (file_type[key].ext.indexOf(ext + ",") != -1) {
                fileType.text = file_type[key].name;
                fileType.icon = file_type[key].icon;
                return fileType;
            }
        }
    }
    fileType.name = "file";
    fileType.text = ext + file_type.file.name;
    fileType.icon = file_type.file.icon;
    return fileType;
}

function getFileSizeStr(fileSize) {
    var size = fileSize;
    if (size < 1000) {
        return size + "B";
    }
    size /= 1024;
    if (size < 1000) {
        return size.toFixed(1) + "KB";
    }
    size /= 1024;
    return size.toFixed(2) + "MB";
}