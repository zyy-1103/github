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
            searchInfo:'ms',
            searchData:'',
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
                    operator:this.newMsg
                }
            }).then(res=>{
                if(res.data=="1"){
                    alert("操作成功");
                    location.reload()
                }
            }).catch(res=>{
                alert("输入格式错误，请按照提示输入！")
            })
        }
    },
    created(){
        this.getData();
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
        }
    }
}).mount("#all");
