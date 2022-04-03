let c=0;
let path="";
let home="/home"
let user;
$(function () {
    user = $.cookie("user");
    $("#userName").text(user);
    $("#imgUserName").text(user);

    let s = $("#show")
    s.mouseover(function () {
        $("#showDown").attr("class", "dropdown-menu show");
    });
    s.mouseout(function () {
        $("#showDown").attr("class", "dropdown-menu");
    });

    let u = $("#userspace");
    u.mouseover(function () {
        $("#spaceshow").attr("class", "dropdown-menu show");
    });
    u.mouseout(function () {
        $("#spaceshow").attr("class", "dropdown-menu");
    });

    if (location.href.split("type=")[1] != undefined) {
        let type = location.href.split("type=")[1];
        $("#dirs").empty();
        $("#navbarDropdown").hide();
        $("#newDir").hide();
        fileType(type);
    }else {
        path = location.href.split("path=home")[1];
        $("#navbarDropdown").show();
        $("#newDir").show();
        getFiles();
        setDir();
    }

    $("#all").attr("href", "/user/" + user + "?path=home");
    $("#image").attr("href","/user/t/type?type=image")
    $("#doc").attr("href","/user/t/type?type=doc")
    $("#music").attr("href","/user/t/type?type=music")
    $("#video").attr("href","/user/t/type?type=video")
    $("#comp").attr("href","/user/t/type?type=comp")

    $("#download").on('click', function () {
        let t = $("li[aria-checked=true]").each(function () {
            if ($(this).prop("id") != "") {
                let frame = $('<iframe style="display: none;" class="multi-download"></iframe>');
                frame.attr('src', "/user/d/download?id=" + $(this).prop("id"));
                $(document.body).append(frame);
                setTimeout(function() {
                    frame.remove();
                }, 1000);
            }
        });
    });

    $("#delete").on('click', function () {
        $("#ensureDel").click();
    });
    $("#sureDel").on('click', function () {
        let ids ="";
        let t = $("li[aria-checked=true]").each(function () {
            if ($(this).prop("id") != "") {
                ids+=$(this).prop("id")+"/";
            }
        });
        if (ids != "") {
            $.ajax({
                url: "/user/delFiles",
                type:"post",
                data:{
                    'id': ids
                },
                success: function () {
                    console.log("删除成功");
                    location.reload();
                },
                error: function () {
                    alert("由于服务器内部原因导致删除失败，请刷新重试");
                }
            })
        }
    });
    $("#rename").on('click', function () {
        let x = prompt("重命名");
        let t = $("li[aria-checked=true]").each(function () {
            if($(this).prop("id")!="")
            $.ajax({
                url:"/user/u/update",
                data:{
                    'id': $(this).attr("id"),
                    'fileName': x
                },
                type:'post',
                success: function () {
                    console.log("重命名成功")
                    location.reload();
                },
                error: function () {
                    alert("因为系统内部原因导致重命名失败，请刷新重试");
                }
            })
        })
    });
});

function changeColor() {
    $("a[class='list-group-item list-group-item-action list-group-item-dark']").attr("class","list-group-item list-group-item-action list-group-item-light")
}

function fileType(type) {
    let f=$("#files");
    f.empty();
    $.ajax({
        url: "/user/t/type",
        type:"post",
        dataType:'json',
        data:{
          'type':type
        },
        success: function (data) {
            changeColor()
            addFiles(data,f);
        },
        error: function () {
            alert("由于服务器内部原因导致访问失败，请刷新重试");
        }
    })
}

function addFiles(data,f) {
    let len=data.list.length;
    f.append("<li class=\"list-group-item\" aria-checked=\"true\"><div style=\"display: flex;user-select:none\"><span style=\"width: 50%;color: #888\"><i class=\"fa fa-square-o\" onclick=\"setFlag($(this))\" next></i><i class=\"fa fa-check-square-o\" onclick=\"setFlag($(this))\" style=\"color: #09AAFF;display: none\" pre></i>&nbsp;文件名</span><span style=\"width: 30%;color: #888\">大小</span><span style=\"width: 20%;color: #888\">修改日期</span></div></li>")
    for (let i=0;i<len;i++) {
        let x = "<li class=\"list-group-item list-group-item-action\" aria-checked=\"false\">\n" +
            "                    <div style=\"display: flex;user-select:none\">\n" +
            "                        <span style=\"width: 50%;\">\n" +
            "                            <i class=\"fa fa-square-o\" onclick=\"changeChecked($(this),event)\" next></i>\n" +
            "                            <i class=\"fa fa-check-square-o\" onclick=\"changeChecked($(this))\" style=\"color: #09AAFF;display: none\" pre></i>&nbsp\n" +
            "                            <i class=\"fa fa-folder fa-2x\" style=\"color:#F0D7A3\"></i>\n" +
            "                             <span filename>文件名</span>\n" +
            "                        </span>\n" +
            "                        <span style=\"width: 30%;\" filesize>大小</span>\n" +
            "                        <span style=\"width: 20%;\" filedate>修改日期</span>\n" +
            "                    </div>\n" +
            "                </li>";
        f.append(x);
        let l = $("ul li:last-child");
        $("span[filename]:last").text(data.list[i].fileName);
        $("span[filesize]:last").text(data.list[i].fileSize);
        $("span[filedate]:last").text(data.list[i].date);
        l.attr("name", data.list[i].fileName);
        l.attr("id",data.list[i].id)
        let tabi=l.find("i")[2];
        switch (data.list[i].type) {
            case "folder":
                l.attr("onclick", "cd(this.getAttribute('name'))");
                tabi.setAttribute("class", "fa fa-folder fa-2x");
                tabi.setAttribute("style", "color:#F0D7A3");
                break;
            case "doc":
                tabi.setAttribute("class", "fa fa-file-text-o fa-2x");
                tabi.setAttribute("style", "color:darkgray");
                break;
            case "image":
                tabi.setAttribute("class", "fa fa-picture-o fa-2x");
                tabi.setAttribute("style", "color:orange");
                break;
            case "music":
                tabi.setAttribute("class", "fa fa-music fa-2x");
                tabi.setAttribute("style", "color:#107eb1");
                break;
            case "comp":
                tabi.setAttribute("class", "fa fa-file-archive-o fa-2x");
                tabi.setAttribute("style", "color:#f7d493");
                break;
            case "video":
                tabi.setAttribute("class", "fa fa-file-video-o fa-2x");
                tabi.setAttribute("style", "color:#7952b3");
                break;
            case "other":
                tabi.setAttribute("class", "fa fa-themeisle fa-2x");
                tabi.setAttribute("style", "color:red");
                break;
        }
    }
}

function getFiles() {
    let myPath = path == undefined? home : path;
    let f=$("#files")
    f.empty();
    $.ajax({
        url: "/user/getFiles",
        type: 'post',
        data:{
            'user':user,
            'path':myPath
        },
        dataType: 'json',
        success: function (data) {
            addFiles(data,f);
        },
        error: function () {
            alert("请求失败");
        }
    })
}

function uploadFolder() {
    let myPath = path == undefined ? home : path;
    let file = document.getElementById("fileFolder");
    let formData = new FormData();
    let f = new File([myPath], "path");
    formData.append("fileFolder", f);
    for (let i = 0; i < file.files.length; i++) {
        formData.append('fileFolder', file.files[i]);
    }

    let xhr = new XMLHttpRequest();
    let url = "/user/p/uploadFolder";
    xhr.open("post", url);
    xhr.send(formData);
    xhr.onreadystatechange = function () {
        if (xhr.readyState == 4 && xhr.status == 200) {
            let s = xhr.responseText;
            if (s == "success") {
                console.log("上传成功");
            }else {
                alert("上传失败");
            }
        }
    };

}

function upload() {
    let myPath = path == undefined ? home : path;
    let file = document.getElementById("file");
    let formData = new FormData();
    let f = new File([myPath], "path");
    formData.append("file", f);
    for (let i = 0; i < file.files.length; i++) {
        formData.append('file', file.files[i]);
    }

    let xhr = new XMLHttpRequest();
    let url = "/user/p/upload";
    xhr.open("post", url);
    // xhr.setRequestHeader("Content-Type","multipart/form-data; boundary=----WebKitFormBoundarytNbrqcO4pY08rk6I;charset=utf-8")
    xhr.upload.addEventListener('progress', function (e) {
        let al=e.loaded/e.total *100;
        $("#progress").attr("style", "width: " + al + "%;height: 20px;margin-right: 10px");
        $("#number").text(al + "%");
        if(al==100){
            $("#progressButton").hide();
            $("#number").text("上传成功")
            location.reload();
        }else {
            $("#progressButton").show();
        }
    });
    xhr.send(formData);
    xhr.onreadystatechange = function () {
        if (xhr.readyState == 4 && xhr.status == 200) {
            let s = xhr.responseText;
            if (s == "success") {
                console.log("上传成功");
            }else {
                alert("上传失败");
            }
        }
    };
}

function changeChecked(s,e) {
    if (s.attr("next") != undefined) {
        s.parent().parent().parent().attr("aria-checked",true);
        s.hide();
        s.next().show();
        c++;
        w(c);
        check();
    }else {
        c--;
        if(!c){
            w(c)
        }
        check()
        s.parent().parent().parent().attr("aria-checked", false);
        s.hide();
        s.prev().show();
    }
    e=e||event;
    if (e && e.stopPropagation) {
        event.stopPropagation();
    }else {
        window.event.cancelBubble;
    }
}

function check() {
    if (c == 1) {
        $("#rename").attr("disabled",false);
    }else {
        $("#rename").attr("disabled",true);
    }
}

function setFlag(s) {
    if (s.attr("next") != undefined){
        let f = $("#files");
        let x = f.children("li");
        let y = f.find("[class='fa fa-square-o']");
        let z = f.find("[class='fa fa-check-square-o']");
        if (!c) {
            //最上面无效的也会加上去，所以-1
            c = x.length-1;
            w(c);
        }
        check()
        for(let i=0;i<x.length;i++){
            x[i].setAttribute("aria-checked", true);
        }
        for(let i=0;i<y.length;i++){
            y[i].style.display = "none";
        }
        for (let i = 0; i < z.length; i++) {
            z[i].style.display = "inline-block";
        }
    }else {
        c=0;
        w(c);
        let f = $("#files");
        let x = f.children("li");
        let y = f.find("[class='fa fa-square-o']");
        let z = f.find("[class='fa fa-check-square-o']");
        for(let i=0;i<x.length;i++){
            x[i].setAttribute("aria-checked", false);
        }
        for(let i=0;i<y.length;i++){
            y[i].style.display = "inline-block";
        }
        for (let i = 0; i < z.length; i++) {
            z[i].style.display = "none";
        }
    }
}

function w(p) {
    let b = $("#buttons").children("button");
    if (p) {
        for(let i=0;i<b.length;i++){
            if (b[i].getAttribute("id") == "rename") {
                continue
            }
            b[i].disabled=false;
        }
    }else {
        for(let i=0;i<b.length;i++){
            if (b[i].getAttribute("id") == "rename") {
                continue
            }
            b[i].disabled=true;
        }
    }
}

function createDir() {
    let myPath = path == undefined ? home : path;
    let x = prompt("文件夹名称");
    if (x == null) {
        return;
    }
    $.ajax({
        url:"/user/"+user,
        type:'post',
        data: {
            'path': myPath,
            'name': x
        },
        dataType: 'json',
        success: function (data) {
            if (data.code == "success") {
                alert("suc")
                console.log("新建成功");
            }else if (data.code == "alreadyExist") {
                alert("文件夹已存在");
            }else {
                alert("创建失败");
            }
            // location.reload();
        },
        error: function () {
            alert("由于服务器原因导致请求失败，请刷新重试");
        }
    });
}

function cd(name) {
    if (path == undefined) {
        location.href = "./" + user + "?path=" + home;
    }else {
        location.href = location.href + "/" + name;
    }
}

function setDir() {
    let d = $("#dirs");
    let pathArr = path.split("/");
    let len = pathArr.length;
    let H = location.href.split("?")[0];
    if (len > 1) {
        let P = "home";
        d.append("<li class=\"breadcrumb-item\" style=\"color: #007bff;cursor: pointer;\" onclick=\"CH('"+H+"?path=home')\">home</li>");
        for (let i = 1; i < len - 1; i++) {
            P = P +"/"+ pathArr[i];
            d.append("<li class=\"breadcrumb-item\" style=\"color: #007bff;cursor: pointer;\" onclick=\"CH('"+H+"?path="+P+"');\">" + pathArr[i] + "</li>")
        }
        d.append("<li class=\"breadcrumb-item\">" + pathArr[len - 1] + "</li>");
    } else {
        d.append("<li class=\"breadcrumb-item\">home</li>");
    }
}

function CH(HREF) {
    location.href = HREF;
}