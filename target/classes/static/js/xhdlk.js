let tableName = location.href.split("/")[location.href.split("/").length - 1];
let s="color: #09AAFF;background-color:lightgrey";
const vm=Vue.createApp({
    data(){
        return{
            squadron:s,
            brigade:"",
            isDel:0,
            isChange:0,
            checked:[],
            msg:[{}],
            curr:"1",
            isNewCapable: 0,
            updateInfo:'',
            updateData:''
        }
    },
    methods:{
        transferTo(id){
            location.href = "./o/"+tableName+"/"+id
        },
        del(){
            axios({
                url:"./del",
                method:"post",
                data:{
                    strings:this.checked,
                    tableName:tableName
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
                    tableName:tableName
                }
            }).then((res)=>{
                this.msg=res.data.data;
                console.log(this.msg[0])
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
                    data:this.updateData,
                    tableName:tableName
                }
            }).then(res=>{
                if(res.data=="1"){
                    location.reload()
                }else{
                    alert("修改失败");
                }
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
