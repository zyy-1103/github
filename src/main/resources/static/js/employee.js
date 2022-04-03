let s="color: #09AAFF;background-color:lightgrey";
const vm=Vue.createApp({
    data(){
        return{
            isDel:0,
            isChange:0,
            checked:[],
            msg:[{}],
            newMsg:{},
            isNewCapable: 1,
            updateInfo:'',
            updateData:'',
            searchInfo:'',
            searchData:'',
            currPage:1,
            pages:0,
        }
    },
    methods:{
        transferTo(id){
            location.href = "./xx/"+id
        },
        del(){
            axios({
                url:"./del",
                method:"post",
                data:{
                    strings:this.checked
                }
            }).then(res=>{
                if (res.data == "1") {
                    location.reload();
                }else{
                    alert("删除失败");
                }
            })
        },
        getData(){
            axios({
                url:'./getData',
                method:'post',
                data:{
                    n:this.currPage
                }
            }).then((res)=>{
                this.msg=res.data.data;
            }).catch((e)=>{
                console.log(e)
            })
        },
        update(){
            axios({
                url:"update",
                method:"post",
                data:{
                    id:this.checked[0],
                    info:this.updateInfo,
                    data:this.updateData
                }
            }).then(res=>{
                if(res.data=="1"){
                    location.reload()
                }else{
                    alert("修改失败");
                }
            })
        },
        search(){
            axios({
                url:"search",
                method:"post",
                data:{
                    info: this.searchInfo,
                    data: this.searchData
                }
            }).then(res=>{
                this.msg=res.data.data;
                console.log(this.msg)
            })
        },
        newD(){
            axios({
                url:"touch",
                method:"post",
                headers: {
                    "Content-Type": "application/json;charset=utf-8"
                },
                data:{
                    dljcxx:this.newMsg
                }
            }).then(res=>{
                if(res.data=="1"){
                    alert("操作成功");
                    location.reload()
                }
            }).catch(res=>{
                alert("输入格式错误，请按照提示输入！")
            })
        },
        turn(n){
            //0首页，1上一页，2下一页，3尾页
            if (!n) {
                this.currPage=1;
            }else if (n == 1) {
                if (this.currPage > 1) {
                    this.currPage--;
                }
            }else if (n == 2) {
                if (this.currPage < this.pages) {
                    this.currPage++;
                }
            }else if (n == 3) {
                this.currPage=this.pages-1;
            }
        }
    },
    created(){
        this.getData();
        axios({
            url:"./getPages",
            method:"post"
        }).then(res=>{
            this.pages=res.data;
        })
    },
    watch:{
        checked(){
            if (this.checked.length >= 1) {
                if (this.checked.length == 1) {
                    this.isChange=1;
                }else{
                    this.isChange=0;
                }
                this.isDel=1;
            }else{
                this.isDel=this.isChange=0;
            }
        },
        currPage(){
            this.getData();
        }
    }
}).mount("#all");
